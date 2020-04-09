package tw.b2e.receipt.enums;

public enum PrizeEnum {
	SPECIAL_FIRST  ("特別獎", 10000000, 0),
	SPECIAL_SECOND("特獎", 2000000, 0),
	FIRST("頭獎", 200000, 0),
	SECOND("二獎", 40000, 1),
	THIRD("三獎", 10000, 2),
	FOURTH("四獎", 4000, 3),
	FIFTH("五獎", 1000, 4),
	SIXTH("六獎", 200, 5),
	EXTRA_SIXTH("增開六獎", 200, 0);
	 
	private String typeName;
	private long amount;
	private int matchBeginIndex;
	
	PrizeEnum(String typeName, long amount, int matchBeginIndex) {
		this.typeName = typeName;
		this.amount = amount;
		this.matchBeginIndex = matchBeginIndex;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public int getMatchBeginIndex() {
		return matchBeginIndex;
	}

	public void setMatchBeginIndex(int matchBeginIndex) {
		this.matchBeginIndex = matchBeginIndex;
	}
}
