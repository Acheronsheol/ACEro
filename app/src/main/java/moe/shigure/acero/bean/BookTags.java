package moe.shigure.acero.bean;

import moe.shigure.acero.network.json.JsonPack;

/**
 * Created by Shigure on 2020/11/4
 **/

/*{
    "id": 29963,
    "type": "language",
    "name": "chinese",
    "url": "/language/chinese/",
    "count": 53179
}*/

public class BookTags {

    private long id;
    private String type;
    private String name;
    private String url;
    private long count;

    public BookTags setId(long id) {
        this.id = id;
        return this;
    }
    public BookTags setType(String type) {
        this.type = type;
        return this;
    }
    public BookTags setName(String name) {
        this.name = name;
        return this;
    }
    public BookTags setUrl(String url) {
        this.url = url;
        return this;
    }
    public BookTags setCount(long count) {
        this.count = count;
        return this;
    }

    public long getId() {
        return id;
    }
    public String getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public String getUrl() {
        return url;
    }
    public long getCount() {
        return count;
    }

    public BookTags fillFromJson(JsonPack jsonPack){
        id = jsonPack.getLong("id");
        type = jsonPack.getString("type");
        name = jsonPack.getString("name");
        url = jsonPack.getString("url");
        count = jsonPack.getLong("count");
        return this;
    }

}
