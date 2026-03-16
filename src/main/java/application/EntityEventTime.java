package application;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Сущность, которая отображает экземпляр DatesAndTasksMap в PSQL.
 */
@Entity
@Table(name = "TimeEvents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntityEventTime {

	@Id
	@Column(name = "Date")
	public LocalDate localDate;

	@Column(name = "Time")
	public LocalTime timeEvent;

	@Column(name = "Task")
	public String eventDescription;

}

