package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Account;

public class AccountDao {
	public static List<Account> getAccounts(DataSource ds) throws SQLException {
		List<Account> accounts = new ArrayList<>();

		Connection con = ds.getConnection();
		String SQL = "SELECT * FROM Account";
		PreparedStatement stmt = con.prepareStatement(SQL);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			String email = rs.getString("user_mail");
			String password = rs.getString("password");
			String name = rs.getString("user_name");
			String address = rs.getString("user_address");
			String phone = rs.getString("user_phone");
			int role = rs.getInt("account_role");
			Account account = new Account(email, password, name, address, phone, role);
			accounts.add(account);
		}

		return accounts;
	}

	public static void addAccount(DataSource ds, Account account) throws SQLException {
		Connection con = ds.getConnection();
		String SQL = "INSERT INTO Account (user_mail, password, user_name, user_address, user_phone, account_role) VALUES (?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(SQL);
		stmt.setString(1, account.getUsername());
		stmt.setString(2, account.getPassword());
		stmt.setString(3, account.getName());
		stmt.setString(4, account.getAddress());
		stmt.setString(5, account.getPhone());
		stmt.setInt(6, account.getRole());
		stmt.execute();
		con.close();
	}
	
	public static void updateAccount(DataSource ds, Account account) throws SQLException {
		Connection con = ds.getConnection();
		String SQL = "UPDATE Account SET password = ?, user_name = ?, user_address = ?, user_phone = ?, account_role = ? WHERE user_mail = ?";
		PreparedStatement stmt = con.prepareStatement(SQL);
		stmt.setString(1, account.getPassword());
		stmt.setString(2, account.getName());
		stmt.setString(3, account.getAddress());
		stmt.setString(4, account.getPhone());
		stmt.setInt(5, account.getRole());
		stmt.setString(6, account.getUsername());
		stmt.execute();
		con.close();
	}
}
