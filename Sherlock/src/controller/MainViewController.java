package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainViewController implements Initializable {

	@FXML
	private DatePicker dpDate; // 날짜정보 창
	@FXML
	private Button btnTime0; // 시간 버튼
	@FXML
	private Button btnTime1; // 시간 버튼
	@FXML
	private Button btnTime2; // 시간 버튼
	@FXML
	private Button btnTime3; // 시간 버튼
	@FXML
	private Button btnTime4; // 시간 버튼
	@FXML
	private Button btnTime5; // 시간 버튼
	@FXML
	private Button btnTime6; // 시간 버튼
	@FXML
	private Button btnTime7; // 시간 버튼
	@FXML
	private Button btnTime8; // 시간 버튼
	@FXML
	private Button btnTime9; // 시간 버튼
	@FXML
	private Button btnTime10; // 시간 버튼
	@FXML
	private Button btnTime11; // 시간 버튼
	@FXML
	private Button btnTime12; // 시간 버튼
	@FXML
	private Button btnTime13; // 시간 버튼
	@FXML
	private Button btnTime14; // 시간 버튼
	@FXML
	private Button btnTime15; // 시간 버튼
	@FXML
	private Button btnTime16; // 시간 버튼
	@FXML
	private Button btnTime17; // 시간 버튼
	@FXML
	private Button btnTime18; // 시간 버튼
	@FXML
	private Button btnTime19; // 시간 버튼
	@FXML
	private Button btnTime20; // 시간 버튼
	@FXML
	private Button btnTime21; // 시간 버튼
	@FXML
	private Button btnTime22; // 시간 버튼
	@FXML
	private Button btnTime23; // 시간 버튼
	@FXML
	private Button btnTime24; // 시간 버튼
	@FXML
	private Button btnTime25; // 시간 버튼
	@FXML
	private Button btnTime26; // 시간 버튼
	@FXML
	private Button btnTime27; // 시간 버튼
	@FXML
	private Button btnTime28; // 시간 버튼
	@FXML
	private Button btnTime29; // 시간 버튼
	@FXML
	private Button btnTime30; // 시간 버튼
	@FXML
	private Button btnTime31; // 시간 버튼
	@FXML
	private Button btnTime32; // 시간 버튼

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

		btnTime0.setOnAction(event -> handlerbtnTime0Action(event)); // 시간 버튼 이벤트
		btnTime1.setOnAction(event -> handlerbtnTime1Action(event)); // 시간 버튼 이벤트
		btnTime2.setOnAction(event -> handlerbtnTime2Action(event)); // 시간 버튼 이벤트
		btnTime3.setOnAction(event -> handlerbtnTime3Action(event)); // 시간 버튼 이벤트
		btnTime4.setOnAction(event -> handlerbtnTime4Action(event)); // 시간 버튼 이벤트
		btnTime5.setOnAction(event -> handlerbtnTime5Action(event)); // 시간 버튼 이벤트
		btnTime6.setOnAction(event -> handlerbtnTime6Action(event)); // 시간 버튼 이벤트
		btnTime7.setOnAction(event -> handlerbtnTime7Action(event)); // 시간 버튼 이벤트
		btnTime8.setOnAction(event -> handlerbtnTime8Action(event)); // 시간 버튼 이벤트
		btnTime9.setOnAction(event -> handlerbtnTime9Action(event)); // 시간 버튼 이벤트
		btnTime10.setOnAction(event -> handlerbtnTime10Action(event)); // 시간 버튼 이벤트

		btnTime11.setOnAction(event -> handlerbtnTime11Action(event)); // 시간 버튼 이벤트
		btnTime12.setOnAction(event -> handlerbtnTime12Action(event)); // 시간 버튼 이벤트
		btnTime13.setOnAction(event -> handlerbtnTime13Action(event)); // 시간 버튼 이벤트
		btnTime14.setOnAction(event -> handlerbtnTime14Action(event)); // 시간 버튼 이벤트
		btnTime15.setOnAction(event -> handlerbtnTime15Action(event)); // 시간 버튼 이벤트
		btnTime16.setOnAction(event -> handlerbtnTime16Action(event)); // 시간 버튼 이벤트
		btnTime17.setOnAction(event -> handlerbtnTime17Action(event)); // 시간 버튼 이벤트
		btnTime18.setOnAction(event -> handlerbtnTime18Action(event)); // 시간 버튼 이벤트
		btnTime19.setOnAction(event -> handlerbtnTime19Action(event)); // 시간 버튼 이벤트
		btnTime20.setOnAction(event -> handlerbtnTime20Action(event)); // 시간 버튼 이벤트
		btnTime21.setOnAction(event -> handlerbtnTime21Action(event)); // 시간 버튼 이벤트

		btnTime22.setOnAction(event -> handlerbtnTime22Action(event)); // 시간 버튼 이벤트
		btnTime23.setOnAction(event -> handlerbtnTime23Action(event)); // 시간 버튼 이벤트
		btnTime24.setOnAction(event -> handlerbtnTime24Action(event)); // 시간 버튼 이벤트
		btnTime25.setOnAction(event -> handlerbtnTime25Action(event)); // 시간 버튼 이벤트
		btnTime26.setOnAction(event -> handlerbtnTime26Action(event)); // 시간 버튼 이벤트
		btnTime27.setOnAction(event -> handlerbtnTime27Action(event)); // 시간 버튼 이벤트
		btnTime28.setOnAction(event -> handlerbtnTime28Action(event)); // 시간 버튼 이벤트
		btnTime29.setOnAction(event -> handlerbtnTime29Action(event)); // 시간 버튼 이벤트
		btnTime30.setOnAction(event -> handlerbtnTime30Action(event)); // 시간 버튼 이벤트
		btnTime31.setOnAction(event -> handlerbtnTime31Action(event)); // 시간 버튼 이벤트
		btnTime32.setOnAction(event -> handlerbtnTime32Action(event)); // 시간 버튼 이벤트
	}

	// 버튼 마다 이벤트 걸어주기

	public void handlerbtnTime0Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime0.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime1Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime1.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime2Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime2.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime3Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime3.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime4Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime4.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime5Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime5.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime6Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime6.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime7Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime7.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime8Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime8.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime9Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime9.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime10Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime10.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime11Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime11.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime12Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime12.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime13Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime13.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime14Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime14.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime15Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime15.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime16Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime16.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime17Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime17.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime18Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime18.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime19Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime19.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime20Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime20.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime21Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime21.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime22Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime22.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime23Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime23.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime24Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime24.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime25Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime25.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime26Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime26.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime27Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime27.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime28Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime28.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime29Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime29.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime30Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime30.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime31Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime31.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	public void handlerbtnTime32Action(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Reservation.fxml"));
			Parent mainView = (Parent) loader.load();
			Scene scane = new Scene(mainView);
			Stage mainMtage = new Stage();

			mainMtage.setTitle("명탐정 방탈출 예약관리 시스템");

			mainMtage.setTitle("방탈출 예약관리 시스템");

			mainMtage.setResizable(false);
			mainMtage.setScene(scane);
			Stage oldStage = (Stage) btnTime32.getScene().getWindow();
			oldStage.close();
			mainMtage.show();
		} catch (IOException e) {
			System.err.println("오류" + e);
		}

	}

	
	public static void MainViewTotalList() {

	}

}
