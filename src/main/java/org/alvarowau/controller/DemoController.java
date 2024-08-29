package org.alvarowau.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.alvarowau.service.HelloService;

/**
 * Controlador REST para el servicio de saludo.
 * <p>
 * Este controlador proporciona un endpoint para obtener un saludo personalizado utilizando el servicio {@link HelloService}.
 * </p>
 */
@Path("/hello")
public class DemoController {

    private final HelloService helloService;

    /**
     * Constructor de {@link DemoController}.
     * <p>
     * Este constructor inyecta una instancia de {@link HelloService} para manejar las solicitudes de saludo.
     * </p>
     *
     * @param helloService una instancia de {@link HelloService} para generar saludos
     */
    @Inject
    public DemoController(HelloService helloService) {
        this.helloService = helloService;
    }

    /**
     * Obtiene un saludo personalizado.
     * <p>
     * Este método maneja solicitudes HTTP GET a la ruta "/hello" y devuelve un saludo personalizado basado en el parámetro de consulta "name".
     * Si no se proporciona un nombre, se utiliza el valor predeterminado "AlvaroWau".
     * </p>
     *
     * @param name el nombre para incluir en el saludo; el valor predeterminado es "AlvaroWau"
     * @return una cadena de texto con el saludo personalizado
     */
    @GET
    public String getHello(@QueryParam("name") @DefaultValue("AlvaroWau") String name) {
        return helloService.createHello(name);
    }
}
