package org.alvarowau.controller;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Configuración de la aplicación JAX-RS.
 * <p>
 * Esta clase extiende {@link Application} y establece el {@code ApplicationPath} base para los servicios RESTful de la aplicación.
 * La ruta base para todos los recursos JAX-RS se configurará como "/rest".
 * </p>
 */
@ApplicationPath("/rest")
public class JaxRsApplication extends Application {
    // La clase no necesita implementar ningún método,
    // ya que se basa en la configuración proporcionada por el anotador @ApplicationPath.
}
