##Table of Contents

1. [Math Symbols](#math-symbols)
1. [Proof](#proof)

## I. Math Symbols [↑](#table-of-contents) <a id="math-symbols"></a>

Symbol | Meaning
-------|--------
∀ | for all
∃ | there exists
ε | in [the set]
N | the set of non-negative natural numbers
=> or -> | implies
Z | integers { 0, 1, -1, -1, etc. }
\# | contradction

## II. Proof [↑](#table-of-contents) <a id="proof"></a>

1. Definitions
2. Notes

### 1. Definitions

The **implication** p => q is true if pi is false or q is true (i.e., if pigs can fly then I am king is a true implication)

A **mathematical proof** is a verification of a proposition by a chain of logical deductions from a set of propositions.

A **predicate** is a proposition whose truth depends on the value of a variable

A **proposition** is a statement that is either true or false

An **axiom** is a proposition that is assumed to be true.  Must be both (1) consistent and (2) complete.

A set of axioms is **consistent** if no proposition can be proven both true and false.

A set of axioms is **complete** if it can be used to prove that every proposition is either true or false.

Kurt Gödel proved that it is not possible that there is any set of axioms that are both consistent and complete.  Means that if you want consistency (which is a must) then there must be true facts that you will not be able to prove are true.

### 2. Proof By Contradiction

To prove that proposition P is true, assume that P is false.  Then use that hypothesis to derive a contradiction.  Result being that P must be true (i.e., can't be false).

Example:

>**Goal:** to prove that √2 is irrational
f Contents

1. [Math Symbols](#math-symbols)
1. [Proof](#proof)

## I. Math Symbols [↑](#table-of-contents) <a id="math-symbols"></a>

Symbol | Meaning
-------|--------
∀ | for all
∃ | there exists
ε | in [the set]
N | the set of non-negative natural numbers
=> or -> | implies
Z | integers { 0, 1, -1, -1, etc. }
\# | contradction

## II. Proof [↑](#table-of-contents) <a id="proof"></a>

1. Definitions
2. Notes

### 1. Definitions

The **implication** p => q is true if pi is false or q is true (i.e., if pigs can fly then I am king is a true implication)

A **mathematical proof** is a verification of a proposition by a chain of logical deductions from a set of propositions.

A **predicate** is a proposition whose truth depends on the value of a variable

A **proposition** is a statement that is either true or false

An **axiom** is a proposition that is assumed to be true.  Must be both (1) consistent and (2) complete.

A set of axioms is **consistent** if no proposition can be proven both true and false.

A set of axioms is **complete** if it can be used to prove that every proposition is either true or false.

Kurt Gödel proved that it is not possible that there is any set of axioms that are both consistent and complete.  Means that if you want consistency (which is a must) then there must be true facts that you will not be able to prove are true.

### 2. Proof By Contradiction

To prove that proposition P is true, assume that P is false.  Then use that hypothesis to derive a contradiction.  Result being that P must be true (i.e., can't be false).

>Goal: to prove that √2 is irrational
>
>Assume: √2 is rational
>
> * √2  = a/b (fraction in lowest terms)  
> * 2 = a^2/b^2  
> * 2b^2 = a^2  
> * 2 | a (a is even because it is double b^2)  
> * 4 | a^2  
> * 2 | b^2  
> * 2 | b  
> * \# a/b is NOT in lowest terms 
> * √2 is irrational  
>
>QED

### 3. Proof By Induction

**Induction axiom:** 
if P(0) is true, and  
∀ n ε N P(n-1) => P(n) is true, then  
∀ n ε N => P(n) is true  

Example:

> **Proposition/P(n):** 1 + 2 + .. + n = (n(n+1)) / 2  
> **Base case:** P(0) is 0 == 0, which is true  
> **Inductive step:** for n >= 0, show P(n-1) => P(n) is true  
> * can only be false if P(n) is true and P(n+1) is false, so we assume P(n) is true  
>
> **Need to Show:** 1 + 2 + ... + n + (n+1) =  (n+1)(n+2)) / 2  
>
> * (n(n+1))/2 + n + 1  
> * (n^2 + n + 2n + 2) / 2  
> * ((n+1)(n+2)) / 2  
>
> QED

