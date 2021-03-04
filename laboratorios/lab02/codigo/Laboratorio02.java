/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio02;

import java.util.ArrayList;

/**
 *
 * @author NN
 */
public class Laboratorio02 {
    /**
     * Metodo auxiliar que llama al metodo recorrido posterior
     * con cada uno de los vertices
     * @param g grafo dado 
     * @return cual es el costo que tiene
     */
    public static int camino(Digraph g) {
        boolean[] univisited = new boolean[g.size()];
        return caminoAux(g, 0, univisited,0);
    }
    private static int caminoAux(Digraph g, int v, boolean[] unvisited, int max) {
        int minimo = Integer.MAX_VALUE;
        if(ok(unvisited)&& v == 0)return max;
        if(ok(unvisited)&& v != 0)return Integer.MAX_VALUE;
        unvisited[v]=true;
        ArrayList<Integer> suces = g.getSuccessors(v);
        if (ok(unvisited)){
            for(int i = 0; i < suces.size(); i++){
                int suma = max +  g.getWeight(v, suces.get(i)); 
                minimo = Math.min(minimo, caminoAux(g, suces.get(i), unvisited, suma));
    
            }
            return minimo;
        }
        
        for(int i = 0; i < suces.size(); i++){
            if(unvisited[suces.get(i)] == true){
                continue;
            }else{
                int suma = max +  g.getWeight(v, suces.get(i)); 
                minimo = Math.min(minimo, caminoAux(g, suces.get(i), unvisited, suma));
                unvisited[suces.get(i)] = false;
            }
        }
        return minimo;
    }
    private static boolean ok(boolean[] szs){ 
        for(int i = 0; i<szs.length; i++){
            if(!szs[i])return false;
        }
        return true;
    }
    
}
