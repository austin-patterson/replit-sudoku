import java.io.*;
import java.util.*;

class Sudoku {
  int[][] board;

  public Sudoku() {
    this.board = new int[9][9];
  }

  public Sudoku(Sudoku old) {
    this.board = Arrays.copyOf(old.getBoard(), 9);
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    int counter = 0;
    for (int[] row : this.getRows()) {
      for (int num : row) {
        if (counter % 9 == 3 || counter % 9 == 6)
          s.append("| ");
        s.append(num).append(' ');
        counter++;
      }
      if (counter % 81 == 27 || counter % 81 == 54) {
        s.append('\n').append("------+-------+------");
      }
      s.append('\n');
    }
    return s.toString();
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  public int[][] getRows() {
    int[][] result = new int[9][9];

    for (int i = 0; i <= 8; i++) {
      result[i] = board[i];
    }

    return result;
  }

  public int[][] getBoard() {
    return this.board;
  }

  public int[][] getCols() {
    int[][] result = new int[9][9];

    for (int i = 0; i < 81; i++) {
      result[i % 9][i / 9] = board[i / 9][i % 9];
    }

    return result;
  }

  public int[][] getBlocks() {
    int[][] result = new int[9][9];

    int row = 0;
    int col = 0;
    int pos = 0;

    while (pos < 81) {
      if (pos % 9 != 0 && pos % 3 == 0) {
        row = (row + 1) % 9;
        col -= 3;
      }
      if (pos == 27 || pos == 54) {
        col = 0;
        row++;
      } else if (pos != 0 && pos % 9 == 0) {
        row -= 2;
      }

      // System.out.printf("pos: %d, row: %d, col: %d %n", pos, row, col);
      result[pos / 9][pos % 9] = board[row][col];

      col++;
      pos++;
    }

    return result;
  }

  // 0 <= pos <= 80
  public int get(int pos) {
    return board[pos / 9][pos % 9];
  }

  public void set(int pos, int val) {
    this.board[pos / 9][pos % 9] = val;
  }

  public int[] getRow(int pos) {
    return this.board[pos / 9];
  }

  public int[] getCol(int pos) {
    int[] result = new int[9];
    for (int n : result) {
      result[n] = board[pos / 9][pos % 9];
    }
    return result;
  }

  public int[] getBlock(int pos) {
    int targetBlock = (pos / 27) + (pos % 9) / 3;
    return this.getBlocks()[targetBlock];
  }

  public static void printBoard(Sudoku s, StringBuilder sb) {
    printBoard(s.getBoard(), sb);
  }

  public static void printBoard(int[][] board) {
    StringBuilder sb = new StringBuilder();
    printBoard(board, sb);
    System.out.println(sb);
  }

  public static void printBoard(int[][] board, StringBuilder sb) {
    for (int[] block : board) {
      sb.append("[ ");
      for (int cell : block) {
        sb.append(cell).append(" ");
      }
      sb.append("]").append('\n');
    }
  }

  public void parse(BufferedReader in) throws IOException {
    try {
      String line = "";
      int row = 0;
      int col = 0;
      // While not at end of .sudoku file
      while ((line = in.readLine()) != null && !line.equals("") && row <= 8) {
        for (char c : line.toCharArray()) {
          // ASCII char to int 1-9
          if (c == '.' || c == '0')
            board[row][col++] = (int) c - 46;
          else if (Character.isDigit(c))
            board[row][col++] = c - 48;
        }
        col = 0;
        row++;
      }
    } catch (IOException e) {
      System.err.println("FUCKED UP ====> " + e);
    }
  }

// TODO
  public static void solve(Sudoku sudoku) {
    Decision next;
    int pos = 0;
    PriorityQueue<Decision> queue = new PriorityQueue<>();
    queue.add(new Decision(sudoku, pos, (sudoku.get(pos) == 0) ? 1 : sudoku.get(pos)));

    do {
      next = queue.poll();
      if (next.isValid()) {
        // add combinations for next
        for (int i = 0; i < 9; i++) {
          queue.add(new Decision(next.getSudoku(), pos, i));
        }
        pos++;
      }
    } while (queue.size() > 0 && pos < 81);
  }
}