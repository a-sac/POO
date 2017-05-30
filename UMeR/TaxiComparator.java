import java.util.Comparator;
import java.io.Serializable;

public class TaxiComparator implements Comparator<Taxi>, Serializable 
{
     public int compare(Taxi t1, Taxi t2){
       return t1.getVehicle().getPlate().compareTo(t2.getVehicle().getPlate());
     }
}
