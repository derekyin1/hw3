/** Derek Yin 113251504 Recitation Section 1
*  This class defines an exception that is called every time a method attempts to pop an empty stack.
*
*  @author Derek Yin
*/
public class EmptyStackException extends Exception{
/**
*This is the constructor for the EmptyStackException.
*
*@param message
*the error message to be displayed
*
*/
  public EmptyStackException(String message){
    super(message);
  }
}
