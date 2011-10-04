package hu.ideaimpl.mageofscrum.client.place;

import hu.ideaimpl.mageofscrum.shared.Errors;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ErrorPlace extends Place {

	private Errors error;
	
	public ErrorPlace(Errors error) {
		this.error = error;
	}
	
	public Errors getError() {
		return error;
	}

	public static class Tokenizer implements PlaceTokenizer<ErrorPlace>{

		@Override
		public ErrorPlace getPlace(String token) {
			return new ErrorPlace(Errors.valueOf(token.toUpperCase()));
		}

		@Override
		public String getToken(ErrorPlace place) {
			return place.getError().toString().toLowerCase();
		}
		
	}

}
