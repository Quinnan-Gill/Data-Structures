package cs445.a1;

public class SetTest {

  //CHECK THE SIZE COUNTER
  public static void main(String args[]) {
    SetInterface<String> set = new Set<String>(2);
    try {
      System.out.println("Is it empty: " + set.isEmpty());
      set.add("testing");
      set.add("hello");
      set.add("world");
      set.add("Quinnan");
      set.add("what");
      set.add("up");
      if(set.remove() instanceof Object){
        System.out.println("It is an instanceof a string");
      }
      if(set.toArray() instanceof String[]){
        System.out.println("toArray is workings");
      }
      printArr(set.toArray());

    }catch(IllegalArgumentException e) {
      System.out.println("Mr. wise guy, ehhhh, has to be unique, ehhhh");
    }catch(SetFullException e) {
      System.out.println("Way to go, you messed it up");
    }
  }

  public static void printArr(Object[] arr) {
    if(arr instanceof String[]) {
      System.out.println("Is a string array");
    }
    for(int i = 0; i < arr.length; i++) {
      if(arr[i] instanceof String) {
        System.out.println("Not casted");
      }else if((String) arr[i] instanceof String) {
        System.out.println("Is casted");
      }
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }
}
