package de.vogella.task.dao.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import de.vogella.task.dao.MockDao;
import de.vogella.task.model.ITask;

public class MockDaoTest {

	@Test
	public void addAndDelete() {
		MockDao instance = MockDao.INSTANCE;
		List<ITask> tasks = instance.getTasks();
		assertTrue(tasks.size() == 2);
		tasks.remove(0);
		assertTrue(tasks.size() == 1);
	}
}
