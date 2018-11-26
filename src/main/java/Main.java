import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		ExcelReader reader = new ExcelReader();

		SurveyData data = reader.readData();
		List<Person> checkedPersons = data.generateResidencyListFromData();
		ExcelWriter.writeData(checkedPersons);
	}

}
