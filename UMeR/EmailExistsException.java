
import java.io.Serializable;
/**
 * Write a description of class EmailExistsException here.
 *
 * @author jhbb
 * @version 08/05
 */
public class EmailExistsException extends Exception implements Serializable
{
  public EmailExistsException(){
    super();
  }

  public EmailExistsException(String message){
    super(message);
  }
}
