package sarvlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Encryption;
import dao.UserDao;
import model.User;

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
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String loginID = request.getParameter("loginID");
		String password = request.getParameter("password");
		UserDao userDao = new UserDao();
		HttpSession session = request.getSession();

		User u = userDao.searchLoginID(loginID);
		if(u.getPassword().equals(Encryption.encryption(password))) {
			//System.out.println("ログイン成功");

			session.setAttribute("loginUser", u);

			String url = "./UserListServlet";
			response.sendRedirect(url);
		}else {
			//System.out.println("ログイン失敗");
			request.setAttribute("login", true);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
			dispatcher.forward(request, response);
		}

	}

}
