Android Test
===================

Se utilizaron 4 ramas (una para cada pregunta) .

Pregunta 1
-------------
- Se utilizó un módulo android (mylibrary) para el componente Stepper, se agregaron atributos para customizar el componente por xml y por código


Pregunta 2
-------------
- Se utilizó *SensorManager* y *SensorEventListener* para detectar los movimientos del celular

Pregunta 3
-------------
- Para este demo, se está utilizando 3 módulos *data*, *domain* y *presentation*, además *Retrofit*, OkHttp* y *Picasso*.
	* **data**
		* **api**: Conexión al servidor, interceptors y manejo de errores
		*  **datasource**: Para manejar las peticiones hacia el servidor o data local (*Shared Preferences* o *SQLite*)
		* **mapper**: Validar la integridad de la data
		* **model**: *Request* y *Responses* de los servicios
		* **repository**: Implementa la petición de *datasource* y se comunica con el <kbd>presenter</kbd> de <kbd>presentation</kbd>
	* **domain**
		* **repository**: Interfaces que permiten pasar la data hacia <kbd>interactor</kbd>
		* **model**:  Entidades con data correcta que se mostrará en <kbd>presentation</kbd>
		*  **interactor**: Utiliza las interfaces de <kbd>repository</kbd> para enviar la información hacia el <kbd>presenter</kbd> en <kbd>presentation</kbd>
	
	* **presentation**
		*	**paquete por funcionalidad**
			*	**activity** y **fragment**: Para mostrar todo lo relacionado con UI
			*	**view**: Para comunicar el <kbd>presenter</kbd> con la *actividad* o *fragment*
			*	**presenter**: Para comunicarse con <kbd>interactor</kbd> de <kbd>domain</kbd>, hacer validaciones y enviar la data mediante <kbd>view</kbd>

Pregunta 4
-------------
* Se utilizó <kbd>Canvas</kbd> y <kbd>Paint</kbd> para dibujar un *círculo*, *letras* y una *aguja*.
* Se agregaron atributos por *XML*
