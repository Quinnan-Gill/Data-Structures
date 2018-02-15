package cs445.lab10;

import java.util.Iterator;

public class SieveOfEratosthenes {
    public static ListInterface<Integer> primesUpTo(int max) {
        ListInterface<Integer> list = new ArrayList<Integer>();
        // ListInterface<Boolean> helper = new ArrayList<Boolean>();
        for(int i = 2; i <= max; i++) {
          list.add(new Integer(i));
          // helper.add(new Boolean(true));
        }
        boolean untouched = false;

        Iterator<Integer> holdIter = list.iterator();
        // Iterator<Integer> helpIter = helper.iterator();

        while(holdIter.hasNext()) {
          int compVal = holdIter.next().intValue();
          Iterator<Integer> primeIter = list.iterator();
          while(primeIter.hasNext()){
            int test = primeIter.next().intValue();
            if(test <= compVal){
              continue;
            }else if(test % compVal == 0){
              primeIter.remove();
            }
          }

        }
        return list;
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
            ListUtils.printList(result);
            System.out.println(" ");
        } else {
            System.out.println("primesUpTo() returned null. Did you complete it?");
        }
    }
}
