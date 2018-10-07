package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import info.ostaszewski.controller.JDBCController;
import info.ostaszewski.controller.CSVFileManager;
import info.ostaszewski.controller.XMLFileManager;
import info.ostaszewski.model.Request;

@SpringBootApplication
public class BootCampZadanieApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootCampZadanieApplication.class, args);
		// ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");


		
		menu();
		
	}

	private static void menu() {
		
		JDBCController jdbcController = new JDBCController();
		jdbcController.createTable();
		CSVFileManager readCSVFile = new CSVFileManager();
		XMLFileManager readXMLFile = new XMLFileManager();

		List<Request> list = readXMLFile.getData("file.xml");
		jdbcController.insertToTable(list);
		list = readCSVFile.getData("plik.csv");
		jdbcController.insertToTable(list);
		List<Request> listOfRequest;
		
		Scanner input = new Scanner(System.in);
		boolean mainLoop = true;
		System.out.println("Program do obsługi zamowień\n");
		int choice;
		while (mainLoop) {
			System.out.println("Wybierz opcje i wprowadź numer\n");
			System.out.print("1.) Ilość zamówień łącznie \n");
			System.out.print("2.) Ilość zamówień do klienta o wskazanym identyfikatorze\n");
			System.out.print("3.) Łączna kwota zamówień\n");
			System.out.print("4.) Łączna kwota zamówień do klienta o wskazanym identyfikatorze\n");
			System.out.print("5.) Lista wszystkich zamówień\n");
			System.out.print("6.) Lista wszystkich zamówień do klienta o wskazanym identyfikatorze\n");
			System.out.print("7.) Średnia wartość zamówienia\n");
			System.out.print("8.) Średnia wartość zamówienia do klienta o wskazanym identyfikatorze\n");
			System.out.print("9.) Wyjście\n");

			System.out.print("\nWpisz numer z listy: ");

			choice = input.nextInt();
			int choiceFromSmallMenu;
			switch (choice) {
			case 1:
			
				choiceFromSmallMenu =smallMenu();
				if (choiceFromSmallMenu==1)System.out.println("Ilość zamówień łącznie: "+ jdbcController.getNumberOfOrders()+"\n");
				if(choiceFromSmallMenu==2)readCSVFile.writeData("1.) Ilość zamówień łącznie.csv", Long.toString(jdbcController.getNumberOfOrders()));
				break;

			case 2:
				choiceFromSmallMenu =smallMenu();
				if (choiceFromSmallMenu==1)System.out.println("Ilość zamówień do klienta o wskazanym identyfikatorze: "+ jdbcController.getNumberOfOrders(getIdMenu())+"\n");
				if(choiceFromSmallMenu==2)readCSVFile.writeData("2.) Ilość zamówień do klienta o wskazanym identyfikatorze.csv", Long.toString(jdbcController.getNumberOfOrders(getIdMenu())));
				break;
			case 3:
				choiceFromSmallMenu =smallMenu();
				if (choiceFromSmallMenu==1)System.out.println("Łączna kwota zamówień: "+ jdbcController.getTotalAmountOfOrders()+"\n");
				if(choiceFromSmallMenu==2)readCSVFile.writeData("3.) Łączna kwota zamówień.csv", Long.toString(jdbcController.getTotalAmountOfOrders()));;
				break;
			case 4:
				choiceFromSmallMenu =smallMenu();
				if (choiceFromSmallMenu==1)System.out.println("Łączna kwota zamówień do klienta o wskazanym identyfikatorze: "+ jdbcController.getTotalAmountOfOrders(getIdMenu())+"\n");
				if(choiceFromSmallMenu==2)readCSVFile.writeData("4.) Łączna kwota zamówień do klienta o wskazanym identyfikatorze.csv", Long.toString(jdbcController.getTotalAmountOfOrders(getIdMenu())));
				break;
			case 5:
				choiceFromSmallMenu =smallMenu();
				listOfRequest = jdbcController.getListOfOrders();
				if (choiceFromSmallMenu==1) {
					System.out.println("Lista wszystkich zamówień: \n");
					
					for (int i=0;i<listOfRequest.size();i++) {
						 System.out.println(listOfRequest.get(i)); }
				}
				if(choiceFromSmallMenu==2)readCSVFile.writeData("5.) Lista wszystkich zamówień.csv", listOfRequest);
				break;
			case 6:
				choiceFromSmallMenu =smallMenu();
				listOfRequest = jdbcController.getListOfOrders(getIdMenu());
				if (choiceFromSmallMenu==1) {
					System.out.println("Lista wszystkich zamówień do klienta o wskazanym identyfikatorze: \n");
					
					for (int i=0;i<listOfRequest.size();i++) {
						 System.out.println(listOfRequest.get(i)); }
				}
				if(choiceFromSmallMenu==2)readCSVFile.writeData("6.) Lista wszystkich zamówień do klienta o wskazanym identyfikatorze.csv", listOfRequest);
				break;
			case 7:
				choiceFromSmallMenu =smallMenu();
				if (choiceFromSmallMenu==1)System.out.println("Średnia wartość zamówienia: "+ jdbcController.getAvarageValueOfOrders());
				if(choiceFromSmallMenu==2)readCSVFile.writeData("7.) Średnia wartość zamówienia.csv", Double.toString(jdbcController.getAvarageValueOfOrders()));
				break;

			case 8:
				choiceFromSmallMenu =smallMenu();
				if (choiceFromSmallMenu==1)System.out.println("Średnia wartość zamówienia do klienta o wskazanym identyfikatorze: "+ jdbcController.getAvarageValueOfOrders(getIdMenu()));
				if(choiceFromSmallMenu==2)readCSVFile.writeData("8.) Średnia wartość zamówienia do klienta o wskazanym identyfikatorze.csv", Double.toString(jdbcController.getAvarageValueOfOrders(getIdMenu())));
				break;
			case 9:
				mainLoop = false;
				System.out.println("zamknieto aplikacje");
				System.exit(0);
				input.close();
				break;
			default:
				System.out.println("Nie rozpoznany wybór");
				break;
			
			}

		}
	}

	private static int smallMenu() {
		Scanner input = new Scanner(System.in);
		boolean mainLoop = true;
		int choiceResult = 0;
		int choice;
		while (mainLoop) {
			System.out.println("Wybierz opcje i wprowadź numer\n");
			System.out.print("1.) Wygeneruj raport na ekranie \n");
			System.out.print("2.) Wygeneruj raport do pliku csv\n");
			System.out.print("3.) Powrót\n");

			System.out.print("\nWpisz numer z listy: ");

			choice = input.nextInt();

			switch (choice) {

			case 1:
				choiceResult = 1;
				mainLoop = false;
				// input.close();
				break;
			case 2:
				choiceResult = 2;
				mainLoop = false;
				// .close();
				break;
			case 3:
				mainLoop = false;

				break;
			default:
				System.out.println("Nie rozpoznany wybór");
				break;

			}

		}
		return choiceResult;
	}



	private static String getIdMenu() {
		Scanner input = new Scanner(System.in);
		System.out.print("Wprowadz identyfikator \n");
		String choice = input.nextLine();

		return choice;
	}
}
