package org.alvarowau.util;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;

/**
 * Proveedor de {@link EntityManager} y {@link EntityManagerFactory} para la aplicación.
 * <p>
 * Esta clase es responsable de crear y proporcionar instancias de {@link EntityManager}
 * y {@link EntityManagerFactory} para la aplicación. Se asegura de que la fábrica de entidades
 * esté correctamente inicializada y proporciona métodos para crear y cerrar instancias de {@link EntityManager}.
 * </p>
 */
@ApplicationScoped
public class EntityManagerProducer {

    /**
     * La fábrica de gestores de entidades utilizada para crear instancias de {@link EntityManager}.
     */
    @PersistenceUnit
    private EntityManagerFactory emf;

    /**
     * Inicializa la fábrica de gestores de entidades después de la construcción del objeto.
     * <p>
     * Este método se llama automáticamente después de que el objeto ha sido construido y
     * asegura que {@link EntityManagerFactory} sea inicializado si no ha sido inyectado.
     * </p>
     */
    @PostConstruct
    public void init() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("default-pu");
        }
    }

    /**
     * Proporciona una instancia de {@link EntityManagerFactory}.
     * <p>
     * Este método es utilizado por el contenedor para proporcionar una instancia de
     * {@link EntityManagerFactory} cuando sea necesario.
     * </p>
     *
     * @return una instancia de {@link EntityManagerFactory}.
     */
    @Produces
    @Default
    public EntityManagerFactory createEntityManagerFactory() {
        return emf;
    }

    /**
     * Proporciona una instancia de {@link EntityManager}.
     * <p>
     * Este método es utilizado por el contenedor para proporcionar una instancia de
     * {@link EntityManager} basada en la {@link EntityManagerFactory}.
     * </p>
     *
     * @return una instancia de {@link EntityManager}.
     */
    @Produces
    @Default
    @Dependent
    public EntityManager createEntityManager() {
        return this.emf.createEntityManager();
    }

    /**
     * Cierra una instancia de {@link EntityManager}.
     * <p>
     * Este método se llama automáticamente para liberar los recursos utilizados por
     * la instancia de {@link EntityManager} cuando ya no es necesaria.
     * </p>
     *
     * @param em la instancia de {@link EntityManager} que debe ser cerrada.
     */
    public void close(@Disposes EntityManager em) {
        if (em.isOpen()) {
            em.close();
        }
    }
}
