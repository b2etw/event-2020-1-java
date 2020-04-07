package tw.b2e.receipt.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class WinInfoDetail {

	public WinInfoDetail() {
		super();
	}
	
	public WinInfoDetail(List<String> numbers) {
		super();
		this.numbers = numbers;
	}
	
	public WinInfoDetail(List<String> numbers, String prizeMemo) {
		super();
		this.numbers = numbers;
		this.prizeMemo = prizeMemo;
	}

	private List<String> numbers = new ArrayList<String>();
	private String prizeMemo;
}
