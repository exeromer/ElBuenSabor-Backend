El Buen Sabor - Backend
Descripción del Proyecto
Este repositorio contiene el código fuente de la API RESTful para el proyecto "El Buen Sabor", se ha desarrollado un sistema que permite a los clientes explorar su variada oferta y realizar pedidos tanto de forma presencial en el local como cómodamente desde sus casas o dispositivos móviles. La aplicación está construida sobre una arquitectura robusta que soporta funcionalidades clave como la gestión multi-sucursal, control de stock en tiempo real, un sistema de roles de usuario y un módulo de promociones dinámicas.

Integrantes del Grupo
Franco Albornoz

Gaston Davila

Gonzalo Murguia

Sahid Romero

Roman Martin

Tecnologías Utilizadas
Framework Principal: Spring Boot 3

Lenguaje: Java 17

Persistencia de Datos: Spring Data JPA / Hibernate

Base de Datos: MySQL

Seguridad: Spring Security 6, integrado con Auth0 para autenticación y autorización basada en roles (JWT).

Documentación de API: SpringDoc (OpenAPI 3 / Swagger UI)

Gestor de Dependencias: Maven

Módulos y Funcionalidades Implementadas
1. Gestión de Seguridad y Usuarios
Autenticación y Autorización: Integración con Auth0 para validar tokens JWT. El sistema asigna roles (ADMIN, CLIENTE, EMPLEADO) a los usuarios, y los endpoints están protegidos según estos roles.

Perfiles de Entorno: Configuración dual para desarrollo y producción. Un perfil dev permite deshabilitar la seguridad para facilitar las pruebas, mientras que el perfil por defecto la activa.

2. Lógica Multi-Sucursal
Gestión de Sucursales: El sistema soporta múltiples locales, cada uno con su propio domicilio, horarios y configuración.

Stock por Sucursal: El inventario de insumos se gestiona de manera independiente para cada sucursal a través de la entidad StockInsumoSucursal. Esto asegura un control de stock preciso y localizado.

Disponibilidad de Productos: La posibilidad de pedir un artículo manufacturado se calcula en tiempo real, verificando la disponibilidad de todos sus insumos en la sucursal seleccionada.

3. Gestión de Artículos y Stock
CRUD Completo: Endpoints para la gestión de ArticulosInsumo (ingredientes, bebidas) y ArticulosManufacturados (platos elaborados).

Control de Stock Centralizado y Local: Se proveen controladores para gestionar tanto el stock global de un insumo al crearlo (ArticuloInsumoController) como para ajustar el inventario de una sucursal específica (StockInsumoSucursalController).

4. Flujo de Pedidos y Promociones
Creación de Pedidos: Lógica robusta para procesar pedidos desde el carrito de un cliente. El sistema asocia cada pedido a una sucursal y descuenta el stock correspondiente.

Sistema de Promociones:

Soporte para promociones de tipo "2x1" (precio fijo) y descuento por porcentaje.

Las promociones son asignables a sucursales específicas y tienen vigencia por fecha y hora.

El PedidoService ha sido diseñado para detectar y aplicar automáticamente la promoción más ventajosa al crear un pedido, ajustando precios y garantizando la correcta deducción de stock.

Instrucciones de Instalación y Ejecución
Prerrequisitos
Java JDK 17 o superior.

Apache Maven 3.x.

Un servidor de MySQL en ejecución.

Una cuenta en Auth0 para configurar la autenticación.

1. Configuración de la Base de Datos
En su servidor MySQL, cree una nueva base de datos. Por defecto, el nombre esperado es el_buen_sabor.

CREATE DATABASE el_buen_sabor;

La aplicación está configurada con spring.jpa.hibernate.ddl-auto=update, por lo que las tablas se crearán o actualizarán al iniciar la aplicación.

Carga de Datos Iniciales: Para poblar la base de datos con datos de prueba (sucursales, artículos, promociones, etc.), asegúrese de que la tabla empresa esté vacía antes de la primera ejecución. El DataInitializer se ejecutará automáticamente.

2. Configuración de la Aplicación
Navegue al archivo src/main/resources/application.properties.

Configure la conexión a su base de datos:

spring.datasource.url=jdbc:mysql://localhost:3306/el_buen_sabor
spring.datasource.username=tu_usuario_mysql
spring.datasource.password=tu_contraseña_mysql

Configure sus credenciales de Auth0:

auth0.audience=https://tu-api-audience.com
spring.security.oauth2.resourceserver.jwt.issuer-uri=https://tu-dominio.us.auth0.com/

3. Ejecución de la Aplicación
Puede ejecutar el proyecto en dos modos diferentes:

A) Modo Producción (Seguridad Activada)
Este es el modo por defecto. Asegúrese de que la línea spring.profiles.active=dev esté comentada o no exista en application.properties. Desde la raíz del proyecto, ejecute:

mvn spring-boot:run

En este modo, los endpoints protegidos requerirán un token JWT válido de Auth0.

B) Modo Desarrollo (Seguridad Desactivada)
Este modo es ideal para pruebas ágiles del backend.

Active el perfil dev: En application.properties, descomenta o añade la siguiente línea:

spring.profiles.active=dev

Ejecute la aplicación:

mvn spring-boot:run

En este modo, gracias a DevSecurityConfig.java, todos los endpoints serán públicos y accesibles sin necesidad de autenticación.