package application;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Класс, который содержит описание задачи и время ее выполнения. Возвращает FlowPane.
 */
public class EventTime extends AnchorPane {
	
	private LocalTime timeEvent;
	private String eventDescription;
	private Label timeInET;
	private Label eventInET;
	private Button removeET;
	private Button editingBTN;
	private LocalDate ld;
	
	public void setLocalTime(LocalTime timeEvent) {
		this.timeEvent = timeEvent;
	}
	
	public LocalTime getLocalTime() {
		return this.timeEvent;
	}
	
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	
	//Для отладки
	public String getStr() {
		return eventDescription;
	}
	
	public void setLocalDate(LocalDate ld) {
		this.ld = ld;
	}
	
	public LocalDate getLocalDate() {
		return ld;
	}
	
	public EventTime(LocalTime timeEvent, String eventDescription, CalendarController calendarController) {		
		//Присваиваем значения полям конструктора.
		this.eventDescription = eventDescription;
		this.timeEvent = timeEvent;
		//Настраиваем отображение времени задачи
		timeInET = new Label("      " + timeEvent.toString());
		timeInET.setStyle("-fx-text-fill: #FFFFFF;");
		AnchorPane.setLeftAnchor(timeInET, 0d);
		//Настраиваем отображение описания задачи
		eventInET = new Label("  " + eventDescription);
		eventInET.setStyle("-fx-text-fill: #FFFFFF;");
		AnchorPane.setLeftAnchor(eventInET, 50d);
		
		//Создаем кнопку удаления.
		removeET = new Button("Удалить");
		removeET.setStyle("-fx-background-color: #000000; -fx-text-fill: #FFFFFF");
		AnchorPane.setRightAnchor(removeET, 10d);
		//Изменение стиля кнопки удаления при наведении на нее мыши
		removeET.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
			removeET.setStyle("-fx-background-color:  #FFFFFF; -fx-text-fill: #000000");
		});
		//Возврат стиля кнопки удаления
		removeET.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
			removeET.setStyle("-fx-background-color:  #000000; -fx-text-fill: #FFFFFF");
		});
		//Событие при нажатии кнопки удаления.
		removeET.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			VBox vb = (VBox)(this.getParent());
			vb.getChildren().remove(this);
			DatesAndTasksMap.getDateMap().get(DateTopStatus.getDateTopController().getLocalDate()).remove(this);
			if(DatesAndTasksMap.getDateMap().get(DateTopStatus.getDateTopController().getLocalDate()).isEmpty()) {
				Label lb = new Label("    Записи отсутствуют");
				lb.setStyle("-fx-text-fill: #FFFFFF;");
				vb.getChildren().add(lb);
			}
		});
		
		//Создаем кнопку редактирования.
		editingBTN = new Button("Изменить");
		editingBTN.setStyle("-fx-background-color: #000000; -fx-text-fill: #FFFFFF");
		AnchorPane.setRightAnchor(editingBTN, 75d);
		
		//Изменение стиля кнопки редактирования при наведении на нее мыши
		editingBTN.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
			editingBTN.setStyle("-fx-background-color:  #FFFFFF; -fx-text-fill: #000000");
		});
		//Возврат стиля кнопки редактирования
		editingBTN.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
			editingBTN.setStyle("-fx-background-color:  #000000; -fx-text-fill: #FFFFFF");
		});
		// Событие при нажатии кнопки редактирования		
		editingBTN.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			
			calendarController.getStackPane().getChildren().add(calendarController.getEditingEventController().getEditingEventNode()); 
    		GaussianBlur blurEffect = new GaussianBlur();
    		blurEffect.setRadius(10);
    		calendarController.getAScrollPane().setEffect(blurEffect);
    		
    		//Присваиваем полю для ввода и спиннерам ранее введенный текст.
    		calendarController.getEditingEventController().getDescriptionEvent().setText(eventDescription);
    		calendarController.getEditingEventController().getBeginSPH().getValueFactory().setValue(timeEvent.getHour());
    		calendarController.getEditingEventController().getBeginSPM().getValueFactory().setValue(timeEvent.getMinute());
    		
    		//Сохраняем ссыылку на этот объект в EditingEventController для редактирования.
    		calendarController.getEditingEventController().setEventTime(this);
    		
		});
		
		
		
		this.getChildren().add(timeInET);
		this.getChildren().add(eventInET);
		this.getChildren().add(removeET);
		this.getChildren().add(editingBTN);		
		
		
		
	}
	//Временно для отладки.
	public String getStringEventTime() {
		return timeEvent.toString() + " " + eventDescription;
	}
}

