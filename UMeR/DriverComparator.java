import java.util.Comparator;
/**
 * Write a description of class DriverComparator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DriverComparator implements Comparator<Driver>
{
  public int compare(Driver d1, Driver d2){
    if(d1.getTimeExceeded() > d2. getTimeExceeded())
      return 1;
    if(d1.getTimeExceeded() == d2. getTimeExceeded())
      return 0;
    else return -1;
  }
}
