CinePlusApp.

Integrantes 
- Javiera Tapia
- Joaquín Diez


<u>1. Caso elegido y alcance</u>

  El proyecto elegido corresponde a CinePlus, una aplicación tipo streaming enfocada en la visualización y gestión de contenido audiovisual.
  En esta proyecto se trabajó principalmente en el diseño/UI, las validaciones de formularios, la navegación entre pantallas, la gestión del estado, la persistencia local y el uso de recursos nativos del dispositivo. Se gestiona la autenticación de usuarios mediante un backend basado en Xano.

<u>2. Requisitos y ejecución</u>

**Para el desarrollo se utilizaron las siguientes tecnologías y librerías:**

-Kotlin como lenguaje principal.

-Android Studio como entorno de desarrollo.

-Jetpack Compose y Material 3 para la interfaz de usuario.

-ViewModel para el manejo de estado.

-Navigation Component para la navegación entre pantallas.

**Pasos para ejecutar el proyecto:**

1.Descargar el repositorio desde GitHub.

2.Clonar el proyecto en Android Studio --> git clone https://github.com/JaviTapiaq/CinePlusApp.git

3.Sincronizar Gradle y esperar a que finalice la configuración.

4.Seleccionar el perfil “app” y ejecutar en un emulador Android.

<u>3. Arquitectura y flujo</u>

**Carpetas principales de la estructura del proyecto:**

- ui/screens: contiene las pantallas principales (Login, Registro, Perfil e Inicio).

- viewmodel: incluye la lógica de negocio, validaciones y manejo del estado.

- navigation: define las rutas y el flujo entre pantallas.

- theme: agrupa los colores y tipografías personalizadas.

- assets: almacena datos locales del usuario.

**Gestión de estado:**
Se implementó una estrategia local basada en ViewModel, con un flujo de datos unidireccional:
usuario → acción → estado → UI.

**Navegación:**
Se usó un stack simple mediante Navigation Component, con control del backstack para asegurar el retorno correcto a la pantalla de inicio de sesión.

<u>4. Funcionalidades principales</u>

- Formulario validado para el inicio de sesión (usuario demo).

- Navegación fluida entre pantallas, con control de sesión.

- Manejo de estado visual (carga, éxito y error).

- Persistencia local del usuario, incluso sin conexión a internet.

- Almacenamiento de la imagen de perfil como recurso local.

- Uso de recursos nativos del dispositivo, como cámara y galería, incluyendo permisos y mecanismos de respaldo.

- Búsqueda de películas por título.
  
- Visualizacion de cartelera
  
- Visualización de información de las películas:
  - Imagen
  - Categoría
  - Restricción de edad
  - Duración
  - Horarios
    
- Consumo de API REST desde backend NoSQL --> XANO.
  
- Interfaz moderna y responsiva usando Jetpack Compose.


## Endpoints utilizados
Base_url: https://x8ki-letl-twmt.n7.xano.io/api:KxXEX5K_/

| Método | Endpoint       | Descripción |
|------|---------------|-------------|
| POST | /auth/login   | Inicio de sesión |
| POST | /auth/signup  | Registro de usuario |
| GET  | /auth/me      | Obtener usuario autenticado |

Base_url: https://x8ki-letl-twmt.n7.xano.io/api:KxXEX5K_/
| Método | Endpoint | Descripción |
|------|----------|-------------|
| GET  | /movies  | Obtener listado de películas |



