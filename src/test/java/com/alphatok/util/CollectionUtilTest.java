package com.alphatok.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class CollectionUtilTest {

    @Test
    public void testIsAsc() {
        assert CollectionUtil.isAsc(null);
        assert( CollectionUtil.isAsc(new int[]{}));
        assert( CollectionUtil.isAsc(new int[]{1}));
        assert( CollectionUtil.isAsc(new int[]{1,2,3}));
        assert( !CollectionUtil.isAsc(new int[]{2,1}));
        assert( !CollectionUtil.isAsc(new int[]{1,2,3,0}));
        assert( CollectionUtil.isAsc(new int[]{-1, 1}));
        assert( !CollectionUtil.isAsc(new int[]{1, -1}));
    }


    @Test
    public void testPrintIntArray() {

        String nullResult = PrintInterceptor.print(() -> {
            CollectionUtil.printIntArray(null);
        });

        assert ("null\r\n".equals(nullResult));

        String emptyResult = PrintInterceptor.print(() -> {
            CollectionUtil.printIntArray(new int[]{});
        });

        assert ("\r\n".equals(emptyResult));

        String oneResult = PrintInterceptor.print(() -> {
            CollectionUtil.printIntArray(new int[]{1});
        });

        assert ("1\r\n".equals(oneResult));


        String oneTwoResult = PrintInterceptor.print(() -> {
            CollectionUtil.printIntArray(new int[]{1,2});
        });

        assert ("1,2\r\n".equals(oneTwoResult));
    }


    @Test
    public void mergeAsc() {
        assertArrayEquals(new int[]{}, CollectionUtil.mergeAsc(new int[]{}, new int[]{}));
        assertArrayEquals(new int[]{1}, CollectionUtil.mergeAsc(new int[]{1}, new int[]{}));
        assertArrayEquals(new int[]{2}, CollectionUtil.mergeAsc(new int[]{}, new int[]{2}));
        assertArrayEquals(new int[]{1,2}, CollectionUtil.mergeAsc(new int[]{1}, new int[]{2}));
        assertArrayEquals(new int[]{2,2,3,5}, CollectionUtil.mergeAsc(new int[]{2,3,5}, new int[]{2}));
        assertArrayEquals(new int[]{1,2,3,3,4,4,5,6}, CollectionUtil.mergeAsc(new int[]{1,2,3,4,5,6}, new int[]{3,4}));
        assertArrayEquals(new int[]{1,2,3,4,5,6}, CollectionUtil.mergeAsc(new int[]{}, new int[]{1,2,3,4,5,6}));
        assertArrayEquals(new int[]{-10, 1,2,3,4,5,6}, CollectionUtil.mergeAsc(new int[]{-10}, new int[]{1,2,3,4,5,6}));
        assertArrayEquals(new int[]{1,2,3,4,5,6, 122}, CollectionUtil.mergeAsc(new int[]{1,2,3,4,5,6}, new int[]{122}));
    }

    @Test
    public void mergeDesc() {
        assertArrayEquals(new int[]{}, CollectionUtil.mergeDesc(new int[]{}, new int[]{}));
        assertArrayEquals(new int[]{1}, CollectionUtil.mergeDesc(new int[]{1}, new int[]{}));
        assertArrayEquals(new int[]{2}, CollectionUtil.mergeDesc(new int[]{}, new int[]{2}));
        assertArrayEquals(new int[]{2,1}, CollectionUtil.mergeDesc(new int[]{1}, new int[]{2}));
        assertArrayEquals(new int[]{5,3,2,2}, CollectionUtil.mergeDesc(new int[]{5,3,2}, new int[]{2}));
        assertArrayEquals(new int[]{6,5,4,4,3,3,2,1}, CollectionUtil.mergeDesc(new int[]{6,5,4,3,2,1}, new int[]{4,3}));
        assertArrayEquals(new int[]{6,5,4,3,2,1}, CollectionUtil.mergeDesc(new int[]{}, new int[]{6,5,4,3,2,1}));
        assertArrayEquals(new int[]{6,5,4,3,2,1, -10}, CollectionUtil.mergeDesc(new int[]{-10}, new int[]{6,5,4,3,2,1}));
        assertArrayEquals(new int[]{122, 6,5,4,3,2,1}, CollectionUtil.mergeDesc(new int[]{6,5,4,3,2,1}, new int[]{122}));
    }

    @Test
    public void join() {
        assertEquals("", CollectionUtil.join(null));
        assertEquals("", CollectionUtil.join(new int[]{}));
        assertEquals("1", CollectionUtil.join(new int[]{1}));
        assertEquals("1,2,3", CollectionUtil.join(new int[]{1,2,3}));
    }

    @Test
    public void swap() {
        int[] arr = {1, 2};
        CollectionUtil.swap(arr, 0, 1);
        assertArrayEquals(new int[]{2,1}, arr);
    }

    @Test
    public void reverse() {
        assertArrayEquals(new int[]{}, CollectionUtil.reverse(new int[]{}));
        assertArrayEquals(new int[]{1}, CollectionUtil.reverse(new int[]{1}));
        assertArrayEquals(new int[]{2,1}, CollectionUtil.reverse(new int[]{1,2}));
        assertArrayEquals(new int[]{2,1,3}, CollectionUtil.reverse(new int[]{3,1, 2}));
        assertArrayEquals(new int[]{2,1,3,5}, CollectionUtil.reverse(new int[]{5, 3,1, 2}));
    }

    @Test
    public void reverseSection() {
        assertArrayEquals(new int[]{1}, CollectionUtil.reverse(new int[]{1}, 0, 0));
        assertArrayEquals(new int[]{1,2}, CollectionUtil.reverse(new int[]{1,2}, 1, 1));
        assertArrayEquals(new int[]{1,2}, CollectionUtil.reverse(new int[]{1,2}, 0, 0));
        assertArrayEquals(new int[]{2,1}, CollectionUtil.reverse(new int[]{1,2}, 0, 1));
        assertArrayEquals(new int[]{1,2,3}, CollectionUtil.reverse(new int[]{1,2,3}, 1, 1));
        assertArrayEquals(new int[]{2,1,3}, CollectionUtil.reverse(new int[]{1,2,3}, 0, 1));
        assertArrayEquals(new int[]{3,2,1}, CollectionUtil.reverse(new int[]{1,2,3}, 0, 2));
    }

    @Test
    public void mergeAscInplaceRecursive() {
        assertArrayEquals(new int[]{1}, CollectionUtil.mergeAscInplaceRecursive(new int[]{1}, 0, 0, 0));
        assertArrayEquals(new int[]{1,2}, CollectionUtil.mergeAscInplaceRecursive(new int[]{1,2}, 0, 1, 1));
        assertArrayEquals(new int[]{1,2}, CollectionUtil.mergeAscInplaceRecursive(new int[]{2,1}, 0, 1, 1));
        assertArrayEquals(new int[]{1,2,3}, CollectionUtil.mergeAscInplaceRecursive(new int[]{1,2,3}, 0, 1, 2));
        assertArrayEquals(new int[]{1,2,3}, CollectionUtil.mergeAscInplaceRecursive(new int[]{2,3,1}, 0, 2, 2));
        assertArrayEquals(new int[]{1,2,3}, CollectionUtil.mergeAscInplaceRecursive(new int[]{2,1,3}, 0, 1, 2));
        assertArrayEquals(new int[]{1,1,2,2,3,3}, CollectionUtil.mergeAscInplaceRecursive(new int[]{1,2,3,1,2,3}, 0, 3, 5));
        assertArrayEquals(new int[]{1,2,3, 5,5,6,6,7,7,  1,2,3}, CollectionUtil.mergeAscInplaceRecursive(
                new int[]{1,2,3, 5,6,7,5,6,7, 1,2,3}, 3, 6, 8));

        int testCaseCount = 1000;
        for (int count = 0; count < testCaseCount; count++) {
            int arrayLen = ThreadLocalRandom.current().nextInt(2, 2000);
            int midx = ThreadLocalRandom.current().nextInt(0, arrayLen - 1);
            int[] arr = new int[arrayLen];
            int index = 0;
            int preVal = 0;
            for (; index < midx; index++) {
                arr[index] = (int) (Math.random()*10) + preVal;
                preVal = arr[index];
            }

            preVal = 0;
            for (; index < arrayLen; index++) {
                arr[index] = (int) (Math.random()*10) + preVal;
                preVal = arr[index];
            }

            int[] expected = arr.clone();
            Arrays.sort(expected);
            String inArr = CollectionUtil.join(arr);
//            System.out.println(inArr + ":: " + midx + ", " + arrayLen);
            int[] actuals = CollectionUtil.mergeAscInplaceRecursive(arr, 0, midx, arrayLen -1);
            String outArr = CollectionUtil.join(actuals);
            assertArrayEquals("in: " + inArr + "\r\nout:\r\n" + outArr, expected, actuals);
        }
    }

    @Test
    public void shiftLeftInPlace() {
        {
            int[] arr = {1};
            assertArrayEquals(CollectionUtil.join(arr), new int[]{1}, CollectionUtil.shiftLeftInPlace(arr, 0));
        }

        {
            int[] arr = {1};
            assertArrayEquals(CollectionUtil.join(arr), new int[]{1}, CollectionUtil.shiftLeftInPlace(arr, 1));
        }

        {
            int[] arr = {1,2,3,4};
            assertArrayEquals(CollectionUtil.join(arr), new int[]{2,3,4,1}, CollectionUtil.shiftLeftInPlace(arr, 1));
        }

        {
            int[] arr = {1,2,3,4};
            assertArrayEquals(CollectionUtil.join(arr), new int[]{3,4,1,2}, CollectionUtil.shiftLeftInPlace(arr, 2));
        }

        {
            int[] arr = {1,2,3,4};
            assertArrayEquals(CollectionUtil.join(arr), new int[]{2,3,4,1}, CollectionUtil.shiftLeftInPlace(arr, 5));
        }


        int testCaseCount = 1000;
        for (int count = 0; count < testCaseCount; count++) {
            int arrayLen = ThreadLocalRandom.current().nextInt(2, 20);
            int restartIndex = ThreadLocalRandom.current().nextInt(1, arrayLen);
            int[] arr = new int[arrayLen];
            int index = 0;
            int preVal = 0;
            for (; index < restartIndex; index++) {
                arr[index] = (int) (Math.random()*10) + preVal;
                preVal = arr[index];
            }

            preVal = 0;
            for (; index < arrayLen; index++) {
                arr[index] = (int) (Math.random()*10) + preVal;
                preVal = arr[index];
            }

            int[] input = new int[arrayLen];
            int section2Length = arrayLen - restartIndex;
            System.arraycopy(arr, 0, input, restartIndex, section2Length);
            System.arraycopy(arr, section2Length, input, 0, restartIndex);
            assertArrayEquals(CollectionUtil.join(input) +" step:" + restartIndex, arr, CollectionUtil.shiftLeftInPlace(input, restartIndex));
        }
    }

    @Test
    public void shiftLeftInPlaceRange() {
        {
            int[] arr = {1,2,3,4};
            assertArrayEquals(CollectionUtil.join(arr), new int[]{2,3,4,1}, CollectionUtil.shiftLeftInPlace(arr, 0, arr.length - 1, 1));
        }

        {
            int[] arr = {1,2, 3,4,5,6, 7,8,9};
            assertArrayEquals(CollectionUtil.join(arr), new int[]{1,2, 3,4,5,6, 7,8,9}, CollectionUtil.shiftLeftInPlace(arr, 2, 5, 0));
        }

        {
            int[] arr = {1,2, 3,4,5,6, 7,8,9};
            int[] actuals = CollectionUtil.shiftLeftInPlace(arr, 2, 5, 1);
            assertArrayEquals(CollectionUtil.join(arr) + "\r\n" + CollectionUtil.join(actuals), new int[]{1,2, 4,5,6,3, 7,8,9}, actuals);
        }


        {
            int[] arr = {1,2, 3,4,5,6, 7,8,9};
            assertArrayEquals(CollectionUtil.join(arr), new int[]{1,2, 5,6,3,4, 7,8,9}, CollectionUtil.shiftLeftInPlace(arr, 2, 5, 2));
        }

        {
            int[] arr = {1,2, 3,4,5,6, 7,8,9};
            assertArrayEquals(CollectionUtil.join(arr), new int[]{1,2, 5,6,3,4, 7,8,9}, CollectionUtil.shiftLeftInPlace(arr, 2, 5, 6));
        }
    }
}