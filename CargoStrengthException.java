/** Derek Yin 113251504 Recitation Section 1
*  This class defines an exception is called when a stronger cargo is attempted to be placed on a weaker one. 
*
*  @author Derek Yin
*/

public class CargoStrengthException extends Exception{
/**
*This is the constructor for the CargoStrengthException
*
*@param message
*the error message to be displayed
*
*/
  public CargoStrengthException(String message){
    super(message);
  }
}
