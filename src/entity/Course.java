package entity;

public class Course extends AbstractEntity {
	private Currency currency;
	private float courseToOneDollar;
	private String where;

	public Course() {
		super();
	}

	public Course(Currency currency, float courseToOneDollar, String where) {
		super();
		this.currency = currency;
		this.courseToOneDollar = courseToOneDollar;
		this.where = where;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public float getCourseToOneDollar() {
		return courseToOneDollar;
	}

	public void setCourseToOneDollar(float courseToOneDollar) {
		this.courseToOneDollar = courseToOneDollar;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

}
