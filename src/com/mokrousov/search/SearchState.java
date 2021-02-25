package com.mokrousov.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Клас, що зберігає конкретний стан системи
 * у деякий момент часу.
 */
public class SearchState {
  int emptyCellIndex;
  int[] cells;
  
  public SearchState(int emptyCellIndex, int[] cells) {
    this.emptyCellIndex = emptyCellIndex;
    this.cells = cells;
  }
  
  public List<SearchState> expand() {
    List<SearchState> states = new ArrayList<>();
    
    if (emptyCellIndex < cells.length - 1 && cells[emptyCellIndex + 1] == CellStates.WHITE) {
      var newState = Arrays.copyOf(cells, cells.length);
      newState[emptyCellIndex] = CellStates.WHITE;
      newState[emptyCellIndex + 1] = CellStates.EMPTY;
      states.add(new SearchState(emptyCellIndex + 1, newState));
    }
    
    if (emptyCellIndex < cells.length - 2 && cells[emptyCellIndex + 2] == CellStates.WHITE) {
      var newState = Arrays.copyOf(cells, cells.length);
      newState[emptyCellIndex] = CellStates.WHITE;
      newState[emptyCellIndex + 2] = CellStates.EMPTY;
      states.add(new SearchState(emptyCellIndex + 2, newState));
    }
    
    if (emptyCellIndex > 0 && cells[emptyCellIndex - 1] == CellStates.BLACK) {
      var newState = Arrays.copyOf(cells, cells.length);
      newState[emptyCellIndex] = CellStates.BLACK;
      newState[emptyCellIndex - 1] = CellStates.EMPTY;
      states.add(new SearchState(emptyCellIndex - 1, newState));
    }
  
    if (emptyCellIndex > 1 && cells[emptyCellIndex - 2] == CellStates.BLACK) {
      var newState = Arrays.copyOf(cells, cells.length);
      newState[emptyCellIndex] = CellStates.BLACK;
      newState[emptyCellIndex - 2] = CellStates.EMPTY;
      states.add(new SearchState(emptyCellIndex - 2, newState));
    }
  
    return states;
  }
  
  @Override
  public String toString() {
    return Arrays.toString(cells)
      .replaceAll("0", "W")
      .replaceAll("-1", "E")
      .replaceAll("1", "B");
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
}
