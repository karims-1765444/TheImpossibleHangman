package com.example.theimpossiblehangman.Manager;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class EvilHangmanManager extends HangmanManager {
    private Set<String> currentWords = new TreeSet<>();
    public static boolean  CHEAT_CODE = false;  // show # of choices left

    //Pre: accepts a list of nonempty strings of lowercase letters
    //	   takes a length greater than 1, otherwise throws IllegalArgumentException
    //	   takes a max greater than 0 , otherwise throws IllegalArgumentException
    //Post: initializes game using the strings
    public EvilHangmanManager(Collection<String> dictionary, int length, int max) {
        super(length, max);
        for (String str : dictionary) {
            if (str.length() == length)
                currentWords.add(str);
        }
    }

    // returns the set of the words still available, sorted alphabetically
    public Set<String> words() {
        return currentWords;
    }

    // Pre: throws IllegalArgumentException if there is no more available words
    // Post: returns the current pattern of the game, separated by spaces
    public String pattern() {
        if (words().size() == 0)
            throw new IllegalArgumentException();
        return super.pattern();
    }

    // calculates which pattern family is largest, changes pattern and available words accordingly
    private void largestFamily(Map<String, Set<String>> wordMap) {
        int currentMax = 0;
        for (String key : wordMap.keySet()) {
            int nextSize = wordMap.get(key).size();
            if (nextSize > currentMax) {
                currentMax = nextSize;
                currentWords = wordMap.get(key);
                setCurrentPattern(key);
            }
        }
    }

    // Pre: accepts a lowercase letter as a guess
    // 		throws IllegalArgumentException if user has no more guesses
    //	    throws IllegalStateException if guess is repeated and no more available words
    // Post: returns a string describing the number of occurrences of the guess in the word
    public String record(char guess) {
        if (guessesLeft() < 1)
            throw new IllegalArgumentException();
        if (words().size()==0 && guesses().contains(guess))
            throw new IllegalStateException();
        if (guesses().contains(guess))
            return ("\nYou already guessed that");
        if (guessesLeft() == 1) {
            winGame(guess);
        }
        Map<String, Set <String>> wordMap = new TreeMap<>();
        guesses().add(guess);
        for (String word : words()) {
            setCurrentPattern(reveal(word, guess));
            if (!wordMap.containsKey(pattern()))
                wordMap.put(pattern(), new TreeSet<String>());
            wordMap.get(pattern()).add(word);
        }
        largestFamily(wordMap);
        wordMap.clear();
        if (pattern().indexOf(guess) == -1)
            setGuessesLeft(guessesLeft()-1);
        return numOccurences(pattern(), guess);
    }

    public void winGame(char guess){
            String losingWord = "";
            Iterator<String> iter = words().iterator();
            boolean found = false;
            while (iter.hasNext() && !found){
                String word = iter.next();
                if(word.indexOf(guess) == -1){
                    found = true;
                    losingWord = word;
                    words().clear();
                    words().add(losingWord);
                }
            }
    }

    public String displayGameState(){
        String gameState = super.displayGameState();
        if (CHEAT_CODE)
            gameState+=("\nwords   : " + words().size());
        return gameState;
    }
}
