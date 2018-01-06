package HOJ_wCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author White3
 * @Problem Description 把一个偶数拆成两个不同素数的和，有几种拆法呢？
 * @Input 输入包含一些正的偶数，其值不会超过10000，个数不会超过500，若遇0，则结束。
 * @Output 对应每个偶数，输出其拆成不同素数的个数，每个结果占一行。
 */
public class Main2098 {
	public static List<String> combinationStr(String str){
	    List<String> result = new ArrayList<String>();
	    List<Character> temp = new ArrayList<Character>();
	    char ch[] = str.toCharArray();
	    for(int i=1; i<ch.length; i++)
	        combinationStrDao(temp, result, ch, 0, i);
	    return result;
	}
	
	public static void combinationStrDao(List<Character> temp, List<String> result, char[] ch, int pos, int num) {
        if(num==0) { result.add(temp.toString()); return;}
        if(pos==ch.length) return;
        temp.add(ch[pos]);
        combinationStrDao(temp, result, ch, pos+1, num-1);
        temp.remove(temp.size()-1);
        combinationStrDao(temp, result, ch, pos+1, num);
	}
	
	public static List<Integer> getPrimeList(int num){
		List<Integer> ns = new ArrayList<Integer>();
		for(int i=2; i<5; i++) {
			;
		}
		return ns;
	}
	
	public static void main(String[] args) {
 		List<String> s = combinationStr("abc");
 		System.out.println(s.toString());
		Scanner cin = new Scanner(System.in);
		while(cin.hasNext()) {
			int num = cin.nextInt();
			if(num==0) break;
			/**
			 * 
			 * 1. 判断是否为偶数
			 * 2. 16 : 2,3,5,7,11,13
			 * 1 16 - 2 = 14 - 3 = 11
			 *         2  14 - 5 = 9 - 7 = 2 x
			 *            14 - 7 = 7 x ...
			 * 16   ?     运算
			 * num, time, temp;
			 * List pal = getPrimeList();
			 * List pal2 = null;
			 * while(pal.empty()){ 
			 *     temp = num - pal.remove(0)
			 *     pal2 = new ArrayList(pal);
			 *     for(int j=temp; pal.empty(); j=num){
			 *         if(pal.indexOf(j)) time++;
			 *         if(j <= 2) continue;
			 *         while(true){}
			 *     }
			 * }
			 */
		}
		cin.close();
	}
}
