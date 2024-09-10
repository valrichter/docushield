# Documento

- **@Auditable** es una anotacion que forma parte de Spring Data JPA  
Proposito: Se utiliza para habilitar el registro automatico de auditorias en las entidades de JPA. Esto permite que ciertas propiedades de una entidad, como quien la creo, cuando fue creada, quien la modifico, y cuando fue modificada, se registren automaticamente cada vez que la entidad se persiste o actualiza en la base de datos.

`CreatedBy (@CreatedBy)`: Indica el usuario que creo la entidad.  
`CreatedDate (@CreatedDate)`: Indica la fecha y hora en que se creo la entidad.  
`LastModifiedBy (@LastModifiedBy)`: Indica el usuario que modifico por ultima vez la entidad.  
`LastModifiedDate (@LastModifiedDate)`: Indica la fecha y hora de la ultima modificacion de la entidad.

- **@MappedSuperclass**
Proposito: Indica que una clase es una superclase que proporciona atributos y mapeos comunes a otras entidades, pero que no debe ser mapeada directamente a una tabla en la base de datos.  
Uso tipico: Se utiliza para definir atributos comunes (como createdAt, updatedAt, id, etc.) y comportamientos que deberian estar presentes en varias entidades. Al marcar una clase con @MappedSuperclass, otras entidades pueden heredar estos atributos, lo que facilita la reutilizacion del codigo.  

- **@JsonIgnoreProperties** en Spring Boot y Jackson  
Proposito: Se utiliza para controlar la serializacion y deserializacion de los atributos de un objeto JSON.  
Parametros:
  - value: Especifica los nombres de los campos que deben ser ignorados.
  - allowGetters: Cuando se establece en true, permite que los campos ignorados sean serializados si tienen metodos getters disponibles. Esto significa que aunque se ignoren los campos durante la deserializacion (es decir, cuando se recibe JSON y se convierte en un objeto Java), aun pueden ser incluidos en el JSON al serializar el objeto Java a JSON.  

- **@PrePersist**: Este metodo se ejecuta antes de que se inserte una nueva entidad en la base de datos. En este caso, esta configurando campos de auditoría como createdAt, createdBy, updatedAt, y updatedBy, y verifica que userId no sea nulo.  

- **@PreUpdate**: Aunque no se muestra aqui, un metodo anotado con @PreUpdate haria algo similar, pero antes de que se actualice una entidad existente.

![Auditable](Screenshot_20240909_211739.png)
