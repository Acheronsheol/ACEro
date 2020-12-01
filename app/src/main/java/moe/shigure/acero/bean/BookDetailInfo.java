package moe.shigure.acero.bean;

import java.util.ArrayList;

import moe.shigure.acero.network.json.JsonPack;

/**
 * Created by Shigure on 2020/11/4
 **/

/*{
        "id": "337751",
        "hash": "1786943",
        "origin": "nhentai",
        "title": {
            "full_name": "[Okina Flying Factory (OKINA)] Love Lopetto [Chinese] [空白个人汉化]",
            "translated": "[Okina Flying Factory (OKINA)] Love Lopetto [中国翻訳]",
            "abbre": "Love Lopetto"
        },
        "pages": 32,
        "favorites": 0,
        "upload_date": 1606628461,
        "cover": "/ero/nh/t/1786943/cover.jpg",
        "galleries": "/ero/nh/galleries/=IzM8NDN5YDO3EDfxUzN3MzM",
        "tags": [
            {
                "id": 29963,
                "type": "language",
                "name": "chinese",
                "url": "/language/chinese/",
                "count": 53179
            },
            {
                "id": 17249,
                "type": "language",
                "name": "translated",
                "url": "/language/translated/",
                "count": 124965
            }
        ]
    }*/

public class BookDetailInfo {

    private long id;
    private String hash;
    private String origin;
    private BookTitle title;
    private int pages;
    private long favorites;
    private long uploadDate;
    private String cover;
    private String galleries;
    private ArrayList<BookTags> tags;

    public BookDetailInfo setId(long id) {
        this.id = id;
        return this;
    }
    public BookDetailInfo setHash(String hash) {
        this.hash = hash;
        return this;
    }
    public BookDetailInfo setOrigin(String origin) {
        this.origin = origin;
        return this;
    }
    public BookDetailInfo setTitle(BookTitle title) {
        this.title = title;
        return this;
    }
    public BookDetailInfo setPages(int pages) {
        this.pages = pages;
        return this;
    }
    public BookDetailInfo setFavorites(long favorites) {
        this.favorites = favorites;
        return this;
    }
    public BookDetailInfo setUploadDate(long uploadDate) {
        this.uploadDate = uploadDate;
        return this;
    }
    public BookDetailInfo setCover(String cover) {
        this.cover = cover;
        return this;
    }
    public BookDetailInfo setGalleries(String galleries) {
        this.galleries = galleries;
        return this;
    }
    public BookDetailInfo setTags(ArrayList<BookTags> tags) {
        this.tags = tags;
        return this;
    }

    public long getId() {
        return id;
    }
    public String getHash() {
        return hash;
    }
    public String getOrigin() {
        return origin;
    }
    public BookTitle getTitle() {
        return title;
    }
    public int getPages() {
        return pages;
    }
    public long getFavorites() {
        return favorites;
    }
    public long getUploadDate() {
        return uploadDate;
    }
    public String getCover() {
        return cover;
    }
    public String getGalleries() {
        return galleries;
    }
    public ArrayList<BookTags> getTags() {
        return tags;
    }

    public BookDetailInfo fillFromJson(JsonPack jsonPack){
        id = jsonPack.getLong("id");
        hash = jsonPack.getString("hash");
        origin = jsonPack.getString("origin");
        title = new BookTitle().fillFromJson(jsonPack.getJsonPack("title"));
        pages = jsonPack.getInt("pages");
        favorites = jsonPack.getLong("favorites");
        uploadDate = jsonPack.getLong("upload_date");
        cover = jsonPack.getString("cover");
        galleries = jsonPack.getString("galleries");
        tags = new ArrayList<BookTags>(){
            {
                for (JsonPack model : jsonPack.getJsonPackList("tags")){
                    add(new BookTags().fillFromJson(model));
                };
            }
        };
        if(cover.startsWith("/")){
            cover = "https://ero.raxianch.moe" + cover;
        }
        return this;
    }

}
