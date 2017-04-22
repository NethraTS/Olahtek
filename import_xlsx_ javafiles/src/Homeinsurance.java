


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

 public class Homeinsurance
 {
   public static void main(String[] args) 
   {
       try
       {
     	Mongo mongo = new Mongo("192.168.1.241", 27017);
   		DB db = mongo.getDB("home");
   		DBCollection collection = db.getCollection("Raf");
   		collection.drop();
     	 
           FileInputStream file = new FileInputStream(new File("C:/Users/Balasubramaniyan/Desktop/New folder/State Homeowners Insurance.xlsx"));

           //Create Workbook instance holding reference to .xlsx file
           XSSFWorkbook workbook = new XSSFWorkbook(file);

           //Get first/desired sheet from the workbook
           XSSFSheet sheet = workbook.getSheetAt(1);

           //Iterate through each rows one by one
           Iterator<Row> rowIterator = sheet.iterator();
           
           while (rowIterator.hasNext()) 
           {
         	 int count = 0;
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
                     		  documentDetail.put("monthly_Homeowners_Insurance", cell.getNumericCellValue());
                     	  else if(count == 2)
                     		  documentDetail.put("annual_Homeowners_Insurance", cell.getNumericCellValue());
                     	 else if(count == 3)
                    		  documentDetail.put("change_vs_Avg", cell.getNumericCellValue());
                     	  System.out.print(cell.getNumericCellValue() + "\t");
                     	  count++;
                     	  break; 
                      case Cell.CELL_TYPE_STRING:
                     	  if(count == 0)
                     		  documentDetail.put("state", cell.getStringCellValue().toUpperCase());
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

