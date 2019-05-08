package control;

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

public class MainController implements Initializable {
	@FXML
	private TabPane mainPane;
	@FXML
	private Tab Subject;
	@FXML
	private SubjectTabController subjectTabController;// 참조변수명 부여 방법:include 시 명시한 id+"controller"
	@FXML
	private Tab student;
	@FXML
	private StudentTabController studentTabController;
	@FXML
	private Tab lesson;
	@FXML
	private LessonTabController lessonTabController;
	@FXML
	private Tab traineeTotal;
	@FXML
	private TraineeTotalTabController traineeTotalTabController;

	// 메뉴
	@FXML
	private MenuItem menuExit;
	@FXML
	private MenuItem menuLogout;
	@FXML
	private MenuItem menuInfo;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			mainPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
				@Override
				public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
					if (newValue == Subject) {
						System.out.println("학과");
						try {
							subjectTabController.subjectTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (newValue == student) {
						try {
							studentTabController.studentTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (newValue == lesson) {
						try {
							lessonTabController.lessonTotalList();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (newValue == traineeTotal) {
						try {
							traineeTotalTabController.traineeTotalList();
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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();
			mainMtage.setTitle("미래 대학교 학사관리");
			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) mainPane.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handlerMenuInfoAction(ActionEvent event) {
		Alert alert;
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("미래 대학교");
		alert.setHeaderText("미래 대학교 수강신청 프로그램");
		alert.setContentText("Future Universit Register Courses Version 0.01");
		alert.setResizable(false);
		alert.showAndWait();
	}

	public void handlerMenuExitAction(ActionEvent event) {
		Alert alert;
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("미래 대학교");
		alert.setHeaderText("미래 대학교 수강신청 프로그램 종료");
		alert.setContentText("확인 버튼을 클릭하면 미래 대학교 수강신청 프로그램종료합니다.");
		alert.setResizable(false);
		alert.showAndWait();
		Platform.exit();
	}
}
