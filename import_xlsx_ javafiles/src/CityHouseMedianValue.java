

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bson.BasicBSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class CityHouseMedianValue {



	public static void main(String[] args) throws Exception {  
    	Mongo mongo = new Mongo("192.168.1.241", 27017);
  		DB db = mongo.getDB("home");
  		DBCollection collection = db.getCollection("Raf");
  		collection.drop();
   FileInputStream fileIn = new FileInputStream("C:/Users/Balasubramaniyan/Desktop/New folder/CityMedianSoldPriceAllHomes.xlsx");
   XSSFWorkbook filename = new XSSFWorkbook(fileIn);
   XSSFSheet sheet = filename.getSheetAt(0);
   
    String columnWanted1 = "RegionID";
    Integer columnNo1 = null;
    String columnWanted2 = "State";
    Integer columnNo2 = null;
    String columnWanted3 = "RegionName";
    Integer columnNo3 = null;
    String columnWanted4 = "CountyName";
    Integer columnNo4 = null;
    String columnWanted5 = "2016-06";
    Integer columnNo5 = null;
    String columnWanted6 = "2016-05";
    Integer columnNo6 = null;
    String columnWanted7 = "2016-04";
    Integer columnNo7 = null;
    String columnWanted8 = "2016-03";
    Integer columnNo8 = null;
    String columnWanted9 = "2016-02";
    Integer columnNo9 = null;
    
    Row firstRow = sheet.getRow(0);

    for(Cell cell:firstRow){
        if (cell.getStringCellValue().equals(columnWanted5)){
            columnNo5 = cell.getColumnIndex();
        }
        else if (cell.getStringCellValue().equals(columnWanted1)){
            columnNo1 = cell.getColumnIndex();
        }else if (cell.getStringCellValue().equals(columnWanted2)){
            columnNo2 = cell.getColumnIndex();
        }else if (cell.getStringCellValue().equals(columnWanted3)){
            columnNo3 = cell.getColumnIndex();
        }else if (cell.getStringCellValue().equals(columnWanted4)){
            columnNo4 = cell.getColumnIndex();
        }else if (cell.getStringCellValue().equals(columnWanted6)){
            columnNo6 = cell.getColumnIndex();
        }else if (cell.getStringCellValue().equals(columnWanted7)){
            columnNo7 = cell.getColumnIndex();
        }else if (cell.getStringCellValue().equals(columnWanted8)){
            columnNo8 = cell.getColumnIndex();
        }else if (cell.getStringCellValue().equals(columnWanted9)){
            columnNo9 = cell.getColumnIndex();
        }
    }
    for (Row row : sheet) {
    	if(row.getRowNum()==0){
     	   continue; 
     	  }
    BasicDBObject documentDetail = new BasicDBObject();
       Cell regionid = row.getCell(columnNo1);
       Cell state = row.getCell(columnNo2);
       Cell city = row.getCell(columnNo3);
       Cell county = row.getCell(columnNo4);
       Cell value = row.getCell(columnNo5);
       Cell value1 = row.getCell(columnNo6);
       Cell value2 = row.getCell(columnNo7);
       Cell value3 = row.getCell(columnNo8);
       Cell value4 = row.getCell(columnNo9);
	switch (regionid.getCellType()) 
    {
  	    case Cell.CELL_TYPE_NUMERIC:
      documentDetail.append("regionId", regionid.getNumericCellValue());
       
       
     
        if(value4 == null && value3 == null && value2 == null && value1 == null  && value == null) {
    	   documentDetail.append("value", "0");
    	   }
        else if(value3 == null && value2 == null && value4 == null && value == null) {
      	   documentDetail.append("value",value1.getNumericCellValue());
      	 documentDetail.append("timePeriod", "2016-05");
      	   }
        else if(value4 == null && value3 == null && value1 == null && value == null) {
      	   documentDetail.append("value",value2.getNumericCellValue());
      	 documentDetail.append("timePeriod", "2016-04");
      	   }
        else if(value4 == null && value2 == null && value1 == null && value == null) {
       	   documentDetail.append("value",value3.getNumericCellValue());
       	 documentDetail.append("timePeriod", "2016-03");
       	   }
        else if(value3 == null && value2 == null && value1 == null && value == null) {
        	   documentDetail.append("value",value4.getNumericCellValue());
        	 documentDetail.append("timePeriod", "2016-02");
        	   }
        else if(value4 == null && value3 == null && value == null) {
      	   documentDetail.append("value",value1.getNumericCellValue());
      	 documentDetail.append("timePeriod", "2016-05");
      	   }
        else if(value3 == null && value2 == null && value == null) {
       	   documentDetail.append("value",value1.getNumericCellValue());
       	 documentDetail.append("timePeriod", "2016-05");
       	   }
        else if(value4 == null && value2 == null && value == null) {
        	   documentDetail.append("value",value1.getNumericCellValue());
        	 documentDetail.append("timePeriod", "2016-05");
        	   }
        else if(value2 == null && value1 == null && value == null) {
        	   documentDetail.append("value",value3.getNumericCellValue());
        	 documentDetail.append("timePeriod", "2016-03");
        	   }
        else if(value3 == null && value1 == null && value == null) {
     	   documentDetail.append("value",value2.getNumericCellValue());
     	 documentDetail.append("timePeriod", "2016-04");
     	   }
        else if(value4 == null && value1 == null && value == null) {
      	   documentDetail.append("value",value2.getNumericCellValue());
      	 documentDetail.append("timePeriod", "2016-04");
      	   }
        else if(value4 == null  && value == null) {
     	   documentDetail.append("value", value1.getNumericCellValue());
     	   documentDetail.append("timePeriod", "2016-05");
     	   }
        else if(value3 == null  && value == null) {
    	   documentDetail.append("value", value1.getNumericCellValue());
    	   documentDetail.append("timePeriod", "2016-05");
    	   }
       else if(value2 == null  && value == null) {
    	   documentDetail.append("value", value1.getNumericCellValue());
    	   documentDetail.append("timePeriod", "2016-05");
    	   }
       else if(value1 == null  && value == null) {
    	   documentDetail.append("value", value2.getNumericCellValue());
    	   documentDetail.append("timePeriod", "2016-04");
    	   }
       else if(value == null ) {
    	   documentDetail.append("value", value1.getNumericCellValue());
    	   documentDetail.append("timePeriod", "2016-05");
    	   }
     
        else  {
    	   documentDetail.append("value", value.getNumericCellValue());
    	   documentDetail.append("timePeriod", "2016-06");
       }
    }
        documentDetail.append("state", state.toString());
        documentDetail.append("city", city.toString());
        documentDetail.append("countyName", county.toString());
       
       collection.insert(documentDetail);
       documentDetail.append("value", "0");
      	collection.remove(documentDetail);
      	
    }
    }
    
}
