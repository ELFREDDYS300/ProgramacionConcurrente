# Preguntas para pensar — Respuestas

### Ejercicio 1 — Hola Hilo

**Pregunta:** ¿Qué sucede si llamas a `hilo.run()` en lugar de `hilo.start()`? ¿Cuál es la diferencia?

**Respuesta:**  
Si se utiliza `start()`, Java crea un nuevo hilo y dentro de ese hilo se ejecuta el método `run()`. De esta manera, el programa puede realizar varias tareas al mismo tiempo. En cambio, si llamamos directamente a `run()`, no se crea ningún hilo nuevo, sino que el método se ejecuta normalmente dentro del hilo que hizo la llamada, por ejemplo, `main`. Por eso, en ese caso no existe concurrencia real. Además, un mismo hilo solamente puede iniciarse una vez con `start()`, mientras que `run()` puede llamarse varias veces porque funciona como un método común.

### Ejercicio 2 — Hilos con Runnable

**Pregunta:** ¿Cuál es la principal ventaja de usar `Runnable` en lugar de extender `Thread`?

**Respuesta:**  
Una de las principales ventajas es que Java no permite que una clase herede de dos clases diferentes. Por eso, si una clase ya hereda de `Thread`, no puede heredar de otra clase. Al utilizar `Runnable`, la clase puede seguir heredando de otra clase y, al mismo tiempo, definir la tarea que debe realizar el hilo. También permite separar la tarea del hilo que la ejecuta, haciendo que el código sea más flexible y reutilizable. Por ejemplo, un mismo `Runnable` puede ser utilizado por diferentes hilos o por un `ExecutorService`.

### Ejercicio 3 — Múltiples Hilos Simples

**Pregunta:** ¿El orden de las salidas siempre es el mismo? ¿Por qué sí o por qué no?

**Respuesta:**  
No necesariamente. El orden puede cambiar cada vez que se ejecuta el programa porque los hilos son administrados por el sistema operativo, que decide cuándo darle tiempo de procesamiento a cada uno. También influyen factores como la cantidad de núcleos del procesador y las tareas que se estén ejecutando en ese momento. Aunque todos los hilos tengan un `sleep(100)`, no significa que vayan a continuar exactamente al mismo tiempo. Por eso, las salidas pueden aparecer mezcladas o en distinto orden en diferentes ejecuciones.

### Ejercicio 4 — Compartiendo Datos y `synchronized`

**Pregunta:** ¿Por qué `synchronized` resuelve el problema? ¿Qué es lo que bloquea exactamente?

**Respuesta:**  
El problema aparece porque una operación como `valor++` no se realiza de una sola vez. Primero se lee el valor, después se incrementa y finalmente se guarda el nuevo resultado. Si dos hilos hacen esto al mismo tiempo, pueden trabajar con el mismo valor inicial y terminar perdiéndose alguno de los incrementos. `synchronized` evita esta situación haciendo que solamente un hilo pueda ejecutar esa sección sincronizada del objeto a la vez. Mientras un hilo tiene el bloqueo, los demás deben esperar hasta que lo libere. No se bloquea directamente la variable, sino el acceso a las partes sincronizadas de esa instancia.

### Ejercicio 5 — Productor-Consumidor Simple

**Pregunta:** ¿Por qué es importante el bucle `while` en `wait()` en lugar de un `if`?

**Respuesta:**  
El `while` es necesario porque después de que un hilo sale de `wait()`, la condición que estaba esperando puede haber cambiado. También puede ocurrir que varios hilos sean despertados mediante `notifyAll()`, aunque solamente uno pueda continuar con la tarea. Si se utilizara un `if`, el hilo seguiría ejecutándose sin comprobar nuevamente la condición. En cambio, con `while`, la condición se revisa otra vez y, si todavía no se cumple, el hilo vuelve a esperar. Esto evita errores y hace que el funcionamiento sea más seguro cuando hay varios hilos.

### Ejercicio 6 — Usando Executors y Future

**Pregunta:** ¿Cuál es la ventaja de usar `ExecutorService` sobre la creación manual de hilos con `new Thread()`?

**Respuesta:**  
`ExecutorService` facilita la administración de varios hilos porque permite crear un grupo de hilos que puede reutilizarse para distintas tareas. Esto evita tener que crear y eliminar un hilo nuevo cada vez que aparece una tarea. También permite establecer una cantidad máxima de hilos trabajando al mismo tiempo, por ejemplo, utilizando `newFixedThreadPool(3)`. Otra ventaja es que, mediante `Callable` y `Future`, una tarea puede devolver un resultado que después se obtiene con `future.get()`. Además, el `ExecutorService` cuenta con métodos para cerrar correctamente el grupo de hilos cuando ya no se necesita.

### Ejercicio 7 — CountDownLatch para Sincronización

**Pregunta:** ¿En qué escenarios `CountDownLatch` sería más útil que `Thread.join()`?

**Respuesta:**  
`CountDownLatch` puede resultar más práctico cuando se necesita esperar a que varias tareas terminen, especialmente si esas tareas son administradas por un `ExecutorService` y no se tienen directamente las referencias de cada hilo. Cada tarea puede avisar que terminó mediante `countDown()`, mientras que el hilo que espera utiliza `await()`. También puede utilizarse para coordinar que varios hilos comiencen después de que se cumpla una determinada condición. Otra diferencia es que permite establecer un tiempo máximo de espera para todo el conjunto de tareas. A diferencia de otras herramientas de sincronización, el contador de `CountDownLatch` no se puede reiniciar una vez que llega a cero.