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

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *SciServer (Servidor SciLab)
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
    
    public Manejador(Socket s) {

        socket=s;

    }

    @Override
    public void run(){

        //TO DO: Manejo de scilab
        System.out.println("Manejador.run()");


    }

}
