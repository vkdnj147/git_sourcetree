package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.CustomerVO;
import model.ReservationVO;
import model.TotalReservationVO;

public class MainViewController implements Initializable {


   @FXML
   DatePicker dpDate; //

   @FXML
   ComboBox<String> cbx_t_Theme;// 테마 선택
   @FXML
   ComboBox<String> cbx_c_Team;// 인원수
   @FXML
   ComboBox<String> cbx_r_Payment;// 결제방법
   @FXML
   ComboBox<String> cbx_r_Pay;// 결제여부
   @FXML
   ComboBox<String> cbxHint;// 결제여부
   @FXML
   ComboBox<String> cbx_searchList;// 검색 분류
   @FXML
   ComboBox<String> cbx_escape; // 탈출 여부
   @FXML
   ComboBox<String> cbx_time; // 이용시간

   @FXML
   TextField txtc_Name;// 고객이름
   @FXML
   TextField txtSearchWord;// 검색
   @FXML
   TextField txtc_Phone;// 고객 핸드폰 번호
   @FXML
   TextField txtc_Age;// 고객 나이
   @FXML
   TextField txtr_Price;// 금액
   @FXML
   TextField txtr_recode;// 탈출기록

   @FXML
   TextField txtr_No; // 고객번호
   @FXML
   Button btnJoin;// 정보 등록버튼
   @FXML
   Button btnCorrect;// 수정 버튼
   @FXML
   Button btnOk;// 확인 버튼
   @FXML
   Button btnCancel;// 취소 버튼
   @FXML
   Button btnSearch;// 검색 버튼
   @FXML
   Button btnTotal;// 전체 버튼
   @FXML
   Label lblCount;// 카운트
   @FXML
   private Label lblIconImg; // 아이콘 이미지
   @FXML
   private ImageView iconImg; // 이미지뷰
   

   private static final ObservableList<CustomerVO> TotalCustomerDataList = null;
   private static List<ReservationVO> ResvervationList;

   @FXML
   TableView<ReservationVO> reservationTableView = new TableView<>();// 고객 테이블 인스턴스화
   @FXML
   TableView<ReservationVO> reservationTodayTableView = new TableView<>();// 고객 테이블 인스턴스화

   private ObservableList<ReservationVO> reservationDataList = FXCollections.observableArrayList();// 고객 정보를 저장
   private ObservableList<ReservationVO> reservationTodayDataList = FXCollections.observableArrayList();// 고객
   // 오류 나면 스테이틱 추가 // 정보를
   // 저장

   int selectedIndex; // 테이블에서 선택한 예약 정보 인덱스 저장

   // TODO Auto-generated method stub
   // 이벤트 핸들러
   //ReservationVO rVo = new ReservationVO(); // VO클래스를 인스턴스화
   //CustomerVO cvo = new CustomerVO();

   
   String selectedReservationIndex; // 고객들의 예약등록에서 정보 인덱스를 저장한다
   String TotlaReservationNumber = "";

   @Override
   public void initialize(URL location, ResourceBundle resources) {

      try {

         // 가져와야하는 전체이용자 예약 관리 컬럼 이름 설정하기
         // 새로만든 VO에서 가져온다
         // 고객 번호 , 이름, 핸드폰 번호, 테마이름, 탈출여부

         TableColumn colReservationR_no = new TableColumn("NO");
         colReservationR_no.setPrefWidth(50); // 크기 설정
         colReservationR_no.setCellValueFactory(new PropertyValueFactory<>("r_no"));

         // 예약날짜
         TableColumn colReservationR_reserveddate = new TableColumn("예 약 날 짜");
         colReservationR_reserveddate.setPrefWidth(50); // 크기 설정
         colReservationR_reserveddate.setCellValueFactory(new PropertyValueFactory<>("r_reserveddate"));

         // 예약날짜
         TableColumn colReservationR_reservedtime = new TableColumn("예 약 시 간");
         colReservationR_reservedtime.setPrefWidth(50); // 크기 설정
         colReservationR_reservedtime.setCellValueFactory(new PropertyValueFactory<>("r_reservedtime"));

         TableColumn colReservationT_name = new TableColumn("테 마");
         colReservationT_name.setPrefWidth(150);
         colReservationT_name.setCellValueFactory(new PropertyValueFactory<>("t_name"));

         TableColumn colReservationC_name = new TableColumn("이 름");
         colReservationC_name.setPrefWidth(150);
         colReservationC_name.setCellValueFactory(new PropertyValueFactory<>("c_name"));

         TableColumn colReservationC_team = new TableColumn("인 원 수");
         colReservationC_team.setPrefWidth(150);
         colReservationC_team.setCellValueFactory(new PropertyValueFactory<>("c_team"));

         TableColumn colReservationC_Phone = new TableColumn("핸 드 폰");
         colReservationC_Phone.setPrefWidth(150);
         colReservationC_Phone.setCellValueFactory(new PropertyValueFactory<>("c_phone"));

         TableColumn colReservationC_age = new TableColumn("나 이");
         colReservationC_age.setPrefWidth(150);
         colReservationC_age.setCellValueFactory(new PropertyValueFactory<>("c_age"));

         TableColumn colReservationT_price = new TableColumn("금 액");
         colReservationT_price.setPrefWidth(150);
         colReservationT_price.setCellValueFactory(new PropertyValueFactory<>("t_price"));

         TableColumn colReservationR_payment = new TableColumn("결 제 방 법");
         colReservationR_payment.setPrefWidth(150);
         colReservationR_payment.setCellValueFactory(new PropertyValueFactory<>("r_payment"));

         TableColumn colReservationR_pay = new TableColumn("결 제 여 부");
         colReservationR_pay.setPrefWidth(150);
         colReservationR_pay.setCellValueFactory(new PropertyValueFactory<>("r_pay"));

         TableColumn colReservationR_escape = new TableColumn("탈 출 여 부");
         colReservationR_escape.setPrefWidth(150);
         colReservationR_escape.setCellValueFactory(new PropertyValueFactory<>("r_escape"));

         TableColumn colReservationR_hint = new TableColumn("힌 트");
         colReservationR_hint.setPrefWidth(150);
         colReservationR_hint.setCellValueFactory(new PropertyValueFactory<>("r_hint"));

         // today 전체 관련된 값
         reservationTodayTableView.setItems(reservationTodayDataList);

         // 컬럼값을 집어 넣고
         reservationTodayTableView.getColumns().addAll(colReservationR_no, colReservationR_reserveddate,
               colReservationR_reservedtime, colReservationT_name, colReservationC_name, colReservationC_team,
               colReservationC_Phone, colReservationC_age, colReservationT_price, colReservationR_payment,
               colReservationR_pay, colReservationR_escape, colReservationR_hint);
         // 밑에 부터 수정해야 함 5월 20일

         // 전체 이용자 목록을 불러오는 것으로 선언, 정보를 불러오기 위한 것들은 밑에 메소드에서 추가해야함
         resvervationTodayList();

         
         
         TableColumn colReservationR_no1 = new TableColumn("NO");
         colReservationR_no1.setPrefWidth(50); // 크기 설정
         colReservationR_no1.setCellValueFactory(new PropertyValueFactory<>("r_no"));

         // 예약날짜
         TableColumn colReservationR_reserveddate1 = new TableColumn("예 약 날 짜");
         colReservationR_reserveddate1.setPrefWidth(50); // 크기 설정
         colReservationR_reserveddate1.setCellValueFactory(new PropertyValueFactory<>("r_reserveddate"));

         // 예약날짜
         TableColumn colReservationR_reservedtime1 = new TableColumn("예 약 시 간");
         colReservationR_reservedtime1.setPrefWidth(50); // 크기 설정
         colReservationR_reservedtime1.setCellValueFactory(new PropertyValueFactory<>("r_reservedtime"));

         TableColumn colReservationT_name1 = new TableColumn("테 마");
         colReservationT_name1.setPrefWidth(150);
         colReservationT_name1.setCellValueFactory(new PropertyValueFactory<>("t_name"));

         TableColumn colReservationC_name1 = new TableColumn("이 름");
         colReservationC_name1.setPrefWidth(150);
         colReservationC_name1.setCellValueFactory(new PropertyValueFactory<>("c_name"));

         TableColumn colReservationC_team1 = new TableColumn("인 원 수");
         colReservationC_team1.setPrefWidth(150);
         colReservationC_team1.setCellValueFactory(new PropertyValueFactory<>("c_team"));

         TableColumn colReservationC_Phone1 = new TableColumn("핸 드 폰");
         colReservationC_Phone1.setPrefWidth(150);
         colReservationC_Phone1.setCellValueFactory(new PropertyValueFactory<>("c_phone"));

         TableColumn colReservationC_age1 = new TableColumn("나 이");
         colReservationC_age1.setPrefWidth(150);
         colReservationC_age1.setCellValueFactory(new PropertyValueFactory<>("c_age"));

         TableColumn colReservationT_price1 = new TableColumn("금 액");
         colReservationT_price1.setPrefWidth(150);
         colReservationT_price1.setCellValueFactory(new PropertyValueFactory<>("t_price"));

         TableColumn colReservationR_payment1 = new TableColumn("결 제 방 법");
         colReservationR_payment1.setPrefWidth(150);
         colReservationR_payment1.setCellValueFactory(new PropertyValueFactory<>("r_payment"));

         TableColumn colReservationR_pay1 = new TableColumn("결 제 여 부");
         colReservationR_pay1.setPrefWidth(150);
         colReservationR_pay1.setCellValueFactory(new PropertyValueFactory<>("r_pay"));

         TableColumn colReservationR_escape1 = new TableColumn("탈 출 여 부");
         colReservationR_escape1.setPrefWidth(150);
         colReservationR_escape1.setCellValueFactory(new PropertyValueFactory<>("r_escape"));

         TableColumn colReservationR_hint1 = new TableColumn("힌 트");
         colReservationR_hint1.setPrefWidth(150);
         colReservationR_hint1.setCellValueFactory(new PropertyValueFactory<>("r_hint"));
         
         // 전체 관련된 값
         reservationTableView.setItems(reservationDataList);

         // 컬럼값을 집어 넣고
         reservationTableView.getColumns().addAll(colReservationR_no1, colReservationR_reserveddate1,
               colReservationR_reservedtime1, colReservationT_name1, colReservationC_name1, colReservationC_team1,
               colReservationC_Phone1, colReservationC_age1, colReservationT_price1, colReservationR_payment1,
               colReservationR_pay1, colReservationR_escape1, colReservationR_hint1);

         resvervationList();

         // 고객의 정보를 불러오는것
         // TotalCustomerList();

         // 콤보 박스 값 불러오기
         cbx_searchList.setItems(FXCollections.observableArrayList("핸드폰", "이름"));
         cbx_t_Theme.setItems(FXCollections.observableArrayList("스릴러", "살인사건", "밀실"));
         cbx_c_Team.setItems(FXCollections.observableArrayList("1", "2", "3", "4", "5"));
         cbx_r_Payment.setItems(FXCollections.observableArrayList("카드", "현금", "계좌이체"));
         cbx_r_Pay.setItems(FXCollections.observableArrayList("결제 대기", "결제 완료", "결제 취소"));
         cbx_escape.setItems(FXCollections.observableArrayList("이용 대기", "탈출 성공", "탈출 실패"));
         cbxHint.setItems(FXCollections.observableArrayList("0회", "1회", "2회", "3회", "4회"));
         cbx_time.setItems(FXCollections.observableArrayList("11:00~12:00", "12:10~13:10", "13:20~14:20",
               "14:30~15:30", "15:40~16:40", "16:50~17:50", "18:00~19:00", "19:10~20:10", "20:20~21:20",
               "21:30~22:30", "22:40~23:40"));

         btnJoin.setOnAction(event -> handlerbtnJoinAction(event)); // 등록 버튼 이벤트->예약자 정보가 등록된다
         btnCorrect.setOnAction(event -> handlerbtnCorrectAction(event)); // 수정 버튼 이벤트->누르면 수정창이 뜬다
         btnOk.setOnAction(event -> handlerBtnOkAction(event)); // 확인 버튼 이벤트->예약창에서 확인을 누르면 메인창으로 넘어간다
         cbx_c_Team.setOnAction(event -> handlercbx_c_TeamAction(event));
         btnSearch.setOnAction(event -> handlerBtnSearchAction(event));

         txtr_Price.setEditable(false); // 금액은 수정 불가능하게

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void handlercbx_c_TeamAction(ActionEvent event) {

      // 클릭시 콤보박스에 맞는 값

      int price = 0;
      if (Integer.parseInt(cbx_c_Team.getSelectionModel().getSelectedItem()) == 1) {
         price = 35000;

      } else {
         price = Integer.parseInt(cbx_c_Team.getSelectionModel().getSelectedItem()) * 20000;
      }

      txtr_Price.setText(price + "");

      // set Text //금액수정 불가

   }

   // 등록창
   public void handlerbtnJoinAction(ActionEvent event) {

      try {
         reservationDataList.removeAll(reservationDataList);

         ReservationVO rvo = null;
         ReservationDAO cdao = null;

         rvo = new ReservationVO(txtr_No.getText().trim(), dpDate.getValue().toString(),
               cbx_time.getSelectionModel().getSelectedItem(), cbx_t_Theme.getSelectionModel().getSelectedItem(),
               txtc_Name.getText().trim(), cbx_c_Team.getSelectionModel().getSelectedItem(),
               txtc_Phone.getText().trim(), txtc_Age.getText().trim(), txtr_Price.getText().trim(),
               cbx_r_Payment.getSelectionModel().getSelectedItem(),
               cbx_r_Pay.getSelectionModel().getSelectedItem(), cbx_escape.getSelectionModel().getSelectedItem(),
               cbxHint.getSelectionModel().getSelectedItem());
         cdao = new ReservationDAO();
         cdao.getReservationInsert(rvo);

         if (cdao != null) {
            resvervationList();

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("예약 완료");
            alert.setHeaderText(txtc_Name.getText() + " 예약이 성공적으로 완료 되었습니다..");
            alert.setContentText("다음예약을 입력하세요");
            alert.showAndWait();
            txtr_No.clear();
            txtc_Name.clear();
            txtc_Phone.clear();
            txtc_Age.clear();
            txtr_Price.clear();
            txtr_recode.clear();
            txtc_Name.requestFocus();
         }

      } catch (Exception e) {
         Alert alert = new Alert(AlertType.WARNING);
         alert.setTitle("예약 정보 입력");
         alert.setHeaderText("예약 정보를 정확히 입력하시오.");
         alert.setContentText("다음에는 주의하세요!");
         alert.showAndWait();
      }
   }

   // 현재 불러와야 하는 창의 이름
   public void resvervationTodayList() throws Exception {

      reservationTodayDataList.removeAll(reservationTodayDataList);

      // 고객 번호 , 이름, 핸드폰 번호, 테마이름, 탈출여부를 불러와야하는 것
      // 객체 저장
      ReservationDAO rDao = new ReservationDAO();
      // 값을 다 받아와야만 불러 올 수 있게 해야 한다
      ReservationVO rVo = null;

      ArrayList<ReservationVO> list;

      list = rDao.getReservationTodayList();
      int rowCount = list.size();
      System.out.println(rowCount);
      for (int index = 0; index < rowCount; index++) {
         rVo = list.get(index);
         reservationTodayDataList.add(rVo);
      }

   }

   // 현재 불러와야 하는 창의 이름
   public void resvervationList() throws Exception {

      reservationDataList.removeAll(reservationDataList);

      // 고객 번호 , 이름, 핸드폰 번호, 테마이름, 탈출여부를 불러와야하는 것
      // 객체 저장
      ReservationDAO rDao = new ReservationDAO();
      // 값을 다 받아와야만 불러 올 수 있게 해야 한다
      ReservationVO rVo1 = null;

      ArrayList<ReservationVO> list;

      list = rDao.getReservationList();
      int rowCount = list.size();

      System.out.println(rowCount);

      for (int index = 0; index < rowCount; index++) {
         rVo1 = list.get(index);
         reservationDataList.add(rVo1);
      }

   }

   // 수정버튼을 누르면 안에 입력한 텍스트 박스의 값 초기화
   public void handlerbtnCorrectAction(ActionEvent event) {
      try {
         boolean sucess;
         CustomerDAO cdao = new CustomerDAO();
         sucess = cdao.getTotalCustomerList(selectedIndex, txtc_Name.getText().trim(), txtc_Phone.getText().trim(),
               txtc_Age.getText().trim(), txtr_Price.getText().trim(), txtr_recode);
         if (sucess) {
            TotalCustomerDataList.removeAll(TotalCustomerDataList);
            resvervationList();
            txtc_Name.clear();
            txtc_Phone.clear();
            txtc_Age.clear();
            txtr_Price.clear();
            txtr_recode.clear();
            btnJoin.setDisable(false);
            btnCorrect.setDisable(true);
            btnCancel.setDisable(true);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   // 검색 버튼 이벤트
   public void handlerBtnSearchAction(ActionEvent event) {

      String search = "";
      search = cbx_searchList.getSelectionModel().getSelectedItem();

      TotalReservationVO trVo = new TotalReservationVO();
      ReservationVO rVo = new ReservationVO();
      ReservationDAO rDao = new ReservationDAO();

      String searchName = "";
      boolean searchResult = false;

      //공백제외하고 값을 찾는다
      searchName = txtSearchWord.getText().trim();

      try {
         if (searchName.equals("") || search.equals("")) {
            try {
               searchResult = true;
               Alert alert = new Alert(AlertType.WARNING);
               alert.setTitle("예약 정보 검색");
               alert.setHeaderText("예약 검색 정보를 입력하시오.");
               alert.setContentText("일치 하는 값이 없습니다.");
               alert.showAndWait();
               resvervationList();
            } catch (Exception e) {
               e.printStackTrace();
            }
         } else {
            ArrayList<CustomerVO> title;
            ArrayList<ReservationVO> list = null;
            title = rDao.getTotalCustomerColumnName();
            int columnCount = title.size();

            if (search.equals("이름")) {

               // list = rDao.getReservationNameSearchList(searchName);
               if (list.size() == 0) {
                  txtSearchWord.clear();
                  Alert alert = new Alert(AlertType.INFORMATION);
                  alert.setTitle("고객 정보 검색");
                  alert.setHeaderText(searchName + " 고객이름이 리스트에 없습니다.");
                  alert.setContentText("다시 검색하세요.");
                  alert.showAndWait();
                  list = rDao.getReservationList();
               }
            }

            if (search.equals("핸드폰")) {

               // list = rDao.getTotalReservationNameSearchList(searchName);
               if (list.size() == 0) {
                  txtSearchWord.clear();
                  Alert alert = new Alert(AlertType.INFORMATION);
                  alert.setTitle("고객 정보 검색");
                  alert.setHeaderText(searchName + " 핸드폰 번호가 리스트에 없습니다.");
                  alert.setContentText("다시 검색하세요.");
                  alert.showAndWait();
                  list = rDao.getReservationList();
               }
            }

            txtSearchWord.clear();
            reservationDataList.removeAll(reservationDataList);
            int rowCount = list.size();
            lblCount.setText("검색 : " + rowCount + " 명");
            for (int index = 0; index < rowCount; index++) {

               rVo = list.get(index);
               reservationDataList.add(rVo);

            }
            searchResult = true;
         }
         if (!searchResult) {
            txtSearchWord.clear();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("고객 정보 검색");
            alert.setHeaderText(searchName + " 고객이 리스트에 없습니다.");
            alert.setContentText("다시 검색하세요.");
            alert.showAndWait();
         }
      } catch (

      Exception e) {
         e.printStackTrace();
      }
   }

   // 전체 고객 리스트 조회하기 위함
   public void TotalReservationList() throws Exception {
      TotalCustomerDataList.removeAll(TotalCustomerDataList);
      // 객체 생성
      CustomerDAO cDao = new CustomerDAO();

      CustomerVO cVo = new CustomerVO();
      ArrayList<String> title;
      ArrayList<CustomerVO> list;

      title = cDao.getTotalCustomerColumnName();
      int columnCount = title.size();

      list = cDao.getTotalCustomerList();

      int rowCount = list.size();
      lblCount.setText("전체 이용자 :" + rowCount + " 명");

      for (int index = 0; index < rowCount; index++) {

         cVo = list.get(index);
         //TotalCustomerDataList.add(cVo);
      }
   }

   /*
    * public void handlerbtnCancelAction(ActionEvent event) { try { FXMLLoader
    * loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
    * 
    * Parent mainView = (Parent) loader.load(); Scene scane = new Scene(mainView);
    * Stage mainMtage = new Stage();
    * 
    * mainMtage.setTitle("예약 접수"); mainMtage.setScene(scane);
    * 
    * Stage oldStage = (Stage) btnJoin.getScene().getWindow();
    * 
    * oldStage.close(); mainMtage.show(); } catch (IOException e) {
    * 
    * System.err.println("오류" + e);
    * 
    * } }
    */

   public void handlerBtnOkAction(ActionEvent event) {
      try {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainViewTab.fxml"));

         Parent mainView = (Parent) loader.load();
         Scene scane = new Scene(mainView);
         Stage mainMtage = new Stage();

         mainMtage.setTitle("메인 메뉴");
         mainMtage.setScene(scane);

         Stage oldStage = (Stage) btnJoin.getScene().getWindow();

         oldStage.close();
         mainMtage.show();
      } catch (IOException e) {

         System.err.println("오류" + e);

      }
   }

}