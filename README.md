# ColorPicker
Selector de color


Crea una aplicación móvil en Android que nos permita elegir colores tanto en notación rgb como en notación hexadecimal. Hay que tener en cuenta:

La interfaz tiene que contar, al menos, con:

*Un texto que ponga el color tanto en rgb como en hexadecimal.

*Una zona relativamente grande en la que se muestre color elegido.

*Para cada color base (rojo, verde y azul):

-- Un identificador para saber que estamos modificando.

--Una forma de introducir el número directamente.

--Una barra que permita establecer un valor.

En cuanto al aspecto de la aplicación:

*Todas las vistas que muestren un resultado de un color tienen que tener un fondo de tono gris que permita ver razonablemente bien el color elegido.

*Las vista con texto deben tener un tamaño de letra que se vea sin problemas, mínimo 18sp, si es mayor mejor.

*Para esto hay que usar estilos definidos en el archivo styles.xml y aplicado a las vistas.

Respecto al funcionamiento de la aplicación:

*Podemos introducir los colores de dos maneras:

--Escribiendo el número tal cual.

--Mediante una barra que nos permita establecer el valor.

Cuando modificamos, ya sea la barra o el texto, automáticamente se actualiza los demás elementos, incluyendo:

El estado de la otra vista.

El color en grande.

El nombre del color tanto en rgb como en hexadecimal.

El color del texto del color base que se haya modificado y su barra.

No tiene que haber errores de todo tipo, por ejemplo, hay que controlar los valores permitidos o no se puede dividir por 0,...

Se pide implementar un menú de opciones para seleccionar la forma de introducir los colores en modo texto, decimal o hexadecimal.
