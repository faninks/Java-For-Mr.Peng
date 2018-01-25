package com.cftest;

import java.util.*;

/**
 * 
 * @author White3 Doctors have a strange working schedule. The doctor i goes to
 *         work on the si-th day and works every di day. So, he works on days
 *         si, si + di, si + 2di, ....
 */
public class Main443_1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = 2*in.nextInt(), temp = 0, num = 0;
		int[] a = new int[n];
		for(int i=0; i<n; ++i)
			a[i] = in.nextInt();
		for(int i=0; i<n; i+=2) {
			temp = a[i];
			for(int j=1; temp<=num; ++j) {
				temp += j*a[i+1];
			}
			num = temp;
		}
		System.out.println(num);
		in.close();
	}

}
