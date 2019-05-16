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

		String sql = "insert into employeejoin " + "(em_name, em_id, em_passwd, em_phone, em_bank, em_account, em_rank, em_entry, em_leaveday, em_address, em_no, em_whether)" + " values " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean joinSucess = false;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, jvo.getEm_Name());
			pstmt.setString(2, jvo.getEm_Id());
			pstmt.setString(3, jvo.getEm_Passwd());
			pstmt.setString(4, jvo.getEm_Phone());
			pstmt.setString(5, jvo.getEm_Bank());
			pstmt.setString(6, jvo.getEm_Account());
			pstmt.setString(7, jvo.getEm_Rank());
			pstmt.setString(8, jvo.getEm_Entry());
			pstmt.setString(9, jvo.getEm_Leaveday());
			pstmt.setString(10, jvo.getEm_Address());
			pstmt.setString(11, jvo.getEm_No());
			pstmt.setString(12, jvo.getEm_Whether());
		
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
		
		String sql = "select * from employee where sd_id = ?";
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
	 
	   

