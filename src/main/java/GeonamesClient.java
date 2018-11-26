import org.geonames.GeoNamesException;
import org.geonames.PostalCode;
import org.geonames.PostalCodeSearchCriteria;
import org.geonames.WebService;

import java.util.ArrayList;
import java.util.List;

public class GeonamesClient {

	public static List<PostalCode> getCodesFor(String targetPlace) {
		WebService.setUserName("residencychecker");
		List<PostalCode> results = new ArrayList<>();
		try {
			PostalCodeSearchCriteria criteria = new PostalCodeSearchCriteria();
			criteria.setPlaceName(targetPlace);
			criteria.setRadius(30);
			results = WebService.findNearbyPostalCodes(criteria);
		} catch(GeoNamesException e) {
			PostalCode error = new PostalCode();
			error.setPostalCode("00000");
			results.add(error);
			System.out.println(e.getMessage());
			return results;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return results;
	}

}
