/** Derek Yin 113251504 Recitation Section 1
*  This class defines a CargoShip on which Cargo objects are to be placed. Its specifications include number of stacks, maximum height, and maximum weight.
*
*  @author Derek Yin
*/
import java.util.Stack;
public class CargoShip{
  private java.util.Stack[] stacks;
  private java.util.Stack dock;
  private int maxHeight;
  private int biggestHeight;
  private double maxWeight;
  private double totalWeight;
/** This is a constructor that creates a new CargoShip with desired specifications
* @param numStacks
* desired total number of stacks of cargoship
* @param initMaxHeight
* desired initial maximum height of cargoship
* @param initMaxWeight
* desired initial max weight of cargoship.
* @throws
* IllegalArgumentException if any parameters are negative
*/
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

/** This method attempts to push cargo from one stack to another.
* @param cargo
* desired cargo to be pushed
* @param stack
* desired stack to push cargo to
*
*@throws
*throws FullStackException if desired stack is full
*@throws
*throws ShipOverweightException if Ship is over weight after adding.
*@throws
*throws CargoStrengthException if Cargo at top of desired stack is more fragile than the desired Cargo.
*/
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

/** This method attempts to pop cargo from a desired stack.
* @param stack
* desired stack to pop cargo from.
*
* @return
* returns popped cargo.
*
*@throws
*throws EmptyStackException if desired stack is empty.
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
/** This method attempts to access the top cargo from a desired stack.
* @param stack
* desired stack to peek top cargo from
*
* @return
* returns accessed cargo.
*
*@throws
*throws EmptyStackException if desired stack is empty.
*/
  public Cargo peekCargo(int stack) throws EmptyStackException{
    if (stack == -1){
      if (!dock.isEmpty()){
        return (Cargo) dock.peek();
      }
      else{
        throw new EmptyStackException("");
      }
    }
    else if (stack >= 1 && stack <= stacks.length){
      if (!stacks[stack-1].isEmpty()){
        return (Cargo) stacks[stack-1].peek();
      }
      else{
        throw new EmptyStackException("");
      }
    }
    else throw new IllegalArgumentException();
  }

/** This method attempts to find and print all instances of Cargo with a certain name in a formatted table.
* @param name
* desired name of Cargo to find on the ship.
*/
  public void findAndPrint(String name){
    java.util.Stack[] clone = new Stack[stacks.length];
    for (int i = 0; i < stacks.length; i++){
      clone[i] = (Stack) stacks[i].clone();
    }
    boolean empty = true;
    System.out.printf("%-9s%-8s%-9s%1s", " Stack", "Depth", "Weight", "Strength");
    System.out.print("\n");
    System.out.println("=======+=======+========+==========");
    for (int i = 0; i < clone.length; i++){
      int depth = 0;
      while (!clone[i].isEmpty()){
        if (((Cargo) clone[i].peek()).getName().equals(name)){
          System.out.printf("%-9s%-8d%-9s%1s", " " + (i+1), depth, ((Cargo) clone[i].peek()).getWeight(), ((Cargo) clone[i].peek()).getStrength().strengthToString());
          System.out.print("\n");
          empty = false;
          depth++;
        }
        else {
          depth++;
        }
        clone[i].pop();
      }
    }
    if (empty){
      System.out.println("Cargo " + name + " could not be found on the ship.");
    }
  }
/** This method prints the CargoShip, dock, and current and max weight in the Terminal.
*
*/
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
/** This method returns size of dock
*
*@return
*Returns size of dock
*
*/
  public int dockSize(){
    return dock.size();
  }
/** This method clears dock.
*
*/
  public void clearDock(){
    while (!dock.isEmpty()){
      dock.pop();
    }
  }


}
