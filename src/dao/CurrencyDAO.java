package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import entity.Currency;

public class CurrencyDAO {
	private Connection myConn;

	public CurrencyDAO() throws Exception {
		Properties props = new Properties();
		props.load(new FileInputStream("db.properties"));
		String dburl = props.getProperty("dburl");
		myConn = DriverManager.getConnection(dburl, "", "");
		System.out.println("DB Currency connection success");
	}

	public List<Currency> readAll() throws Exception {
		List<Currency> list = new ArrayList<Currency>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM currency");
			while (myRs.next()) {
				Currency tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public List<Currency> search(String name) throws Exception {
		List<Currency> list = new ArrayList<Currency>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			name = "%" + name + "%";
			myStmt = myConn.prepareStatement("SELECT * FROM currency WHERE name LIKE ?");
			myStmt.setString(1, name);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Currency tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void create(Currency entity) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("insert into currency" + " (name)" + " values (?)");
			myStmt.setString(1, entity.getName());
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public Currency read(Long id) throws Exception {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConn.prepareStatement("SELECT * FROM currency WHERE id=?");
			myStmt.setLong(1, id);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				return convertRowToEntity(myRs);
			}

		} finally {
			close(myStmt, myRs);
		}
		return null;
	}

	public void update(String nameNew, String descriptionNew, Float priceNew, Long id) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("UPDATE currency SET name=? WHERE id=?");
			myStmt.setString(1, nameNew);
			myStmt.setLong(2, id);
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public void Delete(Long id) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("DELETE FROM currency WHERE id=?");
			myStmt.setLong(1, id);
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	private Currency convertRowToEntity(ResultSet myRs) throws SQLException {
		String name = myRs.getString("name");
		Long id = myRs.getLong("id");
		Currency temp = new Currency(name);
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
