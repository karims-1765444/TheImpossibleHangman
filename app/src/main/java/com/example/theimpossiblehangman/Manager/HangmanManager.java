package com.example.theimpossiblehangman.Manager;

import java.util.SortedSet;
import java.util.TreeSet;

public class HangmanManager {

    private int guessesLeft;
    private String currentPattern;
    private SortedSet<Character> guessedLetters = new TreeSet<>();

    //Pre: accepts a list of nonempty strings of lowercase letters
    //	   takes a length greater than 1, otherwise throws IllegalArgumentException
    //	   takes a max greater than 0 , otherwise throws IllegalArgumentException
    //Post: initializes game using the strings
    public HangmanManager(int length, int max) {
        if (length < 1 || max < 0) {
            throw new IllegalArgumentException();
        }
        guessesLeft = max;
        setCurrentPattern(dashes(length));
    }

    // returns the number of guesses left for the user
    public int guessesLeft() {
        return guessesLeft;
    }

    public void setGuessesLeft(int newGuessesLeft){
        guessesLeft = newGuessesLeft;
    }

    // Pre: throws IllegalArgumentException if there is no more available words
    // Post: returns the current pattern of the game, separated by spaces
    public String pattern() {
        return currentPattern;
    }

    public String viewPattern(){
        return spaces(pattern());
    }

    public void setCurrentPattern(String newPattern){
        currentPattern = newPattern;
    }

    // returns the set of guessed letters, sorted alphabetically
    public SortedSet<Character> guesses() {
        return guessedLetters;
    }

    // returns a string of n number of dashes, used for initial word pattern
    private String dashes(int n) {
        String result = "";
        for (int index = 0 ;  index < n; index++) {
            result += "-";
        }
        return result;
    }

    // accepts a word and returns it with a space between each letter
    private String spaces(String pattern) {
        String result = "";
        for (int index = 0; index < pattern.length() - 1 ; index ++) {
            result += pattern.charAt(index) + " ";
        }
        result+= pattern.charAt(pattern.length() - 1);
        return result;
    }

    // Pre:	accepts a word and a guess
    // Post: returns updated word with guessed letter if found, otherwise returns original word
    public String reveal (String word, char guess) {
        String result = "";
        for (int index = 0 ; index < word.length(); index++) {
            if (word.charAt(index) == pattern().charAt(index)) {
                result += word.charAt(index);
            } else if (word.charAt(index) == guess) {
                result += guess;
            } else {
                result += "-";
            }
        }
        return result;
    }

    // returns the number of times a letter exists in a word
    public String numOccurences(String word, char guess) {
        int count = 0;
        for (int index  = 0 ; index < word.length(); index++) {
            if (word.charAt(index) == guess) {
                count++;
            }
        }
        if (count == 0) {
            return ("\nSorry, there are no " + guess + "'s");
        } else if (count == 1) {
            return ("\nYes, there is one " + guess);
        } else {
            return ("\nYes, there are " + count + " " + guess + "'s");
        }
    }

    public String displayGameState(){
        String gameState = ("guesses : " + guessesLeft());
        gameState += ("\nguessed : " + guesses() + "\ncurrent pattern: " + viewPattern() + "\nYour guess?" );
        return gameState;
    }
}
