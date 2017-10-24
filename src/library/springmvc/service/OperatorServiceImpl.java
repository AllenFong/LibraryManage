package library.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.springmvc.dao.IOperatorDao;
import library.springmvc.model.Operator;

@Service("operService")
public class OperatorServiceImpl implements IOperatorService{
	@Autowired
	private IOperatorDao operDao;

	@Override
	public void addOperator(Operator oper) {
		// TODO Auto-generated method stub
		operDao.save(oper);
	}

	@Override
	public List<Operator> findAll() {
		// TODO Auto-generated method stub
		return operDao.findAll();
	}

	@Override
	public List<Operator> findWithWhere(String strWhere) {
		// TODO Auto-generated method stub
		return operDao.findWithWhere(strWhere);
	}

	@Override
	public List<Operator> findByColumn(String colName, String value) {
		// TODO Auto-generated method stub
		return operDao.findByColumn(colName, value);
	}

	@Override
	public void update(Operator oper) {
		// TODO Auto-generated method stub
		operDao.update(oper);
	}

	@Override
	public void delete(Operator oper) {
		// TODO Auto-generated method stub
		operDao.delete(oper);
	}

	@Override
	public Operator findById(long id) {
		// TODO Auto-generated method stub
		return operDao.findById(id);
	}

}
