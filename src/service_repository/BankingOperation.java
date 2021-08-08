package service_repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import entity.Customers;


public class BankingOperation {

	// To create Database
	public void createDataBase(Connection con) {
		try {
			Statement st = con.createStatement();
			st.executeUpdate("create database Bank");
			System.out.println("Database created successfully");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// To create Table
	public void createTable(Connection con) {
		try {
			Statement st = con.createStatement();
			st.executeUpdate("use bank");
			st.executeUpdate("create table Customer_Details"
					+ "(Customer_ID int Auto_Increment primary key,"
					+ "Customer_Name varchar(25) not null,"
					+ "Customer_Age int not null)");
			System.out.println("Table created successfully");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Insert data into table from User's Input
	public void insertData(Connection con) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the no of customers:");
		int n = sc.nextInt();
		entity.Customers c[] = new entity.Customers[n];

		// Taking input from the user
		for (int i = 0; i < c.length; i++) {
			String name;
			int age;
			c[i] = new Customers();
			System.out.println("Customer-" + (i + 1));
			System.out.println("*************");
			System.out.println("Enter Name:");
			name = sc.next();
			c[i].setcName(name);
			System.out.println("Enter Age:");
			age = sc.nextInt();
			c[i].setcAge(age);
			System.out.println("*************");
		}
		sc.close();

		// Insert the data into table
		for (int i = 0; i < c.length; i++) {
			try {
				PreparedStatement ps = con
						.prepareStatement("insert into Customer_Details" +
								"(Customer_Name,Customer_Age) values(?,?)");
				ps.setString(1, c[i].getcName());
				ps.setInt(2, c[i].getcAge());
				ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		System.out.println("Record updated successfully");
	}
	
	//To display table records
	public void displayTable(Connection con) throws SQLException
	{
		Statement stmt = con.createStatement();
		ResultSet rs = stmt
				.executeQuery("select * from Customer_Details");
		while (rs.next()) {
			System.out.println("\t"+rs.getInt(1) + "\t" + rs.getString(2) + "\t\t"
					+ rs.getInt(3));
		}
	}
}
