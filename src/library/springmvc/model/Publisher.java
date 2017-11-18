package library.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="publisher")
public class Publisher {
	@Id
	@Column
	private Short pubId;
	@Column
	private String pubName;
	public Short getPubId() {
		return pubId;
	}
	public void setPubId(Short pubId) {
		this.pubId = pubId;
	}
	public String getPubName() {
		return pubName;
	}
	public void setPubName(String pubName) {
		this.pubName = pubName;
	}
}
