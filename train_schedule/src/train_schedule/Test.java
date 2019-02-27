package train_schedule;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

class Test {
	private Map<String, LinkedList<String>> allTrains = new LinkedHashMap<String, LinkedList<String>>();
	@org.junit.jupiter.api.Test
	void addTrain() {
	        schedule schedule = new schedule(allTrains);
	        assertEquals("Поезд добавлен", schedule.addTrain("name1", "Город 1", "2:22"));
	        assertEquals("Поезд не добавлен", schedule.addTrain("name1", "14:44", "Город 2"));
	        assertEquals("Поезд добавлен", schedule.addTrain("name2", "Город 3", "8:52"));
	        assertEquals("Поезд не добавлен", schedule.addTrain("name5", "Город 888", "99:44"));
	        allTrains.clear();
	}
	@org.junit.jupiter.api.Test
	void deleteTrain() {
        schedule schedule = new schedule(allTrains);
        schedule.addTrain("name1", "Город 1", "2:22");
        assertEquals("Поезд удален", schedule.deleteTrain("name1"));
        assertEquals("Ошибка, такого поезда не существует", schedule.deleteTrain("name1"));
        schedule.addTrain("name2", "Город 2", "8:20");
        assertEquals("Поезд удален", schedule.deleteTrain("name2"));
        assertEquals("Ошибка, такого поезда не существует", schedule.deleteTrain("name5"));
        allTrains.clear();
}
	
}
