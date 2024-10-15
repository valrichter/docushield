package com.valrichter.docushield.domain;

/**
 * Proporciona un contexto de solicitud para almacenar y recuperar el ID del usuario
 * asociado con la operación actual, utilizando un contenedor de tipo {@code ThreadLocal}.
 * <p>
 * Esta clase es útil para almacenar datos específicos de la solicitud en un contexto seguro por hilo,
 * permitiendo que diferentes hilos accedan a su propio valor de ID de usuario sin interferencias.
 * </p>
 */
public class RequestContext {

    /**
     * Contenedor {@code ThreadLocal} que almacena el ID del usuario de forma segura por hilo.
     * Cada hilo tiene su propia copia de esta variable, lo que evita problemas de concurrencia.
     */
    private static final ThreadLocal<Long> USER_ID = new ThreadLocal<>();

    /**
     * Constructor privado para evitar la instanciación de la clase {@code RequestContext}.
     * La clase está diseñada como un utility class (clase de utilidad) y no necesita ser instanciada.
     */
    private RequestContext() {}

    /**
     * Inicia el contexto de la solicitud, eliminando cualquier ID de usuario previamente almacenado.
     * <br>
     * Este metodo es útil para limpiar el contexto antes de manejar una nueva solicitud.
     * </p>
     */
    public static void start() {
        USER_ID.remove();
    }

    /**
     * Establece el ID del usuario en el contexto de la solicitud actual.
     * @param userId el ID del usuario a almacenar. Puede ser {@code null} para limpiar el contexto.
     */
    public static void setUserId(Long userId) {
        USER_ID.set(userId);
    }

    /**
     * Recupera el ID del usuario almacenado en el contexto de la solicitud actual.
     * @return el ID del usuario, o {@code null} si no se ha establecido ningún ID.
     */
    public static Long getUserId() {
        return USER_ID.get();
    }
}

