package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import model.CustomerVO;
import model.ReservationVO;
import model.SaleVO;
import model.ThemeVO;
import model.TotalReservationVO;

public class TotalSaleController implements Initializable {
	@FXML
	private Label lblIconImg; // 아이콘 이미지
	@FXML
	private ImageView iconImg; // 이미지뷰
	@FXML
	DatePicker dpDate;// 날짜
	@FXML
	Button btnSearch;// 검색 버튼
	@FXML
	TextField txtDaytotal;// today 총액
	@FXML
	TextField txtDaytotal1;// 전체 총액

	@FXML
	Button btnTotal;// 전체 버튼
	@FXML
	Label lblCount;// 카운트
	@FXML
	private DatePicker dpStart; // 시작일
	@FXML
	private DatePicker dpEnd; // 종료일
	@FXML
	TableView<SaleVO> totalSaleTableView = new TableView<>();// 매출 테이블 인스턴스화
	@FXML
	TableView<SaleVO> todaySaleTableView = new TableView<>();// today매출 테이블 인스턴스화

	static ObservableList<SaleVO> totalSaleDataList = FXCollections.observableArrayList();// 매출 정보를 저장

	static ObservableList<SaleVO> todaySaleDataList = FXCollections.observableArrayList(); // today매출 정보를 저장

	// 각 VO 를 인스턴스화 하여서 가져온다
	SaleVO sVo = new SaleVO();
	CustomerVO cVo = new CustomerVO();
	ThemeVO tVo = new ThemeVO();
	ReservationVO rVo = new ReservationVO();

	ObservableList<SaleVO> data = FXCollections.observableArrayList();// 매출정보를 임시 저장

	ObservableList<ReservationVO> data2 = FXCollections.observableArrayList(); // 예약정보를 임시 저장
	// 등록완료된 정보들이 저장될 배열공간
	ObservableList<SaleVO> data1 = null;

	String o_date = null;
	String eDate = LocalDate.now() + "";// 끝날짜
	String sDate = LocalDate.now() + "";// 시작 날짜

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

		dpStart.setValue(LocalDate.now()); // 오늘 날짜로 설정
		dpEnd.setValue(LocalDate.now()); // 오늘 날짜로 설정
	    // 당일 매출 리스트
	      totalList();

		// 날짜 선택

		// 시작 날짜
		dpStart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sDate = dpStart.getValue() + "";
			}
		});

		// 끝날짜
		dpEnd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				eDate = dpEnd.getValue() + "";
			}
			
		});
		//txtDaytotal.setText(rDao.getDaytotal() + ""); // 금일 총매출액을 가져온다.

		try {
			TableColumn colSaleC_name1 = new TableColumn("이 름");
			colSaleC_name1.setPrefWidth(50); // 크기 설정
			colSaleC_name1.setCellValueFactory(new PropertyValueFactory<>("c_name"));

			TableColumn colSaleC_phone1 = new TableColumn("핸드폰");
			colSaleC_phone1.setPrefWidth(150);
			colSaleC_phone1.setCellValueFactory(new PropertyValueFactory<>("c_phone"));

			TableColumn colSaleC_team1 = new TableColumn("인 원");
			colSaleC_team1.setPrefWidth(50);
			colSaleC_team1.setCellValueFactory(new PropertyValueFactory<>("c_team"));

			TableColumn colSaleT_name1 = new TableColumn("테 마");
			colSaleT_name1.setPrefWidth(50); // 크기 설정
			colSaleT_name1.setCellValueFactory(new PropertyValueFactory<>("t_name"));

			TableColumn colSaleT_price1 = new TableColumn("가 격");
			colSaleT_price1.setPrefWidth(50); // 크기 설정
			colSaleT_price1.setCellValueFactory(new PropertyValueFactory<>("t_price"));

			TableColumn colSaleR_reserveddate1 = new TableColumn("이 용 일");
			colSaleR_reserveddate1.setPrefWidth(150); // 크기 설정
			colSaleR_reserveddate1.setCellValueFactory(new PropertyValueFactory<>("r_reserveddate"));

			TableColumn colSaleR_reservedtime1 = new TableColumn("이 용 시 간");
			colSaleR_reservedtime1.setPrefWidth(151); // 크기 설정
			colSaleR_reservedtime1.setCellValueFactory(new PropertyValueFactory<>("r_reservedtime"));
			// today 전체 관련된 값
			todaySaleTableView.setItems(todaySaleDataList);

			// 컬럼값을 집어 넣고
			todaySaleTableView.getColumns().addAll(colSaleC_name1, colSaleC_phone1, colSaleC_team1, colSaleT_name1,
					colSaleT_price1, colSaleR_reserveddate1, colSaleR_reservedtime1);

			// 오늘 이용자 목록을 불러오는 것으로 선언, 정보를 불러오기 위한 것들은 밑에 메소드에서 추가해야함
			todaySaleList();

			// 가져와야하는 매출 관리 컬럼 이름 설정하기
			// 새로만든 VO에서 가져온다 테마 이름,테마 가격,인원
			TableColumn colSaleC_name = new TableColumn("이 름");
			colSaleC_name.setPrefWidth(50); // 크기 설정
			colSaleC_name.setCellValueFactory(new PropertyValueFactory<>("c_name"));

			TableColumn colSaleC_phone = new TableColumn("핸드폰");
			colSaleC_phone.setPrefWidth(150);
			colSaleC_phone.setCellValueFactory(new PropertyValueFactory<>("c_phone"));

			TableColumn colSaleC_team = new TableColumn("인 원");
			colSaleC_team.setPrefWidth(50);
			colSaleC_team.setCellValueFactory(new PropertyValueFactory<>("c_team"));

			TableColumn colSaleT_name = new TableColumn("테 마");
			colSaleT_name.setPrefWidth(50); // 크기 설정
			colSaleT_name.setCellValueFactory(new PropertyValueFactory<>("t_name"));

			TableColumn colSaleT_price = new TableColumn("가 격");
			colSaleT_price.setPrefWidth(50); // 크기 설정
			colSaleT_price.setCellValueFactory(new PropertyValueFactory<>("t_price"));

			TableColumn colSaleR_reserveddate = new TableColumn("이 용 일");
			colSaleR_reserveddate.setPrefWidth(150); // 크기 설정
			colSaleR_reserveddate.setCellValueFactory(new PropertyValueFactory<>("r_reserveddate"));

			TableColumn colSaleR_reservedtime = new TableColumn("이 용 시 간");
			colSaleR_reservedtime.setPrefWidth(151); // 크기 설정
			colSaleR_reservedtime.setCellValueFactory(new PropertyValueFactory<>("r_reservedtime"));

			totalSaleList();
			// 전체 관련된 값
			totalSaleTableView.setItems(totalSaleDataList);

			btnTotal.setOnAction(event -> handlerBtnTotalAction(event));// 전체 버튼
			btnSearch.setOnAction(event -> handlerBtnSearchAction(event)); // 검색 버튼 이벤트

			// 컬럼값을 집어 넣고
			totalSaleTableView.getColumns().addAll(colSaleC_name, colSaleC_phone, colSaleC_team, colSaleT_name,
					colSaleT_price, colSaleR_reserveddate, colSaleR_reservedtime);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 조회 버튼
	public void handlerBtnSearchAction(ActionEvent event) {

		data2.removeAll(data2);

		SaleDAO sDao = new SaleDAO();
		ReservationVO rVo = null;
		ArrayList<String> title;
		ArrayList<ReservationVO> list2;

		title = sDao.getColumnName();
		int columnCount = title.size();

		list2 = sDao.getSelectInfo(sDate, eDate);
		int rowCount = list2.size();

		for (int index = 0; index < rowCount; index++) {
			rVo = list2.get(index);
			data2.add(rVo);
		}

	}

	// 전체 버튼 이벤트
	public void handlerBtnTotalAction(ActionEvent event) {
		try {
			totalSaleDataList.removeAll(totalSaleDataList);
			totalSaleList();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 전체 매출 리스트
	public void totalSaleList() throws Exception {
		try {
			totalSaleDataList.removeAll(totalSaleDataList);
			// 객체 생성
			SaleDAO sDao = new SaleDAO();

			SaleVO sVo = null;
			ArrayList<String> title;
			ArrayList<SaleVO> searchList;

			title = sDao.getSaleColumnName();
			int columnCount = title.size();

			searchList = sDao.getSaleTotalList();

			int rowCount = searchList.size();
			lblCount.setText("총원 : " + rowCount + " 명");

			for (int index = 0; index < rowCount; index++) {

				sVo = searchList.get(index);
				totalSaleDataList.add(sVo);
			}
		} catch (Exception e) {

		}
	}

	// 현재 불러와야 하는 창의 이름-today
	public void todaySaleList() throws Exception {

		todaySaleDataList.removeAll(todaySaleDataList);

		// 이름, 핸드폰 번호, 인원, 테마이름, 가격, 이용일,이용시간 을 불러와야하는 것
		// sale dao 를 인스턴스화
		SaleDAO sDao = new SaleDAO();
		// 값을 다 받아와야만 불러 올 수 있게 해야 한다
		SaleVO sVo = null;

		ArrayList<SaleVO> list;

		list = sDao.getTodaySaleList();
		int rowCount = list.size();

		for (int index = 0; index < rowCount; index++) {
			sVo = list.get(index);
			todaySaleDataList.add(sVo);
		}

	}

	// 현재 불러와야 하는 창의 이름 -전체
	public void totalsaleList() throws Exception {

		totalSaleDataList.removeAll(totalSaleDataList);

		// 이름, 핸드폰 번호, 인원, 테마이름, 가격, 이용일,이용시간 을 불러와야하는 것
		// sale dao 를 인스턴스화
		SaleDAO sDao1 = new SaleDAO();
		// 값을 다 받아와야만 불러 올 수 있게 해야 한다
		SaleVO sVo1 = null;

		ArrayList<SaleVO> list;

		list = sDao1.getTotalSaleList();
		int rowCount = list.size();

		System.out.println(rowCount);

		for (int index = 0; index < rowCount; index++) {
			sVo1 = list.get(index);
			totalSaleDataList.add(sVo1);
		}

	}
	 // 당일 매출 리스트
	   public void totalList() {

	      txtDaytotal.clear();
	     // txtDaytotal.setText(sDate.getDaytotal() + ""); // 기간 조회한 날짜의 총매출액을 가져온다.
	      
	      data2.removeAll(data2);

	      SaleDAO sDao = new SaleDAO();
	     ReservationVO rVo = null;
	      ArrayList<String> title;
	      ArrayList<ReservationVO> list2;

	      title = sDao.getColumnName();
	      int columnCount = title.size();

	      list2 = sDao.getSelectInfo(sDate, eDate);
	      int rowCount = list2.size();

	      for (int index = 0; index < rowCount; index++) {
	         rVo = list2.get(index);
	         data2.add(rVo);
	      }

	   }
}
