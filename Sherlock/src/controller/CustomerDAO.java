package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.DBUtil;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.CustomerVO;

public class CustomerDAO {

	private static final boolean CustomerJoinUpdateSucess = false;

	// 전체 수강 탭에서 핸드폰 검색
	public ArrayList<CustomerVO> getTotalCustomerColumnName(String c_phone) throws Exception {
		ArrayList<CustomerVO> list = new ArrayList<>();
		String sql = "select c_no, c_name, c_age, c_phone, c_team" + "from customer ";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerVO cVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c_phone);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cVo = new CustomerVO();
				cVo.setC_no(rs.getString("c_no"));
				cVo.setC_name(rs.getString("c_name"));
				cVo.setC_phone(rs.getString("c_Phone"));
				cVo.setC_age(rs.getString("c_age"));
				cVo.setC_team(rs.getString("c_team"));

				list.add(cVo);
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

	// String sql = "select * from customer order by c_no "

	// 전체 수강 탭에서 이름 검색
	public ArrayList<CustomerVO> getTotalCustomerNameSearchList(String c_name) throws Exception {
		ArrayList<CustomerVO> list = new ArrayList<>();
		String sql = "select c_no, c_name, c_age, c_phone, c_team" + "from customer ";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerVO cVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c_name);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cVo = new CustomerVO();
				cVo.setC_no(rs.getString("c_no"));
				cVo.setC_name(rs.getString("c_name"));
				cVo.setC_phone(rs.getString("c_Phone"));
				cVo.setC_age(rs.getString("c_age"));
				cVo.setC_team(rs.getString("c_team"));

				list.add(cVo);
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

	public ArrayList<CustomerVO> getTotalCustomerList() throws Exception {
		// 전체 고객 목록

		ArrayList<CustomerVO> list = new ArrayList<>();
		String sql = "select c_no, c_name, c_age, c_phone, c_team" + "from customer ";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerVO cVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cVo = new CustomerVO();
				cVo.setC_no(rs.getString("c_no"));
				cVo.setC_name(rs.getString("c_name"));
				cVo.setC_phone(rs.getString("c_Phone"));
				cVo.setC_age(rs.getString("c_age"));
				cVo.setC_team(rs.getString("c_team"));

				list.add(cVo);
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

	// 고객의 컬럼 이름을 가져오는 메소드
	public ArrayList<String> getTotalCustomerColumnName() {
		ArrayList<String> columnName = new ArrayList<String>();

		String sql = "select * from customer ";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// ResultSetMetaData 객체 변수 선언
		ResultSetMetaData rsmd = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			for (int i = 1; i <= cols; i++) {
				columnName.add(rsmd.getColumnName(i));
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

	// 고객 정보를 등록
	public boolean getCustomerInsert(CustomerVO cVo) throws Exception {

		// DB에서 경로를 찾아서 입력된 정보를 가져오는 커리문
		String sql = "insert into Customer (c_no , c_name , C_phone , c_age , c_ team )";

		Connection con = null;
		PreparedStatement pstmt = null;
		boolean employeeInsertsucess = false;

		try {

			con = DBUtil.getConnection();

			// 고객 번호, 이름 , 핸드폰 , 나이, 인원수
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cVo.getC_no());
			pstmt.setString(2, cVo.getC_name());
			pstmt.setString(3, cVo.getC_phone());
			pstmt.setString(4, cVo.getC_age());
			pstmt.setString(5, cVo.getC_team());

			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("고객 정보 ");
				alert.setHeaderText(" 고객 정보 등록 완료.");
				alert.setContentText(" 고객 정보 등록 성공!!");
				alert.showAndWait();
				employeeInsertsucess = true;
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("고객 정보 ");
				alert.setHeaderText("고객 정보에 누락 된 것이 있는지 확인해주세요.");
				alert.setContentText("고객 정보 등록 실패!!");
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

		return CustomerJoinUpdateSucess;

	}

	// 고객 정보를 가져오는 토탈 리스트
	public boolean getTotalCustomerList(int selectedIndex, String trim, String trim2, String trim3, String trim4,
			TextField txtr_recode) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean getReservationTotalList(int selectedIndex, String trim, String trim2, String trim3, String trim4,
			TextField txtr_recode) {
		// TODO Auto-generated method stub
		return false;
	}
}
