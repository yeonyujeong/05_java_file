package step5_02.file;

import java.io.FileWriter;

//# 파일 저장하기 : 연습문제1


public class FileEx03_내정답 {

	public static void main(String[] args) {		// 2021.1.5
		
		// 김철수/20,이만수/30,이영희/40
		
		String[] names = {"김철수", "이만수", "이영희"};
		int[]     ages = {	   20,     30,     40};
		
		String fileName = "fileTest01.txt";
		
		
		String data = "";

		
		FileWriter fw = null;
		
		try {		
			fw = new FileWriter(fileName);
			
			for (int i = 0; i < ages.length; i++) {
				data += names[i];
				data += "/";
				data += Integer.toString(ages[i]);
				if(i != ages.length - 1) {data += ",";}
			}
			
			fw.write(data);

			fw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
