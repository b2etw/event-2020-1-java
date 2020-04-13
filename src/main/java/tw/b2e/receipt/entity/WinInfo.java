package tw.b2e.receipt.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Data;
import tw.b2e.receipt.enums.PrizeEnum;

@Data
public class WinInfo {
	private String period;
	private Map<PrizeEnum, PrizeInfo> prizes = new HashMap<PrizeEnum, PrizeInfo>();
		
	public List<String> get(PrizeEnum key) {		
		return prizes.get(key).getNumbers();
	}
	
	public String getPrizeMemo(PrizeEnum key) {		
		return prizes.get(key).getMemo();
	}
		
	public void setPrizeMemo(PrizeEnum key, String memo) {
		if(prizes.get(key) != null) {
			prizes.get(key).setMemo(memo);
		}else {
			PrizeInfo prize = new PrizeInfo();
			prize.setMemo(memo);
			prizes.put(key, prize);
		}
	}
	
	public void add(PrizeEnum key, String number) {
		add(key, number, "");
	}
	
	public void add(PrizeEnum key, String number, String memo) {
		List<String> numbers = new ArrayList<String>();
		numbers.add(number);
		add(key, numbers, memo);
	}
	
	public void add(PrizeEnum key, List<String> numbers) {
		add(key, new PrizeInfo(numbers, ""));
	}
	
	public void add(PrizeEnum key, List<String> numbers, String memo) {
		add(key, new PrizeInfo(numbers, memo));
	}
	
	public void add(PrizeEnum key, PrizeInfo prize) {
		if(prizes.get(key) != null) {
			prizes.get(key).getNumbers().addAll(prize.getNumbers());
		}else {
			prizes.put(key, prize);
		}
	}
	
	public void clear() {
		prizes.clear();
	}
	
	public int size() {
		return prizes.size();
	}
	
	public Set<PrizeEnum> keySet() {
		return prizes.keySet();
	}
}
