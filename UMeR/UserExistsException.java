
/**
 * Write a description of class UserExistsException here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UserExistsException extends Exception
{
    public UserExistsException(){
      super();
    }

    public UserExistsException(String message){
      super(message);
    }
}
