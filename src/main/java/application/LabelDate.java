package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.Label;

/**
 * Класс, который принимает аргумент дата-LocalDateTime и возвращает метку-Label с датой.
 */
public class LabelDate {
	
	private LocalDateTime ldt;
	
	
	public LabelDate(LocalDateTime ldt) {
		this.ldt = ldt;
	}
	
	public LocalDateTime identDate() {
		return ldt;
	}
	
	public Label getLabelDate() {
		return new Label(ldt.format(DateTimeFormatter.ofPattern("d MMMM")));
	}
}
