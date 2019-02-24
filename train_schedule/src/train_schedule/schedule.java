package train_schedule;

import java.util.LinkedList;
import java.util.Map;

public class schedule {
	public Map<String, LinkedList<String>> allTrains; //���������� ������� ���
	public schedule( Map<String, LinkedList<String>> allTrains) {
		this.allTrains = allTrains;
	}
	public void addTrain(String name , String endStation , String time) {  // ���������� ������ ������, ���������� ����� ��������� �������� ������ � �������� �������
		if (!allTrains.containsKey(name)) {
			LinkedList<String> trainInfo = new LinkedList<String>();
			trainInfo.add(time);// ������ ������� ������
			trainInfo.add(endStation); // ��������� ������� ������, ������� ����� ��������� ��� ���������� ����� �������(���������� � addStation), � ������ ����� ���������
			allTrains.put(name , trainInfo);
			System.out.println("����� ��������");
		}
		else {
			System.out.println("������, ����� ��� ����������");
		}
	}
	
	public void deleteTrain(String name) { //�������� ������
		if (allTrains.containsKey(name)) {
			allTrains.remove(name);
			System.out.println("����� ������");
		}
		else {
			System.out.println("������, ������ ������ �� ����������");
		}
		
	}
	public void addStation(String name , String Station) { //���������� ������������� �������
		if (allTrains.containsKey(name)) {
			if (!(allTrains.get(name).contains(Station))){
				allTrains.get(name).add(1, Station);//��������� ����� �������, ��� ���� �������� ������� �������� ��������� � ������
				System.out.println("������� ���������");
			}
			else {
				System.out.println("����� ������� ��� ���������");
			}
		}
		else {
			System.out.println("������, ������ ������ �� ����������");
		}
	}
	 public void deleteStation(String name, String Station) { // �������� ������������� �������
		 if (allTrains.containsKey(name)) {
			 if (allTrains.get(name).contains(Station)){
				 if (!Station.equals(allTrains.get(name).getLast())) { // �������� �� �������� �� ��������� ������� ��������
					 allTrains.get(name).remove(Station);
				 }
				 else {
					 System.out.println("������, ������ ������� �������� �������");
				 }
			 }
			 else {
				 System.out.println("������, ����� ������� �� ����������");
			 }
		 }
		 else {
			 System.out.println("������, ������ ������ �� ����������");
		 }
	 }

}
