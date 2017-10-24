package library.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import library.springmvc.model.Reader;
@Repository("readerDao")
public class ReaderDaolmpl implements IReaderDao{
	@Autowired
	private SessionFactory sessionFactory;
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(Reader reader) {
		// TODO Auto-generated method stub
		getSession().save(reader);
	}

	@Override
	public List<Reader> findReaderWithWhere(String where) {
		// TODO Auto-generated method stub
		String sql="from Reader where "+where;
		return getSession().createQuery(sql).getResultList();
	}

	@Override
	public List<Reader> findAllReader() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Reader").getResultList();
	}

	@Override
	public void changePwd(Long readerId, String pwd) {
		// TODO Auto-generated method stub
		String sql="update Reader set pwd='"+pwd+"' where readerId="+readerId;
		getSession().createQuery(sql).executeUpdate();
	}

	@Override
	public Reader findById(Long readerId) {
		// TODO Auto-generated method stub
		return getSession().get(Reader.class, readerId);
	}

	@Override
	public void delete(Reader reader) {
		// TODO Auto-generated method stub
		getSession().delete(reader);
	}

	@Override
	public void borrowBook(Long readerId) {
		// TODO Auto-generated method stub
		String sql="update Reader set borrowNum=borrowNum+1 where readerId="+readerId;
		getSession().createQuery(sql).executeUpdate();
	}

	@Override
	public void returnBook(Long readerId) {
		// TODO Auto-generated method stub
		String sql="update Reader set borrowNum=borrowNum-1 where readerId="+readerId;
		getSession().createQuery(sql).executeUpdate();
	}

}
