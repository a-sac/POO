import java.io.Serializable;
/**
* Write a description of class NoTaxisException here.
*
* @author (your name)
* @version (a version number or a date)
*/
public class NoEvaluationsException extends Exception implements Serializable
{
  public NoEvaluationsException(){
    super();
  }
  
  public NoEvaluationsException(String message){
    super(message);
  }
}
