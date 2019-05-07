package control;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.JoinVO;

public class JoinDAO {

	//관리자 등록
	public boolean getMangerRegiste(JoinVO jvo) throws Exception {
		
		String sql = "insert into mangerjoin" + "id, password, name" + "valuse" + "(?,?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean joinSucess =false;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, jvo.getId());
			pstmt.setString(2, jvo.getPassword());
			pstmt.setString(3, jvo.getName());
			
			int i = pstmt.executeUpdate();
			
			//조건
			if (i == 1) {
	            Alert alert = new Alert(AlertType.INFORMATION);
	            alert.setTitle("관리자를 등록했습니다");
	            alert.setHeaderText("관리자 등록 완료");
	            alert.setContentText("관리선생님 등록 성공!!");
	            alert.showAndWait();
	            joinSucess = true;
				
			}
			
		}
		
	}
	

}
