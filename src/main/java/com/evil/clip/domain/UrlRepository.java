package com.evil.clip.domain;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.util.concurrent.ConcurrentMap;

public class UrlRepository {

    private final ConcurrentMap<String, String> urls;

    public UrlRepository() {
        DB db = DBMaker.newFileDB(new File("url.db")).closeOnJvmShutdown().make();
        urls = db.createHashMap("urls").make();
    }

    public String findByShortUrl(String shortUrl) {
        return urls.get(shortUrl);
    }

    public void add(String shortened, String original) {
        urls.put(shortened, original);
    }
}
