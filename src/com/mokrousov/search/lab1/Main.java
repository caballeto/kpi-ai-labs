package com.mokrousov.search.lab1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Головний клас. Містить логіку реалізації алгоритму пошуку з ітеративним заглибленням.
 */
public class Main {
    public static final int MAX_DEPTH = 25;
    
    public static final SearchState START = new SearchState(4, new int[]{1, 1, 1, 1, -1, 0, 0, 0});
    public static final SearchState END = new SearchState(3, new int[]{0, 0, 0, -1, 1, 1, 1, 1});
    
    public static List<SearchState> iterativeDeepeningSearch(SearchState start, SearchState end, int maxDepth) {
        int depth = 0;
        
        while (depth < maxDepth) {
            List<SearchState> path = new ArrayList<>(Collections.singletonList(start));
            if (depthLimitedSearch(depth, start, end, path)) {
                return path;
            } else {
                depth++;
            }
        }
        
        return null;
    }
    
    public static boolean depthLimitedSearch(int depth, SearchState state, SearchState end, List<SearchState> searchPath) {
        if (depth == 0 && state.equals(end)) {
            return true;
        } else if (depth > 0) {
            for (SearchState newState : state.expand()) {
                searchPath.add(newState);
                if (depthLimitedSearch(depth - 1, newState, end, searchPath)) {
                    return true;
                }
                searchPath.remove(searchPath.size() - 1);
            }
            return false;
        } else {
            return false;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Running algorithm.");
        System.out.println("Start state: " + START);
        System.out.println("End state: " + END);
        System.out.println("Max depth: " + MAX_DEPTH);
        System.out.println();
        
        List<SearchState> path = iterativeDeepeningSearch(START, END, MAX_DEPTH);
        if (path != null) {
            System.out.println("Successfully found path.");
            for (int i = 0; i < path.size(); i++) {
                System.out.println(i + ": " + path.get(i));
            }
            
            System.out.println();
            System.out.println("Final state matches END state: " + (path.get(path.size() - 1).equals(END)));
        } else {
            System.out.println("Could not find path. Consider increasing max depth.");
        }
    }
}
