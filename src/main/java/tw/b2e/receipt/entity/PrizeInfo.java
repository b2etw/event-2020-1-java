package tw.b2e.receipt.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PrizeInfo {
	
	private List<String> numbers = new ArrayList<String>();
	private String memo;
	
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
}
