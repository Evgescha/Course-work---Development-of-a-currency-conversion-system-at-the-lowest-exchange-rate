package entity;

public class Currency extends AbstractEntity {
	private String name;

	public Currency() {
		super();
	}

	public Currency(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
