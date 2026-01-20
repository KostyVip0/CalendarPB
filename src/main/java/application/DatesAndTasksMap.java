package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Класс, в котором создается единственный экземпляр карты для хранения задач. Используется паттерн "Одиночка" (Singleton).
 */
public class DatesAndTasksMap {
	
	private static final TreeMap<LocalDate, ArrayList<EventTime>> datMap = new TreeMap<>();
	private DatesAndTasksMap() {}
	
	/**
	 * Метод для получения ссылки карты
	 * @return Карта datMap, которая содержит данные по задачам.
	 */
	public static TreeMap<LocalDate, ArrayList<EventTime>> getDateMap() {
		return datMap;
	}
}