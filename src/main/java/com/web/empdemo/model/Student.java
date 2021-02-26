package com.web.empdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Student
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long	studentID;

	@NotEmpty(message = "Enter Student Name")
	@Column(name = "studentName")
	private String	studentName;

	@NotEmpty(message = "Enter City")
	@Column(name = "city")
	private String	city;

	@NotEmpty(message = "Select Gender")
	@Column(name = "gender")
	private String	gender;

	public long getStudentID()
	{
		return studentID;
	}
	public void setStudentID(long studentID)
	{
		this.studentID = studentID;
	}
	public String getStudentName()
	{
		return studentName;
	}
	public void setStudentName(String studentName)
	{
		this.studentName = studentName;
	}
	public String getCity()
	{
		return city;
	}
	public void setCity(String city)
	{
		this.city = city;
	}
	public String getGender()
	{
		return gender;
	}
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	public Student(long studentID, String studentName, String city, String gender)
	{
		super();
		this.studentID = studentID;
		this.studentName = studentName;
		this.city = city;
		this.gender = gender;
	}
	public Student()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString()
	{
		return "Human [studentID=" + studentID + ", studentName=" + studentName + ", city=" + city + ", Gender=" + gender + "]";
	}
}
