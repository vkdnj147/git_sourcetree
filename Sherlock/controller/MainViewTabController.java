package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.security.auth.Subject;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class MainViewTabController implements Initializable {

	// 메인 뷰를 보여주는 탭
	@FXML
	private Tab mainView;
	@FXML
	private MainViewController mainViewController;// 참조변수명 부여 방법:include 시 명시한 id+"controller"

	// 이용자 관리
	@FXML
	private Tab Reservation;
	
	@FXML private ReservationController reservationController;
	 
	// 직원 관리
	@FXML
	private Tab totalEmployee;
	@FXML
	private TotalEmployeeTabController totalEmployeeTabController;

	// 매출현황
	@FXML
	private Tab totalSale;

	// @FXML private TotalSaleTabController TotalSaleTabController;

	@FXML
	private MenuItem menuExit;
	@FXML
	private MenuItem menuLogout;
	@FXML
	private MenuItem menuInfo;
	@FXML
	private TabPane mainViewTabPane; //

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			mainViewTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
				@Override
				public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
					if (newValue == mainView) {

						try {
							MainViewController.MainViewTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (newValue == Reservation) {
						try {
							ReservationController.TotalResvervationList();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (newValue == totalEmployee) {
						try {
							TotalEmployeeTabController.employeeTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (newValue == totalSale) {
						try {
							// TotalSaleTabController.TotalSaleTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			});
			// 메뉴 이벤트 등록
			menuExit.setOnAction(event -> handlerMenuExitAction(event));
			menuLogout.setOnAction(event -> handlerMenuLogoutAction(event));
			menuInfo.setOnAction(event -> handlerMenuInfoAction(event));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handlerMenuLogoutAction(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();
			mainMtage.setTitle("명탐정 방탈출 예약 관리 시스템");
			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) mainViewTabPane.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handlerMenuInfoAction(ActionEvent event) {
		Alert alert;
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("명탐정 방탈출카페");
		alert.setHeaderText("명탐정 방탈출 카페 예약 관리 시스템");
		alert.setContentText("The Ultimate Real-Life Room Escape Game");
		alert.setResizable(false);
		alert.showAndWait();
	}

	public void handlerMenuExitAction(ActionEvent event) {
		Alert alert;
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("명탐정 방탈출카페");
		alert.setHeaderText("명탐정 방탈출 카페 예약 관리 프로그램 종료");
		alert.setContentText("확인 버튼을 클릭하면 명탐정 방탈출 카페 예약 관리 프로그램을 종료합니다.");
		alert.setResizable(false);
		alert.showAndWait();
		Platform.exit();
	}
}
