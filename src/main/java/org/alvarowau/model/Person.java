package org.alvarowau.model;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * Representa una entidad de persona en la base de datos.
 * <p>
 * La entidad {@link Person} corresponde a la tabla "Person" en la base de datos y contiene información sobre una persona,
 * incluyendo un identificador único, nombre y correo electrónico.
 * </p>
 */
@Entity
@Table(name = "Person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único de la entidad {@link Person}.
     * <p>
     * Este campo es la clave primaria de la entidad y se genera automáticamente.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_person")
    private Long id;

    /**
     * Nombre de la persona.
     * <p>
     * Este campo almacena el nombre de la persona. No se permiten valores nulos.
     * </p>
     */
    @Basic
    @Column(name = "name")
    private String name;

    /**
     * Correo electrónico de la persona.
     * <p>
     * Este campo almacena el correo electrónico de la persona. Puede ser nulo.
     * </p>
     */
    @Basic
    @Column(name = "email")
    private String email;

    /**
     * Obtiene el identificador único de la persona.
     *
     * @return el identificador único de la persona
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único de la persona.
     *
     * @param id el identificador único de la persona
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la persona.
     *
     * @return el nombre de la persona
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre de la persona.
     *
     * @param name el nombre de la persona
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el correo electrónico de la persona.
     *
     * @return el correo electrónico de la persona
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico de la persona.
     *
     * @param email el correo electrónico de la persona
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
