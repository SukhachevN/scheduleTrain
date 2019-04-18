package train_schedule;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Schedule {
	private Map<String, TrainInfo> allTrains; // расписание поездов

	public Schedule(Map<String, TrainInfo> allTrains) {
		this.allTrains = new HashMap<String, TrainInfo>();
	}

	public boolean addTrain(String name, String endStation, String time) { 
		if (!allTrains.containsKey(name)) { // проверка на наличие в расписании поезда с таким именем
			ArrayList<String> data = new ArrayList<String>();
			TrainInfo trainInfo = new TrainInfo(null, data, null);
			if (timeConverter(time) != null) { // проверяет правильность введенного времени
				trainInfo.addTime(timeConverter(time));
				trainInfo.addEndStation(endStation);
				allTrains.put(name, trainInfo);
				return true;
			}
		}
		return false;
	}

	public boolean deleteTrain(String name) { // удаление поезда
		if (allTrains.containsKey(name)) { // проверка на наличие в расписании поезда с таким именем
			allTrains.remove(name);
			return true;
		}
		return false;
	}

	public boolean addStation(String name, String Station) { // добавление промежуточных станций
		if (allTrains.containsKey(name)) { // проверка на наличие в расписании поезда с таким именем
			TrainInfo info = allTrains.get(name);
			if (!(info.getStations().contains(Station) && !Station.equals(info.getLastStation()))) { 
				allTrains.get(name).addStations(Station);
				return true;
			}
		}
		return false;
	}

	public boolean deleteStation(String name, String Station) { // удаление промежуточной станции
		if (allTrains.containsKey(name)) {// проверка на наличие в расписании поезда с таким именем
			TrainInfo info = allTrains.get(name);
			if (info.getStations().contains(Station)) { // проверка на наличие станции в маршруте поезда
				allTrains.get(name).removeStation(Station);
				return true;
			}
		}
		return false;
	}

	public Date timeConverter(String time) {
		DateFormat data = new SimpleDateFormat("dd.MM.yyyy H:mm:ss");
		try {
			Date date = new Date(data.parse(time).getTime());
			return date;
		} catch (ParseException e) {
			return null;
		}
	}

	public String nearestTrain(String Station, String time) {
		Date nearestTime = timeConverter("31.12.9999 23:59:00");
		String nearestName = null;
		Date currentTime = timeConverter(time);
		Date trainTime = null;
		for (Map.Entry train : allTrains.entrySet()) {
			TrainInfo info = (TrainInfo) train.getValue();
			if (currentTime.before(info.getTime())) { 
				trainTime = info.getTime();
				if ((trainTime.before(nearestTime)) && trainTime.after(currentTime)) {
					if ((info.getStations().contains(Station) || Station.equals(info.getLastStation()))) { 
						nearestName = (String) train.getKey();
						nearestTime = trainTime;
					}
				}
			}
		}
		return nearestName;

	}

}
