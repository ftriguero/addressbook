package com.techtalentsouth.addressbook.newcontact;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewContactRepository extends CrudRepository<NewContact,Long>{
	
	List<NewContact> findByEmailAddress(String emailAddress);

}
