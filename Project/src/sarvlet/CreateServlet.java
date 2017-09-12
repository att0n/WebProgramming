package sarvlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
 * Servlet implementation class CreateServlet
 */
@WebServlet("/CreateServlet")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");

		if (user == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
			dispatcher.forward(request, response);
		} else {

			request.setCharacterEncoding("UTF-8");
			String loginID = request.getParameter("loginID");
			String pass1 = request.getParameter("pass1");
			String pass2 = request.getParameter("pass2");
			String name = request.getParameter("name");
			String birth = request.getParameter("birth");
			UserDao userDao = new UserDao();

			if (userDao.searchLoginID(loginID).getLogin_id() == null && pass1.equals(pass2) && loginID != ""
					&& pass1 != "" && pass2 != "" && name != "" && birth != "") {
				String ePass = Encryption.encryption(pass1);
				Calendar c = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
				String d = sdf.format(c.getTime());
				userDao.insertUser(loginID, name, birth, ePass, d, d);

				String url = "./UserListServlet";
				response.sendRedirect(url);
			} else {
				User u = new User();
				u.setLogin_id(loginID);
				u.setName(name);

				request.setAttribute("user", u);
				request.setAttribute("formCheck", true);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userCreate.jsp");
				dispatcher.forward(request, response);
			}
		}

	}

}
