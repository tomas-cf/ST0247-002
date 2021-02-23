/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller05;

import java.util.ArrayList;

/**
 * Clase en la cual se implementan los metodos del Taller 5
 * 
 * @author Mauricio Toro, Camilo Paez
 */
public class Taller05 {


	/**
	* Metodo que dado un grafo y un numero m, se asigna un color
	* a cada nodo, de manera que dos nodos adyacentes no poseean el mismo color
	* @param g grafo dado 
	* @param m numero de colores
	* @return true si es posible, false de lo contrario
	*/
        public static boolean mColoring(Digraph g, int m) {
            return mColoring( g, 0, new int[g.size()], m);
            // Puede pintar el grafo, iniciando en 0, con un arreglo de colores
            // del tamaño de los vértices del grafo, y con m colores?
	}

	 private static boolean mColoring(Digraph g, int v, int[] colors, int m) {
        if(v == g.size()) return true;
        for(int i = 1; i < m+1; i++){
            if(isSafe(g, v, colors, i)){
                colors[v] = i;
                if(mColoring(g,v+1, colors, m)){
                    return true;
                }
            }  
        }
        return false;        
    }

    /**
     * Metodo que dado un grafo y un vertice v, intenta asignar un color colors en la 
     * posicion c al nodo v, de manera que dos nodos adyacentes no poseean el mismo color
     * @param g grafo dado 
     * @param c indice de colores
     * @param v vertice 
     * @param colors conjunto de colores
     * @return true si es posible, false de lo contrario
     */
    private static boolean isSafe(Digraph g, int v, int[] colors, int c) {
        ArrayList<Integer> sucesores = g.getSuccessors(v);
        for(int i = 0; i < sucesores.size(); i++){
            if(colors[sucesores.get(i)] == c){
                return false;
            }
        }
        return true;
    }

	

}
