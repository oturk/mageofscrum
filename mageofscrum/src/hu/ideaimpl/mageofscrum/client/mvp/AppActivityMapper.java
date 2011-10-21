package hu.ideaimpl.mageofscrum.client.mvp;

import hu.ideaimpl.mageofscrum.client.ClientFactory;
import hu.ideaimpl.mageofscrum.client.activity.BacklogActivity;
import hu.ideaimpl.mageofscrum.client.activity.ErrorActivity;
import hu.ideaimpl.mageofscrum.client.activity.ProfileActivity;
import hu.ideaimpl.mageofscrum.client.activity.ProjectActivity;
import hu.ideaimpl.mageofscrum.client.activity.RoleActivity;
import hu.ideaimpl.mageofscrum.client.activity.SprintActivity;
import hu.ideaimpl.mageofscrum.client.activity.TeamActivity;
import hu.ideaimpl.mageofscrum.client.activity.WelcomeActivity;
import hu.ideaimpl.mageofscrum.client.place.BacklogPlace;
import hu.ideaimpl.mageofscrum.client.place.ErrorPlace;
import hu.ideaimpl.mageofscrum.client.place.ProfilePlace;
import hu.ideaimpl.mageofscrum.client.place.ProjectPlace;
import hu.ideaimpl.mageofscrum.client.place.RolePlace;
import hu.ideaimpl.mageofscrum.client.place.SprintPlace;
import hu.ideaimpl.mageofscrum.client.place.TeamPlace;
import hu.ideaimpl.mageofscrum.client.place.WelcomePlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class AppActivityMapper implements ActivityMapper {
	private final ClientFactory clientFactory;
	
	public AppActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		System.out.println("activityMapper");
		if(place instanceof WelcomePlace){
			return new WelcomeActivity(clientFactory);
		}
		if(place instanceof ErrorPlace){
			return new ErrorActivity(((ErrorPlace) place).getError(), clientFactory);
		}
		if(place instanceof TeamPlace){
			return new TeamActivity(clientFactory);
		}
		if(place instanceof RolePlace){
			return new RoleActivity(clientFactory);
		}
		if(place instanceof ProfilePlace){
			return new ProfileActivity();
		}
		if(place instanceof ProjectPlace){
			return new ProjectActivity();
		}
		if(place instanceof BacklogPlace){
			return new BacklogActivity();
		}
		if(place instanceof SprintPlace){
			return new SprintActivity();
		}
		return null;
	}

}
