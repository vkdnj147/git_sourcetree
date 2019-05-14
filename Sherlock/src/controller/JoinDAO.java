package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.JoinVO;

public class JoinDAO {
	private static final String idcheck = null;

	// 관리자 등록
	public boolean getMangerRegiste(JoinVO jvo) throws Exception {

		String sql = "insert into employeejoin " + "(name, id, passwd, phone, bank, account)" + " values " + "(?, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean joinSucess = false;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, jvo.getName());
			pstmt.setString(2, jvo.getId());
			pstmt.setString(3, jvo.getPassword());
			pstmt.setString(3, jvo.getPhone());
			pstmt.setString(3, jvo.getBank());
			pstmt.setString(3, jvo.getAccount());
			int i = pstmt.executeUpdate();

			// 조건
			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("직원/파트타이머 등록");
				alert.setHeaderText("직원/파트타이머 등록 완료");
				alert.setContentText("직원/파트타이머 등록 성공!!");
				alert.showAndWait();
				joinSucess = true;

			} else {

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("직원/파트타이머 등록");
				alert.setHeaderText("직원/파트타이머 등록 실패");
				alert.setContentText("직원/파트타이머 등록 실패!!");
				alert.showAndWait();
			}

		} catch (SQLException e) {

			System.out.println("e=[" + e + "]");

		} catch (Exception e) {

			System.out.println("e=[" + e + "]");

		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return joinSucess;

	}

	public boolean getIdCheck(String searchId) throws Exception{
		
		String sql = "select * from student where sd_id = ?";
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      boolean idcheckResult = false;
	      
	      try {
	         con = DBUtil.getConnection();
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, idcheck);
	         rs = pstmt.executeQuery();
	         
	         if(rs.next()) {
	        	 idcheckResult = true; // 중복된 아이디 있을 경우
	         }
	      }catch (SQLException e) {
	         System.out.println("e=[" + e + "]");
	      }catch (Exception e) {
	         System.out.println("e=[" + e + "]");
	      }finally {
	         try {
	            if (rs != null)
	               rs.close();
	            if (pstmt != null)
	               pstmt.close();
	            if (con != null)
	               con.close();
	         }catch (SQLException e) {
	         }
	      }
	      return idcheckResult;
	   }
	}
	 
	   

