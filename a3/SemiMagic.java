package cs445.a3;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Set;

import java.io.File;
import java.io.FileNotFoundException;

public class SemiMagic {

    private static boolean[][] perm; //This is a boolean 2D array to have permanent values
    private static boolean permSet = true; //Just to set perm once

    /**
     * Evaluates if the matrix is a proper semi-magic square
     * @param  square The matrix being evaluated
     * @return If the matrix is a semi-magic square returns true else
     * it returns false
     */
    public static boolean isFullSolution(int[][] square) {
      int n = square.length;
      int magicVal = n * (n*n + 1) / 2;
      int horiz;
      int vert;
      int[] freq = new int[magicVal+1];
      for(int i = 0; i < square.length; i++) {
        horiz = 0;
        vert = 0;
        for(int j = 0; j < square[i].length; j++) {
          vert += square[j][i];
          horiz += square[i][j];
          if(square[i][j] <= 0) {
            return false;
          }
          if(square[i][j] <= magicVal && square[i][j] > 0) {
            freq[square[i][j]]++;
            if(freq[square[i][j]] > 1) {
              return false;
            }
          }

        }
        if(vert != magicVal || horiz != magicVal){
          return false;
        }
      }
      return true;
    }

    /**
     * Rejects the matrix if it has values that add up too big or
     * are just too big individually
     * @param  square The matrix being evaluated
     * @return Returns true if the the sum either horizontally or
     * vertically is too big
     */
    public static boolean reject(int[][] square) {
      int n = square.length;
      int magicVal = n * (n*n + 1) / 2;
      // boolean temp = isUnique(square);
      // int horiz;
      // int vert;
      // if(n == 1){
      //   if(square[0][0] == 1) {
      //     return false;
      //   }
      // }
      // for(int i = 0; i < square.length; i++) {
      //   horiz = 0;
      //   vert = 0;
      //   for(int j = 0; j < square.length; j++) {
      //     horiz += square[i][j];
      //     vert += square[j][i];
      //     if((j < n-1 && horiz > magicVal) || (i < n-1 && vert > magicVal)) {
      //       return true;
      //     } else if(square[i][j] == magicVal || square[i][j] < 0) {
      //       return true;
      //     }
      //   }
      //
      // }
      return false;

    }

    /**
     * Adds an element to the very first empty cell
     * @param  square The matrix being added to
     * @return If there is an empty cell this method will increase it by
     * one and return it, but if all cells are full then return null
     */
    public static int[][] extend(int[][] square) {
      int n = square.length;
      // System.out.println("extended");
      int magicVal = n * (n*n + 1) / 2;
      boolean extended = false;
      int[][] result = new int[square.length][square.length];
      for(int i =0; i < result.length; i++) {
        for(int j=0; j < result.length; j++) {
          if(square[i][j] == 0 && !extended) {
            result[i][j] = 1;
            extended = true;
          }else{
            result[i][j] = square[i][j];
          }
        }
      }
      if(extended){
        return result;
      }else{
        return null;
      }
    }


    /**
     * Increments the last available cell by one unless if permanent
     * @param  square The matrix being altered
     * @return A matrix with one added to the last filled cell (with
     * exceptions of permanent) if the sum of the vertical and horizontal
     * is greater than magic value then it returns null
     */
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
          // System.out.println("Square[2][1]: " + square[2][1] + " I: " + i + " J: " + j);
          if(horiz > magicVal || vert > magicVal || square[i][j] == magicVal - (n-1)) {
              return null;
          }
          if(!perm[i][j] && ((j == n-1 && i == n-1) || isZero(square, i, j)) && !nexted) {
              if(horiz > magicVal || vert > magicVal || square[i][j] == magicVal - (n-1)) {
                  return null;
              } else {
                  // System.out.println("touched");
                  // System.out.println("I is: " + i + " and J is:  " + j);
                  result[i][j] = square[i][j] + 1;

                  nexted = true;
                  // break;
              }
          } else {
            horiz += square[i][j];
            // System.out.println("Horizontal: " + horiz);
            vert += square[j][i];
            // System.out.println("Vertical: " + vert);
            if(horiz > magicVal || vert > magicVal || square[i][j] == magicVal) {
                return null;
            }

            result[i][j] = square[i][j];
          }
          // System.out.println("I is: " + i + " and J is:  " + j);
        }
      }
      return result;
    }

    /**
     * A helper method to next. It checks for the first zero. The main
     * reason for this is to check permanent values
     * @param  square the matrix being checked for a extra matrix
     * @param  x start row index
     * @param  y start column index
     * @return returns true if there is a zero value next to it
     * especially if there is a permanent in between
     */
    private static boolean isZero(int[][] square, int x, int y){
      int n = square.length;
      int i = x;
      int j =y;
      boolean ok = true;
      while(i < n && j < n) {
        if(j < n-1 && perm[i][j+1]){
          j++;
          continue;

        }else if(j == n-1 && i < n-1 && perm[i+1][0]){
          i++;
          j = 0;
          continue;
        }else if(j < n-1){
          return square[i][j+1] == 0;
        }else if(j == n-1 && i < n-1){
          return square[i+1][0] == 0;
        }
      }

      return false;
    }

    /**
     * Tests the fullSolutions method and prints out put
     */
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
          },
          {
            {1, 2},
            {1, 4}
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
          },
          {
            {1, 15, 14, 4},
            {8, 0, 11, 5},
            {9, 7, 0, 12},
            {16, 2, 3, 13}
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
          },
          {
            {11, 0, 5, 0, 0},
            {4, 0, 0, 2, 0},
            {0, 3, 0, 21, 0},
            {0, 0, 0, 0, 12},
            {6, 1, 19, 0, 0}
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

        int[][] ex = new int[][]{
          {11, 0, 5, 0, 0},
          {4, 0, 0, 2, 0},
          {0, 3, 0, 21, 0},
          {0, 0, 0, 0, 12},
          {6, 1, 19, 0, 0}
        };
        printSquare(ex);
        while(ex != null){
          ex = extend(ex);
          printSquare(ex);
        }

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
          {2, 1, 1},
          {1, 14, 0}
        },
        {
          {1, 4, 5},
          {2, 8, 5},
          {2, 4, 5}
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
          {1, 1, 1},
          {0, 0, 0}
        },
        {
          {1, 1, 1},
          {1, 1, 1},
          {1, 0, 0}
        },
        {
          {11, 1, 5, 1, 1},
          {4, 0, 0, 2, 0},
          {0, 3, 0, 21, 0},
          {0, 0, 0, 0, 12},
          {6, 1, 19, 0, 0}
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


      System.err.println("These can be nexted and has a Permanent:");
      for (int[][] test : next) {
          perm = new boolean[test.length][test.length];
          perm[0][0] = true;
          perm[0][2] = true;
          System.err.println("Nexted ");
          printSquare(test);
          System.err.println(" to ");
          printSquare(next(test));
      }
      System.out.println();

      resetPerm();
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
      perm = null;
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
