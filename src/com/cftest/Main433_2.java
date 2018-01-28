package com.cftest;

import java.math.*;
import java.util.*;

public class Main433_2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(), flag = 0, win = 0;
		BigInteger k = in.nextBigInteger();
		LinkedList<Integer> a = new LinkedList<>();
		win = in.nextInt();
		for(int i=1; i<n; ++i)
			a.add(in.nextInt());
		while(flag==k.intValue()) {
			if(win == a.getFirst()) { 
				flag++;
				a.add(a.remove());
			}else {
				flag = 0;
				a.add(win);
				win = a.remove();
			}
		}
		System.out.println(win);
		in.close();
	}

}
