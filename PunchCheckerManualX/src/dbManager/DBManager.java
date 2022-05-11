package dbManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * The class DBManager is tasked with creating the connection to the Data Base containing the list
 * of stores with their respective IDs. This class extracts the name of the stores and IDs from the 
 * data base and stores then in a HashMap<String StoreName, String StoreID>.
 * 
 * Requires at least JDBC 8.0.29 to exist in the class path. 
 * @author Ydiel Z. Flores Torres
 * @see String
 * @see Statement
 * @see ResultSet
 * @see Connection
 * @see HashMap
 */
public class DBManager {
	
	private Statement statement;
	private ResultSet resultSet;
	private Connection connection;
	private String usr = "root";
	private String dbAddress = "jdbc:mysql://localhost:3306/tiendas_el_meson";
	private String pswrd = "elme$on123098";
	
	/**
	 * The DBManager constructor allows us to access the DBManager methods form other classes.
	 */
	public DBManager() {}
	
	/**
	 * The connect() method utilizes private data belonging to the DBManager class to connect to the data base.
	 * 
	 * @see Connection
	 * @see DriverManager
	 */
	public void connect() {
		
		
		try {
			
		
			 connection = DriverManager.getConnection(dbAddress, usr, pswrd);
			
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The sotreLoader() is in charge of sending Query requests to the data base after being connected to it.
	 * This method requests for the list of stores and their respective IDs. After, it will store them in 
	 * HashMap<ID,StoreName> and return it.
	 * 
	 * @return HashMap
	 * @see Statement
	 * @see ResultSet
	 * @see HashMap
	 */
	public HashMap<String,String> storeLoader(){
		
		HashMap<String,String> loadedStores = new HashMap<String,String>();
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select* from tiendas");
			while(resultSet.next()) {
				loadedStores.put(resultSet.getString("id_tiendas"), resultSet.getString("tienda_localizacion"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return loadedStores;
	}
	/**
	 * The method disconnect() will close all connections to the DB
	 */
	public void disconnect() {
		if(resultSet!=null) {
			try {
				resultSet.close();
			}catch (Exception e) {}
		}
		if(statement!=null) {
			try {
				statement.close();
			}catch (Exception e) {}
		}
		if(connection!=null) {
			try {
				connection.close();
			}catch (Exception e) {}
		}
	}
	
}

