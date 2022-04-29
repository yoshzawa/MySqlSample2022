
import java.io.IOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListItemServlet
 */
@WebServlet("/item/list")
public class ListItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final String url = "jdbc:mysql://192.168.54.225:3306/warehouse";
	final String user = "user";
	final String password = "pass";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListItemServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ドライバーのロード
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// データベースに接続
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// SQL登録
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("select item_id , item_name from item");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// SQL（検索）実行
		ResultSet rs = null;
		try {
			rs = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 結果をListに格納してrequestスコープで保存する
		List<String[]> resultList = new ArrayList<>();
		try {
			while (rs.next() != false) {
				String id = rs.getString("item_id");
				String name = rs.getString("item_name");

				String[] s = new String[2];
				s[0] = id;
				s[1] = name;
				resultList.add(s);
			}
			request.setAttribute("resultList", resultList);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/jsp/listItem.jsp").forward(request, response);

	}

}
