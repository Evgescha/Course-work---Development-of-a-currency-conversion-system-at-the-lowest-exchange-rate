package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import controller.ApplicationController;
import entity.ConvertSaver;
import entity.Currency;

public class ConvertSaverDAO {
	private Connection myConn;

	public ConvertSaverDAO() throws Exception {
		Properties props = new Properties();
		props.load(new FileInputStream("db.properties"));
		String dburl = props.getProperty("dburl");
		myConn = DriverManager.getConnection(dburl, "", "");
		System.out.println("DB ConvertSaver connection success");
	}

	public List<ConvertSaver> readAll() throws Exception {
		List<ConvertSaver> list = new ArrayList<ConvertSaver>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM сonvertSaver");
			while (myRs.next()) {
				ConvertSaver tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public List<ConvertSaver> search(String name) throws Exception {
		List<ConvertSaver> list = new ArrayList<ConvertSaver>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			name = "%" + name + "%";
			myStmt = myConn.prepareStatement("SELECT * FROM сonvertSaver WHERE currencyCurrent in (select id from currency where name LIKE ?)");
			myStmt.setString(1, name);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				ConvertSaver tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void create(ConvertSaver entity) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("insert into сonvertSaver" 
									+ " (currencyCurrent, currencyNew, count, courseToOneDollar, where, dates, summ)" 
									+ " values (?,?,?,?,?,?,?)");
			myStmt.setLong(1, entity.getCurrencyCurrent().getId());
			myStmt.setLong(2, entity.getCurrencyNew().getId());
			myStmt.setFloat(3, entity.getCount());
			myStmt.setString(4, entity.getCourseToOneDollar());
			myStmt.setString(5, entity.getWhere());
			myStmt.setDate(6, entity.getDates());
			myStmt.setFloat(7, entity.getSumm());
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public List<ConvertSaver> read(Long id) throws Exception {
		List<ConvertSaver> list = new ArrayList<ConvertSaver>();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConn.prepareStatement("SELECT * FROM convertSaver WHERE id=?");
			myStmt.setLong(1, id);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				ConvertSaver tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void update(ConvertSaver entityNew, Long idPast) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("UPDATE сonvertSaver SET "
											+ "currencyCurrent=?, currencyNew=?, count=?, courseToOneDollar=?, where=?, dates=?, summ=?"
											+ "  WHERE id=?");
			myStmt.setLong(1, entityNew.getCurrencyCurrent().getId());
			myStmt.setLong(2, entityNew.getCurrencyNew().getId());
			myStmt.setFloat(3, entityNew.getCount());
			myStmt.setString(4, entityNew.getCourseToOneDollar());
			myStmt.setString(5, entityNew.getWhere());
			myStmt.setDate(6, entityNew.getDates());
			myStmt.setFloat(7, entityNew.getSumm());
			myStmt.setLong(8, idPast);
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public void Delete(Long id) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("DELETE FROM сonvertSaver WHERE id=?");
			myStmt.setLong(1, id);
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	private ConvertSaver convertRowToEntity(ResultSet myRs) throws Exception {
		Long id = myRs.getLong("id");
		Long idcurrencyCurrent = myRs.getLong("currencyCurrent");
		Long idcurrencyNew= myRs.getLong("currencyNew");
		Float count= myRs.getFloat("count");
		String courseToOneDollar= myRs.getString("courseToOneDollar");
		String where = myRs.getString("where");
		Date dates = myRs.getDate("dates");
		Float summ= myRs.getFloat("summ");
		
		Currency currencyCurrent  =ApplicationController.currencyController.DAO.read(idcurrencyCurrent );
		Currency currencyNew  = ApplicationController.currencyController.DAO.read(idcurrencyNew );
		
		ConvertSaver temp = new ConvertSaver(currencyCurrent, currencyNew, count, courseToOneDollar, where, dates, summ);
		temp.setId(id);
		return temp;
	}

	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException {
		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			myStmt.close();
		}

		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);
	}

	private void close(Statement myStmt) throws SQLException {
		close(null, myStmt, null);
	}
}
