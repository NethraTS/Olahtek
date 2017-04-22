

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

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class StateHouseMedianValue {


    public static void main(String[] args) throws Exception {  
    	Mongo mongo = new Mongo("192.168.1.241", 27017);
  		DB db = mongo.getDB("home");
  		DBCollection collection = db.getCollection("Raf");
  		collection.drop();
   FileInputStream fileIn = new FileInputStream("C:/Users/Balasubramaniyan/Desktop/New folder/State_MedianSoldPrice_AllHomes.xlsx");
   XSSFWorkbook filename = new XSSFWorkbook(fileIn);
   XSSFSheet sheet = filename.getSheetAt(0);
    String columnWanted = "2016-06";
    Integer columnNo = null;
    String columnWanted1 = "RegionID";
    Integer columnNo1 = null;
    String columnWanted2 = "RegionName";
    Integer columnNo2 = null;
    
    //List<Cell> cells = new ArrayList<Cell>();
   // List<Cell> cells1 = new ArrayList<Cell>();
    Row firstRow = sheet.getRow(0);

    for(Cell cell:firstRow){
        if (cell.getStringCellValue().equals(columnWanted)){
            columnNo = cell.getColumnIndex();
        }
        else if (cell.getStringCellValue().equals(columnWanted1)){
            columnNo1 = cell.getColumnIndex();
        }else if (cell.getStringCellValue().equals(columnWanted2)){
            columnNo2 = cell.getColumnIndex();
        }
    }
    for (Row row : sheet) {
    BasicDBObject documentDetail = new BasicDBObject();
       Cell regionid = row.getCell(columnNo1);
       Cell state = row.getCell(columnNo2);
       Cell value = row.getCell(columnNo);

       documentDetail.append("regionId", regionid.toString());
       documentDetail.append("state", state.toString());
       if(value == null) {
    	   documentDetail.append("value", "0");
       } else {
    	   documentDetail.append("value", value.toString());
       }
       
       
       collection.insert(documentDetail);
       documentDetail.append("value", "0");
       documentDetail.append("value", "2016-06");
      	collection.remove(documentDetail);
    }
    }
    

}
