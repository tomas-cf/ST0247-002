
package proyectoED2;

import java.util.*;
import java.io.*;
import javafx.util.Pair;
/**
 *
 * @author NN
 * 
 */
class Proyecto
{
    private static Estacion tipo0;
    private static Estacion tipo1;
    private static Estacion tipo2;
    /**
     * @param g El parámetro g recibe el grafo que representa el mapa que contiene el deposito, 
     * los clientes y las estaciones.
     * 
     * @param camion El parámetro camion representa el camion que realiza la ruta.
     * 
     * @param visitado El parámetro visitados muestra cuales son los clientes que han sido visitados 
     * para no visitarlos más de una vez.
     * 
     * @param tiempo El parámetro tiempo almacena el tiempo total que toman todas la rutas realizadas.
     * 
     * @param ruta El parámetro ruta almacena el recorrido que realizan los camiones.
     * 
     * @param estaciones El parametro estaciones almacena que vertices del grafo son estaciones.
     * 
     * @return El método retorna un número flotante representando el tiempo total que toman todas las rutas.
     */
    private static float agenteViajeroAux(Digraph g, Camion camion, boolean[] visitado, float tiempo, ArrayList <Camion> ruta, ArrayList<Pair<Integer,Integer>> estaciones){ 
        ArrayList<Integer> sucesores = new ArrayList<Integer>();
        ruta.add(camion);
        int deposito = 0;
        int actual = deposito;
        int costo = 0;
        float tCamion = 0;
        float tNodos = 0;
        float tDepot = 0;
        float tSobrante = camion.Tmax;
        boolean terminado = false;
        float qCamion = 0;
        float qNodos = 0;
        float qDepot = 0;
        float qSobrante = camion.Q;
        float r = 0;
        
        for(int i = 0; i < visitado.length-estaciones.size();++i){
            if(visitado[i]){
                terminado = true;
            }
            else{
                terminado = false;
                break;
            }
        }        
        
        if(terminado){
             return tiempo;
        }
        System.out.print("\n Ruta "+ ruta.size()+":\n0"+" (0 horas), ");
        for(int i = 0; i < g.size()-estaciones.size(); ++i){
             int minimo = Integer.MAX_VALUE;
             int cerca = 0;
             sucesores = g.getSuccessors(actual);
             visitado[actual] = true;
            for(Integer sucesor : sucesores){
                if(!visitado[sucesor] && sucesor != actual && (g.getWeight(actual, sucesor) < minimo) && (sucesor < g.size()-estaciones.size())){
                    minimo = g.getWeight(actual, sucesor);
                    cerca = sucesor;
                    tNodos = (g.getWeight(actual, cerca))/camion.speed;
                    tDepot = (g.getWeight(cerca, deposito))/camion.speed;
                    qNodos = camion.r*g.getWeight(actual, cerca);
                    qDepot = camion.r*g.getWeight(cerca, deposito);
                }
            }
            if(!hayTiempo(tNodos, tDepot, camion.st_customer, tSobrante)){
                tCamion += (g.getWeight(actual,deposito))/camion.speed;
                tiempo += (g.getWeight(actual,deposito))/camion.speed;
                tSobrante = camion.Tmax;
                qCamion += camion.r*g.getWeight(actual,deposito);
                qCamion += camion.r*g.getWeight(actual,deposito);
                actual = deposito;
                visitado[deposito] = true;

                System.out.println("0 "+"("+tCamion+"Hrs)");
                return agenteViajeroAux(g, camion, visitado, tiempo,ruta, estaciones);
            }
            else if(hayTiempo(tNodos, tDepot, camion.st_customer, tSobrante) && qNodos < qSobrante){
                actual = cerca;
                tCamion += camion.st_customer + tNodos;
                tiempo += camion.st_customer + tNodos;
                tSobrante -= camion.st_customer + tNodos;
                qCamion += qNodos;
                qSobrante -= qNodos;
                visitado[actual] = true;
                if (actual==0){
                    System.out.println("0 "+"("+tCamion+" Horas)");
                    System.out.println("");
                    break;
                } 
                System.out.print(actual+" "+" (" +tCamion+" Horas), ");
            }
            else if(hayTiempo(tNodos, tDepot, camion.st_customer, tSobrante) && !hayEnergia(qNodos, qDepot, qSobrante)){
                minimo = Integer.MAX_VALUE;
                cerca = 0;
                sucesores = g.getSuccessors(actual);
                for(Integer sucesor : sucesores){
                    if((g.getWeight(actual,sucesor) < minimo) &&(sucesor >= g.size()-estaciones.size())){
                        minimo = g.getWeight(actual, sucesor);
                        cerca = sucesor;
                        tNodos = (g.getWeight(actual, cerca))/camion.speed;
                        tDepot = (g.getWeight(cerca, deposito))/camion.speed;
                        qNodos = camion.r*g.getWeight(actual, cerca);
                        qDepot = camion.r*g.getWeight(cerca, deposito);
                    }
                }
                for(int j = 0; j < estaciones.size(); ++j){
                    if(estaciones.get(j).getKey() == cerca){
                        if(estaciones.get(j).getValue() == 0){
                            r = camion.Q/tipo0.tiempoCarga;
                        }
                        if(estaciones.get(j).getValue() == 1){
                            r = camion.Q/tipo1.tiempoCarga;
                        }
                        if(estaciones.get(j).getValue() == 2){
                            r = camion.Q/tipo2.tiempoCarga;
                        }
                    }
                }
                if((tNodos+tDepot+(qCamion/r)) < tSobrante && qNodos < qSobrante){
                    actual = cerca;
                    tCamion += tNodos + qCamion/r;
                    tiempo += tNodos + qCamion/r;
                    tSobrante -= tNodos + qCamion/r;
                    qSobrante = camion.Q;
                    qCamion = 0;
                    visitado[actual] = true;
                    System.out.print(actual+" "+" (" +tCamion+" Horas), ");
                }
                else{ 
                    tCamion += (g.getWeight(actual,deposito))/camion.speed;
                    tiempo += (g.getWeight(actual,deposito))/camion.speed;
                    tSobrante = camion.Tmax;
                    qCamion += camion.r*g.getWeight(actual,deposito);
                    actual = deposito;
                    visitado[deposito] = true;
                   
                        //System.out.println(Arrays.toString(visitado));
                       
                    System.out.println("0 "+"("+tCamion+" Horas)");
                    return agenteViajeroAux(g, camion, visitado, tiempo,ruta, estaciones);
                }
            }
        }   
        return tiempo;
    }
  
    /**
     * Método que calcula el tiempo que toman los camiones en visitar a todos los clientes.
     * 
     * @param g El parámetro g recibe el grafo que representa el mapa que contiene el deposito, 
     * los clientes y las estaciones.
     * 
     * @param c El parámetro camion representa el camion que realiza la ruta.
     * 
     * @param estaciones El parametro estaciones almacena que vertices del grafo son estaciones.
     * 
     * @return El método retorna un número flotante representando el tiempo total que toman todas las rutas.
     */
    public static float agenteViajero(Digraph g, Camion c, ArrayList<Pair<Integer,Integer>> estaciones){
        ArrayList <Camion> camiones = new ArrayList <Camion>();
        return agenteViajeroAux(g, c, new boolean[g.size()], 0,camiones, estaciones);
    }
    
    /**
     * Método que revisa si hay tiempo suficiente para ir al vertice más cercano y volver al deposito.
     * 
     * @param tNodos El parámetro tNodos almacena el tiempo que se demora en ir del vertice actual 
     * al más cercano.
     * 
     * @param tDepot El parámetro tDepot almacena el tiempo que se demora en ir del vertice cercano 
     * al deposito.
     * 
     * @param st_customer El parámetro st_customer almacena el tiempo que se demora la visita de 
     * cada cliente.
     * 
     * @param tSobrante El parámetro tSobrante almacena el tiempo que le queda al camíon para tener 
     * que volver al deposito.
     * 
     * @return El método devuelve true si hay tiempo necesario, de lo contrario retorna false.
     */
    private static boolean hayTiempo(float tNodos, float tDepot, float st_customer, float tSobrante){
        if((tNodos+tDepot+st_customer) > tSobrante){
            return false;
        }
        return true;
    }
    
    /**
     * Método que revisa si hay energía suficiente para ir al vertice más cercano y volver al deposito.
     * 
     * @param qNodos El parámetro tNodos almacena la energía que se gasta al ir del vertice actual 
     * al más cercano.
     * 
     * @param qDepot El parámetro tDepot almacena la energía que se gasta al ir del vertice cercano 
     * al deposito.
     * 
     * @param qSobrante El parámetro tSobrante almacena la energía que le queda al camíon.
     * 
     * @return El método devuelve true si hay energía suficiente, de lo contrario retorna false.
     */
    private static boolean hayEnergia(float qNodos, float qDepot, float qSobrante){
        if((qNodos + qDepot) > qSobrante){
            return false;
        }
        return true;
    }
    
    /**
     * Método para leer el fichero y crear el grafo que representa el mapa.
     * 
     * @param filename el parámetro filename representa el nombre del fichero que se desea leer.
     */
    private static void leer(String filename) throws IOException{
        BufferedReader lector = new BufferedReader(new FileReader(filename));
        String linea;
        int k=0, n=0, m=0, u=0, breaks=0;
        float r=0.0f, speed=0.0f, Tmax=0.0f, Smax=0.0f, st_customer=0.0f,Q=0.0f;
        while(k <= 12){
            linea = lector.readLine();
            String[] arr = linea.split(" ");
            if(arr[0].equals("n")){
                n = Integer.parseInt(arr[2]);
            }
            if(arr[0].equals("m")){
                m = Integer.parseInt(arr[2]);
            }
            if(arr[0].equals("u")){
                u = Integer.parseInt(arr[2]);
            }
            if(arr[0].equals("breaks")){
                breaks = Integer.parseInt(arr[2]);
            }
            if(arr[0].equals("r")){
                r = Float.parseFloat(arr[2]);
            }
            if(arr[0].equals("speed")){
                speed = Float.parseFloat(arr[2]);
            }
            if(arr[0].equals("Tmax")){
                Tmax = Float.parseFloat(arr[2]);
            }
            if(arr[0].equals("Smax")){
                Smax = Float.parseFloat(arr[2]);
            }
            if(arr[0].equals("st_customer")){
                st_customer = Float.parseFloat(arr[2]);
            }
            if(arr[0].equals("Q")){
                Q = Float.parseFloat(arr[2]);
            }
            ++k;
        }
        Camion c = new Camion(r, speed, Tmax, st_customer, Q, 0);
        Pair<Float,Float>[] arreglo = new Pair[n]; 
        Digraph g = new DigraphAM(n); 
        //            #nodo, tipo estacion
        ArrayList<Pair<Integer,Integer>> estaciones = new ArrayList<Pair<Integer,Integer>>(); 
        
        while (!(linea = lector.readLine()).equals("l")){  
            String[] lineaPartida = linea.split(" ");
            if(lineaPartida.length == 6){
                if (lineaPartida[4].equals("d") || lineaPartida[4].equals("c")){
                    arreglo[Integer.parseInt(lineaPartida[0])] = new Pair(Float.parseFloat(lineaPartida[2]),Float.parseFloat(lineaPartida[3]));
                }
                if(lineaPartida[4].equals("s")){
                    arreglo[Integer.parseInt(lineaPartida[0])] = new Pair(Float.parseFloat(lineaPartida[2]),Float.parseFloat(lineaPartida[3]));
                    estaciones.add(new Pair<Integer,Integer>(Integer.parseInt(lineaPartida[0]),Integer.parseInt(lineaPartida[5])));
                } 
            }
        } 
        linea = lector.readLine();
        linea = lector.readLine();
        String[] valores = linea.split(" ");
        tipo0 = new Estacion(0,Float.parseFloat(valores[3]));
        
        linea = lector.readLine();
        valores = linea.split(" ");
        tipo1 = new Estacion(1,Float.parseFloat(valores[3]));
        
        linea = lector.readLine();
        valores = linea.split(" ");
        tipo2 = new Estacion(2,Float.parseFloat(valores[3]));
        for (int i = 0; i< n; i++){
            for (int j = 0; j < n; j++){
                g.addArc(i,j,(int)Math.sqrt(Math.pow(arreglo[i].getKey()-arreglo[j].getKey(),2) + Math.pow(arreglo[i].getValue()-arreglo[j].getValue(),2)));
            }
        }
        System.out.println(" Tiempo total de rutas: "+agenteViajero(g, c, estaciones));
    }
    
    public static void main(String[] args)throws IOException{
        //leer("test1.txt");
        //leer("test2.txt");
        leer("test3.txt");
     
    }
}

