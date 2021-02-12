package laboratorio01;



import java.util.ArrayList;
import laboratorio01.Pareja;
/**
 * Abstract class for implementations of Digraphs
 * 
 * @author NN
 */
public abstract class Graph
{
   protected int size;
   public  Graph(int vertices) 
   {
       size = vertices;
   }
   public  abstract void addArc(int source, int destination, double weight, String nombre);
   public abstract ArrayList<Integer> getSuccessors(int vertice);
   
   public abstract Pareja getWeight(int source, int destination);
   public  int size() {return size;}
}