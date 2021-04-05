/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab04;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Clase en la cual se implementan los metodos del Taller 6
 *
 * @NN
 */
public class Lab04{
    /**
     *Method that runs through the entire graph with the intention of looking for a
     * path that represents the lowest cost going through all vertices exactly
     * once and return to the initial node
     * @param g graph
     * @return lowest cost
     */
    public static int recorrido_V(Digraph g) {
        boolean[] visited = new boolean[g.size()];
        int resp = 0;
        int node = 0;
        int snode = 0;

        for(int i = 0; i < g.size(); i++) {
            int menor = Integer.MAX_VALUE;
            visited[node] = true;
            ArrayList<Integer> hijos = g.getSuccessors(node);

            for(Integer hijo : hijos) {
                int peso = g.getWeight(node, hijo);
                if((!visited[hijo] && peso < menor) || (i==g.size()-1 && hijo==0)) {
                    menor = peso;
                    snode = hijo;
                }
            }
            resp += menor;
            node = snode;
        }

        return resp;
    }
    public static void main(String[] args) {
        DigraphAL g1 = new DigraphAL(5);
        g1.addArc(0, 1, 2);
        g1.addArc(0, 2, 2);
        g1.addArc(0, 3, 1);
        g1.addArc(0, 4, 4);
        g1.addArc(1, 0, 2);
        g1.addArc(1, 2, 3);
        g1.addArc(1, 3, 2);
        g1.addArc(1, 4, 3);
        g1.addArc(2, 0, 2);
        g1.addArc(2, 1, 3);
        g1.addArc(2, 3, 2);
        g1.addArc(2, 4, 2);
        g1.addArc(3, 0, 1);
        g1.addArc(3, 1, 2);
        g1.addArc(3, 2, 2);
        g1.addArc(3, 4, 4);
        g1.addArc(4, 0, 4);
        g1.addArc(4, 1, 3);
        g1.addArc(4, 2, 2);
        g1.addArc(4, 3, 4);
        System.out.println(recorrido_V(g1));

    }
}
