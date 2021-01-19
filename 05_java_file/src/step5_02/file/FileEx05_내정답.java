package step5_02.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

//# 파일 로드하기 : 연습문제  //4번문제 기반


public class FileEx05_내정답 {

	public static void main(String[] args) {	// 2021.1.6		12:27 - 1:10
		
		String[] ids = null;
		String[] pws = null;
		int[] moneys = null;
		
		String fileName = "fileTest02.txt";

		File file = new File(fileName);
		FileReader fr = null;
		BufferedReader br = null;
		
		if(file.exists()) {
			
			try {
				
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				
				
				String line = "";
				String data = "";
				
				while(true) {
					line = br.readLine();
					if(line == null) {
						break;
					}
					data += line;
					data += "\n";
				}
				
				
				String[] temp = data.split("\n");
				
				ids = new String[3];
				pws = new String[3];
				moneys = new int[3];
				
				
				for (int i = 0; i < temp.length; i++) {
					String[] arr = temp[i].split("/");
					ids[i] = arr[0];
					pws[i] = arr[1];
					for (int j = 0; j < moneys.length; j++) {
						moneys[i] += Integer.parseInt(arr[2]);	
					}
				}
				
				
				br.close();
				fr.close();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		System.out.println(Arrays.toString(ids));
		System.out.println(Arrays.toString(pws));
		System.out.println(Arrays.toString(moneys));
		
		
		

	}

}
