
import java.io.Serializable;

public class NoEvaluationsException extends Exception implements Serializable
{
  public NoEvaluationsException(){
    super();
  }

  public NoEvaluationsException(String message){
    super(message);
  }
}