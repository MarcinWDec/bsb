package bsbapp.ejb.service;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateless;

import bsbapp.ejb.local.PersonDaoLocal;
import bsbapp.model.Person;
import bsbapp.model.filter.PersonFilter;
import bsbapp.remote.PersonServiceRemote;

@Stateless
public class PersonService implements PersonServiceRemote {

	@EJB
	PersonDaoLocal dao;

	public PersonService() {
	}

	public Person createPerson(Person per) {
		return dao.createPerson(per);
	}

	public void removePerson(int id) {
		dao.removePerson(id);
	}

	public Person findPerson(int id) {
		return dao.findPerson(id);
	}

	public Collection<Person> findAllPersons() {
		return dao.findAllPersons();
	}

	public void ping() {
	}

	@Remove
	public void remove() {
		System.out.println("removed");
	}

	public List<Person> findPersons(int startingAt, int maxPerPage) {
		return dao.findPersons(startingAt, maxPerPage);
	}

	public void create100Persons() {
		dao.create100Persons();
	}

	public int countPersonsTotal() {
		return dao.countPersonsTotal();
	}

	@Override
	public List<Person> findPersons(int startingAt, int maxPerPage,
			PersonFilter personFilter) {
		return dao.findPersons(startingAt, maxPerPage, personFilter);
	}

	@Override
	public int countPersonsTotal(PersonFilter personFilter) {
		return dao.countPersonsTotal(personFilter);
	}

	@Override
	public Person updatePerson(Person person) {
		return dao.updatePerson(person);
	}

}
