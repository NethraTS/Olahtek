
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
public class Rural_List_2017 
{
  public static void main(String[] args) 
  {
      try
      {
    	Mongo mongo = new Mongo("192.168.1.241", 27017);
  		DB db = mongo.getDB("home");
  		DBCollection collection = db.getCollection("Raf");
  		collection.drop();
    	 
          FileInputStream file = new FileInputStream(new File("C:/Users/Balasubramaniyan/Desktop/New folder/Rural_List_2017.xlsx"));

          //Create Workbook instance holding reference to .xlsx file
          XSSFWorkbook workbook = new XSSFWorkbook(file);

          //Get first/desired sheet from the workbook
          XSSFSheet sheet = workbook.getSheetAt(0);

          //Iterate through each rows one by one
          Iterator<Row> rowIterator = sheet.iterator();
          
          while (rowIterator.hasNext()) 
          {
        	 int count = 1;
              Row row = rowIterator.next();
              //For each row, iterate through all the columns
              if(row.getRowNum()==0){
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
                    	 if(count == 1)
                    		  documentDetail.put("fips", cell.getNumericCellValue());
                    	  System.out.print(cell.getNumericCellValue() + "\t");
                    	  count++;
                    	  break; 
                      case Cell.CELL_TYPE_STRING:
                    	  if(count == 2)
                    		  documentDetail.put("state", cell.getStringCellValue());
                    	  else if(count == 3)
                    		  documentDetail.put("countyName", cell.getStringCellValue());
       
                    	  count++;
                    	  System.out.print(cell.getStringCellValue() + "\t"); 
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

