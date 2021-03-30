package com.mokrousov.search.lab3;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SearchState {
  int[] cells;
  boolean isBoatLeft;
  
  public SearchState(int[] cells, boolean isBoatLeft) {
    this.cells = cells;
    this.isBoatLeft = isBoatLeft;
  }
  
  public List<SearchState> expand() {
    return generateAllStates().stream().filter(SearchState::isValid).collect(Collectors.toList());
  }
  
  public boolean isValid() {
    for (int knightIdx = 0; knightIdx < 3; knightIdx++) {
      int squireSide = cells[knightIdx + 3];
      if (cells[knightIdx] != squireSide) {
        for (int otherKnightIdx = 0; otherKnightIdx < 3; otherKnightIdx++) {
          if (otherKnightIdx == knightIdx) continue;
          if (cells[otherKnightIdx] == squireSide) {
            return false; // squire is on same side with other knight without owner knight
          }
        }
      }
    }
    return true;
  }
  
  public Set<SearchState> generateAllStates() {
    Set<SearchState> states = new HashSet<>();
    int selectionValue = isBoatLeft ? 0 : 1;
    List<Integer> flipCells = IntStream.range(0, 6).filter(i -> cells[i] == selectionValue).boxed().collect(Collectors.toList());
    flipCells.forEach(i -> states.add(deriveFromIndexes(i)));
    for (int i = 0; i < flipCells.size() - 1; i++) {
      for (int j = i + 1; j < flipCells.size(); j++) {
        states.add(deriveFromIndexes(flipCells.get(i), flipCells.get(j)));
      }
    }
    return states;
  }
  
  public int fullHash() {
    return Objects.hash(Arrays.hashCode(cells), isBoatLeft);
  }
  
  private SearchState deriveFromIndexes(int... indexes) {
    int[] newCells = Arrays.copyOf(cells, cells.length);
    for (int index : indexes)
      flip(newCells, index);
    return new SearchState(newCells, !isBoatLeft);
  }
  
  private static void flip(int[] cells, int index) {
    cells[index] = cells[index] == 0 ? 1 : 0;
  }
  
  @Override
  public int hashCode() {
    return Arrays.hashCode(cells);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("State: \n");
    for (int i = 0; i < 3; i++) {
      sb.append("Knight ")
        .append(i + 1)
        .append(": ")
        .append(cellToString(cells[i]))
        .append(", Squire ")
        .append(i + 1)
        .append(": ")
        .append(cellToString(cells[i + 3]))
        .append("\n");
    }
    sb.append("\n");
    return sb.toString();
  }
  
  private static String cellToString(int value) {
    return value == 0 ? "LEFT" : "RIGHT";
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null) return false;
    if (!(obj instanceof SearchState)) return false;
    SearchState that = (SearchState) obj;
    return Arrays.equals(this.cells, that.cells);
  }
}
