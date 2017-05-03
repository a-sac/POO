import java.util.Comparator;
/**
 * Write a description of class TaxiComparator here.
 *
 * @author jhbb
 * @version 02/05
 */
public class TaxiComparator implements Comparator<Taxi>
{
    public int compare(Taxi t1, Taxi t2){
      if(t1.getDriver().getTrustFactor() > t2.getDriver().getTrustFactor())
        return 1;
      if(t1.getDriver().getTrustFactor() == t2.getDriver().getTrustFactor())
        return 0;
      else return -1;
    }
}
