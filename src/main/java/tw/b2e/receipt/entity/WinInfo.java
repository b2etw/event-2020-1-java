package tw.b2e.receipt.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tw.b2e.receipt.enums.PrizeEnum;

public class WinInfo {
	private String period;
	private Map<PrizeEnum, WinInfoDetail> prizeInfo = new HashMap<PrizeEnum, WinInfoDetail>();
		
	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public List<String> get(PrizeEnum key) {		
		return prizeInfo.get(key).getNumbers();
	}
	
	public String getPrizeMemo(PrizeEnum key) {		
		return prizeInfo.get(key).getPrizeMemo();
	}
	
	public void set(Map<PrizeEnum, WinInfoDetail> prizeInfo) {
		this.prizeInfo = prizeInfo;
	}
	
	public void setPrizeMemo(PrizeEnum key, String prizeMemo) {
		if(prizeInfo.get(key) != null) {
			prizeInfo.get(key).setPrizeMemo(prizeMemo);
		}else {
			WinInfoDetail detail = new WinInfoDetail();
			detail.setPrizeMemo(prizeMemo);
			prizeInfo.put(key, detail);
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
		add(key, new WinInfoDetail(numbers, ""));
	}
	
	public void add(PrizeEnum key, List<String> numbers, String memo) {
		add(key, new WinInfoDetail(numbers, memo));
	}
	
	public void add(PrizeEnum key, WinInfoDetail winInfoDetail) {
		if(prizeInfo.get(key) != null) {
			prizeInfo.get(key).getNumbers().addAll(winInfoDetail.getNumbers());
		}else {
			prizeInfo.put(key, winInfoDetail);
		}
	}
	
	public void clear() {
		prizeInfo.clear();
	}
	
	public int size() {
		return prizeInfo.size();
	}
	
	public Set<PrizeEnum> keySet() {
		return prizeInfo.keySet();
	}
}
