import org.geonames.PostalCode;
import java.util.List;

public class ResidencyComparator {

	public static String isAttendeeResident(Person attendee){
		String attendeeHomeZip = attendee.getHomeZip();
		List<PostalCode> festivalZipCodes = GeonamesClient.getCodesFor(attendee.getFestivalCity());
		for(PostalCode code : festivalZipCodes) {
			if(code.getPostalCode().equals("00000")) {
				return "ERROR- ZIP NOT FOUND";
			}
			if(attendeeHomeZip.equals(code.getPostalCode())) {
				return "1";
			}
		}
		return "2";
	}

	public static boolean checkNumberOfResidents(String residencyStatusToCheck) {
		String resident = "1";
		if(residencyStatusToCheck.equals("1")) {
			return true;
		}
		return false;
	}
}