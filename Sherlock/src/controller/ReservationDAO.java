package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.ReservationVO;

public class ReservationDAO {

		// 예약 전체 목록
		public ArrayList<ReservationVO> getReservationTotalList() throws Exception {
			
			ArrayList<ReservationVO> list = new ArrayList<>();
			// 이용자번호, 이름, 핸드폰번호, 테마, 성공여부
			String sql = "select no.c_no as c_no, cu.c_name as c_name, cu.c_phone as c_phone, th.t_name as t_name, re.r_escape as r_escape "
					+ "from customer c_no ,customer cu, reservation re, theme th "
					+ " where c_no = re.c_no, cu.c_name = re.c_name and cu.c_phone = re.c_phone and cu.c_no = re.c_no ";
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

		

		// 전체 수강 탭에서 핸드폰 검색
		public ArrayList<ReservationVO> getTotalReservationNameSearchList(String c_phone) throws Exception {
			//String c_phone = getC_phone(c_phone);
			ArrayList<ReservationVO> list = new ArrayList<>();
			String sql = "select cu.c_name as c_name, cu.c_phone as c_phone, th.t_name as t_name, re.r_escape as r_escape "
					+ "from customer cu, reservation re, theme th "
					+ " where cu.c_name = re.c_name and cu.c_phone = re.c_phone and cu.c_no = re.c_no ?";
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

		private String getC_phone(String c_phone) {
			// TODO Auto-generated method stub
			return null;
		}



		// 전체 수강 탭에서 고객이름 검색
		public ArrayList<ReservationVO> getTraineeStudentNameSearchList(String c_name) throws Exception {
			ArrayList<ReservationVO> list = new ArrayList<>();
			String sql = "select cu.c_name as c_name, cu.c_phone as c_phone, th.t_name as t_name, re.r_escape as r_escape "
					+ " from customer cu, reservation re, theme th "
					+ " where cu.c_name = re.c_name and cu.c_phone = re.c_phone and cu.c_no = re.c_no ";
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
	}
	


