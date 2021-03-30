solve(Node, StatePath)  :-
  dfs([], Node, StatePath).

% base case when goal state is reached
dfs(SearchPath, Node, [Node | SearchPath])  :-
   goal(Node).

% main case, iterate over available edges and try to reach end node
dfs( SearchPath, Node, Solution)  :-
  s(Node, Next),
  \+ member(Next, SearchPath),
  dfs([Node | SearchPath], Next, Solution).

% END state
goal([rb, r, r, r, r, r, r]).

%  search space graph
s([lb, l, l, l, l, l, l], [rb, l, l, l, l, l, r]).
s([lb, l, l, l, l, l, l], [rb, l, l, l, l, r, l]).
s([lb, l, l, l, l, l, l], [rb, l, l, l, l, r, r]).
s([lb, l, l, l, l, l, l], [rb, l, l, l, r, l, l]).
s([lb, l, l, l, l, l, l], [rb, l, l, l, r, l, r]).
s([lb, l, l, l, l, l, l], [rb, l, l, l, r, r, l]).
s([lb, l, l, l, l, l, l], [rb, l, l, r, l, l, r]).
s([lb, l, l, l, l, l, l], [rb, l, r, l, l, r, l]).
s([lb, l, l, l, l, l, l], [rb, r, l, l, r, l, l]).
s([lb, l, l, l, l, l, r], [rb, l, l, l, l, r, r]).
s([lb, l, l, l, l, l, r], [rb, l, l, l, r, l, r]).
s([lb, l, l, l, l, l, r], [rb, l, l, l, r, r, r]).
s([lb, l, l, l, l, l, r], [rb, l, l, r, l, l, r]).
s([lb, l, l, l, l, r, l], [rb, l, l, l, l, r, r]).
s([lb, l, l, l, l, r, l], [rb, l, l, l, r, r, l]).
s([lb, l, l, l, l, r, l], [rb, l, l, l, r, r, r]).
s([lb, l, l, l, l, r, l], [rb, l, r, l, l, r, l]).
s([lb, l, l, l, r, l, l], [rb, l, l, l, r, l, r]).
s([lb, l, l, l, r, l, l], [rb, l, l, l, r, r, l]).
s([lb, l, l, l, r, l, l], [rb, l, l, l, r, r, r]).
s([lb, l, l, l, r, l, l], [rb, r, l, l, r, l, l]).
s([lb, l, l, l, r, l, r], [rb, l, l, l, r, r, r]).
s([lb, l, l, l, r, l, r], [rb, r, l, r, r, l, r]).
s([lb, l, l, l, r, r, l], [rb, l, l, l, r, r, r]).
s([lb, l, l, l, r, r, l], [rb, r, r, l, r, r, l]).
s([lb, l, l, r, l, l, r], [rb, l, r, r, l, r, r]).
s([lb, l, l, r, l, l, r], [rb, r, l, r, r, l, r]).
s([lb, l, l, r, l, l, r], [rb, r, r, r, l, l, r]).
s([lb, l, r, l, l, r, l], [rb, l, r, r, l, r, r]).
s([lb, l, r, l, l, r, l], [rb, r, r, l, r, r, l]).
s([lb, l, r, l, l, r, l], [rb, r, r, r, l, r, l]).
s([lb, r, l, l, r, l, l], [rb, r, l, r, r, l, r]).
s([lb, r, l, l, r, l, l], [rb, r, r, l, r, r, l]).
s([lb, r, l, l, r, l, l], [rb, r, r, r, r, l, l]).
s([lb, r, r, l, r, r, l], [rb, r, r, r, r, r, l]).
s([lb, r, r, l, r, r, l], [rb, r, r, r, r, r, r]).
s([lb, r, r, r, l, l, l], [rb, r, r, r, l, l, r]).
s([lb, r, r, r, l, l, l], [rb, r, r, r, l, r, l]).
s([lb, r, r, r, l, l, l], [rb, r, r, r, l, r, r]).
s([lb, r, r, r, l, l, l], [rb, r, r, r, r, l, l]).
s([lb, r, r, r, l, l, l], [rb, r, r, r, r, l, r]).
s([lb, r, r, r, l, l, l], [rb, r, r, r, r, r, l]).
s([rb, l, l, l, l, l, r], [lb, l, l, l, l, l, l]).
s([rb, l, l, l, l, r, r], [lb, l, l, l, l, l, l]).
s([rb, l, l, l, l, r, r], [lb, l, l, l, l, l, r]).
s([rb, l, l, l, l, r, r], [lb, l, l, l, l, r, l]).
s([rb, l, l, l, r, l, l], [lb, l, l, l, l, l, l]).
s([rb, l, l, l, r, l, r], [lb, l, l, l, l, l, l]).
s([rb, l, l, l, r, l, r], [lb, l, l, l, l, l, r]).
s([rb, l, l, l, r, l, r], [lb, l, l, l, r, l, l]).
s([rb, l, l, l, r, r, l], [lb, l, l, l, l, l, l]).
s([rb, l, l, l, r, r, l], [lb, l, l, l, l, r, l]).
s([rb, l, l, l, r, r, l], [lb, l, l, l, r, l, l]).
s([rb, l, l, l, r, r, r], [lb, l, l, l, l, l, r]).
s([rb, l, l, l, r, r, r], [lb, l, l, l, l, r, l]).
s([rb, l, l, l, r, r, r], [lb, l, l, l, l, r, r]).
s([rb, l, l, l, r, r, r], [lb, l, l, l, r, l, l]).
s([rb, l, l, l, r, r, r], [lb, l, l, l, r, l, r]).
s([rb, l, l, l, r, r, r], [lb, l, l, l, r, r, l]).
s([rb, l, r, l, l, r, l], [lb, l, l, l, l, l, l]).
s([rb, l, r, l, l, r, l], [lb, l, l, l, l, r, l]).
s([rb, l, r, r, l, r, r], [lb, l, l, l, l, r, r]).
s([rb, l, r, r, l, r, r], [lb, l, l, r, l, l, r]).
s([rb, l, r, r, l, r, r], [lb, l, r, l, l, r, l]).
s([rb, r, l, l, r, l, l], [lb, l, l, l, l, l, l]).
s([rb, r, l, l, r, l, l], [lb, l, l, l, r, l, l]).
s([rb, r, l, r, r, l, r], [lb, l, l, l, r, l, r]).
s([rb, r, l, r, r, l, r], [lb, l, l, r, l, l, r]).
s([rb, r, l, r, r, l, r], [lb, r, l, l, r, l, l]).
s([rb, r, r, l, r, r, l], [lb, l, l, l, r, r, l]).
s([rb, r, r, l, r, r, l], [lb, l, r, l, l, r, l]).
s([rb, r, r, l, r, r, l], [lb, r, l, l, r, l, l]).
s([rb, r, r, r, l, l, r], [lb, l, l, r, l, l, r]).
s([rb, r, r, r, l, l, r], [lb, r, r, r, l, l, l]).
s([rb, r, r, r, l, r, l], [lb, l, r, l, l, r, l]).
s([rb, r, r, r, l, r, l], [lb, r, r, r, l, l, l]).
s([rb, r, r, r, r, l, l], [lb, r, l, l, r, l, l]).
s([rb, r, r, r, r, l, l], [lb, r, r, r, l, l, l]).
s([rb, r, r, r, r, r, l], [lb, r, r, l, r, r, l]).
s([rb, r, r, r, r, r, l], [lb, r, r, r, l, l, l]).
s([rb, r, r, r, r, r, l], [lb, r, r, r, l, r, l]).
s([rb, r, r, r, r, r, l], [lb, r, r, r, r, l, l]).

?- solve([lb, l, l, l, l, l, l], Solution).
