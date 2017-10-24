package library.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//����������ejb��ʵ��bean
@Entity
//ָ�����ݿ��ж�Ӧ��
@Table(name="readers")
public class Reader {
	// ע��ɽ�ʵ��Bean��ĳ�����Զ���Ϊ����
	@Id
	@Column
	private Long readerId;
	@Column
	private String name;
	@Column
	private String identityNum;
	@Column
	private String gender;
	@Column
	private String phone;
	@Column
	private Short readerType;
	@Column
	private Short borrowNum;
	@Column
	private String pwd;
	public Long getReaderId() {
		return readerId;
	}
	public void setReaderId(Long readerId) {
		this.readerId=readerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getIdentityNum() {
		return identityNum;
	}
	public void setIdentityNum(String identityNum) {
		this.identityNum=identityNum;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender=gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone=phone;
	}
	public Short getReaderType() {
		return readerType;
	}
	public void setReaderType(Short readerType) {
		this.readerType=readerType;
	}
	public Short getBorrowNum() {
		return borrowNum;
	}
	public void setBorrowNum(Short borrowNum) {
		this.borrowNum=borrowNum;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd=pwd;
	}

}
