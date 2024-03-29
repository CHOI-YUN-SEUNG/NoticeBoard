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
			registerUser();
			break;
		case 2:
			login();
			break;
		case 3:
			logout();
			break;
		case 4:
			unregisterUser(scanner, userManager);
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

	private boolean checkUser(String id) {
		if (userManager.getUser(id) != null)
			return true;
		return false;
	}

	private void registerUser() {
		System.out.println("\n===== 회원가입 =====");
		String id = inputString("사용자 ID");
		String password = inputString("사용자 PW");

		if (!checkUser(id)) {
			userManager.addUser(id, password);
			System.out.println("회원가입이 완료되었습니다.");
		} else
			System.out.println("해당 아이디는 사용하실 수 없습니다.");
	}

	private void login() {
		System.out.println("\n===== 로그인 =====");
		String id = inputString("사용자 ID");
		String password = inputString("사용자 PW");
		RegisteredUser user = userManager.getUser(id);
		if (user != null && user.getPassWord().equals(password)) {
			userManager.setLoggedIn(true);
			System.out.println("로그인 성공! " + id + "님 환영합니다.");
		} else {
			System.out.println("로그인 실패! 사용자 이름 또는 비밀번호를 확인하세요.");
		}
	}

	private void logout() {
		System.out.println("\n===== 로그아웃 =====");
		if (userManager.isLoggedIn()) {
			userManager.setLoggedIn(false);
			System.out.println("로그아웃 되었습니다.");
		} else {
			System.out.println("이미 로그아웃 상태입니다.");
		}
	}
	
	private void unregisterUser(Scanner scanner2, UserManager userManager2) {
		// TODO Auto-generated method stub

	}

	private void myPage(Scanner scanner2, UserManager userManager2, PostManager postManager2) {

	}

	private void writePost(Scanner scanner2, UserManager userManager2, PostManager postManager2) {

	}

	private void viewBoards(PostManager postManager2) {

	}



}