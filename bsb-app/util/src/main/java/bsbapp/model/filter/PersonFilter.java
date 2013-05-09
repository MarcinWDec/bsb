package bsbapp.model.filter;

import java.io.Serializable;
import java.util.Date;

import bsbapp.model.enums.GenderE;
import bsbapp.util.Util;


public class PersonFilter implements Serializable {

	static final long serialVersionUID = 2;

	private String name;
	private String id;
	private Date dateFrom;
	private Date dateTo;
	private GenderE gender;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	public GenderE getGender() {
		return gender;
	}
	public void setGender(GenderE gender) {
		this.gender = gender;
	}
	
	public boolean isNotEmpty(){
		return !Util.isBlank(id) 
				|| !Util.isBlank(name) 
				|| !Util.isNull(dateFrom) 
				|| !Util.isNull(dateTo) 
				|| !Util.isNull(gender);
	}

	
	@Override
	public String toString() {
		return "PersonFilter [name=" + name + ", id=" + id + ", dateFrom="
				+ dateFrom + ", dateTo=" + dateTo + ", gender=" + gender + "]";
	}

}
