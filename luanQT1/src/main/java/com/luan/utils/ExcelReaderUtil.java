package com.luan.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * POI解析Excel
 */
public class ExcelReaderUtil {

    /**
     * 根据fileType不同读取excel文件
     *
     * @param path
     * @param path
     * @throws IOException
     */
    public static List<List<String>> readExcel(String path) {
        String fileType = path.substring(path.lastIndexOf(".") + 1);
        // return a list contains many list
        List<List<String>> lists = new ArrayList<List<String>>();
        //读取excel文件
        InputStream is = null;
        try {
            is = new FileInputStream(path);
            //获取工作薄
            Workbook wb = null;
            if (fileType.equals("xls")) {
                wb = new HSSFWorkbook(is);
            } else if (fileType.equals("xlsx")) {
//                wb = new XSSFWorkbook(is);
            } else {
                return null;
            }

            //读取第一个工作页sheet
            Sheet sheet = wb.getSheetAt(0);
            //第一行为标题
            for (Row row : sheet) {
                ArrayList<String> list = new ArrayList<String>();
                for (Cell cell : row) {
                    //根据不同类型转化成字符串
                    cell.setCellType(CellType.STRING);
                    list.add(cell.getStringCellValue());
                }
                lists.add(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lists;
    }


    /**
     * 创建Excel.xls
     * @param lists 需要写入xls的数据
     * @param titles 列标题
     * @param name  文件名
     * @return
     * @throws IOException
     */
    public static Workbook creatExcel(List<List<String>> lists, String[] titles, String name) throws IOException {
        System.out.println(lists);
        //创建新的工作薄
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet(name);
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for(int i=0;i<titles.length;i++){
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }

        // 创建第一行
        Row row = sheet.createRow((short) 0);

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
//        f.setBoldweight(Font.BOLDWEIGHT_BOLD);
        f.setBold(true);
        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderBottom(BorderStyle.THIN);
        cs.setBorderLeft(BorderStyle.THIN);
        cs.setBorderRight(BorderStyle.THIN);
        cs.setBorderTop(BorderStyle.THIN);
        cs.setAlignment(HorizontalAlignment.CENTER);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderBottom(BorderStyle.THIN);
        cs2.setBorderLeft(BorderStyle.THIN);
        cs2.setBorderRight(BorderStyle.THIN);
        cs2.setBorderTop(BorderStyle.THIN);
        //设置列名
        for(int i=0;i<titles.length;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(cs);
        }
        if(lists == null || lists.size() == 0){
            return wb;
        }
        //设置每行每列的值
        for (short i = 1; i <= lists.size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row1 = sheet.createRow((short)i);
            for(short j=0;j<titles.length;j++){
                // 在row行上创建一个方格
                Cell cell = row1.createCell(j);
                cell.setCellValue(lists.get(i-1).get(j));
                cell.setCellStyle(cs2);
            }
        }
        return wb;
    }

    public static void main(String[] args) throws Exception {
        String path = "D:/2019年04月11日0点班一厂化验表.xls";
        List<List<String>> lists = readExcel(path);
        for (List<String> list : lists) {
            for (String strs : list) {
                System.out.println(strs);
            }
        }
        String[] title= {"编号","车牌号","化验时间","来源地","1","2","3","4","5","6","7","8"};
        Workbook workbook = creatExcel(lists, title, "huay");
        workbook.write(new FileOutputStream("d:/huayan.xls"));
    }
}