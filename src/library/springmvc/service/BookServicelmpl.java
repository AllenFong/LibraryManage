package library.springmvc.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.springmvc.dao.IBookCodeDao;
import library.springmvc.dao.IBookDao;
import library.springmvc.dao.IBookTypeDao;
import library.springmvc.dao.IBorrowDao;
import library.springmvc.dao.IPublisherDao;
import library.springmvc.dao.IReaderDao;
import library.springmvc.model.Book;
import library.springmvc.model.BookCode;
import library.springmvc.model.BookType;
import library.springmvc.model.Borrow;
import library.springmvc.model.Publisher;
@Service("bookService")
public class BookServicelmpl implements IBookService{
	@Autowired
	private IBookTypeDao bookTypeDao;
	@Autowired
	private IPublisherDao pubDao;
	@Autowired
	private IBookDao bookDao;
	@Autowired
	private IBookCodeDao bookCodeDao;
	@Autowired
	private IReaderDao readerDao;
	@Autowired
	private IBorrowDao borrowDao;

	@Override
	public List<BookType> getAllBookType() {
		// TODO Auto-generated method stub
		return bookTypeDao.findAll();
	}

	@Override
	public Book findBookById(Long id) {
		// TODO Auto-generated method stub
		return bookDao.findById(id);
	}

	@Override
	public List<Publisher> getAllPublisher() {
		// TODO Auto-generated method stub
		return pubDao.findAll();
	}

	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		book.setCurrrentNum(book.getTotalNum());
		Long bookId=bookDao.save(book);
		book.setId(bookId);
		bookCodeDao.insertBookCode(book);
	}

	@Override
	public List<Book> findBookWithWhere(String where) {
		// TODO Auto-generated method stub
		return bookDao.findBookWithWhere(where);
	}

	@Override
	public List<Book> findAllBooks() {
		// TODO Auto-generated method stub
		return bookDao.findAllBooks();
	}

	@Override
	public void delete(Book book) {
		// TODO Auto-generated method stub
		bookDao.delete(book);
		bookCodeDao.deleteByBookId(book.getId());
	}

	@Override
	public void update(Book book) {
		// TODO Auto-generated method stub
		Book src=bookDao.findById(book.getId());
		if(src.getTotalNum()!=book.getTotalNum()||src.getBookTypeId()!=book.getBookTypeId()) {
			//����ͼ��������仯,��Ҫ���½��б��
			bookCodeDao.deleteByBookId(book.getId());
			bookCodeDao.insertBookCode(book);
			book.setCurrrentNum(book.getTotalNum());
		}
		bookDao.updata(book);
	}

	@Override
	public void addBorrow(Long ReaderId, String BookCode, Long operId) {
		// TODO Auto-generated method stub
		Borrow borrow=new Borrow();
		borrow.setReaderId(ReaderId);
		borrow.setBookCode(BookCode);
		borrow.setBorrowDate(new Date());
		borrow.setBorrowOperId(operId);
		borrow.setIsRenew((short) 0);
		borrow.setIsReturn((short) 0);
		Date date=new Date();
		
		Calendar calendar=new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 30);
		date=calendar.getTime();
		borrow.setLastDate(date);
		borrowDao.save(borrow);
		readerDao.borrowBook(ReaderId);
		BookCode bc=bookCodeDao.findById(BookCode);
		Book book=bookDao.findById(bc.getBookId());
		book.setCurrrentNum(book.getCurrentNum()-1);
		bookDao.updata(book);
	}

	@Override
	public void addReturn(String BookCode, Long opId) {
		// TODO Auto-generated method stub
		
	}

}
