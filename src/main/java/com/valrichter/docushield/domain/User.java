package com.valrichter.docushield.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.valrichter.docushield.entity.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

/**
 * Clase que representa un usuario en el sistema, proporcionando información básica y de autenticación.
 * <p>
 * Esta clase extiende la funcionalidad de auditoría de la clase `Auditable`, lo que permite rastrear quién crea
 * y actualiza las instancias, así como las fechas correspondientes. Además, contiene detalles del usuario
 * como nombre, correo electrónico, roles y opciones de autenticación.
 * </p>
 * <p>
 * Características principales:
 * </p>
 * <ul>
 *   <li>{@code @Entity}: Marca esta clase como una entidad gestionada por JPA.</li>
 *   <li>{@code @Table(name = "users")}: Especifica que esta entidad está mapeada a la tabla "users" en la base de datos.</li>
 *   <li>{@code @JsonInclude(NON_DEFAULT)}: Incluye solo las propiedades no predeterminadas en la serialización JSON.</li>
 *   <li>{@code @Getter}, {@code @Setter}: Proporciona automáticamente los métodos getter y setter para todas las propiedades.</li>
 *   <li>{@code @ToString}: Genera un metodo `toString()` que incluye todos los campos de la clase.</li>
 *   <li>{@code @NoArgsConstructor}, {@code @AllArgsConstructor}: Proporciona constructores sin argumentos y con todos los argumentos respectivamente.</li>
 *   <li>{@code @Builder}: Permite la creación de instancias de la clase mediante el patrón builder.</li>
 * </ul>
 * <p>
 * Campos clave:
 * </p>
 * <ul>
 *   <li>{@code userId}: Identificador único del usuario, no actualizable, único y obligatorio.</li>
 *   <li>{@code firstName}, {@code lastName}: Nombre y apellido del usuario.</li>
 *   <li>{@code email}: Correo electrónico único y obligatorio del usuario.</li>
 *   <li>{@code loginAttempts}: Número de intentos de inicio de sesión fallidos.</li>
 *   <li>{@code lastLogin}: Fecha y hora del último inicio de sesión.</li>
 *   <li>{@code phone}, {@code bio}: Información adicional del usuario, como teléfono y biografía.</li>
 *   <li>{@code roles}: Roles del usuario en el sistema (pendiente crear la clase `Role` para mapear con JPA).</li>
 *   <li>{@code accountNonExpired}, {@code accountNonLocked}, {@code enabled}, {@code mfa}: Indicadores sobre el estado de la cuenta del usuario, como expiración, bloqueo, habilitación y autenticación multifactor (MFA).</li>
 *   <li>{@code qrCodeSecret}, {@code qrCodeImageUri}: Datos relacionados con la autenticación MFA mediante códigos QR, donde el secreto se ignora en las respuestas JSON.</li>
 * </ul>
 * <p>
 * Esta clase proporciona toda la información necesaria para gestionar un usuario dentro del sistema,
 * incluyendo sus datos personales y su estado de autenticación.
 * </p>
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
@JsonInclude(NON_DEFAULT)
public class User extends Auditable {
    @Column(updatable = false, unique = true, nullable = false)
    private String userId;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    private Integer loginAttempts;
    private LocalDateTime lastLogin;
    private String phone;
    private String bio;
    private String imageUrl;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean enabled;
    private boolean mfa;
    @JsonIgnore
    private String qrCodeSecret;
    @Column(columnDefinition = "TEXT")
    private String qrCodeImageUri;
    private String roles; // TODO: create Role class and map here with JPA
}
