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
	@org.junit.jupiter.api.Test
	void addStation() {
        schedule schedule = new schedule(allTrains);
        schedule.addTrain("name1", "Город 1", "2:22");
        assertEquals("Станция добавлена", schedule.addStation("name1" , "Город 2"));
        assertEquals("Такая станция уже добавлена", schedule.addStation("name1" , "Город 2"));
        schedule.addTrain("name2", "Город 2", "8:20");
        assertEquals("Станция добавлена", schedule.addStation("name2" , "Город 3"));
        assertEquals("Такая станция уже добавлена", schedule.addStation("name2" , "Город 3"));
        assertEquals("Ошибка, такого поезда не существует", schedule.addStation("name5" , "Город 111"));
        allTrains.clear();
}
	@org.junit.jupiter.api.Test
	void deleteStation() {
        schedule schedule = new schedule(allTrains);
        schedule.addTrain("name1", "Город 1", "2:22");
        schedule.addStation("name1" , "Город 2");
        assertEquals("Станция удалена", schedule.deleteStation("name1" , "Город 2"));
        assertEquals("Ошибка, такого поезда не существует", schedule.deleteStation("name2" , "Город 21"));
        schedule.addTrain("name2", "Город 2", "8:20");
        assertEquals("Ошибка, нельзя удалить конечную станцию", schedule.deleteStation("name2" , "Город 2"));
        assertEquals("Ошибка, такой станции не существует", schedule.deleteStation("name2" , "Город 343"));
        allTrains.clear();
}
	@org.junit.jupiter.api.Test
	void nearestTrain() {
	        schedule schedule = new schedule(allTrains);
	        schedule.addTrain("name1", "Город 1", "2:22");
	        schedule.addStation("name1" , "Город 2");
	        schedule.addTrain("name2", "Город 2", "8:20");
	        schedule.addTrain("name3", "Город 4", "10:52");
	        schedule.addStation("name3" , "Город 5");
	        schedule.addStation("name3" , "Город 6");
	        schedule.addTrain("name4", "Город 5", "17:47");
	        schedule.addStation("name4" , "Город 4");
	        schedule.addStation("name4" , "Город 6");
	        assertEquals("name1", schedule.nearestTrain("Город 1", "2:23"));
	        assertEquals("name2", schedule.nearestTrain("Город 2" , "9:21"));
	        assertEquals("Станция отсутствует", schedule.nearestTrain("Город 3", "8:52"));
	        assertEquals("name3", schedule.nearestTrain("Город 6" , "9:51"));
	        allTrains.clear();
	}
	
}
