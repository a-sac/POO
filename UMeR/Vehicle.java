import java.util.Queue;
/**
 * Escreva a descrição da classe Vehicle aqui.
 *
 * @author tdaa
 * @version 19/04
 */
public class Vehicle{
    private double avgSpeed;
    private double factor;
    private String plate;
    private int type;

    public Vehicle(double speed, double factor, String plate, int type){
      this.avgSpeed = speed;
      this.factor = factor;
      this.plate = plate;
      this.type = type;
    }

    public Vehicle(Vehicle v){
      this.avgSpeed = v.avgSpeed;
      this.factor = v.factor;
      this.plate = v.plate;
      this.type = v.type;
    }

    public boolean isCar(){
      return (this.type == 0);
    }

    public boolean isVan(){
      return (this.type == 1);
    }

    public boolean isMotorbike(){
      return (this.type == 2);
    }

    public int getType(){
      return this.type;
    }

    public double getSpeed(){
      return this.avgSpeed;
    }

    public double getFactor(){
      return this.factor;
    }

    public String getPlate(){
      return this.plate;
    }

    public Vehicle clone(){
      return new Vehicle(this);
    }

}
