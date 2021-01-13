# Boggle Solver 

Boggle is a word game where you are given a grid filled with letters and you are trying to 
form words using squares adjacent letters. The game can be easily solved programmatically with the aid of 
a dictionary, and indeed there are several such implementations.

The solution presented here provides a solution that combines a Trie data structure combined with Depth First Search.

## The Game of Boggle

The game of boggle is described ad nauseum in multiple websites, so I will just give a brief digest of 
the basic rules:

1. Words must be 3 letters or longer
2. The letters in a word must be adjecent vertically, horizontally, or diagonally. 
3. A letter cannot be used more than once in a single word
4. You cannot revisit the same square twice while forming a word

## Run the UI

To run the Swing UI, simply run the executable "BogglePanel"

To start from the command line using Maven:
```text
mvn exec:java
```