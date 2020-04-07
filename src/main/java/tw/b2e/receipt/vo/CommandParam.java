package tw.b2e.receipt.vo;

import java.util.HashMap;
import java.util.Map;

public class CommandParam {
	private String action;
	private Map<String, String> params = new HashMap<String, String>();
		
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	
	public void putParam(String key, String value) {
		params.put(key, value);
	}
	
	public String getParam(String key) {
		return params.get(key);
	}
	
}
