public class Person {
	private String homeZip;
	private String festivalCity;
	private String residencyStatus;

	public Person(String homeZip, String festivalCity) {
		this.homeZip = homeZip;
		this.festivalCity = festivalCity;
		this.residencyStatus = null;
	}

	public Person(String homeZip, String festivalCity, String residencyStatus) {

		this.homeZip = homeZip;
		this.festivalCity = festivalCity;
		this.residencyStatus = residencyStatus;
	}

	public String getHomeZip() {
		return homeZip;
	}

	public String getFestivalCity() {
		return festivalCity;
	}

	public String getResidencyStatus() {
		return residencyStatus;
	}
}
