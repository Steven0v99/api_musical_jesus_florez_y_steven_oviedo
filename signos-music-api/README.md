# SIGNOS MUSIC API

## Descripción del proyecto

SIGNOS MUSIC API es un proyecto desarrollado en Java y Spring Boot que tiene como finalidad crear una pequeña aplicación relacionada con el mundo de la música.

La idea del proyecto es permitir gestionar información básica de canciones de un repertorio musical. A través de la aplicación podemos consultar las canciones disponibles, buscar canciones según su género y agregar nuevas canciones.

Se eligió este contexto porque la música hace parte de nuestros intereses y permite desarrollar un ejemplo sencillo y cercano a una situación de la vida real.

## ¿Para qué sirve cada parte del proyecto?

### Cancion.java

Este archivo representa una canción. Aquí definimos la información básica que queremos guardar de cada una, como su nombre, artista, género, duración y un número que permite identificarla.

Su finalidad es establecer qué información tendrá cada canción dentro de nuestra aplicación.

###  CancionDTO.java

Este archivo nos ayuda a organizar la información que recibimos cuando una persona quiere agregar una nueva canción.

Por ejemplo, cuando enviamos el nombre de una canción, su artista, género y duración, estos datos son recibidos y organizados mediante este archivo.
###  CancionService.java

Este archivo se encarga de realizar las principales operaciones con las canciones.

Aquí tenemos las canciones iniciales del proyecto y las funciones que permiten mostrar todas las canciones, buscar una canción específica, buscar canciones por género y agregar una nueva canción.

En palabras sencillas, es la parte que se encarga de trabajar con la información de nuestro repertorio.

###  CancionController.java

Este archivo permite que las personas puedan interactuar con nuestra aplicación.

Aquí definimos las diferentes formas de consultar y enviar información a la API.
Por ejemplo, podemos solicitar todas las canciones, consultar una canción específica mediante su identificador, buscar canciones por género o registrar una nueva canción.

###  SignosMusicApiApplication.java

Este e el archivo principal del proyecto.
Su función es iniciar nuestra aplicación y poner en funcionamiento el sistema para que podamos utilizar los diferentes servicios que hemos creado.

### pom.xml

Este archivo contiene la información necesaria para que el proyecto pueda funcionar correctamente.
También permite identificar las herramientas y componentes que utiliza nuestra aplicación para trabajar con Java y Spring Boot.

### README.md

Este documento sirve como guía y presentación del proyecto.

Aquí explicamos de qué trata nuestra API, para qué fue creada, cómo está organizada y cómo puede ejecutarse.

## Finalidad del proyecto

La finalidad principal de SIGNOS MUSIC API es poner en práctica los conocimientos adquiridos sobre el desarrollo de aplicaciones utilizando Java y Spring Boot.
A través de un contexto relacionado con la música buscamos demostrar cómo una aplicación puede recibir, organizar, consultar y entregar información de una manera sencilla.
Además, el proyecto nos permite comprender mejor cómo funcionan las solicitudes y respuestas entre una aplicación y una persona que desea consultar o registrar información.

## Funcionamiento

La aplicación funciona de manera local y puede ser ejecutada desde el computador utilizando Spring Boot.

Una vez iniciada, podemos acceder a la API mediante:

http://localhost:8080

Para consultar las canciones utilizamos:

http://localhost:8080/api/canciones

De esta manera podemos comprobar que nuestra aplicación está funcionando y que la información de las canciones puede ser consultada correctamente.
## Uso de Ia

Se uso inteligencia artificial principalmente para corregir errores, los que mas costaron fueron debido a que no estaba instalado el maeve, y tambien por problemas con las carpetas y su organisacion, por lo que nos ayudo dando una rama de jerarquia de carpetas, y correcciones

##  Conclusión

SIGNOS MUSIC API es una aplicación sencilla creada con el propósito de aprender y aplicar los conceptos fundamentales de una API REST.

El proyecto combina un tema cercano como la música con los conocimientos adquiridos durante el curso, permitiéndonos construir una aplicación funcional, organizada y fácil de comprender.