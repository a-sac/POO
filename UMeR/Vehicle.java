import java.util.Queue;
/**
 * Escreva a descrição da classe Vehicle aqui.
 *
 * @author tdaa
 * @version 19/04
 */
public abstract class Vehicle{

    private double avgSpeed;
    private double factor;
    private String plate;

    public Vehicle(double speed, double factor, String plate){
      this.avgSpeed = speed;
      this.factor = factor;
      this.plate = plate;
    }

    public Vehicle(Vehicle v){
      this.avgSpeed = v.getSpeed();
      this.factor = v.getFactor();
      this.plate = v.getPlate();
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

    public abstract Vehicle clone();

}
