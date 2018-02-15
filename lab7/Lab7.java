package cs445.lab7;

import java.util.Arrays;

public class Lab7 {
    /**
     * Reverses the order of the objects in an array, using divide and conquer
     * recursion.
     */
    public static <T> void reverse(T[] a) {
      if(a.length <= 1){
        //Do nothing
      }else {
        @SuppressWarnings("unchecked")
        T[] firstHalf = (T[])new Object[a.length/2];

        for(int i = 0; i < a.length/2; i++){
          firstHalf[i] = a[i];
        }
        System.out.println("The firstHalf = " + Arrays.toString(firstHalf));
        @SuppressWarnings("unchecked")
        T[] secondHalf = (T[])new Object[(int)(((double) a.length)/2+0.5)];
        int index = 0;
        System.out.println("Length of sub: " + secondHalf.length);
        for(int i = a.length/2; i < a.length; i++){
          System.out.println("Index= " + index + "  i= " + i);
          secondHalf[index] = a[i];
          index++;
        }
        System.out.println("The secondHalf = " + Arrays.toString(secondHalf));
        reverse(firstHalf);
        reverse(secondHalf);
        for(int i = 0; i < secondHalf.length; i++){
          a[i] = secondHalf[i];
        }
        index = 0;
        System.out.println("It goes: " + ((a.length-1) - (a.length/2)));
        for(int i = secondHalf.length; i < a.length; i++){
          System.out.println("Index= " + index + "  i= " + i);
          a[i] = firstHalf[index];
          index++;
        }
      }

    }


    /**
     * Replaces each instance of character before with character after within
     * str, and returns the resulting string. This uses divide and conquer
     * recursion.
     */
    public static String replace(String str, char before, char after) {
      System.out.println(str);
      if(str.length() == 0){
        return "";
      }else if(str.length() == 1){
        if(str.charAt(0) == before) {
          return after + "";
        }else{
          return str;
        }
      } else {
        int mid = str.length()/2;
        char result;
        if(str.charAt(mid) == before) {
          result = after;
        } else {
          result = str.charAt(mid);
        }
        return replace(str.substring(0,mid), before, after)
                + result + ""
                + replace(str.substring(mid+1, str.length()), before, after);
      }
    }
}
