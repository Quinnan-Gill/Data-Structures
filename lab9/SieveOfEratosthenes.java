package cs445.lab9;

public class SieveOfEratosthenes {
    public static ListInterface<Integer> primesUpTo(int max) {
        ListInterface<Integer> list = new ArrayList<Integer>();
        ListInterface<Boolean> helper = new ArrayList<Boolean>();

        for(int i = 2; i <= max; i++) {
          list.add(new Integer(i));
          helper.add(new Boolean(true));
        }
        boolean untouched = false;
        int start = 0;
        while(!untouched){
          // System.out.println("Untouched= " + untouched);
          if(!helper.get(start)){
            start++;
            continue;
          }
          untouched = true;
          for(int i=start+1; i < list.getSize(); i++){
            if(list.get(i)%list.get(start) == 0) {
              untouched = false;
              helper.set(i, new Boolean(false));
            }
          }
          start++;
        }
        ListInterface<Integer> result = new ArrayList<Integer>();
        for(int i =0; i < list.getSize(); i++){
          if(helper.get(i)) {
            result.add(list.get(i));
          }
        }
        return result;
    }

    public static void main(String[] args) {
        int end = 0;
        try {
            end = new Integer(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Please use a integer parameter for maximum value");
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please use a integer parameter for maximum value");
            return;
        }

        ListInterface<Integer> result = primesUpTo(end);
        if (result != null) {
            System.out.println("Primes:");
            for (int i = 0; i < result.getSize(); i++) {
                System.out.print(result.get(i) + " ");
            }
            System.out.println(" ");
        } else {
            System.out.println("primesUpTo() returned null. Did you complete it?");
        }
    }
}
