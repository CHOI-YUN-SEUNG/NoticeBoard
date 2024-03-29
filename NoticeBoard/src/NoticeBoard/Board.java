package NoticeBoard;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Board {
	private Scanner scanner;
	private UserManager userManager;
	private PostManager postManager;
	private String nowUser;
	private boolean isRun;

	public Board() {
		nowUser = "";
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
			unregisterUser();
			break;
		case 5:
			viewBoards();
			break;
		case 6:
			writePost();
			break;
		case 7:
			myPage();
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
			nowUser = id;
			System.out.println("로그인 성공! " + id + "님 환영합니다.");
		} else {
			System.out.println("로그인 실패! 사용자 이름 또는 비밀번호를 확인하세요.");
		}
	}

	private void logout() {
		if (userManager.isLoggedIn()) {
			userManager.setLoggedIn(false);
			nowUser = "";
			System.out.println("로그아웃 되었습니다.");
		} else {
			System.out.println("이미 로그아웃 상태입니다.");
		}
	}

	private void unregisterUser() {
		System.out.println("\n===== 회원탈퇴 =====");
		if (userManager.isLoggedIn()) {
			String password = inputString("사용자 PW");
			User user = userManager.getUser(nowUser);
			if (user != null && user.getPassWord().equals(password)) {

				while (postManager.findPost(nowUser) != null) {
					UserPost post = postManager.findPost(nowUser);
					postManager.removePost(post.getTitle());
				}

				userManager.removeUser(nowUser);

				System.out.println("회원탈퇴가 완료되었습니다.");
				logout();
			} else
				System.out.println("잘못된 비밀번호입니다. 탈퇴를 취소합니다");
		} else
			System.out.println("로그인이 필요합니다");
	}

	private void viewBoards() {// 게시글 유저검색,제목검색,번호 입력으로 내용확인
		System.out.println("\n===== 게시글 조회 =====");
		HashMap<String, UserPost> boards = postManager.getPosts();
		if (postManager.getSize() > 0) {
			for (Map.Entry<String, UserPost> entry : boards.entrySet()) {
				System.out.println("제목: " + entry.getKey() + ", 내용: " + entry.getValue().getContent() + ", 작성자: "
						+ entry.getValue().getAuthor().getId());
			}
		} else
			System.out.println("게시글이 없습니다.");
	}

	private void writePost() {
		System.out.println("\n===== 게시글 작성 =====");
		if (userManager.isLoggedIn()) {
			String title = inputString("게시글 제목");
			Post post = postManager.getPost(title);
			if (post == null) {
				String content = inputString("게시글 내용");
				RegisteredUser author = userManager.getUser(nowUser);
				postManager.addPost(title, content, author);
				System.out.println("게시글이 작성되었습니다.");
			} else
				System.out.println("해당 제목의 게시글이 이미 존재합니다. 다른 제목으로 작성해주시길 바랍니다.");
		} else {
			System.out.println("로그인이 필요합니다.");
		}
	}

	private void myPage() { // 내 게시글 모아보여주고, 게시글 수정, 삭제

	}

}