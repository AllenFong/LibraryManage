package library.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import library.springmvc.model.ReaderType;

@Repository("readerTypeDao")
public class ReaderTypeDaolmpl implements IReaderTypeDao{
	@Autowired
	private SessionFactory sessionFactory;
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	public List<ReaderType> finAll() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from ReaderType").getResultList();
	}
	@Override
	public ReaderType findById(Short readerType) {
		// TODO Auto-generated method stub
		return getSession().get(ReaderType.class, readerType);
	}

}
