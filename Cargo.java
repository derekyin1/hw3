public class Cargo{
  private String name;
  private double weight;
  private CargoStrength strength;

  public Cargo(String initName, double initWeight, CargoStrength initStrength){
    if (initName != null && initWeight > 0){
      name = initName;
      weight = initWeight;
      strength = initStrength;
    }
    else throw new IllegalArgumentException();
  }

  public String getName(){
    return name;
  }

  public double getWeight(){
    return weight;
  }

  public CargoStrength getStrength(){
    return strength;
  }


}
