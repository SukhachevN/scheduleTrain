package train_schedule;

import java.util.LinkedList;
import java.util.Map;

public class schedule {
	public Map<String, LinkedList<String>> allTrains; //расписание поездов
	public schedule( Map<String, LinkedList<String>> allTrains) {
		this.allTrains = allTrains;
	}
	public String addTrain(String name , String endStation , String time) {  // Добавление нового поезда, необходимо сразу указывать название поезда и конечную станцию
		if (!allTrains.containsKey(name)) { //проверка на наличие в расписании поезда с таким именем
			LinkedList<String> trainStations = new LinkedList<String>(); 
			if (timeConverter(time)!=-1) { // проверяет правильность введенного времени
				trainStations.add(time); // первым элементом будет храниться время, чтобы не пришлось хранить его отдельно
				trainStations.add(endStation); // последний элемент списка, который будет смещаться при добавлении новых станций(объяснение в addStation), и всегда будет последним
				allTrains.put(name , trainStations);
				return "Поезд добавлен";
			}
		}
		return "Поезд не добавлен" ;
	}
	
	public String deleteTrain(String name) { //удаление поезда
		if (allTrains.containsKey(name)) { // проверка на наличие в расписании поезда с таким именем
			allTrains.remove(name);
			return "Поезд удален";
		}
			return "Ошибка, такого поезда не существует";
	}
	public String addStation(String name , String Station) { //добавление промежуточных станций
		if (allTrains.containsKey(name)) { // проверка на наличие в расписании поезда с таким именем
			if (!(allTrains.get(name).contains(Station))){ // проверка на наличие станции в маршруте поезда
				allTrains.get(name).add(1, Station);//добавляет новую станцию, при этом конечная станция остается последней в списке
				return "Станция добавлена";
			}
			else {
				return "Такая станция уже добавлена";
			}
		}
		else {
			return "Ошибка, такого поезда не существует";
		}
	}
	 public String deleteStation(String name, String Station) { // удаление промежуточной станции
		 if (allTrains.containsKey(name)) {// проверка на наличие в расписании поезда с таким именем
			 if (allTrains.get(name).contains(Station)){ // проверка на наличие станции в маршруте поезда
				 if (!Station.equals(allTrains.get(name).getLast())) { // проверка не является ли удаляемая станция конечной
					 allTrains.get(name).remove(Station);
					 return "Станция удалена";
				 }
				 else {
					 return "Ошибка, нельзя удалить конечную станцию";
				 }
			 }
			 else {
				 return "Ошибка, такой станции не существует";
			 }
		 }
		 else {
			 return "Ошибка, такого поезда не существует";
		 }
	 }
	 public int timeConverter(String time) { // преобразует время, и исключает ввод неверного времени
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
			 System.out.println("Неверное время");
			 return -1;
		 }
		 else {
			 return hours*60+min;
		 }
	 }
	 public String nearestTrain(String Station , String time) {
		 int nearestTime = 1441; // больше чем максимальное количество минут в 1 дне, будет использоваться для хранения ближайшего времени
		 String nearestName = "nothing";
		 int currentTime = timeConverter(time);
		 LinkedList<String> allNames = new LinkedList<String>();
		 allNames.addAll(allTrains.keySet());
	     int i = 0;
		 for (LinkedList<String> timeOfTrain : allTrains.values()) {
			 int difference;
			 if (timeConverter(timeOfTrain.getFirst()) - currentTime >= 0) { 
				 difference = timeConverter(timeOfTrain.getFirst()) - currentTime;
			 }
			 else {
				 difference = currentTime - timeConverter(timeOfTrain.getFirst());
			 }
			 if ((allTrains.get(allNames.toArray()[i]).contains(Station) && (difference < nearestTime))) { //проверяет наличие станции в маршруте поезда и ищет минимальную разницу во времени
				 nearestName = (String) allNames.toArray()[i];
				 nearestTime = difference;
			 }
			 i++;
		 }
		 if (nearestName.equals("nothing")) { //если не произошло изменений, значит заданной станции нет ни у одного поезда
			 return "Станция отсутствует";
		 }
		return nearestName;
		 
		 
	 }

}
