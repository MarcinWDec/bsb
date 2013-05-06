package bsbapp.ejb.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class FilteredQueryParameters implements Serializable{

	private static final long serialVersionUID = 1L;
	private String queryStr;
	private Map<String,Object> params;
	public String getQueryStr() {
		return queryStr;
	}
	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}
	public Map<String,Object> getParams() {
		return params;
	}
	public void setParams(Map<String,Object> params) {
		this.params = params;
	}
	
	public void addParam(String name,Object value){
		if(params==null){
			params=new HashMap<String, Object>();
		}
		params.put(name, value);
	}

}
