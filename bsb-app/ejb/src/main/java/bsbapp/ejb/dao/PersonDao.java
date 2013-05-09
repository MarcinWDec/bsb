package bsbapp.ejb.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Remove;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bsbapp.ejb.local.PersonDaoLocal;
import bsbapp.model.Person;
import bsbapp.model.enums.GenderE;
import bsbapp.model.filter.PersonFilter;
import bsbapp.util.Util;

@Stateless
public class PersonDao implements PersonDaoLocal {
	@PersistenceContext
	EntityManager em;

	public PersonDao() {
	}

	public Person createPerson(Person per) {
		em.persist(per);
		em.flush();
		return per;
	}

	public void removePerson(int id) {
		Person per = findPerson(id);
		if (per != null) {
			em.remove(per);
		}
	}

	public Person findPerson(int id) {
		return em.find(Person.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Person> findAllPersons() {
		Query query = em.createQuery("SELECT p FROM Person p");
		Collection<Person> resultList = (Collection<Person>) query
				.getResultList();
		return resultList;
	}

	public void ping() {
	}

	@Remove
	public void remove() {
		System.out.println("removed");
	}

	@SuppressWarnings("unchecked")
	public List<Person> findPersons(int startingAt, int maxPerPage) {

		Query query = em.createQuery("select p from Person p");
		query.setFirstResult(startingAt);
		query.setMaxResults(maxPerPage);

		return query.getResultList();
	}

	public void create100Persons() {

		Person person;

		for (int x = 0; x < 100; x++) {
			person = new Person();
			person.setName("Mr: " + x);
			person.setGender(x%2==0?GenderE.FEMALE:GenderE.MALE);
			em.persist(person);
		}

		em.flush();
	}

	public int countPersonsTotal() {
		Query query = em.createQuery("select COUNT(p) from Person p");
		Number result = (Number) query.getSingleResult();
		return result.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> findPersons(int startingAt, int maxPerPage,
			PersonFilter personFilter) {
		FilteredQueryParameters queryParam = createQueryParams(
				"select p from Person p ", personFilter);
		Query query = prepareQuery(queryParam);
		query.setFirstResult(startingAt);
		query.setMaxResults(maxPerPage);

		return query.getResultList();
	}

	@Override
	public int countPersonsTotal(PersonFilter personFilter) {
		FilteredQueryParameters queryParam = createQueryParams(
				"select COUNT(p) from Person p ", personFilter);
		Query query = prepareQuery(queryParam);
		Number result = (Number) query.getSingleResult();
		return result.intValue();
	}

	private Query prepareQuery(FilteredQueryParameters queryParam) {
		Query query = em.createQuery(queryParam.getQueryStr());
		Map<String, Object> params = queryParam.getParams();
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			Object value = params.get(key);
			query.setParameter(key, value);
		}
		return query;
	}

	private FilteredQueryParameters createQueryParams(String queryStr,
			PersonFilter personFilter) {
		
		System.out.println("createQueryParams: "+personFilter);

		FilteredQueryParameters params = new FilteredQueryParameters();

		StringBuffer strB = new StringBuffer(queryStr);

		String nameStr = personFilter.getName();
		String idStr = personFilter.getId();
		Date dateFrom = personFilter.getDateFrom();
		Date dateTo = personFilter.getDateTo();
		GenderE gender = personFilter.getGender();

		if (personFilter != null
				&& personFilter.isNotEmpty()) {
			strB.append(" where 1=1 ");

			if (!Util.isBlank(nameStr)) {
				strB.append(" and name like :name ");
				params.addParam("name", "%"+nameStr.trim()+"%");
			}

			if (!Util.isBlank(idStr) && Util.isNumeric(idStr)) {
				strB.append(" and id = :id ");
				params.addParam("id", Integer.parseInt(idStr));
			}
			
			if (!Util.isNull(dateFrom)) {
				strB.append(" and born >=:dateFrom ");
				params.addParam("dateFrom", dateFrom);
			}
			
			if (!Util.isNull(dateTo)) {
				strB.append(" and born <=:dateTo ");
				params.addParam("dateTo", dateTo);
			}

			if (!Util.isNull(gender)) {
				strB.append(" and gender =:gender ");
				params.addParam("gender", gender);
			}
		}
		
		System.out.println("createQueryParams: "+strB.toString());
				
		params.setQueryStr(strB.toString());
		return params;

	}

	@Override
	public Person updatePerson(Person person) {
		if (person != null) {
			Person merged = em.merge(person);
			return merged;
		}
		return null;
	}

}
