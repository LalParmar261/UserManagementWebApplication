package web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.UserDAO;

/**
 * ControllerServlet.java This servlet acts as a page controller for the
 * application, handling all requests from the user.
 * 
 * @author Permeshawar Lal Parmar
 */

//Servlet implementation class UserServlet

@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO dao;

	public void init() {

		dao = new UserDAO();
	}

//	public UserServlet() {
//
//		this.dao = new UserDAO();
//	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//
//			throws ServletException, IOException {
//		doGet(request, response);
//	}

	protected void service(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

		String action = request.getServletPath();
		switch (action) {

		case "/new":
			showNewForm(request, response);
			break;
		case "/insert":
			try {
				insertUser(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;
		case "/delete":
			try {
				deleteUser(request, response);
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
			break;
		case "/edit":
			try {
				showEditForm(request, response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/update":
			try {
				updateUser(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			try {
				listUser(request, response);
			} catch (SQLException | ServletException | IOException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	@SuppressWarnings("unused")
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("userForm.jsp");
		rd.forward(request, response);
	}

	@SuppressWarnings("unused")
	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

		String name = request.getParameter("Name");
		String email = request.getParameter("Email");
		String country = request.getParameter("Country");
		User newUser = new User(name, email, country);
		dao.insertUser(newUser);
		response.sendRedirect("list");
	}

	@SuppressWarnings("unused")
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

		int id = Integer.parseInt(request.getParameter("Id"));
		dao.deleteUser(id);
		response.sendRedirect("list");
	}

	@SuppressWarnings("unused")
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)

			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("Id"));
		User existingUser = dao.selectById(id);
		RequestDispatcher rd = request.getRequestDispatcher("userForm.jsp");
		request.setAttribute("user", existingUser);
		rd.forward(request, response);
	}

	@SuppressWarnings("unused")
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

		int id = Integer.parseInt(request.getParameter("Id"));
		String name = request.getParameter("Name");
		String email = request.getParameter("Email");
		String country = request.getParameter("Country");
		User book = new User(id, name, email, country);
		dao.updaateUser(book);
		response.sendRedirect("list");
	}

	@SuppressWarnings("unused")
	private void listUser(HttpServletRequest request, HttpServletResponse response)

			throws SQLException, ServletException, IOException {
		List<User> listUser = dao.selectAll();
		request.setAttribute("listUser", listUser);
		RequestDispatcher rd = request.getRequestDispatcher("userList.jsp");
		rd.forward(request, response);
	}
}
