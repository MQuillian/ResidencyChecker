import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelReader {
	private static SurveyData surveyData;

	public ExcelReader() {
		this.surveyData = new SurveyData();
	}

	public static final String SURVEY_RESPONSES_FILE_PATH = "ResidentsTouristsTest.xlsx";

	public static SurveyData readData() {

		try {
			Workbook workbook = WorkbookFactory.create(new File(SURVEY_RESPONSES_FILE_PATH));

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
				cellValue = dataFormatter.formatCellValue(cell);
				homeZip = cellValue;

				surveyData.addPerson(new Person(homeZip, festivalAddress));
			}
			workbook.close();
			return surveyData;
		} catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}


}