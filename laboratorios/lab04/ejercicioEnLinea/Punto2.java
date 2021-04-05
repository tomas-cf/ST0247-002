/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab04;

import java.util.Arrays;
import java.util.Scanner;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;


public class Punto2 {

    public static void main(String[] args) {
        lectura();
    }

    private static int minimo(int n, int d, int r, int[] a, int[] b) {
        int horasExtra = 0;

        for(int index = 0; index < n; index++) {
            int sum = a[index] + b[n-index-1];
            if(sum > d) {
                horasExtra += Math.abs(sum - d);
            }
        }

        return horasExtra * r;
    }

    private static void lectura() {
        int lineaExcepcion = 0;
        int n, d, r;
        String[] a;
        String[] b;
        int[] aInt;
        int[] bInt;

        try {
            File dataset = new File("dataset.txt");
            FileReader in = new FileReader(dataset);
            BufferedReader br = new BufferedReader(in);

            String linea;
            while((linea=br.readLine()) != "0 0 0") {
                lineaExcepcion++;
                String[] arr = linea.split(" ");
                n = Integer.parseInt(arr[0]);
                d = Integer.parseInt(arr[1]);
                r = Integer.parseInt(arr[2]);

                linea = br.readLine();
                lineaExcepcion++;
                if(linea == null) break;
                a = linea.split(" ");
                aInt = new int[n];

                linea = br.readLine();
                lineaExcepcion++;
                b = linea.split(" ");
                bInt = new int[n];

                for(int i = 0; i < n; i++) {
                    aInt[i] = Integer.parseInt(a[i]);
                    bInt[i] = Integer.parseInt(b[i]);
                }

                Arrays.sort(aInt);
                Arrays.sort(bInt);

                System.out.println(minimo(n, d, r, aInt, bInt));
            }

        } catch (Exception e) {
            System.out.println("Error leyendo el archivo en línea: " + lineaExcepcion);
        }
    }

}