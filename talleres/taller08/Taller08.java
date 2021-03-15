/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller08;

/**
 * Clase en la cual se implementan los metodos del Taller 8
 * 
 * @author NN
 */

public class Taller08 {
	/**
	* Metodo que pretende implementar el funcionamiento del algoritmo MergeSort
	* de un conjunto de elementos
	* @param a un arreglo con elementos
	* 
	* para mas informacion ver
	* @see <a href="https://www.youtube.com/watch?v=JSceec-wEyw">
	*
	*/
	public static void mergesort(int[] a) {
		mergesort(a, 0 , a.length-1);
	}
        
        private static void mergesort(int[] a, int i , int f){
            int mitad= (f-i)/2;
            mergesort(a,i,mitad);
            mergesort(a,mitad+1,f);
            merge(a, i , mitad, f);  //Pegar dos arreglos que ya están ordenados O(n)
        }
        
        private static void merge(int[] a, int i, int mitad, int f){
            
        }
        
        
	/**
	* Metodo que pretende implementar el funcionamiento del algoritmo MergeSort
	* de un conjunto de elementos
	* @param a un arreglo con elementos
	* 
	* para mas informacion ver
	* @see <a href="https://www.youtube.com/watch?v=PgBzjlCcFvc">
	*
	*/
	public static void quicksort(int[] a) {
            quicksort(a,0,a.length-1);
	}
        
        private static void quicksort(int[]a, int i, int f){
            //Falta condición de parada
            
            int pos = partition(a,i,f); //Mueve el pivote y deja menores al izq, mayores a la der y reotnra la posición
            quicksort(a,i,pos-1); //Mejor caso esta la mitad, en el peor a un solo lado (en el peor nada)
            quicksort(a,pos+1,f);   //Mejor caso esta ala mitad, (en el peor todo)
            
        }
        
        private static int partition(int[] a, int i, int f){
            int pivote = a[f];
            int pivoteIndice = f;
            for (int index = i; index < f; index++) {
                if(a[index]>pivote){
                    swap(a,index,pivoteIndice);
                    pivote = a[index];
                    pivoteIndice = index;
                    
                }
            }
            
            return pivoteIndice;
        }
        
        
        private static void swap(int[] a, int i, int j){
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }

}
