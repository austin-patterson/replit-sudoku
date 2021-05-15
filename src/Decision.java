class Decision {
  Sudoku sudoku;
  int pos;
  int val;

  public Decision(Sudoku old, int pos, int val) {
    this.sudoku = new Sudoku(old);
    this.pos = pos;
    this.val = val;
  }

  public int getPos() { return this.pos; }

  public Sudoku getSudoku() { return this.sudoku; }

  public boolean isValid() {
    boolean strike = false;
      for (int num : this.sudoku.getBlock(pos)) {
        if (num == this.val) {
          if (!strike) strike = true;
          else return false;
        }
      }
    return true;
  }
}