public class CargoShip{
  private java.util.Stack[] stacks;
  private int maxHeight;
  private double maxWeight;

  public CargoShip(int numStacks, int initMaxHeight, double initMaxWeight){
    if (numStacks > 0 && initMaxHeight > 0 && initMaxWeight > 0){
      stacks = new java.util.Stack[numStacks];
      maxHeight = initMaxHeight;
      maxWeight = initMaxWeight;
    }
    else throw new IllegalArgumentException();
  }

  public void pushCargo(Cargo cargo, int stack) /*throws FullStackException, ShipOverweightException, CargoStrengthException*/{
    if (cargo != null && stack >= 1 && stack <= stacks.length){

    }
    else throw new IllegalArgumentException();
  }

  public Cargo popCargo(int stack) /*throws EmptyStackException*/{
    if (stack >= 1 && stack <= stacks.length){

    }
    else throw new IllegalArgumentException();
  }

  public void findAndPrint(String name){

  }

  


}
