package pl.bsb.web.util;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import org.primefaces.model.LazyDataModel;

import bsbapp.model.Person;
import bsbapp.model.filter.PersonFilter;
import bsbapp.remote.PersonServiceRemote;

public class PersonLazyList extends LazyDataModel<Person> {

	private static final long serialVersionUID = 1L;

	private List<Person> persons;
	private PersonFilter personFilter;



	@EJB
	private PersonServiceRemote personService;

	@Override	
	public List<Person> load(int startingAt, int maxPerPage, String sortField,
			org.primefaces.model.SortOrder sortOrder, Map<String, String> filters) {
            
            if(isFilter()){
            	persons = personService.findPersons(startingAt, maxPerPage,personFilter);
            }else{
			    persons = personService.findPersons(startingAt, maxPerPage);
            }
            
			if (!isFilter() &&  (persons == null || persons.isEmpty())) {
				personService.create100Persons();
				persons = personService.findPersons(startingAt, maxPerPage);
			}



		if (getRowCount() <= 0) {
            if(isFilter()){
            	setRowCount(personService.countPersonsTotal(personFilter));
            }else{
			  setRowCount(personService.countPersonsTotal());
            }
		}

		setPageSize(maxPerPage);

		return persons;
	}

	private boolean isFilter() {
		boolean isFilter = personFilter!=null && personFilter.isNotEmpty();
	    return isFilter;
	}

	public PersonServiceRemote getPersonService() {
		return personService;
	}

	public void setPersonService(PersonServiceRemote personService) {
		this.personService = personService;
	}

	@Override
	public Object getRowKey(Person person) {
		return person.getId();
	}

	@Override
	public Person getRowData(String personId) {
		Integer id = Integer.valueOf(personId);

		for (Person person : persons) {
			if (id.equals(person.getId())) {
				return person;
			}
		}

		return null;
	}

	public PersonFilter getPersonFilter() {
		return personFilter;
	}

	public void setPersonFilter(PersonFilter personFilter) {
		this.personFilter = personFilter;
	}
	
	@Override
	public void setRowIndex(int rowIndex) {
	       if (getPageSize() == 0) {
	            rowIndex = -1;
	        }
	        super.setRowIndex(rowIndex);
	}


}
