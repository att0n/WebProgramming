package sarvlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.user;

/**
 * Servlet implementation class LoginSarvlet2
 */
@WebServlet("/LoginSarvlet2")
public class LoginSarvlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginSarvlet2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String loginID = request.getParameter("loginID");
		String password = request.getParameter("password");
		UserDao userDao = new UserDao();

		int i = Integer.parseInt(userDao.checkUser(loginID, password));
		System.out.println(i);

		if(i==1) {
			List<user> userList = userDao.searchIdPassword(loginID, password);

			HttpSession session = request.getSession();
			session.setAttribute("loginUser", userList.get(0).getName());
			//System.out.println(userList.get(0).getName());

			String url = "./UserListServlet";
			response.sendRedirect(url);

		}else if(i==0){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index2.jsp");
			dispatcher.forward(request, response);
		}
	}

}
