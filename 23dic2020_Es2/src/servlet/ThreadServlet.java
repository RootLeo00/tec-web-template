package servlet;

import java.util.ArrayList;
import java.util.List;
import pojo.Result;

public class ThreadServlet extends Thread{

	private Result res;
	private List<String> words;
	private char l1;
	private char l2;
	private char l3;
	private final static int L1=0;
	private final static int L2=1;
	private final static int L3=2;



	public ThreadServlet(List<String> list, char l1, char l2, char l3, Result res) {
		super();
		this.words=new ArrayList<String>(list);
		this.res=res;
		this.l1=l1;
		this.l2=l2;
		this.l3=l3;
	}

	public void run() {

		if(!this.words.isEmpty()) {
			for(String s: this.words) {
				boolean check[]=new boolean[3];
				for(int i=0;i<3;i++) {
					check[i]=false;
				}
				if(check(s,this.l1)==true) {
					check[L1]=true;
				}
				if(check(s,l2)==true) {
					check[L2]=true;
				}
				if(check(s,l3)==true) {
					check[L3]=true;
				}
				if((check[L1]==true&&check[L2]==true)||(check[L1]==true&&check[L3]==true)||(check[L3]==true&&check[L2]==true)
						||(check[L1]==true&&check[L2]==true&&check[L3]==true)) res.getResult().add(s);
			}
		}
	}
	private boolean check(String word, char c) {
		int i=0;
		while( i != 3){
			if(word.charAt(i)==c) {
				return true;
			}
		}
		return false;
	}
}


