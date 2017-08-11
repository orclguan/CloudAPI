package com.oracle.citiccloud.api;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper; 

public class JacksonUtil {
	private final static ObjectMapper objectMapper = new ObjectMapper();  
	  
    static {  
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);  
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);  
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);  
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);  
        objectMapper.configure(JsonParser.Feature.INTERN_FIELD_NAMES, true);  
        objectMapper.configure(JsonParser.Feature.CANONICALIZE_FIELD_NAMES, true);  
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);  
    }  
  
    public static String encode(Object obj) {  
        try {  
            return objectMapper.writeValueAsString(obj);  
        } catch (JsonGenerationException e) {  
            e.printStackTrace(); //$NON-NLS-1$  
        } catch (JsonMappingException e) {  
            e.printStackTrace(); //$NON-NLS-1$  
        } catch (IOException e) {   
            e.printStackTrace(); //$NON-NLS-1$  
        }  
        return null;  
    }  
  
    /** 
     * 将json string反序列化成对象 
     * 
     * @param json 
     * @param valueType 
     * @return 
     */  
    public static <T> T decode(String json, Class<T> valueType) {  
        try {  
            return objectMapper.readValue(json, valueType);  
        } catch (JsonParseException e) {  
        	e.printStackTrace();  
        } catch (JsonMappingException e) {  
        	e.printStackTrace(); 
        } catch (IOException e) {  
        	e.printStackTrace();  
        }  
        return null;  
    }  
}
