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
![Diagrama de Clases](https://raw.githubusercontent.com/CodeURJC-DAW-2018-19/santatecla-citas-2/working_branch/images/diagramaAplicacion.png)
>

## API REST Endpoints

- Quote Endpoint
  1. URI `/api/quote`
  2. Supported Operations
    - GET (If Logged)
    - POST (If admin)
    - PUT (If admin)
    - DELETE ( If admin)
  3. Input/Output
    - Input:
    `{
      "id": 18,
      "text": "Amigo, esto es una cita de prueba",
      "author": "Grupo 4",
      "book": "Pruebas Daw"
    }`
    - Output:
    `{
      "id": 4,
      "text": "Que va si no estaba durmiendo, solo estaba mirando pa´dentro",
      "author": "Tragabuche",
      "book": "Bandolero"
    }`
  4. Status Codes

  5. Examples

-Topic Endpoint
  1. URI `/api/topic`
  2. Supported Operations
     - GET (ALL)
     - POST (If admin)
     - PUT (If admin
     - DELETE (If admin)
  3. Input/Output
  
    # Insertar JSON de Tema
  
## Dockerized App execution
1. Run the script *DAW_BuildScript.sh*
2. Tag the new image called app
3. Push image to the repository
4. Run the command `docker-compose up` this should Start the app
