package library.springmvc.service;

import java.util.List;

import library.springmvc.model.Borrow;
import library.springmvc.model.Reader;
import library.springmvc.model.ReaderType;

public interface IReaderService {

	List<ReaderType> getAllReaderType();

	void addReader(Reader reader);

	List<Reader> findReaderWithWhere(String where);

	List<Reader> findAllReader();

	void delete(Reader reader) throws Exception;

	Reader findReaderById(Long readerId);

	List<Borrow> getReaderBorrowed(Long readerId);

	int checkBorrow(Long readerId, String bookCode);

	void updateBorrowRenew(Long readerId, String bookCode);

	void updateReaderPwd(Long readerId, String newPwd);

}
