package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.EmployeeVO;
import model.ThemeVO;

public class ThemeDAO {
	
	//INSERT 만 일단 추가
	// 테마 전체 목록
		public ArrayList<ThemeVO> getThemeTotalList() throws Exception {

			// 로그인 후 관리하는 직원이 볼 수 있게 만들자 !
			// 테마 정보를 가져오는 리스트
			ArrayList<ThemeVO> list = new ArrayList<>();

			//테마 이름, 테마이용요금, 방 이용시간,테마 번호
			// 예약자 정보를 가진 테이블 이름 : theme
			String sql = "select t_no , t_name , t_price , t_time "
					+ " from theme " + "order by t_no ";

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ThemeVO tVo = null;
			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					tVo = new ThemeVO();

					tVo.setT_no(rs.getString("t_no"));
					tVo.setT_name(rs.getString("t_name"));
					tVo.setT_price(rs.getString("t_price"));
					tVo.setT_time(rs.getString("t_price"));
					list.add(tVo);
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

		public boolean getThemeInsert(ThemeVO tVo)throws Exception {

			// DB에서 경로를 찾는다.
			//입력된 모든 정보를 가져오는 쿼리문
			String sql = "insert into Theme (t_no, t_name, t_price, t_time) values ( ?, ?, ?, ?)";


			
			
			Connection con = null;
			PreparedStatement pstmt = null;
			boolean themeInsertsucess = false;

			try {

				con = DBUtil.getConnection();

				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, tVo.getT_no());
				pstmt.setString(2, tVo.getT_name());
				pstmt.setString(3, tVo.getT_price());
				pstmt.setString(4, tVo.getT_time());
				

				int i = pstmt.executeUpdate();

				if (i == 1) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("테마 정보 ");
					alert.setHeaderText(" 테마 정보 등록 완료.");
					alert.setContentText(" 테마 정보 등록 성공!!");
					alert.showAndWait();
					themeInsertsucess = true;
				} else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("테마 정보 ");
					alert.setHeaderText("테마 정보에 누락 된 것이 있는지 확인해주세요.");
					alert.setContentText("테마 정보 등록 실패!!");
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

			return themeInsertsucess;
		}
}
