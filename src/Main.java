import java.io.*;
import java.util.*;

class Main {
  public static void main(String args[]) {
    try{
      BufferedReader noBordersReader = new BufferedReader(new FileReader("./noBorders.test"));
      BufferedReader bordersReader = new BufferedReader(new FileReader("./borders.test"));
      BufferedReader solvedReader = new BufferedReader(new FileReader("./solved.test"));
      StringBuilder sb = new StringBuilder('\n');

      // Parse inputs and print them to console
      Sudoku noBorders = new Sudoku();
      noBorders.parse(noBordersReader);
      sb.append("noBorders.test:\n\n").append(noBorders).append("\n\n");

      Sudoku borders = new Sudoku();
      borders.parse(bordersReader);
      sb.append("borders.test:\n\n").append(borders).append("\n\n");

      Sudoku solved = new Sudoku();
      solved.parse(solvedReader);
      sb.append("solved.test:\n\n").append(solved).append("\n\n");

      // Solve and print
      // sb.append("SOLVE 1:\n\n");
      // Sudoku.solve(noBorders);
      // Sudoku.printBoard(noBorders.getBoard(), sb);
      sb.append("solved.test using Sudkou.printBoard():\n\n");
      Sudoku.printBoard(solved, sb);

      System.out.println(sb);
    } catch (Exception e) {
      System.err.println("\nFUCKED UP ------> " + e);
    }
  }
}
