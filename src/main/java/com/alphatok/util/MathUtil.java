package com.alphatok.util;

import java.util.ArrayList;
import java.util.List;

public class MathUtil {
	public static int pow(int a, int x) {
		int result = 1;
		for (int i = 0; i < x; i++) {
			result *= a;
		}

		return result;
	}

	public static boolean powBasedOnTwo(int n) {
		return n > 0 && (n & (n - 1)) == 0;
	}

	public static List<Integer> getPrimeFactors(int n) {
		List<Integer> factors = new ArrayList<Integer>();
		for (int i = 2; i <= n; i++) {
			while (n % i == 0) {
				factors.add(i);
				n = n / i;
			}
		}
		if (n > 1) {
			factors.add(n);
		}

		return factors;
	}

	public static boolean isPrimal(int n) {
		int maxCheck = (int) Math.sqrt(n);
		boolean result = true;
		for (int i = 2; i <= maxCheck; i++) {
			if (n % i == 0) {
				result = false;
				break;
			}
		}
		return result;
	}

	/**
	 * 辗转相除法求最大公约数又叫欧几里德算法<br>
	 * 它的依据是公理gcd(n,m) = (m, n%m)
	 */
	public int getGcd(int i, int j) {
		int k;
		while ((k = i % j) != 0) {
			i = j;
			j = k;
		}
		return j;
	}

	public static void main(String[] args) {
		System.out.println(powBasedOnTwo(0));
		System.out.println(powBasedOnTwo(2));
		System.out.println(powBasedOnTwo(3));
		System.out.println(powBasedOnTwo(9));
		System.out.println(powBasedOnTwo(12));

		int n = 232323;
		CollectionUtil.print(getPrimeFactors(n));
	}
}
