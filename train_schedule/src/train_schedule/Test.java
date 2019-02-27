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
	
}
