public class Respondent implements Comparable<Respondent> {
	private String homeZip;
	private String festivalCity;
	private String residencyStatus;
	private double distance;

	public Respondent(String homeZip, String festivalCity) {
		this.homeZip = homeZip;
		this.festivalCity = festivalCity;
		this.residencyStatus = null;
	}

	public Respondent(String homeZip, String festivalCity, String residencyStatus) {

		this.homeZip = homeZip;
		this.festivalCity = festivalCity;
		this.residencyStatus = residencyStatus;
	}

	public Respondent(String homeZip, String festivalCity, double distance) {
		this.homeZip = homeZip;
		this.festivalCity = festivalCity;
		this.distance = distance;
	}

	@Override
	public int compareTo(Respondent respondent) {
		if(this.getDistance() >= respondent.getDistance()) {
			return 1;
		} else {
			return -1;
		}
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

	public double getDistance() {
		return distance;
	}
}
