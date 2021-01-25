package entity;

import java.sql.Date;

public class ConvertSaver extends AbstractEntity {
	private Currency currencyCurrent;
	private Currency currencyNew;
	private float count;
	private float courseToOneDollar;
	private String where;
	private Date dates;
	private float summ;

	public ConvertSaver() {
		super();
	}

	public ConvertSaver(Currency currencyCurrent, Currency currencyNew, float count, float courseToOneDollar,
			String where, Date dates, float summ) {
		super();
		this.currencyCurrent = currencyCurrent;
		this.currencyNew = currencyNew;
		this.count = count;
		this.courseToOneDollar = courseToOneDollar;
		this.where = where;
		this.dates = dates;
		this.summ = summ;
	}

	public Currency getCurrencyCurrent() {
		return currencyCurrent;
	}

	public void setCurrencyCurrent(Currency currencyCurrent) {
		this.currencyCurrent = currencyCurrent;
	}

	public Currency getCurrencyNew() {
		return currencyNew;
	}

	public void setCurrencyNew(Currency currencyNew) {
		this.currencyNew = currencyNew;
	}

	public float getCount() {
		return count;
	}

	public void setCount(float count) {
		this.count = count;
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

	public Date getDates() {
		return dates;
	}

	public void setDates(Date dates) {
		this.dates = dates;
	}

	public float getSumm() {
		return summ;
	}

	public void setSumm(float summ) {
		this.summ = summ;
	}

}
