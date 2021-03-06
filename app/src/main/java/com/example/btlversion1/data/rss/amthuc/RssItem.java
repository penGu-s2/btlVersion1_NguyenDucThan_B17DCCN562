package com.example.btlversion1.data.rss.amthuc;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

@Root(name = "item", strict = false)
public class RssItem implements Serializable {
    @Element(name ="title")
    private String title;

    @Element(name = "link")
    private String link;

    @Element(name ="pubDate")
    private String pubDate;
    @Element(name ="description")
    private String description;
    //@ElementList(inline = true, required = false)
    //private List<Rssdescription> mdescriptionList;
    //private Rssdescription description;
    public RssItem() {

    }

    public RssItem(String title, String link, String pubDate, String description) {
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RssItem{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", description=" + description +
                '}';
    }
}
