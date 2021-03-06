/*
 * TEsta clase Java es Software Libre y se distribuye gratuitamente bajo
 * licencia GPL, por lo que está permitida su copia o distribución bajo los
 * criterios de la misma.
 * Ante cualquier problema, consulta, sugerencia, etc, puede escribir a
 * darkpriestrelative@gmail.com, o dejar un mensaje en el sitio
 * http://jingmap.blogspot.com
 * Para descargar actualizaciones u obtener mayor documentación consulte la
 * ayuda en línea del programa o visite la página oficial del proyecto
 * (http://jingmap.blogspot.com).
 *
 * Copyleft 2010 Darkest Priest - (Manuel Pérez P. darkpriestrelative@gmail.com)
 */
package sciserver.server;

/**
 *SciServer (Servidor SciLab)
 * Esta clase la clase "main" un Servidor que permite ejecutar rutinas de Scilab
 * usando javasci
 * @version 0.1.0
 * @author Manuel Pérez P.
 */
public class SciServer {
    
    public static void main(String[] args){
        
        System.out.println("SciServer 0.1.0");
        System.out.println("Este servidor está en desarrollo, por favor, ejecutar con precaución.");
        SciSocketServer sciServer=new SciSocketServer();
        sciServer.runServidor();
        
        
    }
    
}
