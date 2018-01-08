package HOJ_wCode;

import java.util.*;

import com.model.Staff;

public class test {
	
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		System.out.println(cin);
		cin.close();
		String ch = "111                 111  111 ";
		String[] str = ch.trim().split("[ ]{1,}");
		System.out.println(str.length);
		TreeSet<Staff> ss = new TreeSet<>();
		Staff ss1[] = new Staff[3];
		ss1[0] = new Staff();
		ss1[0].setStaffNum("0001");
		ss1[1] = new Staff();
		ss1[1].setStaffNum("0002");
		ss1[2] = new Staff();
		ss1[2].setStaffNum("0001");
		ss.add(ss1[0]);
		ss.add(ss1[1]);
		ss.add(ss1[2]);
		for (Iterator<Staff> iterator = ss.iterator(); iterator.hasNext();) {
			Staff string = iterator.next();
			System.out.println(string.toString());
		}
		// m.end(1); //返回3 返回第一组匹配到的子字符串的最后一个字符在字符串中的索引位置. 
		// m.group(1); //返回aaa,返回第一组匹配到的子字符串 
	}
}
