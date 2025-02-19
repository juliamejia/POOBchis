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
1. Patrón de nombramiento:  
    Para los test del proyecto POOBCHIS vemos que todos tienen una estructura parecida, donde aunque en algunos casos se puede entender lo que hace el test con el       simple nombre, entendemos los test mas por la documentación, para corregir esto deberíamos ponerle un nombre mas diciente , con su escenario             correspondiente.Para ello vamos a usar la estructura given_when_that.
    * ![image](https://user-images.githubusercontent.com/98657146/190672756-ac434a4a-c271-455e-8664-34fac36ecd63.png)
      Solución : `void givenOnePerson_whenAddYourNameToPlay_thenCreateThePlayerOnTheBoard ()` Asi podemos entender del nombre que el test quiere verificar que se crea el         jugador en el tablero 
      
    * ![image](https://user-images.githubusercontent.com/98657146/190673613-9f89f284-aff9-4af8-9431-e612591bd6eb.png)
      Solución : `void givenAPlayer_whenRegisteringInTheGame_thenTheGameAllowsYouToPutYourIdentificationData()` Asi podemos entender del nombre que el test quiere               verificar que se le permita al usuario registrarse con sus datos de identificación 
    
    * ![image](https://user-images.githubusercontent.com/98657146/190674034-31b18362-741f-4977-8306-b4f706bfb50b.png)
      Solución : `void givenTheTokensOfThePlayers_WhenTheGameStarts_thenVerifyThatTheyareInTheirRespectiveHouses()` Asi podemos entender con el nombre que el test         quiere verificar que al iniciar el juego , las fichas esten en sus casas  correspondientes
2. Patrón AAA
    * Arrange(Organizar): Inicializa los objetos y establece los valores de los datos que vamos a utilizar en el test que lo contiene
    * Act (Actuar): Ejecuta el metodo o la acción que vamos a probar 
    * Asssert (Confirmar): Comprueba que el método de pruebas ejecutado se comporta como teníamos previsto que lo hiciera  
    **Ejemplo Estructura del patrón AAA** 
    ![image](https://user-images.githubusercontent.com/98657146/190697341-cc66a26f-f04c-41c7-9fe2-aa38091365a3.png)  
    
    * Al ejecutar los test de POOBCHIS nos damos cuenta que violan inmediatamente el principio Arrange  ya que al correr cualquier test , este ejecuta y empieza a         interactuar con el usuario , pidiendo los datos que necesita para realizar el test. Para mostrar lo dicho anteriormente, tomamos como ejemplo el                     shouldAllowNameAndColorPlay 
    ![image](https://user-images.githubusercontent.com/98657146/190697823-1e73c71c-300a-42ef-a144-4550b08b6cb4.png)  
    ![image](https://user-images.githubusercontent.com/98657146/190697902-95ab3696-ba6f-48e3-87c4-4371a8f0cfd3.png)  
    ![image](https://user-images.githubusercontent.com/98657146/190698281-21628550-8be2-494f-8fdd-a6858152af30.png)  
    ![image](https://user-images.githubusercontent.com/98657146/190698345-e799867a-bde8-4739-9e93-449376513724.png)  
    Después de digitar los datos necesarios ahí si muestra el resultado del test
    ![image](https://user-images.githubusercontent.com/98657146/190698445-14d6b627-f5e0-4d5e-89b9-0230b6282378.png)  
    **Como lo solucionamos?:** Declarando variables con casos clave que se quieran probar en el código 
    En este ejemplo sería declarar el nombre del jugador y el color para posteriormente correr la prueba y verificar que el resultado que dio es el resultado           esperado.  
    
    * Partiendo de que ninguna prueba cumple la estructura AAA debido a que ninguno declara las variables adecuadamente, podemos ver que algunos test tampoco             cumplen la siguiente sigla que es Ejecutar el método que vamos a probar , como este test: ya que no tiene un método especifico para ver verificar este hecho ,       pero cumple con la ultima A que es comportarse como el desarrollador tenia previsto que se comportara 
    ![image](https://user-images.githubusercontent.com/98657146/190698988-7d8bcfe0-9e91-4f1d-83d7-51b9f35441c3.png)  
    Solución: Tener un método que indique que inició el juego y pueda probar que el jugador inscrito se haya creado 
    
    * Este test viola el assert Ya que esta probando ambas cosas y no sabemos cual de las dos es la que espera el desarrollador que lo hizo 
    ![image](https://user-images.githubusercontent.com/98657146/190699185-e732245c-0351-47a8-807f-cb26adca6442.png)  
    Solución : hacer dos pruebas diferentes para probar cada caso mencionado en el código de la prueba  
    
3. Principios FIRST 
  * Fast(rápido) = los test deben poder ejecutarse en cuestión de segundos , ya que esto nos posibilita ejecutar los test muy frecuentemente y poder detectar bugs       de forma rápida y sencilla 
  * INDEPENDENT(independiente) : todas las pruebas deben ser independientes de las otra y de cualquier sistema externo
  * REPEATABLE(repetible) :  El resultado de las pruebas debe ser el mismo independienteente del servidor 
  * SELF- VALIDATING (auto evaluable): las pruebas deben ser automatizadas para poder ejecutarlas simplemente al pulsar el botón , todo esto podría pasar mientras       realizamos otra tarea 
  * TIMELY (oportuno) : Esta última característica se basa en cuándo deberíamos tener desarrolladas las pruebas, que deben estar desarrolladas lo antes posible y       siempre antes de subir código a producción.  
   **Flujo perfecto de trabajo:**
   1. Escribir las pruebas.
   2. Ejecutar las pruebas. Deben fallar, ya que el código a testear no está todavía desarrollado.
   3. Escribir el código de nuestro producto.
   4. Ejecutar de nuevo las pruebas. En este caso las pruebas deberían de funcionar y darnos un resultado positivo.

   Sabiendo que significa cada principio, revisando el código POOBCHIS nos damos cuenta que: 
   *	RAPIDO: ningun test cumplen este principio ya que después desde que inicia la prueba hasta que termina transcurre mucho tiempo. 
   *	INDEPENDENCIA: Todos los test incumplen con este principio ya que dependen de interaccion humana 
   *	REPETIBLE: Todos los test cumplen con este principio ya que se corren sin importar el servidor
   *	SELF-VALIDATING: ningún test cumple con este principio ya que se requiere una introducción manual de los datos para realizar el test 
   *	TIMELY: ningún test cumple con este principio ya que todos se hicieron en base al código en vez de realizar primero la prueba 


### MVC (modelo-vista-controlador) : son las capas o grupos en los que se organizan las aplicaciones  

* Capa modelo: Encontraremos una representación de los datos del dominio, es decir, aquellas entidades que nos servirán para almacenar información del sistema que      estamos desarrollando y encontraremos la lógica de negocio de la aplicación, es decir la implementación de las reglas, las acciones y restricciones que nos          permiten gestionar las entidades.
   El modelo también será el encargado de incluir mecanismos de persistencia o será capaz de interactuar con ellos  
   En este caso el proyecto POOBCHIS tiene la capa de dominio que es donde se sostiene la lógica del juego y lo que va por detrás de el movimiento de las fichas en    el tablero  

  ![image](https://user-images.githubusercontent.com/98657146/190739058-9149a052-6438-4cbf-8200-44a439bd96a9.png)  
  
* Capa vista: Es responsable de generar la interfaz de nuestra aplicación, es decir, de componer las pantallas, paginas o cualquier tipo de resultado utilizable por   el usuario o cliente del sistema.
Cuando las vistas componen la interfaz de usuario de una aplicación, deberán contener los elementos de interacción que permitan al usuario enviar información e     invocar acciones en el sistema, como botones, cuadros de edición o cualquier otro tipo de elemento  
En este caso el proyecto POOBCHIS tiene la vista ya que tiene una capa presentación la cual se encarga de crear la interfaz del juego e interactuar con el usuario   para guardar los datos del juego  
![image](https://user-images.githubusercontent.com/98657146/190742043-47adc59e-2d7c-4fa4-9959-8afb0e19919e.png)  
Apenas corremos la aplicación, nos pide los datos de los jugadores y a lo ultimo despliega el tablero del juego, de esto se compone la capa de presentación de este proyecto   
  ![image](https://user-images.githubusercontent.com/98657146/190742529-abc62cbe-b8bb-4533-90d2-3d6da926de7c.png)  
  ![image](https://user-images.githubusercontent.com/98657146/190742764-6d14cee6-edb3-4674-8fae-48159a6d540d.png)  
  ![image](https://user-images.githubusercontent.com/98657146/190742973-62f8bb3e-2ab6-42cf-9976-9cbd1522373a.png)  
  ![image](https://user-images.githubusercontent.com/98657146/190743224-0d69c72b-890c-45b1-b66d-1c8237edb8b0.png)  
  ![image](https://user-images.githubusercontent.com/98657146/190743497-2ac89e9f-a814-4d37-8ec2-1b4e029cf688.png)  
  ![image](https://user-images.githubusercontent.com/98657146/190743967-88927f0f-9828-4118-b5c2-d8514ea95c76.png)  
  
  * Controlador: la misión principal de los componentes incluidos en el controlados es actuar como intermediario entre el usuario y el sistema. Serán capaces de         capturar acciones de este sobre la vista, como puede ser la pulsación de un botón o la selección de una opción de menú, interpretarlas y actuar en función de       ellas.  
    Por ultimo , el proyecto POOBCHIS contiene el controlador ya que es el que guarda los datos y pulsaciones que hace el usuario , para mostrar la interfaz             correspondiente de cada acción 

### BIBLIOGRAFIA  
[mvc](https://www.campusmvp.es/recursos/post/que-es-el-patron-mvc-en-programacion-y-por-que-es-util.aspx)  
[first](https://www.paradigmadigital.com/dev/principio-first-aumentar-la-calidad-tests-unitarios/)  
[unitTest](https://www.youtube.com/watch?v=8rKy1juvVmI)  






    
    
    


    


  

