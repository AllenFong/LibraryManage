package library.springmvc.service;

import java.util.List;

import library.springmvc.model.Book;
import library.springmvc.model.BookType;
import library.springmvc.model.Publisher;

public class BookServicelmpl implements IBookService{

	@Override
	public List<BookType> getAllBookType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book findBookById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Publisher> getAllPublisher() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Book> findBookWithWhere(String where) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> findAllBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBorrow(Long ReaderId, String BookCode, Long operId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addReturn(String BookCode, Long opId) {
		// TODO Auto-generated method stub
		
	}

}
