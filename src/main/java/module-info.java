module CalendarPB {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	// Для создания сущностей
	requires spring.data.jpa;
	requires jakarta.persistence;
	// Для создания репозитория
	requires spring.data.commons;
	// Для создания сервиса (@Service)
	requires spring.context;
	requires spring.beans;
	requires spring.boot.autoconfigure;
	requires spring.boot;
	requires spring.core;
	requires org.hibernate.orm.core;

	requires static lombok;

	exports application;


	opens application to javafx.graphics, javafx.fxml, spring.core, org.hibernate.orm.core;
}