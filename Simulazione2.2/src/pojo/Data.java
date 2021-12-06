package pojo;

import java.io.Serializable;

public class Data implements Serializable {

	private static final long serialVersionUID = 1L;

	private int x;
	private int y;
	
	public Data(int x, int y) {
		super();
		this.x = x;
		this.y=y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
