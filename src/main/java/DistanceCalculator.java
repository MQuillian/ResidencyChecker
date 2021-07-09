import com.google.maps.model.LatLng;

public class DistanceCalculator {

	private MapClient mapClient;

	public DistanceCalculator(MapClient mapClient) {
		this.mapClient = mapClient;
	}

	public double calculateDistanceForRespondent(Respondent respondent) {
		try {
			Coordinates festivalCoords = mapClient.getCoordinates(respondent.getFestivalCity());
			Coordinates homeCoords = mapClient.getCoordinates(respondent.getHomeZip());

			double theta = festivalCoords.getLongitude() - homeCoords.getLongitude();
			double distance = Math.sin(Math.toRadians(festivalCoords.getLatitude())) * Math.sin(Math.toRadians(homeCoords.getLatitude()))
					+ Math.cos(Math.toRadians(festivalCoords.getLatitude())) * Math.cos(Math.toRadians(homeCoords.getLatitude()))
					* Math.cos(Math.toRadians(theta));

			distance = Math.acos(distance);
			distance = Math.toDegrees(distance);
			distance = distance * 60 * 1.1515 * 1.609344;

			return distance;
		} catch(Exception e) {
			System.out.println("Error in " + respondent.getFestivalCity() + " - setting distance to 9999999");
			return 9999999;
		}
	}
}
