package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDAO {
	private String url = "jdbc:mysql://localhost:3306/usermanagement";
	private String user = "root";
	private String pass = "262001";

	private static final String insertQuery = "insert into userm(Name, Email, Country) values(?,?,?)";

	private static final String selectById = "select Id, Name, Email, Country from userm where Id = ?";
	private static final String selectAll = "select * from userm";
	private static final String delete = "delete from userm where id = ?";
	private static final String update = "update userm set Name = ?, Email = ?, Country = ? where Id = ?";

	protected Connection getConnection() {

		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return con;
	}

	// create or insert user
	public void insertUser(User user) throws SQLException {

		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(insertQuery);
//		ps.setInt(1, user.getId());
		ps.setString(1, user.getName());
		ps.setString(2, user.getEmail());
		ps.setString(3, user.getCountry());
		ps.executeUpdate();
	}

	// select user by ID
	public User selectById(int id) throws SQLException {

		User user = null;
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(selectById);
		ps.setInt(1, id);
		System.out.println(ps);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String name = rs.getString("Name");
			String email = rs.getString("Email");
			String country = rs.getString("Country");
			user = new User(id, name, email, country);
		}
		return user;
	}

	// select all users
	public List<User> selectAll() throws SQLException {

		List<User> user = new ArrayList();
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(selectAll);
		System.out.println(ps);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("Id");
			String name = rs.getString("Name");
			String email = rs.getString("Email");
			String country = rs.getString("Country");
			user.add(new User(id, name, email, country));
		}
		return user;
	}

	// delete the user
	public boolean deleteUser(int id) throws SQLException {

		boolean dlt;
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(delete);
		ps.setInt(1, id);
		return dlt = ps.executeUpdate() > 0;
	}

	// update the user
	public boolean updaateUser(User user) throws SQLException {

		boolean updt;
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(update);
		ps.setString(1, user.getName());
		ps.setString(2, user.getEmail());
		ps.setString(3, user.getCountry());
		ps.setInt(4, user.getId());

		return updt = ps.executeUpdate() > 0;
	}

}
