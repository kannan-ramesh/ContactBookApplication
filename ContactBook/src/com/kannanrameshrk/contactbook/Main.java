package com.kannanrameshrk.contactbook;

import java.util.Scanner;

import com.kannanrameshrk.contactbook.addcandidate.AddContactView;

public class Main {
	AddContactView addcontactview = new AddContactView();
	public static void main(String[] args) {
		Main addcontact = new Main();
		addcontact.init();
	}

	private void init() {
		System.out.println("\u001B[34m");
		System.out.println("       Contacts   ");
		System.out.println("   ================   ");
		Scanner input = new Scanner(System.in);
		boolean loop=true;
		do {
			System.out.println("\u001B[31m");
			System.out.println(" 1.View Contacts\n 2.Add Contact\n 3.Delete Contact\n 4.Search Contact\n 5.Update Contact\n 6.Exit");
			System.out.println("\u001B[0m");
			System.out.println("Enter your option:");
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
		
			switch(choice) {
				case 1:{
					addcontactview.viewContact();
					break;
				}
				case 2:{
					addcontactview.addContact();
					break;
				}
				case 3:{
					addcontactview.deleteContact();
					break;
				}
				case 4:{
					addcontactview.searchContact();
					break;
				}
				case 5:{
					addcontactview.updateContact();
					break;
				}
				case 6:{
					loop=false;
					System.out.println("Exit the application..");
					break;
				}
				default:{
					System.out.println("Please Select correct Options:");
					break;
				}
			}
		}while(loop);
	}
}
