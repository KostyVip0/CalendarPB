package application;

import java.time.LocalDate;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class DateTopController {
	
	CalendarController controller;
	
	private LocalDate ld;
	
	public void setLocalDate(LocalDate ld) {
		this.ld = ld;
	}
	
	public LocalDate getLocalDate() {
		return this.ld;
	}

	/**
	 * Метка с датой.
	 */
    @FXML
    private Label dateTop; 

    /**
     * Главный узел, в который входит метка с датой.
     */
    @FXML
    private AnchorPane topNodeDateTop;
    
    /**
     * Возвращает метку с датой.
     * @return Метка с датой.
     */
    public Label getDateTop() {
    	return this.dateTop;
    }   
    
    // Действие при выборе даты.
    @FXML
    void dateSelection(MouseEvent event) {  
    	
    	//Присваиваем переменной AnchorPane выбранный узел.
    	AnchorPane currentActiveNode = (AnchorPane)event.getSource(); 
    	
  	
    	//Меняем флаг на "выбран".
    	
    	
    	// Проверяем соответствие последнего выбранного узла с текущим. Если не совпадает, то стиль меняется на стиль по умолчанию.
    	if(!currentActiveNode.equals(DateTopStatus.getTopStatus()) | !DateTopStatus.getStatusFlag()) {
    		DateTopStatus.getTopStatus().pseudoClassStateChanged(ifDateSelection, false);
    		currentActiveNode.pseudoClassStateChanged(ifDateSelection, true);
    		DateTopStatus.setStatusFlag(true);
    	} else {
    		currentActiveNode.pseudoClassStateChanged(ifDateSelection, false);
    		DateTopStatus.setStatusFlag(false);
    		
    	}    		
    	
    	// Сохраняем выбранный узел во внешний класс.
    	DateTopStatus.setDateTopStatus(currentActiveNode);  
    	DateTopStatus.setDateTopController(this);     	
    	
    }
    
    /**
	 * Псевдокласс, который меняет стиль узла выбора даты.
	 * 
	 * @author Chudinov Konstantin S, ProcessBuilding corp.
	 * @version 0.01
	 */
    @FXML
	private final PseudoClass ifDateSelection = PseudoClass.getPseudoClass("ifDateSelection");
    
    
    
    @FXML
    void initialize() {  

    }
}

