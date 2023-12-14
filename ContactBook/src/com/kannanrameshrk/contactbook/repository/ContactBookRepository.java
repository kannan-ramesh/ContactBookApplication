package com.kannanrameshrk.contactbook.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.kannanrameshrk.contactbook.dto.Candidate;

public class ContactBookRepository {
	private static ContactBookRepository repository;
	private  Map<String,Candidate> map=new HashMap<>();
	private List<Candidate> searchContact=new ArrayList<>();
	
	private ContactBookRepository() {
		
	}
	
	public static ContactBookRepository getInstance() {
		if(repository==null) {
			repository = new ContactBookRepository();
		}
		return repository;
	}

	public void insertContact(Candidate candidate) {
		map.put(candidate.getFirstName(),candidate);
	}

	public List<Candidate> getAllContacts() {
		return new ArrayList<>(map.values());
	}

	public boolean deleteContactByName(String deleteName) {
		if(map.containsKey(deleteName)) {
			map.remove(deleteName);
			return true;
		}else {
			return false;
		}
		
	}

	public List<Candidate> searchContactName(String searchName) {
		for(Candidate sc:map.values()) {
			if(sc.getFirstName().equalsIgnoreCase(searchName)) {
				searchContact.add(sc);
			}
		}
		return searchContact;
	}
}
