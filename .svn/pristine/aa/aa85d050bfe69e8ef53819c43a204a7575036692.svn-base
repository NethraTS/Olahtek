

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

//import statements
public class RateAdjustmentFactor 
{
  public static void main(String[] args) 
  {
      try
      {
    	Mongo mongo = new Mongo("192.168.1.241", 27017);
  		DB db = mongo.getDB("home");
  		DBCollection collection = db.getCollection("Raf");
  		collection.drop();
    	 
          FileInputStream file = new FileInputStream(new File("C:/Users/Balasubramaniyan/Desktop/New folder (16)/Rates Adjustment Factors.xlsx"));

          //Create Workbook instance holding reference to .xlsx file
          XSSFWorkbook workbook = new XSSFWorkbook(file);

          //Get first/desired sheet from the workbook
          XSSFSheet sheet = workbook.getSheetAt(0);

          //Iterate through each rows one by one
          Iterator<Row> rowIterator = sheet.iterator();
          
          while (rowIterator.hasNext()) 
          {
        	 int count = 0;
        	 Row row = rowIterator.next();
				if (row.getRowNum() == 0 || row.getRowNum() == 1) {
					continue;
				}
             
              Iterator<Cell> cellIterator = row.cellIterator();
              
              BasicDBObject documentDetail = new BasicDBObject();
              
            
             
              while (cellIterator.hasNext())
              {
            	
                  Cell cell = cellIterator.next();
                  //Check the cell type and format accordingly
                  switch (cell.getCellType()) 
                  {
                  case Cell.CELL_TYPE_NUMERIC:
                	  
               	       /*if(count==0)
               	      documentDetail.put("creditScore", cell.getStringCellValue()); */  
                       if(count == 1)
                      documentDetail.put("thirtyYearFixed", cell.getNumericCellValue());
                	  else if(count == 2)
                      documentDetail.put("fifteenYearFixed", cell.getNumericCellValue());
                	  else if(count == 3)
                      documentDetail.put("threeOneARM", cell.getNumericCellValue());
                	  else if(count == 4)
                      documentDetail.put("fiveOneARM", cell.getNumericCellValue());
                	  else if(count == 5)
                      documentDetail.put("thirtyYearFixedJumbo", cell.getNumericCellValue());
                	  else if(count == 6)
                      documentDetail.put("fifteenYearFixedJumbo", cell.getNumericCellValue());
                	  else if(count == 7)
                      documentDetail.put("threeOneARMJumbo", cell.getNumericCellValue());
                	  else if(count == 8)
                      documentDetail.put("fiveOneARMJumbo", cell.getNumericCellValue());
                	  else if(count == 9)
                      documentDetail.put("threeSixMonthAuto", cell.getNumericCellValue());
                	  else if(count == 10)
                      documentDetail.put("fourEightMonthAuto",cell.getNumericCellValue());
                	  else if(count == 11)
                      documentDetail.put("sixZeroMonthAuto", cell.getNumericCellValue());
                		  
               	      System.out.print(cell.getNumericCellValue() + "\t"); 
                	  count++;
                	  break;
                	  
           
                     case Cell.CELL_TYPE_STRING:
                    	 if(count==0)
                    	 {
                    	 documentDetail.put("creditScore", cell.getStringCellValue());
                    	 //System.out.println("documentDetail" +documentDetail);
                    	 //System.out.println("count" +count);
                    	 }
                    	  count++;
                          break;
                          
                       }
                 
              }
              
             
              
              collection.insert(documentDetail);
             
       System.out.println("");
          }
         
          
          
          file.close();
      } 
      catch (Exception e) 
      {
          e.printStackTrace();
      }
  }
}

