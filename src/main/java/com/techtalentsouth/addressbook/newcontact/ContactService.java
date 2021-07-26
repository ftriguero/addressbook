package com.techtalentsouth.addressbook.newcontact;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    NewContactRepository newContactRepository;
    
    @Autowired
    NewContactController newContactController;

    // Returns a list with all entries
    List<NewContact> listAllEntries()
    {
        return (List<NewContact>) newContactRepository.findAll();
    }

    // Returns first entry of list of all users by email
    public NewContact getEntryByEmailAddress(String emailAddress) 
    {
       List<NewContact> entries = newContactRepository.findByEmailAddress(emailAddress);
       
       // If list size is 0, no entries were found
       if (entries.size() == 0)
       {
          return null; 
       }
       
       return entries.get(0); 
    }
    

}
