package com.theironyard;

import com.theironyard.services.JobRepository;
import com.theironyard.services.MessageRepository;
import com.theironyard.services.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CrudSpringApplication.class)
@WebAppConfiguration
public class CrudSpringApplicationTests {

	@Autowired
	UserRepository users;

	@Autowired
	MessageRepository messages;

	@Autowired
	JobRepository jobs;

	@Autowired
	WebApplicationContext wap;

	MockMvc mockMvc;

	@Before
	public void before() {
		messages.deleteAll();
		jobs.deleteAll();
		users.deleteAll();
		mockMvc = MockMvcBuilders.webAppContextSetup(wap).build();
	}

	@Test
	public void testCreateAccount() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/create-account")
					.param("username", "testUsername")
					.param("password", "testPassword")
					.param("firstName", "testFirstName")
					.param("lastName", "testLastName")
					.param("location", "testLocation")
					.param("age", "25")
					.param("music", "testMusic")
					.param("vacation", "testVacation")
					.param("company", "testCompany")
					.param("position", "testPosition")
					.param("yearsWorked", "1")
		);

		assertTrue(users.count() == 1);
	}

//	@Test
//	public void testLogin() throws Exception {
//		mockMvc.perform(
//				MockMvcRequestBuilders.post("/login")
//					.param("username", "testUser")
//					.param("password", "testPass")
//		);
//	}


}
