package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

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
		String sql = "select em_no, em_rank, em_name, em_id, em_passwd, em_phone, em_address, em_bank, em_account, em_entry, em_leaveday, em_whether "
				+ "from employeejoin" + "order by no";

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
				EVo.setEm_no(rs.getString("no"));
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

	// 데이터베읻스에서 직원 테이블 컬럼의 갯수
	public ArrayList<String> getEmployeeColumnName() throws Exception {
		ArrayList<String> columnName = new ArrayList<String>(); // 이 안에 직원의 정보를 넣는다

		String sql = "select * from EmployeeJoin order by no"; // order by를 사용해서 정렬

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

	// 등록한 직원의 정보 수정
	// 이름, 번호, 주소 , 은행명, 계좌번호

	public boolean getEmployeeJoin(String name, String phone, String address, String bank, String account)
			throws Exception {

		// DB에서 경로를 찾는다.
		String sql = "update EmployeeJoin set name=?, phone=?, address=?, bank=? , account=? where no=? ";

		Connection con = null;
		PreparedStatement pstmt = null;
		boolean studentUpdateSucess = false;

		try {

			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			pstmt.setString(3, address);
			pstmt.setString(4, bank);
			pstmt.setString(5, account);

			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("직원 정보 수정");
				alert.setHeaderText(" 직원 정보 수정 완료.");
				alert.setContentText(" 직원 정보 수정 성공!!");
				alert.showAndWait();
				studentUpdateSucess = true;
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("직원 정보 수정");
				alert.setHeaderText("직원 정보에 누락 된 것이 있는지 확인해주세요.");
				alert.setContentText("직원 정보 수정 실패!!");
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

	public boolean getEmployeeIdOverlap(String searchId) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean getEmployeeUpdate(String selectedEmployeeIndex, String trim, String trim2, String trim3,
			String trim4, String trim5, String trim6, String trim7) {
		// TODO Auto-generated method stub
		return false;
	}


	// 등록된 직원의 정보 삭제의 기능은 넣지 않았기 때문에 기능을 넣지 않았습니다.
}
