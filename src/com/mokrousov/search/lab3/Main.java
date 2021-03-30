package com.mokrousov.search.lab3;

import java.util.*;
import java.util.function.Function;

public class Main {
  //public static final int MAX_DEPTH = 25;
  
  public static final SearchState START = new SearchState(new int[]{0, 0, 0, 0, 0, 0}, true);
  //public static final SearchState END = new SearchState(new int[]{1, 1, 1, 1, 1, 1}, false);
  
/*  public static List<SearchState> iterativeDeepeningSearch(SearchState start, SearchState end, int maxDepth) {
    int depth = 0;
    
    while (depth < maxDepth) {
      List<SearchState> path = new ArrayList<>(Collections.singletonList(start));
      if (depthLimitedSearch(depth, start, end, path, new HashSet<>(Collections.singletonList(start.fullHash())))) {
        return path;
      } else {
        depth++;
      }
    }
    
    return new ArrayList<>();
  }
  
  public static boolean depthLimitedSearch(int depth, SearchState state, SearchState end, List<SearchState> searchPath, Set<Integer> visited) {
    if (depth == 0 && state.equals(end)) {
      return true;
    } else if (depth > 0) {
      for (SearchState newState : state.expand()) {
        if (visited.contains(newState.fullHash())) continue;
        visited.add(newState.fullHash());
        searchPath.add(newState);
        if (depthLimitedSearch(depth - 1, newState, end, searchPath, visited)) {
          return true;
        }
        searchPath.remove(searchPath.size() - 1);
      }
      return false;
    } else {
      return false;
    }
  }*/
  
  public static SearchState getBestNextState(SearchState state, Function<SearchState, Double> h) {
    List<SearchState> nextStates = state.expand();
    SearchState bestState = null;
    for (SearchState nextState : nextStates) {
      double val = h.apply(nextState);
      if (val > h.apply(state) && (bestState == null || val > h.apply(bestState))) {
        bestState = nextState;
      }
    }
    return bestState;
  }
  
  public static List<SearchState> hillClimbingSearch(SearchState state, Function<SearchState, Double> h) {
    List<SearchState> path = new ArrayList<>();
    while (true) {
      path.add(state);
      SearchState bestState = getBestNextState(state, h);
      if (bestState != null) {
        state = bestState;
      } else {
        return path;
      }
    }
  }
  
  public static void main(String[] args) {
    System.out.println("Variant #22: task 9, method 1");
    Function<SearchState, Double> fn = (state) -> {
      double score = 0;
      for (int i = 0; i < state.cells.length; i++) {
        score += state.cells[i];
      }
      return score;
    };
    
    List<SearchState> path = hillClimbingSearch(START, fn);
    for (SearchState state : path) {
      System.out.println("Evaluation function: " + fn.apply(state));
      System.out.println(state);
    }
  }
}
