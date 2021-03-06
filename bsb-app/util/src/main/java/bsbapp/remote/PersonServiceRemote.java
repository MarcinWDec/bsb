package bsbapp.remote;

import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;

import bsbapp.model.Person;
import bsbapp.model.filter.PersonFilter;

@Remote
public interface PersonServiceRemote{
  public void ping();  
  public Person createPerson(Person per) ;
  public Person updatePerson(Person per) ;
  public void removePerson(int id);
  public Person findPerson(int id);
  public Collection<Person> findAllPersons() ; 
  public List<Person> findPersons(int startingAt, int maxPerPage);
  public void create100Persons();
  public int countPersonsTotal() ;
  public List<Person> findPersons(int startingAt, int maxPerPage,PersonFilter personFilter);
  public int countPersonsTotal(PersonFilter personFilter);

}

