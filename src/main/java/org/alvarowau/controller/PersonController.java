package org.alvarowau.controller;

import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.alvarowau.model.Person;
import org.alvarowau.repository.PersonRepository;

import java.net.URI;
import java.util.List;

/**
 * Controlador REST para gestionar operaciones sobre la entidad {@link Person}.
 * <p>
 * Este controlador proporciona endpoints para crear, leer, actualizar y eliminar entidades {@link Person}.
 * </p>
 */
@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class PersonController {

    @Inject
    private PersonRepository repository;

    /**
     * Recupera todas las personas.
     * <p>
     * Este método maneja solicitudes HTTP GET a la ruta "/person" y devuelve una lista de todas las entidades {@link Person}.
     * </p>
     *
     * @return una respuesta HTTP con una lista de entidades {@link Person} en formato JSON
     */
    @GET
    public Response findAll() {
        List<Person> persons = repository.findAll();
        return Response.ok(persons).build();
    }

    /**
     * Recupera una persona por su ID.
     * <p>
     * Este método maneja solicitudes HTTP GET a la ruta "/person/{id}" y devuelve la entidad {@link Person} correspondiente al ID proporcionado.
     * Si no se encuentra la entidad, se devuelve una respuesta HTTP 404.
     * </p>
     *
     * @param id el ID de la persona a recuperar
     * @return una respuesta HTTP con la entidad {@link Person} en formato JSON, o una respuesta HTTP 404 si no se encuentra
     */
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        Person person = repository.findById(id);
        if (person != null) {
            return Response.ok(person).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Person with id " + id + " not found.").build();
        }
    }

    /**
     * Crea una nueva persona.
     * <p>
     * Este método maneja solicitudes HTTP POST a la ruta "/person" para crear una nueva entidad {@link Person}.
     * Devuelve una respuesta HTTP 201 con la ubicación del nuevo recurso.
     * </p>
     *
     * @param person la entidad {@link Person} a crear
     * @return una respuesta HTTP 201 con la ubicación del nuevo recurso
     */
    @POST
    public Response create(Person person) {
        repository.create(person);
        URI location = URI.create("/person/" + person.getId());
        return Response.created(location).build();
    }

    /**
     * Actualiza una persona existente.
     * <p>
     * Este método maneja solicitudes HTTP PUT a la ruta "/person/{id}" para actualizar una entidad {@link Person} existente.
     * Si la entidad con el ID proporcionado no se encuentra, se devuelve una respuesta HTTP 404.
     * </p>
     *
     * @param id el ID de la persona a actualizar
     * @param person la entidad {@link Person} actualizada
     * @return una respuesta HTTP 200 con la entidad actualizada en formato JSON, o una respuesta HTTP 404 si no se encuentra la entidad
     */
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Person person) {
        try {
            person.setId(id);
            repository.update(id, person);
            return Response.ok(person).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    /**
     * Elimina una persona existente.
     * <p>
     * Este método maneja solicitudes HTTP DELETE a la ruta "/person/{id}" para eliminar una entidad {@link Person} existente.
     * Si la entidad con el ID proporcionado no se encuentra, se devuelve una respuesta HTTP 404.
     * </p>
     *
     * @param id el ID de la persona a eliminar
     * @return una respuesta HTTP 204 No Content si la eliminación es exitosa, o una respuesta HTTP 404 si no se encuentra la entidad
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            repository.delete(id); // Asegúrate de que este método maneje correctamente la eliminación
            return Response.noContent().build(); // Devuelve un 204 No Content cuando la eliminación es exitosa
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build(); // Devuelve un 404 si no se encuentra la entidad
        }
    }

}
