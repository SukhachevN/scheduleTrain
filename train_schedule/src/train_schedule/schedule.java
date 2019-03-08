package train_schedule;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class schedule {
	private Map<String, TrainInfo> allTrains; //���������� �������
	public schedule( Map<String, TrainInfo> allTrains) {
		this.allTrains = new HashMap<String,TrainInfo>() ;
	}
	public boolean addTrain(String name , String endStation , String time) {  // ���������� ������ ������, ���������� ����� ��������� �������� ������ � �������� �������
		if (!allTrains.containsKey(name)) { //�������� �� ������� � ���������� ������ � ����� ������
			LinkedList<ArrayList<String>> data = new LinkedList<ArrayList<String>>();
			TrainInfo trainInfo = new TrainInfo(data); 
			if (timeConverter(time) != null) { // ��������� ������������ ���������� �������
				trainInfo.addTime(time);
                trainInfo.addEndStation(endStation);
				allTrains.put(name , trainInfo);
				return true;
			}
		}
		return false;
	}
	
	public boolean deleteTrain(String name) { //�������� ������
		if (allTrains.containsKey(name)) { // �������� �� ������� � ���������� ������ � ����� ������
			allTrains.remove(name);
			return true;
		}
			return false;
	}
	public boolean addStation(String name , String Station) { //���������� ������������� �������
		if (allTrains.containsKey(name)) { // �������� �� ������� � ���������� ������ � ����� ������
			if (!(allTrains.get(name).getStations().contains(Station) && !Station.equals(allTrains.get(name).getLastStation()))){ // �������� �� ������� ������� � �������� ������
				allTrains.get(name).addStations(Station);//��������� ����� �������, ��� ���� �������� ������� �������� ��������� � ������
				return true;
			}
		}
			return false;
	}
	 public boolean deleteStation(String name, String Station) { // �������� ������������� �������
		 if (allTrains.containsKey(name)) {// �������� �� ������� � ���������� ������ � ����� ������
			 if (allTrains.get(name).getStations().contains(Station)){ // �������� �� ������� ������� � �������� ������
					 allTrains.get(name).removeStation(Station);
					 return true;
			 }
		 }
			 return false;
	 }
	 public Date timeConverter(String time) {//��������� ���� ��������� ������� � ����������� � ������ ����
		 DateFormat data = new SimpleDateFormat("dd.MM.yyyy H:mm:ss");
		 try {
			Date date = new Date(data.parse(time).getTime());
			return date;
		} catch (ParseException e) {
			return null;
		}
	 }
	 public String nearestTrain(String Station , String time) { //������� ��� ���������� ������ �� �������� ������� � �������� ������� �������
		 Date nearestTime = timeConverter("31.12.9999 23:59:00") ; 
		 String nearestName = null;
		 Date currentTime = timeConverter(time);
		 Date trainTime = null;
		 for (String name : allTrains.keySet()) {
			 if (currentTime.before(timeConverter(allTrains.get(name).getTime()))) { //���������� � ����������� �����, ������ ���� ����� ������ ������� �������� �������
				 trainTime = (timeConverter(allTrains.get(name).getTime()));
				 if ((trainTime.before(nearestTime)) && trainTime.after(currentTime)) {
					 if ((allTrains.get(name).getStations().contains(Station) || 
							 Station.equals(allTrains.get(name).getLastStation()))) { //��������� ������� ������� � �������� ������ � ���� ����������� ������� �� �������
							 nearestName = name;
							 nearestTime = trainTime;
						 }
				 }
			 }
		 }
		return nearestName;
		 
		 
	 }

}
