package com.mokrousov.search.lab2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchState {
  private static final int[][] ADJ = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
  
  int[][] cells;
  int emptyX;
  int emptyY;
  int g;
  
  public SearchState(int emptyX, int emptyY, int[][] cells, int g) {
    this.emptyX = emptyX;
    this.emptyY = emptyY;
    this.cells = cells;
    this.g = g;
  }
  
  public int h() {
    return toInt(cells[0][2] != CellStates.ARMCHAIR) + toInt(cells[1][2] != CellStates.WARDROBE);
  }
  
  public int g() {
    return g;
  }
  
  public int f() {
    return g() + h();
  }
  
  public List<SearchState> expand() {
    List<SearchState> states = new ArrayList<>();
    
    for (int[] adj : ADJ) {
      int newEmptyX = emptyX + adj[0];
      int newEmptyY = emptyY + adj[1];
      if (newEmptyX < 0 || newEmptyX >= cells.length || newEmptyY < 0 || newEmptyY >= cells[0].length) continue;
      int[][] newCells = Arrays.stream(this.cells).map(int[]::clone).toArray(int[][]::new);
      swapCells(newCells, emptyX, emptyY, newEmptyX, newEmptyY);
      states.add(new SearchState(newEmptyX, newEmptyY, newCells, g + 1));
    }
    
    return states;
  }
  
  public boolean isEndState() {
    return h() == 0;
  }
  
  private static void swapCells(int[][] cells, int i, int j, int x, int y) {
    int temp = cells[x][y];
    cells[x][y] = cells[i][j];
    cells[i][j] = temp;
  }
  
  private static String replaceEnums(String s) {
    return s.replaceAll("0", "TABLE")
      .replaceAll("1", "CHAIR")
      .replaceAll("2", "ARMCHAIR")
      .replaceAll("3", "WARDROBE")
      .replaceAll("4", "EMPTY");
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int[] cellRow : cells) {
      sb.append(replaceEnums(Arrays.toString(cellRow)));
      sb.append('\n');
    }
    return sb.toString();
  }
  
  @Override
  public int hashCode() {
    return Arrays.hashCode(cells);
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null) return false;
    if (!(obj instanceof SearchState)) return false;
    SearchState other = (SearchState) obj;
    return Arrays.equals(cells, other.cells);
  }
  
  private static int toInt(boolean x) {
    return x ? 1 : 0;
  }
}
