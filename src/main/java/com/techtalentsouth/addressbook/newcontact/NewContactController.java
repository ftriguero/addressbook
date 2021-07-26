package com.techtalentsouth.addressbook.newcontact;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NewContactController {
	
		@Autowired
		private NewContactRepository newContactRepository;
		private static List<NewContact> posts = new ArrayList<>();
		private ContactService contactService;
		
		@Autowired
		public void setContactService(ContactService contactService)
		{
			this.contactService = contactService;
		}
		//Returns Main Page
		@GetMapping("/")
		public String index(Model model)
		{
			posts.removeAll(posts);
			for( NewContact post:newContactRepository.findAll())
			{
				posts.add(post);
			}
			model.addAttribute("posts", posts);
			return "newcontact/index";
		}
		
		@GetMapping ("/contactlist/new")
		public String newEntry(NewContact newContact)
		{
			return "newcontact/new";
		}
		
		@PostMapping("/contactlist")
		public String addNewNewContact(NewContact newContact, Model model)
		{
			newContactRepository.save(newContact);
			model.addAttribute("newcontact", newContact);
			return "newcontact/result";
		}
		@RequestMapping(value = "/contactlist/{id}", method=RequestMethod.GET)
	    public String editPostWithId(@PathVariable Long id, NewContact newContact, Model model) 
		{
	        Optional<NewContact> post = newContactRepository.findById(id);
	        if (post.isPresent()) 
	        {
	            NewContact actualContact = post.get();
	            model.addAttribute("newContact", actualContact);
	        }
	        return "newcontact/edit";
	    }
	@RequestMapping(value="/contactlist/update/{id}")
	public String updateExistingContact(@PathVariable Long id, NewContact newContact, Model model)
	{
		 	Optional<NewContact> post = newContactRepository.findById(id);
		 	if (post.isPresent()) 
		    {
		 		NewContact actualContact = post.get();
		 		actualContact.setFirstName(newContact.getFirstName());
		 		actualContact.setLastName(newContact.getLastName());
		 		actualContact.setPhoneNumber(newContact.getPhoneNumber());
		 		actualContact.setEmailAddress(newContact.getEmailAddress());
		 		newContactRepository.save(actualContact);
		 		model.addAttribute("newContact", newContact);
		    }
		 	return "newcontact/result";
	}
		
	@RequestMapping(value="/contactlist/delete/{id}")
	public String deletePostById(@PathVariable Long id, NewContact newContact, Model model)
	{
		Optional<NewContact> post = newContactRepository.findById(id);
		if (post.isPresent()) 
	        {
			newContactRepository.deleteById(id);
			model.addAttribute("newContact", post.get());
	        }
		return "newcontact/delete";
	}
	 @GetMapping("/searchresult")
	    public String search(NewContact newContact, Model model)
	    {
	        return "newcontact/searchresult";
	    }

	    @PostMapping("/searchresult")
	    public String searchResult(NewContact newContact, Model model)
	    {
	        // Creates a list of entries and stores entries with that email
	        List<NewContact> entries = newContactRepository.findByEmailAddress(newContact.getEmailAddress());

	        // If there are no entries in the list, return the error page
	        if(entries.size() == 0)
	        {
	            return "newcontact/error";
	        }

	        // Grab the first entry of that list
	        NewContact firstEntry = entries.get(0);

	        // Add the attributes of the first entry to output in searchresult.html
	        model.addAttribute("firstName", firstEntry.getFirstName());
	        model.addAttribute("lastName", firstEntry.getLastName());
	        model.addAttribute("phoneNumber", firstEntry.getPhoneNumber());
	        model.addAttribute("email", firstEntry.getEmailAddress());

	        return "newcontact/searchresult";
	    }

	    @GetMapping(value = "/contactlist/print")
	    public String printAll(NewContact newContact, Model model)
	    {
	        // List all entries from entryService
	        model.addAttribute("entries", contactService.listAllEntries());

	        return "newcontact/print";
	    }

	    @GetMapping(value = "/contactlist/delete")
	    public String deleteAll(NewContact newContact, Model model)
	    {
	        // Deletes all entries in repository
	        newContactRepository.deleteAll();

	        return "newcontact/delete";
	    }	
	
}
