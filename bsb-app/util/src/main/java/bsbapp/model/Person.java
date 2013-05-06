package bsbapp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = "findPersonAll", query = "SELECT o FROM Person o"),
		@NamedQuery(name = "findPersonByName", query = "SELECT o FROM Person o WHERE o.name = :name") })
public class Person implements Serializable {
	static final long serialVersionUID = 2;

	private String name;
	private int id;
	private String password;
	private Date born;

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBorn() {
		return born;
	}

	public void setBorn(Date born) {
		this.born = born;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", id=" + id + ", password=" + password
				+ ", born=" + born + "]";
	}
	
	
}
