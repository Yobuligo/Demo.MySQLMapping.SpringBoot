package com.vpsd.demomysqlmapping.interest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vpsd.demomysqlmapping.InterestNotFoundException;

@RestController
public class InterestController {

	@Autowired
	private IInterestRepository interestRepository;

	@GetMapping("/interests")
	public Iterable<Interest> findAllInterests() {
		System.out.println("find all interests");
		return interestRepository.findAll();
	}

	@GetMapping("/interests/{id}")
	public Interest findById(@PathVariable("id") Long id) {
		return interestRepository.findById(id).orElseThrow(() -> new InterestNotFoundException(id));
	}

	@PostMapping("/interests")
	public Interest createInterest(@RequestBody Interest interest) {
		return interestRepository.save(interest);
	}

	@PutMapping("/interests/{id}")
	public Interest updateInterest(@PathVariable("id") Long id, @RequestBody Interest interestRequest) {
		Interest interest = interestRepository.findById(id).orElseThrow(() -> new InterestNotFoundException(id));
		interest.setText(interestRequest.getText());
		return interestRepository.save(interest);
	}

	@DeleteMapping("/interests/{id}")
	public ResponseEntity<?> deleteInterest(@PathVariable("id") Long id) {
		Interest interest = interestRepository.findById(id).orElseThrow(() -> new InterestNotFoundException(id));
		interestRepository.delete(interest);
		return ResponseEntity.ok().build();
	}
}
