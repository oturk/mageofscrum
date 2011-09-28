package hu.ideaimpl.mageofscrum.shared;

import java.io.Serializable;
import java.util.List;

public class UserDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	private String email;
	private String nickname;
	private String fullName;
	private String address;
	private List<TeamDO> teams;
	private List<RoleDO> roles;

	public UserDetails() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public List<TeamDO> getTeams() {
		return teams;
	}

	public void setTeams(List<TeamDO> teams) {
		this.teams = teams;
	}
	
	public List<RoleDO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDO> roles) {
		this.roles = roles;
	}

	public String teamsToString(){
		String result ="";
		for(TeamDO team : teams){
			if(!result.isEmpty()){
				result += ", ";
			}
			result += team.getName();
		}
		return result;
	}
	
	public String rolesToString(){
		String result ="";
		for(RoleDO role : roles){
			if(!result.isEmpty()){
				result += ", ";
			}
			result += role.getName();
		}
		return result.toLowerCase();
	}

}
