import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BookInformationReader {

	void readBookInfor(BookShelf b) {
		File dataFile = new File("./books.txt"); // 책 정보 텍스트 파일 객체 생성

		String readData; // 책정보를 저장할 변수
		StringTokenizer st; // 구분점을 이용해 책 정보를 분할하기 위한 변수
		try {
			BufferedReader br = new BufferedReader(new FileReader(dataFile));
			while ((readData = br.readLine()) != null) { // 텍스트 파일을 한줄씩 String으로 반환한다(빈공간이 나타나기 전까지 반복)
				Book book = new Book(); // book객체 생성.
				st = new StringTokenizer(readData, "/"); // 텍스트에 /를 구분점으로 두어 각각 토큰을 book객체 필드에 저장한다.
				book.id = st.nextToken();
				book.title = st.nextToken();
				book.author = st.nextToken();
				book.publisher = st.nextToken();
				book.year = st.nextToken();
				book.type = st.nextToken();
				String temp = st.nextToken(); // 국내,국외를 비교하기 위해 temp 에 담아준다.
				if (temp.charAt(0) == 'K') { // 만약 K면 true 저장
					book.korean = true;
				} else if (temp.charAt(0) == 'F') { // 아니면 false저장.
					book.korean = false;
				}
				b.bookList.add(book);	//각 필드에 정보를 저장후, BookShelf에 있는
					//ArrayList에 book 객체를 저장해준다.
			}
			br.close();	
		} catch (IOException e) {
			System.out.println(e.getMessage());// 오류메세지 출력
		}
	}
}
