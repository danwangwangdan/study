package com.study.poi;

import com.common.model.ItemsInExcel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelTools {

    public static List<ItemsInExcel> readExcel(String path) {
        List<ItemsInExcel> infoList = new ArrayList<ItemsInExcel>();
        int line = 0;
        try {
            FileInputStream fis = new FileInputStream(path);
            // 1. 新建workbook对象
            Workbook workbook = null;
            if (path.toLowerCase().endsWith("xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (path.toLowerCase().endsWith("xls")) {
                workbook = new HSSFWorkbook(fis);
            }
            // 2. 获取该excel的sheet数量
            int numberOfSheets = workbook.getNumberOfSheets();
            // 3. 遍历数据
            for (int i = 0; i < numberOfSheets; i++) {
                // 3.1 获取一个sheet
                Sheet sheet = workbook.getSheetAt(i);
                // 3.2 遍历sheet的每一行
                Iterator<Row> rowIterator = sheet.iterator();
                while (rowIterator.hasNext()) {

                    line++;
                    if (line == 1) {
                        rowIterator.next();
                        continue;
                    }
                    String code = "";
                    String name = "";
                    String price = "";
                    String detail = "";
                    String doc = "";
                    // 3.3 获取一个row
                    Row row = rowIterator.next();
                    // 3.4 遍历row的每一个单元格
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        // 3.5 获取一个Cell
                        Cell cell = cellIterator.next();
                        // 3.6 检查cell的类型并获取数据
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        switch (cell.getColumnIndex()) {
                            case 0: {
                                //System.out.println(cell.toString());
                                code = cell.getStringCellValue().trim();
                                break;
                            }
                            case 1: {
                                //System.out.println(cell.toString());
                                name = cell.getStringCellValue().trim();
                                break;
                            }
                            case 2: {
                                //System.out.println(cell.toString());
                                detail = cell.getStringCellValue().trim();
                                break;
                            }
                            case 3: {
                                //System.out.println(cell.toString());
                                price = cell.getStringCellValue().trim();
                                break;
                            }
                            case 4: {
                                //System.out.println(cell.toString());
                                doc = cell.getStringCellValue().trim();
                                break;
                            }
                            default:
                                //System.out.println(cell.getStringCellValue().trim());
                                break;
                        }
                    }
                    // 3.7 每一行生成一个对象
                    ItemsInExcel itemsInExcel = new ItemsInExcel();
                    itemsInExcel.setCode(code);
                    itemsInExcel.setName(name);
                    itemsInExcel.setPrice(price);
                    itemsInExcel.setDetail(detail);
                    itemsInExcel.setDoc(doc);
                    infoList.add(itemsInExcel);
                }
            }
            return infoList;
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }
}

