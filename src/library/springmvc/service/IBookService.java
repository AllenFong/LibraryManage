package library.springmvc.service;

import java.util.List;

import library.springmvc.model.Book;
import library.springmvc.model.BookType;
import library.springmvc.model.Publisher;

public interface IBookService {
	public List<BookType> getAllBookType();
	public Book findBookById(Long id);
	public List<Publisher> getAllPublisher();
	public void addBook(Book book);
	public List<Book> findBookWithWhere(String where);
	public List<Book> findAllBooks();
	public void delete(Book book);
	public void update(Book book);
	void addBorrow(Long ReaderId,String BookCode,Long operId);
	void addReturn(String BookCode,Long opId);

}
