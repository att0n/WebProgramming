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
import model.User;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
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
			String logID = request.getParameter("SearchLoginId");
			String name = request.getParameter("SearchName");
			String birth1 = request.getParameter("birth01");
			String birth2 = request.getParameter("birth02");
			boolean noRecord = false;

			UserDao userDao = new UserDao();
			if (logID == "" && name == "" && birth1 == "" && birth2 == "") {
				List<User> userList = userDao.findAll();
				request.setAttribute("userList", userList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
				dispatcher.forward(request, response);
			} else if (logID != "" && name != "" && birth1 != "" && birth2 != "") {
				if (userDao.checkUser(logID, name, birth1, birth2)) {
					List<User> userList = userDao.searchUser(logID, name, birth1, birth2);
					request.setAttribute("userList", userList);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
					dispatcher.forward(request, response);
				} else {
					noRecord = true;
				}
			} else if (logID != "" && name != "") {
				if (userDao.checkUserloginIDName(logID, name)) {
					List<User> userList = userDao.searchUserLoginIDName(logID, name);
					request.setAttribute("userList", userList);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
					dispatcher.forward(request, response);
				} else {
					noRecord = true;
				}
			} else if (logID != "" && birth1 != "" && birth2 != "") {
				if (userDao.checkUserloginIDBirthday(logID, birth1, birth2)) {
					List<User> userList = userDao.searchUserLoginIDBirthday(logID, birth1, birth2);
					request.setAttribute("userList", userList);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
					dispatcher.forward(request, response);
				} else {
					noRecord = true;
				}
			} else if(birth1!="" && birth2=="") {
				if(userDao.checkBirthday(birth1)) {
					List<User> userList = userDao.searchBirthday(birth1);
					request.setAttribute("userList", userList);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
					dispatcher.forward(request, response);
				} else {
					noRecord = true;
				}
			} else if(birth1=="" && birth2!="") {

				if(userDao.checkBirthday(birth1)) {
					System.out.println("test");
					List<User> userList = userDao.searchBirthday2(birth2);
					request.setAttribute("userList", userList);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
					dispatcher.forward(request, response);
				} else {
					noRecord = true;
				}

			} else if (name != "" && birth1 != "" && birth2 != "") {
				if (userDao.checkUserNameBirthday(name, birth1, birth2)) {
					List<User> userList = userDao.searchUserNameBirthday(name, birth1, birth2);
					request.setAttribute("userList", userList);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
					dispatcher.forward(request, response);
				} else {
					noRecord = true;
				}
			} else if (logID != "") {
				User u = userDao.searchLoginID(logID);
				if (u.getLogin_id() == null) {
					noRecord = true;
				} else {
					List<User> userList = userDao.searchLoginID2(logID);
					request.setAttribute("userList", userList);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
					dispatcher.forward(request, response);
				}
			} else if (name != "") {
				if (userDao.checkUserName(name)) {
					List<User> userList = userDao.searchName(name);
					request.setAttribute("userList", userList);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
					dispatcher.forward(request, response);
				} else {
					noRecord = true;
				}
			} else if (birth1 != "" && birth2 != "") {
				if (userDao.checkUserBirthday(birth1, birth2)) {
					List<User> userList = userDao.searchBirthday(birth1, birth2);
					request.setAttribute("userList", userList);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
					dispatcher.forward(request, response);
				} else {
					noRecord = true;
				}
			} else {
				noRecord = true;
			}

			if (noRecord) {
				User formWord = new User();
				formWord.setLogin_id(logID);
				formWord.setName(name);
				request.setAttribute("formWord", formWord);

				request.setAttribute("noRecord", true);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userList.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

}
