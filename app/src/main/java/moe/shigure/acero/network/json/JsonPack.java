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

	/**
	 * JsonObject源
	 */
	JsonObject jo;

	/**
	 * 创建被封装的JsonObject
	 */
	public JsonPack(JsonObject jo){
		this.jo=jo;
	}

	/**
	 * 重新设置封装内的JsonObject
	 */
	public void reset(JsonObject jo){
		this.jo=jo;
	}

	/**
	 * 获取封装内的JsonObject
	 */
	public JsonObject getJson(){
		return jo;
	}

	/**
	 * key是否拥有value
	 */
	public boolean isHasValue(String id){
		JsonElement je=jo.get(id);
		return je != null && !(je instanceof JsonNull);
	}

	/**
	 * 获取byte字节型
	 */
	public byte getByte(String id){
		JsonElement je=jo.get(id);
		if(je==null||je instanceof JsonNull)return 0;
		return je.getAsByte();
	}

	/**
	 * 获取short短整数
	 */
	public short getShort(String id){
		JsonElement je=jo.get(id);
		if(je==null||je instanceof JsonNull)return 0;
		return je.getAsShort();
	}

	/**
	 * 获取int整数
	 */
	public int getInt(String id){
		JsonElement je=jo.get(id);
		if(je==null||je instanceof JsonNull)return 0;
		return je.getAsInt();
	}

	/**
	 * 获取long长整数
	 */
	public long getLong(String id){
		JsonElement je=jo.get(id);
		if(je==null||je instanceof JsonNull)return 0;
		return je.getAsLong();
	}

	/**
	 * 获取float单精度浮点
	 */
	public float getFloat(String id){
		JsonElement je=jo.get(id);
		if(je==null||je instanceof JsonNull)return 0;
		return je.getAsFloat();
	}

	/**
	 * 获取double双精度浮点
	 */
	public double getDouble(String id){
		JsonElement je=jo.get(id);
		if(je==null||je instanceof JsonNull)return 0;
		return je.getAsDouble();
	}

	/**
	 * 获取String字符串
	 */
	public String getString(String id){
		JsonElement je=jo.get(id);
		if(je==null||je instanceof JsonNull)return "";
		String str= je.getAsString();
		str=str.replaceAll("\"", "");
		str=str.replaceAll("'", "");
		str=str.replaceAll(";", "；");
		str=str.replaceAll(",", "，");
		return str;
	}

	/**
	 * 获取char字符
	 */
	public double getCharacter(String id){
		JsonElement je=jo.get(id);
		if(je==null)return 0;
		return je.getAsCharacter();
	}

	/**
	 * 获取boolean值
	 */
	public boolean getBoolean(String id){
		JsonElement je=jo.get(id);
		if(je==null||je instanceof JsonNull)return false;
		return je.getAsBoolean();
	}

	/**
	 * 获取JsonObject
	 */
	public JsonObject getJsonObject(String id){
		JsonElement je=jo.get(id);
		if(je==null||je instanceof JsonNull)return null;
		return je.getAsJsonObject();
	}

	/**
	 * 获取JsonArray
	 */
	public JsonArray getJsonArray(String id){
		JsonElement je=jo.get(id);
		if(je==null||je instanceof JsonNull)return null;
		return je.getAsJsonArray();
	}

	/**
	 * 获取Json封装
	 */
	public JsonPack getJsonPack(String id){
		JsonElement je=jo.get(id);
		if(je==null||je instanceof JsonNull)return null;
		return new JsonPack(je.getAsJsonObject());
	}

	/**
	 * 获取Json封装数组
	 */
	public JsonPack[] getJsonPacks(String id){
		JsonArray ja=getJsonArray(id);
		if(ja==null)return new JsonPack[0];
		JsonPack[] jps=new JsonPack[ja.size()];
		for(int i=0;i<jps.length;i++){
			jps[i]=new JsonPack(ja.get(i).getAsJsonObject());
		}
		return jps;
	}

	/**
	 * 获取Json封装数组列表
	 */
	public ArrayList<JsonPack> getJsonPackList(String id){
		JsonArray ja=getJsonArray(id);
		ArrayList<JsonPack> jpl=new ArrayList<>();
		if(ja==null)return jpl;
		for(JsonElement je : ja){
			jpl.add(new JsonPack(je.getAsJsonObject()));
		}
		return jpl;
	}

	/**
	 * 获取int数组
	 */
	public int[] getInts(String id){
		JsonArray ja = getJsonArray(id);
		if(ja==null)return new int[0];
		int[] ints = new int[ja.size()];
		for(int i=0;i<ja.size();i++){
			ints[i]=ja.get(i).getAsInt();
		}
		return ints;
	}

	/**
	 * 获取int数组列表
	 */
	public ArrayList<Integer> getIntList(String id){
		JsonArray ja = getJsonArray(id);
		ArrayList<Integer> intList = new ArrayList<>();
		if(ja==null)return intList;
		for (JsonElement model : ja){
			intList.add(model.getAsInt());
		}
		return intList;
	}

	/**
	 * 获取long数组
	 */
	public long[] getLongs(String id){
		JsonArray ja = getJsonArray(id);
		if(ja==null)return new long[0];
		long[] longs = new long[ja.size()];
		for(int i=0;i<ja.size();i++){
			longs[i]=ja.get(i).getAsLong();
		}
		return longs;
	}

	/**
	 * 获取long数组列表
	 */
	public ArrayList<Long> getLongList(String id){
		JsonArray ja = getJsonArray(id);
		ArrayList<Long> longList = new ArrayList<>();
		if(ja==null)return longList;
		for (JsonElement model : ja){
			longList.add(model.getAsLong());
		}
		return longList;
	}

	/**
	 * 获取string数组
	 */
	public String[] getStrings(String id){
		JsonArray ja = getJsonArray(id);
		if(ja==null)return new String[0];
		String[] strings = new String[ja.size()];
		for(int i=0;i<ja.size();i++){
			strings[i]=ja.get(i).getAsString();
		}
		return strings;
	}

	/**
	 * 获取string数组列表
	 */
	public ArrayList<String> getStringList(String id){
		JsonArray ja = getJsonArray(id);
		ArrayList<String> stringList = new ArrayList<>();
		if(ja==null)return stringList;
		for (JsonElement model : ja){
			stringList.add(model.getAsString());
		}
		return stringList;
	}

}
