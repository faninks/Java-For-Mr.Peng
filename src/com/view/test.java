package com.view;

import java.util.Iterator;
import java.util.TreeSet;

public class test {
	public static void main(String[] args) {
		// System.out.println(Integer.valueOf("000x"));
		TreeSet<String> all = new TreeSet<>();
		all.add("222");
		for (Iterator<String> iterator = all.iterator(); iterator.hasNext();) {
			String string = iterator.next();
			string.toCharArray();
		}
		for (Iterator<String> iterator = all.iterator(); iterator.hasNext();) {
			System.out.println(iterator.next().toString());
		}
	}
}
