package hw3;

import java.util.Scanner;
import java.io.File;


public class Tests {

	public static void main(String[] args) {
		
		//pass the path to the file as a parameter
		try {
			File file = new File("movies.txt");
			Scanner sc = new Scanner(file);
		
			int h = 0;
			while (sc.hasNextLine()) {
				System.out.println(sc.nextLine());
				h++;
				if (h > 20) {
					break;
				}	
			}
			sc.close();
		
		} catch (Exception e){
			System.out.println("File not found");
		}

	}
	}

