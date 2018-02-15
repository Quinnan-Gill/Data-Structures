package cs445.lab5;

public class Lab5 {


  static <T> void reverse(T[] a) {
    T swap1 = a[0];
    T swap2 = a[a.length-1];
    if(a.length > 2) {
      @SuppressWarnings("unchecked")
      T[] newArr = (T[])new Object[a.length-2];
      for(int i = 1; i < a.length-1; i++) {
        newArr[i-1] = a[i];
      }
      reverse(newArr);
      a[0] = swap2;
      a[a.length-1] = swap1;
      for(int i = 1; i < a.length-1; i++) {
        a[i] = newArr[i-1];
      }

    }
  }


  static String replace(String str, char before, char after) {
    if(str.length() == 0) {
      return "";
    }else {
      if(str.charAt(0) == before) {
        return after + "" + replace(str.substring(1), before, after);
      }else {
        return str.charAt(0) + "" + replace(str.substring(1), before, after);
      }
    }
  }
}
