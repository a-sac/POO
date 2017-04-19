
/**
 * Escreva a descrição da classe Vehicle aqui.
 *
 * @author tdaa
 * @version 1.0
 */
public class Vehicle{
    private double avgSpeed;
    private double price;
    private int factor;
    private String plate;

    public Vehicle(double speed, double price, int factor, String plate){
      this.avgSpeed = speed;
      this.price = price;
      this.factor = factor;
      this.plate = plate;
    }

    public Vehicle(Vehicle v){
      this.avgSpeed = v.avgSpeed;
      this.price = v.price;
      this.factor = v.factor;
      this.plate = v.plate;
    }

    public double getSpeed(){
      return this.avgSpeed;
    }

    public double getPrice(){
      return this.price;
    }

    public int getFactor(){
      return this.factor;
    }

    public String getPlate(){
      return this.plate;
    }

}
