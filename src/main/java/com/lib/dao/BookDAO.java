package com.lib.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.lib.exception.LibException;
import com.lib.pojo.Book;
import com.lib.pojo.User;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

@Component
public class BookDAO extends DAO {

	public BookDAO() {
	}
	
	
// ######################################### CRUD OPERATIONS ###########################################	

	public void save(Book book) throws LibException {
		try {
			begin();
			getSession().save(book);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		finally {
			close();
		}
	}

	public void update(Book book) throws LibException {
		try {
			begin();
			getSession().update(book);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}
		finally {
			close();
		}
	}

	public void delete(Book book) throws LibException {
		try {
			begin();
			getSession().delete(book);
			commit();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();
		}finally {
			close();
		}
	}

// ######################################### get all books ###########################################		
	
	public List<Book> getAllBooks() throws LibException {
		List<Book> books = new ArrayList<Book>();
		try {
			begin();
			Criteria criteria = getSession().createCriteria(Book.class);
			books = criteria.list();
			commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return books;

	}
	
// ######################################### get all reserved books ###########################################	
	
	public List<Book> getReservedBooks() throws LibException {

		List<Book> books = new ArrayList<Book>();
		List<Book> reservedBooks = new ArrayList<Book>();

		try {
			begin();
			Criteria criteria = getSession().createCriteria(Book.class);
			books = criteria.list();
			commit();
			for (Book book : books) {
				if (book.getReadyForPickUp() == true) {
					reservedBooks.add(book);
				}
			}

		} catch (HibernateException e) {
//			throw new Exception("Could not list reserved books: ", e);
			e.printStackTrace();

		}finally {
			close();
		}
		return reservedBooks;

	}

	public List<Book> getReservedBooks1() throws LibException {

		List<Book> reservedBooks = new ArrayList<Book>();

		for (Book book : this.getAllBooks()) {
			if (book.getReadyForPickUp() == true) {
				reservedBooks.add(book);
			}
		}

		return reservedBooks;

	}
	
	public LinkedHashMap<Book,String> getReservedAllBooks() throws LibException  {
		LinkedHashMap<Book, String> books = new LinkedHashMap<Book, String>();
		
		for(Book book: this.getAllBooks())
		{
			if(book.getReservedByUser()!=null && book.getTheUser()==null)
			{
				String username=book.getReservedByUser().getUsername();
				books.put(book, username);
			}
		}
		return books;
	}
	
// ######################################### get books in use ###########################################	
	
	public LinkedHashMap<Book,String>getAllBooksInUse() throws LibException {
		LinkedHashMap<Book, String> books = new LinkedHashMap<Book, String>();

		for(Book book : this.getAllBooks())
		{
			if(book.getTheUser()!=null && book.getReservedByUser()==null)
			{
				String username=book.getTheUser().getUsername();
				books.put(book, username);
			}
		}
		return books;
	}
	
// ######################################### get reserved books by user ###########################################		
	
	public List<Book> getReservedBooksByUser(User user) throws LibException {

		List<Book> books=new ArrayList<Book>();
		try {
		begin();
		Query q = getSession().createQuery("from Book where reservedByUser =:user");
		q.setEntity("user", user);
		books = (List<Book>) q.list();
		commit();
		}
		catch(HibernateException e)
		{
//			throw new Exception("Could not Books of user: ", e);
			e.printStackTrace();
		}finally {
			close();
		}
		return books;
	}

// #########################################  books in use by user ###########################################	
	
	public List<Book> getBooksInUserByUser(User user) throws LibException {

		List<Book> books=new ArrayList<Book>();
		try {
		begin();
		Query q = getSession().createQuery("from Book where theUser =:user");
		q.setEntity("user", user);
		books = (List<Book>) q.list();
		commit();
		}
		catch(HibernateException e)
		{
//			throw new Exception("Could not find Books in use of user: ", e);
			e.printStackTrace();
		}finally {
			close();
		}
		return books;
	}

// ######################################### books by id ###########################################	
	
	public Book getBookById(long bookId) throws LibException {

//		Book book=new Book();
		try {
			begin();
			Query q = getSession().createQuery("from Book where bookId = :bookId");
			q.setLong("bookId", bookId);
			Book book = (Book) q.uniqueResult();
			commit();
			return book;
			
		} catch (HibernateException e) {
			rollback();
			throw new LibException("Could not find book by Id: ", e);
//			e.printStackTrace();
		}finally {
			close();
		}
//		return null;
	}
	
// ######################################### check if ISBN exists #####################################
	
	public boolean isbnExists(String isbn) throws LibException {
		
		try {

			Query q = getSession()
					.createQuery("from Book where isbn = :isbn");
			q.setString("isbn", isbn);
			Object obj = q.uniqueResult();
			if (obj == null) {
				return false;
			}

		} catch (Exception e) {
			rollback();
			e.printStackTrace();
		} finally {
			close();
		}
		return true;
		
	}
	
}
