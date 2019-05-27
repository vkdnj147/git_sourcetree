package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.CustomerVO;
import model.EmployeeVO;

public class TotalEmployeeTabController implements Initializable {
	@FXML
	private ComboBox<EmployeeVO> cbx_Employee;// 직원정보를 저장할 매개변수
	@FXML
	private TextField txtem_no;// 사원 번호
	@FXML
	private TextField txtem_name;// 사원 이름
	@FXML
	private TextField txtem_id;// 사원아이디
	@FXML
	private TextField txtem_passwd;// 사원 비밀번호
	@FXML
	private TextField txtem_phone;// 사원 핸드폰 번호
	@FXML
	private TextField txtem_address;// 주소
	@FXML
	private TextField txtem_bank;// 은행명
	@FXML
	private TextField txtem_account;// 계좌번호
	@FXML
	private TextField txtem_entry;// 입사일
	@FXML
	private TextField txtem_leaveday;// 퇴사일
	// 콤보 박스 선언
	@FXML
	private ComboBox<String> cbx_rank;// 직급
	@FXML
	private ComboBox<String> cbx_whether;// 직급

	@FXML
	private Button btnIdCheck;// 아이디 체크
	@FXML
	private Button btnEmployeeInsert;// 직원등록
	@FXML
	private Button btnEmployeeUpdate;// 직원 정보수정
	@FXML
	private Button btnEmployeeInit;// 직원 초기화
	@FXML
	private Button btnEmployeeTotalList;// 직원 전체 목롱
	@FXML
	private TableView<EmployeeVO> totalEmployeeTableView = new TableView<>();// 테이블뷰 필드 선언

	@FXML
	private TextField txtSearchWord;// 검색단어
	@FXML
	private Button btnSearch;// 검색 버튼
	@FXML
	private Button btnTotal;// 전체 버튼
	@FXML
	private Label lblCount;// 총 직원수 세는 카운트

	private ObservableList<EmployeeVO> employeeDataList = FXCollections.observableArrayList();// 직원 정보를 저장

	ObservableList<EmployeeVO> selectEmployee = null;// 직원등록 테이블에서 선택한 정보 저장

	String EmployeeNumber = "";

	ComboBox<EmployeeVO> EmployeeTableView;

	private Object eDAO;

	private EmployeeVO selectedEmployee;

	Object selectedEmployeeIndex;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			// 직원등록 초기화
			btnEmployeeInsert.setDisable(false);// 직원등록 버튼
			btnEmployeeUpdate.setDisable(false);// 직원 수정 버튼
			btnEmployeeInit.setDisable(true);// 직원 초기화 버튼
			totalEmployeeTableView.setEditable(false);// 등록하기 전 테이블뷰 비활성화
			txtem_passwd.setEditable(false);// 아이디 중복체크하기전 비번 비활성화

			// 사원번호 수정금지
			txtem_no.setEditable(true);

			// 콤보박스에 값을 지정하는것
			cbx_rank.setItems(FXCollections.observableArrayList("직원", "파트"));
			cbx_whether.setItems(FXCollections.observableArrayList("재직중", "퇴사"));

			// 직원 테이블 뷰 컬럼이름 설정
			TableColumn colEmployeeNo = new TableColumn("사원번호");
			colEmployeeNo.setPrefWidth(70);
			colEmployeeNo.setCellValueFactory(new PropertyValueFactory<>("em_no"));

			TableColumn colEmployeeRank = new TableColumn("직급");
			colEmployeeRank.setPrefWidth(70);
			colEmployeeRank.setCellValueFactory(new PropertyValueFactory<>("em_rank"));

			TableColumn colEmployeeName = new TableColumn("이름");
			colEmployeeName.setPrefWidth(80);
			colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("em_name"));

			TableColumn colEmployeeId = new TableColumn("아이디");
			colEmployeeId.setPrefWidth(80);
			colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("em_id"));

			TableColumn colEmployeePasswd = new TableColumn("비밀번호");
			colEmployeePasswd.setPrefWidth(80);
			colEmployeePasswd.setCellValueFactory(new PropertyValueFactory<>("em_passwd"));

			TableColumn colEmployeePhone = new TableColumn("핸드폰");
			colEmployeePhone.setPrefWidth(80);
			colEmployeePhone.setCellValueFactory(new PropertyValueFactory<>("em_phone"));

			TableColumn colEmployeeAddress = new TableColumn("주소");
			colEmployeeAddress.setPrefWidth(150);
			colEmployeeAddress.setCellValueFactory(new PropertyValueFactory<>("em_address"));

			TableColumn colEmployeeBank = new TableColumn("은행");
			colEmployeeBank.setPrefWidth(80);
			colEmployeeBank.setCellValueFactory(new PropertyValueFactory<>("em_bank"));

			TableColumn colEmployeeAccount = new TableColumn("계좌번호");
			colEmployeeAccount.setPrefWidth(150);
			colEmployeeAccount.setCellValueFactory(new PropertyValueFactory<>("em_account"));

			TableColumn colEmployeeEntry = new TableColumn("입사일");
			colEmployeeEntry.setPrefWidth(80);
			colEmployeeEntry.setCellValueFactory(new PropertyValueFactory<>("em_entry"));

			TableColumn colEmployeeLeaveday = new TableColumn("퇴사일");
			colEmployeeLeaveday.setPrefWidth(80);
			colEmployeeLeaveday.setCellValueFactory(new PropertyValueFactory<>("em_leaveday"));

			TableColumn colEmployeeWhether = new TableColumn("재직여부");
			colEmployeeWhether.setPrefWidth(80);
			colEmployeeWhether.setCellValueFactory(new PropertyValueFactory<>("em_whether"));

			totalEmployeeTableView.setItems(employeeDataList);
			totalEmployeeTableView.getColumns().addAll(colEmployeeNo, colEmployeeRank, colEmployeeName, colEmployeeId,
					colEmployeePasswd, colEmployeePhone, colEmployeeAddress, colEmployeeBank, colEmployeeAccount,
					colEmployeeEntry, colEmployeeLeaveday, colEmployeeWhether);

			// 직원 전체 목록
			employeeTotalList();

			// 핸들러 이벤트 등록
			// cbx_searchList.setItems(FXCollections.observableArrayList("이름"));
			btnEmployeeInsert.setOnAction(event -> handlerbtnEmployeeInsertAction(event)); // 직원 등록버튼
			btnEmployeeUpdate.setOnAction(event -> handlerbtnEmployeeUpdateAction(event)); // 직원 수정 버튼
			btnEmployeeInit.setOnAction(event -> handlerbtnEmployeeInitAction(event)); // 직원 초기화
			btnIdCheck.setOnAction(event -> handlerbtnIdCheckAction(event)); // 아이디체크
			totalEmployeeTableView.setOnMouseClicked(event -> handlerEmployeeTableViewAction(event));// 테이블뷰 클릭 이벤트
			btnSearch.setOnAction(event -> handlerBtnSearchAction(event));// 검색 버튼
			btnTotal.setOnAction(event -> handlerBtnTotalAction(event));// 전체 버튼
			// 전체 목록 이벤트 아직 없음 추가 될 예정

			// 엔터키 이벤트
			// 키 이벤트 등록
			txtem_no.setOnKeyPressed(event -> handlerTxtem_noKeyPressed(event));
			txtem_name.setOnKeyPressed(event -> handlerTxtem_nameKeyPressed(event));
			txtem_id.setOnKeyPressed(event -> handlerTxtem_idKeyPressed(event));
			txtem_passwd.setOnKeyPressed(event -> handlerTxtem_passwdKeyPressed(event));
			btnIdCheck.setOnKeyPressed(event -> handlerBtnIdCheckKeyPressed(event));
			txtem_phone.setOnKeyPressed(event -> handlerTxtem_phoneKeyPressed(event));
			txtem_address.setOnKeyPressed(event -> handlerTxtem_addressKeyPressed(event));
			txtem_bank.setOnKeyPressed(event -> handlerTxtem_bankKeyPressed(event));
			txtem_account.setOnKeyPressed(event -> handlerTxtem_accountKeyPressed(event));
			txtem_entry.setOnKeyPressed(event -> handlerTxtem_entryKeyPressed(event));
			txtem_leaveday.setOnKeyPressed(event -> handlerTxtem_leavedayKeyPressed(event));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 전체 버튼 이벤트
	public void handlerBtnTotalAction(ActionEvent event) {
		try {
			employeeDataList.removeAll(employeeDataList);
			employeeTotalList();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 직원 전체 목록
	public void employeeTotalList() throws Exception {// 앞에는 소문자~
		try {
			employeeDataList.removeAll(employeeDataList);
			// 객체 생성
			EmployeeDAO eDao = new EmployeeDAO();

			EmployeeVO eVo = null;
			ArrayList<String> title;
			ArrayList<EmployeeVO> searchList;

			title = eDao.getEmployeeColumnName();
			int columnCount = title.size();

			searchList = eDao.getEmployeeTotalList();
			// searchList = eDao.getEmployeeNameSearchList(em_name);

			int rowCount = searchList.size();
			// System.out.println(rowCount);
			lblCount.setText("총원 : " + rowCount + " 명");

			for (int index = 0; index < rowCount; index++) {

				eVo = searchList.get(index);
				employeeDataList.add(eVo);
			}
		} catch (Exception e) {

		}
	}

	// 검색버튼
	public void handlerBtnSearchAction(ActionEvent event) {
		ArrayList<EmployeeVO> searchList = new ArrayList<EmployeeVO>();

		EmployeeVO eVo = null;
		EmployeeDAO eDao = null;

		String searchName = "";
		boolean searchResult = false;

		try {
			searchName = txtSearchWord.getText().trim();
			eDao = new EmployeeDAO();
			searchList = eDao.getEmployeeNameSearchList(searchName);

			int rowCount = searchList.size();
			lblCount.setText("검색 : " + rowCount + " 명");

			for (int index = 0; index < rowCount; index++) {

				eVo = searchList.get(index);
				employeeDataList.add(eVo);
			}

			if (searchName.equals("")) {
				searchResult = true;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("직원 정보 입력");
				alert.setHeaderText("직원 이름을 입력하시오.");
				alert.setContentText("다음에는 주의하세요!");
				alert.showAndWait();
			}

			if (searchList != null) {
				// int rowCount = searchList.size();
				txtSearchWord.clear();
				employeeDataList.removeAll(employeeDataList);
				for (int index = 0; index < rowCount; index++) {
					eVo = searchList.get(index);
					employeeDataList.add(eVo);
					searchResult = true;
				}
			}
			if (!searchResult) {
				txtSearchWord.clear();
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("직원 정보 검색");
				alert.setHeaderText(searchName + " 직원이 리스트에 없습니다.");
				alert.setContentText("다시 검색하세요!");
				alert.showAndWait();
				txtSearchWord.clear();
				employeeDataList.removeAll(employeeDataList);
				// int rowCount = searchList.size();
				lblCount.setText("검색 : " + rowCount + " 명");
				for (int index = 0; index < rowCount; index++) {
					eVo = searchList.get(index);
					employeeDataList.add(eVo);
				}

			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("직원 정보 검색 오류");
			alert.setHeaderText("직원 정보 검색에 오류가 발생했습니다.");
			alert.setContentText("다시 하세요!");
			alert.showAndWait();
		}
	}

	// 아이디 중복체크 마우스로 눌렀을경우
	public void handlerbtnIdCheckAction(ActionEvent event) {
		btnEmployeeInsert.setDisable(false);
		btnIdCheck.setDisable(true);
		EmployeeDAO eDao = null;
		String searchId = "";
		boolean searchResult = true;

		try {
			searchId = txtem_id.getText().trim();
			eDao = new EmployeeDAO();
			searchResult = (boolean) eDao.getEmployeeIdOverlap(searchId);

			if (!searchResult && !searchId.equals("")) {
				txtem_id.setDisable(true);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("아이디 중복검사");
				alert.setHeaderText(searchId + "를 사용할수 있습니다 !");
				alert.setContentText("패스워드를 입력하세요");
				alert.showAndWait();

				txtem_passwd.setEditable(true);// 수정가능

				btnEmployeeInsert.setDisable(false);
				btnIdCheck.setDisable(false);// 아이디 체크 활성화
			} else if (searchId.equals("")) {
				btnEmployeeInsert.setDisable(true);
				btnIdCheck.setDisable(false);// 아이디 체크 활성화
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("아이디 중복검사");
				alert.setHeaderText("아이디를 입력하시오!");
				alert.setContentText("등록할 아이디를 입력하세요");
				alert.showAndWait();
			} else {
				btnEmployeeInsert.setDisable(true);
				btnIdCheck.setDisable(false);// 아이디 체크 활성화
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("아이디 중복검사");
				alert.setHeaderText(searchId + "를 사용할 수 없습니다!");
				alert.setContentText("다른 아이디를 입력해주세요");
				alert.showAndWait();
				txtem_id.requestFocus();
			}
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("아이디 중복 검사 오류");
			alert.setHeaderText("아이디 중복 검사에 오류가 발생하였습니다!");
			alert.setContentText("다시 시도해주세요");
			alert.showAndWait();
		}
	}

	// 직원 정보 수정
	public void handlerbtnEmployeeUpdateAction(ActionEvent event) {
		try {
			boolean sucess;

			EmployeeDAO eDao = new EmployeeDAO();
			// 등록한 직원의 정부 수정
			// 이름,번호,주소,은행명,계좌번호

			sucess = eDao.getEmployeeUpdate(txtem_no.getText().trim(), txtem_name.getText().trim(),
					txtem_id.getText().trim(), txtem_passwd.getText().trim(), txtem_phone.getText().trim(),
					txtem_address.getText().trim(), txtem_bank.getText().trim(), txtem_account.getText().trim(),
					txtem_entry.getText().trim(), txtem_leaveday.getText().trim(),
					cbx_whether.getSelectionModel().getSelectedItem());

			if (sucess) {
				employeeDataList.removeAll(employeeDataList);
				employeeTotalList();
				// 입력된 정보 초기화
				txtem_no.clear();
				txtem_name.clear();
				txtem_id.clear();
				txtem_passwd.clear();
				txtem_address.clear();
				txtem_phone.clear();
				txtem_bank.clear();
				txtem_account.clear();
				txtem_entry.clear();
				txtem_leaveday.clear();
				// 번호 이름 아이디는 비활성화
				txtem_no.setDisable(true);
				txtem_name.setDisable(true);
				txtem_id.setDisable(true);

				btnIdCheck.setDisable(true);// 중복체크 비활성화
				cbx_rank.setDisable(false);// 직급 활성화
				cbx_whether.setDisable(false);// 재직여부 활성화

				btnEmployeeUpdate.setDisable(true);
				btnEmployeeInit.setDisable(true);
				btnEmployeeInsert.setDisable(true);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void handlerEmployeeTableViewAction(MouseEvent event) {
		if (event.getClickCount() == 2) {
			try {
				selectedEmployee = totalEmployeeTableView.getSelectionModel().getSelectedItem();
				// 주석 해제 하고 방법을 찾아야 한다

				selectedEmployeeIndex = Integer.parseInt(selectedEmployee.getEm_no());

				String selectedem_no = selectedEmployee.getEm_no();
				String selectedem_rank = selectedEmployee.getEm_rank();
				String selectedem_name = selectedEmployee.getEm_name();
				String selectedem_id = selectedEmployee.getEm_id();
				String selectedem_passwd = selectedEmployee.getEm_passwd();
				String selectedem_phone = selectedEmployee.getEm_phone();
				String selectedem_address = selectedEmployee.getEm_address();
				String selectedem_bank = selectedEmployee.getEm_bank();
				String selectedem_account = selectedEmployee.getEm_account();
				String selectedem_entry = selectedEmployee.getEm_entry();
				String selectedem_leaveday = selectedEmployee.getEm_leaveday();
				String selectedem_whether = selectedEmployee.getEm_whether();

				// 선택한 값만 가져오는 것
				txtem_no.setText(selectedem_no);
				txtem_name.setText(selectedem_name);
				txtem_id.setText(selectedem_id);
				txtem_passwd.setText(selectedem_passwd);
				txtem_phone.setText(selectedem_phone);
				txtem_address.setText(selectedem_address);
				txtem_bank.setText(selectedem_bank);
				txtem_account.setText(selectedem_account);
				txtem_entry.setText(selectedem_entry);

				cbx_whether.setValue(selectedem_whether);

				if (selectedem_leaveday.equals(null)) {
					txtem_leaveday.setText(selectedem_leaveday);
				}

				txtem_no.setEditable(false);
				txtem_name.setEditable(false);
				txtem_id.setEditable(false);

				btnIdCheck.setDisable(true);
				cbx_rank.setDisable(true);
				cbx_whether.setDisable(false);// 재직 여부

				btnEmployeeUpdate.setDisable(false);
				btnEmployeeInit.setDisable(false);
				btnEmployeeInsert.setDisable(true);

			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}

	// 직원등록 이벤트 핸들러
	public void handlerbtnEmployeeInsertAction(ActionEvent event) {
		try {
			// EmployeeDataList.remove(EmployeeDataList);
			EmployeeVO evo = null;
			EmployeeDAO edao = null;

			String rank = cbx_rank.getSelectionModel().getSelectedItem();// 콤보박스에서 선택한 아이템을 가져온다
			String whether = cbx_whether.getSelectionModel().getSelectedItem();

			// 입력된 모든 직원의 정보를 가져온다
			evo = new EmployeeVO(txtem_no.getText().trim(), rank, txtem_name.getText().trim(),
					txtem_id.getText().trim(), txtem_passwd.getText().trim(), txtem_phone.getText().trim(),
					txtem_address.getText().trim(), txtem_bank.getText().trim(), txtem_account.getText().trim(),
					txtem_entry.getText().trim(), txtem_leaveday.getText().trim(), whether);

			edao = new EmployeeDAO();
			edao.getEmployeeInsert(evo);
			// 날짜는 추가 수정이 들어가야함

			if (edao != null) {
				employeeTotalList();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("근무자 입력");
				alert.setHeaderText(txtem_name.getText() + "근무자가 성공적으로 추가되었습니다 !");
				alert.setContentText("근무자 정보 등록 완료 !");
				alert.showAndWait();

				txtem_id.setDisable(false);// 아이디 활성화 시켜주는거
				txtem_no.setDisable(false);// 사원번호 직접입력을 위해 활성화

				txtem_no.clear();
				txtem_name.clear();
				txtem_id.clear();
				txtem_passwd.clear();
				txtem_phone.clear();
				txtem_address.clear();
				txtem_bank.clear();
				txtem_account.clear();

				btnEmployeeInsert.setDisable(false);
				btnEmployeeUpdate.setDisable(false);
				btnEmployeeInit.setDisable(false);
				txtem_leaveday.setDisable(true);
			}

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("근무자 정보 입력");
			alert.setHeaderText("근무자 정보를 정확하게 입력하세요.");
			alert.setContentText("다시 시도하세요");
			alert.showAndWait();
		}
	}

	// 직원 정보 초기화
	public void handlerbtnEmployeeInitAction(ActionEvent event) {
		try {
			employeeDataList.removeAll(employeeDataList);
			employeeTotalList();

			// 안에 있는 텍스트를 모두 비우라는 메소드
			txtem_no.clear();
			txtem_name.clear();
			txtem_id.clear();
			txtem_passwd.clear();
			txtem_phone.clear();
			txtem_address.clear();
			txtem_bank.clear();
			txtem_account.clear();
			txtem_entry.clear();
			txtem_leaveday.clear();

			txtem_no.setEditable(true);
			txtem_name.setEditable(true);
			txtem_id.setEditable(true);

			btnIdCheck.setDisable(false);
			cbx_rank.setDisable(false);
			cbx_whether.setDisable(false);

			btnEmployeeUpdate.setDisable(true);
			btnEmployeeInsert.setDisable(true);
			btnEmployeeInit.setDisable(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 키 이벤트(점수입력창에서 엔터 누르면 그 다음 텍스트필드로 이동)
	public void handlerTxtem_noKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtem_name.requestFocus();
		}
	}

	public void handlerTxtem_nameKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtem_id.requestFocus();
		}
	}

	public void handlerTxtem_idKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			btnIdCheck.requestFocus();
		}
	}

	// 아이디 중복검사 엔터로 적용
	public void handlerBtnIdCheckKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtem_passwd.requestFocus();
			// 아이디 중복 체크 핸들러
			// public void handlerbtnIdCheckAction(ActionEvent event) {
			btnEmployeeInsert.setDisable(false);
			btnIdCheck.setDisable(true);
			EmployeeDAO eDao = null;
			String searchId = "";
			boolean searchResult = true;

			try {
				searchId = txtem_id.getText().trim();
				eDao = new EmployeeDAO();
				searchResult = (boolean) eDao.getEmployeeIdOverlap(searchId);

				if (!searchResult && !searchId.equals("")) {
					txtem_id.setDisable(true);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("아이디 중복검사");
					alert.setHeaderText(searchId + "를 사용할수 있습니다 !");
					alert.setContentText("패스워드를 입력하세요");
					alert.showAndWait();

					txtem_passwd.setEditable(true);// 수정가능

					btnEmployeeInsert.setDisable(false);
					btnIdCheck.setDisable(false);// 아이디 체크 활성화
				} else if (searchId.equals("")) {
					btnEmployeeInsert.setDisable(true);
					btnIdCheck.setDisable(false);// 아이디 체크 활성화
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("아이디 중복검사");
					alert.setHeaderText("아이디를 입력하시오!");
					alert.setContentText("등록할 아이디를 입력하세요");
					alert.showAndWait();
				} else {
					btnEmployeeInsert.setDisable(true);
					btnIdCheck.setDisable(false);// 아이디 체크 활성화
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("아이디 중복검사");
					alert.setHeaderText(searchId + "를 사용할 수 없습니다!");
					alert.setContentText("다른 아이디를 입력해주세요");
					alert.showAndWait();
					txtem_id.requestFocus();
				}
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("아이디 중복 검사 오류");
				alert.setHeaderText("아이디 중복 검사에 오류가 발생하였습니다!");
				alert.setContentText("다시 시도해주세요");
				alert.showAndWait();
			}
		}

	}

	// 엔터키 이벤트

	public void handlerTxtem_passwdKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtem_phone.requestFocus();
		}
	}

	public void handlerTxtem_phoneKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtem_address.requestFocus();
		}
	}

	public void handlerTxtem_addressKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtem_bank.requestFocus();
		}
	}

	public void handlerTxtem_bankKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtem_account.requestFocus();
		}
	}

	public void handlerTxtem_accountKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtem_entry.requestFocus();
		}
	}

	public void handlerTxtem_entryKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtem_leaveday.requestFocus();
		}
	}

	public void handlerTxtem_leavedayKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			btnEmployeeInsert.requestFocus();
		}
	}

}
