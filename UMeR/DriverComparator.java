import java.util.Comparator;
import java.io.Serializable;
/**
 * Write a description of class DriverComparator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
 public class DriverComparator implements Comparator<Driver>, Serializable
 {
   public int compare(Driver d1, Driver d2){
     if(d1.getTrustFactor() > d2. getTrustFactor()) return 1;
     if(d1.getTrustFactor() < d2. getTrustFactor()) return -1;
       return 0;
   }
 }
