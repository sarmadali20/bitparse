package com.pxlim.bitparse;


import android.util.Log;

public class BitUtils {
    public static int getBitsToChange(int a, int b) {
        String aBitStr = Integer.toBinaryString(a);
        String bBitStr = Integer.toBinaryString(b);
        char[] binaryCharArrayA = aBitStr.toCharArray();
        char[] binaryCharArrayB = bBitStr.toCharArray();
        int lenA = binaryCharArrayA.length;
        int lenB = binaryCharArrayB.length;

        Log.d(BitFragment.class.toString(), "A::" + aBitStr);
        Log.d(BitFragment.class.toString(), "B::" + bBitStr);

        if (lenA < lenB) {
            binaryCharArrayA = pad(binaryCharArrayA, lenB);
        }

        if (lenB < lenA) {
            binaryCharArrayB = pad(binaryCharArrayB, lenA);
        }

        Log.d(BitFragment.class.toString(), "Padded A::" + new String(binaryCharArrayA));
        Log.d(BitFragment.class.toString(), "Padded B::" + new String(binaryCharArrayB));

        int len = Math.max(lenA, lenB);
        int bitsToChange = 0;
        for (int i = 0; i < len; i++) {
            if (binaryCharArrayA[i] != binaryCharArrayB[i]) {
                bitsToChange++;
            }
        }
        return bitsToChange;
    }

    public static char[] pad(char[] chars, int len) {
        char[] padded = new char[len];
        int zeroslen = len - chars.length;
        char[] zerochars = new char[zeroslen];
        for (int i = 0; i < zeroslen; i++) {
            zerochars[i] = '0';
        }

        System.arraycopy(zerochars, 0, padded, 0, zeroslen);
        System.arraycopy(chars, 0, padded, zeroslen, chars.length);
        return padded;
    }
}
