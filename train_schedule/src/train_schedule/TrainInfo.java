package train_schedule;

import java.util.ArrayList;
import java.util.LinkedList;

public class TrainInfo { // ��������������� ����� ��� �������� ����������
	private LinkedList<ArrayList<String>> Info; // ������ �������� ����� , ������ ������������� ������� � �������� �������
	public TrainInfo (LinkedList<ArrayList<String>> Info) {
		ArrayList<String> time = new ArrayList<String>();
		ArrayList<String> stationList = new ArrayList<String>();
		ArrayList<String> endStation = new ArrayList<String>();
		this.Info = Info;
		Info.add(time); 
		Info.add(stationList);
		Info.add(endStation);
		
	}
	public void addTime(String time) {  // ��������� �����
				Info.get(0).add(time);
		}
	
	public void addStations(String station) { // ��������� ������������� �������
			if (!Info.get(1).contains(station)) {
				Info.get(1).add(station);
			}
	}
	
	public void addEndStation(String end) { //��������� �������� �������
			Info.get(2).add(end);
	}
	
	public void removeStation(String station) { // ������� ������������� �������
		if (Info.get(1).contains(station)) {
			Info.get(1).remove(station);
		}
	}
	public String getTime(){ // ������� �����
		return Info.get(0).get(0);
	}
	public String getLastStation(){ //������� ��������� �������
		return Info.get(2).get(0);
	}
	public ArrayList<String> getStations(){ //������� ������ ������������� �������
		return Info.get(1);
	}
}


