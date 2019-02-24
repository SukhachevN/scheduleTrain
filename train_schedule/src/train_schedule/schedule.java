package train_schedule;

import java.util.LinkedList;
import java.util.Map;

public class schedule {
	public Map<String, LinkedList<String>> allTrains; //расписание поездов имя
	public schedule( Map<String, LinkedList<String>> allTrains) {
		this.allTrains = allTrains;
	}
	public void addTrain(String name , String endStation , String time) {  // Добавление нового поезда, необходимо сразу указывать название поезда и конечную станцию
		if (!allTrains.containsKey(name)) {
			LinkedList<String> trainInfo = new LinkedList<String>();
			trainInfo.add(time);// первый элемент списка
			trainInfo.add(endStation); // последний элемент списка, который будет смещаться при добавлении новых станций(объяснение в addStation), и всегда будет последним
			allTrains.put(name , trainInfo);
			System.out.println("Поезд добавлен");
		}
		else {
			System.out.println("Ошибка, поезд уже существует");
		}
	}
	
	public void deleteTrain(String name) { //удаление поезда
		if (allTrains.containsKey(name)) {
			allTrains.remove(name);
			System.out.println("Поезд удален");
		}
		else {
			System.out.println("Ошибка, такого поезда не существует");
		}
		
	}
	public void addStation(String name , String Station) { //добавление промежуточных станций
		if (allTrains.containsKey(name)) {
			if (!(allTrains.get(name).contains(Station))){
				allTrains.get(name).add(1, Station);//добавляет новую станцию, при этом конечная станция остается последней в списке
				System.out.println("Станция добавлена");
			}
			else {
				System.out.println("Такая станция уже добавлена");
			}
		}
		else {
			System.out.println("Ошибка, такого поезда не существует");
		}
	}
	 public void deleteStation(String name, String Station) { // удаление промежуточной станции
		 if (allTrains.containsKey(name)) {
			 if (allTrains.get(name).contains(Station)){
				 if (!Station.equals(allTrains.get(name).getLast())) { // проверка не является ли удаляемая станция конечной
					 allTrains.get(name).remove(Station);
				 }
				 else {
					 System.out.println("Ошибка, нельзя удалить конечную станцию");
				 }
			 }
			 else {
				 System.out.println("Ошибка, такой станции не существует");
			 }
		 }
		 else {
			 System.out.println("Ошибка, такого поезда не существует");
		 }
	 }

}
