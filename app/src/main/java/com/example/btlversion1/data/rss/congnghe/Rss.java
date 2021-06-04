package com.example.btlversion1.data.rss.congnghe;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "rss",strict = false)
public class Rss {
    @Element
    public RssChannel channel;

    public RssChannel getChannel() {
        return channel;
    }

    public void setChannel(RssChannel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "RssFeed [channel=" + channel + "]";
    }
}
