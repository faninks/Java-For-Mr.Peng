package com.test;

import java.io.*;
import java.util.*;

public class Main1089 {
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		int[] n = new int[12];
		int j = 0, sum = 0;
		for (int i = 0; i < 12; ++i) 
			n[i] = cin.nextInt();
		for (int i = 0; i < 12; ++i) {
			j += (300 - n[i]);
			if (j < 0) {
				j = -1 * (i+1);
				break;
			}
			sum += j / 100;
			j = j % 100;
		}
		if (j >= 0)
			j += sum * 120;
		System.out.println(j);
		cin.close();
	}

	public static void main1() {
		try {
			String str;
			int i = 0, j = 0, sum = 0;
			BufferedReader in = new BufferedReader(new FileReader(new File("save.in")));
			for (int k = 1; k < 13; ++k) {
				str = in.readLine();
				i = new Integer(str.trim()).intValue();
				j += (300 - i);
				if (j < 0) {
					j = -1 * k;
					break;
				}
				sum += j / 100;
				j = j % 100;
			}
			if (j >= 0)
				j += sum * 120;
			BufferedWriter out = new BufferedWriter(new FileWriter(new File("save.out")));
			out.write(String.valueOf(j));
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
