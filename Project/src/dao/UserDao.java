package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.user;

//ユーザ一覧取得
public class UserDao {
	public List<user> findAll(){
		Connection conn = null;
		List<user> userList = new ArrayList<user>();

		try {
			// データベース接続
            conn = DBManager.getConnection();
            String sql = "SELECT * FROM user";
            PreparedStatement pStmt = conn.prepareStatement(sql);

             // SELECTを実行、結果表を取得
            ResultSet rs = pStmt.executeQuery();

         // 結果表に格納されたレコード内容を
            // userインスタンスに設定し、ArrayListインスタンスに追加
            while (rs.next()) {
                int id = rs.getInt("id");
                String login_id = rs.getString("login_id");
                String name = rs.getString("name");
                String birth_date = rs.getString("birth_date");
                String password = rs.getString("password");
                String create_date = rs.getString("create_date");
                String update_date = rs.getString("update_date");
                user user = new user(id, login_id, name, birth_date, password, create_date, update_date);
                userList.add(user);
            }

		}catch(SQLException e) {
			 e.printStackTrace();
			 return null;
		}finally{
			// データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
		}return userList;
	}

	//ログインID・パスワードからユーザを取得
	public List<user> searchIdPassword(String lId,String pw){
		Connection conn = null;
		List<user> userList = new ArrayList<user>();

		try {
            conn = DBManager.getConnection();
            String sql = "SELECT login_id,name,password FROM user WHERE login_id='" + lId + "' AND password='" + pw + "'";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                String login_id = rs.getString("login_id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                user user = new user(login_id, name, password);
                userList.add(user);
            }

		}catch(SQLException e) {
			 e.printStackTrace();
			 return null;
		}finally{
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
		}return userList;
	}

	//ログインID、パスワードからレコードの有無確認
	public String checkUser(String lId,String pw){
		Connection conn = null;
		List<user> userList = new ArrayList<user>();
		String str = "";

		try {
            conn = DBManager.getConnection();
            String sql = "SELECT count(*) AS cnt FROM user WHERE login_id='"+lId+"' AND password='"+pw+"'";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
            	str = rs.getString("cnt");
            }

		}catch(SQLException e) {
			 e.printStackTrace();
			 return "0";
		}finally{
			// データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return "0";
                }
            }
		}return str;
	}

	//ログインIDからログインID、名前、誕生日を取得
	public List<user> searchLoginID(String lId){
		Connection conn = null;
		List<user> userList = new ArrayList<user>();

		try {
            conn = DBManager.getConnection();
            String sql = "SELECT id,login_id, name, birth_date, password, create_date, update_date FROM user WHERE login_id='" + lId + "'";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
            	int id = rs.getInt("id");
                String login_id = rs.getString("login_id");
                String name = rs.getString("name");
                String birthday = rs.getString("birth_date");
                String pass = rs.getString("password");
                String cDate = rs.getString("create_date");
                String uDate = rs.getString("update_date");
                user user = new user(id,login_id, name, birthday, pass, cDate, uDate);
                userList.add(user);
            }

		}catch(SQLException e) {
			 e.printStackTrace();
			 return null;
		}finally{
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
		}return userList;
	}
}
