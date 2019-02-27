package train_schedule;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

public class schedule {
	public Map<String, LinkedList<String>> allTrains; //расписание поездов им€
	public Map<String ,Integer> trainsTime;//хранит врем€ дл€ каждого поезд
	public schedule( Map<String, LinkedList<String>> allTrains) {
		this.allTrains = allTrains;
	}
	public String addTrain(String name , String endStation , String time) {  // ƒобавление нового поезда, необходимо сразу указывать название поезда и конечную станцию
		if (!allTrains.containsKey(name)) { //проверка на наличие в расписании поезда с таким именем
			LinkedList<String> trainStations = new LinkedList<String>();
			if (timeConverter(time)!=-1) { // провер€ет правильность введенного времени
				trainStations.add(time); // первым элементом будет хранитьс€ врем€, чтобы не пришлось хранить его отдельно
				trainStations.add(endStation); // последний элемент списка, который будет смещатьс€ при добавлении новых станций(объ€снение в addStation), и всегда будет последним
				allTrains.put(name , trainStations);
				return "ѕоезд добавлен";
			}
		}
		return "ѕоезд не добавлен" ;
	}
	
	public String deleteTrain(String name) { //удаление поезда
		if (allTrains.containsKey(name)) { // проверка на наличие в расписании поезда с таким именем
			allTrains.remove(name);
			return "ѕоезд удален";
		}
			return "ќшибка, такого поезда не существует";
	}
	public void addStation(String name , String Station) { //добавление промежуточных станций
		if (allTrains.containsKey(name)) {
			if (!(allTrains.get(name).contains(Station))){
				allTrains.get(name).add(0, Station);//добавл€ет новую станцию, при этом конечна€ станци€ остаетс€ последней в списке
				System.out.println("—танци€ добавлена");
			}
			else {
				System.out.println("“ака€ станци€ уже добавлена");
			}
		}
		else {
			System.out.println("ќшибка, такого поезда не существует");
		}
	}
	 public void deleteStation(String name, String Station) { // удаление промежуточной станции
		 if (allTrains.containsKey(name)) {
			 if (allTrains.get(name).contains(Station)){
				 if (!Station.equals(allTrains.get(name).getLast())) { // проверка не €вл€етс€ ли удал€ема€ станци€ конечной
					 allTrains.get(name).remove(Station);
				 }
				 else {
					 System.out.println("ќшибка, нельз€ удалить конечную станцию");
				 }
			 }
			 else {
				 System.out.println("ќшибка, такой станции не существует");
			 }
		 }
		 else {
			 System.out.println("ќшибка, такого поезда не существует");
		 }
	 }
	 public int timeConverter(String time) { // преобразует врем€, и исключает ввод неверного времени
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
			 System.out.println("Ќеверное врем€");
			 return -1;
		 }
		 else {
			 return hours*60+min;
		 }
	 }
	 public String nearestTrain(String Station , String time) {
		 int nearestTime = 1441; // больше чем максимальное количество минут в 1 дне, будет использоватьс€ дл€ хранени€ ближайшего времени
		 String nearestName = "nothing";
		 int currentTime = timeConverter(time);
		 LinkedList<String> allNames = new LinkedList<String>();
		 ArrayList<LinkedList<String>> allValues = new ArrayList<LinkedList<String>>();
		 allNames.addAll(allTrains.keySet());
	     allValues.addAll(allTrains.values());
	     int i = 0;
		 for (LinkedList<String> timeOfTrain : allValues) {
			 int difference;
			 if (timeConverter(timeOfTrain.getFirst()) - currentTime >= 0) {
				 difference = timeConverter(timeOfTrain.getFirst()) - currentTime;
			 }
			 else {
				 difference = currentTime - timeConverter(timeOfTrain.getFirst());
			 }
			 if ((allTrains.get(allNames.toArray()[i]).contains(Station) && (difference < nearestTime))) { //провер€ет наличие станции в маршруте поезда и ищет минимальную разницу во времени
				 nearestName = (String) allNames.toArray()[i];
				 i++;
				 nearestTime = difference;
			 }
		 }
		return nearestName;
		 
		 
	 }

}
