//Jonathan Diep jjdiep@ucdavis.edu
//Jay Titterud jmtitterud@ucdavis.edu
// Jay's version was not updated so we just put everything into one

package edu.studyup.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import edu.studyup.entity.Event;
import edu.studyup.entity.Location;
import edu.studyup.entity.Student;
import edu.studyup.util.DataStorage;
import edu.studyup.util.StudyUpException;

class EventServiceImplTest {

	EventServiceImpl eventServiceImpl;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		eventServiceImpl = new EventServiceImpl();
		//Create Student
		Student student = new Student();
		student.setFirstName("John");
		student.setLastName("Doe");
		student.setEmail("JohnDoe@email.com");
		student.setId(1);
		
		//Create Event1
		Event event = new Event();
		event.setEventID(1);
		event.setDate(new Date());
		event.setName("Event 1");
		Location location = new Location(-122, 37);
		event.setLocation(location);
		List<Student> eventStudents = new ArrayList<>();
		eventStudents.add(student);
		event.setStudents(eventStudents);
		
		DataStorage.getEventData().put(event.getEventID(), event);
	}

	@AfterEach
	void tearDown() throws Exception {
		DataStorage.getEventData().clear();
	}

	@Test
	void testUpdateEventName_GoodCase() throws StudyUpException {
		int eventID = 1;
		eventServiceImpl.updateEventName(eventID, "Renamed Event 1");
		assertEquals("Renamed Event 1", DataStorage.getEventData().get(eventID).getName());
	}
	
	@Test
	void testUpdateEvent_WrongEventID_badCase() {
		int eventID = 3;
		Assertions.assertThrows(StudyUpException.class, () -> {
			eventServiceImpl.updateEventName(eventID, "Renamed Event 3");
		  });
	}
}
	
	/*@Test
	void testStringLimit() {
		int eventID = 3;
		Assertions.assertThrows(StudyUpException.class, () -> {
			eventServiceImpl.updateEventName(eventID, "This string is twnty");
			});
		
	}
	
	@Test
	void testUpdateEvent_StringExactlyTwenty() {
		// weird thing happens when string is exactly twenty characters
		int eventID = 1;
		eventServiceImpl.updateEventName(eventID, "This string is twnty");
		assertEquals("This string is twnty", DataStorage.getEventData().get(eventID).getName());
	}
	
	@Test
	void testUpdateEvent_StringCalled20() {
		int eventID = 1;
		eventServiceImpl.updateEventName(eventID, "20");
		assertEquals("20", DataStorage.getEventData().get(eventID).getName());
	}
	
	@Test
	void testGetActiveEvent_OneActiveEvent() {
		List<Event> activeEventsList = eventServiceImpl.getActiveEvents();
	}
	
	@Test 
	void testGetActiveEvent_NoAtiveEvent() {
		List<Event> activeEventsList = eventServiceImpl.getActiveEvents();
		assertEquals(null, activeEventsList.get(0));
	} 
	
	@Test
	void testGetPastEvent_noPastEvent() {
		List<Event> pastEventsList = eventServiceImpl.getPastEvents();
		pastEventsList.clear();
		assertEquals(null, pastEventsList.get(0));
	}
	
	@Test
	void testActiveEvent_PastDate() {
		Event event = new Event();
		event.setEventID(12345);
		Calendar past = Calendar.getInstance();
		past.set(Calendar.YEAR, 0001);
		past.set(Calendar.MONTH,Calendar.AUGUST);
		past.set(Calendar.DAY_OF_MONTH,01);
		Date current = past.getTime();
		event.setDate(current);
	}

	@Test
	void testGetPassEvent_addPastEvent() {
		Date date = new Date();
		Event event = new Event();
		event.setDate(date);
		eventServiceImpl.getPastEvents();
	}	 
	
	
	@Test
	void testDeleteEvent() {
		int eventID = 1;
		eventServiceImpl.getActiveEvents();
		eventServiceImpl.deleteEvent(eventID);		
	}
	
	@Test
	void testAddStudents_StringID() throws StudyUpException {
		List<Student> eventStudents = new ArrayList<>();
		Student student = new Student();
		eventStudents.add(student);
		Event event = new Event();
		String eventID = "hello";
		event.setStudents(eventStudents); 
		eventServiceImpl.addStudentToEvent(student, eventID);
		assertEquals( student, eventStudents.get(0));
	}
	
	@Test
	void testAddStudents_OneStudents() throws StudyUpException {
		List<Student> eventStudents = new ArrayList<>();
		Student student = new Student();
		eventStudents.add(student);
		Event event = new Event();
		int eventID = 1;
		event.setStudents(eventStudents); 
		eventServiceImpl.addStudentToEvent(student, eventID);
		assertEquals( student, eventStudents.get(0));
		assertTrue(eventStudents.size() <= 2);
	}
	
	@Test
	void testAddStudents_TwoStudents() throws StudyUpException {
		List<Student> eventStudents = new ArrayList<>();
		Student student1 = new Student();
		Student student2 = new Student();
		eventStudents.add(student1);
		eventStudents.add(student2);
		Event event = new Event();
		int eventID = 1;
		event.setStudents(eventStudents); 
		eventServiceImpl.addStudentToEvent(student1, eventID);
		eventServiceImpl.addStudentToEvent(student2, eventID);
		assertEquals( student1, eventStudents.get(0));
		assertEquals( student2, eventStudents.get(1));
		assertTrue(eventStudents.size() <= 2);
	}
	
	@Test
	void testAddStudents_ThreeStudents() throws StudyUpException {
		List<Student> eventStudents = new ArrayList<>();
		Student student1 = new Student();
		Student student2 = new Student();
		Student student3 = new Student();
		eventStudents.add(student1);
		eventStudents.add(student2);
		Event event = new Event();
		int eventID = 1;
		event.setStudents(eventStudents); 
		eventServiceImpl.addStudentToEvent(student1, eventID);
		eventServiceImpl.addStudentToEvent(student2, eventID);
		assertEquals( student1, eventStudents.get(0));
		assertEquals( student2, eventStudents.get(1));
		// There is a third student where there should not be
		Assertions.assertThrows(StudyUpException.class, () -> {
			eventServiceImpl.addStudentToEvent(student3, eventID);
		});
	}

	@Test
	void testAddStudents_NoEvent() throws StudyUpException {
		Student student = new Student();
		List<Student> eventStudents = new ArrayList<>();
		eventStudents.add(student);
		int eventID = 1;
		eventServiceImpl.addStudentToEvent(student, eventID);
		assertEquals( student, eventStudents.get(0));
	}
	
	@Test
	void testAddStudents_NoStudents() throws StudyUpException {
		Student student = null;
		List<Student> eventStudents = new ArrayList<>();
		eventStudents.add(student);
		int eventID = 1;
		eventServiceImpl.addStudentToEvent(student, eventID);
		assertEquals( student, eventStudents.get(0));
	}

	@Test
	void checkActiveEventsForPastDate() throws StudyUpException {
		//second bug compares myevent2, with older date to show it should fail
		int eventID = 1;
		Event past = DataStorage.getEventData().get(eventID);
		past.setDate(new Date(100));
		past.setName("Past");
		DataStorage.getEventData().put(past.getEventID(), past);
		List<Event> activeEvents = eventServiceImpl.getActiveEvents();
		assertFalse(activeEvents.contains(past));	
	}
}
	*/