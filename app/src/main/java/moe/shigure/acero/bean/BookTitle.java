package moe.shigure.acero.bean;

import moe.shigure.acero.network.json.JsonPack;

/**
 * Created by Shigure on 2020/11/4
 **/

/* {
    "full_name": "[Okina Flying Factory (OKINA)] Love Lopetto [Chinese] [空白个人汉化]",
    "translated": "[Okina Flying Factory (OKINA)] Love Lopetto [中国翻訳]",
    "abbre": "Love Lopetto"
} */

public class BookTitle {

    private String fullName;
    private String translated;
    private String abbre;

    public BookTitle setFull_name(String fullName) {
        this.fullName = fullName;
        return this;
    }
    public BookTitle setTranslated(String translated) {
        this.translated = translated;
        return this;
    }
    public BookTitle setAbbre(String abbre) {
        this.abbre = abbre;
        return this;
    }

    public String getFullName() {
        return fullName;
    }
    public String getTranslated() {
        return translated;
    }
    public String getAbbre() {
        return abbre;
    }

    public BookTitle fillFromJson(JsonPack jsonPack){
        fullName = jsonPack.getString("full_name");
        translated = jsonPack.getString("translated");
        abbre = jsonPack.getString("abbre");
        return this;
    }

}
