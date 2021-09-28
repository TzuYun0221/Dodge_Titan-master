package userinterface;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

public class ConnectMySQL{
	String url = "jdbc:mysql://localhost:3306/score?";
	String user = "root";
	String pw = "12345678";
	String sql;
	Connection conn;
	PreparedStatement ps;
	Properties properties = new Properties();

	public void record() throws Exception {
		if (GameScreen.finish == false) {
			int s = GameScreen.score;
			String name = GameoverPanel.name;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Driver OK!");

				conn = DriverManager.getConnection(url, user, pw);

			    ps = conn.prepareStatement("INSERT INTO dinoscore (name, score) VALUES(?, ?);");
			    ps.setString(1, name);
			    ps.setInt(2, s);
			    ps.execute();
			} catch (SQLException e){
   
				System.out.println("MYSQL操作失敗");
				e.printStackTrace();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	String getScore(){
		// 結果放進List全部存好
		String showScore = "";

		sql = "SELECT * FROM dinoscore order by score DESC";
		try{

			conn = DriverManager.getConnection(url, user, pw);
		   Statement st = conn.createStatement();
		   // 抓取結果放這
		   ResultSet result = st.executeQuery(sql);
	   // 顯示出抓取結果
	   while (result.next()){
		   String addScore = ("名字 : " + result.getString(1) + "\n分數 : " + result.getInt(2) + "\n"
				   				+ "------------------" + "\n");
			   showScore = showScore + addScore;
	    /*
	     * System.out.println("名字 : " + result.getString(1)); System.out.println();
	     * System.out.println("分數 : " + result.getInt(2)); System.out.println();
	     */

    // 關閉連接
    /*
     * result.close(); st.close(); conn.close();
     */
			   }
	   	
	   } catch (SQLException e){
		  e.printStackTrace();
	   }
		return showScore;

	}
}