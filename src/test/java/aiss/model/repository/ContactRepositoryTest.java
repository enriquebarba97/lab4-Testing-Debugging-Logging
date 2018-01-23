package aiss.model.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import aiss.model.Contact;

public class ContactRepositoryTest {

	private ContactRepository repository;
	
	@Before
	public void setUp() throws Exception {
		repository=ContactRepository.getInstance();
		repository.init();
		repository.addContact("Test name 1", "000000000");
		repository.addContact("Test name 2", "000000001");
		repository.addContact("Test name 3", "000000002");
		
	}

	@Test
	public void testGetContacts() {
		Map<String,Contact> contacts=repository.getContacts();
		
		assertNotNull("The list of contacts is null",contacts);
		assertTrue("The list of contacts is empty",contacts.size()>=3);
	}
	
	@Test
	public void testGetContact() {
		Contact contact = repository.getContact("c0");
		
		assertNotNull("The contact is null",contact);
	}

	@Test
	public void testUpdateContact() {
		String id = "c1"; 
		Contact contact = repository.getContact(id);			
		String oldName = contact.getName();
		String oldPhone = contact.getTelephone();
		
		// Update contact
		contact.setName(oldName + "Test");
		contact.setTelephone("000000003");
		repository.updateContact(contact);
		
		// Get updated contact
		contact = repository.getContact(id);			
		
		assertFalse("The name has not been updated correctly",oldName.equals(contact.getName()));
		assertFalse("The phone number has not been updated correctly",oldPhone.equals(contact.getTelephone()));
	}

	@Test
	public void testAddContact() {
		int numberContacts=repository.getContacts().size();
		repository.addContact("Test name 4", "000000004");
		int newNumberContacts=repository.getContacts().size();
		
		assertTrue("The contact has not been added correctly", newNumberContacts==numberContacts+1);
	}

	@Test
	public void testDeleteContact() {
		String id = "c2";
		int numberContacts=repository.getContacts().size();
		repository.deleteContact(id);
		int newNumberContacts=repository.getContacts().size();
		
		Contact c=repository.getContact(id);
		
		assertNull("The contact has not been deleted", c);
		assertTrue("The contact has not been delected correctly", newNumberContacts==numberContacts-1);
	}
}
