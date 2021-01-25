package entity;

import java.io.Serializable;

public class AbstractEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
