package tw.b2e.receipt.entity;

import lombok.Data;
import tw.b2e.receipt.enums.PrizeEnum;

@Data
public class Receipt {
	private String number;
	private PrizeEnum prize;
	
	public Receipt() {
		super();
	}	
	
	public Receipt(String number) {
		super();
		this.number = number;
	}
	
	public Receipt(String number, PrizeEnum prize) {
		super();
		this.number = number;
		this.prize = prize;
	}
	
	public boolean isWin() {
		return prize != null;
	}	
}
