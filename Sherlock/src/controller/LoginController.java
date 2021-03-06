package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;

public class LoginController implements Initializable {

	@FXML
	private Label lblLogin; // 라벨 로그인
	@FXML
	private TextField txtem_Id; // 텍스트 아이디
	@FXML
	private PasswordField txtem_Passwd; // 텍스트 비밀번호
	@FXML
	private Button btnCancel; // 종료 버튼
	@FXML
	private Button btnLogin; // 로그인 버튼

	@FXML
	private ToggleGroup loginGroup; // 로그인그룹
	@FXML
	private RadioButton rbManager; // 관리자
	@FXML
	private Label lblIconImg; // 아이콘 이미지
	@FXML
	private ImageView iconImg; // 이미지 뷰 이미지

	public static String managerName = ""; // 관리자이름
	boolean sucess = false; // 블린으로 로그인 성공, 실패

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtem_Id.setOnKeyPressed(event -> handerTxtIdKeyPressed(event)); // 아이디 입력에서Enter 키 이벤트 적용
		txtem_Passwd.setOnKeyPressed(event -> handerTxtPasswordKeyPressed(event)); // 패스워드 입력에서 Enter 키 이벤트 적용
		
		btnLogin.setOnAction(event -> handlerBtnLoginActoion(event)); // 로그인
		btnCancel.setOnAction(event -> handlerBtnCancelActoion(event)); // 로그인창 닫기

	}

	// 아이디 입력에서 Enter 키 이벤트 적용
	public void handerTxtIdKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			txtem_Passwd.requestFocus();
		}
	}

	// 패스워드 입력에서 Enter 키 이벤트 적용
	public void handerTxtPasswordKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			if(txtem_Id.getText().trim().equals("admin")&&txtem_Passwd.getText().trim().equals("1234")) {
				sucess=true;
				login();
			}
			
			if(!(txtem_Id.getText().trim().equals("admin")&&txtem_Passwd.getText().trim().equals("1234"))) {
				sucess=false;
				login();
			}
			
		}
	}

	

	// 로그인창 닫기
	public void handlerBtnCancelActoion(ActionEvent event) {
		Platform.exit();
	}

	// 로그인
	public void handlerBtnLoginActoion(ActionEvent event) {
		
		
		if(txtem_Id.getText().trim().equals("admin")&&txtem_Passwd.getText().trim().equals("1234")) {
			sucess=true;
			login();
		}
		
		if(!(txtem_Id.getText().trim().equals("admin")&&txtem_Passwd.getText().trim().equals("1234"))) {
			sucess=false;
			login();
		}
		
		
		
	}

	// 로그인 메소드
	public void login() {
		LoginDAO login = new LoginDAO(); // 로그인 인스턴스, 객체 생성
		

		// 로그인 성공시 메인 페이지로 이동
		if (sucess) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainViewTab.fxml"));
				Parent mainView = (Parent) loader.load();
				Scene scane = new Scene(mainView);
				Stage mainMtage = new Stage();

				mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

				mainMtage.setTitle("방탈출 예약관리 시스템");

				mainMtage.setResizable(false);
				mainMtage.setScene(scane);
				Stage oldStage = (Stage) btnLogin.getScene().getWindow();
				oldStage.close();
				mainMtage.show();
			} catch (IOException e) {
				System.err.println("오류" + e);
			}
		} else  {
			// 아이디패스워드 확인하라는 창
			Alert alert;
			alert = new Alert(AlertType.WARNING);
			alert.setTitle("로그인 실패");
			alert.setHeaderText("아이디와 비밀번호 불일치");
			alert.setContentText("아이디와 비밀번호가 일치하지않습니다." + "\n 다시 제대로 입력하시오.");
			alert.setResizable(false);
			alert.showAndWait();
			txtem_Id.clear(); // 아이디 초기화
			txtem_Passwd.clear(); // 비밀번호 초기화
		}

	}

	/*
	 * public String managerLoginName() { LoginDAO ldao = new LoginDAO(); String
	 * name = null; try { name = ldao.getLoginName(txtem_Id.getText()); } catch
	 * (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * return name; }
	 */

}
