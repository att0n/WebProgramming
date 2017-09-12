package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.User;

//ユーザ一覧取得
public class UserDao {
	public List<User> findAll() {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();

		try {
			// データベース接続
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM user";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();

			// 結果表に格納されたレコード内容を
			// userインスタンスに設定し、ArrayListインスタンスに追加
			while (rs.next()) {
				if (rs.getInt("id") != 1) {// 管理者以外
					int id = rs.getInt("id");
					String login_id = rs.getString("login_id");
					String name = rs.getString("name");
					String birth_date = new SimpleDateFormat("yyyy年MM月dd日").format(rs.getDate("birth_date"));
					String password = rs.getString("password");
					String create_date = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(rs.getDate("create_date"));
					String update_date = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(rs.getDate("update_date"));
					User user = new User(id, login_id, name, birth_date, password, create_date, update_date);
					userList.add(user);
				}
			}

		} catch (SQLException e) {
			System.out.println(e);
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println(e);
					return null;
				}
			}
		}
		return userList;
	}

	// ログインIDからユーザ情報(ID、ログインID、名前、誕生日、…)を取得
	public User searchLoginID(String lId) {
		Connection conn = null;
		User u = new User();
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT id,login_id, name, birth_date, password, create_date, update_date FROM user WHERE login_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, lId);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String name = rs.getString("name");
				String birthday = new SimpleDateFormat("yyyy年MM月dd日").format(rs.getDate("birth_date"));
				String pass = rs.getString("password");
				String cDate = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(rs.getDate("create_date"));
				String uDate = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(rs.getDate("update_date"));
				User user = new User(id, login_id, name, birthday, pass, cDate, uDate);
				u = user;
			}
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println(e);
					return null;
				}
			}
		}
		return u;
	}

	// IDからユーザ情報(ID、ログインID、名前、誕生日、…)を取得
	public User searchID(int i) {
		Connection conn = null;
		User u = new User();
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT id,login_id, name, birth_date, password, create_date, update_date FROM user WHERE id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, i);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String name = rs.getString("name");
				String birthday = new SimpleDateFormat("yyyy年MM月dd日").format(rs.getDate("birth_date"));
				String pass = rs.getString("password");
				String cDate = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(rs.getTimestamp("create_date"));
				String uDate = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(rs.getTimestamp("update_date"));
				User user = new User(id, login_id, name, birthday, pass, cDate, uDate);
				u = user;
			}
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println(e);
					return null;
				}
			}
		}
		return u;
	}

	// ユーザ情報の追加
	public void insertUser(String logID, String name, String birth, String pass, String create, String update) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO user (login_id , name , birth_date , password , create_date , update_date)VALUES (?,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, logID);
			pStmt.setString(2, name);
			pStmt.setString(3, birth);
			pStmt.setString(4, pass);
			pStmt.setString(5, create);
			pStmt.setString(6, update);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		}
	}

	// ユーザの削除
	public void deleteUser(int i) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "DELETE FROM user WHERE id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, i);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		}
	}

	// ユーザの更新
	public void updateUser(String name, String birth, String pass, String loginID) {
		Connection conn = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatDate = sdf1.format(calendar.getTime());

		try {
			conn = DBManager.getConnection();
			String sql = "UPDATE user SET name=?,birth_date=?,password=?,update_date=? WHERE login_id =?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, name);
			pStmt.setString(2, birth);
			pStmt.setString(3, pass);
			pStmt.setString(4, formatDate);
			pStmt.setString(5, loginID);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		}
	}

	// ユーザの更新(パスワード以外)
	public void updateUser(String name, String birth, String loginID) {
		Connection conn = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatDate = sdf1.format(calendar.getTime());

		try {
			conn = DBManager.getConnection();
			String sql = "UPDATE user SET name=?,birth_date=?,update_date=? WHERE login_id =?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, name);
			pStmt.setString(2, birth);
			pStmt.setString(3, formatDate);
			pStmt.setString(4, loginID);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		}
	}

	// ログインIDからユーザ情報(ID、ログインID、名前、誕生日、…)を取得
	public List<User> searchLoginID2(String lId) {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT id,login_id, name, birth_date, password, create_date, update_date FROM user WHERE login_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, lId);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String name = rs.getString("name");
				String birthday = new SimpleDateFormat("yyyy年MM月dd日").format(rs.getDate("birth_date"));
				String pass = rs.getString("password");
				String cDate = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(rs.getDate("create_date"));
				String uDate = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(rs.getDate("update_date"));
				User user = new User(id, login_id, name, birthday, pass, cDate, uDate);
				userList.add(user);
			}
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println(e);
					return null;
				}
			}
		}
		return userList;
	}

	// ユーザネームからユーザ情報を取得
	public List<User> searchName(String word) {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT id,login_id,name, birth_date, password, create_date, update_date FROM user WHERE name LIKE '%"
					+ word + "%'";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String name = rs.getString("name");
				String birthday = new SimpleDateFormat("yyyy年MM月dd日").format(rs.getDate("birth_date"));
				String pass = rs.getString("password");
				String cDate = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(rs.getDate("create_date"));
				String uDate = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(rs.getDate("update_date"));
				User user = new User(id, login_id, name, birthday, pass, cDate, uDate);
				userList.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return userList;
	}

	// 誕生日からユーザ情報を取得
	public List<User> searchBirthday(String n1, String n2) {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT id,login_id,name,birth_date,password,create_date,update_date FROM user WHERE birth_date >= ? AND birth_date <= ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, n1);
			pStmt.setString(2, n2);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String name = rs.getString("name");
				String birthday = new SimpleDateFormat("yyyy年MM月dd日").format(rs.getDate("birth_date"));
				String pass = rs.getString("password");
				String cDate = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(rs.getDate("create_date"));
				String uDate = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(rs.getDate("update_date"));
				User user = new User(id, login_id, name, birthday, pass, cDate, uDate);
				userList.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return userList;
	}

	// ユーザネームからレコードの有無確認
	public boolean checkUserName(String n) {
		Connection conn = null;

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT count(*) AS cnt FROM user WHERE name LIKE '%" + n + "%'";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int cnt = rs.getInt("cnt");
				if (cnt >= 1) {
					return true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return false;
	}

	// 誕生日検索からレコードの有無確認
	public boolean checkUserBirthday(String n1, String n2) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT count(*) AS cnt FROM user WHERE birth_date >= '" + n1 + "' AND birth_date <= '" + n2
					+ "'";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int cnt = rs.getInt("cnt");
				if (cnt >= 1) {
					return true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return false;
	}

	// 誕生日検索からレコードの有無確認
	public boolean checkUser(String login_id, String name, String birth1, String birth2) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT count(*) AS cnt FROM user WHERE login_id=? AND name=? AND birth_date >=? AND birth_date <=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, login_id);
			pStmt.setString(2, name);
			pStmt.setString(3, birth1);
			pStmt.setString(4, birth2);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int cnt = rs.getInt("cnt");
				if (cnt >= 1) {
					return true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return false;
	}

	// ユーザ検索(項目全て記入時)
	public List<User> searchUser(String logID, String name, String birth1, String birth2) {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT id,login_id,name,birth_date,password,create_date,update_date FROM user WHERE login_id=? AND name=? AND birth_date >=? AND birth_date <=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, logID);
			pStmt.setString(2, name);
			pStmt.setString(3, birth1);
			pStmt.setString(4, birth2);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String name2 = rs.getString("name");
				String birthday = new SimpleDateFormat("yyyy年MM月dd日").format(rs.getDate("birth_date"));
				String pass = rs.getString("password");
				String cDate = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(rs.getDate("create_date"));
				String uDate = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(rs.getDate("update_date"));
				User user = new User(id, login_id, name2, birthday, pass, cDate, uDate);
				userList.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return userList;
	}

	// レコードの有無確認(ログインID、名前)
	public boolean checkUserloginIDName(String login_id, String name) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT count(*) AS cnt FROM user WHERE login_id=? AND name=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, login_id);
			pStmt.setString(2, name);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int cnt = rs.getInt("cnt");
				if (cnt >= 1) {
					return true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return false;
	}

	// ユーザ検索(項目全て記入時)
	public List<User> searchUserLoginIDName(String logID, String name) {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT id,login_id,name,birth_date,password,create_date,update_date FROM user WHERE login_id=? AND name=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, logID);
			pStmt.setString(2, name);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String name2 = rs.getString("name");
				String birthday = new SimpleDateFormat("yyyy年MM月dd日").format(rs.getDate("birth_date"));
				String pass = rs.getString("password");
				String cDate = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(rs.getDate("create_date"));
				String uDate = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(rs.getDate("update_date"));
				User user = new User(id, login_id, name2, birthday, pass, cDate, uDate);
				userList.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return userList;
	}

	// レコードの有無確認(ログインID、誕生日)
	public boolean checkUserloginIDBirthday(String login_id, String birth1, String birth2) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT count(*) AS cnt FROM user WHERE login_id=? AND birth_date >=? AND birth_date <=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, login_id);
			pStmt.setString(2, birth1);
			pStmt.setString(3, birth2);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int cnt = rs.getInt("cnt");
				if (cnt >= 1) {
					return true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return false;
	}

	// ユーザ検索(ログインIDと誕生日)
	public List<User> searchUserLoginIDBirthday(String logID, String birth1, String birth2) {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT id,login_id,name,birth_date,password,create_date,update_date FROM user WHERE login_id=? AND birth_date >=? AND birth_date <=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, logID);
			pStmt.setString(2, birth1);
			pStmt.setString(3, birth2);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String name2 = rs.getString("name");
				String birthday = new SimpleDateFormat("yyyy年MM月dd日").format(rs.getDate("birth_date"));
				String pass = rs.getString("password");
				String cDate = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(rs.getDate("create_date"));
				String uDate = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(rs.getDate("update_date"));
				User user = new User(id, login_id, name2, birthday, pass, cDate, uDate);
				userList.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return userList;
	}

	// レコードの有無確認(名前、誕生日)
	public boolean checkUserNameBirthday(String name, String birth1, String birth2) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT count(*) AS cnt FROM user WHERE name=? AND birth_date >=? AND birth_date <=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, name);
			pStmt.setString(2, birth1);
			pStmt.setString(3, birth2);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int cnt = rs.getInt("cnt");
				if (cnt >= 1) {
					return true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return false;
	}

	// ユーザ検索(ログインIDと誕生日)
	public List<User> searchUserNameBirthday(String name, String birth1, String birth2) {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT id,login_id,name,birth_date,password,create_date,update_date FROM user WHERE name=? AND birth_date >=? AND birth_date <=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, name);
			pStmt.setString(2, birth1);
			pStmt.setString(3, birth2);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String name2 = rs.getString("name");
				String birthday = new SimpleDateFormat("yyyy年MM月dd日").format(rs.getDate("birth_date"));
				String pass = rs.getString("password");
				String cDate = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(rs.getDate("create_date"));
				String uDate = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(rs.getDate("update_date"));
				User user = new User(id, login_id, name2, birthday, pass, cDate, uDate);
				userList.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return userList;
	}

	// レコードの有無確認(名前、誕生日)
	public boolean checkBirthday(String birth1) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT count(*) AS cnt FROM user WHERE birth_date >=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, birth1);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int cnt = rs.getInt("cnt");
				if (cnt >= 1) {
					return true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return false;
	}

	// ユーザ検索(誕生日1)
	public List<User> searchBirthday(String birth1) {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT id,login_id,name,birth_date,password,create_date,update_date FROM user WHERE birth_date >=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, birth1);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String name2 = rs.getString("name");
				String birthday = new SimpleDateFormat("yyyy年MM月dd日").format(rs.getDate("birth_date"));
				String pass = rs.getString("password");
				String cDate = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(rs.getDate("create_date"));
				String uDate = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(rs.getDate("update_date"));
				User user = new User(id, login_id, name2, birthday, pass, cDate, uDate);
				userList.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return userList;
	}

	// レコードの有無確認(誕生日2)
	public boolean checkBirthday2(String birth2) {
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "SELECT count(*) AS cnt FROM user WHERE birth_date <=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, birth2);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int cnt = rs.getInt("cnt");
				if (cnt >= 1) {
					return true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return false;
	}
	// ユーザ検索(誕生日2)
		public List<User> searchBirthday2(String birth2) {
			Connection conn = null;
			List<User> userList = new ArrayList<User>();

			try {
				conn = DBManager.getConnection();
				String sql = "SELECT id,login_id,name,birth_date,password,create_date,update_date FROM user WHERE birth_date <=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, birth2);
				ResultSet rs = pStmt.executeQuery();

				while (rs.next()) {
					if(rs.getInt("id")!=1) {
						int id = rs.getInt("id");
						String login_id = rs.getString("login_id");
						String name2 = rs.getString("name");
						String birthday = new SimpleDateFormat("yyyy年MM月dd日").format(rs.getDate("birth_date"));
						String pass = rs.getString("password");
						String cDate = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(rs.getDate("create_date"));
						String uDate = new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(rs.getDate("update_date"));
						User user = new User(id, login_id, name2, birthday, pass, cDate, uDate);
						userList.add(user);
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						return null;
					}
				}
			}
			return userList;
		}
}
