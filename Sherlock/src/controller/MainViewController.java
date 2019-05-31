package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.CustomerVO;
import model.ReservationVO;

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
	TextField txt_todaySearchWord; // 오늘 검색!


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
	Button btnSearch2; // 당일 이용자 검색버튼
	@FXML
	Button btnTotal;// 전체 버튼
	@FXML
	Button btnTotal2; // today 전체 버튼
	
	
	@FXML
	Label lblCount;// 검색했을시 나오는 전체 인원 수 (total)
	@FXML
	Label lblCount1; // 검색했을시 나오는 전체 인원 수 (today)
	@FXML
	Label lbl_todaytotal; // 오늘날짜의 총매출액
	@FXML
	Label lbl_totalsale; //전체 매출

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
	// ReservationVO rVo = new ReservationVO(); // VO클래스를 인스턴스화
	// CustomerVO cvo = new CustomerVO();

	int selectedReservationIndex; // 고객들의 예약등록에서 정보 인덱스를 저장한다
	String TotlaReservationNumber = "";
	String reservationCode = ""; // 예약코드

	// VO의 정보를 불러오기 위해서
	ReservationVO selectedReservation;
	ReservationDAO rDao = new ReservationDAO();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {

			// 가져와야하는 전체이용자 예약 관리 컬럼 이름 설정하기
			// 새로만든 VO에서 가져온다
			// 고객 번호 , 이름, 핸드폰 번호, 테마이름, 탈출여부
			txtr_No.setEditable(false); // 무조건 변경 불가

			dpDate.setValue(LocalDate.now());
			
			//당일 매출을 가져오는 메소드 
			lbl_todaytotal.setText(rDao.gettodayt_price()+"");
			//전체 매출을 가져오는 메소드
			lbl_totalsale.setText(rDao.getTotalt_price()+"");


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

			// 예약시간
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

			cbx_t_Theme.setItems(FXCollections.observableArrayList("스릴러", "살인사건", "밀실"));
			cbx_c_Team.setItems(FXCollections.observableArrayList("1", "2", "3", "4", "5"));
			cbx_r_Payment.setItems(FXCollections.observableArrayList("카드", "현금", "계좌이체"));
			cbx_r_Pay.setItems(FXCollections.observableArrayList("결제 대기", "결제 완료", "결제 취소"));
			cbx_escape.setItems(FXCollections.observableArrayList("이용 대기", "탈출 성공", "탈출 실패"));
			cbxHint.setItems(FXCollections.observableArrayList("0회", "1회", "2회", "3회", "4회"));
			cbx_time.setItems(FXCollections.observableArrayList("11:00~12:00", "12:10~13:10", "13:20~14:20",
					"14:30~15:30", "15:40~16:40", "16:50~17:50", "18:00~19:00", "19:10~20:10", "20:20~21:20",
					"21:30~22:30", "22:40~23:40"));

			// 버튼 이벤트
			btnJoin.setOnAction(event -> handlerbtnJoinAction(event)); // 등록 버튼 이벤트->예약자 정보가 등록된다
			btnCorrect.setOnAction(event -> handlerbtnCorrectAction(event)); // 수정 버튼 이벤트->누르면 수정창이 뜬다
			btnOk.setOnAction(event -> handlerBtnOkAction(event)); // 창 초기화
			cbx_c_Team.setOnAction(event -> handlercbx_c_TeamAction(event)); // 인원 선택시 가격 자동으로 설정
			reservationTableView.setOnMouseClicked(event -> handlerreservationTableViewAction(event));// 테이블뷰 클릭 이벤트
			// 예약코드 이벤트 (일)
			dpDate.setOnAction(event -> handlerdpDateAction(event));
			// 예약코드 이벤트 (시간)
			cbx_time.setOnAction(event -> handlercbx_timeAction(event));
			// 예약코드 이벤트 (테마이름)
			cbx_t_Theme.setOnAction(event -> handlercbx_t_ThemeAction(event));
			txtr_Price.setEditable(false); // 금액은 수정 불가능하게

			// 테이블 뷰에서 전체 목록을 불러올 수 있게하는 람다식 버튼 이벤트
			btnSearch.setOnAction(event -> handlerBtnSearchAction(event));
			btnSearch2.setOnAction(event -> handlerBtnSearch2Action(event));
			// 전체 이용자 전체 버튼 이벤트
			btnTotal.setOnAction(event -> handlerbtnTotalAction(event));
			btnTotal2.setOnAction(event -> handlerbtnTotal2Action(event));

			// 오늘 하루 매출액 버튼을 누르면 금일 매출이 조회되게 할 수 있는 람다식 버튼 이벤트
			//btnTodaytotalsale.setOnAction(event -> handlerbtnTodaytotalsaleAction(event));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	// 테마이름 콤보박스를 누르면 자동으로 예약 번호에 값이 추가 되게 나오게 만드는 메소드
	private void handlercbx_t_ThemeAction(ActionEvent event) {

		String theme_name;

		theme_name = cbx_t_Theme.getSelectionModel().getSelectedItem();

		if (theme_name.equals("스릴러")) {
			reservationCode += "30"; // 입력값
		}
		if (theme_name.equals("살인사건")) {
			reservationCode += "31";
		}
		if (theme_name.equals("밀실")) {
			reservationCode += "32";
		}

		// 뷰에 있는 텍스트 박스와 연결 시킨다.
		txtr_No.setText(reservationCode);

		cbx_t_Theme.setDisable(true); // 선택시 수정불가

	}

	// 시간 버튼 누르면 자동 예약 버튼
	private void handlercbx_timeAction(ActionEvent event) {

		// 변수 선언
		String time_count;

		time_count = cbx_time.getSelectionModel().getSelectedItem();

		if (time_count.equals("11:00~12:00")) {
			reservationCode += "01";
		}
		if (time_count.equals("12:10~13:10")) {
			reservationCode += "02";
		}
		if (time_count.equals("13:20~14:20")) {
			reservationCode += "03";
		}
		if (time_count.equals("14:30~15:30")) {
			reservationCode += "04";
		}
		if (time_count.equals("15:40~16:40")) {
			reservationCode += "05";
		}
		if (time_count.equals("16:50~17:50")) {
			reservationCode += "06";
		}
		if (time_count.equals("18:00~19:00")) {
			reservationCode += "07";
		}
		if (time_count.equals("19:10~20:10")) {
			reservationCode += "08";
		}
		if (time_count.equals("20:20~21:20")) {
			reservationCode += "09";
		}
		if (time_count.equals("19:10~20:10")) {
			reservationCode += "010";
		}
		if (time_count.equals("21:30~22:30")) {
			reservationCode += "011";
		}
		if (time_count.equals("22:40~23:40")) {
			reservationCode += "012";
		}

		// 뷰에 있는 텍스트 박스와 연결 시킨다.
		txtr_No.setText(reservationCode);

		cbx_time.setDisable(true); // 선택시 수정불가
	}

	// 날짜를 누르면 입력이 되서 넘어가는 이벤트 핸들러
	private void handlerdpDateAction(ActionEvent event) {

		// get.Value로 받는다.
		LocalDate day = dpDate.getValue();

		// 위에서 선언한 예약번호는 += 더해서 나온다 + "" 문자열로 만든다
		reservationCode += day.getDayOfMonth() + "";

		// 한 번 선택하면 수정이 불가능하게 막아둔다.
		dpDate.setDisable(true);

	}

	// 인원을 선택했을 때, 값을 지정해주는 메소드
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

	// 예약 등록창
	public void handlerbtnJoinAction(ActionEvent event) {

		try {
			reservationDataList.removeAll(reservationDataList);

			ReservationVO rvo = null;
			ReservationDAO rdao = null;

			// rvo에서 받아와야하는 값들을 넣어준다.
			// dpDate는 저장할때는 dp.getValue.toString으로 받기

			rvo = new ReservationVO(txtr_No.getText().trim(), dpDate.getValue().toString(),
					cbx_time.getSelectionModel().getSelectedItem(), cbx_t_Theme.getSelectionModel().getSelectedItem(),
					txtc_Name.getText().trim(), cbx_c_Team.getSelectionModel().getSelectedItem(),
					txtc_Phone.getText().trim(), txtc_Age.getText().trim(), txtr_Price.getText().trim(),
					cbx_r_Payment.getSelectionModel().getSelectedItem(),
					cbx_r_Pay.getSelectionModel().getSelectedItem(), cbx_escape.getSelectionModel().getSelectedItem(),
					cbxHint.getSelectionModel().getSelectedItem());

			rdao = new ReservationDAO();
			rdao.getReservationInsert(rvo);

			if (rdao != null) {
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
				cbx_t_Theme.setDisable(false);
				cbx_time.setDisable(false);
				dpDate.setDisable(false);
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

	// 예약 정보 수정
	public void handlerbtnCorrectAction(ActionEvent event) {
		try {
			boolean sucess;

			ReservationDAO rDao = new ReservationDAO();
			// 등록한 예약정보를 수정한다

			// 형변환을 해줘야한다
			sucess = rDao.getReservationCorrect(txtr_No.getText(), dpDate.getValue() + "",
					cbx_time.getSelectionModel().getSelectedItem(), cbx_t_Theme.getSelectionModel().getSelectedItem(),
					txtc_Name.getText(), cbx_c_Team.getSelectionModel().getSelectedItem(), txtc_Phone.getText(),
					txtc_Age.getText(), txtr_Price.getText(), cbx_r_Payment.getSelectionModel().getSelectedItem(),
					cbx_r_Pay.getSelectionModel().getSelectedItem(), cbx_escape.getSelectionModel().getSelectedItem(),
					cbxHint.getSelectionModel().getSelectedItem());

			if (sucess) {
				// 등록된 것들을 지우자!
				reservationDataList.removeAll(reservationDataList);
				resvervationList();

				// 입력정보초기화
				txtc_Name.clear();
				txtc_Phone.clear();
				txtc_Age.clear();
				txtr_Price.clear();
				txtr_recode.clear();

				btnCorrect.setDisable(true);
				btnCancel.setDisable(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 전체 버튼을 눌렀을 때 나오는 today 리스트 불러오기 !
	private void handlerbtnTotal2Action(ActionEvent event) {

		try {

			reservationTodayDataList.removeAll(reservationTodayDataList);
			resvervationTodayList();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 전체 버튼을 눌렀을 때 나오는 전체를 다시 불러오는 !
	private void handlerbtnTotalAction(ActionEvent event) {

		try {

			reservationDataList.removeAll(reservationDataList);
			resvervationList();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 더블클릭했을시에 왼쪽으로 옮겨오는 이벤트 메소드
	private void handlerreservationTableViewAction(MouseEvent event) {
		// 마우스 왼쪽 클릭이면 수정 삭제

		if (event.getClickCount() != 2) {

			try {

				// 예약창에 있는 정보를 가져오기 위함
				selectedReservation = reservationTableView.getSelectionModel().getSelectedItem();

				// 저장된 정보를 가져오기 위함이다.
				selectedReservationIndex = Integer.parseInt(selectedReservation.getR_no());

				String selectedr_no = selectedReservation.getR_no(); // 예약번호
				// String selectedr_resrveddate = selectedReservation.getR_reserveddate();
				// DB연결을 해서 출력을 해야 하는 부분이라서 여기서 메소드 입력할 필요는 없다
				String selectedr_reservedtime = selectedReservation.getR_reservedtime(); // 예약시간
				String selectedt_name = selectedReservation.getT_name(); // 테마이름
				String selectedc_name = selectedReservation.getC_name(); // 고객이름
				String selectedc_team = selectedReservation.getC_team(); // 예약 인원
				String selectedc_phone = selectedReservation.getC_phone(); // 핸드폰
				String selectedc_age = selectedReservation.getC_age(); // 고객나이
				String selectedt_price = selectedReservation.getT_price(); // 이용금액
				String selectedr_payment = selectedReservation.getR_payment(); // 결제방법
				String selectedr_pay = selectedReservation.getR_pay(); // 결제여부
				String selectedr_escape = selectedReservation.getR_escape(); // 탈출 여부
				String selectedr_hint = selectedReservation.getR_escape(); // 힌트

				// 선택한 값을 가져와서 변경할 수 있게 하는 것으로 예약 번호는 변경이 불가능하다

				txtr_No.setText(selectedr_no);// 예약번호
				dpDate.setValue(LocalDate.now()); // 예약일
				cbx_time.setValue(selectedr_reservedtime);// 예약시간
				cbx_t_Theme.setValue(selectedt_name);// 테마종류
				txtc_Name.setText(selectedc_name); // 고객 이름
				txtc_Phone.setText(selectedc_phone); // 고객 핸드폰번호
				txtc_Age.setText(selectedc_age); // 고객 나이
				cbx_c_Team.setValue(selectedc_team); // 이용인원
				cbx_r_Payment.setValue(selectedr_payment); // 결제방법 변경가능
				cbx_r_Pay.setValue(selectedr_pay);// 결제여부
				cbx_escape.setValue(selectedr_escape);// 탈출여부
				cbxHint.setValue(selectedr_hint);// 힌트사용횟수

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	// 오늘 이용하는 이용자 검색 버튼 이벤트 (today)
	private void handlerBtnSearch2Action(ActionEvent event) {

		ArrayList<ReservationVO> todaysearchList = new ArrayList<ReservationVO>();

		ReservationVO rVo = null;
		ReservationDAO rDao = null;

		String todaysearchName = "";
		boolean searchResult = false;

		try {
			todaysearchName = txt_todaySearchWord.getText().trim();

			rDao = new ReservationDAO();

			todaysearchList = rDao.getReservationNametodaySearchList(todaysearchName);

			int rowCount = todaysearchList.size();
			System.out.println(rowCount);
			lblCount1.setText("검색 : " + rowCount + " 명");
			for (int index = 0; index < rowCount; index++) {

				rVo = todaysearchList.get(index);
				reservationTodayDataList.add(rVo);
			}

			if (todaysearchName.equals("")) {
				searchResult = true;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("이용자 정보 입력");
				alert.setHeaderText("이용자의 이름을 입력하시오.");
				alert.setContentText("일치하는 정보가 없습니다.");
				alert.showAndWait();
			}

			if (todaysearchList != null) {

				txtSearchWord.clear();
				reservationTodayDataList.removeAll(reservationTodayDataList);

				for (int index = 0; index < rowCount; index++) {
					rVo = todaysearchList.get(index);
					reservationTodayDataList.add(rVo); // 그날 정보 가져오기
					searchResult = true;
				}
			}
			if (!searchResult) {
				txtSearchWord.clear();
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("이용자 정보 검색");
				alert.setHeaderText(todaysearchName + " 이용자 이름과 일치하는 정보가 리스트에 없습니다.");
				alert.setContentText("다시 입력해주시길 바랍니다.");
				alert.showAndWait();
				txtSearchWord.clear();
				reservationTodayDataList.removeAll(reservationTodayDataList);

				int rowCount1 = todaysearchList.size();
				// 메인 뷰에 있는 라벨카운트와 연결 !
				lblCount1.setText("검색 : " + rowCount1 + " 명");
				for (int index = 0; index < rowCount1; index++) {
					rVo = todaysearchList.get(index);
					reservationTodayDataList.add(rVo);
				}

			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("이용자 정보 검색 오류");
			alert.setHeaderText("이용자 정보 검색에 오류가 발생했습니다.");
			alert.setContentText("예약자 명단에  존재하지 않습니다.");
			alert.showAndWait();
		}
	}

	// total 검색 버튼 이벤트
	public void handlerBtnSearchAction(ActionEvent event) {

		ArrayList<ReservationVO> searchList = new ArrayList<ReservationVO>();

		ReservationVO rVo = null;
		ReservationDAO rDao = null;

		String searchName = "";
		boolean searchResult = false;

		try {
			searchName = txtSearchWord.getText().trim();
			rDao = new ReservationDAO();
			searchList = rDao.getReservationNameSearchList(searchName);

			int rowCount = searchList.size();
			lblCount.setText("검색 : " + rowCount + " 명");

			for (int index = 0; index < rowCount; index++) {

				rVo = searchList.get(index);
				reservationDataList.add(rVo);
			}

			if (searchName.equals("")) {
				searchResult = true;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("이용자 정보 입력");
				alert.setHeaderText("이용자의 이름을 입력하시오.");
				alert.setContentText("일치하는 정보가 없습니다.");
				alert.showAndWait();
			}

			if (searchList != null) {
				// int rowCount = searchList.size();
				txtSearchWord.clear();
				reservationDataList.removeAll(reservationDataList);
				for (int index = 0; index < rowCount; index++) {
					rVo = searchList.get(index);
					reservationDataList.add(rVo);
					searchResult = true;
				}
			}
			if (!searchResult) {
				txtSearchWord.clear();
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("이용자 정보 검색");
				alert.setHeaderText(searchName + " 이용자 이름과 일치하는 정보가 리스트에 없습니다.");
				alert.setContentText("다시 입력해주시길 바랍니다.");
				alert.showAndWait();
				txtSearchWord.clear();
				reservationDataList.removeAll(reservationDataList);
				// int rowCount = searchList.size();
				lblCount.setText("검색 : " + rowCount + " 명");
				for (int index = 0; index < rowCount; index++) {
					rVo = searchList.get(index);
					reservationDataList.add(rVo);
				}

			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("이용자 정보 검색 오류");
			alert.setHeaderText("이용자 정보 검색에 오류가 발생했습니다.");
			alert.setContentText("예약자 명단에  존재하지 않습니다.");
			alert.showAndWait();
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
			// TotalCustomerDataList.add(cVo);
		}
	}

	// 확인 버튼 눌렀을 때 메인뷰탭으로 다시 연결되는 메소드
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

	// 핸드폰 번호 이벤트 핸들러

}