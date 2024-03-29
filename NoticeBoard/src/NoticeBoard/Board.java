package NoticeBoard;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Board {
	private Scanner scanner;
	private UserManager userManager;
	private PostManager postManager;

	public Board() {
		scanner = new Scanner(System.in);
		userManager = new UserManager();
		postManager = new PostManager();
	}

	public void run() {
		while (true) {
			printMenu();
			System.out.print("선택>> ");
		}
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
	
	
}