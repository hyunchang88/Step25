package test.main;

import java.sql.Connection;

import test.util.DBConnect;

public class test {
	public static void main(String[] args) {
		Connection conn = new DBConnect().getConn(); 

	}
}
