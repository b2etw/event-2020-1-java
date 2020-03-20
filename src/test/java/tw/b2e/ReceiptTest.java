package tw.b2e;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tw.b2e.receipt.PrizeNumberObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ReceiptTest {

    @Test
    void testGetPrizeNumber() {
        PrizeNumberObject pnobj = new PrizeNumberObject("105", "09");

        assertTrue(pnobj.jsoupRes);
        assertEquals("https://www.etax.nat.gov.tw/etw-main/web/ETW183W2_10509", pnobj.url);
        assertEquals("105", pnobj.year);
        assertEquals("09", pnobj.month);
        assertEquals("84568349", pnobj.specialPrizeNum);
        assertEquals("89536536", pnobj.grandPrizeNum);

        int countF = 0;
        for(String tempF : pnobj.firstPrizeNum){
            if("32149197".equals(tempF)
            || "47672189".equals(tempF)
            || "33669493".equals(tempF)
            ){
                countF++;
            }
        }
        assertEquals(countF, pnobj.firstPrizeNum.size());

        int countA = 0;
        for(String tempA : pnobj.addSixPrizeNum){
            if("161".equals(tempA)
            || "539".equals(tempA)
            || "838".equals(tempA)
            || "037".equals(tempA)
            ){
                countA++;
            }
        }
        assertEquals(countA, pnobj.addSixPrizeNum.size());

//        System.out.println("jsoupRes=" + pnobj.jsoupRes);
//        if (pnobj.jsoupRes) {
//            System.out.println("url=" + pnobj.url);
//            System.out.println("year=" + pnobj.year);
//            System.out.println("month=" + pnobj.month);
//            System.out.println("specialPrizeNum=" + pnobj.specialPrizeNum);
//            System.out.println("grandPrizeNum=" + pnobj.grandPrizeNum);
//            System.out.println("firstPrizeNum=" + pnobj.firstPrizeNum);
//            System.out.println("addSixPrizeNum=" + pnobj.addSixPrizeNum);
//        }
    }
}
