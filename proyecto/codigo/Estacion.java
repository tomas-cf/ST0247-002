
package proyectoED2;

public class Estacion
{
    int tipo;
    float tiempoCarga;
    /**
     * Constructor de estación
     * @param tipo El parámetro tipo representa el tipo de estacion, que puede ser 0, 1 o 2
     * @param tiempoCarga El parámetro tiempoCarga representa el tiempo en horas que se demora la estación
     * para cargar un camión completamente.
     */
    public Estacion(int tipo, float tiempoCarga){
        this.tipo = tipo;
        this.tiempoCarga = tiempoCarga;
    }
}
