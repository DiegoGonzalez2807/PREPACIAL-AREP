# Aspectos a revisar para el parcial

## De qué puede ser?
El parcial se centra en el consumo de una API mediante un servidor web, donde este recibe la petición y tiene que ir hasta la API para traer la info e imprimirla en el cliente

### Regla de oro
1. No se permite el uso de Spring o Spark. Estos servirán como intermediarios entre el servidor y el cliente, por lo que la gracia es que en el servidor se revise lo que viene en la consulta y de ahí empezar la conexión con la API

## Arquetipo de Maven
En caso de que no dejen sacar apuntes para la creación del arquetipo, nos vamos a CMD y hacemos lo siguiente:
1. "mvn archetype:generate"
2. Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): 1947: --> ENTER
3. Choose org.apache.maven.archetypes:maven-archetype-quickstart version: --> "1.0"
4. groupID --> edu.escuelaing.arep
5. artifactID --> Parcial
6. Define value for property 'version' 1.0-SNAPSHOT: : --> ENTER


Con estos pasos se genera de manera correcta el proyecto en maven sin necesidad de dependencias

## Cascarones
Para este proyecto se crean tres cascarones principales: 
1. connection -> Encargado de creacr la conexión mediante internet con la API, traer su información JSON y poder enviarla al sitio que necesite el servicio o los datos


2. HttpServer -> Encargado de abrir la conexión entre el servidor que revisa las peticiones y el cliente que es el que pregunta por el clima de una ciudad específica. 


3. writer -> Encargado de revisar los datos y poder ingresarlos en la página del cliente. En este caso también se encarga de quemar la dirección index.html

