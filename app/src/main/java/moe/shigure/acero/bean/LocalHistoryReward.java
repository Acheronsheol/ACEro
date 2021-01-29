package moe.shigure.acero.bean;

/**
 * Created by Shigure on 2020/11/4
 **/

public class LocalHistoryReward {

    private long id;
    private long lastTime;
    private String data;

    public LocalHistoryReward setId(long id) {
        this.id = id;
        return this;
    }
    public LocalHistoryReward setLastTime(long lastTime) {
        this.lastTime = lastTime;
        return this;
    }
    public LocalHistoryReward setData(String data) {
        this.data = data;
        return this;
    }

    public long getId() {
        return id;
    }
    public long getLastTime() {
        return lastTime;
    }
    public String getData() {
        return data;
    }

}
