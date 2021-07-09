import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriter {
	private static String[] columns = {"Residency Zip Code", "Festival City", "Residency Status"};
	private static List<Respondent> checkedRespondents = null;

	public static void writeData(List<Respondent> checkedRespondents, double limit) {
		try {
			Workbook workbook = new XSSFWorkbook();
			CreationHelper createHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("Residency Statuses");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 14);

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			Row headerRow = sheet.createRow(0);

			for(int i = 0; i < columns.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columns[i]);
				cell.setCellStyle(headerCellStyle);
			}

			int totalResidents = 0;
			int rowNum = 1;
			for(Respondent respondent : checkedRespondents) {
				Row row = sheet.createRow(rowNum++);

				row.createCell(0)
						.setCellValue(respondent.getHomeZip());

				row.createCell(1)
						.setCellValue(respondent.getFestivalCity());

				row.createCell(2)
						.setCellValue(respondent.getDistance());
				if(row.getCell(2).getNumericCellValue() < limit) {
					row.createCell(3)
							.setCellValue("Resident");
					totalResidents++;
				} else if(row.getCell(2).getNumericCellValue() == 9999999) {
					row.createCell(3)
							.setCellValue("Could not find zip code - check by hand");
				} else {
					row.createCell(3)
							.setCellValue("-");
				}
			}


			Row row = sheet.createRow(581);
			row.createCell(2)
					.setCellValue("Total residents: " + totalResidents);

			for(int i = 0; i < columns.length; i++) {
				sheet.autoSizeColumn(i);
			}
			FileOutputStream fileOut = new FileOutputStream("CheckedResidencyList.xlsx");
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
