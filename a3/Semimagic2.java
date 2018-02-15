package cs445.a3;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Set;

import java.io.File;
import java.io.FileNotFoundException;

public class Semimagic2 {

    private static boolean[][] perm;
    private static boolean permSet = true;
    public static boolean isFullSolution(int[][] square) {
        for(int i = 0; i < square.length; i++) {
          for(int j = 0; j < square[i].length; j++) {
            if(square[i][j] <= 0) {
              return false;
            }
          }
        }
        return correctSum(square) && isUnique(square);
    }

    private static boolean isUnique(int[][] square) {
      int n = square.length;
      int size = n * (n*n + 1) / 2;
      int[] freq = new int[size+1];
      for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
          if(square[i][j] <= size && square[i][j] > 0) {
            freq[square[i][j]]++;
          }
        }
      }

      for(int i = 0; i < freq.length; i++) {
        if(freq[i] > 1) {
          return false;
        }
      }
      return true;
    }


    private static boolean correctSum(int[][] square) {
      int n = square.length;
      int magicVal = n * (n*n + 1) / 2;
      for(int i = 0; i < square.length; i++) {
        int horiz = 0;
        for(int j = 0; j < square[i].length; j++) {
          horiz += square[i][j];
        }
        if(horiz != magicVal) {
          return false;
        }
      }
      for(int j = 0; j < square.length; j++) {
        int vert = 0;
        for(int i = 0; i < square[j].length; i++){
          vert += square[i][j];
        }
        if(vert != magicVal) {
          return false;
        }
      }
      return true;
    }



    public static boolean reject(int[][] square) {
      int n = square.length;
      int magicVal = n * (n*n + 1) / 2;
      // boolean temp = isUnique(square);
      if(n == 1){
        if(square[0][0] == 1) {
          return false;
        }
      }
      for(int i = 0; i < square.length; i++) {
        int horiz = 0;
        for(int j = 0; j < square.length; j++) {
          horiz += square[i][j];
          if(j < n-1 && horiz > magicVal) {
            return true;
          } else if(square[i][j] == magicVal || square[i][j] < 0) {
            return true;
          }
        }

      }
      for(int j = 0; j < square.length; j++) {
        int vert = 0;
        for(int i = 0; i < square.length; i++) {
          vert += square[i][j];
          if(i < n-1 && vert > magicVal) {
            return true;
          } else if(square[i][j] == magicVal) {
            return true;
          }
        }
      }
      return false;

    }

    public static int[][] extend(int[][] square) {
      int n = square.length;
      // System.out.println("extended");
      int magicVal = n * (n*n + 1) / 2;
      boolean extended = false;
      int[][] result = new int[square.length][square.length];
      for(int i =0; i < result.length; i++) {
        for(int j=0; j < result.length; j++) {
          if(square[i][j] != 0) {
            result[i][j] = square[i][j];
          }else{
            result[i][j] = 1;
            return result;
          }
        }
      }
      return null;
    }

    public static int[][] next(int[][] square) {
      // System.out.println("Nexted");
      int[][] result = new int[square.length][square.length];
      boolean nexted = false;
      int movI = 1;
      int movJ = 0;
      int n = square.length;
      int magicVal = n * (n*n + 1) / 2;
      // int i = 0;
      for(int i = 0; i < n; i++) {
        int horiz = 0;
        int vert = 0;
        for(int j = 0; j < n; j++) {
          if(((j == n-1 && i == n-1) || (j < n-1 && square[i][j+1] == 0) ||
          (j == n-1 && square[i+1][0] == 0)) && !nexted && !perm[i][j]) {
              if(horiz >= magicVal || vert >= magicVal || square[i][j] == magicVal) {
                  return null;
              } else {
                  // System.out.println("touched");
                  result[i][j] = square[i][j] + 1;
                  nexted = true;
                  break;
              }
          } else {
            // System.out.println(horiz);
            horiz += square[i][j];
            // System.out.println(vert);
            vert += square[j][i];
            result[i][j] = square[i][j];
          }
        }
      }
      return result;
    }

    private static boolean isZero(int[][] square, int i, int j){
      int n = square.length;
      if(j < n-1 && square[i][j+1] == 0){

      }
      return true;
    }


    static void testIsFullSolution() {
        int[][][] fullSolutions = new int[][][]{
          {
            {8, 1, 6},
            {3, 5, 7},
            {4, 9, 2}
          },
          {
            {1, 15, 14, 4},
            {8, 10, 11, 5},
            {9, 7, 6, 12},
            {16, 2, 3, 13}
          },
          {
            {3, 7, 5},
            {8, 6, 1},
            {4, 2, 9}
          },
          {
            {1}
          }
        };

        int[][][] notFullSolutions = new int[][][]{
          {
            {7, 1, 7},
            {3, 5, 7},
            {4, 9, 2}
          },
          {
            {8, 1, 6},
            {3, 5, 0},
            {4, 9, 2}
          },
          {
            {3, 1, 6},
            {8, 5, 7},
            {4, 9, 2}
          }
        };

        System.err.println("These should be full: ");
        for (int[][] test : fullSolutions) {

          if (isFullSolution(test)) {

            System.err.println("\tFull sol'n:\t" + output(test));
          } else {
            System.err.println("\tNot full sol'n:\t" + output(test));
          }
        }

        System.err.println("These should NOT be full:");
        for (int[][] test : notFullSolutions) {
            if (isFullSolution(test)) {
                System.err.println("\tFull sol'n:\t" + output(test));
            } else {
                System.err.println("\tNot full sol'n:\t" + output(test));
            }
        }
    }

    private static void testIsUnique() {
      int[][][] unique = new int[][][]{
        {
          {0, 0, 0},
          {3, 5, 7},
          {4, 9, 2}
        },
        {
          {1, 15, 14, 4},
          {8, 10, 11, 5},
          {9, 7, 6, 12},
          {16, 2, 3, 13}
        },
        {
          {3, 7, 5},
          {8, 6, 1},
          {4, 2, 9}
        }
      };

      int[][][] notUnique = new int[][][]{
        {
          {7, 1, 7},
          {3, 5, 7},
          {4, 9, 2}
        },
        {
          {8, 1, 6},
          {3, 5, 0},
          {4, 9, 2}
        },
        {
          {3, 1, 6},
          {8, 5, 7},
          {4, 9, 2}
        }
      };

      System.err.println("These should be full: ");
      for (int[][] test : unique) {

        if (isUnique(test)) {

          System.err.println("\tIs unique:\t" + output(test));
        } else {
          System.err.println("\tIs not unique:\t" + output(test));
        }
      }

      System.err.println("These should NOT be full:");
      for (int[][] test : notUnique) {
          if (isUnique(test)) {
              System.err.println("\tIs unique:\t" + output(test));
          } else {
              System.err.println("\tIs not unique:\t" + output(test));
          }
      }
    }

    private static String output(int[][] arr){
      String result = "[[ ";
      for(int i = 0; i < arr.length; i++) {
        for(int j = 0; j < arr[i].length; j++) {
          if(j < arr[i].length-1) {
            result += arr[i][j] + ", ";
          }else{
            result += arr[i][j] + " ]";
          }
        }
        if(i < arr.length-1) {
          result += ",\n\t\t\t [ ";
        }else {
          result += "]";
        }
      }
      return result;
    }

    static void testReject() {

      int[][][] notRejected = new int[][][]{
        {
          {0, 0, 0},
          {0, 0, 0},
          {0, 0, 0}
        },
        {
          {8, 1, 0},
          {0, 5, 0},
          {4, 9, 0}
        },
        {
          {1}
        }

      };

      int[][][] rejected = new int[][][]{
        {
          {8, 2, 6},
          {3, 5, 8},
          {4, 9, 2}
        },
        {
          {8, 8, 0},
          {3, 5, 0},
          {4, 0, 2}
        },
        {
          {8, 11, 6},
          {3, 5, 7},
          {4, 9, 22}
        }
      };

      System.err.println("These should NOT be rejected:");
      for (int[][] test : notRejected) {
          if (reject(test)) {
              System.err.println("\tRejected:\t" + output(test));
          } else {
              System.err.println("\tNot rejected:\t" + output(test));
          }
      }

      System.err.println("These should be rejected:");
      for (int[][] test : rejected) {
          if (reject(test)) {
              System.err.println("\tRejected:\t" + output(test));
          } else {
              System.err.println("\tNot rejected:\t" + output(test));
          }
      }
    }

    static void testExtend() {

        int[][][] noExtend = new int[][][]{
          {
            {15, 11, 6},
            {3, 5, 7},
            {4, 9, 22}
          },
          {
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
          },
          {
            {1, 1, 1},
            {1, 1, 1},
            {1, 15, 1}
          },
          {
            {1, 1, 1},
            {1, 1, 1},
            {1, 3, 1}
          }
        };

        System.err.println("These can NOT be extended:");
        for (int[][] test : noExtend) {
            perm  = new boolean[test.length][test.length];
            System.err.println("Extended ");
            printSquare(test);
            System.err.println(" to ");
            printSquare(extend(test));
        }
        System.out.println();

        int[][][] extend = new int[][][]{
          {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
          },
          {
            {15, 15, 15},
            {2222, 0, 11},
            {2, 0, 11}
          }
        };

        System.err.println("These can be extended:");
        for (int[][] test : extend) {
            perm = new boolean[test.length][test.length];
            System.err.println("Extended ");
            printSquare(test);
            System.err.println(" to ");
            printSquare(extend(test));
        }
        System.out.println();
    }

    static void testNext() {

      int[][][] noNext = new int[][][]{
        {
          {15, 11, 6},
          {3, 5, 7},
          {4, 9, 22}
        },
        {
          {1, 15, 1},
          {1, 1, 1},
          {1, 1, 1}
        },
        {
          {1, 1, 1},
          {1, 1, 1},
          {1, 15, 0}
        }
      };

      System.err.println("These can NOT be nexted:");
      for (int[][] test : noNext) {
          setDefaultPerm(test);
          perm  = new boolean[test.length][test.length];
          System.err.println("Nexted ");
          printSquare(test);
          System.err.println(" to ");
          printSquare(next(test));
      }
      System.out.println();

      int[][][] next = new int[][][]{
        {
          {0, 0, 0},
          {0, 0, 0},
          {0, 0, 0}
        },
        {
          {1, 1, 1},
          {1, 1, 1},
          {1, 1, 1}
        },
        {
          {1, 1, 1},
          {1, 1, 0},
          {0, 0, 0}
        },
        {
          {15, 1, 1},
          {1, 1, 1},
          {0, 0, 0}
        }
      };

      System.err.println("These can be nexted:");
      for (int[][] test : next) {
          perm = new boolean[test.length][test.length];
          System.err.println("Nexted ");
          printSquare(test);
          System.err.println(" to ");
          printSquare(next(test));
      }
      System.out.println();

      perm[1][2] = true;
      System.err.println("These can be nexted and has a Permanent:");
      for (int[][] test : next) {
          perm = new boolean[test.length][test.length];
          System.err.println("Nexted ");
          printSquare(test);
          System.err.println(" to ");
          printSquare(next(test));
      }
      System.out.println();
    }

    /**
     * Returns a string representation of a number, padded with enough zeros to
     * align properly for the current size square.
     * @param num the number to pad
     * @param size the size of the square that we are padding to
     * @return the padded string of num
     */
    static String pad(int num, int size) {
        // Determine the max value for a square of this size
        int max = size * size;
        // Determine the length of the max value
        int width = Integer.toString(max).length();
        // Convert num to string
        String result = Integer.toString(num);
        // Pad string with 0s to the desired length
        while (result.length() < width) {
            result = " " + result;
        }
        return result;
    }

    /**
     * Prints a square
     * @param square the square to print
     */
    public static void printSquare(int[][] square) {
        if (square == null) {
            System.out.println("Null (no solution)");
            return;
        }
        int size = square.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(pad(square[i][j], size) + " ");
            }
            System.out.print("\n");
        }
    }

    /**
     * Reads a square of a specified size from a plaintext file
     * @param filename the name of the file to read from
     * @param size the size of the square in the file
     * @return the square
     * @throws FileNotFoundException if the named file is not found
     */
    public static int[][] readSquare(String filename, int size)
                throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        int[][] square = new int[size][size];
        int val = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                square[i][j] = scanner.nextInt();
            }
        }
        return square;
    }

    /**
     * Solves the magic square
     * @param square the partial solution
     * @return the solution, or null if none
     */
    public static int[][] solve(int[][] square) {
        setPerm(square);
        if (reject(square)) return null;
        if (isFullSolution(square)) return square;
        int[][] attempt = extend(square);
        while (attempt != null) {
            int[][] solution;
            solution = solve(attempt);
            if (solution != null) return solution;
            attempt = next(attempt);
        }
        return null;
    }

    private static void setPerm(int[][] square) {
      if(permSet) {

        perm = new boolean[square.length][square.length];
        for(int i = 0; i < square.length; i++) {
          for(int j = 0; j < square.length; j++) {
            if(square[i][j] > 0) {
              perm[i][j] = true;
            }
          }
        }
        permSet = false;
      }
    }

    private static void setDefaultPerm(int[][] square) {
      perm = new boolean[square.length][square.length];
    }

    private static void resetPerm(){
      permSet = true;
    }

    public static void main(String[] args) {
        if (args.length >= 1 && args[0].equals("-t")) {
            System.out.println("Running tests...");
            // testIsUnique();
            testIsFullSolution();
            testReject();
            testExtend();
            testNext();
        } else if (args.length >= 1) {
            try {
                // First get the specified size
                int size = Integer.parseInt(args[0]);

                int[][] square;
                if (args.length >= 2) {
                    // Read the square from the file
                    square = readSquare(args[1], size);
                } else {
                    // Initialize to a blank square
                    square = new int[size][size];
                }

                System.out.println("Initial square:");
                printSquare(square);

                System.out.println("\nSolution:");
                int[][] result = solve(square);
                printSquare(result);
            } catch (NumberFormatException e) {
                // This happens if the first argument isn't an int
                System.err.println("First argument must be the size");
            } catch (FileNotFoundException e) {
                // This happens if the second argument isn't an existing file
                System.err.println("File " + args[1] + " not found");
            }
        } else {
            System.err.println("See usage in assignment description");
        }
    }
}
