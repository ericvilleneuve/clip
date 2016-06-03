package com.evil.clip.domain;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.util.concurrent.ConcurrentMap;

public class UrlRepository {

    private final ConcurrentMap<String, String> urls;
    private final DB db;

    public UrlRepository(String dbFilename) {
        db = DBMaker.newFileDB(new File(dbFilename)).closeOnJvmShutdown().make();
        urls = db.getHashMap("urls");
    }

    public String findByShortUrl(String shortUrl) {
        return urls.get(shortUrl);
    }

    public void add(String shortened, String original) {
        urls.put(shortened, original);
        db.commit();
    }
}
