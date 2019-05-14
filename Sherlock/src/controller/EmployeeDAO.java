package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.EmployeeVO;

public class EmployeeDAO {

	// 로그인을 한 사람들이라면 모두 볼 수 있다
	// 등록 된 직원들의 전체 목록

	public ArrayList<EmployeeVO> getEmployeeTotalList() throws Exception {

		ArrayList<EmployeeVO> list = new ArrayList<>(); // 객체 생성

		// sql문
		// 사원 번호 , 직급 , 이름, 재직*퇴사여부
		String sql = "select em_no, em_rank, em_name, em_whether" + "from employeejoin" + "order by no";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmployeeVO EVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// 사원번호, 직급, 이름, 재직*퇴사여부
			while (rs.next()) {
				EVo = new EmployeeVO();
				EVo.setEm_no(rs.getInt("no"));
				EVo.setEm_rank(rs.getString("em_rank"));
				EVo.setEm_name(rs.getString("em_name"));
				EVo.setEm_whether(rs.getString("em_whether"));

				list.add(EVo);
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
		return list;
	}

	// 등록한 직원의 일련번호

	public String getEmployeeCount(String em_no) throws Exception {
		String sql = "select LPAD (count (*) + 1 , 4 , '0' as EmployeeJoinCount" + "from EmplyeeJoin";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String serialNumber = "";

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, em_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				serialNumber = rs.getString("EmployeeCount");
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
		return serialNumber;
	}

	// 등록한 직원의 정보 수정
	// 이름, 번호, 주소 , 은행명, 계좌번호
	public boolean getEmployeeUpdate( String em_name, String em_phone
			, em_address , em_bank, em_account ) throw Exception {
			
		//검색해서 찾을 수 있게
		String sql = "update Employee set em_name =?, set em_phone = ?, set em_addresd=?"
				+ ", em_bank =? , em_accoount =? where no =?" ; //형식
	
		  Connection con = null;
	      PreparedStatement pstmt = null;
	      boolean studentUpdateSucess = false;
	      
	      try {
	    	  con = DBUtil.getConnection();
	    	  
	    	  pstmt = con.prepareStatement(sql);
	    	  pstmt.setString(1, em_name);
	    	  pstmt.setString(2, em_phone);
	    	  pstmt.setString(3, em_address);
	    	  pstmt.setString(4, em_bank);
	    	  pstmt.setString(5, em_account);
	    	  
	    	  int i = pstmt.executeUpdate();
	    	  
	    	  if ( i == i ) {
	    		  Alert alert = new Alert(AlertType.INFORMATION);
	              alert.setTitle("직원 정보 수정");
	              alert.setHeaderText(no + " 입력한 직원 정보를 수정합니다.");
	              alert.setContentText("직원 정보 수정 성공!!");
	              alert.showAndWait();
	              studentUpdateSucess = true;
	    	  }else {
	    		  Alert alert = new Alert(AlertType.WARNING);
	              alert.setTitle("직원 정보 수정");
	              alert.setHeaderText("입력한 직원 정보가 일치하지 않습니다.");
	              alert.setContentText("직원 정보 수정 실패!!");
	              alert.showAndWait();
	    	  }
	      }catch (SQLException e ) {
	    	  System.out.println("e = [ " + e + " ]");
	      }catch (Exception e)
	    	  System.out.println("e = [ " + e + "]");
	      }finally {
	    	  try {
	    		  if (pstmt ! = null)
			
		}
	      
	
	}
}
