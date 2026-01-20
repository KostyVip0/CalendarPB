package application;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Iterator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Translate;

/**
 * Класс формирует узел AnchorPane, который принимает от пользователя данные по задаче и ее времени.
 */
public class EditingEventController {
	
	private CalendarController calendarController;
	
	DateTopController dt;
	
	public void setCalendarController (CalendarController calendarController) {
		this.calendarController = calendarController;
	}
	
	private EventTime eventTime;
	
	public void setEventTime (EventTime eventTime) {
		this.eventTime = eventTime;
	}
	
	@FXML
    private AnchorPane editingEventNode;
	
	public AnchorPane getEditingEventNode() {
		return this.editingEventNode;
	}
	
	@FXML
    private Spinner<Integer> beginSPH;
	
	public Spinner<Integer> getBeginSPH() {
		return this.beginSPH;
	}

    @FXML
    private Spinner<Integer> beginSPM;
    
    public Spinner<Integer> getBeginSPM() {
		return this.beginSPM;
	}
    
    @FXML
    private Spinner<Integer> endSPH;

    @FXML
    private Spinner<Integer> endSPM;  
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
    void scrollSpinner(ScrollEvent event) {    	
    	double deltaY = event.getDeltaY();
    	//Прокрутка в плюс
    	if(deltaY > 0) {   
    		((Spinner)event.getSource()).getValueFactory().setValue((Integer)((Spinner)event.getSource()).getValueFactory().getValue() + 1);
    		    		
    	}
    	//Прокрутка в минус
    	if(deltaY < 0) {
    		((Spinner)event.getSource()).getValueFactory().setValue((Integer)((Spinner)event.getSource()).getValueFactory().getValue() - 1);    		
    	}
    }
    

    @FXML
    private Button btnRecord;
    
    @FXML
    void btnEditingAction(ActionEvent event) {     	
    	
    	int beginH = beginSPH.getValueFactory().getValue();
    	int beginM = beginSPM.getValueFactory().getValue(); 
    	
    	String str = descriptionEvent.getText();
    	
    	//Удаляем все записи.
    	VBox low = (VBox)eventTime.getParent();
    	Iterator<Node> iterator = low.getChildren().iterator();
    	while(iterator.hasNext()) {
    		Node node = iterator.next();
    		if(node instanceof EventTime) iterator.remove();;
    	} 
    	
    	//Удаляем из карты старую запись.
    	DatesAndTasksMap.getDateMap().get(eventTime.getLocalDate()).remove(eventTime);
    	
    	//Получаем новую запись
    	EventTime etNew = new EventTime(LocalTime.of(beginH, beginM), str, calendarController);
    	//Зашиваем в EventTime переменную LocalDate
    	etNew.setLocalDate(eventTime.getLocalDate());
    	
    	//Добавляем EventTime в карту.
    	DatesAndTasksMap.getDateMap().get(DateTopStatus.getDateTopController().getLocalDate()).add(etNew); 
    	
    	//Сортируем ArrayList в карте.
    	Collections.sort(DatesAndTasksMap.getDateMap().get(DateTopStatus.getDateTopController().getLocalDate()), new EventTimeComparator());   	 	
    	
    	//Записываем в узел.
    	for(EventTime a: DatesAndTasksMap.getDateMap().get(DateTopStatus.getDateTopController().getLocalDate())) {    		
    		low.getChildren().add(a);
    	}  	

		
    	//Убираем диалоговое окно и блюр
    	calendarController.getStackPane().getChildren().remove(editingEventNode);
    	calendarController.getAScrollPane().setEffect(null);    	
    	
    }

    @FXML
    private Button btnRecordCancel;
    
    @FXML
    void btnEditingCanselAction(ActionEvent event) {
    	calendarController.getStackPane().getChildren().remove(editingEventNode);
    	calendarController.getAScrollPane().setEffect(null);
    }

    @FXML
    private TextArea descriptionEvent;
    
    public TextArea getDescriptionEvent() {
    	return this.descriptionEvent;
    }
    
	/**
	 * @param Начальная координата Х курсора. Используется для изменения его размеров.
	 */
	double xEvStart;
	
	/**
	 * @param Начальная координата Y курсора. Используется для изменения его размеров.
	 */
	double yEvStart;
	
	/**
	 * @param Начальная координата Х окна приложения. Используется для изменения его размеров.
	 */
	double xEvSceneStart;
	
	/**
	 * @param Начальная координата Y окна приложения. Используется для изменения его размеров.
	 */
	double yEvSceneStart;
    
    @FXML
    void editingEventMouseDragged(MouseEvent event) {
    	if(event.getButton().equals(MouseButton.PRIMARY)) {
    		
    		Translate translate = new Translate(event.getScreenX() - xEvStart, event.getScreenY() - yEvStart); // xOffset и yOffset — смещения по осям
    		editingEventNode.getTransforms().add(translate);
    		xEvStart = event.getScreenX();
    		yEvStart = event.getScreenY();
		}
    }
    
    @FXML
    void editingEventPressed(MouseEvent event) {
    	if(event.getButton().equals(MouseButton.PRIMARY)) {
			xEvStart = event.getScreenX();
			yEvStart = event.getScreenY();

    }
}
    @FXML
    void initialize() throws IOException {  
    	
    	beginSPH.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23));
    	beginSPH.setEditable(true);
    	beginSPM.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
    	beginSPM.setEditable(true);
    	
    	
    	
    	endSPH.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23));
    	endSPH.setEditable(true);
    	endSPM.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
    	endSPM.setEditable(true);

    }
}