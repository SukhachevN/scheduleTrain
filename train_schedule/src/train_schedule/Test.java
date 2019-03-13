package train_schedule;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

class Test {
	private Map<String, TrainInfo> allTrains = new LinkedHashMap<String, TrainInfo>();
	@org.junit.jupiter.api.Test
	void addTrain() {
	        Schedule schedule = new Schedule(allTrains);
	            assertEquals(true, schedule.addTrain("name1", "Город 1", "21.02.2019 13:35:00"));
	            assertEquals(false, schedule.addTrain("name1", "14:44", "Город 2"));
	            assertEquals(true, schedule.addTrain("name2", "Город 3", "22.02.2019 7:54:43"));
	            assertEquals(false, schedule.addTrain("name5", "Город 888", "1"));
	        allTrains.clear();
	}
	@org.junit.jupiter.api.Test
	void deleteTrain() {
        Schedule schedule = new Schedule(allTrains);
        schedule.addTrain("name1", "Город 1", "01.01.2019 2:22:00");
        schedule.addTrain("name2", "Город 2", "01.01.2019 8:54:00");
                assertEquals(true, schedule.deleteTrain("name1"));
                assertEquals(false, schedule.deleteTrain("name1"));
                assertEquals(true, schedule.deleteTrain("name2"));
                assertEquals(false, schedule.deleteTrain("name5"));
        allTrains.clear();
}
	@org.junit.jupiter.api.Test
	void addStation() {
		Schedule schedule = new Schedule(allTrains);
        schedule.addTrain("name1", "Город 1", "01.01.2019 2:22:00");
        schedule.addTrain("name2", "Город 2", "01.01.2019 8:54:00");
                assertEquals(true, schedule.addStation("name1" , "Город 2"));
                assertEquals(false, schedule.addStation("name1" , "Город 2"));
                assertEquals(true, schedule.addStation("name2" , "Город 3"));
                assertEquals(false, schedule.addStation("name2" , "Город 3"));
                assertEquals(false, schedule.addStation("name5" , "Город 111"));
        allTrains.clear();
        
}
	@org.junit.jupiter.api.Test
	void deleteStation() {
        Schedule schedule = new Schedule(allTrains);
        schedule.addTrain("name1", "Город 1", "01.01.2019 2:22:00");
        schedule.addStation("name1" , "Город 2");
        schedule.addTrain("name2", "Город 2", "01.01.2019 8:54:00");
                assertEquals(true, schedule.deleteStation("name1" , "Город 2"));
                assertEquals(false, schedule.deleteStation("name2" , "Город 21"));
                assertEquals(false, schedule.deleteStation("name2" , "Город 2"));
                assertEquals(false, schedule.deleteStation("name2" , "Город 343"));
        allTrains.clear();
}
	@org.junit.jupiter.api.Test
	void nearestTrain() {
	        Schedule schedule = new Schedule(allTrains);
	        schedule.addTrain("name1", "Город 1", "01.01.2019 02:22:00");
	        schedule.addStation("name1" , "Город 2");
	        schedule.addTrain("name2", "Город 2", "04.02.2019 08:54:00");
	        schedule.addTrain("name3", "Город 4", "01.01.2019 10:52:00");
	        schedule.addStation("name3" , "Город 5");
	        schedule.addStation("name3" , "Город 6");
	        schedule.addTrain("name4", "Город 5", "01.01.2019 17:47:00");
	        schedule.addStation("name4" , "Город 4");
	        schedule.addStation("name4" , "Город 6");
	            assertEquals("name1", schedule.nearestTrain("Город 1", "01.01.2019 02:21:00"));
	            assertEquals("name2", schedule.nearestTrain("Город 2" , "04.02.2019 03:21:00"));
	            assertEquals(null, schedule.nearestTrain("Город 3", "09.11.2019 8:52:00"));
	            assertEquals("name3", schedule.nearestTrain("Город 6" , "01.01.2019 9:51:00"));
	        allTrains.clear();
	}
}
