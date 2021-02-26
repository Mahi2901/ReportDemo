package com.web.empdemo.utils;


import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;


public class JasperExporter {
	
	JasperReport jasperReport;
	JasperPrint jasperPrint;
	OutputStream outputStream;
	File file;
	JRXlsExporter xlsExporter;
	

	public void jasperExporterPDF(HashMap jasperParameter,String jrxmlpath ,String fileName,  HttpServletResponse response) throws IOException
	{
		
		try
		{
			
			outputStream = response.getOutputStream();
	        jasperReport = JasperCompileManager.compileReport(jrxmlpath);
	           
            jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, DBConnection.getConnectionFront()); 
           
            file = File.createTempFile("output.", ".pdf");
            
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition","inline; filename="+fileName+".pdf");
            
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        }
	 	catch (Exception e) {
	     	e.printStackTrace();
	 	}
		
		
	}
	
	public void jasperExporterEXCEL(HashMap jasperParameter,String jrxmlpath ,String fileName,  HttpServletResponse response) throws IOException
	{
		
		try
		{
			
			outputStream = response.getOutputStream();
	        jasperReport = JasperCompileManager.compileReport(jrxmlpath);
	           
            jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, DBConnection.getConnectionFront()); 
           
            file = File.createTempFile("output.", ".xls");
            
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition","inline; filename="+fileName+".xls");
            
            //JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            
            xlsExporter = new JRXlsExporter();
            xlsExporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
            xlsExporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, outputStream);
            xlsExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
            xlsExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            xlsExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            xlsExporter.exportReport();
            
		
	           
        }
	 	catch (Exception e) {
	 		e.printStackTrace();
	 	}
	}

}
