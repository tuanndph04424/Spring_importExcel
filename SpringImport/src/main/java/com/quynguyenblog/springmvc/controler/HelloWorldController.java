package com.quynguyenblog.springmvc.controler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.quynguyenblog.springmvc.entity.UserInfo;



	@Controller
	@RequestMapping(value = "/")
	public class HelloWorldController {

		@Autowired
		ServletContext context;
			
		@RequestMapping(value = "/hello", method = RequestMethod.GET)
		public String HelloWorld(Model model) {		
			//model.addAttribute("lstUser", lstUser);
			model.addAttribute("message", "Welcome to Spring MVC");
			return "hello";
		}
	 
		@RequestMapping(value = "/processExcel", method = RequestMethod.POST)
		public String processExcel(Model model, @RequestParam("excelfile") MultipartFile excelfile) {		
			try {
				List<UserInfo> lstUser = new ArrayList<>();
				int i = 0;
				// Creates a workbook object from the uploaded excelfile
				HSSFWorkbook workbook = new HSSFWorkbook(excelfile.getInputStream());
				// Creates a worksheet object representing the first sheet
				HSSFSheet worksheet = workbook.getSheetAt(0);
				// Reads the data in excel file until last row is encountered
				while (i <= worksheet.getLastRowNum()) {
					// Creates an object for the UserInfo Model
					UserInfo user = new UserInfo();
					// Creates an object representing a single row in excel
					HSSFRow row = worksheet.getRow(i++);
					// Sets the Read data to the model class
					user.setId((int) row.getCell(0).getNumericCellValue());
					user.setUsername(row.getCell(1).getStringCellValue());
					user.setInputDate(row.getCell(2).getDateCellValue());
					// persist data into database in here
					lstUser.add(user);
				}
				model.addAttribute("lstUser", lstUser);
			} catch (Exception e) {
				e.printStackTrace();
			}
	 
			return "hello";	
		}
		
		@RequestMapping(value = "/hello", method = RequestMethod.POST)
		public String processExcel2007(Model model, @RequestParam("excelfile2007") MultipartFile excelfile) {		
			try {
				List<UserInfo> lstUser = new ArrayList<>();
				int i = 0;
				// Creates a workbook object from the uploaded excelfile
				XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
				// Creates a worksheet object representing the first sheet
				XSSFSheet worksheet = workbook.getSheetAt(0);
				// Reads the data in excel file until last row is encountered
				while (i <= worksheet.getLastRowNum()) {
					// Creates an object for the UserInfo Model
					UserInfo user = new UserInfo();
					// Creates an object representing a single row in excel
					XSSFRow row = worksheet.getRow(i++);
					// Sets the Read data to the model class
					user.setId((int) row.getCell(0).getNumericCellValue());
					user.setUsername(row.getCell(1).getStringCellValue());
					user.setInputDate(row.getCell(2).getDateCellValue());
					// persist data into database in here
					lstUser.add(user);
				}			
				
				model.addAttribute("lstUser", lstUser);
			} catch (Exception e) {
				e.printStackTrace();
			}
	 
			return "hello";
		}
}
