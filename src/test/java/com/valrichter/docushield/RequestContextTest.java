package com.valrichter.docushield;

import static org.junit.jupiter.api.Assertions.*;

import com.valrichter.docushield.domain.RequestContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RequestContextTest {
    @BeforeEach
    public void setUp() {
        // Asegúrate de limpiar el contexto antes de cada prueba
        RequestContext.start();  // Asegúrate de que este metodo limpia correctamente el ThreadLocal
    }

    @AfterEach
    public void tearDown() {
        // Asegúrate de limpiar el contexto después de cada prueba
        RequestContext.start(); // Asegúrate de que este metodo limpia correctamente el ThreadLocal
    }

    @Test
    public void testSetUserIdAndGetUserId() {
        Long userId = 123L;
        RequestContext.setUserId(userId);

        // Verifica que el ID del usuario se haya establecido correctamente
        assertEquals(userId, RequestContext.getUserId());
    }

    @Test
    public void testGetUserIdWhenNotSet() {
        // Verifica que el ID del usuario sea null si no se ha establecido
        assertNull(RequestContext.getUserId());
    }

    @Test
    public void testSetUserIdToNull() {
        RequestContext.setUserId(456L);
        assertNotNull(RequestContext.getUserId());

        // Establece el ID del usuario a null
        RequestContext.setUserId(null);
        // Verifica que el ID del usuario sea null
        assertNull(RequestContext.getUserId());
    }

    @Test
    public void testThreadLocalIsolation() throws InterruptedException {
        // Establecer un ID en el hilo principal
        RequestContext.setUserId(1L);
        assertEquals(Long.valueOf(1), RequestContext.getUserId());

        // Crear un nuevo hilo y establecer un ID diferente
        Thread thread = new Thread(() -> {
            RequestContext.setUserId(2L);
            assertEquals(Long.valueOf(2), RequestContext.getUserId());
        });

        thread.start();
        thread.join(); // Espera a que el hilo termine

        // Verifica que el ID del usuario en el hilo principal no se haya alterado
        assertEquals(Long.valueOf(1), RequestContext.getUserId());
    }
}
