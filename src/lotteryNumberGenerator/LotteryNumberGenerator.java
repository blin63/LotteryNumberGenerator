
package lotteryNumberGenerator;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

/**
 * This program will generate random lottery number for lotto 6/49 or
 * lotto max.
 * @author Brendan Lin
 * @version 1.03
 *
 */
public class LotteryNumberGenerator {

	/**
	 * This method drives the program.
	 * @param args unused
	 */
	public static void main(String[] args) throws IllegalArgumentException,
		InputMismatchException {
		// TODO Auto-generated method stub
		//variables
		int input = 0;
		int limit = 0;
		int lottoNum = 0;
		int checkLimit = 0;
		int numbers[] = null;
		boolean check[];
		String selection = "";
		Random generator = new Random();
		Scanner scan = new Scanner(System.in);
		
		//get input from user and make sure value entered is valid
		try {
			System.out.println("Enter your selection (Enter digit):");
			System.out.println("1. Lotto 6/49");
			System.out.println("2. Lotto Max");
			
			input = scan.nextInt();
			
			scan.close();
			
			//process user selection
			//1 == Lotto 6/49 (requires 6 lottery numbers selected)
			//2 == Lotto Max (requires 7 lottery numbers selected)
			if (input == 1) {
				numbers = new int[6];
				check = new boolean[6];
				limit = 6;
				selection = "Lotto 6/49";
			} else if (input == 2) {
				numbers = new int[7];
				check = new boolean[7];
				limit = 7;
				selection = "Lotto Max";
			} else {
				//If user selects an invalid option
				throw new IllegalArgumentException();
			}
			
			check = construct(check, input);
			//Generate lottery numbers using limit to get appropriate
			//amount of lottery numbers
			for (int i = 0; i < limit; i++) {
				
				//Select the amount of numbers to pick from
				//based on lotto picked
				if (input == 1) {
					lottoNum = generator.nextInt(49) + 1;
				} else {
					lottoNum = generator.nextInt(50) + 1;
				}
				
				check[i] = true;
				
				//check if lotto number is unique
				for (int k = 0; k <= checkLimit; k++) {
					while (lottoNum == numbers[k] && !check[k]) {
						if (input == 1) {
							lottoNum = generator.nextInt(49) + 1;
						} else {
							lottoNum = generator.nextInt(50) + 1;
						}
						
						if (lottoNum != numbers[k]) {
							check[k] = true;
							k = 0;
						}					
					}
				}
				
				//reset check array and increment checkLimit
				check = construct(check, input);
				checkLimit++;
				
				//add number to array if unique
				numbers[i] = lottoNum;	
				
			}
			
			//print lotto numbers
			System.out.println(selection + " numbers:");
			System.out.println();
			
			for (int j = 0; j < limit; j++) {
				System.out.print(numbers[j] + " ");
			}
			
		} catch (InputMismatchException e) {
			System.out.println("Invalid value entered");
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid option selected");
		}
	}
	
	//support function to initialize check array
	public static boolean[] construct(boolean[] array, int lotto) {
		//limit variable to decide the array length
		int limit;
		
		//determine array length based on lotto
		if (lotto == 1) {
			limit = 6;
		} else {
			limit = 7;
		}
		
		//initialize check array
		for (int i = 0; i < limit; i++) {
			array[i] = false;
		}
		
		return array;
	}

}
