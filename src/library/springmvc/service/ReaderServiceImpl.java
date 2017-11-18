package library.springmvc.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.springmvc.dao.IBookDao;
import library.springmvc.dao.IBorrowDao;
import library.springmvc.dao.IReaderDao;
import library.springmvc.dao.IReaderTypeDao;
import library.springmvc.model.Borrow;
import library.springmvc.model.Reader;
import library.springmvc.model.ReaderType;
@Service("readerService")
public class ReaderServiceImpl implements IReaderService {

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
		return readerTypeDao.findAll();
	}
	@Override
	public void addReader(Reader reader) {
		reader.setBorrowNum((short) 0);
		readerDao.save(reader);
		
	}
	@Override
	public List<Reader> findReaderWithWhere(String where) {		
		return readerDao.findReaderWithWhere(where);
	}
	@Override
	public List<Reader> findAllReader() {
		return readerDao.findAllReader();
	}
	@Override
	public void delete(Reader reader) throws Exception {
		Reader r=readerDao.findById(reader.getReaderId());
		if(r==null)
			throw new Exception("读者不存在");
		if(r.getBorrowNum()!=0)
			throw new Exception("该读者有图书尚未归还,不能删除读者");
		readerDao.delete(reader);
	}
	@Override
	public Reader findReaderById(Long readerId) {
		return readerDao.findById(readerId);
	}
	@Override
	public List<Borrow> getReaderBorrowed(Long readerId) {
		return borrowDao.getBorrowedByReaderId(readerId);
	}
	@Override
	public int checkBorrow(Long readerId, String bookCode) {
		List<Borrow> list=getReaderBorrowed(readerId);
		Reader reader=findReaderById(readerId);
		ReaderType readerType=readerTypeDao.findById(reader.getReaderType());
		if(readerType.getMaxNumber()<=list.size())
			return 1;
		for(int i=0;i<list.size();i++){
			if(list.get(i).getBookCode().equals(bookCode)){
				return 2;
			}
		}
		return 0;
	}
	@Override
	public void updateBorrowRenew(Long readerId, String bookCode) {
		Borrow borrow=borrowDao.getBorrowedByBookCode(bookCode);
		borrow.setIsRenew((short) 1);
		Date   date=new   Date();//取时间 
		Calendar   calendar   =   new   GregorianCalendar(); 
		calendar.setTime(date); 
		calendar.add(Calendar.DATE,30);//把日期往后增加30天
		date=calendar.getTime(); 
		borrow.setLastDate(date);
		borrowDao.update(borrow);
	}
	@Override
	public void updateReaderPwd(Long readerId, String newPwd) {
		readerDao.changePwd(readerId, newPwd);
	}

}
