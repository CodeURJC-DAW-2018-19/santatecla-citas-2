# Quote Manager 
## Team (*Group 4*)

## Tools
- [Git](https://git-scm.com/)
- [Trello](https://trello.com/b/lzf2xt3j/daw-grupo-4)
## Development Environment
- [Intellij Idea](https://www.jetbrains.com/idea/)
- [Visual Studio Code](https://code.visualstudio.com/)
- [Brackets](http://brackets.io/) 

### IDE Configuration and Installation
1. Clone the Repository using `git clone https://github.com/CodeURJC-DAW-2018-19/santatecla-citas-2/tree/working_branch`
2. Open one of the three IDEs from above
  - Intellij
    1. Open the project
    2. Set JDK to version 8
    3. Update maven dependencies `mvn clean install`
    4. Build the .jar file `mvn package`
  - Visual Studio Code
    1.
    2.
    3.
  - Brackets (Used only for the html files, so the application does not run from brackets
  
3. Go to the directory where the .jar has been created
4. Using a console, write ´java -jar jar-name.jar´ this should start the application
5. Enjoy

## [Software Requirement Specification](https://docs.google.com/document/d/1PKqs6eayJti3jBymaytm1CKXEUqmyDpC4xDh7tM-wEk/edit?usp=sharing)

## Scrennshots

![Página Principal](https://github.com/CodeURJC-DAW-2018-19/santatecla-citas-2/blob/working_branch/images/PantallaInicio.png?raw=true)
> Index page
Page shown when searching the default url

![Pantalla Citas](https://github.com/CodeURJC-DAW-2018-19/santatecla-citas-2/blob/working_branch/images/PantallaCitas.png?raw=true)
> Quote Management and visualization Page
Page used by admins to create or edit Quotes, and by users to visualize Quotes

![Pantalla Temas](https://github.com/CodeURJC-DAW-2018-19/santatecla-citas-2/blob/working_branch/images/PantallaTema.png?raw=true)
> Topic Management and visualization Page
Page used by admis to create or edit Topics, and by users to visualize Topics

![PopUp](https://github.com/CodeURJC-DAW-2018-19/santatecla-citas-2/blob/working_branch/images/PopUpCitas.png?raw=true)
> Quote addition PopUp
PopUp generated when the admin wants so add a quote to a certain topic, used to choose existing quotes 

![Pantalla Admin](https://github.com/CodeURJC-DAW-2018-19/santatecla-citas-2/blob/working_branch/images/PantallaAdmin.png?raw=true)
> Index page for admins
Page used by admins to delete items or enter de edition pages

## Navigation Diagram
![Diagrama de Navegación](https://github.com/CodeURJC-DAW-2018-19/santatecla-citas-2/blob/working_branch/images/diagrama-Navegacion.png?raw=true)
>

## Entity Diagram
![Diagrama de Entidades](https://github.com/CodeURJC-DAW-2018-19/santatecla-citas-2/blob/working_branch/images/diagramBBDD.png?raw=true)
> 

## Class Diagram
![Diagrama de Clases](https://github.com/CodeURJC-DAW-2018-19/santatecla-citas-2/blob/working_branch/images/Diagrama-Rest.png?raw=true)
>

## [API REST Endpoints](https://github.com/CodeURJC-DAW-2018-19/santatecla-citas-2/blob/working_branch/API_ENDPOINTS.md)


## Dockerized App execution
1. Run the script *DAW_BuildScript.sh*
2. Tag the new image called app `docker tag app user/repo:tag`
3. Push image to the repository `docker push user/repo:tah`
4. Run the command `docker-compose up` this should Start the app

FASE 4. Documentación:

• Preparación del entorno de desarrollo: Se añadirá a las instrucciones cómo instalar y
configurar el entorno de desarrollo para poder compilar y ejecutar la aplicación SPA con
Angular.
 Configurar entorno de desarrollo:
 Prerrequisitos:
 Instalar  Node.jsversión 8.x o 10.x
 Instalar el gestor de paquetes npm, Angular CLI y Angular dependen de las características y funcionalidades proporcionadas por las bibliotecas que están disponibles en npm.
 Ana vez instalado el Node y npm, abrimos la consola:
   Instale el CLI angular: npm install -g @angular/cli


• Diagrama de clases y templates: Reflejará las clases y templates del código Angular. El
diagrama deberá diferenciar claramente qué elementos son componentes y cómo se relacionan
entre sí (relaciones padre-hijo). También se deberán incluir los servicios y el resto de clases
auxiliares.

![Diagrama Clase ng](https://github.com/CodeURJC-DAW-2018-19/santatecla-citas-2/blob/working_branch/images/Diagrama-Clase-ng.png).

En esta fase además se deberá crear un vídeo, subir ese vídeo a YouTube y enlazar el vídeo al principio
del README. Este vídeo tendrá una duración de entre 2 y 3 minutos y mostrará las principales
funcionalidades de la aplicación web. Uno de los alumnos deberá grabarse la voz describiendo qué está
haciendo según vaya interactuando con la aplicación web. El vídeo deberá mostrar los aspectos más
relevantes de la aplicación web, entre otros:
• Acceso de un usuario anónimo (sin hacer login)
• Hacer login con un estudiante existente y mostrar las funcionalidades adicionales
• Si es necesario usar varios estudiantes (usando varios navegadores web) para que se observe
cómo las acciones de un estudiante afectan a otro (mensajes, puntos, comentarios, solicitudes
de amistad, etc.)
• Login como docente y mostrar las funcionalidades propias del administrador.
• Subida de imágenes.
• Creación de un nuevo estudiante.
• Modificación de los datos de una entidad de la aplicación web (cita, autor, libro, entrega, etc...)
Calificación extra: Control de calidad con tests
Es posible obtener un punto adicional a la nota de la práctica si se realiza un control de calidad del
código. En concreto:
◦ Tests servidor Java/Spring (Fase 2): Se incrementará la nota de la práctica en 0,5 puntos si se
implementan tests unitarios y de sistema del backend Java. Los tests se implementarán en Java
con JUnit. Los tests de sistema se implementarán con Selenium.
◦ Tests cliente TypeScript/Angular (Fase 4): Se incrementará la nota de la práctica en 0,5
puntos si se implementan tests unitarios y de sistema. Los tests se implementarán con Jasmine.
Los tests de sistema se implementarán con Protractor.
El README deberá incluir en cada fase cómo ejecutar los tests desde la línea de comandos.