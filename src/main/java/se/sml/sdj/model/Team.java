package se.sml.sdj.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
public class Team {
	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true, nullable = false)
	private String name;

	@OneToMany(fetch = FetchType.EAGER)
	private Collection<User> users;

	protected Team() {
	}

	public Team(String name) {
		this.name = name;
		this.users = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addUser(User user) {
//		user.setTeam    - BiDirectional
		users.add(user);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	@Override
	public boolean equals(Object otherObj) {
		if (this == otherObj) {
			return true;
		}

		if (otherObj instanceof Team) {
			Team otherTeam = (Team) otherObj;
			return this.name.equals(otherTeam.name);
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result += 37 * name.hashCode();
		return result;
	}
}
