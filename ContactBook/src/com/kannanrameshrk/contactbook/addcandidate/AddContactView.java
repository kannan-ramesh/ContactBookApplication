package com.kannanrameshrk.contactbook.addcandidate;

import java.util.List;
import java.util.Scanner;

import com.kannanrameshrk.contactbook.dto.Candidate;

public class AddContactView {
	private AddContactViewModel addcontactviewmodel;
	Scanner input = new Scanner(System.in);

	public AddContactView() {
		this.addcontactviewmodel = new AddContactViewModel(this);
	}

	public void addContact() {
		System.out.println("Enter First Name:");
		String firstName = input.nextLine();
		System.out.println("Enter Last Name:");
		String lastName = input.nextLine();
		System.out.println("Enter Phone Number:");
		String contactNumber = input.nextLine();
		System.out.println("Enter email:");
		String email = input.nextLine();
		Candidate candidate = new Candidate(firstName, lastName, contactNumber, email);

		addcontactviewmodel.validate(candidate);
	}

	public void viewContact() {
		List<Candidate> c = addcontactviewmodel.getContact();
		if (!c.isEmpty()) {
			System.out.println("         Contacts List..");
			System.out.println("\u001B[34m FirstName  LastName  PhoneNumber  Email \u001B[0m");
			System.out.println("\u001B[31m^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\u001B[0m");
			for (Candidate val : c) {
				System.out.println("  " + val.getFirstName() + "    " + val.getLastName() + "    "
						+ val.getContactNumber() + "    " + val.getEmail());
			}

		} else {
			System.out.println("No Contacts in Contact Book");
		}

	}

	public void deleteContact() {
		System.out.println("Eneter Contact Name:");
		String deleteName = input.nextLine();
		addcontactviewmodel.deleteContact(deleteName);
	}

	public void searchContact(){
		System.out.println("Search Contact..");
		System.out.println("------------------");
		System.out.println("Enter person Name:");
		String searchName = input.nextLine();
		
		List<Candidate> searchConatctList = addcontactviewmodel.searchContact(searchName);
		if(searchConatctList !=null) {
			System.out.println("      Search Contacts List..");
			System.out.println("\u001B[34m FirstName  LastName  PhoneNumber  \u001B[0m");
			System.out.println("\u001B[31m^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ \u001B[0m");
			for (Candidate val : searchConatctList) {
				System.out
						.println("  " + val.getFirstName() + "    " + val.getLastName() + "    " + val.getContactNumber());
			}
		}
	}

	public void updateContact() {
		System.out.println("update Contact..");
		System.out.println("\u001B[31m 1.Update Name\n 2.Update Phone Number\n 3.Exit \u001B[0m");
		int choice;
		while (true) {
		    if (input.hasNextInt()) {
		        choice = input.nextInt();
		        break;
		    } else {
		        input.next();
		        System.out.println("Invalid input! Please enter a valid number:");
		    }
		}
		input.nextLine();
		switch(choice) {
			case 1:{
				System.out.println("Enter Old Name:");
				String oldName=input.nextLine();
				System.out.println("Enter New Name:");
				String newName = input.nextLine();
				addcontactviewmodel.updateName(oldName,newName);
				break;
			}
			case 2:{
				System.out.println("Enter Contact Name:");
				String contactName= input.nextLine();
				System.out.println("Enter New Contact Number:");
				String newNumber = input.nextLine();
				if(addcontactviewmodel.validateContactNumber(newNumber)) {
					addcontactviewmodel.updateNumber( contactName,newNumber);
				}
				break;
			}
			case 3:{
				
				break;
			}
			default:
				System.out.println("Invalid choice");
				break;
		}
	}

	public void showError(String errMessage) {
		System.out.println(errMessage);
	}

	public void onSuccess(String onSuccess) {
		System.out.println(onSuccess);
	}
}
