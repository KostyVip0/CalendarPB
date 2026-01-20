package application;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.scene.layout.VBox;
/**
 * Класс, который инкапсулирует данные в датах: дата, описание задач и время.
 */
public class MyDate extends VBox{
	
	private LocalDate date;		
	private ArrayList<EventTime> eventsTimes; // При создании необходимо отсортировать по времени!
	
	
	public MyDate(LocalDate date, ArrayList<EventTime> eventsTimes) {
		this.date = date;
		this.eventsTimes = eventsTimes;			
	}	
}
