package com.vpsd.demomysqlmapping.person;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vpsd.demomysqlmapping.interest.Interest;
import com.vpsd.demomysqlmapping.model.Model;

@Entity
public class Person extends Model {

	@Size(max = 100)
	private String firstname;

	@Size(max = 100)
	private String lastname;

	@OneToMany(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "createdAt", "changedAt" })
	private List<Interest> interests;

	@OneToMany(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "createdAt", "changedAt", "buddies", "interests" })
	private List<Person> buddies;

	public List<Person> getBuddies() {
		return buddies;
	}

	public void addBuddy(Person buddy) {
		List<Person> buddies = getBuddies();
		buddies.add(buddy);
	}

	public void setBuddies(List<Person> buddies) {
		this.buddies = buddies;
	}

	public void deleteBuddy(Person buddy) {
		buddies.remove(buddy);
	}

	public List<Interest> getInterests() {
		return interests;
	}

	public void setInterests(List<Interest> interests) {
		this.interests = interests;
	}

	public void addInterest(Interest interest) {
		List<Interest> interests = getInterests();
		interests.add(interest);
	}

	public void deleteInterest(Interest interest) {
		interests.remove(interest);
	}

	public Interest findInterestById(Long id) {
		for (Interest interest : interests) {
			if (interest.getId() == id)
				return interest;
		}

		return null;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Person findBuddyById(Long buddyId) {
		for (Person buddy : buddies) {
			if (buddy.getId() == buddyId)
				return buddy;
		}

		return null;

	}

}
