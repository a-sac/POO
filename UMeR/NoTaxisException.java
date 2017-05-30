
import java.io.Serializable;
/**
 * Write a description of class NoTaxisException here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NoTaxisException extends Exception implements Serializable
{
  public NoTaxisException(){
    super();
  }

  public NoTaxisException(String message){
    super(message);
  }
}
