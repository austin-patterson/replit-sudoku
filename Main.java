import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Main {
  public static void main(String args[]) throws IOException {
    try{
      BufferedReader in = new BufferedReader(new FileReader("./test1.sudoku"));
    
      Board board = new Board();
      board.populate(in);

      System.out.println(board);
    } catch (IOException e) {
      System.err.println("FUCKED UP ------> " + e);
    }
  }
}

class Board {
  int[][] board;

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for(int[] row : this.getRows()) {
      for(int num : row) {
        s.append(num);
      }
      s.append('\n');
    }
    return s.toString();
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  public void populate(BufferedReader in) throws IOException {
    try {
      String line = "";
      int row = 0;
      int col = 0;
      // While not at end of .sudoku file
      while ((line = in.readLine()) != null && !line.equals("") && row <= 8) {
        for(char c : line.toCharArray()) {
          // ASCII char to int 1-9
          if (c == '.') board[row][col++] = (int)c - 46;
          else board[row][col++] = c - 48;
        }
        col = 0;
        row++;
      }
    } catch (IOException e) {
      System.err.println("FUCKED UP ====> " + e);
    }
  }

  public Board() {
    this.board = new int[9][9];
  }

  public int[][] getRows() {
    int[][] result = new int[9][9];

    for(int i = 0; i <= 8; i++) {
      result[i] = board[i];
    }

    return result;
  }

  public int[][] getCols() {
    int[][] result = new int[9][9];

    for(int i = 0; i <= 8; i++) {
      result[i] = board[i];
    }

    return result;
  }

  public int[][] getBoard() {
    return this.board;
  }

}