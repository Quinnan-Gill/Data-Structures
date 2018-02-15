package cs445.a5;

import java.util.Iterator;

public class TernaryTreeTest {

  public static void main(String[] args) {
    /*
                 1
            /    |     \
           /     |      \
          2      3       4
        / | \  / | \   / |  \
       5  6 7 8  9 10 11 12 13
     */

    // TernaryTreeInterface<Integer> empty = new TernaryTree<>();
    // System.out.println("The empty tree is empty? " + empty.isEmpty());
    //
    // //Bottom level first branch
    // TernaryTreeInterface<Integer> five = new TernaryTree<>(new Integer(5));
    // TernaryTreeInterface<Integer> six = new TernaryTree<>(new Integer(6));
    // TernaryTreeInterface<Integer> seven = new TernaryTree<>(new Integer(7));
    //
    // //Bottom level second branch
    // TernaryTreeInterface<Integer> eight = new TernaryTree<>(new Integer(8));
    // TernaryTreeInterface<Integer> nine = new TernaryTree<>(new Integer(9));
    // TernaryTreeInterface<Integer> ten = new TernaryTree<>(new Integer(10));
    //
    // //Bottom level third branch
    // TernaryTreeInterface<Integer> eleven = new TernaryTree<>(new Integer(11));
    // TernaryTreeInterface<Integer> twelve = new TernaryTree<>(new Integer(12));
    // TernaryTreeInterface<Integer> thirteen = new TernaryTree<>(new Integer(13));
    //
    // // Middle level
    // TernaryTreeInterface<Integer> two = new TernaryTree<>(new Integer(2),
    //                                         five, six, seven);
    // TernaryTreeInterface<Integer> three = new TernaryTree<>(new Integer(3),
    //                                         eight, nine, ten);
    // TernaryTreeInterface<Integer> four = new TernaryTree<>(new Integer(4),
    //                                         eleven, twelve, thirteen);
    // // Top level or the root
    // TernaryTreeInterface<Integer> one = new TernaryTree<>(new Integer(1),
    //                                         two, three, four);
    //
    // System.out.println("The root is: " + one.getRootData());
    // System.out.println("The number of nodes: " + one.getNumberOfNodes());
    // System.out.println("The height of five: " + one.getHeight());
    //
    // Iterator<Integer> preIt = one.getPreorderIterator();
    // System.out.print("The pre-order traversal is: ");
    // while(preIt.hasNext()) {
    //   System.out.print(preIt.next()+ " ");
    // }
    // System.out.println("\n");
    //
    // Iterator<Integer> postIt = one.getPostorderIterator();
    // System.out.print("The post-order traversal is: ");
    // while(postIt.hasNext()) {
    //   System.out.print(postIt.next()+ " ");
    // }
    // System.out.println("\n");
    //
    // Iterator<Integer> levelIt = one.getLevelOrderIterator();
    // System.out.print("The post-order traversal is: ");
    // while(levelIt.hasNext()) {
    //   System.out.print(levelIt.next()+ " ");
    // }
    // System.out.println("\n");
    // TernaryTreeInterface<Integer> five = new TernaryTree<>(new Integer(5));


    /* More abstract tree
            1
              \
              4
                \
                13
     */
    // // Bottom branch
    // TernaryTreeInterface<Integer> thirteen = new TernaryTree<>(new Integer(13));
    //
    // //Middle
    // TernaryTreeInterface<Integer> four = new TernaryTree<>(new Integer(4)
    //                                                         , null
    //                                                         , null
    //                                                         , thirteen);
    //
    // //Top or Root
    // TernaryTreeInterface<Integer> one = new TernaryTree<>(new Integer(1)
    //                                                       , null
    //                                                       , null
    //                                                       , four);
    //
    // //Assemble
    //
    //
    // System.out.println("The root is: " + one.getRootData());
    // System.out.println("The number of nodes: " + one.getNumberOfNodes());
    // System.out.println("The height of five: " + one.getHeight());
    //
    // Iterator<Integer> preIt = one.getPreorderIterator();
    // System.out.print("The pre-order traversal is: ");
    // while(preIt.hasNext()) {
    //   System.out.print(preIt.next()+ " ");
    // }
    // System.out.println("\n");
    //
    // Iterator<Integer> postIt = one.getPostorderIterator();
    // System.out.print("The post-order traversal is: ");
    // while(postIt.hasNext()) {
    //   System.out.print(postIt.next()+ " ");
    // }
    // System.out.println("\n");
    //
    // Iterator<Integer> levelIt = one.getLevelOrderIterator();
    // System.out.print("The post-order traversal is: ");
    // while(levelIt.hasNext()) {
    //   System.out.print(levelIt.next()+ " ");
    // }
    // System.out.println("\n");
    // TernaryTreeInterface<Integer> five = new TernaryTree<>(new Integer(5));

    /*
                            1
                  /         |       \
                 2          3        4
                 |    \   / | \   /  |  \
                 6    7   8  9 10 11 12  13
              /  |  / | \          |
            14  15 16 17 18        19
     */

     // TernaryTreeInterface<Integer> forteen = new TernaryTree<>(new Integer(14));
     // TernaryTreeInterface<Integer> fifteen = new TernaryTree<>(new Integer(15));
     //
     // TernaryTreeInterface<Integer> sixteen = new TernaryTree<>(new Integer(16));
     // TernaryTreeInterface<Integer> seventeen = new TernaryTree<>(new Integer(17));
     // TernaryTreeInterface<Integer> eighteen = new TernaryTree<>(new Integer(18));
     //
     // TernaryTreeInterface<Integer> nineteen = new TernaryTree<>(new Integer(19));
     //
     // //3rd level has children
     // TernaryTreeInterface<Integer> six = new TernaryTree<>(new Integer(6),
     //                                          forteen, fifteen, null);
     // TernaryTreeInterface<Integer> seven = new TernaryTree<>(new Integer(7),
     //                                            sixteen, seventeen, eighteen);
     //
     // //Bottom level second branch
     // TernaryTreeInterface<Integer> eight = new TernaryTree<>(new Integer(8));
     // TernaryTreeInterface<Integer> nine = new TernaryTree<>(new Integer(9));
     // TernaryTreeInterface<Integer> ten = new TernaryTree<>(new Integer(10));
     //
     // //Bottom level third branch
     // TernaryTreeInterface<Integer> eleven = new TernaryTree<>(new Integer(11),
     //                                            null, nineteen, null);
     // TernaryTreeInterface<Integer> twelve = new TernaryTree<>(new Integer(12));
     // TernaryTreeInterface<Integer> thirteen = new TernaryTree<>(new Integer(13));
     //
     // // Middle level
     // TernaryTreeInterface<Integer> two = new TernaryTree<>(new Integer(2),
     //                                         null, six, seven);
     // TernaryTreeInterface<Integer> three = new TernaryTree<>(new Integer(3),
     //                                         eight, nine, ten);
     // TernaryTreeInterface<Integer> four = new TernaryTree<>(new Integer(4),
     //                                         eleven, twelve, thirteen);
     // // Top level or the root
     // TernaryTreeInterface<Integer> one = new TernaryTree<>(new Integer(1),
     //                                         two, three, four);
     //
     // System.out.println("The root is: " + one.getRootData());
     // System.out.println("The number of nodes: " + one.getNumberOfNodes());
     // System.out.println("The height of five: " + one.getHeight());
     //
     // Iterator<Integer> preIt = one.getPreorderIterator();
     // System.out.print("The pre-order traversal is: ");
     // while(preIt.hasNext()) {
     //   System.out.print(preIt.next()+ " ");
     // }
     // System.out.println("\n");
     //
     // Iterator<Integer> postIt = one.getPostorderIterator();
     // System.out.print("The post-order traversal is: ");
     // while(postIt.hasNext()) {
     //   System.out.print(postIt.next()+ " ");
     // }
     // System.out.println("\n");
     //
     // Iterator<Integer> levelIt = one.getLevelOrderIterator();
     // System.out.print("The post-order traversal is: ");
     // while(levelIt.hasNext()) {
     //   System.out.print(levelIt.next()+ " ");
     // }
     // System.out.println("\n");

     /*
            1                     1
                         /                   \
            2           2                    3
                        |      \
            3           10     4
                          /    |    \
            4            5     6    11
                       / |       \   |
            5         8  9       16  12
                                     |
            6                        13
                                   /    \
            7                    14     15
      */

      // Height = 7
      TernaryTreeInterface<Integer> forteen = new TernaryTree<>(new Integer(14);
      TernaryTreeInterface<Integer> fifteen = new TernaryTree<>(new Integer(15));
      System.out.println("Forteen height and number of nodes: " + forteen.getHeight()
                            + " and " + forteen.getNumberOfNodes());

      // Height = 6
      TernaryTreeInterface<Integer> thirteen = new TernaryTree<>(new Integer(13),
                                                    forteen, null, fifteen);
      System.out.println("Thirteen height and number of nodes: " + thirteen.getHeight()
                            + " and " + thirteen.getNumberOfNodes());

      // Height = 5
      TernaryTreeInterface<Integer> twelve = new TernaryTree<>(new Integer(12),
                                                    null, thirteen, null)

      TernaryTreeInterface<Integer> sixteen = new TernaryTree<>(new Integer(16));
      TernaryTreeInterface<Integer> eight = new TernaryTree<>(new Integer(8));
      TernaryTreeInterface<Integer> nine = new TernaryTree<>(new Integer(9));
      System.out.println("Twelve height and number of nodes: " + twelve.getHeight()
                            + " and " + twelve.getNumberOfNodes());

      // Height = 4
      TernaryTreeInterface<Integer> five = new TernaryTree<>(new Integer(12),
                                                null, twelve, null);
      TernaryTreeInterface<Integer> six = new TernaryTree<>(new Integer(6),
                                                null, null, sixteen);
      TernaryTreeInterface<Integer> eleven = new TernaryTree<>(new Integer(11),
                                                eight, nine, null);
      System.out.println("Eleven height and number of nodes: " + eleven.getHeight()
                            + " and " + eleven.getNumberOfNodes());

      // Height = 3
      TernaryTreeInterface<Integer> ten = new TernaryTree<>(new Integer(10));
      TernaryTreeInterface<Integer> four = new TernaryTree<>(new Integer(4),
                                                five, six, eleven);
      System.out.println("Four height and number of nodes: " + four.getHeight()
                            + " and " + four.getNumberOfNodes());

      // Height = 2
      TernaryTreeInterface<Integer> two = new TernaryTree<>(new Integer(2),
                                                null, ten, four);
      TernaryTreeInterface<Integer> three = new TernaryTree<>(new Integer(3));
      System.out.println("Two height and number of nodes: " + two.getHeight()
                            + " and " + two.getNumberOfNodes());

      // Height = 1
      TernaryTreeInterface<Integer> three = new TernaryTree<>(new Integer(1),
                                                  two, null, three);
      System.out.println("Two height and number of nodes: " + two.getHeight()
                            + " and " + two.getNumberOfNodes());

      System.out.println("The root is: " + one.getRootData());
      System.out.println("The number of nodes: " + one.getNumberOfNodes());
      System.out.println("The height of five: " + one.getHeight());

      Iterator<Integer> preIt = one.getPreorderIterator();
      System.out.print("The pre-order traversal is: ");
      while(preIt.hasNext()) {
        System.out.print(preIt.next()+ " ");
      }
      System.out.println("\n");

      Iterator<Integer> postIt = one.getPostorderIterator();
      System.out.print("The post-order traversal is: ");
      while(postIt.hasNext()) {
        System.out.print(postIt.next()+ " ");
      }
      System.out.println("\n");

      Iterator<Integer> levelIt = one.getLevelOrderIterator();
      System.out.print("The post-order traversal is: ");
      while(levelIt.hasNext()) {
        System.out.print(levelIt.next()+ " ");
      }
      System.out.println("\n");
  }

}
