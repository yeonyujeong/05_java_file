package step5_02.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

// # 파일 로드 (불러오기,output)

public class FileEx02_복습 {

	public static void main(String[] args) {
			
	
		
		String fileName = "ex01.txt";
		
		File file = new File(fileName);
		FileReader fr = null;
		BufferedReader br = null;
		
		if(file.exists()) {
			
			try {
				
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				
				while(true) {
					String data = br.readLine();
					if(data == null) {
						break;
					}
					System.out.println(data);
				} 
				
				br.close();
				fr.close();
			
			}
			catch (Exception e) {
				e.printStackTrace();
			}
				
			
		}
		
		
	}

}
