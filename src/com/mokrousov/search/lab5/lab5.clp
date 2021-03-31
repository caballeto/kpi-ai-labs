(deftemplate transition (multislot u) (multislot v))

(deffacts state
    (search-state 0 1 0)
    (state-path "0 1 0" "0 1 0")
    (marked 0 1 0)
    (end 0 0 0)
    (end 1 1 1))

(defrule t1 (search-state 0 1 ?c) => (assert (transition (u 0 1 ?c) (v 1 0 ?c))))
(defrule t2 (search-state 0 0 ?c) => (assert (transition (u 0 0 ?c) (v 1 1 ?c))))
(defrule t3 (search-state 1 1 ?c) => (assert (transition (u 1 1 ?c) (v 0 0 ?c))))
(defrule t4 (search-state 1 0 ?c) => (assert (transition (u 1 0 ?c) (v 0 1 ?c))))
(defrule t5 (search-state 0 ?b 0) => (assert (transition (u 0 ?b 0) (v 1 ?b 1))))
(defrule t6 (search-state 0 ?b 1) => (assert (transition (u 0 ?b 1) (v 1 ?b 0))))
(defrule t7 (search-state 1 ?b 0) => (assert (transition (u 1 ?b 0) (v 0 ?b 1))))
(defrule t8 (search-state 1 ?b 1) => (assert (transition (u 1 ?b 1) (v 0 ?b 0))))
(defrule t9 (search-state ?a 0 0) => (assert (transition (u ?a 0 0) (v ?a 1 1))))
(defrule t10 (search-state ?a 0 1) => (assert (transition (u ?a 0 1) (v ?a 1 0))))
(defrule t11 (search-state ?a 1 0) => (assert (transition (u ?a 1 0) (v ?a 0 1))))
(defrule t12 (search-state ?a 1 1) => (assert (transition (u ?a 1 1) (v ?a 0 0))))

(defrule run-algorithm
    (search-state $?state)
    (not (end $?state))
    (transition (u $?state) (v $?next))
    (not (marked $?next))
=>
    (assert
        (search-state $?next)
        (state-path (implode$ $?state) (implode$ $?next))
        (marked $?next)))

(defrule on-end-state
  (search-state $?state)
  (end $?state)
  (not (stack $?))
=>
  (assert (stack (implode$ $?state))))

(defrule states-path
  (stack ?top $?rest)
  (state-path ?from ?top)
=>
  (if (eq ?from ?top) then
    (printout t (create$ ?top $?rest) crlf)
  else
    (assert (stack ?from ?top $?rest))))
