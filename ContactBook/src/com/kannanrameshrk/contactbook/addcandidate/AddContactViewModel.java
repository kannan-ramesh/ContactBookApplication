package com.kannanrameshrk.contactbook.addcandidate;

import java.util.List;
import java.util.Map;

import com.kannanrameshrk.contactbook.dto.Candidate;
import com.kannanrameshrk.contactbook.repository.ContactBookRepository;

 class AddContactViewModel {
	private AddContactView addcontactview;

	public AddContactViewModel(AddContactView addCandidateView) {
		this.addcontactview = addCandidateView;
	}

	public void validate(Candidate candidate) {
		 boolean isFirstNameValid = validateFirstName(candidate);
	     boolean validateContactNumber = validateContactNumber(candidate);
	     boolean isEmailValid = validateEmail(candidate);
	     
	     if(isFirstNameValid && validateContactNumber && isEmailValid ) {
	    	 ContactBookRepository.getInstance().insertContact(candidate);
	    	 this.addcontactview.onSuccess("Your Contact is Saved");
	     }
	}

	private boolean validateEmail(Candidate candidate) {
		if (!candidate.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
	        this.addcontactview.showError("Invalid Email - Please Check");
	        return false;
	    }
	    return true;
	}

	private boolean validateContactNumber(Candidate candidate) {
		if(candidate.getContactNumber().length()!=10 && !candidate.getContactNumber().matches("\\\\d{10}")) {
			this.addcontactview.showError("Invalid Phone Number");
			return false;
		}
		return true;
	}

	private boolean validateFirstName(Candidate candidate) {
		if(!(candidate.getFirstName().length()>3 && candidate.getFirstName().length()<40)) {
			this.addcontactview.showError("Invalid Name - Name length should be min 3 and Max 50");
			return false;
		}
		return true;
	}

	public List<Candidate> getContact() {
		 ContactBookRepository repository = ContactBookRepository.getInstance();
		 List<Candidate> allContacts = repository.getAllContacts();
		 
		return allContacts;
	}

	public void deleteContact(String deleteName) {
		 ContactBookRepository repository=ContactBookRepository.getInstance();
		 if(repository.deleteContactByName(deleteName)) {
			 this.addcontactview.onSuccess("Sucessfully deleted contact");
		 }else {
			 this.addcontactview.showError("Not found this Name");
		 }
		 
	}

	public List<Candidate> searchContact(String searchName) {
		ContactBookRepository repository = ContactBookRepository.getInstance();
		List<Candidate> searchConatctList=repository.searchContactName(searchName);
		if(!searchConatctList.isEmpty()) {
			return searchConatctList;
		}else {
			 this.addcontactview.showError("Not found this Name");
			 return null;
		}
	}

	public void updateName(String oldName, String newName) {
		Map<String,Candidate> contact=ContactBookRepository.getInstance().updateName(oldName, newName);
		if(contact.isEmpty()) {
			this.addcontactview.showError("No Contacts in Your Contact Book..");
		}
		for(Map.Entry<String, Candidate> person:contact.entrySet()) {
			if(person.getKey().equals(oldName)) {
				person.getValue().setFirstName(newName);
				this.addcontactview.onSuccess("Successfully Changed Name...");
			}else {
				this.addcontactview.showError("No Contact Name in Your Contacts");
			}
		}
	}

	public void updateNumber(String contactName, String newNumber) {
		Map<String,Candidate> contact=ContactBookRepository.getInstance().updateNumber(contactName, newNumber);
		
		if(contact.isEmpty()) {
			this.addcontactview.showError("No Contacts in Your Contact Book..");
		}
		
		for(Map.Entry<String, Candidate> person:contact.entrySet()) {
			if(person.getKey().equals(contactName)) {
				person.getValue().setContactNumber(newNumber);
				this.addcontactview.onSuccess("Successfully Changed Number...");
			}else {
				this.addcontactview.showError("No Contact Name in Your Contacts");
			}
		}
	}

	public boolean validateContactNumber(String newNumber) {
		if(newNumber.length()!=10 && !newNumber.matches("\\\\d{10}")) {
			this.addcontactview.showError("Invalid Phone Number");
			return false;
		}
		return true;
	}
}
