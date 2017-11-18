package library.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reader_type")
public class ReaderType {
	@Id
	@Column
	private Short typeId;
	@Column
	private String typeName;
	@Column
	private Short maxNumber;
	public Short getTypeId() {
		return typeId;
	}
	public void setTypeId(Short typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Short getMaxNumber() {
		return maxNumber;
	}
	public void setMaxNumber(Short maxNumber) {
		this.maxNumber = maxNumber;
	}
}
