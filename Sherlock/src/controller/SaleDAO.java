package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

}