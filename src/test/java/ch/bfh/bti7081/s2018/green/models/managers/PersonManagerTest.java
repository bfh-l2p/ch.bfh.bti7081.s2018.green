package ch.bfh.bti7081.s2018.green.models.managers;

import ch.bfh.bti7081.s2018.green.models.entities.Person;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PersonManagerTest {

	private List<Person> insertedPeople = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		addTestData();
	}

	@After
	public void tearDown() throws Exception {
		removeTestData();
	}

	@Test
	public void get() throws Exception {
		int testPersonId = getTestPersonId();
		PersonManager personManager = new PersonManager();
		Person person = personManager.get(testPersonId);
		Assert.assertEquals(testPersonId, person.getId());
	}

	@Test
	public void findAll() throws Exception {
		PersonManager personManager = new PersonManager();
		List<Person> personList = personManager.findAll();

		insertedPeople.forEach(p1 -> {
			Assert.assertTrue(personList.stream().anyMatch(p2 -> p1.getId() == p2.getId()));
		});
	}

	@Test
	public void add() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Date dobPerson = new Date(format.parse("14.10.1991").getTime());
		Person person = new Person("Martin", "Scheck", dobPerson, "Chutzenstrasse 27", "3007", "Bern",
				"martinscheck91@gmail.com", "0798340599");
		PersonManager personManager = new PersonManager();
		personManager.add(person);
		insertedPeople.add(person);

		Assert.assertEquals(person.getFirstName(), personManager.get(person.getId()).getFirstName());
	}

	@Test
	public void update() throws Exception {
		int testPersonId = getTestPersonId();
		String s = "Testing is fun";

		PersonManager personManager = new PersonManager();
		Person person = personManager.get(testPersonId);

		person.setFirstName(s);
		personManager.update(person);

		Person updatedPerson = personManager.get(testPersonId);
		Assert.assertEquals(s, updatedPerson.getFirstName());
	}

	@Test
	public void remove() throws Exception {
		PersonManager personManager = new PersonManager();
		Integer i = insertedPeople.size() - 1;
		Person person = insertedPeople.get(i);
		personManager.remove(person);
		List<Person> personList = personManager.findAll();
		Assert.assertTrue(personList.stream().noneMatch(p -> p.getId() == person.getId()));
		insertedPeople.remove(person);
	}

	/**
	 * Insert some test data
	 */
	private void addTestData() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Date dobPerson = new Date(format.parse("14.10.1991").getTime());
		Person person = new Person("Tobias", "Scheck", dobPerson, "Chutzenstrasse 28", "3007", "Bern",
				"martinscheck91@gmail.com", "0798340599");
		PersonManager personManager = new PersonManager();
		personManager.add(person);
		insertedPeople.add(person);
	}

	/**
	 * Remove all persons and people inserted during the test
	 */
	private void removeTestData() {
		PersonManager personManager = new PersonManager();
		insertedPeople.forEach(personManager::remove);
		insertedPeople = new ArrayList<>();
	}

	/**
	 * Get the id of the last person inserted
	 *
	 * @return id
	 */
	private int getTestPersonId() {
		return insertedPeople.get(insertedPeople.size() - 1).getId();
	}
}
