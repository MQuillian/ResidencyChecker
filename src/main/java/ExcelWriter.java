import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriter {
	private static String[] columns = {"Residency Zip Code", "Festival City", "Residency Status"};
	private static List<Person> checkedPersons = null;

	public static void writeData(List<Person> checkedPersons) {
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

			int numResidents = 0;
			int rowNum = 1;
			for(Person person : checkedPersons) {
				Row row = sheet.createRow(rowNum++);

				row.createCell(0)
						.setCellValue(person.getHomeZip());

				row.createCell(1)
						.setCellValue(person.getFestivalCity());

				row.createCell(2)
						.setCellValue(person.getResidencyStatus());
				if(person.getResidencyStatus().equals("1")) {
					numResidents++;
				};
			}

			Row row = sheet.createRow(582);
			row.createCell(2)
					.setCellValue("Total residents: " + numResidents);

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
