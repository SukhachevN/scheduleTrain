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
	        assertEquals("����� ��������", schedule.addTrain("name1", "����� 1", "2:22"));
	        assertEquals("����� �� ��������", schedule.addTrain("name1", "14:44", "����� 2"));
	        assertEquals("����� ��������", schedule.addTrain("name2", "����� 3", "8:52"));
	        assertEquals("����� �� ��������", schedule.addTrain("name5", "����� 888", "99:44"));
	        allTrains.clear();
	}
	@org.junit.jupiter.api.Test
	void deleteTrain() {
        schedule schedule = new schedule(allTrains);
        schedule.addTrain("name1", "����� 1", "2:22");
        assertEquals("����� ������", schedule.deleteTrain("name1"));
        assertEquals("������, ������ ������ �� ����������", schedule.deleteTrain("name1"));
        schedule.addTrain("name2", "����� 2", "8:20");
        assertEquals("����� ������", schedule.deleteTrain("name2"));
        assertEquals("������, ������ ������ �� ����������", schedule.deleteTrain("name5"));
        allTrains.clear();
}
	@org.junit.jupiter.api.Test
	void addStation() {
        schedule schedule = new schedule(allTrains);
        schedule.addTrain("name1", "����� 1", "2:22");
        assertEquals("������� ���������", schedule.addStation("name1" , "����� 2"));
        assertEquals("����� ������� ��� ���������", schedule.addStation("name1" , "����� 2"));
        schedule.addTrain("name2", "����� 2", "8:20");
        assertEquals("������� ���������", schedule.addStation("name2" , "����� 3"));
        assertEquals("����� ������� ��� ���������", schedule.addStation("name2" , "����� 3"));
        assertEquals("������, ������ ������ �� ����������", schedule.addStation("name5" , "����� 111"));
        allTrains.clear();
}
	@org.junit.jupiter.api.Test
	void deleteStation() {
        schedule schedule = new schedule(allTrains);
        schedule.addTrain("name1", "����� 1", "2:22");
        schedule.addStation("name1" , "����� 2");
        assertEquals("������� �������", schedule.deleteStation("name1" , "����� 2"));
        assertEquals("������, ������ ������ �� ����������", schedule.deleteStation("name2" , "����� 21"));
        schedule.addTrain("name2", "����� 2", "8:20");
        assertEquals("������, ������ ������� �������� �������", schedule.deleteStation("name2" , "����� 2"));
        assertEquals("������, ����� ������� �� ����������", schedule.deleteStation("name2" , "����� 343"));
        allTrains.clear();
}
	@org.junit.jupiter.api.Test
	void nearestTrain() {
	        schedule schedule = new schedule(allTrains);
	        schedule.addTrain("name1", "����� 1", "2:22");
	        schedule.addStation("name1" , "����� 2");
	        schedule.addTrain("name2", "����� 2", "8:20");
	        schedule.addTrain("name3", "����� 4", "10:52");
	        schedule.addStation("name3" , "����� 5");
	        schedule.addStation("name3" , "����� 6");
	        schedule.addTrain("name4", "����� 5", "17:47");
	        schedule.addStation("name4" , "����� 4");
	        schedule.addStation("name4" , "����� 6");
	        assertEquals("name1", schedule.nearestTrain("����� 1", "2:23"));
	        assertEquals("name2", schedule.nearestTrain("����� 2" , "9:21"));
	        assertEquals("������� �����������", schedule.nearestTrain("����� 3", "8:52"));
	        assertEquals("name3", schedule.nearestTrain("����� 6" , "9:51"));
	        allTrains.clear();
	}
	
}
