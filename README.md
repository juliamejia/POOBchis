Parcial 1
---
**Nombres**: Julia Mejia, Sebastian Rojas

### Descripción del proyecto

* Poobchis es un proyecto creado para un proposito academico por estudiantes de la escuela de ingenieria, esta es una versión del parques original la cual implementa una variedad de nuevas opciones para jugar, así como tipos de usuarios y entre otras caracteristicas que hacen de este juego una version interesante para interactuar y en la que a nivel de desarrollo se puede presenciar el trabaja enfocado a la Programación dirigida a objetos
![image](https://user-images.githubusercontent.com/62759668/190569892-f9d59bbf-a5b5-491f-8f50-fce840c7ec34.png)



### Paso a paso GIT

* Este proyecto inicialmente se encontraba en una fuente local de uno de los integrantes, para cumplir con las expectativas de la asignación se procedio a inicializar y clonar el proyecto a un repositorio remoto\
  <img width="472" alt="image" src="https://user-images.githubusercontent.com/98657146/190298309-104a0fd3-f4f6-4346-be90-93db97295188.png"> \
  <img width="467" alt="image" src="https://user-images.githubusercontent.com/98657146/190298474-af7120eb-b3d1-41d8-ab0c-9339f5e6e1cd.png">

* El otro integrante, pasó a hacer un fork del proyecto para así tener una copia de este archivo sin tener que modificar nada en el original
  ![image](https://user-images.githubusercontent.com/98657146/190655200-d58d510c-989f-4bf4-bef3-fc69da760d64.png)

* Vemos que queda guardado en el repositorio remoto del otro integrante, dando los créditos al autor original 
  ![image](https://user-images.githubusercontent.com/98657146/190655697-0576e1e4-a2fc-4630-b7b4-06b5262e8847.png)

* Posteriormente clona el proyecto para tenerlo en su repositorio local por si quiere hacer un cambio, se clona con el comando “git clone”
  ![image](https://user-images.githubusercontent.com/98657146/190655825-f032a019-a1d0-4208-b45a-14b0bb6a34b0.png)

* Después de esto , se modificó el Readme , añadiendo los comentarios y pasos siguientes para agregar actualizaciones en el repositorio (usando commit y push) para ello se usaron los siguientes comandos:
  ![image](https://user-images.githubusercontent.com/98657146/190655956-af338d15-5e18-4351-9da7-f9958251aa1a.png)

* Vemos que sale el commit que se asignó 
  ![image](https://user-images.githubusercontent.com/98657146/190656095-e9285114-7aa8-4d1e-a80f-a4f61de78767.png)

#### Repositorio remoto y local 
Los repositorios remoto son versiones de un proyecto que estan hospedadas en la red y nos permiten colaborar con otros desarrolladores y el repositorio local es un archivo que tenemos en nuestro ordenador.    
En este caso el repositorio remoto, es el que está en git y el local es el que guardamos en nuestro computador


### CraftsmanShip

* SOLID
  * Single Responsability principle
      * **Mala práctica identificada, bien definida:** Podemos encontrar el metodo moveTile dentro de la clase Player el cual se comporta como un "Metodo Dios"  
        <img width="229" alt="image" src="https://user-images.githubusercontent.com/98657146/190303499-89934b60-b88b-42c0-8b83-261724906a28.png"> \
      Funcionalidades que violan el principio:  
        * Obtiene el indice de su casilla  
          <img width="256" alt="image" src="https://user-images.githubusercontent.com/98657146/190305830-2740ed67-7dcc-403e-ba9f-4c5f51d0b7c1.png">
        * Verifica el tipo de ficha  
          <img width="278" alt="image" src="https://user-images.githubusercontent.com/98657146/190305920-7f953b95-ed67-460e-9dee-8a8f26e5a944.png">
        * Mata/remueve fichas dentro de la casilla  
          <img width="340" alt="image" src="https://user-images.githubusercontent.com/98657146/190306135-c91d1173-890a-487f-ac00-cd704e0dd58e.png">
        * Obtiene un JPanel y un JButton de la view  
          <img width="166" alt="image" src="https://user-images.githubusercontent.com/98657146/190306177-eb5264d5-99b5-47ae-9997-9e177df26c40.png">
        * Redimensiona el JButton  
          <img width="226" alt="image" src="https://user-images.githubusercontent.com/98657146/190306221-41fe7d92-b949-4d0d-bb0d-b71836007eb1.png">
        * Renderisa la vista  
          <img width="93" alt="image" src="https://user-images.githubusercontent.com/98657146/190306269-cafaa271-e37f-42cb-b7b7-019311478aed.png">
        * Informa a la clase principal si hay un ganador  
          <img width="170" alt="image" src="https://user-images.githubusercontent.com/98657146/190306428-ca1579b9-9a54-4cba-aa5d-1e7318f396fd.png">
        * Retorna una casilla  
          <img width="137" alt="image" src="https://user-images.githubusercontent.com/98657146/190306509-4d6b1fc4-524d-4c06-97fb-e361c809d99f.png">
       * **Cómo lo solucionariamos?** Para iniciar se debe proceder a identificar los diferentes procedimientos en el método como lo hicimos en el inciso superior, procediendo así a modulizar la lógica en diferentes funciones que se sean declaradas dentro de este.


   * Interface segregation principle
        * **Mala práctica identificada, bien definida:** Para este punto utilizaremos la estructura diseñada como se ve acontinuación
          ![image](https://user-images.githubusercontent.com/62759668/190555022-e8837b5c-d0e3-442a-be53-dae97fb07ff8.png) \
          Basados en la implementación podemos encontrar que la forma de identificación de la clase Tile es por medio de un String caracteristico, lo cual viola varios principios como Liskov, Interface segregation, entre otros.... También se puede observar que hay un tipo de ficha normal de la cual heredan el resto de tipo de fichas el cuál obliga a que los otros tipos de fichas tengan por defecto toda la lógica de la ficha normal lo cuál nos restringe muchos comportamientos los cuales tendríamos que directamente ir a modificar en caso de extención. Abordaremos la solución planteada enfocadonos en el Interface segregation principle
          ![image](https://user-images.githubusercontent.com/62759668/190550867-f384c4e9-18b7-4091-81a7-4e0033d38653.png) \
          ![image](https://user-images.githubusercontent.com/62759668/190551165-666c4fd2-c17d-4008-af5b-a498389f7c1e.png)
        * **Cómo lo solucionariamos?** Para iniciar creariamos una interface Tile la cual implementarian todos los tipos de fichas que podemos llegar a encontrar, incluyendo la normal. Aplicando un metodo *Move* el cual realizaría el movimiento inidicado según las diferentes especificaciones de las fichas
          ![image](https://user-images.githubusercontent.com/62759668/190559186-a8b6cf3e-680d-4ee2-97a7-af7014430fb0.png) \
          Ahora supongamos que segun cada ficha se requiere un movimiento especial o una accion diferente a las otras (Ej. La FichaCohete mueve de 5 en 5 lo cual realizaria el metodo *move5*, la ficha Ventajosa se mueve de seguro en seguro lo cual realizaria *moveSafe*, etc...) esto nos obligaria a introducir los metodos de acceso en la interface haciendo que todas las fichas tengan que implementar dichos metodos y en dado caso que no sean implementables por el metodo tengan que arrojar una excepción \
          ![image](https://user-images.githubusercontent.com/62759668/190560878-79c1aad3-7de0-4301-8b67-4daee9bdf4e0.png) \
          Situación que se  puede facilmente evitar gracias al principio de Segregación de dependencias de la siguiente manera \
          ![image](https://user-images.githubusercontent.com/62759668/190562856-ac890d2c-d486-43ac-a1be-e53224ae6a0a.png) \
          Se separa las operaciones en varias interfaces como se ve en el diagra, de modo que cada ficha sólo dependerá de de su propia interfaz y de la general, por lo que un cambio en la interfaz general que no implique a algúna ficha no afectará a su implementación y compilación. \
          Al crear interfaces más pequeñas, tenemos la ventaja adicional de que, si es necesario crear nuevas implementaciones de la interfaz para un caso de uso concreto, no necesitaremos implementar también el resto de métodos que no están relacionados con ese caso de uso.
          Cumpliendo con el proposito del principio. **ninguna clase debería depender de métodos que no usa.** 

* Patrón de diseño
    * Un patron que podemos ver que se intento llevar a cabo fue el patron Mediador, ya que en el proyecto podemos encontrar ciertas clases tales como Poobchis las cuales sirven como mediadores para la comunicacion entre la vista y el dominio, esto con el proposito de reducir las dependencias \
      ![image](https://user-images.githubusercontent.com/62759668/190568131-1d5bb29a-bf7e-4c6c-9efe-00bfe7685ff0.png)

* Tipo del patron
    * Este patron se identifica como un patron de comportamiento dirigido a objetos tal como los especifica la siguiente tabla \
      ![image](https://user-images.githubusercontent.com/62759668/190568567-d87bb5f2-8d78-4449-9aa3-9e1bbfaed387.png)
      
### UNIT TEST 
1. Patron de nombramiento:  
    Para los test del proyecto POOBCHIS vemos que todos tienen una estructura parecida, donde aunque en algunos casos se puede entender lo que hace el test con el       simple nombre, entendemos los test mas por la documentación, para corregir esto deberíamos ponerle un nombre mas diciente , con su escenario correspondiente.
    * ![image](https://user-images.githubusercontent.com/98657146/190672756-ac434a4a-c271-455e-8664-34fac36ecd63.png)
      Solución : `void givenOnePerson_whenAddYourNameToPlay_thenCreateThePlayerOnTheBoard ()` Asi podemos entender del nombre que el test quiere verificar que se crea el         jugador en el tablero 
      
    * ![image](https://user-images.githubusercontent.com/98657146/190673613-9f89f284-aff9-4af8-9431-e612591bd6eb.png)
      Solución : `void givenAPlayer_whenRegisteringInTheGame_thenTheGameAllowsYouToPutYourIdentificationData()` Asi podemos entender del nombre que el test quiere               verificar que se le permita al usuario registrarse con sus datos de identificación 
    
    * ![image](https://user-images.githubusercontent.com/98657146/190674034-31b18362-741f-4977-8306-b4f706bfb50b.png)
      Solución : `void givenTheTokensOfThePlayers_WhenTheGameStarts_thenVerifyThatTheyareInTheirRespectiveHouses()` Asi podemos entender con el nombre que el test quiere         verificar que al iniciar el juego , las fichas esten en sus casas  correspondientes

  

