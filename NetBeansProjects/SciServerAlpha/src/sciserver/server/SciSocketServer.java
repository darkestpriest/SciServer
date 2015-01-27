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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.scilab.modules.javasci.JavasciException;
import org.scilab.modules.javasci.Scilab;
import org.scilab.modules.types.ScilabDouble;

/**
 *SciSocketServer (Servidor SciLab)
 * Esta clase implementa un Servidor que permite ejecutar rutinas de Scilab
 * usando javasci
 * @version 0.1.0
 * @author Manuel Pérez P.
 */
public class SciSocketServer {
    
    private int PUERTO=1961;
    /**
     * Método que ejecuta el Servidor
     */
    public void runServidor(){

        //Imprimir mensajes iniciales a mostrar por SciServer
        
        System.out.println("Otro programa desarrollado bajo GPL por Darkest Priest\n"
                + "Más información en:\n"
                + "http://jingmap.blogspot.com\n");
        System.out.println("Inicializando SciServer");

        ServerSocket servidor;
        Socket cliente;
        

        System.out.println("SciServer funcionando en el puerto "+PUERTO);

        //Iniciar el funcionamiento del Servidor
        try{

            //Iniciar Socket Servidor
            servidor = new ServerSocket(PUERTO);
            System.out.println("SciServer esperando conexiones...");
            //Ciclo infinito que espera la conexión de los Sockets clientes
            while(true){

                cliente = servidor.accept();
                //Manejador es Thread que permitirá la ejecución de múltiples
                //clientes
                System.out.println(""+cliente.getInetAddress());
                new Manejador(cliente).start();
                
            }


        }catch(IOException e){

            System.out.println("Error IO:"+e);

        }

    }
    
}

/*Clase Thread que es instanciada por SciServer cada vez que se conecta un
  cliente
 */
class Manejador extends Thread{

    Socket socket;
    ObjectOutputStream os;
    ObjectInputStream is;
    Scilab sci;
    private String VARIABLE;
    
    public Manejador(Socket s) {

        try {
            socket=s;
            os= new ObjectOutputStream(socket.getOutputStream());
            is=new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            sci=new Scilab();
        } catch (IOException ex) {
            Logger.getLogger(Manejador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JavasciException.InitializationException ex) {
            Logger.getLogger(Manejador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void run(){

        try{
            
             //Escritura de objetos en el cliente
            //ObjectOutputStream os= new ObjectOutputStream(socket.getOutputStream());
            //Lectura de objetos del cliente
            //ObjectInputStream is=new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

            //Objeto leído desde el cliente
            Object o = is.readObject();
            /*linea incluye los objetos a evaluar en Scilab
             * 0. Debe incluir el número de puntos a evaluar
             * 1. Incluye la creación en Scilab de los puntos pertenecientes a
             * la variable independiente. Éste objeto puede ser String o un
             * double[]. Si es String debe ser un comando de creación de una
             * matriz de nombre t y tamaño igual a n. Si es double[] debe
             * incluir los puntos de interés a evaluar
             * 2. Este objeto debe ser un String con el comando a ejecutar en
             * Scilab. El nombre de la matriz resultante debe ser f
             */
            String linea=o.toString();
            double[] resultado;
            VARIABLE="f";
            System.out.println("Línea recibida:\n"+linea);
            
            //Ejecución de Scilab en el servidor
            sci.open();
            //TO DO: use the String sent  by SciSocketClient
            sci.exec(linea);
            resultado=((ScilabDouble)sci.get(VARIABLE)).getRealPart()[0];
            sci.close();
            os.writeObject(resultado);
            os.close();
            is.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Manejador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Manejador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JavasciException.InitializationException ex) {
            Logger.getLogger(Manejador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JavasciException ex) {
            Logger.getLogger(Manejador.class.getName()).log(Level.SEVERE, null, ex);
            try {
                os.writeObject(ex);
                sci.close();
            } catch (IOException ex1) {
                Logger.getLogger(Manejador.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }


    }

}
