package sarvlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Encryption;
import dao.UserDao;
import model.User;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String loginID = request.getParameter("login_id");
		String pass1 = request.getParameter("pass");
		String pass2 = request.getParameter("pass2");
		String name = request.getParameter("name");
		String birth = request.getParameter("birth");

		UserDao userDao = new UserDao();
		if(pass1.equals(pass2) && name!="" && birth!="") {
			String ePass = Encryption.encryption(pass1);
			userDao.updateUser(name, birth, ePass, loginID);

			String url = "./UserListServlet";
			response.sendRedirect(url);
		}else if(pass1=="" && pass2=="" && name!="" && birth!=""){
			//パスワード以外を更新
			userDao.updateUser(name, birth, loginID);

			String url = "./UserListServlet";
			response.sendRedirect(url);
		}else {
			User u = new User();
			u.setLogin_id(loginID);
			u.setName(name);
			request.setAttribute("user", u);
			request.setAttribute("error", true);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
			dispatcher.forward(request, response);
		}
	}

}
