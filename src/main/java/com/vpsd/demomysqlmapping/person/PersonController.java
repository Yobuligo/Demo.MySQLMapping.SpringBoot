package com.vpsd.demomysqlmapping.person;

import org.hibernate.bytecode.internal.bytebuddy.BulkAccessorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vpsd.demomysqlmapping.BuddyNotFoundException;
import com.vpsd.demomysqlmapping.InterestNotFoundException;
import com.vpsd.demomysqlmapping.PersonNotFoundException;
import com.vpsd.demomysqlmapping.interest.IInterestRepository;
import com.vpsd.demomysqlmapping.interest.Interest;

@RestController
public class PersonController {

	@Autowired
	private IPersonRepository personRepository;

	@Autowired
	private IInterestRepository interestRepository;

	@GetMapping("/persons")
	public Iterable<Person> findAllPersons() {
		return personRepository.findAll();
	}

	@GetMapping("/persons/{id}")
	public Person findById(@PathVariable("id") Long id) {
		return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
	}

	@PostMapping("/persons")
	public Person createPerson(@RequestBody Person personRequest) {
		return personRepository.save(personRequest);
	}

	@PutMapping("/persons/{id}")
	public Person updatePerson(@PathVariable("id") Long id, @RequestBody Person personRequest) {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
		person.setFirstname(personRequest.getFirstname());
		person.setLastname(personRequest.getLastname());
		return personRepository.save(person);
	}

	@DeleteMapping("/persons/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable("id") Long id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
		personRepository.delete(person);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/persons/{id}/interests")
	public Iterable<Interest> findPersonsInterests(@PathVariable("id") Long id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
		return person.getInterests();
	}

	@PostMapping("/persons/{personId}/interests/{interestId}")
	public Interest createPersonsInterest(@PathVariable("personId") Long personId,
			@PathVariable("interestId") Long interestId) {
		Person person = personRepository.findById(personId).orElseThrow(() -> new PersonNotFoundException(personId));
		Interest interest = interestRepository.findById(interestId)
				.orElseThrow(() -> new InterestNotFoundException(interestId));
		person.addInterest(interest);
		personRepository.save(person);
		return interest;
	}

	@DeleteMapping("/persons/{personId}/interests/{interestId}")
	public ResponseEntity<?> deletePersonsInterest(@PathVariable("personId") Long personId,
			@PathVariable("interestId") Long interestId) {
		Person person = personRepository.findById(personId).orElseThrow(() -> new PersonNotFoundException(personId));
		Interest interest = person.findInterestById(interestId);
		if (interest == null) {
			throw new InterestNotFoundException(interestId);
		}

		person.deleteInterest(interest);
		personRepository.save(person);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/persons/{personId}/buddies")
	public Iterable<Person> getBuddies(@PathVariable("personId") Long personId) {
		Person person = personRepository.findById(personId).orElseThrow(() -> new PersonNotFoundException(personId));
		return person.getBuddies();
	}

	@PostMapping("/persons/{personId}/buddies/{buddyId}")
	public Person addBuddy(@PathVariable("personId") Long personId, @PathVariable("buddyId") Long buddyId) {
		Person person = personRepository.findById(personId).orElseThrow(() -> new PersonNotFoundException(personId));
		Person buddy = personRepository.findById(buddyId).orElseThrow(() -> new BuddyNotFoundException(buddyId));
		person.addBuddy(buddy);
		personRepository.save(person);
		return buddy;
	}

	@DeleteMapping("/persons/{personId}/buddies/{buddyId}")
	public ResponseEntity<?> deletePersonsBuddy(@PathVariable("personId") Long personId,
			@PathVariable("buddyId") Long buddyId) {
		Person person = personRepository.findById(personId).orElseThrow(() -> new PersonNotFoundException(personId));
		Person buddy = person.findBuddyById(buddyId);
		if (buddy == null) {
			throw new BuddyNotFoundException(buddyId);
		}
		
		person.deleteBuddy(buddy);
		personRepository.save(person);
		return null;
	}
}
