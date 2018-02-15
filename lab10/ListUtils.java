package cs445.lab10;

import java.util.Iterator;

public class ListUtils {

    public static <T> void printList(ListInterface<T> list) {
      Iterator<T> iter = list.iterator();
      for(int i=0; i < list.getSize(); i++) {
        System.out.print(iter.next() + " ");
      }
      System.out.println();
    }

    public static void removeShortStrings(ListInterface<String> list, int limit) {
      Iterator<String> iter = list.iterator();
      int len = list.getSize();
      for(int i = 0; i < len; i++){
        String temp = iter.next();
        if(temp.length() < limit) {
          iter.remove();
        }
        
      }
    }

    public static void main(String[] args) {
		ListInterface<String> myList = new ArrayList<String>();

		myList.add("Apples");
		myList.add("are");
		myList.add("so");
		myList.add("good");
		myList.add("...");
		myList.add("in");
		myList.add("fact");
		myList.add("I");
		myList.add("eat");
		myList.add("them");
		myList.add("when");
		myList.add("I");
		myList.add("write");
		myList.add("programs");

        // prints "Apples are so good ... in fact I eat them when I write programs"
		System.out.println("Original list:");
		printList(myList);

        //prints "Apples write programs"
		System.out.println("Strings of length 5 or greater:");
		removeShortStrings(myList, 5);
		printList(myList);
    }
}
