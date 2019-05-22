package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.CustomerVO;
import model.ReservationVO;
import model.ThemeVO;

public class ReservationDAO {

	// 예약 전체 목록
	public ArrayList<ReservationVO> getReservationTotalList() throws Exception {

		// 로그인 후 관리하는 직원이 볼 수 있게 만들자 !
		// 예약한 사람들의 정보가 떠야한다
		ArrayList<ReservationVO> list = new ArrayList<>();

		// 예약자 번호, 이름, 핸드폰, 테마, 성공여부를 불러와야한다.
		// 예약자 정보를 가진 테이블 이름 : reservation
		String sql = "select c.c_no , c.c_name , c.c_phone , t.t_name , r.r_escape "
				+ " from customer c , theme t , reservation r " + "order by c_no ";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReservationVO rVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				rVo = new ReservationVO();

				rVo.setC_name(rs.getString("c_name"));
				rVo.setC_phone(rs.getString("c_phone"));
				rVo.setT_name(rs.getString("t_name"));
				rVo.setR_escape(rs.getString("r_escape"));
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

	// 전체 예약 탭에서 핸드폰 번호 입력으로 검색
	public ArrayList<ReservationVO> getTotalReservationSearchList(String c_phone) throws Exception {
		// String c_phone = getC_phone(c_phone);
		ArrayList<ReservationVO> list = new ArrayList<>();

		// 예약자 번호, 이름, 핸드폰, 테마, 성공여부를 불러와야한다.
		// 예약자 정보를 가진 테이블 이름 : reservation
		String sql = "select c.c_no , c.c_name , c.c_phone , t.t_name , r.r_escape "
				+ " from customer c , theme t , reservation r " + "order by c_phone ";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReservationVO rVo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c_phone);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				rVo = new ReservationVO();

				rVo.setC_name(rs.getString("c_name"));
				rVo.setC_phone(rs.getString("c_phone"));
				rVo.setT_name(rs.getString("t_name"));
				rVo.setR_escape(rs.getString("r_escape"));
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
	// 전체 예약 탭에서 이름 입력으로 검색
		public ArrayList<ReservationVO> getTotalReservationNameSearchList(String c_name) throws Exception {
			// String c_phone = getC_phone(c_phone);
			ArrayList<ReservationVO> list = new ArrayList<>();

			// 예약자 번호, 이름, 핸드폰, 테마, 성공여부를 불러와야한다.
			// 예약자 정보를 가진 테이블 이름 : reservation
			String sql = "select c.c_no , c.c_name , c.c_phone , t.t_name , r.r_escape "
					+ " from customer c , theme t , reservation r " + "order by c_phone ";

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ReservationVO rVo = null;
			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, c_name);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					rVo = new ReservationVO();

					rVo.setC_name(rs.getString("c_name"));
					rVo.setC_phone(rs.getString("c_phone"));
					rVo.setT_name(rs.getString("t_name"));
					rVo.setR_escape(rs.getString("r_escape"));
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

	// 예약 insert 쿼리문
	public boolean getReservationInsert(ReservationVO rVo) throws Exception {

		// DB에서 경로를 찾는다.
		// 입력된 모든 정보를 가져오는 쿼리문
		String sql = "insert into Reservation (r_no, r_payment, r_price, r_reserveddate, r_checkin, r_record, r_escape, c_no, em_no, "
				+ "t_no, c_name, c_phone, c_age, c_team, t_name) values ( ?, ?, ?, ?, ?, ?, ? ,? ,? ,? ,? ,? ,? ,? ,?)";

		Connection con = null;
		PreparedStatement pstmt = null;
		boolean reservationInsertsucess = false;

		try {

			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rVo.getR_no());
			pstmt.setString(2, rVo.getR_payment());
			pstmt.setString(3, rVo.getR_price());
			pstmt.setString(4, rVo.getR_reserveddate());
			pstmt.setString(5, rVo.getR_checkin());
			pstmt.setString(6, rVo.getR_record());
			pstmt.setString(7, rVo.getR_escape());
			pstmt.setString(8, rVo.getC_no());
			pstmt.setString(9, rVo.getEm_no());
			pstmt.setString(10, rVo.getT_no());
			pstmt.setString(11, rVo.getC_name());
			pstmt.setString(12, rVo.getC_phone());
			pstmt.setString(13, rVo.getC_age());
			pstmt.setString(14, rVo.getC_team());
			pstmt.setString(15, rVo.getT_name());

			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("예약 정보 ");
				alert.setHeaderText(" 예약 정보 등록 완료.");
				alert.setContentText(" 예약 정보 등록 성공!!");
				alert.showAndWait();
				reservationInsertsucess = true;
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("예약 정보 ");
				alert.setHeaderText("예약 정보에 누락 된 것이 있는지 확인해주세요.");
				alert.setContentText("예약 정보 등록 실패!!");
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

		return reservationInsertsucess;
	}

	// 전체 고객 컬럼리스트를 읽어오는 메소드
	public ArrayList<CustomerVO> getTotalCustomerColumnName() throws Exception {

		ArrayList<CustomerVO> list = new ArrayList<CustomerVO>(); // 고객리스트의 전체 정보를가져오는 것
		String sql = "select c_no , c_name , c_phone , c_age, c_team " + " from customer " + "order by c_no ";

		// 반환타입
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
				cVo.setC_no(rs.getString(0));
				cVo.setC_name(rs.getString("c_name"));
				cVo.setC_phone(rs.getString("c_phone"));
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

	/*
	 * public ArrayList<CustomerVO> getTotalCustomerList() { ArrayList<CustomerVO>
	 * list = new ArrayList<CustomerVO>(); // 고객리스트의 전체 정보를 가져오는 것 String sql =
	 * "select c_no , c_name , c_phone , c_age, c_team " + " from customer " +
	 * "order by c_no ";
	 * 
	 * // 반환타입 Connection con = null; PreparedStatement pstmt = null; ResultSet rs =
	 * null; CustomerVO cVo = null; try { con = DBUtil.getConnection(); pstmt =
	 * con.prepareStatement(sql); rs = pstmt.executeQuery(); while (rs.next()) { cVo
	 * = new CustomerVO(); cVo.setC_no(rs.getString(0));
	 * cVo.setC_name(rs.getString("c_name"));
	 * cVo.setC_phone(rs.getString("c_phone")); cVo.setC_age(rs.getString("c_age"));
	 * cVo.setC_team(rs.getString("c_team")); list.add(cVo); } } catch (SQLException
	 * se) { System.out.println(se); } catch (Exception e) { System.out.println(e);
	 * } finally { try { if (rs != null) rs.close(); if (pstmt != null)
	 * pstmt.close(); if (con != null) con.close(); } catch (SQLException se) { } }
	 * return list;
	 * 
	 * }
	 */
	/*
	 * private String getC_phone(String c_phone) { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * 
	 * 
	 * // 전체 수강 탭에서 고객이름으로 검색 public ArrayList<ReservationVO>
	 * getTraineeStudentNameSearchList(String c_name) throws Exception {
	 * ArrayList<ReservationVO> list = new ArrayList<>();
	 * 
	 * 
	 * //예약자 번호, 이름, 핸드폰, 테마, 성공여부를 불러와야한다. //예약자 정보를 가진 테이블 이름 : reservation String
	 * sql = "select c.c_no , c.c_name , c.c_phone , t.t_name , r.r_escape " +
	 * " from customer c , theme t , reservation r " + "order by c_name " ;
	 * 
	 * 
	 * 
	 * Connection con = null; PreparedStatement pstmt = null; ResultSet rs = null;
	 * ReservationVO rVo = null; try { con = DBUtil.getConnection(); pstmt =
	 * con.prepareStatement(sql); pstmt.setString(1, c_name); rs =
	 * pstmt.executeQuery(); while (rs.next()) { rVo = new ReservationVO();
	 * 
	 * rVo.setC_name(rs.getString("c_name"));
	 * rVo.setC_phone(rs.getString("c_phone"));
	 * rVo.setT_name(rs.getString("t_name"));
	 * rVo.setR_escape(rs.getString("r_escape")); list.add(rVo); } } catch
	 * (SQLException se) { System.out.println(se); } catch (Exception e) {
	 * System.out.println(e); } finally { try { if (rs != null) rs.close(); if
	 * (pstmt != null) pstmt.close(); if (con != null) con.close(); } catch
	 * (SQLException se) { } } return list; }
	 */

}
