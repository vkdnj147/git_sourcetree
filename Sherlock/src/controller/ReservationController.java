package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

public class ReservationController implements Initializable {

	private static final ObservableList<TotalReservationVO> TotalCustomerDataList = null;
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
	Button btnFile;// 실패 버튼 -> 누르면 실패가 등록되게 설정
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
	@FXML
	DatePicker dpDate;
	@FXML
	TableView<TotalReservationVO> TotalReservationTableView = new TableView<>();// 고객 테이블 인스턴스화
	ObservableList<TotalReservationVO> TotalReservationDataList = FXCollections.observableArrayList();// 고객 정보를 저장

	int selectedIndex; // 테이블에서 선택한 예약 정보 인덱스 저장

	// TODO Auto-generated method stub
	// 이벤트 핸들러
	ReservationVO rVo = new ReservationVO(); // VO클래스를 인스턴스화
	CustomerVO cvo = new CustomerVO();
	TotalReservationVO trVo = new TotalReservationVO();
	// 등록되기 전의 정보가 저장될 배열공간
	ObservableList<TotalReservationVO> data = FXCollections.observableArrayList(); // 입력한 데이터를 임시객체로 저장 할 수 있도록 해준다.
	// 등록완료된 정보들이 저장될 배열공간
	ObservableList<TotalReservationVO> data1 = null;
	String selectedReservationIndex; // 고객들의 예약등록에서 정보 인덱스를 저장한다
	String TotlaReservationNumber = "";

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {

			// 가져와야하는 전체이용자 예약 관리 컬럼 이름 설정하기
			// 새로만든 VO에서 가져온다
			TableColumn colTotalReservationNo = new TableColumn("NO");

			colTotalReservationNo.setPrefWidth(50); // 크기 설정
			colTotalReservationNo.setStyle("-fx-allignment : CENTER");
			colTotalReservationNo.setCellValueFactory(new PropertyValueFactory<>("c_no"));

			TableColumn colTotalReservationName = new TableColumn("이름");
			colTotalReservationName.setPrefWidth(150);
			colTotalReservationName.setStyle("-fx-allignment : CENTER");
			colTotalReservationName.setCellValueFactory(new PropertyValueFactory<>("c_name"));

			TableColumn colTotalReservationPhone = new TableColumn("핸드폰");
			colTotalReservationPhone.setPrefWidth(150);
			colTotalReservationPhone.setStyle("-fx-allignment: CENTER");
			colTotalReservationPhone.setCellValueFactory(new PropertyValueFactory<>("c_name"));

			// 테마 테이블 뷰 컬럼 이름 설정

			TableColumn colTotalReservationTheme = new TableColumn("테마");
			colTotalReservationTheme.setPrefWidth(150);
			colTotalReservationTheme.setStyle("-fx-allignment: CENTER");
			colTotalReservationTheme.setCellValueFactory(new PropertyValueFactory<>("c_num"));

			// 탈출 테이블 뷰 컬럼 이름 설정

			TableColumn colTotalReservationEscape = new TableColumn("탈출 여부");
			colTotalReservationEscape.setPrefWidth(150);
			colTotalReservationEscape.setStyle("-fx-allignment: CENTER");
			colTotalReservationEscape.setCellValueFactory(new PropertyValueFactory<>("r_escape"));

			TotalReservationTableView.setItems(TotalReservationDataList);
			TotalReservationTableView.getColumns().addAll(colTotalReservationNo, colTotalReservationName,
					colTotalReservationPhone, colTotalReservationTheme, colTotalReservationEscape);

			// 밑에 부터 수정해야 함 5월 20일

			// 전체 이용자 목록을 불러오는 것으로 선언, 정보를 불러오기 위한 것들은 밑에 메소드에서 추가해야함
			TotalResvervationList();

			// 고객의 정보를 불러오는것
			TotalCustomerList();

			cbx_searchList.setItems(FXCollections.observableArrayList("핸드폰", "이름"));
			cbx_t_Theme.setItems(FXCollections.observableArrayList("스릴러", "살인사건", "밀실"));
			cbx_c_Team.setItems(FXCollections.observableArrayList("1", "2", "3", "4", "5"));
			cbx_r_Payment.setItems(FXCollections.observableArrayList("카드", "현금", "계좌이체"));
			cbx_r_Pay.setItems(FXCollections.observableArrayList("결제 대기", "결제 완료", "결제 취소"));
			cbxHint.setItems(FXCollections.observableArrayList("1회", "2회", "3회", "4회"));

			// btnFile.setOnAction(event -> handlerbtnFileAction(event)); // 실패 버튼 이벤트->실패
			// 버튼을 누르면 실패 등록
			btnJoin.setOnAction(event -> handlerbtnJoinAction(event)); // 등록 버튼 이벤트->예약자 정보가 등록된다
			btnCorrect.setOnAction(event -> handlerbtnCorrectAction(event)); // 수정 버튼 이벤트->누르면 수정창이 뜬다
			btnOk.setOnAction(event -> handlerBtnOkAction(event)); // 확인 버튼 이벤트->예약창에서 확인을 누르면 메인창으로 넘어간다
			// btnCancel.setOnAction(event -> handlerbtnCancelAction(event)); // 취소 버튼 이벤트
			// ->취소를 누르면 다시 예약창으로 넘어간다
			btnSearch.setOnAction(event -> handlerBtnSearchAction(event));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 전체 이용자 리스트 목록은 여기 안에다가 집어 넣기
	private void TotalResvervationList() {
		// TODO Auto-generated method stub

	}

	// 실패 버튼을 누르면 실패라는 정보가 자동으로 넘어갈 수 있게 메소드를 추가해야한다.
	// 성공 실패 라디오 버튼을 만들고 그 정보를 읽어오는 것으로 한 후 비고란을 추가한 후에 기록할 수 있게 하는 것도 방법
	// 그게 아니라면 성공과 실패만 남아도 상관없을 것 같음

	// 실패하
	/*
	 * public void handlerbtnFileAction(ActionEvent event) {
	 * 
	 * }
	 */

	// 수정버튼을 누르면 안에 입력한 텍스트 박스의 값 초기화
	public void handlerbtnCorrectAction(ActionEvent event) {
		try {
			boolean sucess;
			CustomerDAO cdao = new CustomerDAO();
			sucess = cdao.getTotalCustomerList(selectedIndex, txtc_Name.getText().trim(), txtc_Phone.getText().trim(),
					txtc_Age.getText().trim(), txtr_Price.getText().trim(), txtr_recode);
			if (sucess) {
				TotalCustomerDataList.removeAll(TotalCustomerDataList);
				TotalCustomerList();
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
					TotalCustomerList();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				ArrayList<CustomerVO> title;
				ArrayList<ReservationVO> list = null;
				title = rDao.getTotalCustomerColumnName();
				int columnCount = title.size();

				if (search.equals("이름")) {

					list = rDao.getTotalReservationNameSearchList(searchName);
					if (list.size() == 0) {
						txtSearchWord.clear();
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("고객 정보 검색");
						alert.setHeaderText(searchName + " 고객이름이 리스트에 없습니다.");
						alert.setContentText("다시 검색하세요.");
						alert.showAndWait();
						list = rDao.getReservationTotalList();
					}
				}

				if (search.equals("핸드폰")) {

					list = rDao.getTotalReservationNameSearchList(searchName);
					if (list.size() == 0) {
						txtSearchWord.clear();
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("고객 정보 검색");
						alert.setHeaderText(searchName + " 핸드폰 번호가 리스트에 없습니다.");
						alert.setContentText("다시 검색하세요.");
						alert.showAndWait();
						list = rDao.getReservationTotalList(); 
					}
				}
				
				txtSearchWord.clear();
				TotalReservationDataList.removeAll(TotalReservationDataList);
				int rowCount = list.size();
				lblCount.setText("검색 : " + rowCount + " 명");
				for (int index = 0; index < rowCount; index++) {
					
					rVo = list.get(index);
					TotalReservationDataList.add(trVo);
					
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

	//전체 고객 리스트 조회하기 위함
	public void TotalCustomerList() throws Exception {
		TotalCustomerDataList.removeAll(TotalCustomerDataList);
		// 객체 생성
		CustomerDAO cDao = new CustomerDAO();

		CustomerVO cVo = null;
		ArrayList<String> title;
		ArrayList<CustomerVO> list;

		title = cDao.getTotalCustomerColumnName();
		int columnCount = title.size();

		list = cDao.getTotalCustomerList();

		int rowCount = list.size();
		lblCount.setText("전체 이용자 :" + rowCount + " 명");

		for (int index = 0; index < rowCount; index++) {

			cVo = list.get(index);
			TotalCustomerDataList.add(trVo);
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

	//등록창
	public void handlerbtnJoinAction(ActionEvent event) {
		try {
			TotalCustomerDataList.removeAll(TotalCustomerDataList);
			ReservationVO rvo = null;
			CustomerDAO cdao = null;
			rvo = new ReservationVO(txtc_Age.getText().trim(), txtc_Name.getText().trim(), txtc_Phone.getText().trim(),
					txtr_Price.getText().trim(), txtr_recode.getText().trim(), txtSearchWord.getText().trim());
			cdao = new CustomerDAO();

			if (cdao != null) {
				TotalCustomerList();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("예약 완료");
				alert.setHeaderText(txtc_Name.getText() + " 예약이 성공적으로 완료 되었습니다..");
				alert.setContentText("다음예약을 입력하세요");
				alert.showAndWait();
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

}
