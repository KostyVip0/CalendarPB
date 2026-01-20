package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.application.Platform;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.effect.GaussianBlur;


public class CalendarController {
	
	private AddEventController addEventController;	
	private EditingEventController editingEventController;
	public EditingEventController getEditingEventController() {
		return this.editingEventController;
	}	
	
	/*
	 * Календарь прошлого месяца
	 */
	@FXML
	private AnchorPane lastMonthCalendar;
	public AnchorPane getLastMonthCalendar() {
		return lastMonthCalendar;
	}
	
	@FXML
	private AnchorPane currentMonthCalendar;
	public AnchorPane getCurrentMonthCalendar() {
		return currentMonthCalendar;
	}
	
	@FXML
	private AnchorPane futureMonthCalendar;
	public AnchorPane getFutureMonthCalendar() {
		return futureMonthCalendar;
	}
	
		
	/**
	 * Узел сцены выводящей дату.
	 */
	private AnchorPane lastActiveNodeDateTop;
	
	public AnchorPane getDateTopNode() {
		return lastActiveNodeDateTop;
	}
	
	/**
	 * Главная сцена приложения.
	 */
	@FXML
	private AnchorPane PrimaryAnchorPane;	
	
    /**
     * Шапка приложения.
     */
	@FXML
    public AnchorPane TopAnchorNode;
	
    @FXML
    private Label brandName;
    
    @FXML
    private Label nameProg;
    
    @FXML
    private Label accountName;
	
	@FXML
	private ScrollPane aScrollPane;
	 
	public ScrollPane getAScrollPane() {
	     return aScrollPane;
	 }
	 
	@FXML
	 private StackPane iStackPane;
	 
	 public StackPane getStackPane() {
		 return iStackPane;
	 }
	
	/**
	 * Кнопка добавления задачи
	 */
    @FXML
    private Button addButton;
    
    @FXML
    void addButtonClicked(MouseEvent event) throws IOException {
    	if(!DateTopStatus.getStatusFlag()) errLabel.setText("Необходимо выбрать дату.");
    	else {    		
    		iStackPane.getChildren().add(addEventController.getAddEventNode()); 
    		GaussianBlur blurEffect = new GaussianBlur();
    		blurEffect.setRadius(10);
    		aScrollPane.setEffect(blurEffect);

    	};    	
    }
    
	@FXML
	private final PseudoClass setBlur = PseudoClass.getPseudoClass("setBlur");
    
    @FXML
    private Label errLabel;
    
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	/**
	 * Кнопка минимизации приложения.
	 */	
	@FXML
	private Button minWinBtn;

	/**
	 * Кнопка полноэкранного режима.
	 */
	@FXML
	private Button fullScrBtn;

	/**
	 * Кнопка закрытия приложения.
	 */
	@FXML
	private Button exitBtn;
	
	/**
	 * Метка, которая отображает время.
	 */
	@FXML
	private Label timeLabel;	

	/**
	 * Возвращает ссылку на метку, отображающую текущее время и дату.
	 * @return CalendarController.timeLabel
	 */
	public Label getTimeLabel() {
		return timeLabel;
	}	
	
	/**
	 * Узел, который отображает важные даты.
	 */
	@FXML
	private VBox ImportantEventsVBox;
	
	public VBox getImportantEventsVBox() {
		return ImportantEventsVBox;
	}	

	/**
	 * Узел, содержащий записи пользователя по датам.
	 */
	@FXML
	private VBox DatesAndTasks;
	
	public VBox getDatesAndTasks() {
		return DatesAndTasks; 
	}

	/**
	 * @param Переменная-флаг для определения возможности изменения размера окна приложения. Правый Х.
	 */
	boolean cursResizingXR = false;
	/**
	 * @param Переменная-флаг для определения возможности изменения размера окна приложения. Нижний Y.
	 */
	boolean cursResizingYB = false;	
	/**
	 * @param Переменная-флаг для определения возможности изменения размера окна приложения. Левый Х.
	 */
	boolean cursResizingXL = false;
	/**
	 * @param Переменная-флаг для определения возможности изменения размера окна приложения. Верхний Y.
	 */
	boolean cursResizingYT = false;
	/**
	 * @param Переменная-флаг для определения возможности изменения размера окна приложения. Левый верхний угол.
	 */
	boolean cursResizingXYLT = false;
	/**
	 * @param Переменная-флаг для определения возможности изменения размера окна приложения. Правый верхний угол.
	 */
	boolean cursResizingXYRT = false;
	/**
	 * @param Переменная-флаг для определения возможности изменения размера окна приложения. Правый нижний угол.
	 */
	boolean cursResizingXYRB = false;
	/**
	 * @param Переменная-флаг для определения возможности изменения размера окна приложения. Левый нижний угол.
	 */
	boolean cursResizingXYLB = false;
	
	/**
	 * @param Начальная координата Х курсора. Используется для изменения его размеров.
	 */
	double xStart;
	
	/**
	 * @param Начальная координата Y курсора. Используется для изменения его размеров.
	 */
	double yStart;
	
	/**
	 * @param Начальная ширина окна приложения. Используется для изменения его размеров.
	 */
	double widthSceneStart;
	
	/**
	 * @param Начальная высота окна приложения. Используется для изменения его размеров.
	 */
	double heightSceneStart;
	
	/**
	 * @param Начальная координата Х окна приложения. Используется для изменения его размеров.
	 */
	double xSceneStart;
	
	/**
	 * @param Начальная координата Y окна приложения. Используется для изменения его размеров.
	 */
	double ySceneStart;

		
	/********************	СТАНДАРТНЫЕ КНОПКИ ********************/
	/**
	 * Кнопка минимизации приложения.
	 * 
	 * @author Chudinov Konstantin S, ProcessBuilding corp.
	 * @version 0.01
	 */
	@FXML
	void minimWin(ActionEvent event) {
		((Stage) ((Button) event.getSource()).getScene().getWindow()).setIconified(true);
	}

	/**
	 * Кнопка полноэкранного режима приложения.
	 * 
	 * @author Chudinov Konstantin S, ProcessBuilding corp.
	 * @version 0.01
	 */
	@FXML
	void fullScreen(ActionEvent event) {
		if (!((Stage) ((Button) event.getSource()).getScene().getWindow()).isFullScreen()) {
			((Stage) ((Button) event.getSource()).getScene().getWindow()).setFullScreen(true);
			fullScrBtn.pseudoClassStateChanged(setBtnFullScreen, true);
		}			
		else {
			((Stage) ((Button) event.getSource()).getScene().getWindow()).setFullScreen(false);
			fullScrBtn.pseudoClassStateChanged(setBtnFullScreen, false);
			fullScrBtn.pseudoClassStateChanged(setBtnFullScreenHover, false);
		}		
	}
	
	/**
	 * Изменение стиля кнопки полноэкранного режима, когда курсор входит в ее область.
	 * 
	 * @author Chudinov Konstantin S, ProcessBuilding corp.
	 * @version 0.01
	 */
	@FXML
    void enteredFullScreenBtnYes(MouseEvent event) {
		if (((Stage) ((Button) event.getSource()).getScene().getWindow()).isFullScreen()) {
			fullScrBtn.pseudoClassStateChanged(setBtnFullScreenHover, true);			
		}		
    }
	
	/**
	 * Изменение стиля кнопки полноэкранного режима, когда курсор выходит из ее области.
	 * 
	 * @author Chudinov Konstantin S, ProcessBuilding corp.
	 * @version 0.01
	 */
	@FXML
    void enteredFullScreenBtnNo(MouseEvent event) {
		if (((Stage) ((Button) event.getSource()).getScene().getWindow()).isFullScreen()) {
			fullScrBtn.pseudoClassStateChanged(setBtnFullScreenHover, false);			
		}		
    }
	 
	/**
	 * Псевдокласс, который меняет стиль кнопки полноэкранного режима.
	 * 
	 * @author Chudinov Konstantin S, ProcessBuilding corp.
	 * @version 0.01
	 */
	@FXML
	private final PseudoClass setBtnFullScreen = PseudoClass.getPseudoClass("yesFullScr");
	
	/**
	 * Псевдокласс, который меняет стиль кнопки полноэкранного режима.
	 * 
	 * @author Chudinov Konstantin S, ProcessBuilding corp.
	 * @version 0.01
	 */
	@FXML
	private final PseudoClass setBtnFullScreenHover = PseudoClass.getPseudoClass("yesFullScrHover");

	/**
	 * Кнопка закрытия приложения.
	 * 
	 * @author Chudinov Konstantin S, ProcessBuilding corp.
	 * @version 0.01
	 */
	@FXML
	void exitAppl(ActionEvent event) {
		if(!cursResizingXR & !cursResizingYT & !cursResizingXYRT) {
			Platform.exit();
		}
	}	
	/****************************************/
	
	
	/********************	ПЕРЕМЕЩЕНИЕ ОКНА  ********************/
	/**
	 * Метод, который позволяет перетаскивать окно приложения в рамках рабочего стола.
	 * 
	 * @author Chudinov Konstantin S, ProcessBuilding corp.
	 * @version 0.01
	 */
	@FXML
    void TopMouseDragged(MouseEvent event) {
		if(event.getButton().equals(MouseButton.PRIMARY) & !cursResizingXR & !cursResizingXL & !cursResizingYT & !cursResizingXYLT & !cursResizingXYRT) {
			PrimaryAnchorPane.getScene().getWindow().setX(xSceneStart + (event.getScreenX() - xStart));
			PrimaryAnchorPane.getScene().getWindow().setY(ySceneStart + (event.getScreenY() - yStart));
		}		
    }
	
	/****************************************/
	
	/********************	ИЗМЕНЕНИЕ КУРСОРА ДЛЯ РАСТЯГИВАНИЯ/СВОРАЧИВАНИЯ  ********************/
	
	/**
	 * Метод, который позволяет изменять внешний вид курсора на границе приложения и устанавливает флаги.
	 * 
	 * @author Chudinov Konstantin S, ProcessBuilding corp.
	 * @version 0.01
	 */
	@FXML
	void CursorSceneMoved(MouseEvent event) {
		
		final int offCurs = 10; 
		final int offCursBtn = 5;
		
		double cursX = event.getX();
		double cursY = event.getY();
		if(event.getSource() instanceof AnchorPane) {
			
		/* Условие для AnchorPane*/
		if((cursX >= (((AnchorPane)event.getSource()).getScene().getWidth() - offCurs)) & cursY > offCurs & cursY < (((AnchorPane)event.getSource()).getScene().getHeight() - offCurs)) {			
			cursResizingXR = true;
			cursResizingYB = false;			
			cursResizingXL = false;
			cursResizingYT = false;			
			cursResizingXYLT = false;
			cursResizingXYRT = false;
			cursResizingXYRB = false;
			cursResizingXYLB = false;			
			((AnchorPane)event.getSource()).getScene().setCursor(Cursor.H_RESIZE);	
			//System.out.println("Правый X");
		}
		else if(cursX <= offCurs & cursY > offCurs & cursY < (((AnchorPane)event.getSource()).getScene().getHeight() - offCurs)) {
			cursResizingXR = false;
			cursResizingYB = false;			
			cursResizingXL = true;
			cursResizingYT = false;
			cursResizingXYLT = false;
			cursResizingXYRT = false;
			cursResizingXYRB = false;
			cursResizingXYLB = false;
			((AnchorPane)event.getSource()).getScene().setCursor(Cursor.H_RESIZE);
			//System.out.println("Левый X");
		}
		
		else if((cursY >= (((AnchorPane)event.getSource()).getScene().getHeight() - offCurs)) & cursX > offCurs & cursX < (((AnchorPane)event.getSource()).getScene().getWidth() - offCurs)) {
			cursResizingXR = false;
			cursResizingYB = true;			
			cursResizingXL = false;
			cursResizingYT = false;
			cursResizingXYLT = false;
			cursResizingXYRT = false;
			cursResizingXYRB = false;
			cursResizingXYLB = false;
			((AnchorPane)event.getSource()).getScene().setCursor(Cursor.V_RESIZE);
			//System.out.println("Нижний Y");
		}
		else if(cursY <= offCurs & cursX > offCurs & cursX < (((AnchorPane)event.getSource()).getScene().getWidth() - offCurs)) {
			cursResizingXR = false;
			cursResizingYB = false;			
			cursResizingXL = false;
			cursResizingYT = true;
			cursResizingXYLT = false;
			cursResizingXYRT = false;
			cursResizingXYRB = false;
			cursResizingXYLB = false;
			((AnchorPane)event.getSource()).getScene().setCursor(Cursor.V_RESIZE);
			//System.out.println("Верхний Y");
		}
		
		else if(cursX < offCurs & cursY < offCurs) {
			((AnchorPane)event.getSource()).getScene().setCursor(Cursor.SE_RESIZE);
			cursResizingXR = false;
			cursResizingYB = false;			
			cursResizingXL = false;
			cursResizingYT = false;
			cursResizingXYLT = true;
			cursResizingXYRT = false;
			cursResizingXYRB = false;
			cursResizingXYLB = false;
			//System.out.println("Левый верхний угол");
		}
		else if(cursX < offCurs & cursY > (((AnchorPane)event.getSource()).getScene().getHeight() - offCurs)) {
			((AnchorPane)event.getSource()).getScene().setCursor(Cursor.SW_RESIZE);
			cursResizingXR = false;
			cursResizingYB = false;			
			cursResizingXL = false;
			cursResizingYT = false;
			cursResizingXYLT = false;
			cursResizingXYRT = false;
			cursResizingXYRB = false;
			cursResizingXYLB = true;
			//System.out.println("Левый нижний угол");
		}
		else if(cursX > (((AnchorPane)event.getSource()).getScene().getWidth() - offCurs) & cursY > (((AnchorPane)event.getSource()).getScene().getHeight() - offCurs)) {
			((AnchorPane)event.getSource()).getScene().setCursor(Cursor.SE_RESIZE);
			cursResizingXR = false;
			cursResizingYB = false;			
			cursResizingXL = false;
			cursResizingYT = false;
			cursResizingXYLT = false;
			cursResizingXYRT = false;
			cursResizingXYRB = true;
			cursResizingXYLB = false;
			//System.out.println("Правый нижний угол");
		}
		else if(cursX > (((AnchorPane)event.getSource()).getScene().getWidth() - offCurs) & cursY < offCurs) {
			((AnchorPane)event.getSource()).getScene().setCursor(Cursor.SW_RESIZE);
			cursResizingXR = false;
			cursResizingYB = false;			
			cursResizingXL = false;
			cursResizingYT = false;
			cursResizingXYLT = false;
			cursResizingXYRT = true;
			cursResizingXYRB = false;
			cursResizingXYLB = false;
			//System.out.println("Правый верхний угол");
		}
		else {
			cursResizingXR = false;
			cursResizingYB = false;			
			cursResizingXL = false;
			cursResizingYT = false;
			cursResizingXYLT = false;
			cursResizingXYRT = false;
			cursResizingXYRB = false;
			cursResizingXYLB = false;
			((AnchorPane)event.getSource()).getScene().setCursor(Cursor.DEFAULT);
		}		
	}
		/* Условие для Button*/
		else if(event.getSource().equals(exitBtn)) {
			
			if((cursY <= offCursBtn) & cursX < (((Button)event.getSource()).getWidth() - offCursBtn)) {
				cursResizingXR = false;
				cursResizingYB = false;			
				cursResizingXL = false;
				cursResizingYT = true;
				cursResizingXYLT = false;
				cursResizingXYRT = false;
				cursResizingXYRB = false;
				cursResizingXYLB = false;				
				exitBtn.pseudoClassStateChanged(resizingState, true);
				((Button)event.getSource()).getScene().setCursor(Cursor.V_RESIZE);
				//System.out.println("Верхний Y");
			}
			else if(cursX > (((Button)event.getSource()).getWidth() - offCursBtn) & cursY > offCursBtn) {
				cursResizingXR = true;
				cursResizingYB = false;			
				cursResizingXL = false;
				cursResizingYT = false;			
				cursResizingXYLT = false;
				cursResizingXYRT = false;
				cursResizingXYRB = false;
				cursResizingXYLB = false;
				exitBtn.pseudoClassStateChanged(resizingState, true);
				((Button)event.getSource()).getScene().setCursor(Cursor.H_RESIZE);
				//System.out.println("Правый X");
			}
			else if(cursX > (((Button)event.getSource()).getWidth() - offCursBtn) & cursY < offCursBtn) {
				cursResizingXR = false;
				cursResizingYB = false;			
				cursResizingXL = false;
				cursResizingYT = false;
				cursResizingXYLT = false;
				cursResizingXYRT = true;
				cursResizingXYRB = false;
				cursResizingXYLB = false;	
				exitBtn.pseudoClassStateChanged(resizingState, true);
				((Button)event.getSource()).getScene().setCursor(Cursor.SW_RESIZE);	
				//System.out.println("Правый верхний угол");
			}
			else {
				((Button)event.getSource()).getScene().setCursor(Cursor.DEFAULT);
				exitBtn.pseudoClassStateChanged(resizingState, false);
				cursResizingXR = false;
				cursResizingYB = false;			
				cursResizingXL = false;
				cursResizingYT = false;
				cursResizingXYLT = false;
				cursResizingXYRT = false;
				cursResizingXYRB = false;
				cursResizingXYLB = false;
			}
			
	    }		
		else if(event.getSource().equals(fullScrBtn)) {
			((Button)event.getSource()).getScene().setCursor(Cursor.DEFAULT);
		}
		else if(event.getSource().equals(minWinBtn)) {
			((Button)event.getSource()).getScene().setCursor(Cursor.DEFAULT);
		}			
	}
		
		
	/****************************************/
	
	/**
	 * Псевдокласс, который позволяет изменять стиль кнопки закрытия на обычный во время изменения размеров приложения.
	 * 
	 * @author Chudinov Konstantin S, ProcessBuilding corp.
	 * @version 0.01
	 */
	@FXML
	private final PseudoClass resizingState = PseudoClass.getPseudoClass("resizing");
	
	/**
	 * Метод MouseDragged, который позволяет изменять размер окна приложения.
	 * 
	 * @author Chudinov Konstantin S, ProcessBuilding corp.
	 * @version 0.01
	 */
	@FXML
	void SceneResizing(MouseEvent event) {	
		if((event.getButton().equals(MouseButton.PRIMARY)) & !((Stage) PrimaryAnchorPane.getScene().getWindow()).isFullScreen()) {
		
		if(cursResizingXR || cursResizingYB || cursResizingXYRB) {
			
			if(cursResizingXR) PrimaryAnchorPane.getScene().getWindow().setWidth(widthSceneStart + (event.getScreenX() - xStart));
			else if(cursResizingYB) PrimaryAnchorPane.getScene().getWindow().setHeight(heightSceneStart + (event.getScreenY() - yStart));
			else if(cursResizingXYRB) {			
				PrimaryAnchorPane.getScene().getWindow().setWidth(widthSceneStart + (event.getScreenX() - xStart));
				PrimaryAnchorPane.getScene().getWindow().setHeight(heightSceneStart + (event.getScreenY() - yStart));
			}			
		}
		// Поведение Х слева
		if(cursResizingXL) {
			//System.out.println("Велечина смещения курсора по Х: " + (event.getScreenX() - xStart));
			PrimaryAnchorPane.getScene().getWindow().setWidth(widthSceneStart - (event.getScreenX() - xStart));
			// Перемещаем окно
			PrimaryAnchorPane.getScene().getWindow().setX(xSceneStart + (event.getScreenX() - xStart));
		}
		// Поведение Y сверху
		if(cursResizingYT) {
			//System.out.println("Велечина смещения курсора по Y: " + (event.getScreenY() - yStart));
			PrimaryAnchorPane.getScene().getWindow().setHeight(heightSceneStart - (event.getScreenY() - yStart));
			// Перемещаем окно
			PrimaryAnchorPane.getScene().getWindow().setY(ySceneStart + (event.getScreenY() - yStart));
		}
		
		if(cursResizingXYLT) {
			//System.out.println("Велечина смещения курсора по Х: " + (event.getScreenX() - xStart));
			PrimaryAnchorPane.getScene().getWindow().setWidth(widthSceneStart - (event.getScreenX() - xStart));
			// Перемещаем окно
			PrimaryAnchorPane.getScene().getWindow().setX(xSceneStart + (event.getScreenX() - xStart));
			
			//System.out.println("Велечина смещения курсора по Y: " + (event.getScreenY() - yStart));
			PrimaryAnchorPane.getScene().getWindow().setHeight(heightSceneStart - (event.getScreenY() - yStart));
			// Перемещаем окно
			PrimaryAnchorPane.getScene().getWindow().setY(ySceneStart + (event.getScreenY() - yStart));
		}
		
		if(cursResizingXYLB) {
			//System.out.println("Велечина смещения курсора по Х: " + (event.getScreenX() - xStart));
			PrimaryAnchorPane.getScene().getWindow().setWidth(widthSceneStart - (event.getScreenX() - xStart));
			// Перемещаем окно
			PrimaryAnchorPane.getScene().getWindow().setX(xSceneStart + (event.getScreenX() - xStart));
			
			PrimaryAnchorPane.getScene().getWindow().setHeight(heightSceneStart + (event.getScreenY() - yStart));
		}
		
		if(cursResizingXYRT) {
			PrimaryAnchorPane.getScene().getWindow().setWidth(widthSceneStart + (event.getScreenX() - xStart));
			
			//System.out.println("Велечина смещения курсора по Y: " + (event.getScreenY() - yStart));
			PrimaryAnchorPane.getScene().getWindow().setHeight(heightSceneStart - (event.getScreenY() - yStart));
			// Перемещаем окно
			PrimaryAnchorPane.getScene().getWindow().setY(ySceneStart + (event.getScreenY() - yStart));
		}
	}
}
	
	/**
	 * Метод MousePressed, который запоминает начальные размеры приложения, координаты курсора мыши и приложения во время нажатия кнопки мыши.
	 * Метод предназначен для изменения размеров приложения.
	 * 
	 * @author Chudinov Konstantin S, ProcessBuilding corp.
	 * @version 0.01
	 */
	@FXML
	void ScenePressed(MouseEvent event) {
		if(event.getButton().equals(MouseButton.PRIMARY)) {
			xStart = event.getScreenX();
			yStart = event.getScreenY();
			
			//System.out.println("Координата курсора во время нажатия: " + xStart + " : " + yStart);
			
			widthSceneStart = PrimaryAnchorPane.getWidth();
			heightSceneStart = PrimaryAnchorPane.getHeight();
			
			//System.out.println("Размеры приложения во время нажатия: " + widthSceneStart + " : " + heightSceneStart);
			
			xSceneStart = PrimaryAnchorPane.getScene().getWindow().getX();
			ySceneStart = PrimaryAnchorPane.getScene().getWindow().getY();
			
			//System.out.println("Координата окна во время нажатия: " + xSceneStart + " : " + ySceneStart);
		}		
	}
	
	@FXML
	public void initialize() throws IOException {
		
		
		//Отображаем время
		UpdateTimeDate udt = new UpdateTimeDate(this);
		udt.start();			
				
		DatesAndTasks.setFillWidth(true);		
		
		//Загружаем даты и задачи
		ListDate listDate = new ListDate(LocalDate.now(), this);	
		
		
		
		//Загружаем окно добавления задачи и сохраняем ссылку на него.
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEvent.fxml"));
		Parent dateTopAddEvent = loader.load();		
		this.addEventController = loader.getController();
		
		FXMLLoader loaderEE = new FXMLLoader(getClass().getResource("EditingEvent.fxml"));
		Parent editingEvent = loaderEE.load();		
		this.editingEventController = loaderEE.getController();
		
		//Передаем в AddEventController ссылку на основной контроллер.
		addEventController.setCalendarController(this);
		//Передаем в EditingEventController ссылку на основной контроллер.
		editingEventController.setCalendarController(this);	

		
	}	
}
