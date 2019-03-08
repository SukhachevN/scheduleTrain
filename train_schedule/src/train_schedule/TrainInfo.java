package train_schedule;

import java.util.ArrayList;
import java.util.LinkedList;

public class TrainInfo { // вспомогательный класс для хранения информации
	private LinkedList<ArrayList<String>> Info; // хранит отдельно время , список промежуточных станций и конечную станцию
	public TrainInfo (LinkedList<ArrayList<String>> Info) {
		ArrayList<String> time = new ArrayList<String>();
		ArrayList<String> stationList = new ArrayList<String>();
		ArrayList<String> endStation = new ArrayList<String>();
		this.Info = Info;
		Info.add(time); 
		Info.add(stationList);
		Info.add(endStation);
		
	}
	public void addTime(String time) {  // добавляет время
				Info.get(0).add(time);
		}
	
	public void addStations(String station) { // добавляет промежуточные станции
			if (!Info.get(1).contains(station)) {
				Info.get(1).add(station);
			}
	}
	
	public void addEndStation(String end) { //добавляет конечную станцию
			Info.get(2).add(end);
	}
	
	public void removeStation(String station) { // удаляет промежуточную станцию
		if (Info.get(1).contains(station)) {
			Info.get(1).remove(station);
		}
	}
	public String getTime(){ // выводит время
		return Info.get(0).get(0);
	}
	public String getLastStation(){ //выводит последнюю станцию
		return Info.get(2).get(0);
	}
	public ArrayList<String> getStations(){ //выводит список промежуточных станций
		return Info.get(1);
	}
}


