package application;

import java.io.IOException;
import javafx.util.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.WindowEvent;

/**
 * Класс, который создает коллекцию классов myDate (VBox) для дальнейшей записи в DatesAndTasks (VBox).
 * return ObservableList
 */
public class ListDate {
	
	/**
	 * param Передаваемое число месяца.
	 */

	
	private static CalendarController controller;	
	
	TreeMap<LocalDate, ArrayList<EventTime>> sm = DatesAndTasksMap.getDateMap();	
	
	
	
	public ListDate(LocalDate lt, CalendarController controller) throws IOException {
		
		this.controller = controller;	
		
		// Перебираем каждый день прошлого, месяца, текущего и будущего.
		for(LocalDate a = lt.minusMonths(1).withDayOfMonth(1); a.isBefore(lt.plusMonths(2).withDayOfMonth(1)); ) {
			// Тут заполняем VBox !!!
			
			
			// Загрузка узла с датой
			FXMLLoader loader = new FXMLLoader(getClass().getResource("DateTop.fxml"));
			Parent dateTop = loader.load();
			DateTopController dateController = loader.getController();
			// Записываем дату в VBox в определенном формате.
			dateController.getDateTop().setText(a.format(DateTimeFormatter.ofPattern("d MMMM")));
			//Устанавливаем дату в контроллере
			dateController.setLocalDate(a);
			
			FXMLLoader loaderVBoxLow = new FXMLLoader(getClass().getResource("VBoxLow.fxml"));
			Parent vBoxLow = loaderVBoxLow.load();
			VBoxLowController vBoxLowController = loaderVBoxLow.getController();
			
			vBoxLowController.getVBoxLow().getChildren().add(dateTop);						
			
			if (sm.containsKey(a)) {				
				
				for(var entry: sm.get(a)) {					
					vBoxLowController.getVBoxLow().getChildren().add(entry);
		    	}				
				
			} else {
				ArrayList<EventTime> et = new ArrayList<EventTime>();
				DatesAndTasksMap.getDateMap().put(a, et);				
				
				Label lb = new Label("    Записи отсутствуют");
				lb.setStyle("-fx-text-fill: #FFFFFF;");
				if(sm.get(a).isEmpty()) vBoxLowController.getVBoxLow().getChildren().add(lb);
			}
			controller.getDatesAndTasks().getChildren().add(vBoxLow);
			a = a.plusDays(1);
		}
		
		//Координаты клетки
		double yLastMonth = 65;
		double xLastMonth = 0;
		
		//Заполняем календарь прошлого месяца
		for(LocalDate b = lt.minusMonths(1).withDayOfMonth(1); b.isBefore(lt.withDayOfMonth(1));) {

			//Вычисляем координаты клеток в зависимости от дня недели. Monday, Tuesday, Wednesday, Thursday, Friday, Saturday and Sunday
			switch(b.getDayOfWeek().toString()) {
			case "MONDAY":				
				xLastMonth = 20;	
				break;
			case "TUESDAY":				
				xLastMonth = 65;
				break;
			case "WEDNESDAY":				
				xLastMonth = 110;
				break;
			case "THURSDAY":				
				xLastMonth = 155;
				break;
			case "FRIDAY":				
				xLastMonth = 200;
				break;
			case "SATURDAY":				
				xLastMonth = 245;
				break;
			case "SUNDAY":				
				xLastMonth = 290;				
				break;
			default:
				xLastMonth = 0;
			};
			
			controller.getLastMonthCalendar().getChildren().add(new DateAnchorPane(b, xLastMonth, yLastMonth));			
			
			if(b.getDayOfWeek().toString() == "SUNDAY") yLastMonth += 45;
			//Прибавляем день
			b = b.plusDays(1);			
		}
		
		//Координаты клетки
				double yCurrentMonth = 65;
				double xCurrentMonth = 0;
		
		//Заполняем календарь текущего месяца
		for(LocalDate b = lt.withDayOfMonth(1); b.isBefore(lt.plusMonths(1).withDayOfMonth(1));) {

			//Вычисляем координаты клеток в зависимости от дня недели. Monday, Tuesday, Wednesday, Thursday, Friday, Saturday and Sunday
			switch(b.getDayOfWeek().toString()) {
			case "MONDAY":				
				xCurrentMonth = 20;	
				break;
			case "TUESDAY":				
				xCurrentMonth = 65;
				break;
			case "WEDNESDAY":				
				xCurrentMonth = 110;
				break;
			case "THURSDAY":				
				xCurrentMonth = 155;
				break;
			case "FRIDAY":				
				xCurrentMonth = 200;
				break;
			case "SATURDAY":				
				xCurrentMonth = 245;
				break;
			case "SUNDAY":				
				xCurrentMonth = 290;				
				break;
			default:
				xCurrentMonth = 0;
			};
			
			controller.getCurrentMonthCalendar().getChildren().add(new DateAnchorPane(b, xCurrentMonth, yCurrentMonth));			
			
			if(b.getDayOfWeek().toString() == "SUNDAY") yCurrentMonth += 45;
			//Прибавляем день
			b = b.plusDays(1);			
		}
		
		//Координаты клетки
		double yFutureMonth = 65;
		double xFutureMonth = 0;
		
		//Заполняем календарь текущего месяца
				for(LocalDate b = lt.plusMonths(1).withDayOfMonth(1); b.isBefore(lt.plusMonths(2).withDayOfMonth(1));) {

					//Вычисляем координаты клеток в зависимости от дня недели. Monday, Tuesday, Wednesday, Thursday, Friday, Saturday and Sunday
					switch(b.getDayOfWeek().toString()) {
					case "MONDAY":				
						xFutureMonth = 20;	
						break;
					case "TUESDAY":				
						xFutureMonth = 65;
						break;
					case "WEDNESDAY":				
						xFutureMonth = 110;
						break;
					case "THURSDAY":				
						xFutureMonth = 155;
						break;
					case "FRIDAY":				
						xFutureMonth = 200;
						break;
					case "SATURDAY":				
						xFutureMonth = 245;
						break;
					case "SUNDAY":				
						xFutureMonth = 290;				
						break;
					default:
						xFutureMonth = 0;
					};
					
					controller.getFutureMonthCalendar().getChildren().add(new DateAnchorPane(b, xFutureMonth, yFutureMonth));			
					
					if(b.getDayOfWeek().toString() == "SUNDAY") yFutureMonth += 45;
					//Прибавляем день
					b = b.plusDays(1);			
				}
		
		//Добавляем месяцы
		//Прошлый месяц
		StackPane spLastMonth = new StackPane();
		spLastMonth.setAlignment(Pos.CENTER);
		spLastMonth.setPrefWidth(350);
		spLastMonth.setPrefHeight(20);
		AnchorPane.setTopAnchor(spLastMonth, 10.0);
		Label lbLastMonth = new Label();
		
		//Делаем месяц с большй буквы
		String strLastMonth = LocalDate.now().minusMonths(1).getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("ru")) + " " + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"));
		char firstLastChar = Character.toUpperCase(strLastMonth.charAt(0));
		String strRestLastMonth = strLastMonth.substring(1);
		strLastMonth = firstLastChar + strRestLastMonth;
		
		lbLastMonth.setText(strLastMonth);
		lbLastMonth.setStyle("-fx-text-fill: #FFFFFF;");
		spLastMonth.getChildren().add(lbLastMonth);
		
		controller.getLastMonthCalendar().getChildren().add(spLastMonth);
		
		//Текущий месяц
		StackPane spCurrentMonth = new StackPane();
		spCurrentMonth.setAlignment(Pos.CENTER);
		spCurrentMonth.setPrefWidth(350);
		spCurrentMonth.setPrefHeight(20);
		AnchorPane.setTopAnchor(spCurrentMonth, 10.0);
		Label lbCurrentMonth = new Label();
		
		//Делаем месяц с большй буквы
		String strCurrentMonth = LocalDate.now().getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("ru")) + " " + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"));
		char firstCurrentChar = Character.toUpperCase(strCurrentMonth.charAt(0));
		String strRestCurrentMonth = strCurrentMonth.substring(1);
		strCurrentMonth = firstCurrentChar + strRestCurrentMonth;
		
		lbCurrentMonth.setText(strCurrentMonth);
		lbCurrentMonth.setStyle("-fx-text-fill: #FFFFFF;");
		spCurrentMonth.getChildren().add(lbCurrentMonth);
		
		controller.getCurrentMonthCalendar().getChildren().add(spCurrentMonth);
		
		//Будущий месяц
		
		StackPane spFutureMonth = new StackPane();
		spFutureMonth.setAlignment(Pos.CENTER);
		spFutureMonth.setPrefWidth(350);
		spFutureMonth.setPrefHeight(20);
		AnchorPane.setTopAnchor(spFutureMonth, 10.0);
		Label lbFutureMonth = new Label();
		
		//Делаем месяц с большй буквы
		String strFutureMonth = LocalDate.now().plusMonths(1).getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("ru")) + " " + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"));
		char firstFutureChar = Character.toUpperCase(strFutureMonth.charAt(0));
		String strRestFutureMonth = strFutureMonth.substring(1);
		strFutureMonth = firstFutureChar + strRestFutureMonth;
		
		lbFutureMonth.setText(strFutureMonth);
		lbFutureMonth.setStyle("-fx-text-fill: #FFFFFF;");
		spFutureMonth.getChildren().add(lbFutureMonth);
		
		controller.getFutureMonthCalendar().getChildren().add(spFutureMonth);
		
		
		//Добавляем дни недели
		StackPane spLastMonday = new StackPane();
		spLastMonday.setAlignment(Pos.CENTER);
		spLastMonday.setPrefWidth(40);
		spLastMonday.setPrefHeight(20);
		Label lbLastMonday = new Label("Пн");
		lbLastMonday.setStyle("-fx-text-fill: #FF8B40;");
		spLastMonday.getChildren().add(lbLastMonday);
		AnchorPane.setLeftAnchor(spLastMonday, 20.0);
		AnchorPane.setTopAnchor(spLastMonday, 40.0);
		
		StackPane spCurrentMonday = new StackPane();
		spCurrentMonday.setAlignment(Pos.CENTER);
		spCurrentMonday.setPrefWidth(40);
		spCurrentMonday.setPrefHeight(20);
		Label lbCurrentMonday = new Label("Пн");
		lbCurrentMonday.setStyle("-fx-text-fill: #FF8B40;");
		spCurrentMonday.getChildren().add(lbCurrentMonday);
		AnchorPane.setLeftAnchor(spCurrentMonday, 20.0);
		AnchorPane.setTopAnchor(spCurrentMonday, 40.0);
		
		StackPane spFutureMonday = new StackPane();
		spFutureMonday.setAlignment(Pos.CENTER);
		spFutureMonday.setPrefWidth(40);
		spFutureMonday.setPrefHeight(20);
		Label lbFutureMonday = new Label("Пн");
		lbFutureMonday.setStyle("-fx-text-fill: #FF8B40;");
		spFutureMonday.getChildren().add(lbFutureMonday);
		AnchorPane.setLeftAnchor(spFutureMonday, 20.0);
		AnchorPane.setTopAnchor(spFutureMonday, 40.0);		

		
		controller.getLastMonthCalendar().getChildren().add(spLastMonday);
		controller.getCurrentMonthCalendar().getChildren().add(spCurrentMonday);
		controller.getFutureMonthCalendar().getChildren().add(spFutureMonday);
		
		StackPane spLastTuesday = new StackPane();
		spLastTuesday.setAlignment(Pos.CENTER);
		spLastTuesday.setPrefWidth(40);
		spLastTuesday.setPrefHeight(20);
		Label lbLastTuesday = new Label("Вт");
		lbLastTuesday.setStyle("-fx-text-fill: #FF8B40;");
		spLastTuesday.getChildren().add(lbLastTuesday);
		AnchorPane.setLeftAnchor(spLastTuesday, 65.0);
		AnchorPane.setTopAnchor(spLastTuesday, 40.0);
		
		StackPane spCurrentTuesday = new StackPane();
		spCurrentTuesday.setAlignment(Pos.CENTER);
		spCurrentTuesday.setPrefWidth(40);
		spCurrentTuesday.setPrefHeight(20);
		Label lbCurrentTuesday = new Label("Вт");
		lbCurrentTuesday.setStyle("-fx-text-fill: #FF8B40;");
		spCurrentTuesday.getChildren().add(lbCurrentTuesday);
		AnchorPane.setLeftAnchor(spCurrentTuesday, 65.0);
		AnchorPane.setTopAnchor(spCurrentTuesday, 40.0);
		
		StackPane spFutureTuesday = new StackPane();
		spFutureTuesday.setAlignment(Pos.CENTER);
		spFutureTuesday.setPrefWidth(40);
		spFutureTuesday.setPrefHeight(20);
		Label lbFutureTuesday = new Label("Вт");
		lbFutureTuesday.setStyle("-fx-text-fill: #FF8B40;");
		spFutureTuesday.getChildren().add(lbFutureTuesday);
		AnchorPane.setLeftAnchor(spFutureTuesday, 65.0);
		AnchorPane.setTopAnchor(spFutureTuesday, 40.0);		
		
		
		controller.getLastMonthCalendar().getChildren().add(spLastTuesday);
		controller.getCurrentMonthCalendar().getChildren().add(spCurrentTuesday);
		controller.getFutureMonthCalendar().getChildren().add(spFutureTuesday);
		
		StackPane spLastWednesday = new StackPane();
		spLastWednesday.setAlignment(Pos.CENTER);
		spLastWednesday.setPrefWidth(40);
		spLastWednesday.setPrefHeight(20);
		Label lbLastWednesday = new Label("Ср");
		lbLastWednesday.setStyle("-fx-text-fill: #FF8B40;");
		spLastWednesday.getChildren().add(lbLastWednesday);
		AnchorPane.setLeftAnchor(spLastWednesday, 110.0);
		AnchorPane.setTopAnchor(spLastWednesday, 40.0);
		
		StackPane spCurrentWednesday = new StackPane();
		spCurrentWednesday.setAlignment(Pos.CENTER);
		spCurrentWednesday.setPrefWidth(40);
		spCurrentWednesday.setPrefHeight(20);
		Label lbCurrentWednesday = new Label("Ср");
		lbCurrentWednesday.setStyle("-fx-text-fill: #FF8B40;");
		spCurrentWednesday.getChildren().add(lbCurrentWednesday);
		AnchorPane.setLeftAnchor(spCurrentWednesday, 110.0);
		AnchorPane.setTopAnchor(spCurrentWednesday, 40.0);
		
		StackPane spFutureWednesday = new StackPane();
		spFutureWednesday.setAlignment(Pos.CENTER);
		spFutureWednesday.setPrefWidth(40);
		spFutureWednesday.setPrefHeight(20);
		Label lbFutureWednesday = new Label("Ср");
		lbFutureWednesday.setStyle("-fx-text-fill: #FF8B40;");
		spFutureWednesday.getChildren().add(lbFutureWednesday);
		AnchorPane.setLeftAnchor(spFutureWednesday, 110.0);
		AnchorPane.setTopAnchor(spFutureWednesday, 40.0);
		
		controller.getLastMonthCalendar().getChildren().add(spLastWednesday);
		controller.getCurrentMonthCalendar().getChildren().add(spCurrentWednesday);
		controller.getFutureMonthCalendar().getChildren().add(spFutureWednesday);
		
		StackPane spLastThursday = new StackPane();
		spLastThursday.setAlignment(Pos.CENTER);
		spLastThursday.setPrefWidth(40);
		spLastThursday.setPrefHeight(20);
		Label lbLastThursday = new Label("Чт");
		lbLastThursday.setStyle("-fx-text-fill: #FF8B40;");
		spLastThursday.getChildren().add(lbLastThursday);
		AnchorPane.setLeftAnchor(spLastThursday, 155.0);
		AnchorPane.setTopAnchor(spLastThursday, 40.0);
		
		StackPane spCurrentThursday = new StackPane();
		spCurrentThursday.setAlignment(Pos.CENTER);
		spCurrentThursday.setPrefWidth(40);
		spCurrentThursday.setPrefHeight(20);
		Label lbCurrentThursday = new Label("Чт");
		lbCurrentThursday.setStyle("-fx-text-fill: #FF8B40;");
		spCurrentThursday.getChildren().add(lbCurrentThursday);
		AnchorPane.setLeftAnchor(spCurrentThursday, 155.0);
		AnchorPane.setTopAnchor(spCurrentThursday, 40.0);
		
		StackPane spFutureThursday = new StackPane();
		spFutureThursday.setAlignment(Pos.CENTER);
		spFutureThursday.setPrefWidth(40);
		spFutureThursday.setPrefHeight(20);
		Label lbFutureThursday = new Label("Чт");
		lbFutureThursday.setStyle("-fx-text-fill: #FF8B40;");
		spFutureThursday.getChildren().add(lbFutureThursday);
		AnchorPane.setLeftAnchor(spFutureThursday, 155.0);
		AnchorPane.setTopAnchor(spFutureThursday, 40.0);
		
		controller.getLastMonthCalendar().getChildren().add(spLastThursday);
		controller.getCurrentMonthCalendar().getChildren().add(spCurrentThursday);
		controller.getFutureMonthCalendar().getChildren().add(spFutureThursday);
		
		StackPane spLastFriday = new StackPane();
		spLastFriday.setAlignment(Pos.CENTER);
		spLastFriday.setPrefWidth(40);
		spLastFriday.setPrefHeight(20);
		Label lbLastFriday = new Label("Пт");
		lbLastFriday.setStyle("-fx-text-fill: #FF8B40;");
		spLastFriday.getChildren().add(lbLastFriday);
		AnchorPane.setLeftAnchor(spLastFriday, 200.0);
		AnchorPane.setTopAnchor(spLastFriday, 40.0);
		
		StackPane spCurrentFriday = new StackPane();
		spCurrentFriday.setAlignment(Pos.CENTER);
		spCurrentFriday.setPrefWidth(40);
		spCurrentFriday.setPrefHeight(20);
		Label lbCurrentFriday = new Label("Пт");
		lbCurrentFriday.setStyle("-fx-text-fill: #FF8B40;");
		spCurrentFriday.getChildren().add(lbCurrentFriday);
		AnchorPane.setLeftAnchor(spCurrentFriday, 200.0);
		AnchorPane.setTopAnchor(spCurrentFriday, 40.0);
		
		StackPane spFutureFriday = new StackPane();
		spFutureFriday.setAlignment(Pos.CENTER);
		spFutureFriday.setPrefWidth(40);
		spFutureFriday.setPrefHeight(20);
		Label lbFutureFriday = new Label("Пт");
		lbFutureFriday.setStyle("-fx-text-fill: #FF8B40;");
		spFutureFriday.getChildren().add(lbFutureFriday);
		AnchorPane.setLeftAnchor(spFutureFriday, 200.0);
		AnchorPane.setTopAnchor(spFutureFriday, 40.0);
		
		controller.getLastMonthCalendar().getChildren().add(spLastFriday);
		controller.getCurrentMonthCalendar().getChildren().add(spCurrentFriday);
		controller.getFutureMonthCalendar().getChildren().add(spFutureFriday);
		
		StackPane spLastSaturday = new StackPane();
		spLastSaturday.setAlignment(Pos.CENTER);
		spLastSaturday.setPrefWidth(40);
		spLastSaturday.setPrefHeight(20);
		Label lbLastSaturday = new Label("Сб");
		lbLastSaturday.setStyle("-fx-text-fill: #FF8B40;");
		spLastSaturday.getChildren().add(lbLastSaturday);
		AnchorPane.setLeftAnchor(spLastSaturday, 245.0);
		AnchorPane.setTopAnchor(spLastSaturday, 40.0);
		
		StackPane spCurrentSaturday = new StackPane();
		spCurrentSaturday.setAlignment(Pos.CENTER);
		spCurrentSaturday.setPrefWidth(40);
		spCurrentSaturday.setPrefHeight(20);
		Label lbCurrentSaturday = new Label("Сб");
		lbCurrentSaturday.setStyle("-fx-text-fill: #FF8B40;");
		spCurrentSaturday.getChildren().add(lbCurrentSaturday);
		AnchorPane.setLeftAnchor(spCurrentSaturday, 245.0);
		AnchorPane.setTopAnchor(spCurrentSaturday, 40.0);
		
		StackPane spFutureSaturday = new StackPane();
		spFutureSaturday.setAlignment(Pos.CENTER);
		spFutureSaturday.setPrefWidth(40);
		spFutureSaturday.setPrefHeight(20);
		Label lbFutureSaturday = new Label("Сб");
		lbFutureSaturday.setStyle("-fx-text-fill: #FF8B40;");
		spFutureSaturday.getChildren().add(lbFutureSaturday);
		AnchorPane.setLeftAnchor(spFutureSaturday, 245.0);
		AnchorPane.setTopAnchor(spFutureSaturday, 40.0);
		
		controller.getLastMonthCalendar().getChildren().add(spLastSaturday);
		controller.getCurrentMonthCalendar().getChildren().add(spCurrentSaturday);
		controller.getFutureMonthCalendar().getChildren().add(spFutureSaturday);
		
		StackPane spLastSunday = new StackPane();
		spLastSunday.setAlignment(Pos.CENTER);
		spLastSunday.setPrefWidth(40);
		spLastSunday.setPrefHeight(20);
		Label lbLastSunday = new Label("Вс");
		lbLastSunday.setStyle("-fx-text-fill: #FF8B40;");
		spLastSunday.getChildren().add(lbLastSunday);
		AnchorPane.setLeftAnchor(spLastSunday, 290.0);
		AnchorPane.setTopAnchor(spLastSunday, 40.0);
		
		StackPane spCurrentSunday = new StackPane();
		spCurrentSunday.setAlignment(Pos.CENTER);
		spCurrentSunday.setPrefWidth(40);
		spCurrentSunday.setPrefHeight(20);
		Label lbCurrentSunday = new Label("Вс");
		lbCurrentSunday.setStyle("-fx-text-fill: #FF8B40;");
		spCurrentSunday.getChildren().add(lbCurrentSunday);
		AnchorPane.setLeftAnchor(spCurrentSunday, 290.0);
		AnchorPane.setTopAnchor(spCurrentSunday, 40.0);
		
		StackPane spFutureSunday = new StackPane();
		spFutureSunday.setAlignment(Pos.CENTER);
		spFutureSunday.setPrefWidth(40);
		spFutureSunday.setPrefHeight(20);
		Label lbFutureSunday = new Label("Вс");
		lbFutureSunday.setStyle("-fx-text-fill: #FF8B40;");
		spFutureSunday.getChildren().add(lbFutureSunday);
		AnchorPane.setLeftAnchor(spFutureSunday, 290.0);
		AnchorPane.setTopAnchor(spFutureSunday, 40.0);
		
		controller.getLastMonthCalendar().getChildren().add(spLastSunday);
		controller.getCurrentMonthCalendar().getChildren().add(spCurrentSunday);
		controller.getFutureMonthCalendar().getChildren().add(spFutureSunday);
		
		
		
		//Механизм прокрутки ScrollPane к текущей дате.
		for(Node vb: controller.getDatesAndTasks().getChildren()) {
			VBox vbb = (VBox)vb;			
			AnchorPane ap = (AnchorPane) vbb.getChildren().get(0);			
			Label lbb = (Label)ap.getChildren().get(0);			
			if(lbb.getText().equals(LocalDate.now().format(DateTimeFormatter.ofPattern("d MMMM")))) {				
				
				//Пытаемся прокрутить.
				Platform.runLater(() -> {				    
					Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
						//Расположение элемента относительно родителя.
						double elementYPos = vb.localToScene(vb.getBoundsInLocal()).getMinY();
						
						//Общая высота элементов в VBox DatesAndTasks.
						double totalContentHeight = controller.getDatesAndTasks().getLayoutBounds().getHeight();
						
						// Высота просмотра в окне ScrollPane.						
						double positionPercentage = Math.min(elementYPos / (totalContentHeight), 1.0d);
						
						controller.getAScrollPane().vvalueProperty().set(positionPercentage);
				    }));
					
				    timeline.play();
				});
				break;				
			}			
		}				
	}
}








