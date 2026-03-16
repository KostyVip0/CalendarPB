package application;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CalendarService {
	
	private final CalendarRepository calendarRepository;
	
	@Autowired
	public CalendarService(CalendarRepository calendarRepository) {
		this.calendarRepository = calendarRepository;
	}
	
	//Сохранение данных в БД.
	public void saveEvent(EntityEventTime entityEventTime) {
		calendarRepository.save(entityEventTime);
	}
}
