package com.mokrousov.search.lab2;

import java.util.*;

public class Main {
  public static List<SearchState> rbfs(SearchState start) {
    SearchResult result = solve(start, 2, Integer.MAX_VALUE, new HashSet<>());
    
    List<SearchState> path = new ArrayList<>();
    while (result != null) {
      path.add(result.currState);
      result = result.nextResult;
    }
    
    return path;
  }
  
  private static SearchResult solve(SearchState state, int nodeF, int fLimit, Set<SearchState> stateSet) {
    if (state.isEndState()) return new SearchResult(true, fLimit, state, null);
    
    List<SearchState> successors = expandNodes(state, stateSet);
    
    if (successors.isEmpty()) return new SearchResult(false, Integer.MAX_VALUE, null, null);
    
    int[] fValues = new int[successors.size()];
    for (int successorIdx = 0; successorIdx < successors.size(); successorIdx++) {
      fValues[successorIdx] = Math.max(successors.get(successorIdx).f(), nodeF);
    }
    
    while (true) {
      int bestIdx = getMinIdx(fValues);
      if (fValues[bestIdx] > fLimit) {
        return new SearchResult(false, fValues[bestIdx], null, null);
      }
      
      int altIdx = getNextMinIdx(fValues, bestIdx);
      SearchResult result = solve(successors.get(bestIdx), fValues[bestIdx], Math.min(fLimit, fValues[altIdx]), stateSet);
      fValues[bestIdx] = result.fValue;
      if (result.isSolution) {
        return new SearchResult(true, result.fValue, state, result);
      }
    }
  }
  
  private static List<SearchState> expandNodes(SearchState state, Set<SearchState> stateSet) {
    List<SearchState> successors = new ArrayList<>();
    stateSet.add(state);
    for (SearchState newState : state.expand()) {
      if (stateSet.contains(newState)) continue;
      successors.add(newState);
    }
    return successors;
  }
  
  private static int getMinIdx(int[] fValues) {
    int minIndex = 0;
  
    for (int i = 0; i < fValues.length; i++) {
      if (fValues[i] < fValues[minIndex]) {
        minIndex = i;
      }
    }
  
    return minIndex;
  }
  
  private static int getNextMinIdx(int[] fValues, int bestIdx) {
    int minIndex = 0;
  
    for (int i = 0; i < fValues.length; i++) {
      if (i != bestIdx && fValues[i] < fValues[minIndex]) {
        minIndex = i;
      }
    }
  
    return minIndex;
  }
  
  public static class SearchResult {
    boolean isSolution;
    int fValue;
    SearchState currState;
    SearchResult nextResult;
    
    SearchResult(boolean isSolution, int fValue, SearchState currState, SearchResult nextResult) {
      this.isSolution = isSolution;
      this.fValue = fValue;
      this.currState = currState;
      this.nextResult = nextResult;
    }
  }
  
  public static void main(String[] args) {
    System.out.println("Variant #22, task 11 with RBFS");
    
    SearchState start = new SearchState(1, 1, new int[][]{
      {
        CellStates.TABLE, CellStates.CHAIR, CellStates.WARDROBE
      },
      {
        CellStates.CHAIR, CellStates.EMPTY, CellStates.ARMCHAIR
      }
    },0);
    
    List<SearchState> path = rbfs(start);
    
    System.out.println("Result path: ");
    for (SearchState state : path) {
      System.out.println(state);
    }
  }
}
