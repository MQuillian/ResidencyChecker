import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SurveyData {
	private ArrayList<Respondent> surveyedRespondents;

	public SurveyData() {
		this.surveyedRespondents = new ArrayList<>();
	}

	public void addPerson(Respondent respondent){
		surveyedRespondents.add(respondent);
	}

	public List<Respondent> generateUnsortedListFromData(DistanceCalculator calculator) {
		List<Respondent> unsortedList = new ArrayList<>();
		int count = 0;
		for(Respondent uncheckedRespondent : surveyedRespondents) {
			count++;
			System.out.println(count + ": Checking distance for " + uncheckedRespondent.getFestivalCity());
			double distance = calculator.calculateDistanceForRespondent(uncheckedRespondent);
			Respondent respondentWithDistance = new Respondent(uncheckedRespondent.getHomeZip(), uncheckedRespondent.getFestivalCity(), distance);
			unsortedList.add(respondentWithDistance);
		}

		return unsortedList;
	}

	public double findRadiusLimitForSampleSize(List<Respondent> unsortedList, int desiredSampleSize) {
		if(desiredSampleSize > unsortedList.size()) {
			throw new IllegalArgumentException("Desired sample size [" + desiredSampleSize
					+ "] canont be larger than list size [" + unsortedList.size() + "]");
		}
		List<Respondent> sortedList = new ArrayList<>(unsortedList);
		Collections.sort(sortedList);
		double limit = sortedList.get(desiredSampleSize).getDistance();
		return limit;
	}
}
