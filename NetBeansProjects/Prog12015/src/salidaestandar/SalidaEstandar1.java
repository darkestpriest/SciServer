/*
 *  Esta clase Java es Software Libre y se distribuye gratuitamente bajo
 *  licencia GPL, por lo que está permitida su copia o distribución bajo los
 *  criterios de la misma.
 *  Ante cualquier problema, consulta, sugerencia, etc, puede escribir a
 *  darkpriestrelative@gmail.com, o dejar un mensaje en el sitio
 *  http://jingmap.blogspot.com
 *  Para descargar actualizaciones u obtener mayor documentación consulte la
 *  ayuda en línea del programa o visite la página oficial del proyecto
 *  (http://jingmap.blogspot.com).
 * 
 *  Copyright 2015 Darkest Priest - (Manuel Pérez P. darkpriestrelative@gmail.com)
 * 
 */

package salidaestandar;

/**
 * Clase para mostrar el uso de la salida estándar del Sistema.
 * En esta clase se mostrará como declarar objetos en Java e imprimirlos en el
 * terminal del sistema.
 * @author Manuel Pérez
 * @see SalidaEstandar0
 */
public class SalidaEstandar1 {
    
    public static void main(String[] args){
        
        /**
         * Declaración de un objeto String.
         * Los objeto tipo String se usan, en la mayoría de las veces, para
         * imprimir mensajes al usuario por medio de la terminal.
         * Este tipo de objetos aceta caracteres alfa numéricos.
         */
        String mensaje="Mensaje a imprimir";
        /**
         * En la clase SalidaEstandar0 se imprime un mensaje en la terminal del
         * sistema usando System.out.println(), este objeto posee como argumento
         * un objeto tipo Object, en este caso, al mencionado comando le estamos
         * suministrando el objeto 'mensaje' como argumento, lo cual permite 
         * imprimir en la terminal el valor del mencionado objeto.
         */
        System.out.println(mensaje);
        
        /**
         * Declaración de un objeto numérico.
         * Los objetos tipo int 
         */
        int entero=100;
        
    }
    
}
