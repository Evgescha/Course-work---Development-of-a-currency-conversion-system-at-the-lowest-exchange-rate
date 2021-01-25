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
// Класс для взаимодействия с таблицей валют
public class CurrencyDAO {
	// текущее подключение к бд
	private Connection myConn;
// конструктор по умолчанию
	public CurrencyDAO() throws Exception {
		//Создаем тип свойства и читаем файл с свойствами
		Properties props = new Properties();
		props.load(new FileInputStream("db.properties"));
		// из файла получаем строку подключения к бд
		String dburl = props.getProperty("dburl");
		//подключаемся по этой строке к бд
		myConn = DriverManager.getConnection(dburl, "", "");
		System.out.println("DB Currency connection success");
	}
// метод получения всех записей из бл
	public List<Currency> readAll() throws Exception {
		// заранее создаем список
		List<Currency> list = new ArrayList<Currency>();
		// и параметры запроса
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// выполняем запрос к бд
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM currency");
			while (myRs.next()) {
				// каждую полученную запись преобразуем в сущность и записываем в лист
				Currency tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}
			// возвращаем лист вызывающему методу
			return list;
		} finally {
			close(myStmt, myRs);
		}
	}
	// метод поиска в бд записей по части названия
	public List<Currency> search(String name) throws Exception {
		List<Currency> list = new ArrayList<Currency>();
		// создаем переменный для параметров запроса
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// выполняем запрос
			name = "%" + name + "%";
			myStmt = myConn.prepareStatement("SELECT * FROM currency WHERE name LIKE ?");
			myStmt.setString(1, name);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				// каждую полученную запись преобразуем в сущность и записываем в лист
				Currency tempEntity = convertRowToEntity(myRs);
				list.add(tempEntity);
			}
			// возвращаем лист вызывающему методу
			return list;
		} finally {
			close(myStmt, myRs);
		}
	}
	// метод добавления записи в бд, на вход принимает сущность, которую нужно сохранить
	public void create(Currency entity) throws Exception {
		PreparedStatement myStmt = null;
		try {
			// выполняем запрос к бд
			myStmt = myConn.prepareStatement("insert into currency" + " (name)" + " values (?)");
			myStmt.setString(1, entity.getName());
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}
	// метод чтения по ид записи
	public Currency read(Long id) throws Exception {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			// выполняем запрос и возвращаем результат, если он есть
			myStmt = myConn.prepareStatement("SELECT * FROM currency WHERE id=?");
			myStmt.setLong(1, id);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				return convertRowToEntity(myRs);
			}

		} finally {
			close(myStmt, myRs);
		}
		// если его нет, возвращаем нулевой результат
		return null;
	}
	// метод обновления, принимает на вход новое имя валюты и ид записи в бд, которую обновляем
	public void update(String nameNew, Long id) throws Exception {
		PreparedStatement myStmt = null;
		try {
			// выполняем запрос к бд, передав все параметры
			myStmt = myConn.prepareStatement("UPDATE currency SET name=? WHERE id=?");
			myStmt.setString(1, nameNew);
			myStmt.setLong(2, id);
			myStmt.executeUpdate();
		} finally {
			close(myStmt);
		}
	}
	// метод удаления записи из бд, на вход принимает ид удаляемой записи
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
