#Word puzzle#

There is a common type of word puzzle where you are given two English words of
the same length, say, "HEAD" and "TAIL". The puzzle is to come up with a
sequence of valid English words, starting with "HEAD", and ending with "TAIL",
such that each word is formed by changing a single letter of the previous word.

Example (altered letters capitalized):
 ```
    HEAD
    heaL
    Teal
    teLl
    tAll
    taIl
```

Here's an algorithm to automatically solve such puzzles for any pair of
four-letter words, discovering as short a solution as possible, then outputting
the chain of words to the console (ie. System.out in Java terms), one per line.

It contains a sample dictionary using only 4-letter words.

##Introduction##
A few years ago (5+) I got this coding test from a company and, at the time, 
solved it with a dynamic programming approach (well, more like brute force)
and recursion.

This solution tries to use a directed graph using the [Levenshtein distance](https://en.wikipedia.org/wiki/Levenshtein_distance)
to find words with distance 1 and the searching with [A*](https://en.wikipedia.org/wiki/A*_search_algorithm).

##Resources##
The implementation of Levenshtein distance is based on [this article](http://julesjacobs.github.io/2015/06/17/disqus-levenshtein-simple-and-fast.html)
by Jules Jacobs describing a Levenshtein automata that works in O(max number of edits)
complexity and using an implementation of the A* algorithm found in (this stackexchange question)[http://codereview.stackexchange.com/questions/38376/a-search-algorithm]
with minor modifications and a couple of bugs fixed.