package tw.b2e.drink;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.util.List;

public class DrinkCrawler {

    public static final String fiftyLan = "http://50lan.com/web/products.asp";


    public static void main(String[] args) {
        try {

            Connection.Response response = Jsoup.connect(fiftyLan).execute().charset("BIG5");
            String body=response.body();
            Document data = Jsoup.parse(body);
            Element menu = data.body().getElementById("Table_01").child(0).child(3);
            System.out.println(menu);
            menu.dataNodes().forEach(node-> System.out.println(node));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
