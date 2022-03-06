package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import model.Product;

public class ProductDao {
	public static Product getProduct(DataSource ds, String id) throws SQLException {
		Connection con = ds.getConnection();
		String SQL = "SELECT * FROM Products WHERE product_id = ?";
		PreparedStatement stmt = con.prepareStatement(SQL);
		stmt.setString(1, id);
		ResultSet rs = stmt.executeQuery();
		Product product = null;
		
		while (rs.next()) {
			String name = rs.getString("product_name");
			String description = rs.getString("product_des");
			double price = rs.getDouble("product_price");
			String type = rs.getString("product_type");
			String brand = rs.getString("product_brand");
			String imgURL = rs.getString("product_img_source");
			product = new Product(id, name, description, price, type, brand, imgURL);
		}
		
		return product;
	}
}
