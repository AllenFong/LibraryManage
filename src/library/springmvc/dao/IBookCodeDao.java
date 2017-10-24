package library.springmvc.dao;

import library.springmvc.model.Book;
import library.springmvc.model.BookCode;

public interface IBookCodeDao {
	public void insertBookCode(Book book);
	public void deleteByBookId(Long id);
	public BookCode findById(String id);

}
