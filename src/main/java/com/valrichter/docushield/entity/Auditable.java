package com.valrichter.docushield.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.AlternativeJdkIdGenerator;

import java.time.LocalDateTime;

/**
 * Clase abstracta que proporciona funcionalidad de auditoría para entidades.
 * <br>
 * Esta clase utiliza las siguientes características:
 * <ul>
 *   <li>{@code @MappedSuperclass}: Define que esta clase no se mapeará directamente a una tabla de base de datos, pero sus propiedades serán heredadas por entidades que la extiendan.</li>
 *   <li>{@code @EntityListeners(AuditingEntityListener.class)}: Habilita el seguimiento automático de auditoría, gestionando las fechas de creación y modificación de la entidad.</li>
 *   <li>{@code @JsonIgnoreProperties}: Ignora las propiedades {@code createdAt} y {@code updatedAt} en las respuestas JSON, pero permite que se incluyan en las solicitudes GET.</li>
 *   <li>{@code @Getter}: Proporciona automáticamente los métodos getter para todas las propiedades de la clase.</li>
 * </ul>
 * <p>
 * Las clases que extiendan {@code Auditable} tendrán automáticamente las fechas de creación y modificación manejadas por JPA y serán excluidas en las serializaciones JSON, excepto en solicitudes GET.
 * </p>
 */

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public abstract class Auditable {
    private Long id;
    private String referenceId  = new AlternativeJdkIdGenerator().generateId().toString();
    private Long createdBy;
    private Long updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
