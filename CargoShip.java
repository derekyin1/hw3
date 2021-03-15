import java.util.Stack;
public class CargoShip{
  private java.util.Stack[] stacks;
  private java.util.Stack dock;
  private int maxHeight;
  private int biggestHeight;
  private double maxWeight;
  private double totalWeight;

  public CargoShip(int numStacks, int initMaxHeight, double initMaxWeight){
    if (numStacks > 0 && initMaxHeight > 0 && initMaxWeight > 0){
      stacks = new Stack[numStacks];
      dock = new Stack();
      for (int i = 0; i < numStacks; i++){
        stacks[i] = new Stack();
      }
      maxHeight = initMaxHeight;
      maxWeight = initMaxWeight;
      biggestHeight = 0;
      totalWeight = 0;
    }
    else throw new IllegalArgumentException();
  }


  @SuppressWarnings("unchecked")
  public void pushCargo(Cargo cargo, int stack) throws FullStackException, ShipOverweightException, CargoStrengthException{
    if (cargo != null && stack == -1){
      if (!dock.isEmpty()){
        if (cargo.getStrength().intStrength() <= ((Cargo) dock.peek()).getStrength().intStrength()){
          dock.push(cargo);
        }
        else {
          throw new CargoStrengthException("");
        }
      }
      else{
        dock.push(cargo);
      }
    }
    else if (cargo != null && stack >= 1 && stack <= stacks.length){
      if (stacks[stack-1].size() < maxHeight){
        if (totalWeight + cargo.getWeight() <= maxWeight){
          if (!stacks[stack-1].isEmpty()){
            if (cargo.getStrength().intStrength() <= ((Cargo) stacks[stack-1].peek()).getStrength().intStrength()){
              stacks[stack-1].push(cargo);
              totalWeight += cargo.getWeight();
              if (stacks[stack-1].size()+1 > biggestHeight){
                biggestHeight = stacks[stack-1].size()+1;
              }
            }
            else{
              throw new CargoStrengthException("");
            }
          }
          else {
            stacks[stack-1].push(cargo);
            totalWeight += cargo.getWeight();
            if (stacks[stack-1].size()+1 > biggestHeight){
              biggestHeight = stacks[stack-1].size()+1;
            }
          }
        }
        else{
          throw new ShipOverweightException("");
        }
      }
      else{
        throw new FullStackException("");
      }
    }
    else throw new IllegalArgumentException();
  }

/*public Cargo popCargo(int stack) throws EmptyStackException{
    if (stack >= 1 && stack <= stacks.length){
      Cargo toReturn = (Cargo) stacks[stack-1].peek();
      stacks[stack-1].pop();
      return toReturn;
    }
    else throw new IllegalArgumentException();
  }
  */

  public Cargo popCargo(int stack) throws EmptyStackException{
    if (stack == -1){
      if (!dock.isEmpty()){
        return (Cargo) dock.pop();
      }
      else{
        throw new EmptyStackException("");
      }
    }
    else if (stack >= 1 && stack <= stacks.length){
      if (!stacks[stack-1].isEmpty()){
        if (stacks[stack-1].size() == biggestHeight){
          biggestHeight--;
        }
        totalWeight -= ((Cargo)stacks[stack-1].peek()).getWeight();
        return (Cargo) stacks[stack-1].pop();
      }
      else{
        throw new EmptyStackException("");
      }
    }
    else throw new IllegalArgumentException();

  }


  public void findAndPrint(String name){
    java.util.Stack[] clone = (Stack[]) stacks.clone();
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

  public void printShip(){
    String boatStacks = "\\=";
    String stackNum = " \\";
    String bottom = "  \\";
    for (int i = 1; i <= stacks.length; i++){
      boatStacks+= "|=====";
      if (i == stacks.length){
        bottom += "-----";
      }
      else bottom += "------";
      if (i == 1){
        stackNum += "   " + i;
      }
      else stackNum += "     " + i;
    }
    boatStacks+= "|=/";
    stackNum += "   /";
    bottom += "/";

    String boatCargo = "     ";
    java.util.Stack[] stackClone = new Stack[stacks.length];
    for (int i = 0; i < stacks.length; i++){
      stackClone[i] = (Stack) stacks[i].clone();
    }
    int height = biggestHeight;
    while (height > 0){
      for (int i = 0; i < stackClone.length; i++){
        if (stackClone[i].size() == height){
          if (((Cargo)stackClone[i].peek()).getStrength() == CargoStrength.FRAGILE){
            boatCargo += "F     ";
          }
          else if (((Cargo)stackClone[i].peek()).getStrength() == CargoStrength.MODERATE){
            boatCargo += "M     ";
          }
          else if (((Cargo)stackClone[i].peek()).getStrength() == CargoStrength.STURDY){
            boatCargo += "S     ";
          }
          stackClone[i].pop();
        }
        else boatCargo += "      ";
      }
      height--;
      boatCargo += "\n     ";
    }

    String dockCargo = "";
    java.util.Stack dockClone = (Stack) dock.clone();
      while(!dockClone.isEmpty()){
        dockCargo +="\n";
        if (((Cargo)dockClone.peek()).getStrength() == CargoStrength.FRAGILE){
          dockCargo += "         F";
        }
        else if (((Cargo)dockClone.peek()).getStrength() == CargoStrength.MODERATE){
          dockCargo += "         M";
        }
        else if (((Cargo)dockClone.peek()).getStrength() == CargoStrength.STURDY){
          dockCargo += "         S";
        }
        dockClone.pop();
      }
    String theDock = "Dock: |=====|\n      |=====|\n      |=====|\n";
    String weightDisplay = "Total Weight = " + totalWeight + " / " + maxWeight;





    System.out.println(boatCargo);
    System.out.println(boatStacks);
    System.out.println(stackNum);
    System.out.println(bottom);
    System.out.println(dockCargo);
    System.out.println(theDock);
    System.out.println(weightDisplay);
  }

  public int dockSize(){
    return dock.size();
  }
  public static void main(String[] args) {
    CargoShip test = new CargoShip(8,30,3000);
    Cargo testCargo = new Cargo("test12", 1, CargoStrength.MODERATE);
    Cargo testCargo1 = new Cargo("test", 1, CargoStrength.STURDY);
    Cargo testCargo2 = new Cargo("test1", 1, CargoStrength.MODERATE);
    Cargo testCargo3 = new Cargo("test13", 1, CargoStrength.FRAGILE);

    try{
      test.pushCargo(testCargo3, 1);
      test.pushCargo(testCargo3, 2);
      test.pushCargo(testCargo3, 1);
      test.pushCargo(testCargo3, 1);
    }
    catch (FullStackException e) {
      System.out.println("Operation failed! Cargo stack is at maximum height.");

    }
    catch (ShipOverweightException e){
      System.out.println("Operation failed! Ship is at maximum capacity.");

    }
    catch (CargoStrengthException e){
      System.out.println("Operation failed! Cargo at top of stack cannot support weight.");
    }
    System.out.println(test.dockSize());
    test.printShip();
    System.out.println(test.dockSize());
      test.printShip();

  }


}
