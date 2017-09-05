package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.user;

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
}
