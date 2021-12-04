package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import db.DBManager;
import solver.Options;
import solver.Solver;
import utils.DayGenerator;

public class Main {

	public static void main(String[] args) throws IOException {

		DayGenerator.main(args);
		/*
		 * Set the path for aoc.db
		 */
		DBManager.setPath("aoc.db");

		if (args[0].equals("adduser")) {

			try {
				adduser(args[1]);
			} catch (IOException e) {
				System.err.println("Error while reading file:" + e.getMessage());
			}
			System.exit(0);
		}

		int year = -1, day = -1, currentyear;
		LocalDate currentdate = LocalDate.now();
		currentyear = currentdate.getYear();
		String username;

		if (args.length == 0) {
			username = "";
		} else {
			year = currentyear; // current year by default
			day = -1;
			username = "";
			for (String s : args) {
				String[] ar = s.split("=");
				switch (ar[0]) {
				case "year":
					year = Integer.parseInt(ar[1]);
					break;
				case "day":
					day = Integer.parseInt(ar[1]);
					break;
				case "user":
					username = ar[1];
					break;
				}
			}
		}
		if (username == "") {
			System.err.println("Username is needed: add user=<username> to args");
			System.exit(0);
		}

		if (year < 2015) {
			System.out.println("No AOC before 2015");
		} else if (year > currentyear) {
			System.out.println("Cannot time travel");
		}

		if (day == -1) // no day give, solve all days
			for (int i = 1; i <= 25; i++) {
				solve(username, year, i);
			}
		else // solve just the single day if day is given
			solve(username, year, day);
	}

//	private static void interactiveMode() {
//
//		System.out.println("Interactive mode started!");
//		Scanner s = new Scanner(System.in);
//		System.out.println("Enter the username:");
//		String username = s.nextLine();
//	}

	private static void adduser(String string) throws IOException {
		List<String> file = Files.readAllLines(Paths.get(string))//
				.stream() //
				.map(line -> line.strip()) //
				.filter(line -> line.matches("\\s")) //
				.filter(line -> line.startsWith("#")).collect(Collectors.toList());//

		String username = file.get(0);
		String cookie = file.get(1);
		if (!cookie.matches("session=[a-f0-9]*")) {
			System.err.println("error: malformed cookie:");
			System.err.println(cookie);
			return;
		}
		if (DBManager.hasUser(username)) {
			DBManager.updateUser(username, cookie);
		} else {
			DBManager.addUser(username, cookie);
		}

	}

	public static void solve(String username, int year, int day) {
		System.out.println(String.format("Solving: %d/%d for %s", year, day, username));
		Solver solver = new Solver();
		try {
			solver.solve(username, year, day, new Options());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
