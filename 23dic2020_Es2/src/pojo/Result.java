package pojo;

import java.util.ArrayList;
import java.util.List;

public class Result {

	private List<String> result=new ArrayList<String>();

	public List<String> getResult() {
		return result;
	}

	public void setResult(List<String> result) {
		this.result = result;
	}

	public Result(List<String> result) {
		super();
		this.result = result;
	}
	
	
}
