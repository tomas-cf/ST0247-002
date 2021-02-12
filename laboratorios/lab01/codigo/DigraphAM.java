
package laboratorio01;


import laboratorio01.Graph;
import java.util.ArrayList;

/**
 * Implementacion de un grafo dirigido usando matrices de adyacencia
 *
  * @author NN
 */
public class DigraphAM extends Graph{
    Pareja[][] matrix;
    
    /**
     * Constructor para el grafo dirigido
     * @param vertices el numero de vertices que tendra el grafo dirigido
     *
     */
    public DigraphAM(int size) {
        super(size);
        matrix = new Pareja[size][size];
    }

    /**
     * Metodo para añadir un arco nuevo, donde se representa cada nodo con un entero
     * y se le asigna un peso a la longitud entre un nodo fuente y uno destino  
     * @param source desde donde se hara el arco
     * @param destination hacia donde va el arco
     * @param weight el peso de la longitud entre source y destination
     */
    public void addArc(int source, int destination, double weight, String nombre) {
        Pareja n = new Pareja(nombre, weight);
        matrix[source][destination] = n;
    }

    /**
     * Metodo para obtener una lista de hijos desde un nodo, es decir todos los nodos
     * asociados al nodo pasado como argumento
     * @param vertex nodo al cual se le busca los asociados o hijos
     * @return todos los asociados o hijos del nodo vertex, listados en una ArrayList
     * Para más información de las clases:
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html"> Ver documentacion ArrayList </a>
     */
    public ArrayList<Integer> getSuccessors(int vertex) {
        ArrayList<Integer> successors = new ArrayList<>();
        for(int i = 0; i < size; i++){
            if(matrix[vertex][i].peso != 0){
                successors.add(i);
            }
        }
        if(successors.size() == 0){
            return null;
        }         
        return successors;
    }

    /**
     * Metodo para obtener el peso o longitud entre dos nodos
     * 
     * @param source desde donde inicia el arco
     * @param destination  donde termina el arco
     * @return un entero con dicho peso
     */ 
    
    
    public Pareja getWeight(int source, int destination) {
        Pareja n =(Pareja) matrix[source][destination];
        return n;
    }
}
