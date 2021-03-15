/** Derek Yin 113251504 Recitation Section 1
*  This class defines an exception that is called every time cargo is attempted to be placed on a full stack.
*
*  @author Derek Yin
*/
public class FullStackException extends Exception{
/**
*This is the constructor for the FullStackException
*
*@param message
*the error message to be displayed
*
*/
  public FullStackException(String message){
    super(message);
  }
}
