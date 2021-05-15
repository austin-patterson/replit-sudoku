import java.io.*;
import java.util.*;

class Main {
  public static void main(String args[]) throws IOException {
    try{
      BufferedReader test1 = new BufferedReader(new FileReader("./test1.sudoku"));
      StringBuilder sb = new StringBuilder();

      Sudoku sudoku = new Sudoku();
      sudoku.parse(test1);

      sb.append(sudoku).append('\n');

      System.out.println(sb);
      Sudoku.printBoard(sudoku.getBlocks());

    } catch (IOException e) {
      System.err.println("FUCKED UP ------> " + e);
    }
  }
}
