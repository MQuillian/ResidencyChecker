import java.util.List;

public class Main {
	public static void main(String[] args) {
		String inputFilePath = args[0];
		int desiredSampleSize = Integer.parseInt(args[1]);
		try {
			ExcelReader reader = new ExcelReader();

			SurveyData data = reader.readData(inputFilePath);

			DistanceCalculator calculator = new DistanceCalculator(new GeonamesClient("residencychecker"));
			List<Respondent> results = data.generateUnsortedListFromData(calculator);

			double distanceLimitForDesiredSampleSize = data.findRadiusLimitForSampleSize(results, desiredSampleSize);
			ExcelWriter.writeData(results, distanceLimitForDesiredSampleSize);

		} catch(Exception e) {
			e.printStackTrace();
		}






	}

}
