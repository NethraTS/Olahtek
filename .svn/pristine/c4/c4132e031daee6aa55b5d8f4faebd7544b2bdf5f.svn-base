
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
public class UrbanMidWest {
	public static void main(String[] args) {
		try {
			Mongo mongo = new Mongo("192.168.1.241", 27017);
			DB db = mongo.getDB("home");
			FileInputStream file = new FileInputStream(new File("C:/Users/Balasubramaniyan/Desktop/New folder/Raising a Kid Calculation.xlsx"));
			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
				//System.out.println(workbook.getSheetName(i));
				
				if (workbook.getSheetName(i).equals("Non-Housing Expense (Midwest)")) {
					XSSFSheet sheet = workbook.getSheet("Non-Housing Expense (Midwest)");
					DBCollection collection = db.getCollection("Raf");
					collection.drop();
					Iterator<Row> rowIterator = sheet.iterator();

					while (rowIterator.hasNext()) {
						int count = 0;
						Row row = rowIterator.next();
						if (row.getRowNum() == 0 || row.getRowNum() == 1 || row.getRowNum() == 2|| row.getRowNum() == 3) {
							continue;
						}
						Iterator<Cell> cellIterator = row.cellIterator();

						BasicDBObject documentDetail = new BasicDBObject();
						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();
							// Check the cell type and format accordingly
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								if (count == 0)
									documentDetail.put("childAge", cell.getNumericCellValue());
								else if (count == 1)
									documentDetail.put("cost1", cell.getNumericCellValue());
								else if (count == 2)
									documentDetail.put("cost2", cell.getNumericCellValue());
								else if (count == 3)
									documentDetail.put("cost3", cell.getNumericCellValue());
								System.out.print(cell.getNumericCellValue() + "\t");
								count++;
								break;
							}
						}
						collection.insert(documentDetail);
						System.out.println("");
					}

					file.close();
				} 
				
				else if (workbook.getSheetName(i).equals("Non-Housing Expense (South)")) {
					XSSFSheet sheet = workbook.getSheet("Non-Housing Expense (South)");
					DBCollection collection = db.getCollection("Raf1");
					collection.drop();
					Iterator<Row> rowIterator = sheet.iterator();

					while (rowIterator.hasNext()) {
						int count = 0;
						Row row = rowIterator.next();
						if (row.getRowNum() == 0 || row.getRowNum() == 1 || row.getRowNum() == 2|| row.getRowNum() == 3) {
							continue;
						}
						Iterator<Cell> cellIterator = row.cellIterator();

						BasicDBObject documentDetail = new BasicDBObject();
						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();
							// Check the cell type and format accordingly
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								if (count == 0)
									documentDetail.put("childAge", cell.getNumericCellValue());
								else if (count == 1)
									documentDetail.put("cost1", cell.getNumericCellValue());
								else if (count == 2)
									documentDetail.put("cost2", cell.getNumericCellValue());
								else if (count == 3)
									documentDetail.put("cost3", cell.getNumericCellValue());
								System.out.print(cell.getNumericCellValue() + "\t");
								count++;
								break;
							}
						}
						collection.insert(documentDetail);
						System.out.println("");
					}

					file.close();
				} 
				
				else if (workbook.getSheetName(i).equals("Non-Housing Expense (Northeast)")) {
					XSSFSheet sheet = workbook.getSheet("Non-Housing Expense (Northeast)");
					DBCollection collection = db.getCollection("Raf2");
					collection.drop();
					Iterator<Row> rowIterator = sheet.iterator();

					while (rowIterator.hasNext()) {
						int count = 0;
						Row row = rowIterator.next();
						if (row.getRowNum() == 0 || row.getRowNum() == 1 || row.getRowNum() == 2|| row.getRowNum() == 3) {
							continue;
						}
						Iterator<Cell> cellIterator = row.cellIterator();

						BasicDBObject documentDetail = new BasicDBObject();
						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();
							// Check the cell type and format accordingly
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								if (count == 0)
									documentDetail.put("childAge", cell.getNumericCellValue());
								else if (count == 1)
									documentDetail.put("cost1", cell.getNumericCellValue());
								else if (count == 2)
									documentDetail.put("cost2", cell.getNumericCellValue());
								else if (count == 3)
									documentDetail.put("cost3", cell.getNumericCellValue());
								System.out.print(cell.getNumericCellValue() + "\t");
								count++;
								break;
							}
						}
						collection.insert(documentDetail);
						System.out.println("");
					}

					file.close();
				}
				
				else if (workbook.getSheetName(i).equals("Non-Housing Expense (West)")) {
					XSSFSheet sheet = workbook.getSheet("Non-Housing Expense (West)");
					DBCollection collection = db.getCollection("Raf3");
					collection.drop();
					Iterator<Row> rowIterator = sheet.iterator();

					while (rowIterator.hasNext()) {
						int count = 0;
						Row row = rowIterator.next();
						if (row.getRowNum() == 0 || row.getRowNum() == 1 || row.getRowNum() == 2|| row.getRowNum() == 3) {
							continue;
						}
						Iterator<Cell> cellIterator = row.cellIterator();

						BasicDBObject documentDetail = new BasicDBObject();
						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();
							// Check the cell type and format accordingly
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								if (count == 0)
									documentDetail.put("childAge", cell.getNumericCellValue());
								else if (count == 1)
									documentDetail.put("cost1", cell.getNumericCellValue());
								else if (count == 2)
									documentDetail.put("cost2", cell.getNumericCellValue());
								else if (count == 3)
									documentDetail.put("cost3", cell.getNumericCellValue());
								System.out.print(cell.getNumericCellValue() + "\t");
								count++;
								break;
							}
						}
						collection.insert(documentDetail);
						System.out.println("");
					}

					file.close();
				}
				
				else if (workbook.getSheetName(i).equals("Non-Housing Expense (Married)")) {
					XSSFSheet sheet = workbook.getSheet("Non-Housing Expense (Married)");
					DBCollection collection = db.getCollection("Raf4");
					collection.drop();
					Iterator<Row> rowIterator = sheet.iterator();

					while (rowIterator.hasNext()) {
						int count = 0;
						Row row = rowIterator.next();
						if (row.getRowNum() == 0 || row.getRowNum() == 1 || row.getRowNum() == 2|| row.getRowNum() == 3) {
							continue;
						}
						Iterator<Cell> cellIterator = row.cellIterator();

						BasicDBObject documentDetail = new BasicDBObject();
						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();
							// Check the cell type and format accordingly
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								if (count == 0)
									documentDetail.put("childAge", cell.getNumericCellValue());
								else if (count == 1)
									documentDetail.put("cost1", cell.getNumericCellValue());
								else if (count == 2)
									documentDetail.put("cost2", cell.getNumericCellValue());
								else if (count == 3)
									documentDetail.put("cost3", cell.getNumericCellValue());
								System.out.print(cell.getNumericCellValue() + "\t");
								count++;
								break;
							}
						}
						collection.insert(documentDetail);
						System.out.println("");
					}

					file.close();
				}
				
				else if (workbook.getSheetName(i).equals("Non-Housing Expense (Single)")) {
					XSSFSheet sheet = workbook.getSheet("Non-Housing Expense (Single)");
					DBCollection collection = db.getCollection("Raf5");
					collection.drop();
					Iterator<Row> rowIterator = sheet.iterator();

					while (rowIterator.hasNext()) {
						int count = 0;
						Row row = rowIterator.next();
						if (row.getRowNum() == 0 || row.getRowNum() == 1 || row.getRowNum() == 2|| row.getRowNum() == 3) {
							continue;
						}
						Iterator<Cell> cellIterator = row.cellIterator();

						BasicDBObject documentDetail = new BasicDBObject();
						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();
							// Check the cell type and format accordingly
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								if (count == 0)
									documentDetail.put("childAge", cell.getNumericCellValue());
								else if (count == 1)
									documentDetail.put("cost1", cell.getNumericCellValue());
								else if (count == 2)
									documentDetail.put("cost2", cell.getNumericCellValue());
								/*else if (count == 3)
									documentDetail.put("cost3", cell.getNumericCellValue());*/
								System.out.print(cell.getNumericCellValue() + "\t");
								count++;
								break;
							}
						}
						collection.insert(documentDetail);
						System.out.println("");
					}

					file.close();
				}
				
				else if (workbook.getSheetName(i).equals("Non-Housing Expense (Rural)")) {
					XSSFSheet sheet = workbook.getSheet("Non-Housing Expense (Rural)");
					DBCollection collection = db.getCollection("Raf6");
					collection.drop();
					Iterator<Row> rowIterator = sheet.iterator();

					while (rowIterator.hasNext()) {
						int count = 0;
						Row row = rowIterator.next();
						if (row.getRowNum() == 0 || row.getRowNum() == 1 || row.getRowNum() == 2|| row.getRowNum() == 3) {
							continue;
						}
						Iterator<Cell> cellIterator = row.cellIterator();

						BasicDBObject documentDetail = new BasicDBObject();
						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();
							// Check the cell type and format accordingly
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								if (count == 0)
									documentDetail.put("childAge", cell.getNumericCellValue());
								else if (count == 1)
									documentDetail.put("cost1", cell.getNumericCellValue());
								else if (count == 2)
									documentDetail.put("cost2", cell.getNumericCellValue());
								else if (count == 3)
									documentDetail.put("cost3", cell.getNumericCellValue());
								System.out.print(cell.getNumericCellValue() + "\t");
								count++;
								break;
							}
						}
						collection.insert(documentDetail);
						System.out.println("");
					}

					file.close();
				}
				
				else if (workbook.getSheetName(i).equals("Regional Adjustment Factor")) {
					XSSFSheet sheet = workbook.getSheet("Regional Adjustment Factor");
					DBCollection collection = db.getCollection("Raf7");
					collection.drop();
					Iterator<Row> rowIterator = sheet.iterator();

					while (rowIterator.hasNext()) {
						int count = 0;
						Row row = rowIterator.next();
						if (row.getRowNum() == 0 || row.getRowNum() == 1 || row.getRowNum() == 2) {
							continue;
						}
						Iterator<Cell> cellIterator = row.cellIterator();

						BasicDBObject documentDetail = new BasicDBObject();
						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();
							// Check the cell type and format accordingly
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								if(count == 0)
		                    		  documentDetail.put("childAge", cell.getNumericCellValue());
								 System.out.print(cell.getNumericCellValue() + "\t");
							}
							 if(cell.getCellType() == Cell.CELL_TYPE_FORMULA){
							 switch (cell.getCachedFormulaResultType()) 
			                  {
			                	    case Cell.CELL_TYPE_NUMERIC:
			                    	 /*if(count == 0)
			                    		  documentDetail.put("childAge", cell.getNumericCellValue());*/
			                    	   if(count == 1)
			                    		  documentDetail.put("northEast", cell.getNumericCellValue());
			                    	  else if(count == 2)
			                    		  documentDetail.put("west", cell.getNumericCellValue());
			                    	  else if(count == 3)
			                  		  documentDetail.put("midWest", cell.getNumericCellValue());
			                  	  else if(count == 4)
			                  		  documentDetail.put("south", cell.getNumericCellValue());
			                  	  else 
			                  		  documentDetail.put("rural", cell.getNumericCellValue());
			                    	 
			                    	 System.out.print(cell.getNumericCellValue() + "\t");
			                   	  count++;
			                   	  break; 
			                       }
							 }
						}
						collection.insert(documentDetail);
						System.out.println("");
					}

					file.close();
				}
				
				else if (workbook.getSheetName(i).equals("Inflation Adjustment")) {
					XSSFSheet sheet = workbook.getSheet("Inflation Adjustment");
					DBCollection collection = db.getCollection("Raf8");
					collection.drop();
					Iterator<Row> rowIterator = sheet.iterator();

					while (rowIterator.hasNext()) {
						int count = 0;
						Row row = rowIterator.next();
						if (row.getRowNum() == 0 || row.getRowNum() == 4 ) {
							continue;
						}
						Iterator<Cell> cellIterator = row.cellIterator();

						BasicDBObject documentDetail = new BasicDBObject();
						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();
							// Check the cell type and format accordingly
							switch (cell.getCellType()) 
			                  {
			                	    case Cell.CELL_TYPE_NUMERIC:
			                    	 if(count == 0)
			                    		  documentDetail.put("year", cell.getNumericCellValue());
			                    	  else if(count == 1)
			                    		  documentDetail.put("inflationRate", cell.getNumericCellValue());
			                    	  /*else if(count == 2)
			                    		  documentDetail.put("cost2", cell.getNumericCellValue());
			                    	  else if(count == 3)
			                    		  documentDetail.put("cost3", cell.getNumericCellValue());*/
			                    	  System.out.print(cell.getNumericCellValue() + "\t");
			                    	  count++;
			                    	  break; 
			                	    case Cell.CELL_TYPE_STRING:
			                	    	if(count == 0){
			                	    		if(cell.getStringCellValue().equals("Adjustment from 2013 data"))
			                      		  documentDetail.put("year", 2013);
			                	    	documentDetail.put("inflationRate",1.03227124);
			                	    	}
			                       }
						}
						collection.insert(documentDetail);
						System.out.println("");
					}

					file.close();
				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
