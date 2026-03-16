package application;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Хранилище для сущности EntityEventTime. Автоматически создает базовые CRUD методы для работы с БД PSQL.
 */
@Repository
public interface CalendarRepository extends JpaRepository<EntityEventTime, LocalDate> {

}
