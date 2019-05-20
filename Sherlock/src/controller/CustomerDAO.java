package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.DBUtil;
import javafx.scene.control.TextField;
import model.CustomerVO;

public class CustomerDAO {
	// 전체 수강 탭에서 핸드폰 검색
		public ArrayList<CustomerVO> getTotalCustomerColumnName(String c_phone) throws Exception {
			ArrayList<CustomerVO> list = new ArrayList<>();
			String sql = "select c_no, c_name, c_age, c_phone, c_team"
					+ "from customer ";
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

		

		// 전체 수강 탭에서 이름 검색
		public ArrayList<CustomerVO> getTotalCustomerNameSearchList(String c_name) throws Exception {
			ArrayList<CustomerVO> list = new ArrayList<>();
			String sql = "select c_no, c_name, c_age, c_phone, c_team"
					+ "from customer ";
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



		public ArrayList<CustomerVO> getTotalCustomerList() throws Exception{
			//전체  고객 목록
			
				ArrayList<CustomerVO> list = new ArrayList<>();
				String sql = "select c_no, c_name, c_age, c_phone, c_team"
						+ "from customer ";
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



		public ArrayList<String> getTotalCustomerColumnName() {
			ArrayList<String> columnName = new ArrayList<String>();
			String sql = "select * from subject";
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

	

