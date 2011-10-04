package hu.ideaimpl.mageofscrum.client.mvp;

import hu.ideaimpl.mageofscrum.client.place.ErrorPlace;
import hu.ideaimpl.mageofscrum.client.place.RolePlace;
import hu.ideaimpl.mageofscrum.client.place.TeamPlace;
import hu.ideaimpl.mageofscrum.client.place.UsersPlace;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({
	ErrorPlace.Tokenizer.class,
	TeamPlace.Tokenizer.class,
	RolePlace.Tokenizer.class,
	UsersPlace.Tokenizer.class
	})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper{

}
