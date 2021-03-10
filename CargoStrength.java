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
