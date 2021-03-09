import java.util.Stack;
public class CargoShip{
  private java.util.Stack[] stacks;
  private int maxHeight;
  private double maxWeight;

  public CargoShip(int numStacks, int initMaxHeight, double initMaxWeight){
    if (numStacks > 0 && initMaxHeight > 0 && initMaxWeight > 0){
      stacks = new Stack[numStacks];
      for (int i = 0; i < numStacks; i++){
        stacks[i] = new Stack();
      }
      maxHeight = initMaxHeight;
      maxWeight = initMaxWeight;
    }
    else throw new IllegalArgumentException();
  }
  @SuppressWarnings("unchecked")
  public void pushCargo(Cargo cargo, int stack) /*throws FullStackException, ShipOverweightException, CargoStrengthException*/{
    if (cargo != null && stack >= 1 && stack <= stacks.length){
        stacks[stack-1].push(cargo);
    }
    else throw new IllegalArgumentException();
  }

  public Cargo popCargo(int stack) /*throws EmptyStackException*/{
    if (stack >= 1 && stack <= stacks.length){
      Cargo toReturn = (Cargo) stacks[stack-1].peek();
      stacks[stack-1].pop();
      /*Cargo popped = stacks[stack-1].pop();
      return popped;*/
      return toReturn;
    }
    else throw new IllegalArgumentException();
  }

  public void findAndPrint(String name){
    java.util.Stack[] clone = stacks;
    System.out.printf("%-9s%-8s%-9s%1s", " Stack", "Depth", "Weight", "Strength");
    System.out.print("\n");
    System.out.println("=======+=======+========+==========");
    for (int i = 0; i < clone.length; i++){
      while (!clone[i].isEmpty()){
        int depth = 0;
        if (((Cargo) clone[i].peek()).getName().equals(name)){
          System.out.printf("%-9s%-8d%-9s%1s", " " + (i+1), depth, ((Cargo) clone[i].peek()).getWeight(), ((Cargo) clone[i].peek()).getStrength().strengthToString());
          System.out.print("\n");
          clone[i].pop();
        }
        else {
          depth++;
          clone[i].pop();
        }
      }
    }
  }

  public static void main(String[] args) {
    CargoShip test = new CargoShip(3,1,1);
    Cargo testCargo2 = new Cargo("test1", 1, CargoStrength.FRAGILE);
    Cargo testCargo = new Cargo("test", 1, CargoStrength.FRAGILE);
    Cargo testCargo1 = new Cargo("test", 1, CargoStrength.FRAGILE);

    test.pushCargo(testCargo, 1);
    test.pushCargo(testCargo1, 1);
    test.pushCargo(testCargo2, 1);
    //test.findAndPrint("test");
    test.findAndPrint("test1");
  }


}
