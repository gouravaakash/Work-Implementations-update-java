package com.example.demo;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ExcelRangeRandomFiller {

    static class ColumnConfig {
        int columnIndex;
        String columnName;
        double minValue;
        double maxValue;
        boolean isInteger;

        public ColumnConfig(int columnIndex, double minValue, double maxValue, boolean isInteger) {
            this.columnIndex = columnIndex;
            this.columnName = null;
            this.minValue = minValue;
            this.maxValue = maxValue;
            this.isInteger = isInteger;
        }

        public ColumnConfig(String columnName, double minValue, double maxValue, boolean isInteger) {
            this.columnIndex = -1;
            this.columnName = columnName;
            this.minValue = minValue;
            this.maxValue = maxValue;
            this.isInteger = isInteger;
        }

        public double generateRandomValue(Random random) {
            double range = maxValue - minValue;
            double randomValue = minValue + (random.nextDouble() * range);

            if (isInteger) {
                return Math.floor(randomValue);
            } else {
                return randomValue;
            }
        }
    }

    public static void main(String[] args) {
        exampleUsingColumnNames();
    }

    public static void fillColumnsWithRangedValues(String inputPath, String outputPath, List<ColumnConfig> columnConfigs,
                                                   int headerRowIndex, int dataStartRowIndex, int dataEndRowIndex) {
        Random random = new Random();

        try (FileInputStream fis = new FileInputStream(inputPath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            System.out.println("Excel file opened successfully");

            Sheet sheet = workbook.getSheetAt(0);

            Row headerRow = sheet.getRow(headerRowIndex);
            if (headerRow == null) {
                System.out.println("Header row " + headerRowIndex + " not found. Exiting.");
                return;
            }

            int columnCount = headerRow.getLastCellNum();

            System.out.println("Available columns in row " + headerRowIndex + ":");
            for (int j = 0; j < columnCount; j++) {
                Cell cell = headerRow.getCell(j);
                if (cell != null) {
                    String cellValue;
                    if (cell.getCellType() == CellType.STRING) {
                        cellValue = cell.getStringCellValue();
                    } else if (cell.getCellType() == CellType.NUMERIC) {
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    } else {
                        cellValue = "[" + cell.getCellType() + "]";
                    }
                    System.out.println(j + ": " + cellValue);
                }
            }

            for (ColumnConfig config : columnConfigs) {
                if (config.columnName != null) {
                    config.columnIndex = -1;

                    for (int j = 0; j < columnCount; j++) {
                        Cell cell = headerRow.getCell(j);
                        if (cell != null) {
                            String cellValue = "";
                            if (cell.getCellType() == CellType.STRING) {
                                cellValue = cell.getStringCellValue();
                            } else if (cell.getCellType() == CellType.NUMERIC) {
                                cellValue = String.valueOf(cell.getNumericCellValue());
                            }

                            if (config.columnName.equals(cellValue)) {
                                config.columnIndex = j;
                                System.out.println("Found column '" + config.columnName + "' at index " + j);
                                break;
                            }
                        }
                    }

                    if (config.columnIndex == -1) {
                        System.out.println("Column name '" + config.columnName + "' not found. Skipping.");
                    }
                }
            }

            int lastRowNum = sheet.getLastRowNum();
            if (dataEndRowIndex > lastRowNum) {
                dataEndRowIndex = lastRowNum;
                System.out.println("End row adjusted to last row in sheet: " + lastRowNum);
            }

            System.out.println("Processing rows from " + dataStartRowIndex + " to " + dataEndRowIndex + " and filling selected columns");

            for (int i = dataStartRowIndex; i <= dataEndRowIndex; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    row = sheet.createRow(i);
                }

                for (ColumnConfig config : columnConfigs) {
                    if (config.columnIndex < 0 || config.columnIndex >= columnCount) {
                        continue;
                    }

                    Cell cell = row.getCell(config.columnIndex);
                    if (cell == null) {
                        cell = row.createCell(config.columnIndex);
                    }

                    double randomValue = config.generateRandomValue(random);
                    cell.setCellValue(randomValue);
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream(outputPath)) {
                workbook.write(fileOut);
                System.out.println("Excel file has been generated successfully at " + outputPath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exampleUsingColumnNames() {
        String inputPath = "C:\\Users\\shubh\\Downloads\\Copy of sohail(1).xlsx";
        String outputPath = "C:\\Users\\shubh\\Downloads\\output.xlsx";

        int headerRowIndex = 2;

        int dataStartRowIndex = headerRowIndex + 1;

        int dataEndRowIndex = 173;

        List<ColumnConfig> columnsToFill = new ArrayList<>();

        columnsToFill.add(new ColumnConfig("CRP Test", 1, 15, true));
        columnsToFill.add(new ColumnConfig("FVC(L)", 2.46, 3.36, false));
        columnsToFill.add(new ColumnConfig("CFVC(L)T", 2.46, 3.36, false));
        columnsToFill.add(new ColumnConfig("FEV1 (L)", 1.7, 2.53, false));
        columnsToFill.add(new ColumnConfig("pH", 7.35, 7.54, false));
        columnsToFill.add(new ColumnConfig("pCO2", 40, 53, true));
        columnsToFill.add(new ColumnConfig("pO2", 65, 140, true));
        columnsToFill.add(new ColumnConfig("HCO3", 25, 34, true));

        fillColumnsWithRangedValues(inputPath, outputPath, columnsToFill, headerRowIndex, dataStartRowIndex, dataEndRowIndex);
    }
}