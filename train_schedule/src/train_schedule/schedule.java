package train_schedule;

import java.util.LinkedList;
import java.util.Map;

public class schedule {
	public Map<String, LinkedList<String>> allTrains; //���������� �������
	public schedule( Map<String, LinkedList<String>> allTrains) {
		this.allTrains = allTrains;
	}
	public String addTrain(String name , String endStation , String time) {  // ���������� ������ ������, ���������� ����� ��������� �������� ������ � �������� �������
		if (!allTrains.containsKey(name)) { //�������� �� ������� � ���������� ������ � ����� ������
			LinkedList<String> trainStations = new LinkedList<String>(); 
			if (timeConverter(time)!=-1) { // ��������� ������������ ���������� �������
				trainStations.add(time); // ������ ��������� ����� ��������� �����, ����� �� �������� ������� ��� ��������
				trainStations.add(endStation); // ��������� ������� ������, ������� ����� ��������� ��� ���������� ����� �������(���������� � addStation), � ������ ����� ���������
				allTrains.put(name , trainStations);
				return "����� ��������";
			}
		}
		return "����� �� ��������" ;
	}
	
	public String deleteTrain(String name) { //�������� ������
		if (allTrains.containsKey(name)) { // �������� �� ������� � ���������� ������ � ����� ������
			allTrains.remove(name);
			return "����� ������";
		}
			return "������, ������ ������ �� ����������";
	}
	public String addStation(String name , String Station) { //���������� ������������� �������
		if (allTrains.containsKey(name)) { // �������� �� ������� � ���������� ������ � ����� ������
			if (!(allTrains.get(name).contains(Station))){ // �������� �� ������� ������� � �������� ������
				allTrains.get(name).add(1, Station);//��������� ����� �������, ��� ���� �������� ������� �������� ��������� � ������
				return "������� ���������";
			}
			else {
				return "����� ������� ��� ���������";
			}
		}
		else {
			return "������, ������ ������ �� ����������";
		}
	}
	 public String deleteStation(String name, String Station) { // �������� ������������� �������
		 if (allTrains.containsKey(name)) {// �������� �� ������� � ���������� ������ � ����� ������
			 if (allTrains.get(name).contains(Station)){ // �������� �� ������� ������� � �������� ������
				 if (!Station.equals(allTrains.get(name).getLast())) { // �������� �� �������� �� ��������� ������� ��������
					 allTrains.get(name).remove(Station);
					 return "������� �������";
				 }
				 else {
					 return "������, ������ ������� �������� �������";
				 }
			 }
			 else {
				 return "������, ����� ������� �� ����������";
			 }
		 }
		 else {
			 return "������, ������ ������ �� ����������";
		 }
	 }
	 public int timeConverter(String time) { // ����������� ����� � ������, � ��������� ���� ��������� �������
		 int hours = -1;
		 int min = -1;
		 boolean flag = true;
		 for (String part : time.split(":")) {
			 if (flag) {
			  hours = Integer.parseInt(part);
			  flag = false;
			 }
			 else {
			  min = Integer.parseInt(part);
			 }
		 }
		 if (!(hours >= 0 && hours <= 23 ) || !(min>= 0 && min<= 59)) {
			 System.out.println("�������� �����");
			 return -1;
		 }
		 else {
			 return hours*60+min;
		 }
	 }
	 public String nearestTrain(String Station , String time) { //������� ��� ���������� ������ �� �������� ������� � �������� ������� �������
		 int nearestTime = 1441; // ������ ��� ������������ ���������� ����� � 1 ���, ����� �������������� ��� �������� ���������� �������
		 String nearestName = "nothing";
		 int currentTime = timeConverter(time);
		 LinkedList<String> allNames = new LinkedList<String>();
		 allNames.addAll(allTrains.keySet());
	     int i = 0;
		 for (LinkedList<String> timeOfTrain : allTrains.values()) {
			 int difference; // ����� ������� ����������� ������� �� �������
			 if (timeConverter(timeOfTrain.getFirst()) - currentTime >= 0) { 
				 difference = timeConverter(timeOfTrain.getFirst()) - currentTime;
			 }
			 else {
				 difference = 1440 - (currentTime - timeConverter(timeOfTrain.getFirst()));// ���� ������� ����� ������� ������� ������, ������ ������� �� ��� ����� ����� ������ �� ��������� ����
			 }
			 if ((allTrains.get(allNames.toArray()[i]).contains(Station) && (difference < nearestTime))) { //��������� ������� ������� � �������� ������ � ���� ����������� ������� �� �������
				 nearestName = (String) allNames.toArray()[i];
				 nearestTime = difference;
			 }
			 i++;
		 }
		 if (nearestName.equals("nothing")) { //���� �� ��������� ���������, ������ �������� ������� ��� �� � ������ ������
			 return "������� �����������";
		 }
		return nearestName;
		 
		 
	 }

}
