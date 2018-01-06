package HOJ_wCode;

import java.util.Scanner;

/**
 * @id 2099
 * @author White3
 * 0<a<10000, 10<b<100
 */
public class Main2099 {
	public static void main(String args[]) {
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext()) {
			String s = "";
			int a = cin.nextInt();
			int b = cin.nextInt();
			if (a==0 && b == 0)
				break;
			/* fast
			for(int i=0; i<100; i++) {
				if((a*100+i)%b==0) {
					if(i<10)
						s = s+'0'+i+' ';
					else
						s = s+i+' ';
				}
			}*/
			int max = (a+1)*100;
			int min = a*100;
			for(int j=b*(min/b); j<max; j+=b) {
				if(j>=min) {
					if(j%100<10) 
						s = s+'0'+(j%100)+' ';
					else
						s = s+(j%100) + ' ';
				}
			}
			s = s.substring(0, s.length()-1);
            System.out.println(s);
		}
		cin.close();
	}
}