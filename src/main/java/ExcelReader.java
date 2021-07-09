import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class ExcelReader {
	private static SurveyData surveyData;

	public ExcelReader() {
		this.surveyData = new SurveyData();
	}

	public static SurveyData readData(String inputFilePath) {

		try {
			Workbook workbook = WorkbookFactory.create(new File(inputFilePath));

			Iterator<Sheet> sheetIterator = workbook.sheetIterator();
			while(sheetIterator.hasNext()) {
				Sheet sheet = sheetIterator.next();
			}

			Sheet sheet = workbook.getSheetAt(0);
			DataFormatter dataFormatter = new DataFormatter();

			Iterator<Row> rowIterator = sheet.rowIterator();
			String festivalAddress = "";
			String homeZip = "";
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();

				Cell cell = row.getCell(0);
				String cellValue = dataFormatter.formatCellValue(cell);
				festivalAddress = cellValue;

				cell = row.getCell(1);
				int code = (int)cell.getNumericCellValue();
				String formattedCode = String.format("%05d", code);
				homeZip = formattedCode;

				surveyData.addPerson(new Respondent(homeZip, festivalAddress));
			}
			workbook.close();
			return surveyData;
		} catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}


}