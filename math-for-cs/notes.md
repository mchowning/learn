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
¬ | not
⋀ | and
⋁ | or

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

Example: 

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
∀ n ε N P(n) => P(n+1) is true, then  
∀ n ε N => P(n) is true  

Example:

> **Proposition/P(n):** 1 + 2 + .. + n = (n(n+1)) / 2  
> **Base case:** P(0) is 0 == 0, which is true  
> **Inductive step:** for n >= 0, show P(n) => P(n+1) is true  
> * can only be false if P(n) is true and P(n+1) is false, so we assume P(n) is true  
>
> **Need to Show:** 1 + 2 + ... + n + (n+1) =  (n+1)(n+2)) / 2  
>
> * (n(n+1))/2 + n + 1  
> * (n^2 + n + 2n + 2) / 2  
> * ((n+1)(n+2)) / 2  
>
> QED

It is not uncommon for induction to just confirm the answer you already have (as above) as opposed to providing the answer for you.

Frequently if you are having trouble proving something by induction, you can make your proof easier by (counterintuitively) making your inductive hypothesis stronger.  This gives you more tools to use in your proof.

Proofs should start with something that is obviously true and derive what you are seeking to prove.  If you do the opposite, your proof will be extremely confusing and very prone to error.

Example of bogus proof:

> 0 ?= 1  
> 0 + 1 ?= 1 + 0  
> 1 ?= 1  
>
> QED???

One good approach is to look for an invariant, a property that is present in the beginning and is preserved through each step, but is not present in the target state.

## 4.Strong Induction

**Strong Induction axiom:** Let P(n) be any predicate.  If P(0) is true and ∀n (P(0) ⋀ P(1) ⋀ ... ⋀ P(n) => P(n+1) is true, then ∀n P(n) is true. Only difference from regular induction is that you are assuming more by assuming that P(0)...P(n) are all true. Any proof that you can do with strong induction, you can also do with regular induction, it just might be harder.

Example: A game where you split a stack of blocks and get the product of the two resulting halves as your points.

> **Theorem:** All strategies for the n-block game produce the same score  
> **Inductive Hypothesis:** P(n) is the theorem  
> **Base case:** P(1) = 0  
> **Inductive step:** Assume P(1), P(1),...,P(n) to prove P(n+1)
> Your score for splitting an n+1 size stack (where one of the splits is size k) is k(n+1-k) + P(k) + (P(n+1-k)  
> Stuck here, so strengthen inductive hypothesis  
> **Stronger Inductive Hypothesis:** P(n) = (n(n-1))/2 (the sum of the numbers up to it)  
> **_New_ Base Case:**: P(1) is still 0  
> **Inductive step:** Score for splitting n+1 size stack is k(n+1-k) + P(k) + P(n+1-k)  
> kn + k - k^2 + (k(k-1))/2 + ((n+1-k)(n-k))/2  
> ( 2kn + 2k - 2k^2 + k^2 - k + n^2 -kn + n - k - kn + k^2 ) / 2  
> ( 2kn + 2k - 2k^2 + k^2 - k + n^2 -kn + n - k - kn + k^2 ) / 2  
> ( n^2 + n ) / 2 (all the k's cancel out)  
> Note that this is the same as P(n+1) according to the stronger inductive hypothesis  
>
> QED

In this example, it is strong induction that allows us to handle a split of any size.

