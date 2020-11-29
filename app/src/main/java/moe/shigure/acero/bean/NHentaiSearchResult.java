package moe.shigure.acero.bean;

import java.util.ArrayList;

import moe.shigure.acero.network.json.JsonPack;

/**
 * Created by Shigure on 2020/11/4
 **/

//https://ero.raxianch.moe/ero/nh/search?q=love&page=1

/* "kw": "love",
   "page": 1,
   "pages": "544",
   "results": 25,
   "bookList": []*/

public class NHentaiSearchResult {

    private String keyWord;
    private int page;
    private String pages;
    private int results;
    private  ArrayList<JsonPack> bookList;

    public NHentaiSearchResult setKeyWord(String keyWord) {
        this.keyWord = keyWord;
        return this;
    }
    public NHentaiSearchResult setPage(int page) {
        this.page = page;
        return this;
    }
    public NHentaiSearchResult setPages(String pages) {
        this.pages = pages;
        return this;
    }
    public NHentaiSearchResult setResults(int results) {
        this.results = results;
        return this;
    }
    public NHentaiSearchResult setBookList(ArrayList<JsonPack> bookList) {
        this.bookList = bookList;
        return this;
    }

    public String getKeyWord() {
        return keyWord;
    }
    public int getPage() {
        return page;
    }
    public String getPages() {
        return pages;
    }
    public int getResults() {
        return results;
    }
    public ArrayList<JsonPack> getBookList() {
        return bookList;
    }

    public NHentaiSearchResult fillFromJson(JsonPack jsonPack){
        keyWord = jsonPack.getString("kw");
        page = jsonPack.getInt("page");
        pages = jsonPack.getString("pages");
        results = jsonPack.getInt("results");
        bookList = jsonPack.getJsonPackList("bookList");
        return this;
    }

}
