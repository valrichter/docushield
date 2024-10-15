package com.valrichter.docushield.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.valrichter.docushield.domain.RequestContext;
import com.valrichter.docushield.exception.ApiException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.AlternativeJdkIdGenerator;
import java.time.LocalDateTime;

/**
 * Clase abstracta que proporciona funcionalidad de auditoría para entidades que extienden esta clase.
 * <p>
 * Esta clase incluye un identificador único generado automáticamente, junto con las propiedades de auditoría
 * que registran quién creó y quién actualizó la entidad, además de las fechas de creación y modificación.
 * </p>
 * <p>
 * Características principales:
 * </p>
 * <ul>
 *   <li>{@code @MappedSuperclass}: Indica que esta clase no se mapea directamente a una tabla,
 *       pero sus propiedades serán heredadas por otras entidades.</li>
 *   <li>{@code @EntityListeners(AuditingEntityListener.class)}: Habilita el seguimiento de auditoría para las
 *       propiedades relacionadas con fechas y usuarios que crean o modifican la entidad.</li>
 *   <li>{@code @JsonIgnoreProperties}: Omite las propiedades {@code createdAt} y {@code updatedAt}
 *       en la serialización JSON, pero permite que se incluyan en respuestas GET.</li>
 * </ul>
 * <p>
 * Esta clase proporciona auditoría básica para cualquier entidad que la extienda,
 * y garantiza la generación de un identificador único con {@code referenceId} que puede ser usado como referencia externa.
 * </p>
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public abstract class Auditable {

    /**
     * Identificador único de la entidad, generado mediante una secuencia.
     * Esta secuencia asegura que el campo {@code id} sea único para cada entidad y no se pueda actualizar.
     */
    @Id
    @SequenceGenerator(name = "PK_seq", sequenceName = "PK_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PK_seq")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /**
     * Identificador de referencia generado automáticamente, basado en un generador alternativo.
     * Este campo puede ser utilizado como un identificador adicional para referenciar externamente la entidad.
     */
    private final String referenceId = new AlternativeJdkIdGenerator().generateId().toString();

    /**
     * Identificador del usuario que creó la entidad.
     * Este campo es obligatorio y debe ser proporcionado al momento de la creación de la entidad.
     */
    @NotNull
    private Long createdBy;

    /**
     * Identificador del usuario que actualizó por última vez la entidad.
     * Este campo es obligatorio y debe actualizarse cuando la entidad se modifique.
     */
    @NotNull
    private Long updatedBy;

    /**
     * Fecha y hora en la que se creó la entidad. Este campo se inicializa automáticamente
     * cuando la entidad se guarda por primera vez en la base de datos y no es actualizable posteriormente.
     */
    @NotNull
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Fecha y hora en la que se actualizó por última vez la entidad. Este campo se inicializa automáticamente
     * cuando se crea la entidad y se actualiza cada vez que la entidad se modifica.
     */
    @CreatedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Metodo ejecutado automáticamente antes de que la entidad sea persistida por primera vez en la base de datos.
     * <p>
     * Este metodo verifica si existe un ID de usuario en el {@code RequestContext}. Si no hay un usuario,
     * lanza una excepción, ya que es obligatorio un ID de usuario para auditar la creación de la entidad.
     * Además, establece los campos de auditoría {@code createdAt}, {@code createdBy}, {@code updatedBy} y {@code updatedAt}.
     * </p>
     *
     * @throws ApiException si no se encuentra el ID de usuario en el {@code RequestContext}.
     */
    @PrePersist
    public void prePersist() {
        Long userId = RequestContext.getUserId();
        if (userId == null) {
            throw new ApiException("Cannot persist entity without user ID in RequestContext");
        }
        setCreatedAt(LocalDateTime.now());
        setCreatedBy(userId);
        setUpdatedBy(userId);
        setUpdatedAt(LocalDateTime.now());
    }

    /**
     * Metodo ejecutado automáticamente antes de que la entidad sea actualizada en la base de datos.
     * <p>
     * Este metodo verifica si existe un ID de usuario en el {@code RequestContext}. Si no hay un usuario,
     * lanza una excepción, ya que es obligatorio un ID de usuario para auditar la actualización de la entidad.
     * Además, actualiza los campos de auditoría {@code updatedAt} y {@code updatedBy}.
     * </p>
     *
     * @throws ApiException si no se encuentra el ID de usuario en el {@code RequestContext}.
     */
    @PreUpdate
    public void beforeUpdate() {
        Long userId = RequestContext.getUserId();
        if (userId == null) {
            throw new ApiException("Cannot persist entity without user ID in RequestContext");
        }
        setUpdatedAt(LocalDateTime.now());
        setUpdatedBy(userId);
    }

}

