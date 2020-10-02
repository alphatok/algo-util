package com.alphatok.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertArrayEquals;

public class StringUtilTest {


    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void splitAsIntArrayEmpty() {
        thrown.expect(NumberFormatException.class);
        assertArrayEquals(new int[]{},  StringUtil.splitAsIntArray(" "));
    }

    @Test(expected = NullPointerException.class)
    public void splitAsIntArrayNull() {
        assertArrayEquals(new int[]{},com.alphatok.util.StringUtil.splitAsIntArray(null));
    }

    @Test
    public void splitAsIntArray() {
        assertArrayEquals("1", new int[]{1}, StringUtil.splitAsIntArray("1"));
        assertArrayEquals("1,2", new int[]{1,2}, StringUtil.splitAsIntArray("1,2"));
        assertArrayEquals("1, 2", new int[]{1,2}, StringUtil.splitAsIntArray("1, 2"));
        assertArrayEquals(new int[]{1,2}, StringUtil.splitAsIntArray(" 1, 2 "));
        assertArrayEquals(new int[]{1,2}, StringUtil.splitAsIntArray(" 1,  2 "));
        assertArrayEquals(new int[]{-1,2}, StringUtil.splitAsIntArray(" -1,  2 "));
    }
}