package library.springmvc.dao;

import java.util.List;

import library.springmvc.model.Reader;

public interface IReaderDao {

	void save(Reader reader);

	List<Reader> findReaderWithWhere(String where);

	List<Reader> findAllReader();
	void changePwd(Long readerId,String pwd);
	Reader findById(Long readerId);

	void delete(Reader reader);
	void borrowBook(Long readerId);
	void returnBook(Long readerId);

}
