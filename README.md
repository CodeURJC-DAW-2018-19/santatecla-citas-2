# santatecla-citas-2
# Gestor de citas 
## MODELO DEL DOMINIO https://docs.google.com/document/d/1PKqs6eayJti3jBymaytm1CKXEUqmyDpC4xDh7tM-wEk/edit?usp=sharing

- Una comunidad de usuarios desea estructurar el conocimiento que gestionan en Citas que referencian parte de alguna obra bibliográfica. Por ejemplo, el texto de “No hay felicidad o infelicidad en este mundo; solo hay comparación de un estado con otro. Solo un hombre que ha sentido la máxima desesperación es capaz de sentir la máxima felicidad. Es necesario haber deseado morir para saber lo bueno que es vivir” del escritor Alejandro Dumas en la obra “El conde de Monte Cristo”. Por tanto de cada cita, desean introducir el texto, el autor y la obra.

- Por otro lado, quieren gestionar Temas. Por ejemplo, temas posibles serían “El sentido de la Vida”, “La recursividad”, … A cada tema, desean introducir una secuencia de textos en formato Markdown y/o referencias a citas incorporadas en el sistema.

### Un ejemplo concreto (diagrama de objetos) para el tema “El sentido de la vida” sería:

http://www.plantuml.com/plantuml/uml/PP71IWD138RlynG_lGY5BgYtWbAf7ZpeGOhtT2VLARi9p6JH8hwxORhk7WfxoEpt9VmXcsAKRUYxeFiZjuR5UfkC5g22rWly1C3usnHtU5LyqWa7xbAR8aKeaiolfcbSfi4qk_pGyP4aPiNZq5DneljC3fuwj2gHuT-BYZ6sEHNJy3jbSAMmM8hg4Zh2Uug4DNRSdwEDT6fOxXWnVIXClUS4yMhy8tIrX4UhxRv7xQyRpIk_yO4pIvkeqZmz9sMSy6pSLoLv4VupBbTHlsHrVnxhslKbccSwlxdWTQ2Jsp2B65azHDYmHB_J7m00

## Y la estructura (diagrama de clases) correspondiente sería:


http://www.plantuml.com/plantuml/uml/SoWkIImgAStDuKhEIImkLl18BIfApCrCpIj9X8g45fSc5cNcGtdEcN29aDHS1BEY1AOYA3KjjIWrBpaJ9kbPafhBnOLai8AIePA2bcvAVdcUhfs2afOIp0wyYi3suLK1SHmNK1sc7CGL84B1fWOY3d4rgXYow-5CY6cwEQbf9I333A4MEQJcfG033W00
  
### Los usuarios de esta herramienta son:
1. Docente, actor único en el sistema, podrá realizar cualquier funcionalidad expuesta a continuación
2. Estudiante, todos los usuarios registrados, sólo podrán consultar cualquier información disponible en la herramienta
3. Visitante, todos los usuarios no registrados, únicamente podrán acceder al listado de temas y citas de la pantalla de arranque

## REQUISITOS

## Se obtienen los siguientes actores:
- Visitante, para una visión “general” de la información en el sistema, o sea solo el listado de los nombres de los temas y citas disponibles en el sistema
- Estudiante, para una visión detallada de toda la información disponible en el sistema
- Docente, para una visión y edición (altas, bajas y modificaciones) de toda la información disponible en el sistema.
- La información tiene un carácter global: todo elemento (cita, tema, referencia, texto) editado por cualquier docente será accesible por todos estudiantes y/o visitantes, con distinto nivel de detalle. 

## Y, del modelo del dominio anterior , se obtienen los siguientes casos de uso:
### Para Visitante, Estudiante o Docente:
1. Acceder, para el arranque de la aplicación 
2. Login, para registrarse o “loguearse”
### Para Estudiante o Docente:
1. Abrir cita, para acceder a la información de una cita
2. Abrir tema, para acceder a la información de una tema
2. Logout, salir de la aplicación volviendo al arranque en modo Visitante
### Docente
1. Crear cita, para dar de alta una cita en el sistema
2. Modificar campo, para cambiar los campos de una cita del sistema
3. Eliminar cita, para dar de baja una cita del sistema
4. Crear tema, para dar de alta un tema en el sistema
5. Eliminar tema, para dar de baja un tema del sistema
6. Añadir texto a tema, para dar un texto en un tema del sistema
7. Modificar texto de un tema, para cambiar un texto de un tema del sistema
8. Eliminar texto de un tema, para dar de baja un texto de un tema del sistema
9. Añadir referencia a cita, para dar de alta una referencia a una cita en un tema del sistema
10. Eliminar referencia a cita, para dar de baja una referencia a una cita en un tema del sistema
11. Generar pdf, para visualizar la información de un tema en formato pdf

## PROTOTIPO DE INTERFAZ DE USUARIO

### La estructura general de la interfaz se organiza en pestañas:
- Pestaña Inicio: única, no cerrable y posicionada siempre lo más a la izquierda presente tras el arranque de la aplicación
- Pestaña Cita <X>: una por cada cita abierta, cerrable y posicionada a la derecha de la última pestaña abierta. 
- Pestaña Tema <Y>: una por cada tema abierto, cerrable y posicionada a la derecha de la última pestaña abierta

### Pestaña: Inicio  (Pantalla de Acceso)

- Presenta la lista de temas y citas y permite:
- Login, si no está “logueado”
- Crear tema (+) suministrando el nombre a través de un diálogo 
- Eliminar tema (-) avisando al usuario que se pierden los datos
- Abrir tema (click): activando la pestaña de la Unidad con su subpestaña Fichas activa
- Crear cita (+) suministrando el nombre a través de un diálogo 
- Eliminar cita (-) avisando al usuario que se pierden los datos
- Abrir cita (click): activando su pestaña de Unidad con su subpestaña de Itinerario activa
- Logout, si ya está “logueado”

### Pestaña: Cita <X>

- Presenta los campos de la cita permite:
- Modificar campo (click), directamente sobre su campo correspondiente
- Logout, si ya está “logueado”

### Pestaña: Tema <Y>

- Presenta los campos del tema (textos y referencias a citas), junto con su visualización en HTML con la composición secuencial a partir de los textos en markdown y las citas con el formato se que quiera  y permite:
- Crear texto(+), escribiendo el texto con formato Markdown directamente en el campo
- Modificar texto, escribiendo el texto  con formato Markdown directamente en el campo
- Eliminar texto (-), avisando al usuario de la pérdida de datos
- Crear referencia a cita (+), mediante el siguiente diálogo
- Eliminar referencia a cita (-), avisando al usuario de la pérdida de datos
- Generar pdf (boton), abriendo un documento en formato pdf a partir del HTML anterior
- Logout, si ya está “logueado”

### Diálogo: Selección de Cita

- Solo se cierra con botón cerrar o con una cita seleccionada

### Diálogo: Histograma

- Solo se cierra con botón cerrar




### Navegación: Se actualizarán las capturas de pantalla de las páginas principales de la aplicación.
En caso de que haya cambiado la navegación, se deberá actualizar el diagrama de
navegación.
- Encuanto a diagramas de navegacion no ha habido cambios, con lo cual se conserva el mismo diagrama de navegacion.



## • Entorno de desarrollo: Se indicarán cómo instalar y configurar el entorno de desarrollo para
poder compilar y ejecutar la aplicación:

- spring initializing,seinicia un nuevo proyectocon spring sts..


## • Diagrama con las entidades de la base de datos: Se incluirá un diagrama con las entidades
de la base de datos, sus campos y las relaciones entre ellas. Se usará un diagrama de las clases
Java, no un diagrama entidad-relación de base de datos. Acontinuacion tenemos los enlaces a los diagramas de bbdd

https://drive.google.com/file/d/1Z89_eML_9YIrGaXmFbu-Z0z3xnwypS1N/view?usp=sharing
https://docs.google.com/document/d/1vJgvrwgVRnIQIpyoXT9KIyV70pleEv0J80u-dqYfbkk/edit


## • Diagrama de clases y templates: Se creará un diagrama de clases de la aplicación. No se
incluirán ni atributos ni métodos en las clases. Se mostrarán las relaciones entre las clases
(asociación, composición o herencia) y se diferenciará claramente qué clases son @Controller,
@Service, Repository u otro tipo de clases. Para ello se puede usar un código de colores, una
distribución de las clases por partes u otro mecanismo. En este diagrama también se incluirán
los ficheros que contienen los templates y se indicará con qué @Controller se relacionan
- En los siguientes enlaces estan losdiagramas:

https://drive.google.com/file/d/1Z89_eML_9YIrGaXmFbu-Z0z3xnwypS1N/view?usp=sharing
https://docs.google.com/document/d/1vJgvrwgVRnIQIpyoXT9KIyV70pleEv0J80u-dqYfbkk/edit