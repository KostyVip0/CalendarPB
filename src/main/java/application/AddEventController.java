package application;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
 * Класс формирует узел AnchorPane, который принимает от пользователя данные по задаче и ее времени выполнения.
 */
public class AddEventController {
	
	private CalendarController calendarController;
	
	public void setCalendarController (CalendarController calendarController) {
		this.calendarController = calendarController;
	}
	
	@FXML
    private AnchorPane addEventNode;
	
	public AnchorPane getAddEventNode() {
		return this.addEventNode;
	}
	
	@FXML
    private Spinner<Integer> beginSPH;

    @FXML
    private Spinner<Integer> beginSPM;
    
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
    void btnRecordAction(ActionEvent event) {
    	
    	//Удаляем стандартную запись
    	VBox low = (VBox)DateTopStatus.getTopStatus().getParent();
    	if(DatesAndTasksMap.getDateMap().get(DateTopStatus.getDateTopController().getLocalDate()).isEmpty()) low.getChildren().remove(1);
    	
    	int beginH = beginSPH.getValueFactory().getValue();
    	int beginM = beginSPM.getValueFactory().getValue(); 
    	
    	String str = descriptionEvent.getText();    	
    	EventTime et = new EventTime(LocalTime.of(beginH, beginM), str, calendarController);    	
    	
    	//Добавляем EventTime в карту.
    	DatesAndTasksMap.getDateMap().get(DateTopStatus.getDateTopController().getLocalDate()).add(et); 
    	
    	//Сортируем ArrayList в карте.
    	Collections.sort(DatesAndTasksMap.getDateMap().get(DateTopStatus.getDateTopController().getLocalDate()), new EventTimeComparator());
    	
    	//Зашиваем в EventTime переменную LocalDate
    	et.setLocalDate(DateTopStatus.getDateTopController().getLocalDate());    	

    	// Удаляем все узлы EventTime
    	Iterator<Node> iterator = low.getChildren().iterator();
    	while(iterator.hasNext()) {
    		Node node = iterator.next();
    		if(node instanceof EventTime) iterator.remove();;
    	}
    	
    	//Посмотрим на результат.
    	for(EventTime a: DatesAndTasksMap.getDateMap().get(DateTopStatus.getDateTopController().getLocalDate())) {    		
    		low.getChildren().add(a);
    	}       	  	
    	
    	//Удаляем данные из формы ввода.
    	beginSPH.getValueFactory().setValue(0);
    	beginSPM.getValueFactory().setValue(0);
    	endSPH.getValueFactory().setValue(0);
    	endSPM.getValueFactory().setValue(0);
    	descriptionEvent.setText(null);
    	
    	
    	calendarController.getStackPane().getChildren().remove(addEventNode);
    	calendarController.getAScrollPane().setEffect(null);
    	

    }

    @FXML
    private Button btnRecordCancel;
    
    @FXML
    void btnRecordCanselAction(ActionEvent event) {
    	calendarController.getStackPane().getChildren().remove(addEventNode);
    	calendarController.getAScrollPane().setEffect(null);
    }

    @FXML
    private TextArea descriptionEvent;
    
	/**
	 * @param Начальная координата Х курсора. Используется для его перемещения.
	 */
	double xEvStart;
	
	/**
	 * @param Начальная координата Y курсора. Используется для его перемещения.
	 */
	double yEvStart;
	
	/**
	 * @param Начальная координата Х окна приложения. Используется для его перемещения.
	 */
	double xEvSceneStart;
	
	/**
	 * @param Начальная координата Y окна приложения. Используется для его перемещения.
	 */
	double yEvSceneStart;
    
    @FXML
    void addEventMouseDragged(MouseEvent event) {
    	if(event.getButton().equals(MouseButton.PRIMARY)) {
    		
    		Translate translate = new Translate(event.getScreenX() - xEvStart, event.getScreenY() - yEvStart); // xOffset и yOffset — смещения по осям
    		addEventNode.getTransforms().add(translate);
    		xEvStart = event.getScreenX();
    		yEvStart = event.getScreenY();
		}
    }
    
    @FXML
    void addEventPressed(MouseEvent event) {
    	if(event.getButton().equals(MouseButton.PRIMARY)) {
			xEvStart = event.getScreenX();
			yEvStart = event.getScreenY();

    }
}
    @FXML
    void initialize() throws IOException {  
    	//Настройка спиннеров.
    	beginSPH.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23));
    	beginSPH.setEditable(true);
    	beginSPM.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
    	beginSPM.setEditable(true);    	
    	
    	//Настройка спиннеров.
    	endSPH.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23));
    	endSPH.setEditable(true);
    	endSPM.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
    	endSPM.setEditable(true);

    }
}