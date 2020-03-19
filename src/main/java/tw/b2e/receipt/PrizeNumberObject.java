package tw.b2e.receipt;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/*
統一發票中獎號碼物件類
 */
public class PrizeNumberObject {

    public boolean jsoupRes;

    public String url = "https://www.etax.nat.gov.tw/etw-main/web/ETW183W2_";
    public String year;
    public String month;
    public Document doc;
    public String specialPrizeNum;//特別獎號碼
    public String grandPrizeNum;//特獎號碼
    public ArrayList<String> firstPrizeNum;//頭獎號碼
    public ArrayList<String> addSixPrizeNum;//增開六獎號碼

    public PrizeNumberObject(String year, String month){
        this.year = year;
        this.month = month;
        this.url = url + year + month;
        jsoupRes = this.getPrizeNumber();
    }

    public boolean getPrizeNumber(){
        try {

            doc = Jsoup.connect(url).timeout(3000).get();

            Elements group1 = doc.select("td[headers=specialPrize][class=number]");
            specialPrizeNum = group1.text().trim();

            Elements group2 = doc.select("td[headers=grandPrize][class=number]");
            grandPrizeNum = group2.text().trim();

            Elements group3 = doc.select("td[headers=firstPrize][class=number] p");
            if(group3 != null && group3.size() > 0){
                firstPrizeNum = new ArrayList<>();
                for(Element e : group3){
                    if(!"".equals(e.text().trim())){
                        firstPrizeNum.add(e.text().trim());
                    }
                }
            }

            Elements group9 = doc.select("td[headers=addSixPrize][class=number]");
            if(group9 != null && group9.size() > 0){
                addSixPrizeNum = new ArrayList<>();
                String[] tempG9 = group9.text().trim().split("、");
                for(String s : tempG9){
                    if(!"".equals(s)){
                        addSixPrizeNum.add(s);
                    }
                }
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
