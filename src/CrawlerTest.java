import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by EternalK on 2017/12/12.
 */

public class CrawlerTest {
    public static void main(String[] args) {
        String NEW_LINE = "\n";
        String title = "序号,书名,评分,评价人数,作者,出版社,出版日期,价格";
        OutputStream mOutputStream = null;
        OutputStreamWriter mWriter = null;
        File file = new File("D:\\Users\\EternalFK\\Desktop\\abcd.csv");
        ExecutorService threadPool = Executors.newCachedThreadPool();
        List<DoubanBooks> books = new Vector<>();
        List<DoubanBooks> books2 = new Vector<>();
        Set<DoubanBooks> set = new HashSet<DoubanBooks>();
        threadPool.execute(new ParseHtml("https://book.douban.com", books,"算法"));
        threadPool.execute(new ParseHtml("https://book.douban.com", books,"互联网�"));
        threadPool.execute(new ParseHtml("https://book.douban.com", books,"编程"));
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

            set.addAll(books);
            books2.addAll(set);
            books2.sort((o1, o2) -> Float.compare(o2.getRank(),o1.getRank()));
            mOutputStream = new FileOutputStream(file);
            mWriter = new OutputStreamWriter(mOutputStream, "gbk");
            mWriter.append(title).append(NEW_LINE);
            int i = 1;
            String name;
            for (DoubanBooks book1 : books2) {
                if (i <= 100) {
                    if (book1.getName().contains(",")) {
                        name = "\"" + book1.getName() + "\"";
                    } else {
                        name = book1.getName();
                    }
                    mWriter.append(i++ + ","
                            + name + ","
                            + book1.getRatingNum() + ","
                            + book1.getRatingPeopleNum() + ","
                            + book1.getAuthor() + ","
                            + book1.getPublishing() + ","
                            + book1.getPubDate() + ","
                            + book1.getPrice()).append(NEW_LINE);
                }
            }
            mOutputStream.flush();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (mWriter != null) {
                    mWriter.close();
                }
                if (mOutputStream != null) {
                    mOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
