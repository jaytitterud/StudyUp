package edu.studyup.service;

import edu.studyup.entity.Student;

/**
 * Holds all the CRUD services for {@link Student} class.
 * 
 * @author Shivani
 *
 */
public interface StudentService {

	/**
	 * Creates the student
	 * 
	 * @param student The {@link Student} to be added. There are no restriction on a
	 *                student model.
	 * @return The created {@code Student}.
	 */
	public Student createStudent(Student student);

	/**
	 * Retrieves the student
	 * 
	 * @param studentID Unique Id of the {@link Student} to retrieve.
	 * @return The {@code Student} with the specified Id.
	 */
	public Student getStudent(int studentID);

	/**
	 * Deleted the student
	 * 
	 * @param studentID Unique Id of the {@link Student} to be deleted.
	 * @return Nothing.
	 */
	public void deleteStudent(int studentID);

}