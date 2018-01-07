package HOJ_wCode;

/**
 * 
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
 * 
 * @author White3
 * @Problem Description 
 *
public class Fun2098 <T>{
	public List<T> combinationObj(List<T> obj){
		List<T> temp = new ArrayList<>();
		for(int i=0; i<obj.size(); ++i)
			combinationOjbDao(temp, obj, 0, i);
		return result;
	}

	public void combinationObjDao(List<T> temp, List<T> obj, int pos, int num)
	{
		if(num==0) { System.out.println(temp.toString()); return ;}
		if(pos==obj.size()) return ;
		temp.add(obj.indexOf(pos));
		combinationObjDao(result, temp, obj, pos+1, num-1);
		temp.remove(obj.size()-1);
		combinationObjDao(result, temp, obj, pos+1, num);
	}
	
	public List<T> getList(T obj[]){
		List<T> objL = new ArrayList<T>();
		for(int i=0; i<obj.lenth; ++i)
			objL.add(obj[i]);
		return objL;
	}
	
	public static void main(String args[])
	{
		List<String> ci = new ArrayList<String>();
		Scanner cin = new Scanner(System.in);
		while(cin.hasNext()){
			ci = getList((cin.nextLine()).split(" "));
			ci = combinationObj(ci);
			ci.clear();
		}
		cin.close();
	}
}
*/