package com.web.empdemo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.empdemo.model.Student;
import com.web.empdemo.serviceImp.StudentServiceImpl;
import com.web.empdemo.utils.JasperExporter;

@Controller
public class Home
{
	HashMap						jasperParameter;

	@Autowired
	private StudentServiceImpl	studentService;
	@Autowired
	private MessageSource		messageSource;
	@Value("${default.page.size}")
	private int					pageSize;

	@RequestMapping(value = { "", "/", "index" })
	public String index1(Model model)
	{
		return findPaginated(1,model);
	}
	
	@GetMapping(value = { "/{pageNo}" })
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model)
	{
		try
		{
			Page<Student> page = studentService.getPaginateStudentData(pageNo, pageSize);
			List<Student> listStudent = page.hasContent() ? page.getContent() : null;
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalItems", page.getTotalElements());
			model.addAttribute("listStudent", listStudent);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return "index";
	}
	@RequestMapping("/report")
	@ResponseBody
	public String report(@RequestParam("id1") long id)
	{

		return "index" + id;
	}

	@RequestMapping("/report/pdf")
	@ResponseBody
	public void reportpdf(@RequestParam("id1") long id, HttpServletRequest request, HttpServletResponse response)
	{
		jasperParameter = new HashMap();
		jasperParameter.put("studentID", id);
		JasperExporter jasperExporter = new JasperExporter();
		String Path = "";
		Path = request.getSession().getServletContext().getRealPath("/") + "WEB-INF" + System.getProperty("file.separator") + "jsp" + System.getProperty("file.separator") + "Report";
		System.out.println(Path);
		try
		{
			jasperExporter.jasperExporterPDF(jasperParameter, Path + System.getProperty("file.separator") + "Report.jrxml", "Report", response);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@GetMapping(value = { "/showaddStudent" })
	public ModelAndView showAddStudentPage(Model model)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("student", new Student());
		mv.addObject("operation", "add");
		mv.setViewName("addStudent");
		return mv;
	}

	@GetMapping(value = { "/showReport" })
	public ModelAndView showReportPage(Model model)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("listStudent", studentService.getAllStudent());
		mv.setViewName("report");
		return mv;
	}
	
	@PostMapping(value = { "/addStudent" })
	public String addStudent(@ModelAttribute @Valid Student student, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest req)
	{
		boolean success = false;
		String errMsg = "";
		String opeartion = req.getParameter("operation") == null ? "" : req.getParameter("operation");
		try
		{
			if (bindingResult.hasErrors())
			{
				return "addStudent";
			}
			studentService.saveStudent(student);
			success = true;
		}
		catch (Exception ex)
		{
			errMsg = ex.getMessage();
			ex.printStackTrace();
		}
		if (success)
		{
			redirectAttributes.addFlashAttribute("successMsg", !opeartion.isEmpty() && opeartion.equalsIgnoreCase("add") ? messageSource.getMessage("SuccessfullyAdded", null, Locale.ENGLISH) : messageSource.getMessage("SuccessfullyUpdate", null, Locale.ENGLISH));

		}
		else
		{
			redirectAttributes.addFlashAttribute("errMsg", errMsg.isEmpty() ? messageSource.getMessage("ErrorOccur", null, Locale.ENGLISH) : errMsg);
		}
		return "redirect:/";
	}
	@GetMapping(value = { "/deleteStudent/{studentID}" })
	public String deleteStudent(@PathVariable(value = "studentID") long studentID, RedirectAttributes redirectAttributes)
	{
		boolean success = false;
		String errMsg = "";
		try
		{
			if (studentID != 0)
			{
				Student studentObj = studentService.getStudentById(studentID);
				if (studentObj != null)
				{
					studentService.deleteStudent(studentObj);
					success = true;
				}
				else
				{
					errMsg = messageSource.getMessage("msg_user_not_avaliable", null, Locale.ENGLISH);
				}
			}
			else
			{
				errMsg = messageSource.getMessage("msg_user_not_valid", null, Locale.ENGLISH);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		if (success)
		{
			redirectAttributes.addFlashAttribute("successMsg", messageSource.getMessage("SuccessfullyDeleted", null, Locale.ENGLISH));
		}
		else
		{
			redirectAttributes.addFlashAttribute("errMsg", errMsg.isEmpty() ? messageSource.getMessage("ErrorOccur", null, Locale.ENGLISH) : errMsg);
		}
		return "redirect:/";
	}

	@GetMapping(value = { "/updateStudent/{studentID}" })
	public ModelAndView updateStudent(@PathVariable(value = "studentID") long studentID, Model model)
	{
		boolean success = false;
		String errMsg = "";
		Student student = null;
		ModelAndView mv = new ModelAndView();

		try
		{
			if (studentID != 0)
			{
				student = studentService.getStudentById(studentID);
				if (student != null)
				{
					mv.addObject("student", student);
					success = true;
				}
				else
				{
					errMsg = messageSource.getMessage("msg_user_not_avaliable", null, Locale.ENGLISH);
				}
			}
			else
			{
				errMsg = messageSource.getMessage("msg_user_not_valid", null, Locale.ENGLISH);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		if (!success)
		{
			model.addAttribute("errMsg", errMsg.isEmpty() ? messageSource.getMessage("ErrorOccur", null, Locale.ENGLISH) : errMsg);
		}
		mv.addObject("operation", "update");
		mv.setViewName("addStudent");
		return mv;
	}

}
