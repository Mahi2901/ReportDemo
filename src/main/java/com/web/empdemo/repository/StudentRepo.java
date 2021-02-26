package com.web.empdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.empdemo.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long>
{
}
