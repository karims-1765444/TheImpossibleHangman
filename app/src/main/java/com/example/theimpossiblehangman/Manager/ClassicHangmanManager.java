package com.example.theimpossiblehangman.Manager;

public class ClassicHangmanManager extends HangmanManager {

    private static String gameWord;

    //Pre: accepts a list of nonempty strings of lowercase letters
    //	   takes a length greater than 1, otherwise throws IllegalArgumentException
    //	   takes a max greater than 0 , otherwise throws IllegalArgumentException
    //Post: initializes game using the strings
    public ClassicHangmanManager(String gameWord) {
        super(gameWord.length(), 10);
        this.gameWord = gameWord;
    }

    public static String getGameWord(){
        return gameWord;
    }


    // Pre: accepts a lowercase letter as a guess
    // 		throws IllegalArgumentException if user has no more guesses
    //	    throws IllegalStateException if guess is repeated and no more available words
    // Post: returns a string describing the number of occurrences of the guess in the word
    public String record(char guess) {
        if (guessesLeft() < 1) {
            throw new IllegalArgumentException();
        }
        if (guesses().contains(guess)) {
            return ("\nYou already guessed that");
        }
        guesses().add(guess);
        setCurrentPattern(reveal(getGameWord(), guess));
        if (pattern().indexOf(guess) == -1) {
            setGuessesLeft(guessesLeft()-1);
        }
        return numOccurences(pattern(), guess);
    }
}

