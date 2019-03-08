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
	private Map<String, TrainInfo> allTrains; //расписание поездов
	public schedule( Map<String, TrainInfo> allTrains) {
		this.allTrains = new HashMap<String,TrainInfo>() ;
	}
	public boolean addTrain(String name , String endStation , String time) {  // Добавление нового поезда, необходимо сразу указывать название поезда и конечную станцию
		if (!allTrains.containsKey(name)) { //проверка на наличие в расписании поезда с таким именем
			LinkedList<ArrayList<String>> data = new LinkedList<ArrayList<String>>();
			TrainInfo trainInfo = new TrainInfo(data); 
			if (timeConverter(time) != null) { // проверяет правильность введенного времени
				trainInfo.addTime(time);
                trainInfo.addEndStation(endStation);
				allTrains.put(name , trainInfo);
				return true;
			}
		}
		return false;
	}
	
	public boolean deleteTrain(String name) { //удаление поезда
		if (allTrains.containsKey(name)) { // проверка на наличие в расписании поезда с таким именем
			allTrains.remove(name);
			return true;
		}
			return false;
	}
	public boolean addStation(String name , String Station) { //добавление промежуточных станций
		if (allTrains.containsKey(name)) { // проверка на наличие в расписании поезда с таким именем
			if (!(allTrains.get(name).getStations().contains(Station) && !Station.equals(allTrains.get(name).getLastStation()))){ // проверка на наличие станции в маршруте поезда
				allTrains.get(name).addStations(Station);//добавляет новую станцию, при этом конечная станция остается последней в списке
				return true;
			}
		}
			return false;
	}
	 public boolean deleteStation(String name, String Station) { // удаление промежуточной станции
		 if (allTrains.containsKey(name)) {// проверка на наличие в расписании поезда с таким именем
			 if (allTrains.get(name).getStations().contains(Station)){ // проверка на наличие станции в маршруте поезда
					 allTrains.get(name).removeStation(Station);
					 return true;
			 }
		 }
			 return false;
	 }
	 public Date timeConverter(String time) {//исключает ввод неверного времени и преобразует в формат даты
		 DateFormat data = new SimpleDateFormat("dd.MM.yyyy H:mm:ss");
		 try {
			Date date = new Date(data.parse(time).getTime());
			return date;
		} catch (ParseException e) {
			return null;
		}
	 }
	 public String nearestTrain(String Station , String time) { //находит имя ближайшего поезда по заданной станции и текущему времени времени
		 Date nearestTime = timeConverter("31.12.9999 23:59:00") ; 
		 String nearestName = null;
		 Date currentTime = timeConverter(time);
		 Date trainTime = null;
		 for (String name : allTrains.keySet()) {
			 if (currentTime.before(timeConverter(allTrains.get(name).getTime()))) { //запоминает и преобразует время, только если время поезда позднее текущего времени
				 trainTime = (timeConverter(allTrains.get(name).getTime()));
				 if ((trainTime.before(nearestTime)) && trainTime.after(currentTime)) {
					 if ((allTrains.get(name).getStations().contains(Station) || 
							 Station.equals(allTrains.get(name).getLastStation()))) { //проверяет наличие станции в маршруте поезда и ищет минимальную разницу во времени
							 nearestName = name;
							 nearestTime = trainTime;
						 }
				 }
			 }
		 }
		return nearestName;
		 
		 
	 }

}
