package application;

import javafx.scene.control.Label;

public class ImportantEvents extends Label {
	
	private final CalendarController controller;
	private String str;
	private Label lbl;
	
	
	ImportantEvents (CalendarController controller, String str) {
		this.controller = controller;
		this.str = str;	
		this.lbl = new Label(this.str);
	}
	
	Label getImportantEvents() {
		return lbl;
	}
	
	
}

