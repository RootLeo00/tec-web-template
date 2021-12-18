package pojo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FileServlet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	File file;
	private final static int L1=0;
	private final static int L2=1;
	private final static int L3=2;
	private final static int PRIMA_PARTE=1;
	private final static int SECONDA_PARTE=2;

	public FileServlet(String string) {
		super();
		file=new File(string);
	}

	public File getFile() {
		return file;
	}

	public void setFile(String filename) {
		this.file = new File(filename);
	}

	public int getNumRighe() throws IOException {
		int res=0;
		BufferedReader r=new BufferedReader(new FileReader(file));
		while(r.readLine()!=null) {
			res++;
		}
		r.close();
		return res;
	}

	public List<String> getWords(char l1, char l2, char l3) throws IOException{

		List<String> res=new ArrayList<String>();
		BufferedReader r=new BufferedReader(new FileReader(file));
		String line=null;
		String word=null;
		StringTokenizer token=null;
		while((line=r.readLine())!=null) {
			token=new StringTokenizer(line);
			if(!(word=token.nextToken(" ")).isBlank()||!(word=token.nextToken(" ")).isEmpty()||(word=token.nextToken(" "))!=null){
				boolean check[]=new boolean[3];
				for(int i=0;i<3;i++) {
					check[i]=false;
				}
				if(check(word,l1)==true) {
					check[L1]=true;
				}
				if(check(word,l2)==true) {
					check[L2]=true;
				}
				if(check(word,l3)==true) {
					check[L3]=true;
				}
				if((check[L1]==true&&check[L2]==true)||(check[L1]==true&&check[L3]==true)||(check[L3]==true&&check[L2]==true)
						||(check[L1]==true&&check[L2]==true&&check[L3]==true)) res.add(word);
			}
		}
		r.close();
		return res;
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
	
	
	public List<String> getHalf(int whichHalf) throws IOException{
		List<String> res=new ArrayList<String>();
		BufferedReader r=new BufferedReader(new FileReader(file));
		String line=null;
		int num_rows=this.getNumRighe();
		if(whichHalf==PRIMA_PARTE) {
			for(int i=0;i<num_rows/2;i++) {
				line=r.readLine();
				res.add(line);
			}
		}
		if(whichHalf==SECONDA_PARTE) {
			for(int i=num_rows/2;i<num_rows;i++) {
				line=r.readLine();
				res.add(line);
			}	
		}
		r.close();
		return res;
	}

}
