package library.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.springmvc.dao.IOperatorDao;
import library.springmvc.model.Operator;

@Service("operService")
public class OperatorServiceImpl implements IOperatorService {
	@Autowired
	private IOperatorDao operDao;

	public void setOperDao(IOperatorDao operDao) {
		this.operDao = operDao;
	}

	@Override
	public void addOperator(Operator oper) {
		operDao.save(oper);
	}

	@Override
	public List<Operator> findAll() {
		return operDao.findAll();
	}

	@Override
	public List<Operator> findByColumn(String colName, String value) {		
		return operDao.findByColumn(colName, value);
	}

	@Override
	public void update(Operator oper) {
		operDao.update(oper);

	}

	@Override
	public void delete(Operator oper) {
		operDao.delete(oper);

	}

	@Override
	public Operator findById(Long id) {
		return operDao.findById(id);
	}

	@Override
	public List<Operator> findWithWhere(String strWhere) {
		return operDao.findWithWhere(strWhere);
	}

}
