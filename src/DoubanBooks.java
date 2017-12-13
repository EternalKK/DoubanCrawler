import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

/**
 * Created by EternalK on 2017/12/12.
 */

public class DoubanBooks implements Serializable {
    private String name;
    private String author;
    private String publishing;
    private String pubDate;
    private String ratingNum;
    private String ratingPeopleNum;
    private String price;

    public DoubanBooks(String name, String author, String publishing, String pubDate, String ratingNum, String ratingPeopleNum, String price) {
        this.name = name;
        this.author = author;
        this.publishing = publishing;
        this.pubDate = pubDate;
        this.ratingNum = ratingNum;
        this.ratingPeopleNum = ratingPeopleNum;
        this.price = price;
    }

    public float getRank() {
        return Float.parseFloat(ratingNum);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishing() {
        return publishing;
    }

    public void setPublishing(String publishing) {
        this.publishing = publishing;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getRatingNum() {
        return ratingNum;
    }

    public void setRatingNum(String ratingNum) {
        this.ratingNum = ratingNum;
    }

    public String getRatingPeopleNum() {
        return ratingPeopleNum;
    }

    public void setRatingPeopleNum(String ratingPeopleNum) {
        this.ratingPeopleNum = ratingPeopleNum;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        DoubanBooks book = (DoubanBooks) o;
        if (name == null) {
            if (book.name != null) {
                return false;
            }
        } else if (!name.equals(book.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

//    public static List<DoubanBooks> removeListDuplicateObject(List<DoubanBooks> list) {
////        System.out.println(Arrays.toString(list.toArray()));
//        Set<DoubanBooks> set = new HashSet<DoubanBooks>();
//        set.addAll(list);
////        System.out.println(Arrays.toString(set.toArray()));
//        List<DoubanBooks> listnewList = new Vector<DoubanBooks>(set);
//        return listnewList;
//    }
}
