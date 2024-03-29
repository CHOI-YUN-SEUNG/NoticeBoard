package NoticeBoard;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Board {
	private Scanner scanner;
	private UserManager userManager;
	private PostManager postManager;
	private boolean isRun;

	public Board() {
		isRun = true;
		scanner = new Scanner(System.in);
		userManager = new UserManager();
		postManager = new PostManager();
	}

	public void run() {

		while (isRun) {
			printMenu();
			int choice = inputNumber("선택 ");
			processMenu(choice);
		}
	}

	private int inputNumber(String message) {
		int number = -1;
		while (number < 0) {
			try {
				System.out.print(message + " : ");
				String input = scanner.nextLine();
				number = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.err.println("숫자를 입력해주시길 바랍니다.");
			}
		}
		return number;
	}

	private String inputString(String message) {
		System.out.print(message + ": ");
		String input = scanner.nextLine();
		return input;
	}

	private void printMenu() {
		System.out.println("\n===== 메뉴 =====");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 로그아웃");
		System.out.println("4. 회원탈퇴");
		System.out.println("5. 게시판 둘러보기");
		System.out.println("6. 게시글 작성");
		System.out.println("7. 내 게시글 관리");
		System.out.println("0. 종료");
		System.out.println("===============");
	}

	private void processMenu(int choice) {
		switch (choice) {
		case 1:
			registerUser(scanner, userManager);
			break;
		case 2:
			login(scanner, userManager);
			break;
		case 3:
			logout(userManager);
			break;
		case 4:
			registerUser(scanner, userManager);
			break;
		case 5:
			viewBoards(postManager);
			break;
		case 6:
			writePost(scanner, userManager, postManager);
			break;
		case 7:
			myPage(scanner, userManager, postManager);
			break;
		case 0:
			System.out.println("프로그램을 종료합니다.");
			isRun = false;
			return;
		default:
			System.out.println("올바르지 않은 메뉴 선택입니다. 다시 선택하세요.");
		}
	}

	private void myPage(Scanner scanner2, UserManager userManager2, PostManager postManager2) {

	}

	private void writePost(Scanner scanner2, UserManager userManager2, PostManager postManager2) {

	}

	private void viewBoards(PostManager postManager2) {

	}

	private void logout(UserManager userManager2) {

	}

	private void login(Scanner scanner2, UserManager userManager2) {

	}

	private void registerUser(Scanner scanner2, UserManager userManager2) {

	}

}