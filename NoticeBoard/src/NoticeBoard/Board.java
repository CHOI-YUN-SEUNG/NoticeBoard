package NoticeBoard;

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
	private boolean isAdmin;

	public Board() {
		isRun = true;
		scanner = new Scanner(System.in);
		userManager = UserManager.getInstance();
		postManager = PostManager.getInstance();
		isAdmin = false;
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
		if (isAdmin) { // 관리자인 경우에만
			System.out.println("7. 관리자 기능");
		}
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
		case 7:
			if (isAdmin)
				adminMenu();
			else
				System.out.println("올바르지 않은 메뉴 선택입니다. 다시 선택하세요.");
			break;
		case 0:
			System.out.println("프로그램을 종료합니다.");
			isRun = false;
			return;
		default:
			System.out.println("올바르지 않은 메뉴 선택입니다. 다시 선택하세요.");
		}
	}

	private boolean checkNull(String message) {
		if (nowUser == null)
			return true;
		else
			System.out.println(message);
		return false;
	}

	private boolean checkNotNull(String message) {
		if (nowUser != null)
			return true;
		else
			System.out.println(message);
		return false;
	}

	private void registerUser() {
		if (checkNull("이미 로그인 되어 있습니다")) {
			System.out.println("\n===== 회원가입 =====");
			String id = inputString("사용자 ID");
			String password = inputString("사용자 PW");
			if (!userManager.checkUser(id)) {
				userManager.addUser(id, password);
				System.out.println("회원가입이 완료되었습니다.");
			} else
				System.out.println("해당 아이디는 사용하실 수 없습니다.");
		}
	}

	private void login() {
		if (checkNull("이미 로그인 되어 있습니다")) {
			System.out.println("\n===== 로그인 =====");
			String id = inputString("사용자 ID");
			String password = inputString("사용자 PW");
			RegisteredUser user = userManager.getUser(id);
			if (user != null && user.getPassword().equals(password)) {
				nowUser = id;
				if (nowUser.equals("admin"))
					isAdmin = true;
				System.out.println("로그인 성공! " + id + "님 환영합니다.");
			} else {
				System.out.println("로그인 실패! 사용자 이름 또는 비밀번호를 확인하세요.");
			}
		}
	}

	private void logout() {
		if (checkNotNull("이미 로그아웃 상태입니다.")) {
			nowUser = null;
			isAdmin = false;
			System.out.println("로그아웃 되었습니다.");
		}
	}

	private void unregisterUser() {
		System.out.println("\n===== 회원탈퇴 =====");
		if (checkNotNull("로그인이 필요합니다")) {
			String password = inputString("사용자 PW");
			User user = userManager.getUser(nowUser);
			if (user != null && user.getPassword().equals(password)) {
				postManager.removeAllPost(nowUser);
				userManager.removeUser(nowUser);
				logout();
				System.out.println("회원탈퇴가 완료되었습니다.");
			} else
				System.out.println("잘못된 비밀번호입니다. 탈퇴를 취소합니다");
		}
	}

	private void boardHome() {
		if (checkNotNull("로그인이 필요합니다")) {
			isSubRun = true;
			while (isSubRun) {
				viewBoards();
				printSubMenu();
				int choice = inputNumber("선택 ");
				processSubMenu(choice);
			}
		}
	}

	private void printSubMenu() {
		System.out.println("\n===== 게시판 =====");
		System.out.println("1. 게시글 작성");
		System.out.println("2. 게시글 상세보기");
		System.out.println("3. 제목으로 게시글 검색");
		System.out.println("4. 작성자명으로 게시글 검색");
		System.out.println("5. 게시글 수정");
		System.out.println("6. 게시글 삭제");
		System.out.println("7. 게시글 추천하기");
		System.out.println("0. 뒤로가기");
		System.out.println("===============");
	}

	private void processSubMenu(int choice) {
		switch (choice) {
		case 1:
			writePost();
			break;
		case 2:
			viewPost();
			break;
		case 3:
			searchPostByTitle();
			break;
		case 4:
			searchPostByAuthor();
			break;
		case 5:
			updatePost();
			break;
		case 6:
			deletePost();
			break;
		case 7:
			upvotePost();
			break;
		case 0:
			isSubRun = false;
			return;
		default:
			System.out.println("올바르지 않은 메뉴 선택입니다. 다시 선택하세요.");
		}
	}

	private void viewBoards() {
		if (!postManager.getAllAdminNotices().isEmpty())
			viewAdminNotice();

		if (postManager.getAllPosts().isEmpty()) {
			System.out.println("=======================");
			System.out.println("게시글이 없습니다.");

		} else
			viewUserPosts();
	}

	private void viewAdminNotice() {
		System.out.println("\n===== 공지사항 목록 =====");
		List<AdminNotice> posts = postManager.getAllAdminNotices();
		for (int i = 0; i < posts.size(); i++) {
			AdminNotice post = posts.get(i);
			System.out.println(post);
		}
	}

	private void viewUserPosts() {
		System.out.println("\n===== 게시글 목록 =====");
		List<UserPost> posts = postManager.getAllPosts();
		for (int i = 0; i < posts.size(); i++) {
			UserPost post = posts.get(i);
			System.out.println((i + 1) + ". " + post.getTitle() + " (작성자: " + post.getAuthor() + ")" + " (추천수: "
					+ post.getUpvotes() + ")");
		}
		System.out.println("=======================");
	}

	private void viewPost() {
		List<UserPost> posts = postManager.getAllPosts();
		int choice = inputNumber("상세 내용을 확인할 게시글 번호(0: 뒤로가기)");
		if (choice != 0 && choice <= posts.size()) {
			UserPost selectedPost = posts.get(choice - 1);
			System.out.println("\n===== 게시글 상세 내용 =====");
			System.out.println("제목: " + selectedPost.getTitle());
			System.out.println("작성자: " + selectedPost.getAuthor());
			System.out.println("내용: " + selectedPost.getContent());
			System.out.println("추천수: " + selectedPost.getUpvotes());
			System.out.println("==========================");
		} else if (choice != 0) {
			System.out.println("잘못된 입력입니다.");
		}
	}

	private void searchPostByAuthor() {
		System.out.println("\n===== 작성자로 검색 =====");
		String author = inputString("작성자명");
		List<Post> posts = postManager.getPostsByAuthor(author);
		if (!posts.isEmpty()) {
			for (Post post : posts) {
				System.out.println(post);
			}
		} else {
			System.out.println("게시글이 없습니다.");
		}
	}

	private void searchPostByTitle() {
		System.out.println("\n===== 제목으로 검색 =====");
		String title = inputString("게시글 제목");
		Post post = postManager.getPost(title);
		if (post != null) {
			System.out.println(post);
		} else {
			System.out.println("해당 게시글이 존재하지 않습니다.");
		}
	}

	private void writePost() {
		System.out.println("\n===== 게시글 작성 =====");
		String title = inputString("게시글 제목");
		Post post = postManager.getPost(title);
		if (post == null) {
			writePost(title);
			System.out.println("게시글이 작성되었습니다.");
		} else
			System.out.println("해당 제목의 게시글이 이미 존재합니다. 다른 제목으로 작성해주시길 바랍니다.");
	}

	private void writePost(String title) {
		System.out.println("게시글 내용을 입력하세요 (입력이 끝나면 빈 줄을 입력하세요):");
		StringBuilder contentBuilder = new StringBuilder();
		String line;
		while (!(line = scanner.nextLine()).isEmpty()) {
			contentBuilder.append(line).append("\n");
		}
		String content = contentBuilder.toString().trim();
		String author = userManager.getUser(nowUser).getId();
		postManager.addUserPost(title, content, author);
	}

	private void updatePost() {
		System.out.println("\n===== 게시글 수정 =====");
		String title = inputString("게시글 제목");
		UserPost post = (UserPost) postManager.getPost(title);
		if (post != null) {
			if (nowUser.equals(post.getAuthor())) {
				writePost(title);
				System.out.println("게시글이 수정 되었습니다.");
			} else {
				System.out.println("본인이 작성한 글만 수정할 수 있습니다");
			}
		} else {
			System.out.println("해당 게시글이 존재하지 않습니다.");
		}
	}

	private void deletePost() {
		System.out.println("\n===== 게시글 삭제 =====");
		String title = inputString("게시글 제목");
		Post post = postManager.getPost(title);
		if (post != null) {
			if (nowUser.equals(post.getAuthor())) {
				postManager.removePost(title);
				System.out.println("게시글이 삭제되었습니다");
			} else {
				System.out.println("본인이 작성한 글만 삭제할 수 있습니다");
			}
		} else {
			System.out.println("해당 게시글이 존재하지 않습니다.");
		}
	}

	private void upvotePost() {
		System.out.println("\n===== 게시글 추천하기 =====");
		String title = inputString("추천할 게시글 제목");
		UserPost post = (UserPost) postManager.getPost(title);
		if (post != null) {
			if (!post.hasUpvoted(nowUser)) {
				post.upvote(nowUser);
				System.out.println("게시글을 추천하였습니다.");
			} else {
				System.out.println("이미 추천한 게시글입니다.");
			}
		} else {
			System.out.println("해당 게시글이 존재하지 않습니다.");
		}
	}

	private void printMyPost() {
		if (checkNotNull("로그인이 필요합니다")) {
			List<Post> posts = postManager.getPostsByAuthor(nowUser);
			if (!posts.isEmpty()) {
				for (Post post : posts) {
					System.out.println(post);
				}
			} else
				System.out.println("게시글이 없습니다.");
		}
	}

	private void adminMenu() {
		isSubRun = true;
		while (isSubRun) {
			printAdminMenu();
			int choice = inputNumber("관리자 메뉴 선택 ");
			processAdminMenu(choice);
		}
	}

	private void printAdminMenu() {
		System.out.println("\n===== 관리자 메뉴 =====");
		System.out.println("1. 전체 유저 정보 확인");
		System.out.println("2. 공지사항 작성");
		System.out.println("3. 특정 유저 밴");
		System.out.println("0. 뒤로가기");
		System.out.println("======================");
	}

	private void processAdminMenu(int choice) {
		switch (choice) {
		case 1:
			viewAllUsers();
			break;
		case 2:
			writeNotice();
			break;
		case 3:
			banUser();
			break;
		case 0:
			isSubRun = false;
			return;
		default:
			System.out.println("올바르지 않은 메뉴 선택입니다. 다시 선택하세요.");
		}
	}

	private void viewAllUsers() {
		System.out.println("\n===== 전체 유저 정보 =====");
		HashMap<String, RegisteredUser> users = userManager.getUsers();
		for (RegisteredUser user : users.values()) {
			System.out.println("ID: " + user.getId() + ", Password: " + user.getPassword());
		}
	}

	private void writeNotice() {
		System.out.println("\n===== 공지사항 작성 =====");
		String title = inputString("공지사항 제목");
		String content = inputString("공지사항 내용");
		String author = nowUser;
		postManager.addAdminPost(title, content, author);
		System.out.println("공지사항이 작성되었습니다.");
	}

	private void banUser() {
		System.out.println("\n===== 유저 밴 =====");
		String userId = inputString("밴할 유저 ID");
		if (userManager.checkUser(userId)) {
			postManager.removeAllPost(userId);
			userManager.removeUser(userId);
			System.out.println(userId + "님이 밴되었습니다.");
		} else
			System.out.println("해당 ID를 가진 유저가 존재하지 않습니다.");
	}
}