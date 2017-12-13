import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;


/**
 * Created by EternalK on 2017/12/12.
 */

public class ParseHtml implements Runnable {
    String mAddress;
    String mKeyword;
    List<DoubanBooks> mSort;

    public ParseHtml(String mAddress, List<DoubanBooks> mSort, String mKeyword) {
        this.mAddress = mAddress;
        this.mKeyword = mKeyword;
        this.mSort = mSort;
    }

    @Override
    public void run() {
        try {
            parse(mAddress, mKeyword);
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parse(String url, String keyword) {
        String name = null;
        String author = null;
        String publishing = null;
        String ratingPeopleNum = null;
        String date = null;
        String price = null;
        try {
            for (int i = 0; i < 200; i += 20) {
                Document doc = Jsoup.connect(url + "/tag/" + keyword + "?start=" + i + "&type=S")
                        .header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11")
                        .timeout(3000)
                        .get();
                for (int j = 1; j <= 20; j++) {
                    Elements title = doc.select("#subject_list").select("ul")
                            .select("li:nth-child(" + j +")").select("div.info").select("h2")
                            .select("a");
                    Elements info = doc.select("#subject_list").select("ul")
                            .select("li:nth-child(" + j + ")").select("div.info").select("div.pub");
                    Elements ratingNum = doc.select("#subject_list").select("ul")
                            .select("li:nth-child(" + j + ")").select("div.info")
                            .select("div.star.clearfix").select("span.rating_nums");
                    Elements ratingPeopleNums = doc.select("#subject_list").select("ul")
                            .select("li:nth-child(" + j + ")").select("div.info")
                            .select("div.star.clearfix");
//                    if (ratingNum.text() != null && ratingNum.text().length() != 0) {
//
//                    }
                    name = title.text();
                    String temp = ratingPeopleNums.text();
                    ratingPeopleNum = temp.substring(temp.indexOf("(") + 1, temp.indexOf(")") - 3);
                    StringTokenizer st = new StringTokenizer(info.text(), "/");
                    if (st.countTokens() == 5) {
                        author = st.nextToken();
                        st.nextToken();
                        publishing = st.nextToken();
                        date = st.nextToken();
                        price = st.nextToken();
                    } else if (st.countTokens() == 4) {
                        author = st.nextToken();
                        publishing = st.nextToken();
                        date = st.nextToken();
                        price = st.nextToken();
                    }
                    DoubanBooks book = new DoubanBooks(name, author, publishing, date,
                            ratingNum.text(), ratingPeopleNum, price);
                    if (Integer.parseInt(ratingPeopleNum) > 1000) {
                        mSort.add(book);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
