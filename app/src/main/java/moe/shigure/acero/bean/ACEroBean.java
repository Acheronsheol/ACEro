package moe.shigure.acero.bean;

import moe.shigure.acero.network.json.JsonPack;

/**
 * Created by Shigure on 2020/11/4
 **/

public class ACEroBean {

    private int statusCode;
    private boolean statusBool;
    private int version;
    private String costTime;
    private String date;
    private double timeStamp;
    private String message;
    private JsonPack data;

    public ACEroBean setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }
    public ACEroBean setStatusBool(boolean statusBool) {
        this.statusBool = statusBool;
        return this;
    }
    public ACEroBean setVersion(int version) {
        this.version = version;
        return this;
    }
    public ACEroBean setCostTime(String costTime) {
        this.costTime = costTime;
        return this;
    }
    public ACEroBean setDate(String date) {
        this.date = date;
        return this;
    }
    public ACEroBean setTimeStamp(double timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }
    public ACEroBean setMessage(String message) {
        this.message = message;
        return this;
    }
    public ACEroBean setData(JsonPack data) {
        this.data = data;
        return this;
    }

    public int getStatusCode() {
        return statusCode;
    }
    public boolean isStatusBool() {
        return statusBool;
    }
    public int getVersion() {
        return version;
    }
    public String getCostTime() {
        return costTime;
    }
    public String getDate() {
        return date;
    }
    public double getTimeStamp() {
        return timeStamp;
    }
    public String getMessage() {
        return message;
    }
    public JsonPack getData() {
        return data;
    }

    public ACEroBean fillFromJson(JsonPack jsonPack){
        statusCode = jsonPack.getInt("status_code");
        statusBool = jsonPack.getBoolean("status_bool");
        version = jsonPack.getInt("version");
        costTime = jsonPack.getString("cost_time");
        date = jsonPack.getString("date");
        timeStamp = jsonPack.getDouble("ts");
        message = jsonPack.getString("message");
        data = jsonPack.getJsonPack("data");
        return this;
    }

}
