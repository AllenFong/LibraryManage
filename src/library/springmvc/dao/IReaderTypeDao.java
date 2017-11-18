package library.springmvc.dao;

import java.util.List;

import library.springmvc.model.ReaderType;

public interface IReaderTypeDao {
	public List<ReaderType> findAll();

	public ReaderType findById(Short readerType);
}
