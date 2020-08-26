import java.time.LocalDateTime;
import java.util.StringTokenizer;


/*
 *    프로그램명 : 기말고사 대체 프로젝트  도서관리 프로그램 ver.3
 *    개발 목적 : 멀티 쓰레드와 공유객체를 사용하여 사람1, 2가 각각 책꽂이에 책을 가져가게 한다.  	
 *    
 *    개발자 : 컴퓨터공학과 2016244115 김민재
 *    개발 시작일자 : 2020년 06월 28일 오후 2시
 *    개발 완료일자 : 2020년 06월 28일 오후 4시 40분
 *    
 */
public class Operator {

	//필드	
	BookShelf bs =  new BookShelf();	//공유 객체(책꽂이) 생성
	Person bookCollector1 = new Person(bs);	//공유객체를 받는 쓰레드 클래스(사람)생성
	Person bookCollector2 = new Person(bs);	//공유객체를 받는 쓰레드 클래스(사람)생성
	BookInformationReader br = new BookInformationReader();
	//메소드
	
	public static void main(String[] args) {
		Operator op = new Operator();
		op.working();
		

	}
	void working() {
		String originalTime = LocalDateTime.now().toString();	//현재시간 문자열 객체 생성
		String nowTime = timeFormatConvert(originalTime);	//메소드의 리턴타입은 String 이므로 그에 알맞게 저장해줌.

		System.out.println("KMJ 도서관에 오신것을 환영합니다!!!    현재시간 "+nowTime);	
		//프로그램 시작을 알리는 환영 메세지
		
		
		bs.prepareBook();
		bs.printKoreaBookList();
		bs.printForeignBookList();
		
		//두개의 쓰레드 실행
		bookCollector1.start();
		bookCollector2.start();
		
		try {		//두개의 쓰레드가 책 뽑기를 다 할 때까지 메인 쓰레드는 대기한다.
			bookCollector1.join();
			bookCollector2.join();
		} catch (InterruptedException e) {}
		
		System.out.println("bookCollector1 가 뽑은 국내책 :"+ bookCollector1.numOfKoreanBooks + " 국외 책 :"+bookCollector1.numOfForeignBooks);	//collector 1번 사람이 총 뽑은 책 갯수 출력
		System.out.println("bookCollector2 가 뽑은 국내책 :"+ bookCollector2.numOfKoreanBooks + " 국외 책 :"+bookCollector2.numOfForeignBooks);	//collector 2번 사람이 총 뽑은 책 갯수 출력
		
		
		System.out.printf("\n\n\n\nbookCollector1 가 뽑은 책 내역 : \n");
		for(int i =0; i<bookCollector1.myBooks.size(); i++) {
			System.out.println(		bookCollector1.myBooks.get(i).title);
		}
		System.out.printf("\n\n\nbookCollector2 가 뽑은 책 내역 : \n");
		
		for(int i =0; i<bookCollector1.myBooks.size(); i++) {
			System.out.println(		bookCollector2.myBooks.get(i).title);
		}
		System.out.printf("\n\n\n\nKMJ 도서관을 이용해주셔서 감사합니다.    현재시간 : "+nowTime);
	}
	void nowTimePrint() {	//현재시간 출력 메소드
		String originalTime = LocalDateTime.now().toString();	//현재시간 문자열 객체 생성
		String nowTime = timeFormatConvert(originalTime);	//메소드의 리턴타입은 String 이므로 그에 알맞게 저장해줌.
			
		System.out.println(nowTime);	//리턴받은문자열 출력
	}

	String timeFormatConvert(String originalTime) {	//현재시간 출력을 위한 메소드
		StringTokenizer st;
		st = new StringTokenizer(originalTime, "T");	//시간객체를 T를 구분점으로 나누어준다.
		String nowT = st.nextToken("T");	//2개로 나누어진 시간문자열 중 1번 저장(년,월,일이 있음)
		String nowTt = st.nextToken();	//2개중 2번째 저장(시,분,초 가 있음.)
		st = new StringTokenizer(nowTt, ":");	//시,분,초들을 :를 구분점으로 나누어 각각 변수에 담아준다.
		String hours = st.nextToken();
		String minute = st.nextToken();
		String second = st.nextToken();
		StringTokenizer ssecond = new StringTokenizer(second,".");	//마지막 초는 .를 구분점으로 나누어준다.
		String realSecond = ssecond.nextToken();	//소수점 없는 초만 따로 저장해준다.
		String result = hours + "시 " + minute + "분 " + realSecond + "초";	

		return result;	//결과 문자열 리턴
	}

}
