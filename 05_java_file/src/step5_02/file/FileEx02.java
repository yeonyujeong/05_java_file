package step5_02.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

// # 파일 로드 (불러오기,output)

public class FileEx02 {

	public static void main(String[] args) {
			
		String fileName = "ex01.txt";
		
		File file = new File(fileName);		// 파일 객체 생성
		FileReader fr = null;				// 파일 읽어오기
		BufferedReader br = null;			// 텍스트 읽어오기
		
		if (file.exists()) {					// exists() : 파일이 존재하면
			
			try {
				
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				
				String data = br.readLine(); 	// readLine() : 한줄을 읽어온다.
				System.out.println(data);
				System.out.println();
				
				data = br.readLine();
				System.out.println(data);
				System.out.println();
				
				
				while (true) {			// 전체를 읽어오는 예시
					data = br.readLine();
					if(data == null) { 	// 읽어올 라인이 없으면 null을 반환한다.
						break;
					}
					System.out.println(data);
				}
				
				br.close();				// 나중에 open한 객체를 먼저 close한다.
				fr.close();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		
	}

}
