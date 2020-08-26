import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class BookShelf {

	BookInformationReader bir = new BookInformationReader();
	// 책 정보를 읽는 객체 생성
	ArrayList<Book> bookList = new ArrayList<>();
	// 책 정보를 저장하는 ArrayList

	void prepareBook() { // ArrayList에 책 정보를 저장해주는 메소드
		bir.readBookInfor(this); // this를 이용해 매개변수로 본인객체를 전달한다.
	}

	int temp = 0; // 1~20난수를 저장해줄 필드
	Book tempBook;	//임시로 책을 저장해줄 필드
	//static boolean state = true; // 마지막 거래를 끝냈는지를 판별해주는 불리언 필드

	// 메소드

	void printKoreaBookList() { // 프로그램을 시작하면 국내 도서를 구분하여 출력해주는 메소드
		for (int i = 0; i < this.bookList.size(); i++) {
			String a = this.bookList.get(i).id;
			String b = this.bookList.get(i).title;
			String c = this.bookList.get(i).author;
			String d = this.bookList.get(i).publisher;
			String e = this.bookList.get(i).year;
			String f = this.bookList.get(i).type;
			boolean g = this.bookList.get(i).korean;
			if (bookList.get(i).korean == true) {
				System.out.print("국내 도서 :");
				System.out.println(a + "/" + b + "/" + c + "/" + d + "/" + e + "/" + f + "/" + g);

			}
		}
	}

	void printForeignBookList() { // 프로그램을 시작하면 국외 도서를 구분하여 출력해주는 메소드
		for (int i = 0; i < this.bookList.size(); i++) {
			String a = this.bookList.get(i).id;
			String b = this.bookList.get(i).title;
			String c = this.bookList.get(i).author;
			String d = this.bookList.get(i).publisher;
			String e = this.bookList.get(i).year;
			String f = this.bookList.get(i).type;
			boolean g = this.bookList.get(i).korean;
			if (bookList.get(i).korean != true) {
				System.out.print("해외 도서 :");
				System.out.println(a + "/" + b + "/" + c + "/" + d + "/" + e + "/" + f + "/" + g);
			}
		}
	}

	public synchronized Book removing(int randNum) {

		if ((this.bookList.size()) == 0) {
			// 만약 책정보를 갖고있는 ArrayList의 size가 1을빼면 0보다 작을경우
			System.out.println("책이 다 떨어졌습니다.");
		//	this.state = false;
			return null;
		}
		tempBook = this.bookList.get(temp);
		this.bookList.remove(temp);
		
		notify(); // 대기중인 쓰레드를 꺠운다
	      try {  
	          Thread.sleep(100);	//2초간 거래하는 시간을 가진 후
	          wait(1000);	//다른 쓰레드를 깨운 쓰레드가 일시정지 된다.
	          	//(단 마지막 거래 시 두개의 쓰레드 중 한 쓰레드가 종료되고 남은 쓰레드를 1초간 아무도 꺠우지 않으면 자동으로 쓰레드가 실행됨.)
	       } catch (Exception e) {}
	      return tempBook;


	}

	/*
	int Random() { // 랜덤 클래스에 난수를 이용해 1~20 을 무작위로 리턴해준다.
		Random r = new Random();
		int rand = r.nextInt(this.bookList.size()); // ArrayList 의 크기를 최대값으로 난수를 발생한다.
		return rand;
	}
	*/
}
