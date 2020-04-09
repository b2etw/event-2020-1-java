package tw.b2e.receipt.entity;

import tw.b2e.receipt.enums.PrizeEnum;

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
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public boolean isWin() {
		return prize != null;
	}
	
	public PrizeEnum getPrize() {
		return prize;
	}
	public void setPrize(PrizeEnum prize) {
		this.prize = prize;
	}
	
	
}
