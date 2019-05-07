package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.SubjectVO;

public class SubjectDAO {

	//학과목록
	public ArrayList<SubjectVO> getSubjectTotalList() throws Exception {
		ArrayList<SubjectVO>list = new ArrayList<>();
		
		String sql = "select * from subject oder by no";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SubjectVO sVo = null;
		
		try {
			
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sVo = new SubjectVO();
				sVo.setNo(rs.getInt("no"));
				sVo.setS_num(rs.getString("s_num"));
				sVo.setS_name(rs.getString("s_name"));
			}
		}
		
		
	}
	
	
}
