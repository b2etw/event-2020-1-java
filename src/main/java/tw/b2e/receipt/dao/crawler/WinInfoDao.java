package tw.b2e.receipt.dao.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import tw.b2e.receipt.entity.WinInfo;
import tw.b2e.receipt.enums.PrizeEnum;
import tw.b2e.receipt.entity.WinInfoDetail;

@Component
public class WinInfoDao {

	private static String URL_FORMAT = "https://www.etax.nat.gov.tw/etw-main/front/ETW183W2_%s/";
	public WinInfo get(String period) {
		WinInfo result = new WinInfo();
		
		if(period == null || !isRepublicYear(period)) {
			throw new RuntimeException("請傳入民國年月(yyymm).");
		}		
		
		if(Integer.valueOf(period) % 2 == 0) {//URL規則為該期別單數月
			period = String.valueOf(Integer.valueOf(period) - 1);
		}
		
		String url = String.format(URL_FORMAT, period);
		try {
			Document doc = Jsoup.connect(url).get();
			Elements elements = doc.select("tbody > tr");
			result.setPeriod(getPeriod(elements));
			result.add(PrizeEnum.SPECIAL_FIRST, getSpecialFirst(elements));			
			result.add(PrizeEnum.SPECIAL_SECOND, getSpecialSecond(elements));
			result.add(PrizeEnum.FIRST, getFirst(elements));
			result.add(PrizeEnum.SECOND, getSecond(elements));			
			result.add(PrizeEnum.THIRD, getThird(elements));
			result.add(PrizeEnum.FOURTH, getFourth(elements));
			result.add(PrizeEnum.FIFTH, getFifth(elements));
			result.add(PrizeEnum.SIXTH, getSixth(elements));			
			result.add(PrizeEnum.EXTRA_SIXTH, getExtraSixth(elements));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private String getPeriod(Elements elements) {
		return elements.get(0).child(1).text();
	}
	
	private List<String> numStringToList(String numStr) {
		List<String> result = new ArrayList<String>();
		String[] numbers = numStr.split("、");
		for (String number : numbers) {
			result.add(number);
		}
		return result;
	}
	
	private WinInfoDetail getSpecialFirst(Elements elements) {
		return genWinInfoDetail(elements.get(1).child(1), elements.get(2).child(0));
	}
	private WinInfoDetail getSpecialSecond(Elements elements) {
		return genWinInfoDetail(elements.get(3).child(1), elements.get(4).child(0));
	}	
	private WinInfoDetail getFirst(Elements elements) {
		return genWinInfoDetail(elements.get(5).child(1), elements.get(6).child(0));
	}
	
	private WinInfoDetail getSecond(Elements elements) {
		return genWinInfoDetail(null, elements.get(7).child(1));
	}	
	
	private WinInfoDetail getThird(Elements elements) {
		return genWinInfoDetail(null, elements.get(8).child(1));
	}
	
	private WinInfoDetail getFourth(Elements elements) {
		return genWinInfoDetail(null, elements.get(9).child(1));
	}
	
	private WinInfoDetail getFifth(Elements elements) {
		return genWinInfoDetail(null, elements.get(10).child(1));
	}
	
	private WinInfoDetail getSixth(Elements elements) {
		return genWinInfoDetail(null, elements.get(11).child(1));
	}
	
	private WinInfoDetail getExtraSixth(Elements elements) {
		return genWinInfoDetail(elements.get(12).child(1), elements.get(13).child(0));
	}
	
	private WinInfoDetail genWinInfoDetail(Element numberElement, Element memoElement) {
		WinInfoDetail result = new WinInfoDetail();
		
		if(numberElement != null)
			result.setNumbers(numStringToList(numberElement.text()));
		
		if(memoElement != null)
			result.setPrizeMemo(memoElement.text());
		
		return result;
	}
	
	private boolean isRepublicYear(String period) {
		return period.length() == 5;
	}
}
