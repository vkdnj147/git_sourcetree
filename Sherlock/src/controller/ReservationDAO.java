package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.CustomerVO;
import model.ReservationVO;

public class ReservationDAO {

   // 전체 고객 컬럼리스트를 읽어오는 메소드
   public ArrayList<CustomerVO> getTotalCustomerColumnName() throws Exception {

      ArrayList<CustomerVO> list = new ArrayList<CustomerVO>();
      // 고객리스트의 전체 정보를 가져오는 것
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

   public ArrayList<CustomerVO> getTotalCustomerList() {
      ArrayList<CustomerVO> list = new ArrayList<CustomerVO>();
      // 고객리스트의 전체 정보를 가져오는 것
      String sql = "select * from Customer";
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

   // 객체 저장
   public ArrayList<ReservationVO> getReservationTotalList() throws Exception {

      ArrayList<ReservationVO> list = new ArrayList<>();

      // sql문 작성
      // 고객 번호 , 이름, 핸드폰 번호, 테마이름, 탈출여부를 불러와야하는 것
      // 객체 저장
      String sql = "select * from reservation";

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

            rVo.setR_no(rs.getString("r_no"));
            rVo.setR_reserveddate(rs.getString("r_reserveddate"));
            rVo.setR_reservedtime(rs.getString("r_reservedtime"));
            rVo.setT_name(rs.getString("t_name"));
            rVo.setC_name(rs.getString("c_name"));
            rVo.setC_team(rs.getString("c_team"));
            rVo.setC_phone(rs.getString("c_phone"));
            rVo.setC_age(rs.getString("c_age"));
            rVo.setT_price(rs.getString("t_price"));
            rVo.setR_payment(rs.getString("r_payment"));
            rVo.setR_pay(rs.getString("r_pay"));
            rVo.setR_escape(rs.getString("r_escape"));
            rVo.setR_hint(rs.getString("r_hint"));
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

      // DB에서 경로를 찾는다
      // 입력된 모든 정보를 가져오는 쿼리문
      String sql = "insert into Reservation (r_no, r_reserveddate, r_reservedtime, t_name, c_name, c_team, c_phone , c_age , t_price,"
            + "r_payment , r_pay , r_escape , r_hint ) values (?, ?, ?, ?, ?, ?, ?, ? ,? ,? ,? ,? ,? )";

      Connection con = null;
      PreparedStatement pstmt = null;
      boolean reservationInsertsucess = false;
      try {

         con = DBUtil.getConnection();

         pstmt = con.prepareStatement(sql);

         pstmt.setString(1, rVo.getR_no()); // 예약자 번호
         pstmt.setString(2, rVo.getR_reserveddate()); // 예약날짜
         pstmt.setString(3, rVo.getR_reservedtime()); // 예약시간
         pstmt.setString(4, rVo.getT_name()); // 테마이름
         pstmt.setString(5, rVo.getC_name()); // 예약자 이름
         pstmt.setString(6, rVo.getC_team()); // 예약인원
         pstmt.setString(7, rVo.getC_phone()); // 핸드폰
         pstmt.setString(8, rVo.getC_age());
         pstmt.setString(9, rVo.getT_price()); // 금액
         pstmt.setString(10, rVo.getR_payment()); // 결제 방법 선택
         pstmt.setString(11, rVo.getR_pay()); // 결제 완료 여부
         pstmt.setString(12, rVo.getR_escape()); // 탈출여부
         pstmt.setString(13, rVo.getR_hint()); // 힌트

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

   public ArrayList<ReservationVO> getReservationList() {

      ArrayList<ReservationVO> list = new ArrayList<>();

      // sql문 작성
      // 고객 번호 , 이름, 핸드폰 번호, 테마이름, 탈출여부를 불러와야하는 것
      // 객체 저장
      String sql = "select * from reservation";

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

            rVo.setR_no(rs.getString("r_no"));
            rVo.setR_reserveddate(rs.getString("r_reserveddate"));
            rVo.setR_reservedtime(rs.getString("r_reservedtime"));
            rVo.setT_name(rs.getString("t_name"));
            rVo.setC_name(rs.getString("c_name"));
            rVo.setC_team(rs.getString("c_team"));
            rVo.setC_phone(rs.getString("c_phone"));
            rVo.setC_age(rs.getString("c_age"));
            rVo.setT_price(rs.getString("t_price"));
            rVo.setR_payment(rs.getString("r_payment"));
            rVo.setR_pay(rs.getString("r_pay"));
            rVo.setR_escape(rs.getString("r_escape"));
            rVo.setR_hint(rs.getString("r_hint"));
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

   public ArrayList<ReservationVO> getReservationTodayList() {

      ArrayList<ReservationVO> list = new ArrayList<>();

      // sql문 작성
      // 고객 번호 , 이름, 핸드폰 번호, 테마이름, 탈출여부를 불러와야하는 것
      // 객체 저장
      // String sql = "select * from reservation";

      String sql = "select * from reservation where r_reserveddate = to_char( sysdate, 'YYYY-MM-DD')";

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

            rVo.setR_no(rs.getString("r_no"));
            rVo.setR_reserveddate(rs.getString("r_reserveddate"));
            rVo.setR_reservedtime(rs.getString("r_reservedtime"));
            rVo.setT_name(rs.getString("t_name"));
            rVo.setC_name(rs.getString("c_name"));
            rVo.setC_team(rs.getString("c_team"));
            rVo.setC_phone(rs.getString("c_phone"));
            rVo.setC_age(rs.getString("c_age"));
            rVo.setT_price(rs.getString("t_price"));
            rVo.setR_payment(rs.getString("r_payment"));
            rVo.setR_pay(rs.getString("r_pay"));
            rVo.setR_escape(rs.getString("r_escape"));
            rVo.setR_hint(rs.getString("r_hint"));
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