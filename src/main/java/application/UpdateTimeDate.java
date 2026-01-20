package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * Класс формирует текущую дату, время и передает в метку CalendarController.timeLabel.
 * @author Chudinov Konstantin S, ProcessBuilding corp.
 * @version 0.01
 */
public class UpdateTimeDate {	
	
private final CalendarController controller;
	
	public UpdateTimeDate(CalendarController controller) {
		this.controller = controller;
	}	
	
	private String getCurrentDateTime() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("d MMMM HH:mm:ss"));
	}
	
	public void start() {
		
		KeyFrame keyframe = new KeyFrame(Duration.seconds(0.1), event -> {			
			controller.getTimeLabel().setText(getCurrentDateTime());
		});
		
		Timeline timeline = new Timeline(keyframe);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();	
		
	}	
}
