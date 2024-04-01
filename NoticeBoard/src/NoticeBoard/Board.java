package NoticeBoard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Board {
	private Scanner scanner;
	private UserManager userManager;
	private PostManager postManager;
	private String nowUser;
	private boolean isRun;
	private boolean isSubRun;

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
		System.out.println("6. 내 게시글 모아보기");
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
			boardHome();
			break;
		case 6:
			printMyPost();
			break;
		case 0:
			System.out.println("프로그램을 종료합니다.");
			isRun = false;
			return;
		default:
			System.out.println("올바르지 않은 메뉴 선택입니다. 다시 선택하세요.");
		}
	}

	private void registerUser() {
		System.out.println("\n===== 회원가입 =====");
		String id = inputString("사용자 ID");
		String password = inputString("사용자 PW");

		if (!userManager.checkUser(id)) {
			userManager.addUser(id, password);
			System.out.println("회원가입이 완료되었습니다.");
		} else
			System.out.println("해당 아이디는 사용하실 수 없습니다.");
	}

	private void login() {
		if (nowUser == null) {
			System.out.println("\n===== 로그인 =====");
			String id = inputString("사용자 ID");
			String password = inputString("사용자 PW");
			if(user
			RegisteredUser user = userManager.getUser(id);
			if (user != null && user.getPassWord().equals(password)) {
				nowUser = id;
				System.out.println("로그인 성공! " + id + "님 환영합니다.");
			} else {
				System.out.println("로그인 실패! 사용자 이름 또는 비밀번호를 확인하세요.");
			}
		} else
			System.out.println("이미 로그인 상태입니다");
	}

	private void logout() {
		if (nowUser != null) {
			nowUser = null;
			System.out.println("로그아웃 되었습니다.");
		} else {
			System.out.println("이미 로그아웃 상태입니다.");
		}
	}

	private void unregisterUser() {
		System.out.println("\n===== 회원탈퇴 =====");
		if (nowUser != null) {
			String password = inputString("사용자 PW");
			User user = userManager.getUser(nowUser);
			if (user != null && user.getPassWord().equals(password)) {
				postManager.removeAllPost(nowUser);
				userManager.removeUser(nowUser);
				logout();
				System.out.println("회원탈퇴가 완료되었습니다.");
			} else
				System.out.println("잘못된 비밀번호입니다. 탈퇴를 취소합니다");
		} else
			System.out.println("로그인이 필요합니다");
	}

	private void boardHome() {
		if (nowUser != null) {
			isSubRun = true;
			while (isSubRun) {
				viewBoards();
				printSubMenu();
				int choice = inputNumber("선택 ");
				processSubMenu(choice);
			}
		} else
			System.out.println("로그인이 필요합니다.");
	}

	private void printSubMenu() {
		System.out.println("\n===== 게시판 =====");
		System.out.println("1. 게시글 작성");
		System.out.println("2. 제목으로 게시글 검색");
		System.out.println("3. 작성자명으로 게시글 검색");
		System.out.println("4. 게시글 수정");
		System.out.println("5. 게시글 삭제");
		System.out.println("0. 뒤로가기");
		System.out.println("===============");
	}

	private void processSubMenu(int choice) {
		switch (choice) {
		case 1:
			writePost();
			break;
		case 2:
			searchPostByTitle();
			break;
		case 3:
			searchPostByAuthor();
			break;
		case 4:
			updatePost();
			break;
		case 5:
			deletePost();
			break;
		case 0:
			isSubRun = false;
			return;
		default:
			System.out.println("올바르지 않은 메뉴 선택입니다. 다시 선택하세요.");
		}
	}

	private void viewBoards() {
		System.out.println("\n===== 게시글 조회 =====");
		if (postManager.getSize() > 0) {
			HashMap<String, UserPost> temp = postManager.getPosts();
			List keyset = new ArrayList(temp.keySet());
			for (Object postTitle : keyset) {
				UserPost post = temp.get(postTitle);
				System.out.println(post);
			}
		} else
			System.out.println("게시글이 없습니다.");
	}

	private void writePost() {
		System.out.println("\n===== 게시글 작성 =====");
		String title = inputString("게시글 제목");
		UserPost post = postManager.getPost(title);
		if (post == null) {
			String content = inputString("게시글 내용");
			RegisteredUser author = userManager.getUser(nowUser);
			postManager.addPost(title, content, author);
			System.out.println("게시글이 작성되었습니다.");
		} else
			System.out.println("해당 제목의 게시글이 이미 존재합니다. 다른 제목으로 작성해주시길 바랍니다.");
	}

	private void searchPostByAuthor() {
		System.out.println("\n===== 게시글 검색 =====");
		String author = inputString("작성자명");
		HashMap<String, UserPost> temp = postManager.getPosts();
		List keyset = new ArrayList(temp.keySet());
		for (Object postTitle : keyset) {
			UserPost post = temp.get(postTitle);
			if (post != null && author.equals(post.getAuthor().getId())) {
				System.out.println("제목: " + post.getTitle() + ", 내용: " + post.getContent());
			} else
				System.out.println("게시글이 없습니다.");
		}
	}

	private void searchPostByTitle() {
		System.out.println("\n===== 게시글 검색 =====");
		String title = inputString("게시글 제목");
		UserPost post = postManager.getPost(title);
		if (post != null) {
			System.out.println(
					"제목: " + post.getTitle() + ", 내용: " + post.getContent() + "작성자: " + post.getAuthor().getId());
		} else
			System.out.println("해당 게시글이 존재하지 않습니다.");
	}

	private void updatePost() {
		System.out.println("\n===== 게시글 수정 =====");
		String title = inputString("게시글 제목");
		UserPost post = postManager.getPost(title);
		if (post != null) {
			if (nowUser.equals(post.getAuthor().getId())) {
				String content = inputString("게시글 내용");
				RegisteredUser author = userManager.getUser(nowUser);
				postManager.addPost(title, content, author);
				System.out.println("게시글이 수정 되었습니다.");
			} else
				System.out.println("본인이 작성한 글만 수정할 수 있습니다");
		} else
			System.out.println("해당 게시글이 존재하지 않습니다.");
	}

	private void deletePost() {
		System.out.println("\n===== 게시글 삭제 =====");
		String title = inputString("게시글 제목");
		UserPost post = postManager.getPost(title);
		if (post != null) {
			if (nowUser.equals(post.getAuthor().getId())) {
				postManager.removePost(post.getTitle());
				System.out.println("게시글이 삭제되었습니다");
			} else
				System.out.println("본인이 작성한 글만 삭제할 수 있습니다");
		} else
			System.out.println("해당 게시글이 존재하지 않습니다.");
	}

	private void printMyPost() {
		if (nowUser != null) {
			HashMap<String, UserPost> temp = postManager.getPosts();
			List keyset = new ArrayList(temp.keySet());
			Collections.sort(keyset, Comparator.comparing(User::getId));
			for (Object postTitle : keyset) {
				UserPost post = temp.get(postTitle);
				if (post != null && nowUser.equals(post.getAuthor().getId())) {
					System.out.println("제목: " + post.getTitle() + ", 내용: " + post.getContent());
				} else
					System.out.println("게시글이 없습니다.");
			}
		} else
			System.out.println("로그인이 필요합니다.");
	}
}