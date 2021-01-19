package step5_02.file;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

//# 파일 컨트롤러[3단계] : 장바구니

public class FileEx08_내정답 {	// 2021.1.7		12:32 - 1:30
	
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		String[] ids = {"qwer", "javaking", "abcd"};
		String[] pws = {"1111",   "2222", "3333"};
		String[] items = {"사과", "바나나", "딸기"};
		
		final int MAX_SIZE = 100;
		int[][] jang = new int[MAX_SIZE][2];

		String fileName = "jang.txt";
		
		int count = 0;
		int log = -1;
		
		while (true) {
			
			
			
			System.out.println("---------------");
			for (int i = 0; i < ids.length; i++) {
				System.out.print(ids[i] + ":" + pws[i] );
				System.out.print("(");
				if(count > 0) {
					for (int j = 0; j < count; j++) {
						if(jang[j][0] == i) {
							if(jang[j][1] == 1) {System.out.print("사과");}
							else if(jang[j][1] == 2) {System.out.print("바나나");}
							else if(jang[j][1] == 3) {System.out.print("딸기");}
							System.out.print("/");
						}
					}
				}	
				System.out.print(")");
				System.out.println();
			}
				
			System.out.println("---------------");
			System.out.print("상태 : " );
			if(log == -1) {
				System.out.print("로그아웃");
			}else {
				System.out.println(ids[log] + "님, 로그인중");
			}
			System.out.println();
			System.out.println("[MEGA SHOP]");
			System.out.println("[1]로그인");
			System.out.println("[2]로그아웃");
			System.out.println("[3]쇼핑");
			System.out.println("[4]장바구니");
			System.out.println("[5]저장");
			System.out.println("[6]로드");
			System.out.println("[0]종료");
			
			System.out.print("메뉴 선택 : ");
			int sel = scan.nextInt();
			
			if		(sel == 1) {
				System.out.print("id를 입력하세요 : ");
				String logId = scan.next();
				System.out.println("pw를 입력하세요 :");
				String logPw = scan.next();
				
				for (int j = 0; j < ids.length; j++) {
					if(ids[j].equals(logId) && pws[j].equals(logPw)) {
						System.out.println(ids[j] + "님, 로그인되었습니다.");
						log = j;
					}
				}
				if(log == -1) {
					System.out.println("id와 pw를 확인해주세요");
				}
			}
			else if (sel == 2) {
				if(log == -1) {
					System.out.println("이미 로그아웃 되어있습니다.");
				}
				else {
					System.out.println(ids[log] +"님, 로그아웃되었습니다.");
					log = -1;
				}
			}
			else if (sel == 3) {
				if(log == -1) {
					System.out.println("로그인 후에 이용해주세요.");
					continue;
				}
				
				System.out.println("1.사과 \n2.바나나 \n3.딸기");
				System.out.println("[쇼핑] 상품번호를 선택하세요 : ");
				int shopping = scan.nextInt();
				
				if(shopping == 1) {
					jang[count][0] = log;
					jang[count][1] = 1;
					count++;
				}
				else if(shopping == 2) {
					jang[count][0] = log;
					jang[count][1] = 2;
					count++;
				}
				else if(shopping == 3) {
					jang[count][0] = log;
					jang[count][1] = 3;
					count++;
				}
			}
			else if (sel == 4) {
				if(log == -1) {
					System.out.println("로그인 후에 이용해주세요.");
					continue;
				}
				System.out.println("[" + ids[log] + "님의 장바구니]");
				int cnt = 1;
				for (int i = 0; i < count; i++) {
					if(jang[i][0] == log) {
						System.out.print(cnt + ".");
						if(jang[i][1] == 1) {System.out.println("사과");}
						else if(jang[i][1] == 2) {System.out.println("바나나");}
						else if(jang[i][1] == 3) {System.out.println("딸기");}
						cnt++;
					}
				}
			}
			else if (sel == 5) {
				
				FileWriter fw = null;
				
				String data = "";
				
				for (int i = 0; i < ids.length; i++) {
					data += ids[i];
					data += "/";
					data += pws[i];
					data += "/";
					for (int j = 0; j < count; j++) {
						if(jang[j][0] == i) {
							if(jang[j][1] == 1) {data += "사과";}
							else if(jang[j][1] == 2) {data += "바나나";}
							else if(jang[j][1] == 3) {data += "딸기";}
							data += ":";
						}
					}
					data += "\n";
				}
				
				data = data.substring(0, data.length() - 1);
				
				try {
					fw = new FileWriter(fileName);
					
					fw.write(data);
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if(fw != null) {try {fw.close();} catch (Exception e2) {e2.printStackTrace();}}
				}
				
			}
			else if (sel == 6) {
				
				File file = new File(fileName);
				FileReader fr = null;
				BufferedReader br = null;
				
				if(file.exists()) {
					
					try {
						fr = new FileReader(file);
						br = new BufferedReader(fr);
						
						String data = "";
						while(true) {
						String line = br.readLine();
						if(line == null) {break;}				
						data += line;
						data += "\n";	
						}
						
						data = data.substring(0,data.length()-1);
						
						String[] temp = data.split("\n");
						count = temp.length;
						String[] jangs = new String[count];
						
						for (int i = 0; i < count ; i++) {
							String[] arr = temp[i].split("/");
							ids[i] = arr[0];
							pws[i] = arr[1];
							if(arr.length == 3) {jangs[i] = arr[2];}
							else {jangs[i] = "";}
						}
						System.out.println(Arrays.toString(jangs));
						
						int cnt = 0;
						for (int i = 0; i < jangs.length; i++) {
							String[] jang1 = jangs[i].split(":");
							for (int j = 0; j < jang1.length; j++) {
								if(jang1[j].equals("사과")) {jang[cnt][0] = i; jang[cnt][1] = 1; cnt++;}
								else if(jang1[j].equals("바나나")) {jang[cnt][0] = i; jang[cnt][1] = 2; cnt++;}
								else if(jang1[j].equals("딸기")) {jang[cnt][0] = i; jang[cnt][1] = 3; cnt++;}
							}
						}
	
						}
					 catch (Exception e) {
						e.printStackTrace();
					} finally {
						if(fr!=null) {try {fr.close();} catch (Exception e2) {e2.printStackTrace();}}
						if(br!=null) {try {fr.close();} catch (Exception e2) {e2.printStackTrace();}}
					}
					
					
					
				}
				

			}
			else if (sel == 0) {
				System.out.println("프로그램 종료");
				break;
			}
			
		}
		scan.close();
	}
	
}
