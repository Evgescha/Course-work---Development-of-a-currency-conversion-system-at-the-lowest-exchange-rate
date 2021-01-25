package entity;

import java.sql.Date;

public class ConvertSaver extends AbstractEntity {
	private Currency currencyCurrent;
	private Currency currencyNew;
	private float count;
	private String courseToOneDollar;
	private String where;
	private Date dates;
	private float summ;

	public ConvertSaver() {
		super();
	}

	public ConvertSaver(Currency currencyCurrent, Currency currencyNew, float count, String courseToOneDollar,
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

	public String getCourseToOneDollar() {
		return courseToOneDollar;
	}

	public void setCourseToOneDollar(String courseToOneDollar) {
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

	@Override
	public String toString() {
		return "Из " + currencyCurrent.getName() + " в " + currencyNew.getName() + " в размере " + count +"\n"
				+courseToOneDollar + ",\n"
				+ where
				+ ",\n В новой валюте выйдет "+ summ;
	}

}
