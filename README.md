Parcial 1
---
**Nombres**: Julia Mejia, Sebastian Rojas

### Paso a paso GIT

* Este proyecto inicialmente se encontraba en una fuente local de uno de los integrantes, para cumplir con las expectativas de la asignación se procedio a inicializar y clonar el proyecto a un repositorio remoto\
  <img width="472" alt="image" src="https://user-images.githubusercontent.com/98657146/190298309-104a0fd3-f4f6-4346-be90-93db97295188.png"> \
  <img width="467" alt="image" src="https://user-images.githubusercontent.com/98657146/190298474-af7120eb-b3d1-41d8-ab0c-9339f5e6e1cd.png">

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
          Basados en la implementación podemos encontrar que la forma de identificación de la clase Tile es por medio de un String caracteristico, lo cual viola varios principios como Liskov, Interface segregation, entre otros.... También se puede observar que hay un tipo de ficha normal de la cual heredan el resto de tipo de fichas el cuál obliga a que los otros tipos de fichas tengan por defecto toda la lógica de la ficha normal lo cuál nos restringe muchos comportamientos los cuales tendríamos que directamente ir a modificar en caso de extención. Abordaremos la solución planteada enfocadonos en el Interface segregation principle \
          ![image](https://user-images.githubusercontent.com/62759668/190550867-f384c4e9-18b7-4091-81a7-4e0033d38653.png) \
          ![image](https://user-images.githubusercontent.com/62759668/190551165-666c4fd2-c17d-4008-af5b-a498389f7c1e.png)
        * **Cómo lo solucionariamos?** Para iniciar creariamos una interface Tile la cual implementarian todos los tipos de fichas que podemos llegar a encontrar, incluyendo la normal. No obstante, corregimos 
          

          

          


  
    
  
