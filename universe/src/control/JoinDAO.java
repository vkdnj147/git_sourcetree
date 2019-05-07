package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.JoinVO;

public class JoinDAO {

	// 관리자 등록
	public boolean getMangerRegiste(JoinVO jvo) throws Exception {

		String sql = "insert into mangerjoin" + "id, password, name" + "valuse" + "(?,?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean joinSucess = false;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, jvo.getId());
			pstmt.setString(2, jvo.getPassword());
			pstmt.setString(3, jvo.getName());

			int i = pstmt.executeUpdate();

			// 조건
			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("관리자를 등록");
				alert.setHeaderText("관리자 등록 완료");
				alert.setContentText("관리선생님 등록 성공!!");
				alert.showAndWait();
				joinSucess = true;

			} else {

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("관리자 등록");
				alert.setHeaderText("관리선생님 등록 실패");
				alert.setContentText("관리선생님 등록 실패!!");
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

	// 아이디 중복 체크

	public boolean getIdOverlap(String idOverlap) throws Exception {

		String sql = "select * from managerjoin where id = ? ";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean idOverlapResult = false;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, idOverlap);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				idOverlapResult = true; // 중복된 아이디가 있습니다!

			}

		} catch (SQLException e) {

			System.out.println("e = [ " + e + " ] ");

		} catch (Exception e) {

			System.out.println("e = [ " + e + " ] ");

		} finally {

			try {

				// 6 데이터 베이스와의 연결에 사용된 오브젝트를 해제한다
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {

			}
		}

		return idOverlapResult;

	}

}
