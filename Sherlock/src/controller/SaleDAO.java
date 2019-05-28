package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ReservationVO;
import model.SaleVO;

public class SaleDAO {

	// 객체를 저장한다
	public ArrayList<SaleVO> getSaleTotalList() throws Exception {

		ArrayList<SaleVO> list = new ArrayList<>();

		// sql문을 작성
		// 테마 이름, 예약날짜, 예약시간, 이용자이름, 핸드폰 번호, 이용인원

		String sql = "select * from sale";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SaleVO sVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				sVo = new SaleVO();

				sVo.setC_name(rs.getString("c_name"));
				sVo.setC_phone(rs.getString("c_phone"));
				sVo.setC_team(rs.getString("c_team"));
				sVo.setT_name(rs.getString("t_name"));
				sVo.setT_price(rs.getString("t_price"));
				sVo.setR_reserveddate(rs.getString("r_reserveddate"));
				sVo.setR_reservedtime(rs.getString("r_reservedtime"));

				list.add(sVo);

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

	public ArrayList<String> getSaleColumnName() {
		ArrayList<String> columnName = new ArrayList<String>(); // 이 안에 매출의 정보를 넣는다

		String sql = "select * from Sale order by c_name"; // order by를 사용해서 정렬

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

	public ArrayList<SaleVO> getTodaySaleList() {
		ArrayList<SaleVO> list = new ArrayList<>();

		// sql문 작성
		// 이름, 핸드폰 번호, 인원, 테마이름, 가격, 이용일,이용시간 을 불러와야하는 것
		// 객체 저장
		// String sql = "select * from reservation";

		String sql = "select * from sale where r_reserveddate = to_char( sysdate, 'YYYY-MM-DD')";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SaleVO sVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				sVo = new SaleVO();

				sVo.setC_name(rs.getString("c_name"));
				sVo.setC_phone(rs.getString("c_phone"));
				sVo.setC_team(rs.getString("c_team"));
				sVo.setT_name(rs.getString("t_name"));
				sVo.setT_price(rs.getString("t_price"));
				sVo.setR_reserveddate(rs.getString("r_reserveddate"));
				sVo.setR_reservedtime(rs.getString("r_reservedtime"));
				list.add(sVo);

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

	public ArrayList<SaleVO> getTotalSaleList() {
		ArrayList<SaleVO> list = new ArrayList<>();

		// sql문 작성
		// 이름, 핸드폰 번호, 인원, 테마이름, 가격, 이용일,이용시간 을 불러와야하는 것
		// 객체 저장
		String sql = "select * from sale";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SaleVO sVo1 = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sVo1 = new SaleVO();

				sVo1.setC_name(rs.getString("c_name"));
				sVo1.setC_phone(rs.getString("c_phone"));
				sVo1.setC_team(rs.getString("c_team"));
				sVo1.setT_name(rs.getString("t_name"));
				sVo1.setT_price(rs.getString("t_price"));
				sVo1.setR_reserveddate(rs.getString("r_reserveddate"));
				sVo1.setR_reservedtime(rs.getString("r_reservedtime"));
				list.add(sVo1);

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

	// 기간조회 매출 리스트
	public ArrayList<ReservationVO> getSelectInfo(String sDate, String eDate) {

		ArrayList<ReservationVO> list = new ArrayList<ReservationVO>();
		String sql = "select * from reservation " + "WHERE r_reserveddate BETWEEN to_date( ? ,'YYYY/MM/DD') "
				+ "AND to_date( ? ||'23:59:59','YYYY/MM/DD HH24:MI:SS') " + "order by r_reserveddate";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// SaleVO sVo = null;
		ReservationVO rVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sDate);
			pstmt.setString(2, eDate);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				rVo = new ReservationVO();

				rVo.setC_name(rs.getString("c_name"));
				rVo.setC_phone(rs.getString("c_phone"));
				rVo.setC_team(rs.getString("c_team"));
				rVo.setT_name(rs.getString("t_name"));
				rVo.setT_price(rs.getString("t_price"));
				rVo.setR_reserveddate(rs.getString("r_reserveddate"));
				rVo.setR_reservedtime(rs.getString("r_reservedtime"));

				list.add(rVo);
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

	// 선택한 기간 총매출액 가져오기
	public int getDatetotal(String sDate, String eDate) {
		String sql = "select sum(t_price) from reservation " + "WHERE r_reserveddate BETWEEN to_date( ? ,'YYYY/MM/DD') "
				+ "AND to_date( ? ||'23:59:59','YYYY/MM/DD HH24:MI:SS')";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int t_price = 0;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sDate);
			pstmt.setString(2, eDate);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				t_price = rs.getInt("sum(t_price)");
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
		return t_price;
	}

	public ArrayList<String> getColumnName() {
		ArrayList<String> columnName = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from reservation");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// ResultSetMetaData 객체 변수 선언
		ResultSetMetaData rsmd = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
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

}
