package com.web.empdemo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.web.empdemo.model.Student;

public interface StudentService
{

	public void saveStudent(Student student);

	public List<Student> getAllStudent();

	public List<Student> getAllStudentByDescOrder();

	public List<Student> getPaginationStudentData(int pageNo, int pageSize, String sortBy);

	public Student getStudentById(long studentID);

	public void deleteStudent(Student student);

	public Page<Student> getPaginateStudentData(int pageNo, int pageSize);

	public Page<Student> getPaginateSortedStudentData(int pageNo, int pageSize, String sorted);
}
