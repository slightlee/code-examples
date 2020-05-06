package com.tomorrow;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Author Tomorrow
 * @Date 2020/4/25 8:15 下午
 * @Version V1.0
 */
public class ExcelWriteTest {

    /**
     * 03版测试
     */
    @Test
    public void testWrite03() throws Exception {

        // 创建一个工作薄 03
        /**
         *  Ctrl+H 可以看到 Workbook 接口下面的实现类
         *  XSSFWorkbook   03版
         *  SXSSFWorkbook  07版加速
         *  HSSFWorkbook   07版
         */

        Workbook workbook = new XSSFWorkbook();

        // 创建一个工作表
        /**
         *  就是常见 excel 下方的 sheet1 sheet2 ..
         *  这里可以写名字 比如 ：学生成绩统计表
         */
        Sheet sheet = workbook.createSheet("学生成绩统计表");

        // 创建 第一行
        /**
         *  0 默认是第一行
         */
        Row row1 = sheet.createRow(0);

        // 创建列
        Cell cell11 = row1.createCell(0);

        // 第一个格子赋值
        cell11.setCellValue("小明");

        Cell cell12 = row1.createCell(1);

        // 第二个格子赋值
        cell12.setCellValue("85");


        // 第二行
        Row row2 = sheet.createRow(1);

        // 创建列
        Cell cell21 = row2.createCell(0);

        // 第一个格子赋值
        cell21.setCellValue("统计时间");

        Cell cell22 = row2.createCell(1);

        String time = new DateTime().toString("yyyy-MM-dd HH:mm:ss");

        // 第二个格子赋值
        cell22.setCellValue(time);



        // 生成一张表（IO 流 ）
        // 定义生成位置
        String Path = "/Users/ming/local/IdeaProjects/code-examples/poi-demo/";

        FileOutputStream fileOutputStream = new FileOutputStream(Path +"学生成绩统计表03.xls");

        workbook.write(fileOutputStream);

        // 关闭流
        fileOutputStream.close();

        System.out.println("文件生成完毕");
    }


    /**
     * 07版测试
     */
    @Test
    public void testWrite07() throws Exception {

        // 创建一个工作薄 07
        /**
         *  Ctrl+H 可以看到 Workbook 接口下面的实现类
         *  XSSFWorkbook   03版
         *  SXSSFWorkbook  07版加速
         *  HSSFWorkbook   07版
         */

        Workbook workbook = new HSSFWorkbook();

        // 创建一个工作表
        /**
         *  就是常见 excel 下方的 sheet1 sheet2 ..
         *  这里可以写名字 比如 ：学生成绩统计表
         */
        Sheet sheet = workbook.createSheet("学生成绩统计表");

        // 创建 第一行
        /**
         *  0 默认是第一行
         */
        Row row1 = sheet.createRow(0);

        // 创建列
        Cell cell11 = row1.createCell(0);

        // 第一个格子赋值
        cell11.setCellValue("小明");

        Cell cell12 = row1.createCell(1);

        // 第二个格子赋值
        cell12.setCellValue("85");


        // 第二行
        Row row2 = sheet.createRow(1);

        // 创建列
        Cell cell21 = row2.createCell(0);

        // 第一个格子赋值
        cell21.setCellValue("统计时间");

        Cell cell22 = row2.createCell(1);

        String time = new DateTime().toString("yyyy-MM-dd HH:mm:ss");

        // 第二个格子赋值
        cell22.setCellValue(time);



        // 生成一张表（IO 流 ）
        // 定义生成位置
        String Path = "/Users/ming/local/IdeaProjects/code-examples/poi-demo/";

        FileOutputStream fileOutputStream = new FileOutputStream(Path +"学生成绩统计表07.xlsx");

        workbook.write(fileOutputStream);

        // 关闭流
        fileOutputStream.close();

        System.out.println("文件生成完毕");
    }


    /**
     *  读
     */
    @Test
    public void testRead07() throws Exception {

        // 创建一个工作薄 07
        /**
         *  Ctrl+H 可以看到 Workbook 接口下面的实现类
         *  XSSFWorkbook   03版
         *  SXSSFWorkbook  07版加速
         *  HSSFWorkbook   07版
         */

        String Path = "/Users/ming/local/IdeaProjects/code-examples/poi-demo/";

        FileInputStream fileInputStream = new FileInputStream(Path + "ESC.xls");

        Workbook workbook = new HSSFWorkbook(fileInputStream);

        Sheet sheetAt = workbook.getSheetAt(0);

        Row row = sheetAt.getRow(0);

        List<String> list = new ArrayList<String>();
        // 总行数
        int physicalNumberOfRows = sheetAt.getPhysicalNumberOfRows();
        Cell cell = row.getCell(0);

        for (int i = 1; i <= physicalNumberOfRows; i++) {
            Cell cell2 = row.getCell(0);
            String stringCellValue = cell.getStringCellValue();
            list.add(stringCellValue);
        }

        System.out.println("文件读取完毕" + cell.getStringCellValue());
        System.out.println("文件读取完毕" + list.toString());
        fileInputStream.close();
    }


    @Test
    public void testRead() throws Exception {

        // 创建一个工作薄 07
        /**
         *  Ctrl+H 可以看到 Workbook 接口下面的实现类
         *  XSSFWorkbook   03版
         *  SXSSFWorkbook  07版加速
         *  HSSFWorkbook   07版
         */

        String Path = "/Users/ming/local/IdeaProjects/code-examples/poi-demo/";

        FileInputStream fileInputStream = new FileInputStream(Path + "ESC.xls");

        Workbook workbook = new HSSFWorkbook(fileInputStream);

        Sheet sheetAt = workbook.getSheetAt(0);

        List<String> list = new ArrayList<String>();
        // 总行数
        int physicalNumberOfRows = sheetAt.getPhysicalNumberOfRows();

        for (int i = 0; i < physicalNumberOfRows; i++) {
            Row row = sheetAt.getRow(i);
            Cell cell = row.getCell(0);
            String stringCellValue = cell.getStringCellValue();
            list.add(stringCellValue);
        }

        System.out.println("文件读取完毕" + list.toString());
        fileInputStream.close();
    }
}
