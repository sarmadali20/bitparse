package com.pxlim.bitparse;

import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class})
public class BitUtilsUnitTest {

    @Test
    public void testGetBitsToChange() throws Exception {
        PowerMockito.mockStatic(Log.class);
        assertEquals(5, BitUtils.getBitsToChange(124,1000));
        assertNotEquals(4, BitUtils.getBitsToChange(124,1000));
    }


}