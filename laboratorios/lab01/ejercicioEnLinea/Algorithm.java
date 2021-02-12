/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjercicioEnLinea;

/**
 *
 * @author NN
 */
public class Algorithm {
    public static boolean DFSColorFC(DigraphAM am) {
        int[] lista = new int[am.size+1];
        return DFSColorFCAux(am, am.getFirst(), lista, 1);
    }
    private static boolean DFSColorFCAux(DigraphAM am, int nodo, int[]lista, int color) {
        boolean cp = true;
        if(lista[nodo] == 0){
            lista[nodo]=color;
            for(Integer s: am.getSuccessors(nodo)){
                if(color==1){
                    cp = DFSColorFCAux(am, s, lista, 2);
                }else{
                    cp = DFSColorFCAux(am, s, lista, 1); //O(N)
                }
                if(!cp) return false;
            }
        }else{
            if(color == lista[nodo]){
                return true;
            }else{
                return false;
            }
        }
        return cp;
    }
}