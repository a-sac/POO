
import java.io.Serializable;
/**
 * Write a description of class NoClientsException here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NoClientsException extends Exception implements Serializable
{
  public NoClientsException(){
    super();
  }

  public NoClientsException(String message){
    super(message);
  }
}
