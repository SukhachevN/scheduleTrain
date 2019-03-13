package train_schedule;

import java.util.ArrayList;
import java.util.Date;

public class TrainInfo { // вспомогательный класс для хранения информации
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
	public void addStations(String station) { // добавляет промежуточные станции
			if (!stationList.contains(station)) {
				stationList.add(station);
			}
	}
	
	public void addEndStation(String end) { //добавляет конечную станцию
			endStation = end;
	}
	
	public void removeStation(String station) { // удаляет промежуточную станцию
		if (stationList.contains(station)) {
			stationList.remove(station);
		}
	}
	public Date getTime(){ // выводит время
		return timeTrain;
	}
	public String getLastStation(){ //выводит последнюю станцию
		return endStation;
	}
	public ArrayList<String> getStations(){ //выводит список промежуточных станций
		return stationList;
	}
}


