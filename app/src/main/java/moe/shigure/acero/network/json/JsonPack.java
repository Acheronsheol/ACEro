package moe.shigure.acero.network.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import java.util.ArrayList;

/**
 * jsonObject封装包
 * Created by wang on 2020/4/27
 **/

public class JsonPack {

	/*
	* 内容为null时设置默认空值(数值默认为0)
	* */
	private String NULL="";
	static JsonPack[] NULL_PACKS=new JsonPack[0];
	private ArrayList<JsonPack> NULL_PACK_LIST = new ArrayList<>();

	/*
	* JsonObject源
	* */
	JsonObject jo;

	/*
	* 创建被封装的JsonObject
	* */
	public JsonPack(JsonObject jo){
		this.jo=jo;
	}

	/*
	 * 重新设置封装内的JsonObject
	 * */
	public void reset(JsonObject jo){
		this.jo=jo;
	}

	/*
	 * 获取封装内的JsonObject
	 * */
	public JsonObject getJson(){
		return jo;
	}

	/*
	 * 获取byte字节型
	 * */
	public byte getByte(String id){
		JsonElement je=jo.get(id);
		if(je==null)return 0;
		return je.getAsByte();
	}

	/*
	 * 获取short短整数
	 * */
	public short getShort(String id){
		JsonElement je=jo.get(id);
		if(je==null)return 0;
		return je.getAsShort();
	}

	/*
	 * 获取int整数
	 * */
	public int getInt(String id){
		JsonElement je=jo.get(id);
		if(je==null)return 0;
		return je.getAsInt();
	}

	/*
	 * 获取long长整数
	 * */
	public long getLong(String id){
		JsonElement je=jo.get(id);
		if(je==null)return 0;
		return je.getAsLong();
	}

	/*
	 * 获取float单精度浮点
	 * */
	public float getFloat(String id){
		JsonElement je=jo.get(id);
		if(je==null)return 0;
		return je.getAsFloat();
	}

	/*
	 * 获取double双精度浮点
	 * */
	public double getDouble(String id){
		JsonElement je=jo.get(id);
		if(je==null)return 0;
		return je.getAsDouble();
	}

	/*
	 * 获取String字符串
	 * */
	public String getString(String id){
		JsonElement je=jo.get(id);
		if(je==null||je instanceof JsonNull)return NULL;
		String str= je.getAsString();
		str=str.replaceAll("\"", "");
		str=str.replaceAll("'", "");
		str=str.replaceAll(";", "；");
		str=str.replaceAll(",", "，");
		return str;
	}

	/*
	 * 获取char字符
	 * */
	public double getCharacter(String id){
		JsonElement je=jo.get(id);
		if(je==null)return 0;
		return je.getAsCharacter();
	}

	/*
	 * 获取boolean值
	 * */
	public boolean getBoolean(String id){
		JsonElement je=jo.get(id);
		if(je==null||je instanceof JsonNull)return false;
		return je.getAsBoolean();
	}

	/*
	 * 获取JsonObject
	 * */
	public JsonObject getJsonObject(String id){
		JsonElement je=jo.get(id);
		if(je==null||je instanceof JsonNull)return null;
		return je.getAsJsonObject();
	}

	/*
	 * 获取JsonArray
	 * */
	public JsonArray getJsonArray(String id){
		JsonElement je=jo.get(id);
		if(je==null||je instanceof JsonNull)return null;
		return je.getAsJsonArray();
	}

	/*
	* 获取Json封装
	* */
	public JsonPack getJsonPack(String id){
		JsonElement je=jo.get(id);
		if(je==null)return null;
		return new JsonPack(je.getAsJsonObject());
	}

	/*
	 * 获取Json封装数组
	 * */
	public JsonPack[] getJsonPacks(String id){
		JsonArray ja=getJsonArray(id);
		if(ja==null)return NULL_PACKS;
		JsonPack[] jps=new JsonPack[ja.size()];
		for(int i=0;i<jps.length;i++){
			jps[i]=new JsonPack(ja.get(i).getAsJsonObject());
		}
		return jps;
	}

	/*
	 * 获取Json封装数组队列
	 * */
	public ArrayList<JsonPack> getJsonPackList(String id){
		JsonArray ja=getJsonArray(id);
		if(ja==null)return NULL_PACK_LIST;
		ArrayList<JsonPack> jpl=new ArrayList<>();
		for(JsonElement je : ja){
			jpl.add(new JsonPack(je.getAsJsonObject()));
		}
		return jpl;
	}

}
