package br.com.contact.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.contact.factory.ConnectionFactory;
import br.com.contact.model.Contact;

public class ContactDAO {

	/*
	 * CRUD c: CREATE - OK r: READ u: UPDATE d: DELETE
	 */

	public void save(Contact contact) {

		String sql = "INSERT INTO contacts(name, age, dateregister) VALUES (?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Criar uma conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();

			// Criamos uma PreparedStatement, para executar uma query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			// Adicionar os valores que são esperados pela query
			pstm.setString(1, contact.getName());
			pstm.setInt(2, contact.getAge());
			pstm.setDate(3, new Date(contact.getDateRegister().getTime()));

			// Executar a query
			pstm.execute();

			System.out.println("Successfully recorded!");

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			// Fechar as conexões
			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void update(Contact contact) {

		String sql = "UPDATE contacts SET name = ?, age = ?, dateregister = ? " + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Criar conexão com o banco
			conn = ConnectionFactory.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, contact.getName());
			pstm.setInt(2, contact.getId());
			pstm.setDate(3, new Date(contact.getDateRegister().getTime()));

			// Qual o ID do registro que deseja atualizar?
			pstm.setInt(4, contact.getId());

			// Executar a query
			pstm.execute();

		} catch (Exception e) {

		} finally {
			try {
				if (pstm != null) {
					pstm.close();

				}
				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void deleteByID(int id) {

		String sql = "DELETE FROM contacts WHERE id = ?";

		Connection conn = null;

		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = (PreparedStatement) conn.prepareStatement(sql);

			pstm.setInt(1, id);

			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<Contact> getContacts() {
		String sql = "SELECT * FROM contacts";

		List<Contact> contacts = new ArrayList<Contact>();

		Connection conn = null;
		PreparedStatement pstm = null;
		// Classe que recupera os dados do banco ***SELECT***
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = (PreparedStatement) conn.prepareStatement(sql);

			rset = pstm.executeQuery();

			while (rset.next()) {
				Contact contact = new Contact();

				// Recuperar o id
				contact.setId(rset.getInt("id"));
				// Recuperar o nome
				contact.setName(rset.getString("name"));
				// Recuperar a idade
				contact.setAge(rset.getInt("age"));
				// Recuperar a data de cadastro
				contact.setDateRegister(rset.getDate("dateregister"));

				contacts.add(contact);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}

				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return contacts;
	}
}
