package info.ostaszewski.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import info.ostaszewski.model.Request;

public class JDBCController {

	private final String JDBC_DRIVER = "org.h2.Driver";
	private final String DB_URL = "jdbc:h2:file:~/testdb";
	private final String USER = "sa";
	private final String PASS = "admin";
	Connection conn = null;
	Statement stmt = null;

	public JDBCController() {

	}

	public void createTable() {

		conn = null;
		stmt = null;
		try {
			// STEP 1: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 2: Open a connection
			// System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 3: Execute a query
			stmt = conn.createStatement();

			String sql = "CREATE TABLE IF NOT EXISTS ORDERS("
					+ "client_Id VARCHAR(6),request_id BIGINT,name VARCHAR(255),quantity INTEGER,price DOUBLE(2))";

			stmt.executeUpdate(sql);
			// System.out.println("Created table in given database...");

			// STEP 4: Clean-up environment
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
			// System.out.println("Goodbye!");

	}

	public void insertToTable(List<Request> listOfRequests) {
		String clientId;
		long requestId;
		String name;
		int quantity;
		double price;
		String sql;
		conn = null;
		stmt = null;
		try {
			// STEP 1: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// STEP 2: Open a connection
			//System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			//System.out.println("Connected database successfully...");

			// STEP 3: Execute a query
			stmt = conn.createStatement();
			for (int i = 0; i < listOfRequests.size(); i++) {
				clientId = listOfRequests.get(i).getClientId();
				requestId = listOfRequests.get(i).getRequestId();
				name = listOfRequests.get(i).getName();
				quantity = listOfRequests.get(i).getQuantity();
				price = listOfRequests.get(i).getPrice();
				sql = "INSERT INTO Orders " + "VALUES (" + clientId + "," + requestId + "," + "'" + name + "'" + ","
						+ quantity + "," + price + ")";
				stmt.executeUpdate(sql);
			}

			// stmt.executeUpdate("DELETE FROM Orders");

			/*
			 * sql = "INSERT INTO Registration " + "VALUES (101, 'Mahnaz', 'Fatma', 25)";
			 * 
			 * stmt.executeUpdate(sql); sql = "INSERT INTO Registration " +
			 * "VALUES (102, 'Zaid', 'Khan', 30)";
			 * 
			 * stmt.executeUpdate(sql); sql = "INSERT INTO Registration " +
			 * "VALUES(103, 'Sumit', 'Mittal', 28)";
			 * 
			 * stmt.executeUpdate(sql);
			 */
			//System.out.println("Inserted records into the table...");

			// STEP 4: Clean-up environment
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		//System.out.println("Goodbye!");
	}

	public void dropTabel(String tableName) {

		conn = null;
		stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			stmt.executeQuery("DROP TABLE " + tableName);
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

	}

	public long getNumberOfOrders() {

		conn = null;
		stmt = null;
		long result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT COUNT (Client_id)" + "FROM Orders");

			if (rs.next()) {
				result = rs.getLong(1);
			}

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}
		return result;
	}

	public long getNumberOfOrders(String client_Id) {

		conn = null;
		stmt = null;
		long result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT COUNT (Client_id)" + "FROM Orders" + " WHERE Client_id =" + "'" + client_Id + "'");

			if (rs.next()) {
				result = rs.getLong(1);
			}

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}
		return result;
	}

	public long getTotalAmountOfOrders(String client_Id) {

		conn = null;
		stmt = null;
		long result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT SUM(Price) " + "FROM Orders" + " WHERE Client_id =" + "'" + client_Id + "'");

			if (rs.next()) {
				result = rs.getLong(1);
			}

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}
		return result;
	}

	public long getTotalAmountOfOrders() {

		conn = null;
		stmt = null;
		long result = 0;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT SUM (Price)" + "FROM Orders");

			if (rs.next()) {
				result = rs.getLong(1);
			}

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}
		return result;
	}

	public List<Request> getListOfOrders(String client_Id) {

		List<Request> list = new ArrayList<>();
		conn = null;
		stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT * " + "FROM Orders" + " WHERE Client_id =" + "'" + client_Id + "'");

			while (rs.next()) {

				String clientId = rs.getString("Client_id");
				long requestId = rs.getLong("Request_id");
				String name = rs.getString("name");
				int quantity = rs.getInt("quantity");
				double price = rs.getDouble("price");

				list.add(new Request(clientId, requestId, name, quantity, price));
			}

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}
		return list;
	}

	public List<Request> getListOfOrders() {

		List<Request> list = new ArrayList<>();
		conn = null;
		stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * " + "FROM Orders");

			while (rs.next()) {

				String clientId = rs.getString("Client_id");
				long requestId = rs.getLong("Request_id");
				String name = rs.getString("name");
				int quantity = rs.getInt("quantity");
				double price = rs.getDouble("price");

				list.add(new Request(clientId, requestId, name, quantity, price));
			}

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}
		return list;
	}

	public double getAvarageValueOfOrders() {

		double sum = 0;
		int quantity=0;
		
		conn = null;
		stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * " + "FROM Orders");

			while (rs.next()) {
				sum=sum+rs.getDouble("price");
				quantity=quantity+1;
			}
			
			

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}
		return sum/quantity;
	}
	
	public double getAvarageValueOfOrders(String client_Id) {

		double sum = 0;
		int quantity=0;
		
		conn = null;
		stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * " + "FROM Orders" + " WHERE Client_id =" + "'" + client_Id + "'");

			while (rs.next()) {
				sum=sum+rs.getDouble("price");
				quantity=quantity+1;
			}
			
			

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}
		return sum/quantity;
	}

}
