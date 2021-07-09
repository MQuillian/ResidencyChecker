import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

public class GoogleMapsClient implements MapClient {
	private static GeoApiContext context = new GeoApiContext.Builder()
			.apiKey("YOUR_API_KEY_HERE")
			.build();
	@Override
	public Coordinates getCoordinates(String address) {
		LatLng result = getGeocode(address);
		return new Coordinates(result.lat, result.lng);
	}
	private static LatLng getGeocode(String target) {
		try {
			GeocodingResult[] results = GeocodingApi.geocode(context, target).await();
			return results[0].geometry.location;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
