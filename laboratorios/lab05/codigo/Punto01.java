/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio05;


public class Punto01 {


    public static int Held_Karp(Digraph g) {
        
        int n = g.size();
        int[][] costos = new int[n][(int) Math.pow(2, n - 1)];
        int[][] anteriores = new int[n][(int) Math.pow(2, n - 1)];
        for (int i = 0; i < n; ++i) {
            costos[i][0] = g.getWeight(0, i);
            anteriores[i][0] = 0;
        }
        for (int j = 1; j < (int) Math.pow(2, n - 1); ++j) {
            for (int i = 1; i < n; ++i) {
                if (i == j) {
                    costos[i][j] = 0;
                } else {
                    int valor = (j - 1) - i;
                    if (j > n && valor > 0 && valor < n) {  
                        costos[i][j] = 0;
                        costos[valor][j] = 0;
                    } else {
                        if (j < n) {
                            costos[i][j] = costos[j][0] + g.getWeight(j, i);
                                                                           
                        }else{
                            int v2=j-i;
                            int v3=j-v2-1;
                            costos[i][j] = Math.max(costos[v2][v3]+g.getWeight(v2,i),costos[v3][v2]+g.getWeight(v3,i));
                        }
                    }
                }

            }
        }
        return costos[0][(int) Math.pow(2, n - 1)];
    }

}
