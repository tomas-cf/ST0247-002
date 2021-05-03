
package proyectoED2;


public class Camion
{
    float r, speed, Tmax, st_customer, Q, tiempo;
    /**
     * Constructor de camión
     * @param r El parámetro r representa la tasa de consumo en watts * hora / km del camión.
     * @param speed El parámetro speed representa la la velocidad en km/h del camión.
     * @param Tmax El parámetro Tmax representa el tiempo máximo de la ruta de un vehículo en horas
     * @param st_customer El parámetro st_customer representa el tiempo en horas que se toma visitando 
     * a cada cliente.
     * @param Q El parámetro Q representa la capacidad de la batería en watts * hora.
     * @param tiempo El parametro tiempo representa el tiempo que ha recorrido el camión.
     */
    public Camion(float r, float speed, float Tmax, float st_customer, float Q, float tiempo){
        this.r = r;
        this.speed = speed;
        this.Tmax = Tmax;
        this.st_customer = st_customer;
        this.Q = Q;
        this.tiempo = tiempo;
    }
}
