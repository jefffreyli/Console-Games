# Crossword Generator

This project was inspired by this website: https://www.puzzle-maker.com/CW

## How it works 

No OOP. Everything is inside the main method with 12 helper methods. 

1. Place first word across
2. Place all words that can be placed vertically (down)
3. Place all words that can be placed horizontally (across)
4. The helper methods hasSpace check if there’s no words adjacent or in the way of placing the word 
5. Boolean array for each word’s status. True means it’s been placed. False means it hasn’t.
6. Steps 2 - 5 are inside a while loop that stops when the iterations hit 6 and when allWordsPlaced returns true. The reason why I have 6 there is because sometimes it is impossible to put a word down. Looping it 6 times ensures there’s no infinite loop.

## Flaws
* Not all words can be placed sometimes. 
* Because this project was done only with arrays as a challenge from my teacher, the sizes aren't dynamic. Word length is limited, as the board is a 40x40 grid. 

## Files

CrosswordGenerator.java will generate the playable crossword puzzle.
CrosswordGeneratorKey.java will generate the key to the crossword puzzle. 
