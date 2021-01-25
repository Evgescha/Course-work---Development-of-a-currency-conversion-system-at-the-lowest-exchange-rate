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

import entity.Course;
import entity.Currency;

public class CourseDAO {
	private Connection myConn;

	public CourseDAO() throws Exception {
		Properties props = new Properties();
		props.load(new FileInputStream("db.properties"));
		String dburl = props.getProperty("dburl");
		myConn = DriverManager.getConnection(dburl, "", "");
		System.out.println("DB Course connection success");
	}

	public List<Course> readAll() throws Exception {
		List<Course> list = new ArrayList<Course>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM course");
			while (myRs.next()) {
				Course tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public List<Course> search(String name) throws Exception {
		List<Course> list = new ArrayList<Course>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			name = "%" + name + "%";
			myStmt = myConn.prepareStatement("SELECT * FROM course WHERE currency in (select id from currency where name LIKE ?)");
			myStmt.setString(1, name);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Course tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void create(Course entity) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("insert into course" + " (currency, courseToOneDollar, where)" + " values (?,?,?)");
			myStmt.setLong(1, entity.getCurrency().getId());
			myStmt.setFloat(2, entity.getCourseToOneDollar());
			myStmt.setString(3, entity.getWhere());
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public List<Course> read(Long id) throws Exception {
		List<Course> list = new ArrayList<Course>();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myStmt = myConn.prepareStatement("SELECT * FROM course WHERE id=?");
			myStmt.setLong(1, id);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				Course tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}

			return list;
		} finally {
			close(myStmt, myRs);
		}
	}

	public void update(Course entityPast, Long idPast) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("UPDATE course SET currency=?, courseToOneDollar=?, where=?  WHERE id=?");
			myStmt.setLong(1, entityPast.getCurrency().getId());
			myStmt.setFloat(2, entityPast.getCourseToOneDollar());
			myStmt.setString(3, entityPast.getWhere());
			myStmt.setLong(4, idPast);
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	public void Delete(Long id) throws Exception {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("DELETE FROM course WHERE id=?");
			myStmt.setLong(1, id);
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}

	private Course convertRowToEntity(ResultSet myRs) throws Exception {
		Long idCurrency = myRs.getLong("currency");
		Float courseToOneDollar = myRs.getFloat("courseToOneDollar");
		String where = myRs.getString("where");
		Long id = myRs.getLong("id");
		Currency currency = new CurrencyDAO().read(idCurrency);
		Course temp = new Course(currency, courseToOneDollar, where);
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
