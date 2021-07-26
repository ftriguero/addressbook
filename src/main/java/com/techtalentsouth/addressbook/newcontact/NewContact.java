package com.techtalentsouth.addressbook.newcontact;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NewContact {
		//Id (Primary Key)
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private Long id;
		
		//First Name
		private String firstName;
		
		//Last Name
		private String lastName;
		
		//Phone Number
		private String phoneNumber;
		
		//Email Address
		private String emailAddress;
		
		//In order to stroe in the database.
		public NewContact()
		{
			super();
		}

		public NewContact(String firstName, String lastName, String phoneNumber, String emailAddress) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.phoneNumber = phoneNumber;
			this.emailAddress = emailAddress;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public String getEmailAddress() {
			return emailAddress;
		}

		public void setEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
		}

		public Long getId() {
			return id;
		}

		@Override
		public String toString() {
			return "NewContact [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber="
					+ phoneNumber + ", emailAddress=" + emailAddress + "]";
		}	
		 
}