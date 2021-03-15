/** Derek Yin 113251504 Recitation Section 1
*  This enum defines the strength of cargo, fragile, moderate, and sturdy.
*
*  @author Derek Yin
*/
public enum CargoStrength{
  FRAGILE, MODERATE, STURDY;
/** This method converts the strength of a Cargo to a String to facilitate printing.
*@return
*returns "Fragile", "Moderate", or "Sturdy"
*/
  public String strengthToString(){
    if (this.equals(this.FRAGILE)){
      return "Fragile";
    }
    else if (this.equals(this.MODERATE)){
      return "Moderate";
    }
    else if (this.equals(this.STURDY)){
      return "Sturdy";
    }
    else return "";
  }
/** This method returns the strength of a cargo as an Integer, 1 for fragile, 2 for moderate, 3 for sturdy.
*@return
*returns 1 for fragile, 2 for moderate, 3 for sturdy.
*/
  public int intStrength(){
    if (this.equals(this.FRAGILE)){
      return 1;
    }
    else if (this.equals(this.MODERATE)){
      return 2;
    }
    else if (this.equals(this.STURDY)){
      return 3;
    }
    else return -1;
  }
}
