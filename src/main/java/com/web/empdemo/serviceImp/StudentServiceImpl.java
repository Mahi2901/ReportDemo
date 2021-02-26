package com.web.empdemo.serviceImp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.web.empdemo.model.Student;
import com.web.empdemo.repository.StudentRepo;
import com.web.empdemo.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService
{

	@Autowired
	private StudentRepo		studentRepo;
	@Autowired
	private EntityManager	entityManager;

	@Override
	public void saveStudent(Student student)
	{
		studentRepo.save(student);
	}

	@Override
	public List<Student> getAllStudent()
	{
		List<Student> list = new ArrayList<>();
		list = studentRepo.findAll();
		return list.isEmpty() ? null : list;
	}

	@Override
	public List<Student> getAllStudentByDescOrder()
	{
		List<Student> list = new ArrayList<>();
		try
		{
			Session session = entityManager.unwrap(Session.class);
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
			Root<Student> root = criteriaQuery.from(Student.class);
			criteriaBuilder.selectCase(root);
			criteriaQuery.orderBy(criteriaBuilder.desc(root.get("studentId")));
			list = session.createQuery(criteriaQuery).list();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return list.isEmpty() ? null : list;
	}

	@Override
	public List<Student> getPaginationStudentData(int pageNo, int pageSize, String sortBy)
	{
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Direction.DESC, sortBy));
		Page<Student> allStudentEntity = studentRepo.findAll(pageable);
		return allStudentEntity.hasContent() ? allStudentEntity.getContent() : null;
	}

	@Override
	public Student getStudentById(long studentId)
	{
		return studentRepo.findById(studentId).get();
	}

	@Override
	public void deleteStudent(Student student)
	{
		studentRepo.delete(student);

	}

	@Override
	public Page<Student> getPaginateStudentData(int pageNo, int pageSize)
	{
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Direction.DESC, "studentID"));
		return studentRepo.findAll(pageable);
	}

	@Override
	public Page<Student> getPaginateSortedStudentData(int pageNo, int pageSize, String sorted)
	{
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Direction.DESC, sorted));
		return studentRepo.findAll(pageable);
	}
}
