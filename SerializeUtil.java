package com.cpiaoju.utils;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;


/**
 * 
 * 类描述：PHP序列化和反序列化工具
 * @author linmengmeng
 * 创建时间：2019年1月15日 下午4:06:58
 */
public class SerializeUtil {

	/**
	 * 字符串序列化
	 * @Title: serialize
	 * @Description: 字符串序列化
	 * @param cartIds
	 * @throws
	 * @return String    
	 */
	@SuppressWarnings("finally")
	public static String serialize(String[] cartIds) {
		String str = "";
		Map<String, Object> mapImage = new HashMap<String, Object>();
		Map<Integer, String> fileNameList = new HashMap<Integer, String>();
		try {
			for (int i = 0; i < cartIds.length; i++) {
				fileNameList.put(i + 1, cartIds[i]);
			}
			mapImage.put("buyer", fileNameList);
			byte[] a = PHPSerializer.serialize(mapImage);
			str = new String(a, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			return str;
		}
	}
	
	/**
	 * 将字符串反序列
	 * @Title: unserialize
	 * @Description: 将字符串反序列
	 * @param content
	 * @return  
	 * @throws
	 * @return String    
	 */
	@SuppressWarnings({ "finally", "unchecked" })	
	public static String unserialize(String content){
		String string = null;
		if (StringUtils.isEmpty(content)) {
			return string;
		}
		try {
			HashMap<String, Map<Integer, String>> hashMap =  (HashMap<String, Map<Integer, String>>) PHPSerializer.unserialize(content.getBytes());
			Map<Integer, String> map = hashMap.get("buyer");
			Collection<String> values = map.values();
			String[] strings = values.toArray(new String[values.size()]);
			string = StringUtils.join(strings, ',');
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}finally {
			return string;
		}
	}

/*	public static void main(String[] args) {
		//String[] cartIds = {"06008816930683722.jpg", "06008816930681525.jpg"};
		String cartIds = "a:3:{s:1:\"b\";a:2:{i:0;s:3:\"4.1\";i:1;s:2:\"60\";}s:1:\"m\";a:2:{i:0;s:4:\"4.16\";i:1;s:2:\"60\";}s:1:\"s\";a:2:{i:0;s:4:\"4.15\";i:1;s:2:\"60\";}}";
		String serialize = serialize(cartIds);
		System.out.println(serialize);
		String unserialize = unserialize(cartIds);
		System.out.println(unserialize);
		String unserialize = unserialize("N;");
		System.out.println(unserialize);
	}*/
	public static void main(String[] args) {
/*		String[] cartIds = {"06008816930683722.jpg", "06008816930681525.jpg"};
		byte[] serialize = PHPSerializer.serialize(cartIds);*/
		String str = "a:3:{s:1:\"b\";a:2:{i:0;s:3:\"4.1\";i:1;s:2:\"60\";}s:1:\"m\";a:2:{i:0;s:4:\"4.16\";i:1;s:2:\"60\";}s:1:\"s\";a:2:{i:0;s:4:\"4.15\";i:1;s:2:\"60\";}}";
		str = "a:3:{s:1:\"b\";a:2:{i:0;s:4:\"4.92\";i:1;s:2:\"50\";}s:1:\"m\";a:2:{i:0;s:4:\"4.93\";i:1;s:2:\"50\";}s:1:\"s\";a:2:{i:0;s:4:\"4.93\";i:1;s:3:\"100\";}}";
		//String serializeString = new String(serialize);
		//System.out.println(serializeString);
		try {
			try {
				//Object unserialize = PHPSerializer.unserialize(serializeString.getBytes("utf-8"));
				@SuppressWarnings("unchecked")
				Map unserialize = (Map) PHPSerializer.unserialize(str.getBytes("utf-8"));
				System.out.println(unserialize);
				String jsonString = JSON.toJSONString(unserialize);
				System.out.println(jsonString);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
