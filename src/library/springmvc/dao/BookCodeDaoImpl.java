package library.springmvc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import library.springmvc.model.Book;
import library.springmvc.model.BookCode;
import library.springmvc.util.MyUtil;
@Repository("bookCodeDao")
public class BookCodeDaoImpl implements IBookCodeDao {

	@Autowired
	private SessionFactory sessionFactory;
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	public void insertBookCode(Book book) {
		for(int i=1;i<=book.getTotalNum();i++){
			BookCode bc=new BookCode();
			bc.setBookId(book.getId());
			bc.setId(MyUtil.createBookCode(book.getId(),book.getBookTypeId(),i));
			getSession().save(bc);
		}
	}
	@Override
	public void deleteByBookId(Long id) {
		getSession().createQuery("delete from BookCode as bc where bc.bookId="+id.toString()).executeUpdate();
	}
	@Override
	public BookCode findById(String id) {
		return getSession().get(BookCode.class, id);
	}
}
