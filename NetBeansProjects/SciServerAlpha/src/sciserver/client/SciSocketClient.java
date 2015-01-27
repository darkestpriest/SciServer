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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *SciSocketClient (Cliente para SciServer)
 * Esta clase implementa un cliente que permite ejecutar rutinas de Scilab
 * usando javasci a traves de SciSocketServer
 * @version 0.1.0
 * @author Manuel Pérez P.
 */
public class SciSocketClient {
    
    Socket socket;

    /**
     * Método Constructor de la clase SciSocketClient
     * @param IPservidor Dirección IP del servidor. Es válido también usar un
     * nombre registrado en el archivo hosts del sistema operativo o en el
     * DNS de su red
     * @param Puerto Puerto de escucha de SciServer
     * @throws UnknownHostException SciServer no está funcionando en la
     * dirección suministrada.
     * @throws IOException Error I/O
     */
    public SciSocketClient(String IPservidor, int Puerto) throws
            UnknownHostException, IOException {
        
        //Inicializar el scoket con los parámetros suministrados
        socket = new Socket(IPservidor, Puerto);

    }

    /**
     * Método que envía los objetos a SciServer vía socket
     * @param objetos List con los objetos a ser procesados por SciServer
     * @return List con la respuesta de SciServer
     * @throws IOException Error I/O
     * @throws ClassNotFoundException No existe el casting para el objeto
     * seleccionado
     * @throws ExcepcionNumeroObjetosList El número de objetos en el List es
     * distinto a los que están permitidos en la versión de SciServer que corre en el servidor
     */
    public Object procesar(String objeto) throws IOException, ClassNotFoundException,
            ExcepcionNumeroObjetosList{

        //Declaración de los objetos a recibir por SciServer
        Object recibido;
        //Lectura de objetos del cliente
        ObjectInputStream is=new ObjectInputStream
                (new BufferedInputStream(socket.getInputStream()));
        //Escritura de objetos en el cliente
        ObjectOutputStream os= new ObjectOutputStream(socket.getOutputStream());
        //Objeto leído desde SciServer
        Object o;

        //Escribir objetos en SciServer
        os.writeObject(objeto);
        //Leer objetos procesados por SciServer
        o = is.readObject();
        //Transformar o en List
        recibido= o;

        return recibido;

    }


}

/*
 * Clase Exception para lanzar una excepción en el caso de no coincidir el número
 * de elementos en el List que se enviará a SciServer
 */
class ExcepcionNumeroObjetosList extends Exception{

    //Número de elementos del List evaluado
    int elementos;

    //Método Constructor de ExcepcionNumeroObjetosList
    public ExcepcionNumeroObjetosList(int elementos) {
        this.elementos = elementos;
    }

    @Override
    public String toString() {
        return "ExcepcionNumeroObjetosList: Elementos presentes en el List "
                + "objetos "+elementos
                + "\nOBSERVACIÓN: En esta versión de SciServer el "
                + "objeto List debe tener tres (3) elementos.\n"
                + "0. Debe incluir el número de puntos a evaluar\n"
                + "1. Incluye la creación en Scilab de los puntos pertenecientes "
                + "a la variable independiente. Éste objeto puede ser String o un "
                + "double[]. Si es String debe ser un comando de creación de una "
                + "matriz de nombre t y tamaño igual a n. Si es double[] debe "
                + "incluir los puntos de interés a evaluar\n"
                + "2. Este objeto debe ser un String con el comando a ejecutar en"
                + "Scilab. El nombre de la matriz resultante debe ser f\n" + '}';
    }
    
}
