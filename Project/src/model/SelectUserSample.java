package model;

import java.util.List;

import dao.UserDao;

public class SelectUserSample {
	public static void main(String[] args) {
        UserDao userDao = new UserDao();
        List<user> userList = userDao.findAll();

        for (user user : userList) {
            System.out.println("ID:" + user.getId());
            System.out.println("LoginID:" + user.getLogin_id());
            System.out.println("名前:" + user.getName());
        }
    }
}
