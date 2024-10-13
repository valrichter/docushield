

## Functional Requirements

### User Account

#### New Account
1. La aplicacion deberia permitir a los usuarios crear nuevas cuentas con informacion basica: email (los emails son unicos) y contraseña.
2. La aplicacion deberia deshabilitar todas las cuentas recien creadas hasta que sean verificadas.
3. La aplicacion deberia enviar un email con un enlace para confirmar la nueva cuenta de usuario.
4. Solo despues de verificar una nueva cuenta el usuario deberia poder iniciar sesion en la aplicacion.

#### Reset Password
1. La aplicacion deberia permitir a los usuarios restablecer su contraseña.
2. La aplicacion deberia enviar un enlace al email del usuario para restablecer su contraseña (el enlace sera invalido despues de ser abierto).
3. La aplicacion deberia mostrar una pantalla con un formulario para restablecer la contraseña cuando se haga click en el enlace.
4. Si la contraseña es restablecida con exito, el usuario deberia poder iniciar sesion usando la nueva contraseña.
5. La aplicacion deberia permitir a los usuarios restablecer su contraseña tantas veces como lo necesiten.

#### MFA (Multi-Factor Authentication)
1. La aplicacion deberia permitir a los usuarios configurar el MFA para ayudar a asegurar su cuenta.
2. El MFA deberia usar un codigo QR en el telefono del usuario.
3. La aplicacion deberia permitir a los usuarios escanear un codigo QR usando una aplicacion de autenticacion en su telefono para configurar MFA.
4. La aplicacion deberia permitir a los usuarios ingresar el codigo QR de su aplicacion de autenticacion movil para iniciar sesion exitosamente.

#### Log In
1. La aplicacion deberia permitir a los usuarios ingresar un email y contraseña para iniciar sesion.
2. Si el MFA no esta configurado, la aplicacion deberia solicitar un codigo QR despues de ingresar el email y contraseña correctos.
3. Despues de 6 intentos fallidos de inicio de sesion, la cuenta del usuario deberia ser bloqueada por 15 minutos (para mitigar ataques de fuerza bruta).
4. Despues de 90 dias, la contraseña del usuario deberia expirar, por lo que no podra iniciar sesion hasta que la contraseña sea actualizada (password rotation).

#### Profile
1. La aplicacion deberia permitir a los usuarios actualizar su informacion basica mientras estan logueados.
2. La aplicacion deberia permitir a los usuarios actualizar su contraseña mientras estan logueados.
3. La aplicacion deberia permitir a los usuarios actualizar la configuracion de su cuenta mientras estan logueados.
4. La aplicacion deberia permitir a los usuarios actualizar su foto de perfil mientras estan logueados.

### Document Management

#### Document List
1. La aplicacion deberia mostrar una lista de todos los documentos subidos en la pagina principal.
2. La aplicacion deberia mostrar algunos detalles sobre cada documento en la lista (name, size, owner, type, etc.).
3. La aplicacion deberia permitir a los usuarios logueados subir nuevos documentos.
4. La aplicacion deberia tener paginacion para la lista de documentos.
5. La aplicacion deberia permitir establecer cuantos documentos mostrar por pagina.
6. La aplicacion deberia permitir buscar documentos por nombre (el resultado tambien deberia incluir paginacion).
7. La aplicacion deberia permitir hacer click en un documento para ver mas detalles.

#### Document Details
1. La aplicacion deberia mostrar los detalles de un documento cuando se haga click en el documento.
2. Los detalles del documento deberian incluir el owner del documento.
3. La aplicacion deberia permitir actualizar el name y la description de un documento (en detail page).
4. La aplicacion deberia permitir descargar un documento (en detail page).
5. La aplicacion deberia permitir eliminar un documento (en detail page).

### Access Control

#### User Role
1. La aplicacion deberia asignar roles a los usuarios.
2. Los roles de la aplicacion deberian contener permisos especificos (authorities).
3. Los roles de la aplicacion deberian otorgar diferentes niveles de acceso.
4. La aplicacion deberia permitir que solo los usuarios con los roles apropiados puedan realizar ciertas acciones.
5. La aplicacion deberia permitir que solo los usuarios sin rol (non-user role) de usuario puedan actualizar la configuracion de la cuenta.
6. La aplicacion deberia permitir que solo los usuarios sin rol (non-user role) de usuario puedan subir roles de cuenta.
7. La aplicacion deberia permitir que solo los usuarios con permiso de "delete" documentos puedan borrar documentos.
8. La aplicacion deberia permitir que solo los usuarios sin rol (non-user role) de usuario puedan ver a otros usuarios en el sistema.

### Audit Trail

#### Audit Tracking
1. La aplicacion deberia llevar un registro de quien creo una entidad (user, document, etc.).
2. La aplicacion deberia llevar un registro de cuando se creo una entidad (user, document, etc.).
3. La aplicacion deberia llevar un registro de quien actualizo una entidad (user, document, etc.).
4. La aplicacion deberia llevar un registro de cuando se actualizo una entidad (user, document, etc.).








