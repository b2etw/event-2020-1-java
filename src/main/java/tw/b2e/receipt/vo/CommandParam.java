package tw.b2e.receipt.vo;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class CommandParam {
	private String action;
	private Map<String, String> params = new HashMap<String, String>();
		
	public void putParam(String key, String value) {
		params.put(key, value);
	}
	
	public String getParam(String key) {
		return params.get(key);
	}
	
}
