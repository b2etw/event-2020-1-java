package tw.b2e.receipt.dao.crawler;

import java.io.IOException;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import tw.b2e.receipt.entity.PrizeInfo;
import tw.b2e.receipt.entity.WinInfo;
import tw.b2e.receipt.enums.PrizeEnum;

@Component
public class WinInfoDao {

	private static String URL_FORMAT = "https://www.etax.nat.gov.tw/etw-main/front/ETW183W2_%s/";
	public WinInfo get(String period) throws IOException {
		WinInfo result = new WinInfo();
		
		if(Integer.valueOf(period) % 2 == 0) {//URL規則為該期別單數月
			period = String.valueOf(Integer.valueOf(period) - 1);
		}
		
		String url = String.format(URL_FORMAT, period);
		Response response = Jsoup.connect(url).execute();
		if (200 != response.statusCode()) {
			throw new ConnectException(String.format("連線失敗 statusCode:%s URL:%s", response.statusCode(), url));
		}
		
		Document doc = response.parse();			
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
	
	private PrizeInfo getSpecialFirst(Elements elements) {
		return genPrize(elements.get(1).child(1), elements.get(2).child(0));
	}
	private PrizeInfo getSpecialSecond(Elements elements) {
		return genPrize(elements.get(3).child(1), elements.get(4).child(0));
	}	
	private PrizeInfo getFirst(Elements elements) {
		return genPrize(elements.get(5).child(1), elements.get(6).child(0));
	}
	
	private PrizeInfo getSecond(Elements elements) {
		return genPrize(null, elements.get(7).child(1));
	}	
	
	private PrizeInfo getThird(Elements elements) {
		return genPrize(null, elements.get(8).child(1));
	}
	
	private PrizeInfo getFourth(Elements elements) {
		return genPrize(null, elements.get(9).child(1));
	}
	
	private PrizeInfo getFifth(Elements elements) {
		return genPrize(null, elements.get(10).child(1));
	}
	
	private PrizeInfo getSixth(Elements elements) {
		return genPrize(null, elements.get(11).child(1));
	}
	
	private PrizeInfo getExtraSixth(Elements elements) {
		return genPrize(elements.get(12).child(1), elements.get(13).child(0));
	}
	
	private PrizeInfo genPrize(Element numberElement, Element memoElement) {
		PrizeInfo result = new PrizeInfo();
		
		if(numberElement != null)
			result.setNumbers(numStringToList(numberElement.text()));
		
		if(memoElement != null)
			result.setMemo(memoElement.text());
		
		return result;
	}
}
