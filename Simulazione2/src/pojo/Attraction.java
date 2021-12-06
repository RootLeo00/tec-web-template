package pojo;

import java.io.Serializable;

public class Attraction implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final int X=0;
	private static final int Y=1;
	
	

	private int coordinate[];//x,y
	private String name;
	private String description;
	private int num_tourists;
	
	public Attraction(String name, String description, int x, int y, int num_tourists) {
		this.coordinate=new int[2];
		this.coordinate[X]=x;
		this.coordinate[Y]=y;
		this.name=name;
		this.description=description;
		this.num_tourists=num_tourists;
	}

	public int[] getCoordinate() {
		return coordinate;
	}
	
	public int getX() {
		return getCoordinate()[X];
	}
	
	public int getY() {
		return getCoordinate()[Y];
	}

	public void setCoordinate(int[] coordinate) {
		this.coordinate = coordinate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNum_tourists() {
		return num_tourists;
	}

	public void setNum_tourists(int num_tourists) {
		this.num_tourists = num_tourists;
	}


	
}
