package com.ann.automation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.Reporter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ann.automation.utilities.Constants;
import com.ann.automation.utilities.Utils;
import com.ann.automation.utilities.XLUtils;

public class XLUtils{
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	public static String ExcelPath;
	public static String env;
	public static NodeList ExcelEnv;
	static Document doc;
	static File file;
	static FileInputStream ExcelFile;
	private By byProperty=null;
	public static NodeList ExcelPathNode;

	static{
		try{
			
			//Get Xpaths from ObjectsRepository.xml file
			File fXmlFile = new File("./configs/ObjectsRepository.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile); 
			doc.getDocumentElement().normalize(); 
			ExcelEnv = doc.getElementsByTagName("Environment");
			env= ExcelEnv.item(0).getTextContent();
			
			switch(env) {
			case "stage" :
				ExcelPathNode = doc.getElementsByTagName("ExcelLocationStage");
				break;
			case "breakfix" :
				ExcelPathNode = doc.getElementsByTagName("ExcelLocationBreakfix");
				break;
			case "dryrun" :
				ExcelPathNode = doc.getElementsByTagName("ExcelLocationDryRun");
				break;
			case "production" :
				ExcelPathNode = doc.getElementsByTagName("ExcelLocationProduction");
				break;
			case "perfb" :
				ExcelPathNode = doc.getElementsByTagName("ExcelLocationPerfB");
				break;
			case "iad" :
				ExcelPathNode = doc.getElementsByTagName("ExcelLocationIAD");
				break;
			case "ord" :
				ExcelPathNode = doc.getElementsByTagName("ExcelLocationORD");
				break;
			default:
				ExcelPathNode = doc.getElementsByTagName("ExcelLocation");
			}
			
			ExcelPath=ExcelPathNode.item(0).getTextContent();
			
			file = new File(ExcelPath);
			ExcelFile = new FileInputStream(ExcelPath);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
            			 
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.getMessage();
		}
	}

	
	public synchronized By getObjectFromXML(String URLFromXML, String Environment) throws Exception{
		try{
			String sLocator_Property=null;
			
			NodeList nList = doc.getElementsByTagName(Environment);
			for (int temp = 0; temp < nList.getLength(); temp++) 
			{
				Node nNode = nList.item(temp);
				//  System.out.println("\nCurrent Element :" + nNode.getNodeURLFromXML());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element eElement = (Element) nNode; 
					URLFromXML=eElement.getElementsByTagName(URLFromXML).item(0).getTextContent();
				}
			}
			
		try{
			if(!URLFromXML.isEmpty()){
				
				String sLocator = URLFromXML.replaceAll("\\=.*", "");
				sLocator_Property = URLFromXML
						.substring(sLocator.length() + 1);

				// Find the Element Property to be used for Identifying the Object
				if (sLocator.equalsIgnoreCase("css")) {
					byProperty = By.cssSelector(sLocator_Property);
				} else if (sLocator.equalsIgnoreCase("id")) {
					byProperty = By.id(sLocator_Property);
				} else if (sLocator.equalsIgnoreCase("className")) {
					byProperty = By.className(sLocator_Property);
				}  else if (sLocator.equalsIgnoreCase("linkText")) {
					byProperty = By.linkText(sLocator_Property);
				} else if (sLocator.equalsIgnoreCase("name")) {
					byProperty = By.name(sLocator_Property);
				} else if (sLocator.equalsIgnoreCase("partialLinkText")) {
					byProperty = By.partialLinkText(sLocator_Property);
				} else if (sLocator.equalsIgnoreCase("tagName")) {
					byProperty = By.tagName(sLocator_Property);
				} else if (sLocator.equalsIgnoreCase("xpath")) {
					byProperty = By.xpath(sLocator_Property);
				}
				
			}else{
				Reporter.log(Constants.DELIMITER + Constants.FAIL + "| Object " + URLFromXML + "not found in Object Repository");
				throw new IllegalStateException("Object " + URLFromXML + "not found in Object Repository");
			}
			
		}catch(StringIndexOutOfBoundsException e){
				Reporter.log(Constants.DELIMITER + Constants.FAIL + "| Locators(xpath, name, linkText etc) not defined in Object Repository for Object: " + URLFromXML);
				throw new IllegalStateException("Locators(xpath, name, linkText etc) not defined in Object Repository for Object: " + URLFromXML);
		}
			
		}catch(Exception e){
			throw(e);
		}		
		return byProperty;
	}
	
	
	/**
	 * Method to pass action tag and return xpath of that object
	 * @param object
	 * @param Environment
	 * @return String
	 * @throws Exception
	 */
	public synchronized String getXpathFromXML(String object, String environment) throws Exception{
		try{
			NodeList nList = doc.getElementsByTagName(environment);
			for (int temp = 0; temp < nList.getLength(); temp++) 
			{
				Node nNode = nList.item(temp);
				//  System.out.println("\nCurrent Element :" + nNode.getNodeURLFromXML());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element eElement = (Element) nNode; 
					object=eElement.getElementsByTagName(object).item(0).getTextContent();
				}
			}
			
		}catch(Exception e){
			throw(e);
		}		
		return object;
	}

    //This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
    public synchronized static String getCellData(String SheetName,int RowNum, int ColNum) throws Exception{
    	try{
    		ExcelWSheet = ExcelWBook.getSheet(SheetName);
    		Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
    		String CellData = Cell.getStringCellValue();
    		return CellData;
    	}catch (Exception e){
    		return"";
    	}
    }
                
    public synchronized static int getRowContains(String SheetName, String sTestCaseName, int colNum) throws Exception{
    	int i;
    	try {
    		int rowCount = XLUtils.getRowUsed(SheetName);
    		for ( i=0 ; i<rowCount; i++){
    			if  (XLUtils.getCellData(SheetName, i,colNum).equalsIgnoreCase(sTestCaseName)){
    				break;
    			}
    		}
    		return i;
    	}catch (Exception e){
    		System.out.println("Class ExcelUtil | Method getRowContains | Exception desc : " + e.getMessage());
    		throw(e);
    	}
    }
        	
    public synchronized static int getRowUsed(String SheetName) throws Exception {
    	try{
    		ExcelWSheet = ExcelWBook.getSheet(SheetName);
    		int RowCount = ExcelWSheet.getLastRowNum();
    		System.out.println("Total number of Row used return as < " + RowCount + " >.");		
    		return RowCount;
    	}catch (Exception e){
    		System.out.println("Class ExcelUtil | Method getRowUsed | Exception desc : "+e.getMessage());
    		System.out.println(e.getMessage());
    		throw (e);
    	}
    	
    }
    
    public synchronized void WriteToExcelSheet(String SheetName,int RowNum, int ColNum, String ExcelValue)
    {
    	try{

    		ExcelWSheet = ExcelWBook.getSheet(SheetName);
    		Row=ExcelWSheet.getRow(RowNum);
    		    		
    		if(Cell == null){
                Cell = Row.createCell(ColNum);
                Cell.setCellValue(ExcelValue);
    		}else{
    			Cell.setCellValue(ExcelValue);
    		}
    		
    		FileOutputStream oStream= new FileOutputStream(file);
    		ExcelWBook.write(oStream);
    		oStream.flush();
    		oStream.close();
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public synchronized void WriteToExcelSheetForStringData(String SheetName,int RowNum, int ColNum, String StrData)
     {
    	try {

    		ExcelWSheet = ExcelWBook.getSheet(SheetName);
    		Row = ExcelWSheet.getRow(RowNum);    			
    		
    		if(Cell == null){
                Cell = Row.createCell(ColNum);
    			Cell.setCellType(XSSFCell.CELL_TYPE_STRING);

                Cell.setCellValue(StrData);
    		}else{
    			Cell.setCellValue(StrData);
    		}
    			Cell.setCellValue(StrData);
    			FileOutputStream oStream = new FileOutputStream(file);
    			ExcelWBook.write(oStream);
    			oStream.flush();
    			oStream.close();
    			
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
    	}

    public synchronized static String AccessExcelSheetForCellData(String SheetName,int RowNum, int ColNum) throws Exception{
		String CellData=null;  				
		try{
								       
			// Access the required test data sheet
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			int cellType=ExcelWSheet.getRow(RowNum).getCell(ColNum).getCellType();
			
			if(cellType==1)
			{
				CellData = ExcelWSheet.getRow(RowNum).getCell(ColNum).getStringCellValue();    
			}else if(cellType==0)	
			{
				double CellDataNumeric = ExcelWSheet.getRow(RowNum).getCell(ColNum).getNumericCellValue();  
				CellData=String.valueOf(CellDataNumeric); 	
			} 
			return CellData;
			
		}catch(Exception e){
			throw(e);
		}
    }
    
    public synchronized String AccessExcelSheetForNumericCellData(String Path,String SheetName,int RowNum, int ColNum) throws Exception{
    		String CellData=null;  
    	 try{
    		 //Access the required test data sheet
    		 ExcelWSheet = ExcelWBook.getSheet(SheetName);
    		 int CellDataNumeric = (int) ExcelWSheet.getRow(RowNum).getCell(ColNum).getNumericCellValue();  
    		 CellData=String.valueOf(CellDataNumeric); 
    			
    	 }catch (Exception e){		
    		 throw(e);
    	 }
    	 
    	 return CellData;
    }
    
    public synchronized int GetNoOfRows(String Path,String SheetName){
    			int i=0;
    			ExcelWSheet = ExcelWBook.getSheet(SheetName);
    			i=ExcelWSheet.getPhysicalNumberOfRows(); 
    			return i;
      }
    public synchronized List<String> accessExcelSheetForWholeRow(String SheetName,int RowNum, int ColNum) throws Exception{
    	               	 
    	String CellData=null;  
    	List<String> list=new ArrayList<String>();
    	try 
    	{
    		// Access the required test data sheet
    		ExcelWSheet = ExcelWBook.getSheet(SheetName);
    		int excel_get_rows=0;
    		excel_get_rows=ExcelWSheet.getPhysicalNumberOfRows();
    			 
    		for(int i=RowNum,j=ColNum; i<excel_get_rows;i++)
    		{
    			CellData = ExcelWSheet.getRow(i).getCell(j).getStringCellValue(); 
    			list.add(CellData);
    		} 
    		System.out.println("col values"+list);
    			 
    	}catch (Exception e){
    		e.getMessage();
    	} 
    	return list;
     }
    /**
     * Follwoing method is used to fetch whole column values as list based on passed row num 
     * @param SheetName
     * @param rowNum
     * @return
     * @throws Exception
     */
    public synchronized List<String> accessExcelSheetForeCol(String SheetName,int rowNum) throws Exception{
      	 
    	String CellData=null;  
    	List<String> list=new ArrayList<String>();
    	try 
    	{
    		// Access the required test data sheet
    		ExcelWSheet = ExcelWBook.getSheet(SheetName);
    		int excel_get_rows=0;
    		excel_get_rows=getRowUsed(SheetName);
    		int colSize = ExcelWSheet.getRow(0).getLastCellNum();
    			 
    		for(int i=0; i<=excel_get_rows;i++)
    		{
    			 if (i == rowNum) {
    				 for(int j=0;j<colSize;j++){
    					 CellData = AccessExcelSheetForCellData(SheetName,rowNum,j);
    		    			list.add(CellData);
    		    			
    				 }
    				 break;
    			
    		} 
    		}
    		
    			 
    	}catch (Exception e){
    		e.getMessage();
    	} 
    	return list;
     }
    
    public synchronized String getObjectFromXMLString(String URLFromXML, String Environment) throws Exception{
		try{
			
			if(!(Utils.brand.equals("ATF") || Utils.brand.equals("LO"))) {
				URLFromXML = "FP_" + URLFromXML;
			}
			NodeList nList = doc.getElementsByTagName(Environment);
			for (int temp = 0; temp < nList.getLength(); temp++) 
			{
				Node nNode = nList.item(temp);
				//  System.out.println("\nCurrent Element :" + nNode.getNodeURLFromXML());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element eElement = (Element) nNode; 
					URLFromXML=eElement.getElementsByTagName(URLFromXML).item(0).getTextContent();
				}
			}
			
    }catch (Exception e){
		e.getMessage();
	}
		return URLFromXML;
    }
}