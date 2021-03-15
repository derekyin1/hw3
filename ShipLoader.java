/** Derek Yin 113251504 Recitation Section 1
*  This class runs a menu that allows a user to create a ship and move cargo to and from a ship and a dock.
*
*  @author Derek Yin
*/
import java.util.Scanner;
 public class ShipLoader{


 /**This method runs the terminal menu and gives choices to the user.
 *
 */

  public static void main(String[] args) {
    boolean isRunning = false;
    boolean isMenu = true;
    System.out.println("Welcome to ShipLoader!");
    Scanner in = new Scanner(System.in);
    while (isMenu){
      System.out.println("Cargo Ship Parameters");
      System.out.println("--------------------------------------------------");
      System.out.println("Number of stacks:");
      if (in.hasNextInt()){
        int numStacks = in.nextInt();
        if (numStacks > 0){
          System.out.println("Maximum height of stacks:");
          if (in.hasNextInt()){
            int maxHeight = in.nextInt();
            if (maxHeight > 0){
              System.out.println("Maximum total cargo weight:");
              if (in.hasNextDouble()){
                double maxWeight = in.nextDouble();
                if (maxWeight > 0){
                  CargoShip newShip = new CargoShip(numStacks, maxHeight, maxWeight);
                  System.out.println("Cargo ship created.\nPulling ship in to dock...\nCargo ship ready to be loaded.");
                  isRunning = true;
                  while (isRunning){
                    System.out.print("\nPlease select an option: \n C) Create new cargo. \n L) Load cargo from dock. \n U) Unload cargo from ship. \n M) Move cargo between stacks. \n K) Clear dock. \n P) Print ship stacks. \n S) Search for cargo. \n Q) Quit. \n");
                    Scanner on = new Scanner(System.in);
                    String s = on.nextLine();
                    if (s.equals("Q") || s.equals("q")){
                      System.out.println("Program terminating normally...");
                      isRunning = false;
                      isMenu = false;
                    }
                    if (s.equals("C") || s.equals("c")){
                      System.out.println("Enter the name of the cargo:");
                      String name = on.nextLine();
                      System.out.println("Enter the weight of the cargo:");
                      if (on.hasNextDouble()){
                        double weight = on.nextDouble();
                        if (weight > 0){
                          System.out.println("Enter the container strength (F/M/S):");
                          on.nextLine();
                          String strength = on.nextLine();
                          if (strength.equals("F") || strength.equals("f")){
                            Cargo newCargo = new Cargo(name, weight, CargoStrength.FRAGILE);
                            try{
                              newShip.pushCargo(newCargo, -1);
                              System.out.println("Cargo " + name + " pushed onto the dock.");
                              newShip.printShip();
                            }
                            catch (CargoStrengthException e){
                              System.out.println("Operation failed! Cargo at top of stack cannot support weight.");
                              isRunning = false;
                              isRunning = true;
                            }
                            catch (FullStackException e) {
                              System.out.println("Operation failed! Cargo stack is at maximum height.");
                              isRunning = false;
                              isRunning = true;
                            }
                            catch (ShipOverweightException e){
                              System.out.println("Operation failed! Ship is at maximum capacity.");
                              isRunning = false;
                              isRunning = true;
                            }
                          }
                          else if (strength.equals("M") || strength.equals("m")){
                            Cargo newCargo = new Cargo(name, weight, CargoStrength.MODERATE);
                            try{
                              newShip.pushCargo(newCargo, -1);
                              System.out.println("Cargo " + name + " pushed onto the dock.");
                              newShip.printShip();
                            }
                            catch (CargoStrengthException e){
                              System.out.println("Operation failed! Cargo at top of stack cannot support weight.");
                              isRunning = false;
                              isRunning = true;
                            }
                            catch (FullStackException e) {
                              System.out.println("Operation failed! Cargo stack is at maximum height.");
                              isRunning = false;
                              isRunning = true;
                            }
                            catch (ShipOverweightException e){
                              System.out.println("Operation failed! Ship is at maximum capacity.");
                              isRunning = false;
                              isRunning = true;
                            }
                          }
                          else if (strength.equals("S") || strength.equals("s")){
                            Cargo newCargo = new Cargo(name, weight, CargoStrength.STURDY);
                            try{
                              newShip.pushCargo(newCargo, -1);
                              System.out.println("Cargo " + name + " pushed onto the dock.");
                              newShip.printShip();
                            }
                            catch (CargoStrengthException e){
                              System.out.println("Operation failed! Cargo at top of stack cannot support weight.");
                              isRunning = false;
                              isRunning = true;
                            }
                            catch (FullStackException e) {
                              System.out.println("Operation failed! Cargo stack is at maximum height.");
                              isRunning = false;
                              isRunning = true;
                            }
                            catch (ShipOverweightException e){
                              System.out.println("Operation failed! Ship is at maximum capacity.");
                              isRunning = false;
                              isRunning = true;
                            }
                          }
                          else {
                            System.out.println("Invalid input.");
                            isRunning = false;
                            isRunning = true;
                          }
                        }
                        else {
                          System.out.println("Invalid input.");
                          isRunning = false;
                          isRunning = true;
                        }
                      }
                      else {
                        System.out.println("Invalid input.");
                        isRunning = false;
                        isRunning = true;
                      }
                    }
                    if (s.equals("L") || s.equals("l")){
                      System.out.println("Select the load destination stack index:");
                      if (on.hasNextInt()){
                        int index = on.nextInt();
                        if (index > 0){
                          try {
                            Cargo toLoad = (Cargo) newShip.peekCargo(-1);
                            newShip.pushCargo(toLoad, index); // must pop after operation finishes.
                            newShip.popCargo(-1);
                            System.out.println("Cargo " + toLoad.getName() + " moved from dock to stack " + index);
                            newShip.printShip();
                          }
                          catch (CargoStrengthException e){
                            System.out.println("Operation failed! Cargo at top of stack cannot support weight.");
                            isRunning = false;
                            isRunning = true;
                          }
                          catch (FullStackException e) {
                            System.out.println("Operation failed! Cargo stack is at maximum height.");
                            isRunning = false;
                            isRunning = true;
                          }
                          catch (ShipOverweightException e){
                            System.out.println("Operation failed! Ship is at maximum capacity.");
                            isRunning = false;
                            isRunning = true;
                          }
                          catch (EmptyStackException e){
                            System.out.println("Operation failed! Dock is empty.");
                            isRunning = false;
                            isRunning = true;
                          }
                        }
                      }
                      else{
                        System.out.println("Invalid input.");
                        isRunning = false;
                        isRunning = true;
                      }
                    }
                    if (s.equals("U") || s.equals("u")){
                      System.out.println("Select the unload source stack index:");
                      if (on.hasNextInt()){
                        int index = on.nextInt();
                        if (index > 0){
                          try {
                            Cargo toLoad = (Cargo) newShip.peekCargo(index);//must pop after all exceptions are checekd for
                            newShip.pushCargo(toLoad, -1);
                            newShip.popCargo(index);
                            System.out.println("Cargo moved from stack " + index + " to dock.");
                            newShip.printShip();
                          }
                          catch (CargoStrengthException e){
                            System.out.println("Operation failed! Cargo at top of stack cannot support weight.");
                            isRunning = false;
                            isRunning = true;
                          }
                          catch (FullStackException e) {
                            System.out.println("Operation failed! Cargo stack is at maximum height.");
                            isRunning = false;
                            isRunning = true;
                          }
                          catch (ShipOverweightException e){
                            System.out.println("Operation failed! Ship is at maximum capacity.");
                            isRunning = false;
                            isRunning = true;
                          }
                          catch (EmptyStackException e){
                            System.out.println("Operation failed! Cargo stack is empty.");
                            isRunning = false;
                            isRunning = true;
                          }
                        }
                        else {
                          System.out.println("Invalid input.");
                          isRunning = false;
                          isRunning = true;
                        }
                      }
                      else{
                        System.out.println("Invalid input.");
                        isRunning = false;
                        isRunning = true;
                      }
                    }
                    if (s.equals("M") || s.equals("m")){
                      System.out.println("Source stack index:");
                      if (on.hasNextInt()){
                        int srcIndex = on.nextInt();
                        if (srcIndex > 0){
                          System.out.println("Destination stack index:");
                          if (on.hasNextInt()){
                            int dstIndex = on.nextInt();
                            if (dstIndex > 0){
                              try{
                                Cargo toLoad = (Cargo) newShip.peekCargo(srcIndex);
                                newShip.pushCargo(toLoad, dstIndex);
                                newShip.popCargo(srcIndex);
                                System.out.println("Cargo moved from stack " + srcIndex + " to stack " + dstIndex);
                                newShip.printShip();
                              }
                              catch (CargoStrengthException e){
                                System.out.println("Operation failed! Cargo at top of stack cannot support weight.");
                                isRunning = false;
                                isRunning = true;
                              }
                              catch (FullStackException e) {
                                System.out.println("Operation failed! Cargo stack is at maximum height.");
                                isRunning = false;
                                isRunning = true;
                              }
                              catch (ShipOverweightException e){
                                System.out.println("Operation failed! Ship is at maximum capacity.");
                                isRunning = false;
                                isRunning = true;
                              }
                              catch (EmptyStackException e){
                                System.out.println("Operation failed! Source stack is empty.");
                                isRunning = false;
                                isRunning = true;
                              }
                            }
                            else {
                              System.out.println("Invalid input.");
                              isRunning = false;
                              isRunning = true;
                            }
                          }
                          else {
                            System.out.println("Invalid input.");
                            isRunning = false;
                            isRunning = true;
                          }
                        }
                        else {
                          System.out.println("Invalid input.");
                          isRunning = false;
                          isRunning = true;
                        }
                      }
                      else{
                        System.out.println("Invalid input.");
                        isRunning = false;
                        isRunning = true;
                      }
                    }
                    if (s.equals("K") || s.equals("k")){
                      newShip.clearDock();
                      System.out.println("Dock cleared.");
                      newShip.printShip();
                    }
                    if (s.equals("P") || s.equals("p")){
                      newShip.printShip();
                    }
                    if (s.equals("S") || s.equals("s")){
                      System.out.println("Enter the name of the cargo:");
                      String name = on.nextLine();
                      newShip.findAndPrint(name);
                    }
                  }
                }
                else{
                  System.out.println("Invalid Input.");
                  isMenu = false;
                  isMenu = true;
                  in.nextLine();
                }
              }
              else{
                System.out.println("Invalid Input.");
                isMenu = false;
                isMenu = true;
                in.nextLine();
              }
            }
            else{
              System.out.println("Invalid Input.");
              isMenu = false;
              isMenu = true;
              in.nextLine();
            }
          }
          else{
            System.out.println("Invalid Input.");
            isMenu = false;
            isMenu = true;
            in.nextLine();
          }
        }
        else{
          System.out.println("Invalid Input.");
          isMenu = false;
          isMenu = true;
          in.nextLine();
        }
      }
      else{
        System.out.println("Invalid Input.");
        isMenu = false;
        isMenu = true;
        in.nextLine();
      }

    }

  /*  while (isRunning){

  } */

  }
}
