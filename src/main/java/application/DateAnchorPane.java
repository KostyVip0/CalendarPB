package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class DateAnchorPane extends AnchorPane {
	
	private LocalDate dateAnchor;	
	
	public LocalDate getDateAnchor() {
		return this.dateAnchor;
	}
	
	public void setDateAnchor(LocalDate dateAnchor) {
		this.dateAnchor = dateAnchor;
	}
	
	public DateAnchorPane(LocalDate dateAnchor, double x, double y) {
		
		this.dateAnchor = dateAnchor;
		
		//Задаем размер
		this.setPrefWidth(40);
		this.setPrefHeight(40);
		
		//Задаем дату
		Label dateLabel = new Label(dateAnchor.format(DateTimeFormatter.ofPattern("d")));
		
		//Стиль даты в календаре.
		dateLabel.setStyle("-fx-text-fill: #FFFFFF;");		
		
		StackPane sp = new StackPane();
		sp.setAlignment(Pos.CENTER);
		
		sp.getChildren().add(dateLabel);
		
		//Добавляем дату в клетку
		this.getChildren().add(sp);
		
		AnchorPane.setBottomAnchor(sp, 0.0);
		AnchorPane.setLeftAnchor(sp, 0.0);
		AnchorPane.setRightAnchor(sp, 0.0);
		AnchorPane.setTopAnchor(sp, 0.0);	
		
		AnchorPane.setLeftAnchor(this,	x);
		AnchorPane.setTopAnchor(this,	y);
		
		//Изменение стиля при наведении мыши
		this.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
			this.setStyle("-fx-background-color:  #FF8B40;");
			dateLabel.setStyle("-fx-text-fill: #000000;");
		});
		
		//Изменение стиля при выходе мыши
		this.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
			this.setStyle("-fx-background-color:  #091D29;");
			dateLabel.setStyle("-fx-text-fill: #FFFFFF;");
		});
	}
}
