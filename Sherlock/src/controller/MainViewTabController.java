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
public class MainViewTabController implements Initializable{
	@FXML
	private TabPane mainPane;

	//메인 뷰를 보여주는 탭
	@FXML
	private Tab MainView;
	@FXML
	private MainViewController MainViewController;// 참조변수명 부여 방법:include 시 명시한 id+"controller"
	
	//이용자 관리
	@FXML
	private Tab TotalCustomer;
	@FXML
	private TotalCustomerTabController TotalCustomerTabController;
	//직원 관리
	@FXML
	private Tab TotalEmployee;
	@FXML
	private TotalEmployeeTabController TotalEmployeeTabController;
	//매출현황
	@FXML
	private Tab TotalSale;
	@FXML
	private TotalSaleTabController TotalSaleTabController;
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			mainPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
				@Override
				public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
					if (newValue == MainView) {
						System.out.println("학과");
						try {
							MainViewController.MainViewTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (newValue == TotalCustomer) {
						try {
							TotalCustomerTabController.TotalCustomerTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (newValue == TotalEmployee) {
						try {
							TotalEmployeeTabController.TotalEmployeeTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (newValue == TotalSale) {
						try {
							TotalSaleTabController.TotalSaleTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
	
		}
	}



