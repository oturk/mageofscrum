package hu.ideaimpl.mageofscrum.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class DiagnosePlace extends Place {

	public DiagnosePlace() {
	}

	@Prefix("!diag")
	public static class Tokenizer implements PlaceTokenizer<DiagnosePlace>{

		@Override
		public DiagnosePlace getPlace(String token) {
			return new DiagnosePlace();
		}

		@Override
		public String getToken(DiagnosePlace place) {
			return "diagnose";
		}
		
	}
}
