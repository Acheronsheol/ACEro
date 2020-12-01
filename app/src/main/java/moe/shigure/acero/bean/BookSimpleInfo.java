package moe.shigure.acero.bean;

import moe.shigure.acero.network.json.JsonPack;

/**
 * Created by Shigure on 2020/11/4
 **/

/* "id": "337751",
   "hash": "1786943",
   "bname": "[Okina Flying Factory (OKINA)] Love Lopetto [Chinese] [空白个人汉化]",
   "cover": "/ero/nh/t/1786943/thumb.jpg",
   "url": "/ero/nh/id/337751/"
   */

public class BookSimpleInfo {

    private long id;
    private String hash;
    private String bookName;
    private String cover;
    private String url;

    public BookSimpleInfo setId(long id) {
        this.id = id;
        return this;
    }
    public BookSimpleInfo setHash(String hash) {
        this.hash = hash;
        return this;
    }
    public BookSimpleInfo setBookName(String bookName) {
        this.bookName = bookName;
        return this;
    }
    public BookSimpleInfo setCover(String cover) {
        this.cover = cover;
        return this;
    }
    public BookSimpleInfo setUrl(String url) {
        this.url = url;
        return this;
    }

    public long getId() {
        return id;
    }
    public String getHash() {
        return hash;
    }
    public String getBookName() {
        return bookName;
    }
    public String getCover() {
        return cover;
    }
    public String getUrl() {
        return url;
    }

    public BookSimpleInfo fillFromJson(JsonPack jsonPack){
        id = jsonPack.getLong("id");
        hash = jsonPack.getString("hash");
        bookName = jsonPack.getString("bname");
        cover = jsonPack.getString("cover");
        url = jsonPack.getString("url");
        if(cover.startsWith("/")){
            cover = "https://ero.raxianch.moe" + cover;
        }
        return this;
    }

}
