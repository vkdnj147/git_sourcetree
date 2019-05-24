package controller;

import java.net.URL;
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
	ComboBox<String> cbx_searchList;// 검색 분류
	@FXML
	TextField txtSearchWord;// 검색
	@FXML
	Button btnTotal;// 전체 버튼
	@FXML
	Label lblCount;// 카운트
	@FXML
	TableView<SaleVO> TotalSaleTableView = new TableView<>();// 매출 테이블 인스턴스화
	@FXML
	TableView<SaleVO> TodaySaleTableView = new TableView<>();// today매출 테이블 인스턴스화

	static ObservableList<SaleVO> TotalSaleDataList = FXCollections.observableArrayList();// 매출 정보를 저장

	SaleVO sVo = new SaleVO();
	CustomerVO cVo = new CustomerVO();
	ThemeVO tVo = new ThemeVO();
	ReservationVO rVo = new ReservationVO();

	ObservableList<SaleVO> data = FXCollections.observableArrayList();// 매출정보를 임시 저장
	// 등록완료된 정보들이 저장될 배열공간
	ObservableList<SaleVO> data1 = null;

	private static ObservableList<SaleVO> SaleTotalDataList = FXCollections.observableArrayList(); // 매출액

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {

			// 가져와야하는 매출 관리 컬럼 이름 설정하기
			// 새로만든 VO에서 가져온다 테마 이름,테마 가격,인원
			TableColumn colSaleC_name = new TableColumn("이 름");
			colSaleC_name.setPrefWidth(50); // 크기 설정
			colSaleC_name.setStyle("-fx-allignment : CENTER");
			colSaleC_name.setCellValueFactory(new PropertyValueFactory<>("c_name"));

			TableColumn colSaleC_phone = new TableColumn("핸드폰");
			colSaleC_phone.setPrefWidth(150);
			colSaleC_phone.setStyle("-fx-allignment : CENTER");
			colSaleC_phone.setCellValueFactory(new PropertyValueFactory<>("c_phone"));

			TableColumn colSaleC_team = new TableColumn("인 원");
			colSaleC_team.setPrefWidth(150);
			colSaleC_team.setStyle("-fx-allignment: CENTER");
			colSaleC_team.setCellValueFactory(new PropertyValueFactory<>("c_team"));

			TableColumn colSaleT_name = new TableColumn("테 마");
			colSaleT_name.setPrefWidth(50); // 크기 설정
			colSaleT_name.setStyle("-fx-allignment : CENTER");
			colSaleT_name.setCellValueFactory(new PropertyValueFactory<>("t_name"));

			TableColumn colSaleT_price = new TableColumn("가 격");
			colSaleT_price.setPrefWidth(50); // 크기 설정
			colSaleT_price.setStyle("-fx-allignment : CENTER");
			colSaleT_price.setCellValueFactory(new PropertyValueFactory<>("t_price"));

			TableColumn colSaleR_reserveddate = new TableColumn("이 용 일");
			colSaleR_reserveddate.setPrefWidth(50); // 크기 설정
			colSaleR_reserveddate.setStyle("-fx-allignment : CENTER");
			colSaleR_reserveddate.setCellValueFactory(new PropertyValueFactory<>("r_reserveddate"));

			TableColumn colSaleR_reservedtime = new TableColumn("이 용 시 간");
			colSaleR_reservedtime.setPrefWidth(50); // 크기 설정
			colSaleR_reservedtime.setStyle("-fx-allignment : CENTER");
			colSaleR_reservedtime.setCellValueFactory(new PropertyValueFactory<>("r_reservedtime"));

			// 전체 관련된 값
			TotalSaleTableView.setItems(SaleTotalDataList);

			// 컬럼값을 집어 넣고
			TotalSaleTableView.getColumns().addAll(colSaleC_name, colSaleC_phone, colSaleC_team, colSaleT_name,
					colSaleT_price, colSaleR_reserveddate, colSaleR_reservedtime);

			TotalSaleList();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void TotalSaleList() throws Exception {
		SaleDAO sDao = new SaleDAO();
		// 값을 다 받아와야만 불러 올 수 있게 해야 한다
		SaleVO rVo = null;

		ArrayList<SaleVO> list;

		list = sDao.getSaleTotalList();

		int rowCount = list.size();

		for (int index = 0; index < rowCount; index++) {
			rVo = list.get(index);
			TotalSaleDataList.add(rVo);
		}

	}
}