package com.pxlim.bitparse;


import android.util.Log;

public class BitUtils {
    public static int getBitsToChange(int a, int b) {
        int xor = a ^ b;
        return Integer.bitCount(xor);
    }

}
