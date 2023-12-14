package com.kannanrameshrk.contactbook.addcandidate;

import java.util.List;

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
	     //boolean isEmailValid = validateEmail(candidate);
	     
	     if(isFirstNameValid && validateContactNumber ) {
	    	 ContactBookRepository.getInstance().insertContact(candidate);
	    	 this.addcontactview.onSuccess("Your Contact is Saved");
	     }
	}

	private boolean validateEmail(Candidate candidate) {
		if (candidate.getEmail() == null || !candidate.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,}$")) {
	        this.addcontactview.showError("Invalid Email - Please Check");
	        return false;
	    }
	    return true;
	}

	private boolean validateContactNumber(Candidate candidate) {
		if(candidate.getContactNumber().length()!=10) {
			this.addcontactview.showError("Invalid Phone Number - Number length should be min 10 and Max 10");
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
		if(searchConatctList != null) {
			return searchConatctList;
		}else {
			 this.addcontactview.showError("Not found this Name");
			 return null;
		}
	}
}
