import java.util.ArrayList;
import java.util.Random;

public class Person extends Thread {

	ArrayList<Book> myBooks = new ArrayList<>(); // 내가 뽑아온 책을 저장하는 ArrayList
	int numOfKoreanBooks; // 뽑아온 국내도서 권 수 카운트
	int numOfForeignBooks; // 뽑아온 해외도서 권 수 카운트
	BookShelf sharedBookShelf;

	public Person(BookShelf bookList) {
		this.sharedBookShelf = bookList;
	}
	
	
	
	
	@Override
	public void run() { // Thread에 이미 존재하는 메소드인 run 재정의
		int randNum = Random();	//Random메소드를 이용해 난수 발생
		while (true) {
			Book getBook = new Book();	//Book객체 생성
			getBook = sharedBookShelf.removing(randNum);	//동기화 메소드 호출
			if (getBook == null ) {		//만약 반환받은 책이 null이면 종료
				return;
			}
			myBooks.add(getBook);	//반환받은 책 객체 List에 추가

			if (getBook.korean == true)	//국내 책 일 경우 국내책 카운트 1증가
				numOfKoreanBooks++;
			else
				numOfForeignBooks++;//국외 책 일 경우 국내책 카운트 1증가
			try {
				randNum = Random();	//책이 다 떨어졌을떄의 예외 처리
			} catch (Exception e) {
				
			}
		}
		
	}
	
	int Random() { // 랜덤 클래스에 난수를 이용해 1~20 을 무작위로 리턴해준다.
		Random r = new Random();
		int rand = r.nextInt(sharedBookShelf.bookList.size()); // ArrayList 의 크기를 최대값으로 난수를 발생한다.
		return rand;
	}

}
