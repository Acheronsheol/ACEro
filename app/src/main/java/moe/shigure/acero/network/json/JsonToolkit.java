package moe.shigure.acero.network.json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonToolkit {

    /*
    * 实体转Json字符串
    * */
    public static String objectToJsonString(Object bean) {
        return new Gson().toJson(bean);
    }

    /*
    * Json转实体
    *  使用前强制转换 Bean bean = (Bean)JsonToolkit.jsonToObj(json, Bean.class);
    * */
    public static Object jsonToObj(String jsonStr,Class beanClass) {
        try {
            return new Gson().fromJson(jsonStr, beanClass);
        } catch (Exception e){
            return new Object();
        }
    }

    /*
    * json字符串转JsonObject
    * */
    public static JsonObject jsonStrToJsonObj(String jsonStr) {
        try {
            return new Gson().fromJson(jsonStr, JsonObject.class);
        } catch (Exception e){
            return new JsonObject();
        }
    }

    /*
     * json字符串转JsonArray(无数据头)
     * */
    public static JsonArray jsonStrToJsonArr(String jsonStr) {
        try {
            return new Gson().fromJson(jsonStr, JsonArray.class);
        } catch (Exception e){
            return new JsonArray();
        }
    }

    /*
     * json字符串转JsonArray(有数据头)
     * */
    public static JsonArray jsonStrToJsonArr(String jsonStr, String dataHead) {
        try {
            return new Gson().fromJson(jsonStr, JsonObject.class).getAsJsonArray(dataHead);
        } catch (Exception e){
            return new JsonArray();
        }
    }

    /*
     * 判断是否为json字符串
     * */
    public static boolean isJsonStr(String jsonStr) {
        try {
            new Gson().fromJson(jsonStr, Object.class);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

}
