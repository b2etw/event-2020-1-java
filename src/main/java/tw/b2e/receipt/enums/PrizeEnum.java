package tw.b2e.receipt.enums;

public enum PrizeEnum {
	SPECIAL_FIRST  ("SPECIAL_FIRST", "特別獎", 10000000),
	SPECIAL_SECOND("SPECIAL_SECOND", "特獎", 2000000),
	FIRST("FIRST ", "頭獎", 200000),
	SECOND("SECOND", "二獎", 40000),
	THIRD("THIRD", "三獎", 10000),
	FOURTH("FOURTH", "四獎", 4000),
	FIFTH("FIFTH", "五獎", 1000),
	SIXTH("SIXTH", "六獎", 200),
	EXTRA_SIXTH("EXTRA_SIXTH", "增開六獎", 200);;
	 
	private String type;
	private String typeName;
	private long amount;
	
	PrizeEnum(String type, String typeName, long amount) {
		this.type = type;
		this.typeName = typeName;
		this.amount = amount;
	}
}
