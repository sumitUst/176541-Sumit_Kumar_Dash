package controller;

import java.sql.Connection;
import java.sql.DriverManager;

import service_repository.BankingOperation;

public class Banking {
	static BankingOperation bo=new BankingOperation();
	public static void main(String[] args) {
		Connection con=null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/", "root", "root");
			System.out.println("**********Welcome******************");
			bo.createDataBase(con);
			System.out.println("***********************************");
			bo.createTable(con);
			System.out.println("***********************************");
			bo.insertData(con);
			System.out.println("***********************************");
			System.out.println("Customer Table");
			System.out.println("**************");
			System.out.println("Customer_ID"+"\tCustomer_Name"+"\tCustomer_Age");
			System.out.println("-----------"+"\t-------------"+"\t------------");
			bo.displayTable(con);
			System.out.println("*********Thank You****************");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally{
			try {
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}
