package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.DBUtil;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.EmployeeVO;

public class EmployeeDAO {

	private static final boolean EmployeeJoinUpdateSucess = false;

	// 로그인을 한 사람들이라면 모두 볼 수 있다
	// 등록 된 직원들의 전체 목록

	public ArrayList<EmployeeVO> getEmployeeTotalList() throws Exception {

		ArrayList<EmployeeVO> list = new ArrayList<>(); // 객체 생성

		// sql문 : 직원, 파트타이머 테이블명 : employeejoin에 대한 내용을 가지고 오는 것
		// 사원 번호 , 직급 , 이름, 아이디, 비밀번호, 핸드폰 번호, 주소, 은행명, 계좌번호, 입사일, 퇴사일, 재직여부
		String sql = "select * from employee order by em_no";// 직원 디비를 가져오는 쿼리문

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmployeeVO eVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// 사원번호, 직급, 이름, 재직*퇴사여부
			while (rs.next()) {
				eVo = new EmployeeVO();
				eVo.setEm_no(rs.getString("em_no"));
				eVo.setEm_rank(rs.getString("em_rank"));
				eVo.setEm_name(rs.getString("em_name"));
				eVo.setEm_id(rs.getString("em_id"));
				eVo.setEm_passwd(rs.getString("em_passwd"));
				eVo.setEm_phone(rs.getString("em_phone"));
				eVo.setEm_address(rs.getString("em_address"));
				eVo.setEm_bank(rs.getString("em_bank"));
				eVo.setEm_account(rs.getString("em_account"));
				eVo.setEm_entry(rs.getDate("em_entry")+"");
				eVo.setEm_leaveday(rs.getDate("em_leaveday")+"");
				eVo.setEm_whether(rs.getString("em_whether"));

				list.add(eVo);
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
	/*
	 * public String getEmployeeCount(String em_no) throws Exception { String sql =
	 * "select em_no from Emplyee";
	 * 
	 * Connection con = null; PreparedStatement pstmt = null; ResultSet rs = null;
	 * 
	 * String serialNumber = "";
	 * 
	 * try { con = DBUtil.getConnection(); pstmt = con.prepareStatement(sql);
	 * pstmt.setString(1, em_no); rs = pstmt.executeQuery();
	 * 
	 * if (rs.next()) { serialNumber = rs.getString("EmployeeCount"); } } catch
	 * (SQLException se) { System.out.println(se); } catch (Exception e) {
	 * System.out.println(e); } finally { try { if (rs != null) rs.close(); if
	 * (pstmt != null) pstmt.close(); if (con != null) con.close(); } catch
	 * (SQLException se) { } } return serialNumber; }
	 */
	// 데이터베읻스에서 직원 테이블 컬럼의 갯수
	public ArrayList<String> getEmployeeColumnName() throws Exception {
		ArrayList<String> columnName = new ArrayList<String>(); // 이 안에 직원의 정보를 넣는다

		String sql = "select * from Employee order by em_no"; // order by를 사용해서 정렬

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 객체의 변수 선언
		ResultSetMetaData rsmd = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();

			int cols = rsmd.getColumnCount();

			for (int i = 1; i <= cols; i++) {
				columnName.add(rsmd.getCatalogName(i));
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
		return columnName;
	}

	// 등록한 직원의 정보 등록
	// 이름, 번호, 주소 , 은행명, 계좌번호

	public boolean getEmployeeInsert(EmployeeVO eVo)
			throws Exception {

		// DB에서 경로를 찾는다.
		//입력된 모든 정보를 가져오는 쿼리문
		String sql = "insert into Employee (em_no, em_rank, em_name, em_id, em_passwd, em_phone, em_address, em_bank, em_account, em_entry, em_whether) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, ? )";


		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean employeeInsertsucess = false;

		try {

			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, eVo.getEm_no());
			pstmt.setString(2, eVo.getEm_rank());
			pstmt.setString(3, eVo.getEm_name());
			pstmt.setString(4, eVo.getEm_id());
			pstmt.setString(5, eVo.getEm_passwd());
			pstmt.setString(6, eVo.getEm_phone());
			pstmt.setString(7, eVo.getEm_address());
			pstmt.setString(8, eVo.getEm_bank());
			pstmt.setString(9, eVo.getEm_account());
			pstmt.setString(10, eVo.getEm_whether());

			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("직원 정보 ");
				alert.setHeaderText(" 직원 정보 등록 완료.");
				alert.setContentText(" 직원 정보 등록 성공!!");
				alert.showAndWait();
				employeeInsertsucess = true;
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("직원 정보 ");
				alert.setHeaderText("직원 정보에 누락 된 것이 있는지 확인해주세요.");
				alert.setContentText("직원 정보 등록 실패!!");
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
			} catch (SQLException se) {

			}
		}

		return EmployeeJoinUpdateSucess;
	}

	//직원 수정
	public boolean getEmployeeUpdate(EmployeeVO selectedEmployee, String em_name, String em_phone, String em_entry, String em_bank,
			String em_account, String em_address, String em_leaveday) throws Exception {
		    String sql = "update employee set em_name=?, em_phone=?, em_entry=?"
		            + ", em_address=?, em_bank=? , em_account, em_leaveday where no=?"; //형식
		     
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
		         pstmt.setString(6, em_entry);
		         pstmt.setString(6, em_leaveday);
		         
		         int i = pstmt.executeUpdate();
		         
		         if(i==1) {
		            Alert alert = new Alert(AlertType.INFORMATION);
		            alert.setTitle("학생 정보 수정");
		            alert.setHeaderText(em_name + " 학생 정보 수정 완료.");
		            alert.setContentText("학생 정보 수정 성공!!");
		            alert.showAndWait();
		            studentUpdateSucess = true;
		         }else {
		            Alert alert = new Alert(AlertType.WARNING);
		            alert.setTitle("학생 정보 수정");
		            alert.setHeaderText("학생 정보 수정 실패.");
		            alert.setContentText("학생 정보 수정 실패!!");
		            alert.showAndWait();
		         }
		      }catch(SQLException e) {
		         System.out.println("e=[" + e + "]");
		      }catch (Exception e) {
		         System.out.println("e=[" + e + "]");
		      }finally {
		         try {
		            if (pstmt != null)
		               pstmt.close();
		            if (con != null)
		               con.close();
		         }catch (SQLException se) {
		         }
		      }
		      return studentUpdateSucess;
		   }
	


	//아이디 중복체크
	public boolean getEmployeeIdOverlap(String searchId) {
		 String sql = "select * from employee where em_id = ?";
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      boolean idOverlapResult = false;
	      
	      try {
	         con = DBUtil.getConnection();
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, searchId);
	         rs = pstmt.executeQuery();
	         
	         if(rs.next()) {
	            idOverlapResult = true; // 중복된 아이디 있을 경우
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
	      return idOverlapResult;
	   }
	}

	// 등록된 직원의 정보 삭제의 기능은 넣지 않았기 때문에 기능을 넣지 않았습니다.

