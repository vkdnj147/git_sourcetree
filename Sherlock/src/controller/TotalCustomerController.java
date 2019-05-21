package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CustomerVO;

public class TotalCustomerController implements Initializable {

	@FXML
	ComboBox<String> cbx_searchList;
	@FXML
	TextField txtSearchWord;
	@FXML
	Button btnSearch;
	@FXML
	Button btnTotal;
	@FXML
	Label lblCount;
	@FXML
	TableView<CustomerVO> TotalCustomerTableView = new TableView<>();
	
	ObservableList<CustomerVO> TotalCustomerDataList = FXCollections.observableArrayList();
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {

			cbx_searchList.setItems(FXCollections.observableArrayList("핸드폰", "이름"));

			// 고객 테이블 뷰 컬럼 이름 설정
			TableColumn colCustomerNO = new TableColumn("NO");

			colCustomerNO.setPrefWidth(50); // 크기 설정
			colCustomerNO.setStyle("-fx-allignment : CENTER");
			colCustomerNO.setCellValueFactory(new PropertyValueFactory<>("c_no"));

			TableColumn colCustomerName = new TableColumn("이름");
			colCustomerName.setPrefWidth(150);
			colCustomerName.setStyle("-fx-allignment : CENTER");
			colCustomerName.setCellValueFactory(new PropertyValueFactory<>("c_name"));

			TableColumn colCustomerPhone = new TableColumn("핸드폰");
			colCustomerPhone.setPrefWidth(150);
			colCustomerPhone.setStyle("-fx-allignment: CENTER");
			colCustomerPhone.setCellValueFactory(new PropertyValueFactory<>("c_name"));
			
			TableColumn colCustomerTheme = new TableColumn("테마");
			colCustomerTheme.setPrefWidth(150);
			colCustomerTheme.setStyle("-fx-allignment: CENTER");
			colCustomerTheme.setCellValueFactory(new PropertyValueFactory<>("t_theme"));
			
			TableColumn colCustomerEscape = new TableColumn("탈출 여부");
			colCustomerEscape.setPrefWidth(150);
			colCustomerEscape.setStyle("-fx-allignment: CENTER");
			colCustomerEscape.setCellValueFactory(new PropertyValueFactory<>("r_escape"));
			
			TotalCustomerTableView.setItems(TotalCustomerDataList);
			TotalCustomerTableView.getColumns().addAll(colCustomerNO, colCustomerName, colCustomerPhone, colCustomerTheme, colCustomerEscape);

			//전체 이용자 목록
			TotalCustomerList();

			btnSearch.setOnAction(event -> handlerBtnSearchAction(event));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 검색 버튼  이벤트
	public void handlerBtnSearchAction(ActionEvent event) {

		String search = "";
		search = cbx_searchList.getSelectionModel().getSelectedItem();

		CustomerVO cVo = new CustomerVO();
		CustomerDAO cDao = new CustomerDAO();

		String searchName = "";
		boolean searchResult = false;

		searchName = txtSearchWord.getText().trim();

		try {
			if (searchName.equals("") || search.equals("")) {
				try {
					searchResult = true;
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("수강 정보 검색");
					alert.setHeaderText("수강 검색 정보를 입력하시오.");
					alert.setContentText("다음에는 주의하세요!");
					alert.showAndWait();
					TotalCustomerList();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				ArrayList<String> title;
				ArrayList<CustomerVO> list = null;
				title = cDao.getTotalCustomerColumnName();
				int columnCount = title.size();

				if (search.equals("이름")) {
					list = cDao.getTotalCustomerNameSearchList(searchName);
					if (list.size() == 0) {
						txtSearchWord.clear();
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("고객 정보 검색");
						alert.setHeaderText(searchName + " 고객이름이 리스트에 없습니다.");
						alert.setContentText("다시 검색하세요.");
						alert.showAndWait();
						list = cDao.getTotalCustomerList();
					}
				}
				
				if (search.equals("핸드폰")) {
					list = cDao.getTotalCustomerNameSearchList(searchName);
					if (list.size() == 0) {
						txtSearchWord.clear();
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("고객 정보 검색");
						alert.setHeaderText(searchName + " 핸드폰 번호가 리스트에 없습니다.");
						alert.setContentText("다시 검색하세요.");
						alert.showAndWait();
						list = cDao.getTotalCustomerList();
					}
				}
				txtSearchWord.clear();
				TotalCustomerDataList.removeAll(TotalCustomerDataList);
				int rowCount = list.size();
				lblCount.setText("검색 : " + rowCount + " 명");
				for (int index = 0; index < rowCount; index++) {
					cVo = list.get(index);
					TotalCustomerDataList.add(cVo);
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
		lblCount.setText("전체 이용자 : " + rowCount + " 명");

		for (int index = 0; index < rowCount; index++) {

			cVo = list.get(index);
			TotalCustomerDataList.add(cVo);
		}
	}
}

