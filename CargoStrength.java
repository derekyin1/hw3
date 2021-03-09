public enum CargoStrength{
  FRAGILE, MODERATE, STURDY;

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
}
