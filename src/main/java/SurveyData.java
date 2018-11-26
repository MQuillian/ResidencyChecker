import java.util.ArrayList;
import java.util.List;

public class SurveyData {
	private ArrayList<Person> surveyedPersons;

	public SurveyData() {
		this.surveyedPersons = new ArrayList<>();
	}

	public void addPerson(Person person){
		surveyedPersons.add(person);
	}

	public void printTestPerson(int index){
		System.out.println("Printing test person at index " + index);
		System.out.println(surveyedPersons.get(index).getFestivalCity());
		System.out.println(surveyedPersons.get(index).getHomeZip());
	}

	public Person getPerson(int index) {
		return surveyedPersons.get(index);
	}

	public List<Person> generateResidencyListFromData() {
		List<Person> residencyList = new ArrayList<>();
		for(Person uncheckedPerson : surveyedPersons) {
			String residencyStatus = ResidencyComparator.isAttendeeResident(uncheckedPerson);
			Person checkedPerson = new Person(uncheckedPerson.getHomeZip(), uncheckedPerson.getFestivalCity(), residencyStatus);
			residencyList.add(checkedPerson);
		}
		return residencyList;
	}
}
