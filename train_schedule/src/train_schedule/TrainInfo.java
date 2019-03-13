package train_schedule;

import java.util.ArrayList;
import java.util.Date;

public class TrainInfo { // ��������������� ����� ��� �������� ����������
	private Date timeTrain;
	private ArrayList<String> stationList;
	private String endStation;
	public TrainInfo (Date timeTrain , ArrayList<String> stationList , String endStation) {
		this.timeTrain = timeTrain;
		this.stationList = stationList;
		this.endStation = endStation;
	}

	public void addTime(Date time) { // 
		timeTrain = time;
}
	public void addStations(String station) { // ��������� ������������� �������
			if (!stationList.contains(station)) {
				stationList.add(station);
			}
	}
	
	public void addEndStation(String end) { //��������� �������� �������
			endStation = end;
	}
	
	public void removeStation(String station) { // ������� ������������� �������
		if (stationList.contains(station)) {
			stationList.remove(station);
		}
	}
	public Date getTime(){ // ������� �����
		return timeTrain;
	}
	public String getLastStation(){ //������� ��������� �������
		return endStation;
	}
	public ArrayList<String> getStations(){ //������� ������ ������������� �������
		return stationList;
	}
}


