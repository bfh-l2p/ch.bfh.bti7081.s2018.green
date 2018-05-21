package ch.bfh.bti7081.s2018.green.models.managers;

import ch.bfh.bti7081.s2018.green.models.entities.Staff;
import ch.bfh.bti7081.s2018.green.models.enumerations.StaffType;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StaffManagerTest {

	private List<Staff> insertedStaff = new ArrayList<>();

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
		int testStaffId = getTestStaffId();
		StaffManager staffManager = new StaffManager(Staff.class);
		Staff staff = staffManager.get(testStaffId);
		Assert.assertEquals(testStaffId, staff.getId());
	}

	@Test
	public void findAll() throws Exception {
		StaffManager staffManager = new StaffManager(Staff.class);
		List<Staff> staffList = staffManager.findAll();

		insertedStaff.forEach(p1 -> {
			Assert.assertTrue(staffList.stream().anyMatch(p2 -> p1.getId() == p2.getId()));
		});
	}

	@Test
	public void add() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Date dobStaff = new Date(format.parse("14.10.1991").getTime());
		Staff staff = new Staff("Martin", "Scheck", dobStaff, "Chutzenstrasse 27", "3007", "Bern",
				"martinscheck91@gmail.com", "0798340599", StaffType.PSYCHOLOGIST);
		StaffManager staffManager = new StaffManager(Staff.class);
		staffManager.add(staff);
		insertedStaff.add(staff);

		Assert.assertEquals(staff.getFirstName(), staffManager.get(staff.getId()).getFirstName());
	}

	@Test
	public void update() throws Exception {
		int testStaffId = getTestStaffId();
		String s = "Testing is fun";

		StaffManager staffManager = new StaffManager(Staff.class);
		Staff staff = staffManager.get(testStaffId);

		staff.setFirstName(s);
		staffManager.update(staff);

		Staff updatedStaff = staffManager.get(testStaffId);
		Assert.assertEquals(s, updatedStaff.getFirstName());
	}

	@Test
	public void remove() throws Exception {
		StaffManager staffManager = new StaffManager(Staff.class);
		Integer i = insertedStaff.size() - 1;
		Staff staff = insertedStaff.get(i);
		staffManager.remove(staff);
		List<Staff> staffList = staffManager.findAll();
		Assert.assertTrue(staffList.stream().noneMatch(p -> p.getId() == staff.getId()));
		insertedStaff.remove(staff);
	}

	@Test
	public void findBy() throws Exception {
		StaffManager staffManager = new StaffManager(Staff.class);
		List<Staff> staffList = staffManager.findBy(StaffType.PSYCHIATRIST);

		staffList.forEach(p -> Assert.assertTrue(p.getType() == StaffType.PSYCHIATRIST));
	}

	/**
	 * Insert some test data
	 */
	private void addTestData() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Date dobStaff = new Date(format.parse("14.10.1991").getTime());
		Staff staff = new Staff("Tobias", "Scheck", dobStaff, "Chutzenstrasse 28", "3007", "Bern",
				"martinscheck91@gmail.com", "0798340599", StaffType.PSYCHIATRIST);
		StaffManager staffManager = new StaffManager(Staff.class);
		staffManager.add(staff);
		insertedStaff.add(staff);
	}

	/**
	 * Remove all staffs and people inserted during the test
	 */
	private void removeTestData() {
		StaffManager staffManager = new StaffManager(Staff.class);
		insertedStaff.forEach(staffManager::remove);
		insertedStaff = new ArrayList<>();
	}

	/**
	 * Get the id of the last staff inserted
	 *
	 * @return id
	 */
	private int getTestStaffId() {
		return insertedStaff.get(insertedStaff.size() - 1).getId();
	}
}
