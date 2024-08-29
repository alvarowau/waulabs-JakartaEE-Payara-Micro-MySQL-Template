package org.alvarowau.repository;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.alvarowau.model.Person;

import java.util.List;

/**
 * Repositorio JPA para la entidad {@link Person}.
 * <p>
 * Esta clase proporciona métodos para crear, leer, actualizar y eliminar instancias de la entidad {@link Person}.
 * Se asegura de que todas las operaciones se realicen dentro de una transacción de tipo REQUIRES_NEW.
 * </p>
 */
@RequestScoped
@Transactional(Transactional.TxType.REQUIRED)
public class PersonRepository {

    @Inject
    private EntityManager manager;

    /**
     * Crea una nueva entidad {@link Person}.
     * <p>
     * Este método persiste la entidad {@link Person} en la base de datos.
     * </p>
     *
     * @param person la entidad {@link Person} a crear
     */
    public void create(Person person) {
        manager.persist(person);
    }

    /**
     * Encuentra una entidad {@link Person} por su ID.
     * <p>
     * Este método busca la entidad {@link Person} en la base de datos utilizando su ID.
     * Si no se encuentra ninguna entidad con el ID proporcionado, se devuelve {@code null}.
     * </p>
     *
     * @param id el ID de la entidad {@link Person}
     * @return la entidad {@link Person} encontrada, o {@code null} si no se encuentra
     */
    public Person findById(Long id) {
        return manager.find(Person.class, id);
    }

    /**
     * Actualiza una entidad {@link Person} existente.
     * <p>
     * Este método actualiza los datos de una entidad {@link Person} existente en la base de datos.
     * Si no se encuentra ninguna entidad con el ID proporcionado, se lanza una excepción {@link EntityNotFoundException}.
     * </p>
     *
     * @param id el ID de la entidad {@link Person} a actualizar
     * @param person la entidad {@link Person} con los datos actualizados
     * @throws EntityNotFoundException si no existe ninguna entidad {@link Person} con el ID proporcionado
     */
    public void update(Long id, Person person) {
        // Recuperar la entidad existente
        Person existingPerson = findById(id);
        if (existingPerson != null) {
            // Actualizar campos
            existingPerson.setName(person.getName());
            existingPerson.setEmail(person.getEmail());
            // Fusionar la entidad actualizada
            manager.merge(existingPerson);
        } else {
            // Manejar el caso donde la entidad no existe
            throw new EntityNotFoundException("Person with id " + id + " not found.");
        }
    }

    /**
     * Elimina una entidad {@link Person}.
     * <p>
     * Este método elimina la entidad {@link Person} de la base de datos.
     * Si no se encuentra ninguna entidad con el ID proporcionado, se lanza una excepción {@link EntityNotFoundException}.
     * </p>
     *
     * @param id el ID de la entidad {@link Person} a eliminar
     * @throws EntityNotFoundException si no existe ninguna entidad {@link Person} con el ID proporcionado
     */
    public void delete(Long id) {
        Person person = findById(id); // Busca la entidad por ID
        if (person != null) {
            manager.remove(person); // Elimina la entidad si existe
        } else {
            throw new EntityNotFoundException("Person with id " + id + " not found."); // Lanza una excepción si la entidad no se encuentra
        }
    }

    /**
     * Recupera todas las entidades {@link Person}.
     * <p>
     * Este método devuelve una lista de todas las entidades {@link Person} almacenadas en la base de datos.
     * </p>
     *
     * @return una lista de todas las entidades {@link Person}
     */
    public List<Person> findAll() {
        return manager.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }
}
