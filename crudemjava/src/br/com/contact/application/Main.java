package br.com.contact.application;

import java.util.Date;

import br.com.contact.dao.ContactDAO;
import br.com.contact.model.Contact;

public class Main {

	public static void main(String[] args) {

		ContactDAO contactDao = new ContactDAO();

		Contact contact = new Contact();

		contact.setName("Pia Kamarowski");
		contact.setAge(24);
		contact.setDateRegister(new Date());

		// contactDao.save(contact);

		// Atualizar o contato
		Contact c1 = new Contact();
		c1.setName("Pia Lovato Kamarowski");
		c1.setAge(84);
		c1.setDateRegister(new Date());
		c1.setId(23);

		// contactDao.update(c1);

		// Deletar o contato pelo seu Número de ID
		// contactDao.deleteByID(20);

		// Visualização dos registros do banco de dados TODOS
		for (Contact c : contactDao.getContacts()) {
			System.out.println("Contatc: " + c.getName());

		}

	}

}
