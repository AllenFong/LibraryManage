package library.springmvc.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.springmvc.dao.IBorrowDao;
import library.springmvc.dao.IReaderDao;
import library.springmvc.dao.IReaderTypeDao;
import library.springmvc.model.Borrow;
import library.springmvc.model.Reader;
import library.springmvc.model.ReaderType;
@Service("readerService")
public class ReaderServicelmpl implements IReaderService{
	@Autowired
	private IReaderTypeDao readerTypeDao;
	@Autowired
	private IReaderDao readerDao;
	@Autowired
	private IBorrowDao borrowDao;
	@Autowired
	private IBookService bookService;

	@Override
	public List<ReaderType> getAllReaderType() {
		// TODO Auto-generated method stub
		return readerTypeDao.findAll();
	}

	@Override
	public void addReader(Reader reader) {
		// TODO Auto-generated method stub
		reader.setBorrowNum((short) 0);
		readerDao.save(reader);
	}

	@Override
	public List<Reader> findReaderWithWhere(String where) {
		// TODO Auto-generated method stub
		return readerDao.findReaderWithWhere(where);
	}

	@Override
	public List<Reader> findAllReader() {
		// TODO Auto-generated method stub
		return readerDao.findAllReader();
	}

	@Override
	public void delete(Reader reader) throws Exception {
		// TODO Auto-generated method stub
		Reader r=readerDao.findById(reader.getReaderId());
		if(r==null)
			throw new Exception("用户不存在");
		if(r.getBorrowNum()!=0)
			throw new Exception("");
		readerDao.delete(reader);
	}

	@Override
	public Reader findReaderById(Long readerId) {
		// TODO Auto-generated method stub
		return readerDao.findById(readerId);
	}

	@Override
	public List<Borrow> getReaderBorrowed(Long readerId) {
		// TODO Auto-generated method stub
		return borrowDao.getBorrowedByReaderId(readerId);
	}

	@Override
	public int checkBorrow(Long readerId, String bookCode) {
		// TODO Auto-generated method stub
		List<Borrow> list=getReaderBorrowed(readerId);
		Reader reader=findReaderById(readerId);
		ReaderType readerType=readerTypeDao.findById(reader.getReaderType());
		if(readerType.getMaxNumber()<=list.size())
			return 1;
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getBookCode().equals(bookCode)) {
				return 2;
			}
		}
		return 0;
	}

	@Override
	public void updateBorrowRenew(Long readerId, String bookCode) {
		// TODO Auto-generated method stub
		Borrow borrow=borrowDao.getBorrowedByBookCode(bookCode);
		borrow.setIsRenew((short) 1);
		Date date=new Date();
		Calendar calendar=new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE,30);
		date=calendar.getTime();
		borrow.setLastDate(date);
		borrowDao.update(borrow);
	}

	@Override
	public void updateReaderPwd(Long readerId, String newPwd) {
		// TODO Auto-generated method stub
		readerDao.changePwd(readerId, newPwd);
	}

}
