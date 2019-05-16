package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainViewController implements Initializable {

	@FXML
	private DatePicker dpDate; // 날짜정보 창
	@FXML
	private Button btnTime; // 바차트 버튼
	@FXML
	private TextField txtDay;// 날짜 선택
	@FXML
	private HBox imageBox;// 이미지
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		// 날짜 선택
		dpDate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				LocalDate date = dpDate.getValue();
				txtDay.setText("" + date);
			}
		});
		
		btnTime.setOnAction(event -> handlerBtnTimeAction(event)); //시간 버튼 이벤트

		
		
	}
    //시간을 눌렀을때의 이벤트 핸들러
	public void handlerBtnTimeAction(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(); // 창을 띄우는 기능을 불러온다.
		loader.setLocation(getClass().getResource("/view/Schedule.fxml"));
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.initOwner(btnTime.getScene().getWindow()); // 시간버튼 누를때 데이터를 받았으므로
		dialog.setTitle("예약 접수");

	}
	public static void MainViewTotalList() {
		// TODO Auto-generated method stub
		
	}


}
