package tw.b2e.receipt;

import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ReceiptTest {

    @Autowired
    ReceiptRouter receiptRouter;

    private static PrizeNumberObject pnobj;

    @BeforeAll
    public static void testBeforeClass()
    {
        pnobj = new PrizeNumberObject("2016", "9");
    }

    @Test
    void testGetPrizeNumber()
    {
        System.out.println(pnobj.year + pnobj.month);
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
    }

    @Test
    void testCompareNumber()
    {
        assertTrue(pnobj.jsoupRes);
        assertEquals("統一號碼長度錯誤!", pnobj.compareNumber(null));
        assertEquals("統一號碼長度錯誤!", pnobj.compareNumber(""));
        assertEquals("統一號碼長度錯誤!", pnobj.compareNumber("1234"));
        assertEquals("統一號碼格式錯誤!", pnobj.compareNumber("1qaz2wsx"));
        assertEquals("與特別獎號碼相同，獎金1,000萬元!", pnobj.compareNumber("84568349"));
        assertEquals("與特獎號碼相同，獎金200萬元!", pnobj.compareNumber("89536536"));
        assertEquals("與頭獎號碼相同，獎金20萬元!", pnobj.compareNumber("47672189"));
        assertEquals("末7碼與頭獎號碼末7碼相同，獎金4萬元!", pnobj.compareNumber("57672189"));
        assertEquals("末6碼與頭獎號碼末6碼相同，獎金1萬元!", pnobj.compareNumber("88672189"));
        assertEquals("末5碼與頭獎號碼末5碼相同，獎金4千元!", pnobj.compareNumber("77772189"));
        assertEquals("末4碼與頭獎號碼末4碼相同，獎金1千元!", pnobj.compareNumber("88882189"));
        assertEquals("末3碼與頭獎號碼末3碼相同，獎金2百元!", pnobj.compareNumber("33333189"));
        assertEquals("末3碼與增開六獎號碼相同，獎金2百元!", pnobj.compareNumber("47672838"));
        assertEquals("統一號碼未中獎!", pnobj.compareNumber("47672180"));
    }

    @Test
    void testDefaultReceipt(){
        String result = receiptRouter.handle(new SlashCommandRequest("/receip", null));
        System.out.println(result);
    }
}
