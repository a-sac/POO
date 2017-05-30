import java.util.Comparator;
import java.io.Serializable;
/**
 * Write a description of class ClientComparator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
 public class ClientComparator implements Comparator<Client>, Serializable {
    public int compare(Client c1, Client c2) {
        if (c1.getMoneySpent() < c2.getMoneySpent()) return -1;
        if (c1.getMoneySpent() > c2.getMoneySpent()) return 1;
        return 0;
    }
 }
