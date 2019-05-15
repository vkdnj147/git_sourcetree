package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.JoinVO;

public class JoinController implements Initializable {

	@FXML
	private TextField txtem_Id;
	@FXML
	private PasswordField txtem_Passwd;
	@FXML
	private PasswordField txtPasswdRepeat;
	@FXML
	private TextField txtem_Name;
	@FXML
	private TextField txtem_Phone;
	@FXML
	private TextField txtem_Bank;
	@FXML
	private TextField txtem_Account;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnJoin;
	@FXML
	private Button btnIdCheck;

	@FXML
	private RadioButton rbEmployee;
	@FXML
	private RadioButton rbTimer;

	JoinVO joinVO = new JoinVO();

	// 재정의
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnJoin.setDisable(true);
		txtem_Passwd.setEditable(false);
		txtPasswdRepeat.setEditable(false);

		btnIdCheck.setOnAction(event -> handlerbtnIdCheckActoion(event)); // 아이디 중복 검사 핸들러
		btnJoin.setOnAction(event -> handlerBtnJoinActoion(event)); // 관리자 등록
		btnCancel.setOnAction(event -> handlerBtnCancelActoion(event)); // 등록창 닫기
		rbEmployee.setOnAction(event -> handlerRbEmployeeAction(event));
		rbTimer.setOnAction(event -> handlerRbTimerAction(event));
	}

	// 핸들러 만들기
	// 아이디 중복 검사 이벤트 작동 핸들러
	// 아이디 중복 검사
	public void handlerbtnIdCheckActoion(ActionEvent event) {

		btnJoin.setDisable(false);
		btnIdCheck.setDisable(true);

		JoinDAO jDao = null;
		String searchId = "";
		boolean searchResult = true;

		try {

			searchId = txtem_Id.getText().trim();
			jDao = new JoinDAO();
			searchResult = (boolean) jDao.getIdCheck(searchId);

			if (!searchResult && !searchId.equals("")) {

				txtem_Id.setDisable(true);

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle(" 아이디 중복 검사 ");
				alert.setHeaderText( searchId + "를 사용할 수 있습니다.");
				alert.setContentText("비밀번호를 입력하세요.");
				alert.showAndWait();

				btnJoin.setDisable(false);
				btnIdCheck.setDisable(true);
				txtem_Passwd.setEditable(true);
				txtPasswdRepeat.setEditable(true);
				txtem_Passwd.requestFocus();

			} else if (searchId.equals("")) {

				btnJoin.setDisable(true);
				btnIdCheck.setDisable(false);

				Alert alert = new Alert(AlertType.WARNING);

				alert.setTitle("아이디 중복 검색");
				alert.setHeaderText("아이디를 입력하시오.");
				alert.setContentText("등록할 아이디를 입력하세요!");
				alert.showAndWait();

			} else {

				btnJoin.setDisable(true);
				btnIdCheck.setDisable(false);
				txtem_Id.clear();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("아이디 중복 검사");
				alert.setHeaderText(searchId + "를 사용할 수 없습니다.");
				alert.setContentText("아이디를 다른것으로 입력하세요.");
				alert.showAndWait();

				txtem_Id.requestFocus();
			}
		} catch (Exception e) {

			Alert alert = new Alert(AlertType.ERROR);

			alert.setTitle("아이디 중복 검사 오류");
			alert.setHeaderText("아이디 중복 검사에 오류가 발생하였습니다.");
			alert.setContentText("다시 하세요.");
			alert.showAndWait();
		}
	}
	//직원 라디오 버튼
	public void handlerRbEmployeeAction(ActionEvent event) {
		
	}

	//파트 티어머 라디오 버튼
	public void handlerRbTimerAction(ActionEvent event) {
		
	}

	// 등록창 닫기
	public void handlerBtnCancelActoion(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));

			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("관리자 로그인");
			mainMtage.setScene(scane);

			Stage oldStage = (Stage) btnJoin.getScene().getWindow();

			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {

			System.err.println("오류" + e);

		}
	}

	// 관리자 등록
	public void handlerBtnJoinActoion(ActionEvent event) {

		JoinVO jvo = null;
		JoinDAO jdao = null;

		boolean joinSucess = false;
		// 패스워드 확인

		if (txtem_Passwd.getText().trim().equals(txtPasswdRepeat.getText().trim())
				&& !txtem_Name.getText().trim().equals("")) {
			jvo = new JoinVO(txtem_Id.getText().trim(), txtem_Passwd.getText().trim(), txtem_Name.getText().trim(),
					txtem_Phone.getText().trim(), txtem_Bank.getText().trim(), txtem_Account.getText().trim());
			jdao = new JoinDAO();

			try {
				joinSucess = jdao.getMangerRegiste(jvo);
				if (joinSucess) {
					handlerBtnCancelActoion(event);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else {

			txtem_Passwd.clear();
			txtPasswdRepeat.clear();

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("비밀번호, 이름 확인");
			alert.setHeaderText("비밀번호, 이름 확인 검사에 오류가 발생하였습니다.");
			alert.setContentText("비밀번호와 이름을 다시 입력하세요.");
			alert.showAndWait();
		}
	}
}
