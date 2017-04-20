package com.pxlim.bitparse;


import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

public class ParseUtils {

    public static void search(String input, Set<String> dictionary, Stack<String> words, LinkedHashSet<String> results) {
        for (int i = 0; i < input.length(); i++) {
            // from 0 index to i+1 get a sub string
            String substring = input.substring(0, i + 1);
            // Check if it's a word in in dictionary
            if (dictionary.contains(substring)) {
                // Push the word to stack
                words.push(substring);
                // End of search
                if (i == input.length() - 1) {
                    results.addAll(words);
                } else {
                    // recursive search on the left over input
                    search(input.substring(i + 1), dictionary, words, results);
                }

            }
        }
    }

}
