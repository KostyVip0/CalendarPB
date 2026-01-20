package application;

import javafx.scene.layout.AnchorPane;

/**
 * Класс, который содержит информацию по выбранному узлу.
 */
public class DateTopStatus {
	
	private static boolean statusFlag;
	private static AnchorPane apStatus = new AnchorPane();	
	private static DateTopController dateTopController;
	private DateTopStatus() {};
	
	public static AnchorPane getTopStatus() {
		return apStatus;
	}
	
	public static final void setDateTopStatus(AnchorPane apNew) {
		apStatus = apNew;
	}
	
	public static final void setStatusFlag(boolean b) {
		statusFlag = b;
	}
	
	public static final boolean getStatusFlag() {
		return statusFlag;
	}
	
	public static final void setDateTopController(DateTopController dt) {
		dateTopController = dt;
	}
	
	public static DateTopController getDateTopController() {
		return dateTopController;
	}
}