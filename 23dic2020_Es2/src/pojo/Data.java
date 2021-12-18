package pojo;

import java.io.Serializable;
import java.util.Arrays;

public class Data implements Serializable {

	private static final long serialVersionUID = 1L;

	private char l1;
	private char l2;
	private char l3;
	public Data(char l1, char l2, char l3) {
		super();
		this.l1 = l1;
		this.l2 = l2;
		this.l3 = l3;
	}
	public char getL1() {
		return l1;
	}
	public char getL2() {
		return l2;
	}
	public char getL3() {
		return l3;
	}
	
	
}
