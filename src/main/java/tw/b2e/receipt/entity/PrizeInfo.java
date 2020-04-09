package tw.b2e.receipt.entity;

import java.util.ArrayList;
import java.util.List;

public class PrizeInfo {
	
	public PrizeInfo() {
		super();
	}
	
	public PrizeInfo(List<String> numbers) {
		super();
		this.numbers = numbers;
	}
	
	public PrizeInfo(List<String> numbers, String memo) {
		super();
		this.numbers = numbers;
		this.memo = memo;
	}
	
	private List<String> numbers = new ArrayList<String>();
	private String memo;
	public List<String> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<String> numbers) {
		this.numbers = numbers;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}	
}
