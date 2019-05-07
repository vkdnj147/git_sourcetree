package control;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.JoinVO;

public class JoinController implements Initializable {

	@FXML
	private TextField txtId;
	@FXML
	private PasswordField txtPassWord;
	@FXML
	private PasswordField txtPasswordRepeat;
	@FXML
	private TextField txtName;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnJoin;
	@FXML
	private Button btnOverlap;

	JoinVO joinVO = new JoinVO();

	// 재정의
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnJoin.setDisable(true);
		txtPassWord.setEditable(false);
		txtPasswordRepeat.setEditable(false);

		btnOverlap.setOnAction(event -> handlerBtnOverlapActoion(event)); // 아이디 중복 검사 핸들러
		btnJoin.setOnAction(event -> handlerBtnJoinActoion(event)); // 관리자 등록
		btnCancel.setOnAction(event -> handlerBtnCancelActoion(event)); // 등록창 닫기

	}

	// 핸들러 만들기
	// 아이디 중복 검사 이벤트 작동 핸들러
	// 아이디 중복 검사
	public void handlerBtnOverlapActoion(ActionEvent event) {
		
		btnJoin.setDisable(false);
		btnOverlap.setDisable(true);
		
		JoinDAO jDao = null;
		String searchId = "";
		boolean searchResult = true;
		
		try {
		
			searchId = txtId.getText().trim();
			jDao = new JoinDAO();
			searchResult = (boolean) jDao.getIdOverlap(searchId);
			
			if (!searchResult && !searchId.equals("")) {
			
				txtId.setDisable(true);
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("아이디 중복 검사");
				alert.setHeaderText(searchId + "를 사용할 수 있습니다.");
				alert.setContentText("패스어드를 입력하세요.");
				alert.showAndWait();
				
				btnJoin.setDisable(false);
				btnOverlap.setDisable(true);
				txtPassWord.setEditable(true);
				txtPasswordRepeat.setEditable(true);
				txtPassWord.requestFocus();
			
			} else if (searchId.equals("")) {
				
				btnJoin.setDisable(true);
				btnOverlap.setDisable(false);
				
				Alert alert = new Alert(AlertType.WARNING);
				
				alert.setTitle("아이디 중복 검색");
				alert.setHeaderText("아이디를 입력하시오.");
				alert.setContentText("등록할 아이디를 입력하세요!");
				alert.showAndWait();
			
			} else {
				
				btnJoin.setDisable(true);
				btnOverlap.setDisable(false);
				txtId.clear();
			
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("아이디 중복 검사");
				alert.setHeaderText(searchId + "를 사용할 수 없습니다.");
				alert.setContentText("아이디를 다른것으로 입력하세요.");
				alert.showAndWait();
				
				txtId.requestFocus();
			}
		} catch (Exception e) {
			
			Alert alert = new Alert(AlertType.ERROR);
			
			alert.setTitle("아이디 중복 검사 오류");
			alert.setHeaderText("아이디 중복 검사에 오류가 발생하였습니다.");
			alert.setContentText("다시 하세요.");
			alert.showAndWait();
		}
	}

	// 등록창 닫기
	public void handlerBtnCancelActoion(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
			
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
		
		if (txtPassWord.getText().trim().equals(txtPasswordRepeat.getText().trim())
		
				&& !txtName.getText().trim().equals("")) {
			jvo = new JoinVO(txtId.getText().trim(), txtPassWord.getText().trim(), txtName.getText().trim());
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
			
			txtPassWord.clear();
			txtPasswordRepeat.clear();
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("패스워드, 이름 확인");
			alert.setHeaderText("패스워드, 이름 확인 검사에 오류가 발생하였습니다.");
			alert.setContentText("패스워드와 이름을 다시 입력하세요.");
			alert.showAndWait();
		}
	}
}