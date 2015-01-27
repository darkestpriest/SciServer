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
package sciserver.client;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Testing class
 * @author Manuel Perez P.
 */
public class TestClient {
    
    
    public static void main(String[] args){
        
        String ip="localhost";
        int port=1961;
        
        try {
            SciSocketClient ssc=new SciSocketClient(ip, port);
            Object recibido=ssc.procesar("f=0:0.001:1;");
            if(recibido instanceof double[]){
                
                System.out.println("Recibiendo respuesta");
                double[] resultado=(double[]) recibido;
                System.out.println(Arrays.toString(resultado));
                
            }else{
                
                System.out.println("Error:\n"+recibido);
                
            }

        } catch (IOException ex) {
            Logger.getLogger(TestClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TestClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExcepcionNumeroObjetosList ex) {
            Logger.getLogger(TestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
