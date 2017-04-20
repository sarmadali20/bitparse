package com.pxlim.bitparse;


import android.util.Log;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

public class ParseUtils {

    public static void search(String input, Set<String> dictionary, LinkedHashSet<String> words) {
        for (int i = 0; i < input.length(); i++) {
            // from 0 index to i+1 get a sub string
            String substring = input.substring(0, i + 1);
            // Check if it's a word in in dictionary
            if (dictionary.contains(substring)) {
                // Push the word to stack
                words.add(substring);
                // End of search
                if (i < input.length()) {
                    // recursive search on the left over input
                    search(input.substring(i + 1), dictionary, words);
                }

            }
        }

    }


}
