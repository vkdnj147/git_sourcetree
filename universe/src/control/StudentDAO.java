package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.StudentVO;
import model.SubjectVO;

public class StudentDAO {

   public String getLoginName(String loginId) throws Exception {
      String sql = "select sd_name from student where sd_id = ?";
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String loginName = null;

      try {
         con = DBUtil.getConnection();
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, loginId);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            loginName = rs.getString(1);
         }
      } catch (SQLException e) {
         System.out.println("e=[" + e + "]");
      } catch (Exception e) {
         System.out.println("e=[" + e + "]");
      } finally {
         try {
            if (rs != null)
               rs.close();
            if (pstmt != null)
               pstmt.close();
            if (con != null)
               con.close();
         } catch (SQLException e) {
            System.out.println(e);
         }
      }
      return loginName;
   }

   // 학생 로그인
   public boolean getLogin(String loginId, String loginPassword) throws Exception {
      String sql = "select * from student where sd_id = ? and sd_password = ?";
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      boolean loginResult = false;

      try {
         con = DBUtil.getConnection();
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, loginId);
         pstmt.setString(2, loginPassword);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            loginResult = true; // 아이디와 패스워드 일치할 경우
         }
      } catch (SQLException e) {
         System.out.println("e=[" + e + "]");
      } catch (Exception e) {
         System.out.println("e=[" + e + "]");
      } finally {
         try {
            if (rs != null)
               rs.close();
            if (pstmt != null)
               pstmt.close();
            if (con != null)
               con.close();
         } catch (SQLException e) {
            System.out.println(e);
         }
      }
      return loginResult;
   }

   // 학생 전체 목록
   public ArrayList<StudentVO> getStudentTotalList() throws Exception {
      ArrayList<StudentVO> list = new ArrayList<>();

      String sql = "select st.no as no, sd_num, sd_name, sd_id, sd_passwd, "
            + "su.s_name as s_num, sd_birthday, sd_phone, sd_address, sd_email, sd_date "
            + " from STUDENT st, SUBJECT su" + " where st.s_num = su.s_num" + " order by no";

      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      StudentVO sVo = null;
      
      try {
         con = DBUtil.getConnection();
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();
         
         while (rs.next()) {
            sVo = new StudentVO();
            sVo.setNo(rs.getInt("no"));
            sVo.setSd_num(rs.getString("sd_num"));
            sVo.setSd_name(rs.getString("sd_name"));
            sVo.setSd_id(rs.getString("sd_id"));
            sVo.setSd_passwd(rs.getString("sd_passwd"));
            sVo.setS_num(rs.getString("s_num"));
            sVo.setSd_birthday(rs.getString("sd_birthday"));
            sVo.setSd_phone(rs.getString("sd_phone"));
            sVo.setSd_address(rs.getString("sd_address"));
            sVo.setSd_email(rs.getString("sd_email"));
            sVo.setSd_date(rs.getDate("sd_date") + "");
            
            list.add(sVo);
         }
      }catch (SQLException se) {
         System.out.println(se);
      }catch (Exception e) {
         System.out.println(e);
      }finally {
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
   
   // 동일 학과 학생 일련번호
   public String getStudentCount(String subjectNum) throws Exception {
      String sql = "select LPAD(count(*) + 1, 4, '0') as studentCount "
            + "from student where s_num = ? ";
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      String serialNumber = "";
      
      try {
         con = DBUtil.getConnection();
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, subjectNum);
         rs = pstmt.executeQuery();
         
         if(rs.next()) {
            serialNumber = rs.getString("studentCount");
         }
      }catch (SQLException se) {
         System.out.println(se);
      }catch (Exception e) {
         System.out.println(e);
      }finally {
         try {
            if (rs != null)
               rs.close();
            if (pstmt != null)
               pstmt.close();
            if (con != null)
               con.close();
         }catch (SQLException se) {
      }
   }
      return serialNumber;
   }   
   
   // 학생 아이디 중복 체크
   public boolean getStudentIdOverlap(String idOverlap) throws Exception {
      String sql = "select * from student where sd_id = ?";
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      boolean idOverlapResult = false;
      
      try {
         con = DBUtil.getConnection();
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, idOverlap);
         rs = pstmt.executeQuery();
         
         if(rs.next()) {
            idOverlapResult = true; // 중복된 아이디 있을 경우
         }
      }catch (SQLException e) {
         System.out.println("e=[" + e + "]");
      }catch (Exception e) {
         System.out.println("e=[" + e + "]");
      }finally {
         try {
            if (rs != null)
               rs.close();
            if (pstmt != null)
               pstmt.close();
            if (con != null)
               con.close();
         }catch (SQLException e) {
         }
      }
      return idOverlapResult;
   }
   
   // 학생 등록
   public void getStudentRegiste(StudentVO svo) throws Exception {
      String sql = "insert into student values "
            + "(student_seq.nextval, ?,?,?,?,?,?,?,?,?,sysdate)";
      Connection con = null;
      PreparedStatement pstmt = null;
      
      try {
         con = DBUtil.getConnection();
         pstmt = con.prepareStatement(sql);
         
         pstmt.setString(1,svo.getSd_num());
         pstmt.setString(2,svo.getSd_name());
         pstmt.setString(3,svo.getSd_id());
         pstmt.setString(4,svo.getSd_passwd());
         pstmt.setString(5,svo.getS_num());
         pstmt.setString(6,svo.getSd_birthday());
         pstmt.setString(7,svo.getSd_phone());
         pstmt.setString(8,svo.getSd_address());
         pstmt.setString(9,svo.getSd_email());
         
         int i = pstmt.executeUpdate();
         
         if(i==1) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("학생 등록");
            alert.setHeaderText(svo.getSd_name() + " 학생 등록 완료.");
            alert.setContentText("학생 등록 성공!!");
            alert.showAndWait();
         }else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("학생 등록");
            alert.setHeaderText("학생 등록 실패.");
            alert.setContentText("학생 등록 실패!!");
            alert.showAndWait();
         }
      }catch (SQLException e) {
         System.out.println("e=[" + e + "]");
      }catch (Exception e) {
         System.out.println("e=[" + e + "]");
      }finally {
         try {
            if (pstmt != null)
               pstmt.close();
            if (con != null)
               con.close();
         }catch (SQLException e) {
            System.out.println(e);
         }
      }
   }
   
   // student -> studnet!!!!!
   // 데이터베이스에서 학생 테이블에 컬럼의 갯수
   public ArrayList<String> getStudnetColumnName() throws Exception {
      ArrayList<String> columnName = new ArrayList<String>();
      
      String sql = "select * from student order by no";
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      ResultSetMetaData rsmd = null;
      
      try {
         con = DBUtil.getConnection();
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();
         rsmd = rs.getMetaData();
         int cols = rsmd.getColumnCount();
         
         for(int i = 1; i<=cols; i++) {
            columnName.add(rsmd.getCatalogName(i));
         }
      }catch (SQLException se) {
         System.out.println(se);
      }catch (Exception e) {
         System.out.println(e);
      }finally {
         try {
            if (rs !=null)
               rs.close();
            if (pstmt != null)
               pstmt.close();
            if (con != null)
               con.close();
         }catch (SQLException se) {
         }
      }
      return columnName;
   }
   
   // 학생 정보 수정
   public boolean getStudentUpdate(int no, String sd_passwd, String sd_birthday, String sd_phone,
         String sd_address, String sd_email) throws Exception {
      String sql = "update student set sd_passwd=?, sd_birthday=?, sd_phone=?,"
            + " sd_address=?, sd_email=?, where no=?";
      Connection con = null;
      PreparedStatement pstmt = null;
      boolean studentUpdateSucess = false;
      
      try {
         con = DBUtil.getConnection();
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, sd_passwd);
         pstmt.setString(2, sd_birthday);
         pstmt.setString(3, sd_phone);
         pstmt.setString(4, sd_address);
         pstmt.setString(5, sd_email);
         pstmt.setInt(6, no);
         
         int i = pstmt.executeUpdate();
         
         if(i==1) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("학생 정보 수정");
            alert.setHeaderText(no + " 학생 정보 수정 완료.");
            alert.setContentText("학생 정보 수정 성공!!");
            alert.showAndWait();
            studentUpdateSucess = true;
         }else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("학생 정보 수정");
            alert.setHeaderText("학생 정보 수정 실패.");
            alert.setContentText("학생 정보 수정 실패!!");
            alert.showAndWait();
         }
      }catch(SQLException e) {
         System.out.println("e=[" + e + "]");
      }catch (Exception e) {
         System.out.println("e=[" + e + "]");
      }finally {
         try {
            if (pstmt != null)
               pstmt.close();
            if (con != null)
               con.close();
         }catch (SQLException se) {
         }
      }
      return studentUpdateSucess;
   }
   
   // 학생 정보 삭제
   public boolean getStudentDelete(int no) throws Exception {
      StringBuffer sql = new StringBuffer();
      sql.append("delete from student where no =?");
      
      Connection con = null;
      PreparedStatement pstmt = null;
      boolean studentDeleteSucess = false;
      
      try {
         con = DBUtil.getConnection();
         pstmt = con.prepareStatement(sql.toString());
         pstmt.setInt(1,no);
          
         int i = pstmt.executeUpdate();
         
         if(i==1) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("학생 정보 삭제");
            alert.setHeaderText(no + " 학생 정보 삭제 완료.");
            alert.setContentText("학생 정보 삭제 성공!!");
            alert.showAndWait();
            studentDeleteSucess = true;
         } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("학생 정보 삭제");
            alert.setHeaderText(no + " 학생 정보 삭제 실패.");
            alert.setContentText("학생 정보 삭제 실패!!");
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
            }catch (SQLException e) {
            System.out.println(e);
            }
         }
         return studentDeleteSucess;
         }
      
   // 학과 목록
   public ArrayList<SubjectVO> subjectTotalList() throws Exception {
      ArrayList<SubjectVO> list = new ArrayList<>();
      
      String sql = "select s_name from subject order by no";
      
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      SubjectVO sVo = null;
      
      try {
         con = DBUtil.getConnection();
         pstmt = con.prepareStatement(sql.toString());
         rs = pstmt.executeQuery();
         while(rs.next()) {
            sVo = new SubjectVO();
            sVo.setS_name(rs.getString("s_name"));
            list.add(sVo);
         }
      }catch (SQLException se) {
         System.out.println(se);
      }catch (Exception e) {
         System.out.println(e);
      }finally {
         try {
            if (rs !=null)
               rs.close();
            if (pstmt != null)
               pstmt.close();
            if (con != null)
               con.close();
         }catch (SQLException se) {
         }
      }
      return list;
   }
   
}