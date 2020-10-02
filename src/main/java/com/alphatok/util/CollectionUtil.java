package com.alphatok.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class CollectionUtil {

    public static void printIntArray(int[] arr) {
        if (null == arr) {
            System.out.println("null");
        } else if (arr.length == 0) {
            System.out.println();
        } else {
            StringBuilder buffer = new StringBuilder(arr.length * 4);
            for (int i = 0; i < arr.length; i++) {
                buffer.append(arr[i]).append(",");
            }

            buffer.deleteCharAt(buffer.length() - 1);

            System.out.print(buffer.toString());
            System.out.println();
        }
    }

    public static String join(int[] arr) {
        if (null == arr || arr.length == 0) {
            return "";
        } else {
            StringBuilder buffer = new StringBuilder(arr.length * 4);
            for (int i = 0; i < arr.length; i++) {
                buffer.append(arr[i]).append(",");
            }

            buffer.deleteCharAt(buffer.length() - 1);

            return buffer.toString();
        }
    }

    public static boolean isAsc(int[] arr) {
        if (null == arr || arr.length <= 1) {
            return true;
        } else {
            boolean ascFlag = true;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    ascFlag = false;
                }

                if (!ascFlag) {
                    break;
                }
            }

            return ascFlag;
        }
    }

    /***
     * not null
     */
    public static int[] mergeAsc(int[] left, int[] right) {
        int idxLeft = 0;
        int idxRight = 0;

        int[] result = new int[left.length + right.length];
        int idxRes = 0;
        // mergeAsc common
        while (idxLeft < left.length && idxRight < right.length) {
            if (left[idxLeft] > right[idxRight]) {
                result[idxRes++] = right[idxRight++];
            } else {
                result[idxRes++] = left[idxLeft++];
            }
        }

        // copy remaining right
        for (; idxRight < right.length; ) {
            result[idxRes++] = right[idxRight++];
        }

        // copy remaining left
        for (; idxLeft < left.length; ) {
            result[idxRes++] = left[idxLeft++];
        }

        return result;
    }

    /***
     * not null
     */
    public static int[] mergeDesc(int[] left, int[] right) {
        int idxLeft = 0;
        int idxRight = 0;

        int[] result = new int[left.length + right.length];
        int idxRes = 0;
        // mergeAsc common
        while (idxLeft < left.length && idxRight < right.length) {
            if (left[idxLeft] < right[idxRight]) {
                result[idxRes++] = right[idxRight++];
            } else {
                result[idxRes++] = left[idxLeft++];
            }
        }

        // copy remaining right
        for (; idxRight < right.length; ) {
            result[idxRes++] = right[idxRight++];
        }

        // copy remaining left
        for (; idxLeft < left.length; ) {
            result[idxRes++] = left[idxLeft++];
        }

        return result;
    }

    /**
     * 0...i...  middle....j....end
     *
     * @param arr
     * @param fromInc
     * @param middleIndex
     * @param toInc
     */
    public static int[] mergeAscInplaceRecursive(int[] arr, int fromInc, int middleIndex, int toInc) {
        // end case
        if (fromInc == toInc) {
            return arr;
        } else if (fromInc + 1 == toInc) {
            if (arr[fromInc] > arr[toInc]) {
                swap(arr, fromInc, toInc);
            }

            return arr;
        }


        // find first left[i] > right[0]
        int left1stGtMidIdx = -1;
        for (int i = fromInc; i < middleIndex; i++) {
            if (arr[i] > arr[middleIndex]) {
                left1stGtMidIdx = i;
                break;
            }
        }

        // right greater than left -> return
        if (left1stGtMidIdx == -1) {
            return arr;
        }

        // find first right[j] > left[i]
        int right1stGtMaxIdx = middleIndex;
        for (int i = middleIndex; i < toInc; i++) {
            if (arr[i] < arr[left1stGtMidIdx]) {
                right1stGtMaxIdx = i;
            } else {
                break;
            }
        }

        // swap range[i, middle) <--> range[middle, j)
        shiftLeftInPlace(arr, left1stGtMidIdx, right1stGtMaxIdx, middleIndex - left1stGtMidIdx);

        // reach the end
        if (right1stGtMaxIdx == toInc) {
            return arr;
        }

        // do again calc fromInc, middleIndex until fromInc = toInc
        int newFromInc = (left1stGtMidIdx) + (right1stGtMaxIdx - middleIndex + 1);
        return mergeAscInplaceRecursive(arr, newFromInc, right1stGtMaxIdx + 1, toInc);
    }

    public static int[] shiftLeftInPlace(int[] arr, int fromInc, int toInc, int step) {

        if (step == 0 || fromInc == toInc) {
            return arr;
        }

        int actualStep = step % (toInc - fromInc + 1);

        reverse(arr, fromInc, fromInc + actualStep - 1);
        reverse(arr, fromInc + actualStep, toInc);
        reverse(arr, fromInc, toInc);

        return arr;
    }

    public static void swap(int[] arr, int j, int i) {
        int tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }

    public static int[] reverse(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }

        reverse(arr, 0, arr.length - 1);

        return arr;
    }

    public static int[] reverse(int[] arr, int fromInc, int toInc) {
        if (fromInc == toInc) {
            return arr;
        }

        int frontIndex = fromInc, backwardIndex = toInc;
        while (frontIndex < backwardIndex) {
            swap(arr, frontIndex, backwardIndex);
            frontIndex++;
            backwardIndex--;
        }

        return arr;
    }

    /**
     * to left (if right n-step)
     *
     * @param arr
     * @param step
     * @return
     */
    public static int[] shiftLeftInPlace(int[] arr, int step) {
        assert step >= 0 && arr.length > 0;
        int actualStep = step % arr.length;

        if (0 == actualStep) {
            return arr;
        }

        shiftLeftInPlace(arr, 0, arr.length - 1, step);

//        reverse(arr, 0, actualStep - 1);
//        reverse(arr, actualStep, arr.length -1);
//        reverse(arr);

        return arr;
    }

    public static <E> void print(Collection<E> collections) {
        StringBuilder builder = new StringBuilder();
        for (Iterator<E> iterator = collections.iterator(); iterator.hasNext(); ) {
            E e = iterator.next();
            if (e == null) {
                builder.append(",null");
            } else {
                builder.append(",").append(e.toString());
            }
        }

        System.out.println(builder.substring(1, builder.length()));
    }

    public static <E> void print(E[] objs) {
        StringBuilder builder = new StringBuilder();
        for (E e : objs) {
            if (e == null) {
                builder.append(",null");
            } else {
                builder.append(",").append(e.toString());
            }
        }
        System.out.println(builder.substring(1, builder.length()));
    }

    public static void print(int[] objs) {
        StringBuilder builder = new StringBuilder();
        for (int e : objs) {
            builder.append(",").append(e);
        }
        System.out.println(builder.substring(1, builder.length()));
    }

    public static void main(String[] args) {
        print(Arrays.asList(1, 34, 4, 5, 5, 2));
    }
}
