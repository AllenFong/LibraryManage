package library.springmvc.util;

public class MyUtil {
	public static String createBookCode(Long bookId,Short bookTypeId,int i) {
		String code="";//3λbookTypeId-4λbookId-3λ��������
		if(bookTypeId<10)
			code=code+"00"+bookTypeId;
		else if(bookTypeId<100)
			code=code+"0"+bookTypeId;
		else
			code=code+bookTypeId;
		if(bookId<10)
			code=code+"-000"+bookId;
		else if(bookId<100)
			code=code+"-00"+bookId;
		else if(bookId<1000)
			code=code+"-0"+bookId;
		else
			code=code+"-"+bookId;
		if(i<10)
			code=code+"-00"+i;
		else if(i<100)
			code=code+"-0"+i;
		else
			code=code+"-"+i;
		return code;
	}
}
