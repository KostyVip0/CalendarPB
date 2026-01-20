package application;

import java.util.Comparator;

public class EventTimeComparator implements Comparator<EventTime> {
	public int compare(EventTime first, EventTime second) {
		return first.getLocalTime().compareTo(second.getLocalTime());
	}
}
