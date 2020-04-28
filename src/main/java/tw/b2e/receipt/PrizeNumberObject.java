package tw.b2e.receipt;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 統一發票中獎號碼物件類
 */
public class PrizeNumberObject {

    public boolean jsoupRes;

    public String url = "https://www.etax.nat.gov.tw/etw-main/web/ETW183W2_";
    public String year;
    public String month;
    public String monthDoc;
    public Document doc;
    public String specialPrizeNum;//特別獎號碼
    public String grandPrizeNum;//特獎號碼
    public ArrayList<String> firstPrizeNum;//頭獎號碼
    public ArrayList<String> addSixPrizeNum;//增開六獎號碼

    /**
     * 建構統一發票中獎號碼物件類
     */
    public PrizeNumberObject(String year, String month)
    {
        this.year = this.setYear(year);
        this.month = this.setMonth(month);
        this.url = url + this.year + this.month;
        jsoupRes = this.getPrizeNumber();
    }

    /**
     * 設定年份
     */
    public String setYear(String year)
    {
        if(year.length() > 3){
            int tempY = Integer.parseInt(year);
            tempY = tempY - 1911;
            year = Integer.toString(tempY);
        }
        return year;
    }

    /**
     * 設定月份
     */
    public String setMonth(String month)
    {
        int tempM = Integer.parseInt(month);
        String tempMD = "";
        if(tempM % 2 == 0){
            if(tempM > 10){
                tempMD = (tempM - 1) + "-" + tempM;
                month = "" + (tempM - 1);
            }else if(tempM == 10){
                tempMD = "0" + (tempM - 1) + "-" + tempM;
                month = "0" + (tempM - 1);
            }else{
                tempMD = "0" + (tempM - 1) + "-0" + tempM;
                month = "0" + (tempM - 1);
            }
        }else{
            if(tempM > 10){
                tempMD = tempM+ "-" + (tempM + 1);
            }else if(tempM == 9){
                tempMD = "0" + tempM + "-" + (tempM + 1);
                month = "0" + tempM;
            }else{
                tempMD = "0" + tempM + "-0" + (tempM + 1);
                month = "0" + tempM;
            }
        }
        this.monthDoc = tempMD;
        return month;
    }

    /**
     * 取得統一發票中獎號碼
     */
    public boolean getPrizeNumber()
    {
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

    /**
     * 取得統一發票中獎號碼描述
     */
    public String getAllPrizeNumberDesc()
    {
        String tempAddSix = "";
        for(String oneAddSix : addSixPrizeNum){
            tempAddSix += oneAddSix + "、";
        }
        tempAddSix = tempAddSix.substring(0,tempAddSix.length()-1);

        String allPrizeNumberDesc =
                "%s年度%s月\r\n" +
                "統一發票中獎號碼\r\n" +
                "特別獎：%s\r\n" +
                "　特獎：%s\r\n" +
                "　頭獎：%s\r\n" +
                "　　　　%s\r\n" +
                "　　　　%s\r\n" +
                "增開六獎：%s\r\n" +
                "參考連結：%s\r\n";
        return String.format(allPrizeNumberDesc
                , year, monthDoc, specialPrizeNum, grandPrizeNum
                , firstPrizeNum.get(0), firstPrizeNum.get(1), firstPrizeNum.get(2)
                , tempAddSix, url
        );
    }

    /**
     * 比對統一發票中獎號碼
     */
    public String compareNumber(String receiptStr)
    {
        String res = "";
        if(receiptStr == null || "".equals(receiptStr) || receiptStr.length() != 8) {
            res = "統一號碼長度錯誤!";
        } else if(!receiptStr.matches("[0-9]{8}")) {
            res = "統一號碼格式錯誤!";
        } else if(receiptStr.equals(specialPrizeNum)) {
            res = "與特別獎號碼相同，獎金1,000萬元!";
        } else if(receiptStr.equals(grandPrizeNum)) {
            res = "與特獎號碼相同，獎金200萬元!";
        } else {
            char[] receiptCharArray = receiptStr.toCharArray();
            for (String oneFirstPrizeNum : firstPrizeNum) {
                int resNth = -1;
                char[] oneFirstPrizeNumCharArray = oneFirstPrizeNum.toCharArray();
                for (int nth = 7; nth >= 0; nth--) {
                    if (receiptCharArray[nth] != oneFirstPrizeNumCharArray[nth]) {
                        resNth = nth;
                        break;
                    }
                }
                if (resNth == -1) {
                    res = "與頭獎號碼相同，獎金20萬元!";
                    break;
                } else if (resNth == 0) {
                    res = "末7碼與頭獎號碼末7碼相同，獎金4萬元!";
                    break;
                } else if (resNth == 1) {
                    res = "末6碼與頭獎號碼末6碼相同，獎金1萬元!";
                    break;
                } else if (resNth == 2) {
                    res = "末5碼與頭獎號碼末5碼相同，獎金4千元!";
                    break;
                } else if (resNth == 3) {
                    res = "末4碼與頭獎號碼末4碼相同，獎金1千元!";
                    break;
                } else if (resNth == 4) {
                    res = "末3碼與頭獎號碼末3碼相同，獎金2百元!";
                    break;
                }
            }

            if ("".equals(res)) {
                String lastThreeNum = receiptStr.substring(5);
                for (String oneAddSixPrizeNum : addSixPrizeNum) {
                    if (lastThreeNum.equals(oneAddSixPrizeNum)) {
                        res = "末3碼與增開六獎號碼相同，獎金2百元!";
                        break;
                    }
                }

                if ("".equals(res)) {
                    res = "統一號碼未中獎!";
                }
            }
        }
        return res;
    }

}
