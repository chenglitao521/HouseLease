package com.xiamo.common.utils;

import com.alibaba.fastjson.JSONArray;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

 /**
  * @date:2018/1/10 0010
  * @className:JsonUtils
  * @author:chenglitao
  * @description:Json解析工具类
  *
  */
public class JsonUtils {
	  private static final ObjectMapper objMapper = new ObjectMapper();

	  	public static ObjectMapper getObjectMapper(){
	  		return objMapper;
	  	}
	    public static <T> Object fromJson(String jsonAsString, Class<T> pojoClass)
	    throws JsonMappingException, JsonParseException, IOException {
	        return objMapper.readValue(jsonAsString, pojoClass);
	    }

	    public static <T> Object fromJson(Reader reader, Class<T> pojoClass)
	    throws JsonParseException, IOException
	    {
	        return objMapper.readValue(reader, pojoClass);
	    }

	    public static String toJson(Object pojo)
	    throws JsonMappingException, IOException {
	      
	        return objMapper.writeValueAsString(pojo);
	    }

	    public static void toJson(Object pojo, Writer writer)
	    throws JsonMappingException, IOException {
	    	objMapper.writeValue(writer, pojo);
	    }
	    
	    public static  List str2List(String content,Class c){

		    List list = (List) JSONArray.parseArray(content, c);
		    return list;
		}
	    
}
