package org.alvarowau.service;

import jakarta.enterprise.context.ApplicationScoped;

/**
 * Servicio para generar mensajes de saludo.
 * <p>
 * Esta clase proporciona un método para crear un mensaje de saludo personalizado basado en el nombre proporcionado.
 * </p>
 */
@ApplicationScoped
public class HelloService {

    /**
     * Crea un mensaje de saludo.
     * <p>
     * Este método toma un nombre como entrada y devuelve un mensaje de saludo en formato "Hello <nombre>".
     * </p>
     *
     * @param name el nombre de la persona a la que se desea saludar.
     * @return un mensaje de saludo personalizado.
     */
    public String createHello(String name) {
        return "Hello " + name;
    }

}
