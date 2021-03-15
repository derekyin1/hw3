/** Derek Yin 113251504 Recitation Section 1
*  This class defines an exception called every time new cargo that is to be placed on the ship violates its maximum weight.
*
*  @author Derek Yin
*/
public class ShipOverweightException extends Exception{
/**
*This is the constructor for the ShipOverweightException
*
*@param message
*the error message to be displayed
*
*/
  public ShipOverweightException(String message){
    super(message);
  }
}
