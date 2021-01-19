package step5_02.file;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

//# 파일 컨트롤러[1단계] : 벡터

public class FileEx06_내정답 {		// 2021.1.6		1:12 - 1:57
	
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		int[] vector = null;
		int elementCnt = 0;
		
		String fileName = "vector.txt";
		
		while (true) {
			System.out.println(Arrays.toString(vector));
			
			System.out.println("[벡터 컨트롤러]");
			System.out.println("[1]추가하기");
			System.out.println("[2]삭제하기");
			System.out.println("[3]저장하기");
			System.out.println("[4]로드하기");
			System.out.println("[5]종료하기");
			
			System.out.print("메뉴 선택 : ");
			int sel = scan.nextInt();
			
			if		(sel == 1) {
				System.out.print("추가할 데이터 입력 : ");
				int addData = scan.nextInt();
				if(elementCnt == 0) {
					vector = new int[elementCnt + 1];
					vector[elementCnt] = addData;
					elementCnt++;
				}else {
				elementCnt++;
				int[] temp = vector;
				vector = new int[elementCnt];
				for (int i = 0; i < temp.length; i++) {
					vector[i] = temp[i];
				}
				vector[elementCnt - 1] = addData;
				}
			}
			else if (sel == 2) {
				System.out.print("삭제할 값 입력 : ");
				int deleteData = scan.nextInt();
				
				int deleteIdx = -1;
				for (int i = 0; i < vector.length; i++) {
					if(vector[i] == deleteData) {
						deleteIdx = i;
					}
				}
				if(deleteIdx == -1) {
				System.out.println("해당 값은 존재하지 않습니다.");
				continue;
				}
				
				int[] temp = vector;
				elementCnt--;
				vector = new int[elementCnt];
				
				for (int i = 0; i < deleteIdx; i++) {
					vector[i] = temp[i];
				}
				for (int i = deleteIdx + 1; i < temp.length; i++) {
					vector[i-1] = temp[i];
				}

			}
			else if (sel == 3) {
				
				FileWriter fw = null;
				
				String data = "";
				
				for (int i = 0; i < vector.length; i++) {
					data += vector[i];
					if(i != vector.length) {
						data += "\n";
					}
				}
				
				try {
					
					fw = new FileWriter(fileName);
						
					fw.write(data);
					fw.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
			}
			else if (sel == 4) {
				
				File file = new File("vector.txt");
				FileReader fr = null;
				BufferedReader br = null;
				
				
				String data = "";
				elementCnt = 0;
				
				if(file.exists()) {
					try {
						 fr = new FileReader(file);
						 br = new BufferedReader(fr);
						 
						 
						 while(true) {
							 String num = br.readLine();
							 if(num == null) {
								 break;
							 }
							elementCnt++;
							data += num;
							data += ",";
						 }
						 
						
						 String [] arr = data.split(",");
						 vector =  new int[elementCnt];
						 
						 for (int i = 0; i < arr.length; i++) {
							vector[i] = Integer.parseInt(arr[i]);
						}

						 br.close();
						 fr.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				
				}
				
				
				
			}
			else if (sel == 5) {
				System.out.println("프로그램 종료");
				break;
			}
			
		}
		
		scan.close();
	}
}
