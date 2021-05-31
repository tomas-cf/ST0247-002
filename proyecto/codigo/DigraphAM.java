
package proyectoED2;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 * Implementacion de un grafo dirigido usando matrices de adyacencia
 */
public class DigraphAM extends Digraph {

  int[][] matriz;
  
  /**
   * Constructor de un grafo utilizando matrices.
   */
  public DigraphAM(int size) {
    super(size);
    matriz = new int[size][size];
  }
  
  /**
   * Método que devuelve la distancia que hay de un vertice a otro.
   */
  public int getWeight(int source, int destination) {
    return matriz[source][destination];
  }
  
  /**
   * Método para añadir un arco al grafo.
   */
  public void addArc(int source, int destination, int weight) {
    matriz[source][destination] = weight;
    matriz[destination][source] = weight;
  }
  
  /**
   * Método para obtener los vertices a los que se puede llegar a partir del vertice actual.
   */
  public ArrayList<Integer> getSuccessors(int vertex) {
    ArrayList<Integer> lista = new ArrayList<Integer>();
    for (int j = 0; j < size; j++) {
      if (matriz[vertex][j] != 0) {
        lista.add(j);
      }
    }
    return lista;
  }
}