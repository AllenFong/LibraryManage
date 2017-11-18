package library.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import library.springmvc.model.Reader;
@Repository("readerDao")
public class ReaderDaoImpl implements IReaderDao {
	@Autowired
	private SessionFactory sessionFactory;
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	public void save(Reader reader) {
		getSession().save(reader);

	}

	@Override
	public List<Reader> findReaderWithWhere(String where) {
		String sql="from Reader where "+where;		
		return getSession().createQuery(sql).getResultList();
	}

	@Override
	public List<Reader> findAllReader() {
		return getSession().createQuery("from Reader").getResultList();
	}

	@Override
	public void changePwd(Long readerId, String pwd) {
		String sql="update Reader set pwd='"+pwd+"' where readerId="+readerId;
		getSession().createQuery(sql).executeUpdate();
	}

	@Override
	public Reader findById(Long readerId) {
		return getSession().get(Reader.class, readerId);
	}
	@Override
	public void delete(Reader reader) {
		getSession().delete(reader);
		
	}
	@Override
	public void borrowBook(Long readerId) {
		String sql="update Reader set borrowNum=borrowNum+1 where readerId="+readerId;
		getSession().createQuery(sql).executeUpdate();
	}
	@Override
	public void returnBook(Long readerId) {
		String sql="update Reader set borrowNum=borrowNum-1  where readerId="+readerId;
		getSession().createQuery(sql).executeUpdate();
	}

}
