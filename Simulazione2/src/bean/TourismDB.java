package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pojo.Attraction;

public class TourismDB implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final float DISTANCE_MAX=100;
	private static final float NUM_TOURIST_MAX=10;
	private static final float RAYMAX=10;

	private List<Attraction> attractions;
	
	public TourismDB() {
		attractions=new ArrayList<Attraction>();
		setDB();
	}
	
	private void setDB() {
		Attraction a=new Attraction("nettuno", "piazza maggiore", 1,1, 10);
		this.attractions.add(a);
		a=new Attraction("ingegneria", "risorgimento", 0,0, 20);
		this.attractions.add(a);
	}
	
	public List<Attraction> getAll(){
		return this.attractions;
	}
	
	public Attraction findAttractionByName(String name) {
		for(Attraction a: this.getAll()) {
			if(a.getName().equalsIgnoreCase(name)) {
				return a;
			}
		}
		return null;
	}
	
	public Attraction findAttractionByCoord(int x, int y) {
		//future position (x2, y2) = (x1+50, y1)
		int x2=x+50;
		int y2=y;
		
		//calculate distance d=sqrt[(xA-xB)^2+(yA-yB)^2]
		double distance=0.0;
		for(Attraction a: this.getAll()) {
			distance=Math.sqrt((a.getX()-x2)^2+(a.getY()-y2)^2);
			if(distance<DISTANCE_MAX) {
				//calculate how many people are within 100 m
				//under construction 
				if(getNumTouristByRay(a.getX(),a.getY())>NUM_TOURIST_MAX) {
					return a;
				}
			}
		}
		return null;
	}
	
	public int getNumTouristByRay(int x, int y) {
		int res=0;
		//circ: (x-xc)^2 + (y-yc)^2 =r^2
		for(Attraction a : this.getAll()) {
			if(((a.getX()-x)^2+(a.getY()-y)^2) <= RAYMAX) {
				res+=a.getNum_tourists();
			}
		}
		
		return res;

	}



	
}
