# Stub de Java de Hornero modificado

## Configurar el torneo y problema

Para poder utilizar el stub es necesario que se encuentren inscriptos en algún torneo
de [Hornero](https://hornero.fi.uncoma.edu.ar/hornero/#/inicio). En la página del
torneo en el que estén participando, donde ven la tabla de puntuación, se les va a mostrar arriba un token único para
cada uno de ustedes. Tienen que copiarlo para pegarlo ahora en el siguiente paso.

En la carpeta `resources` hay un único archivo [torneo.properties](resources/torneo.properties). Habrá un campo para
completar con el token y otro para completar con el número del problema que quieran resolver. Peguen el token después
del igual.

El número del problema deben ir actualizándolo cada vez que quieran resolver un problema. Ahora, si van a resolver el
primer problema pongan 1. No se olviden de cambiarlo después cuando pasen a otro problema.

## Solucionar algún problema

Las soluciones de los problemas se implementan en [Ejercicios.java](src/com/epet14/torneo/core/Ejercicios.java) en la
región indicada.

Para probar la solución implementada, el servidor envía un array del tipo `String` con los argumentos para la función y
envía la respuesta correcta, también en un `String`. Internamente, comparará la salida de nuestra función (pasándole los
argumentos que envió) con la respuesta esperada. Si las respuestas coinciden, nos notificarán por consola que la
respuesta es correcta, si no, nos dirán que la respuesta es incorrecta, nos penalizarán con un punto negativo en la
tabla del torneo y también con un minuto de espera para probar nuevamente la solución.
Toda esta lógica se lleva a cabo en el archivo `Main.java` y no debemos tocarlo para nada.

Ahora bien, las funciones que implementen probablemente no esperan recibir argumentos del tión,po `String` sino de algún
otro tipo (un número en la mayoría de los casos). Para ajustar correctamente los tipos de entrada y salida, en el
archivo [Resolucion.java](src/com/epet14/torneo/core/Resolucion.java), hay dos métodos:

- `parseAns()`: que ya está implementado y se encarga de ajustar la salida de sus funciones a String independientemente
  del tipo que retorne `solucion()`.
- `getSolucion()`: es una función que recibe el array con los argumentos, ejecuta alguna de sus funciones, y devuelve
  la salida parseada al `main`.

El método `getSolucion()` **hay que editarlo** cada vez que resolvamos un problema. ¿Qué hay que hacer? Básicamente,
parsear los argumentos a los tipos que esperaríamos recibir en nuestra función y hacer el llamado a la función pasándole
los argumentos ya parseados.

## Probar una solución y enviar solución

### Probar una solución

Para probar una solución sin riesgo a recibir una penalización pueden usar la _run configuration_ "**Test**". Para ello,
primeramente en el método `main` de [Ejercicios.java](src/com/epet14/torneo/core/Ejercicios.java) deben cambiar los
valores del vector `params` por los argumentos que quieran probar.

Cuando corran **Test**, se ejecutará la función que hayan llamado en el método `getSolucion()` de la clase
[Resolucion.java](src/com/epet14/torneo/core/Resolucion.java) con los argumentos que hayan cargado en `params`.

> [!IMPORTANT]
> Asegúrense de que su código funcione correctamente y prueben todas las veces que sea necesario antes de hacer una
> petición al servidor. **Cada respuesta errónea equivale a -1 punto**.

### Enviar una solución

Cuando estén seguros de que su solución es correcta pueden enviarla al servidor simplemente ejecutando la
_run configuration_ "**Submit**". Por consola van a ver si la respuesta fue o no correcta.

> [!NOTE]
> En el caso de que la respuesta sea incorrecta, además de recibir el punto negativo de penalización, no podrán hacer
> nuevamente un Submit hasta pasado un minuto del intento fallido.

## Resumen

Para no cometer errores les sugiero que sigan un flujo de trabajo parecido al que les voy a listar a continuación.

**Inicialmente:**

1. En [torneo.properties](resources/torneo.properties), remplacen `${TOKEN}` por el token que les haya generado el
   torneo.

**Luego, por cada ejercicio:**

2. Elijan un problema para resolver de la lista de problemas y configuren el número de problema en
   [torneo.properties](resources/torneo.properties).
3. Escriban una función que resuelva el problema. Definan correctamente sus parámetros y su tipo de retorno.
4. En el método `getSolucion()` de la clase [Resolucion.java](src/com/epet14/torneo/core/Resolucion.java), parseen los
   valores del vector `params` a los tipos que espera su función.
5. Nuevamente en el método `getSolucion()`, llamen a su función (en la asignación de la variable `ans`) y pásenle los
   argumentos que parsearon en 4.
6. En el método `main()` de [Ejercicios.java](src/com/epet14/torneo/core/Ejercicios.java) modifiquen los valores del
   vector `params` por valores para probar su función.
7. Ejecuten la _run configuration_ **Test**. Repitan el paso 6 y 7 tantas veces como quieran para probar el
   funcionamiento.
8. Realicen la presentación de la solución al servidor con la _run configuration_ **Submit**.
9. Repetir desde el paso 2 hasta completar todos los ejercicios del torneo.