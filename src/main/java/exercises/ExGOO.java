package exercises;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class ExGOO implements Exercise {

  private final int height = 5;
  private final int width = 6;

  private final int[][] table = {
          {0, 0, 1, 2, 2, 1},
          {0, 1, 1, 3, 3, 2},
          {3, 1, 1, 4, 3, 3},
          {3, 3, 2, 4, 2, 2},
          {3, 3, 2, 2, 2, 3}
  };

  @Override
  public void solve() {

    Cell[][] matrix = new Cell[height][width];
    int maxNeighbors = 0;
    int topColor = -1;

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        matrix[i][j] = new Cell(table[i][j], compute(i, j, matrix));
        int neighbors = matrix[i][j].count;
        if (neighbors > maxNeighbors) {
          maxNeighbors = neighbors;
          topColor = matrix[i][j].color;
        }
      }
    }

    System.out.println("Max = " + maxNeighbors + ", Color = " + topColor);
  }

  private int compute(int i, int j, Cell[][] matrix) {
    int count = 1;
    if (j >= 1 && table[i][j] == table[i][j - 1]) {
      count += matrix[i][j - 1].count;
    } else if (i >= 1 && table[i - 1][j] == table[i][j]) {
      Cell up = consecutive(j, matrix[i - 1]);
      count += up.count;
    }
//    if (i >= 1 && j >= 1 && table[i][j] == table[i - 1][j - 1]) {
//      count -= matrix[i - 1][j - 1].count;
//    }
    return count;
  }

  private Cell consecutive(int j, Cell[] row) {
    int k = j + 1;
    while (k < row.length && row[k].color == row[j].color) {
      k++;
    }
    return row[k - 1];
  }

  private static class Cell {

    final int color;
    final int count;

    public Cell(int color, int count) {
      this.color = color;
      this.count = count;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (!(obj instanceof Cell)) {
        return false;
      }
      Cell that = (Cell) obj;
      return new EqualsBuilder()
              .appendSuper(super.equals(obj))
              .append(this.color, that.color)
              .isEquals();
    }
  }
}
