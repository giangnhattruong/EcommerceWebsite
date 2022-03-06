package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import model.Order;
import model.Product;

public class OrderDao {
	public static void addOrder(DataSource ds, Order order, Map<Product, Integer> cart)
			throws SQLException {
		Connection con = ds.getConnection();
		String SQL = "INSERT INTO Orders (user_mail, order_status, order_date, order_discount_code, order_address) VALUES (?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(SQL);
		stmt.setString(1, order.getEmail());
		stmt.setInt(2, order.getStatus());
		stmt.setString(3, order.getDate().toString());
		stmt.setString(4, order.getDiscountCode());
		stmt.setString(5, order.getAddress());

		stmt.execute();
		addOrderDetail(ds, order, cart);
	}

	private static int getLastOrderID(DataSource ds, Order order) throws SQLException {
		int id = 0;

		Connection con = ds.getConnection();
		String SQL = "SELECT MAX(order_id) AS order_id FROM Orders WHERE order_date = ? AND order_address = ?";
		PreparedStatement stmt = con.prepareStatement(SQL);
		stmt.setString(1, order.getDate().toString());
		stmt.setString(2, order.getAddress());
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			id = rs.getInt("order_id");
		}

		return id;
	}

	private static void addOrderDetail(DataSource ds, Order order, Map<Product, Integer> cart)
			throws SQLException {
		int id = getLastOrderID(ds, order);

		Connection con = ds.getConnection();
		String SQL = "INSERT INTO Orders_detail (order_id, product_id, amount_product, price_product) VALUES (?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(SQL);
		
		for (Product product: cart.keySet()) {
			stmt.setInt(1, id);
			stmt.setInt(2, Integer.parseInt(product.getId()));
			stmt.setInt(3, cart.get(product));
			stmt.setDouble(4, product.getPrice());
			stmt.execute();
		}
	}

}
