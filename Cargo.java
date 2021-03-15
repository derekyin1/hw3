/** Derek Yin 113251504 Recitation Section 1
*  This class defines a Cargo object to be placed on a CargoShip.
*
*  @author Derek Yin
*/
public class Cargo{
  private String name;
  private double weight;
  private CargoStrength strength;

/** This is a constructor that creates a new Cargo object with desired specifications
* @param initName
* desired name of cargo
* @param initWeight
* desired weight of cargo
* @param initStrength
* desired Strength of cargo
* @throws
* IllegalArgumentException if any parameters are negative
*/

  public Cargo(String initName, double initWeight, CargoStrength initStrength){
    if (initName != null && initWeight > 0){
      name = initName;
      weight = initWeight;
      strength = initStrength;
    }
    else throw new IllegalArgumentException();
  }
/** This method returns name of this Cargo
*
*@return
*Returns name of this cargo.
*
*/
  public String getName(){
    return name;
  }
/** This method returns name of this Cargo
*
*@return
*Returns weight of this cargo.
*
*/
  public double getWeight(){
    return weight;
  }
/** This method returns name of this Cargo
*
*@return
*Returns strength of this cargo.
*
*/
  public CargoStrength getStrength(){
    return strength;
  }




}
