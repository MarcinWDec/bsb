package pl.bsb.web.mb;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.LazyDataModel;

import pl.bsb.web.util.PersonLazyList;

import bsbapp.model.Person;
import bsbapp.model.enums.GenderE;
import bsbapp.model.filter.PersonFilter;
import bsbapp.remote.PersonServiceRemote;



@SessionScoped
@ManagedBean
public class PersonListMB implements Serializable {

	@EJB
	private PersonServiceRemote personService;
	
	private static final long serialVersionUID = 1L;
	private LazyDataModel<Person> persons = null;
	private Person person;
	private String id;
	private String name;
	private Date dateFrom;
	private Date dateTo;
	private GenderE gender;



	public LazyDataModel<Person> getAllPersons() {
		if (persons == null) {
			PersonLazyList list = new PersonLazyList();
			list.setPersonService(personService);
			persons = list;
		}

		return persons;
	}
	
	public void search() {
		PersonLazyList list = new PersonLazyList();
		list.setPersonService(personService);
		PersonFilter personFilter = new PersonFilter();
		personFilter.setName(name);
		personFilter.setId(id);
		personFilter.setDateFrom(dateFrom);
		personFilter.setDateTo(dateTo);
		personFilter.setGender(gender);
		list.setPersonFilter(personFilter);
		persons = list;
		
	}
	
	public void removePerson(Person person){
		personService.removePerson(person.getId());
		search();
	}
	
	public void updatePerson(Person person){
		Person updatePerson = personService.updatePerson(person);
		this.person=updatePerson;
		search();
	}
	
	public void addNewPerson(){
		Person createdPerson = personService.createPerson(new Person());
		this.person=createdPerson;
		search();
	}
	

	public void clearSearch() {
		setId("");
		setName("");
		setGender(null);
		setDateFrom(null);
		setDateTo(null);
		PersonLazyList list = new PersonLazyList();
		list.setPersonService(personService);
		persons = list;
		
	}

	public Person getPerson() {
		if (person == null) {
			person = new Person();
		}

		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GenderE getGender() {
		return gender;
	}

	public void setGender(GenderE gender) {
		this.gender = gender;
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


}
