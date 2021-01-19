package step5_02.file;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

//# 파일 컨트롤러[2단계] : ATM

public class FileEx07_내정답 {
	
	public static void main(String[] args) {		// 2021.1.6		2:08 - 2:58 / 3:31

		Scanner scan = new Scanner(System.in);
		
		int size = 5;
		int accsCnt = 0;
		int identifier = -1;
		
		String[] accs = new String[size];
		String[]  pws = new String[size];
		int[]  moneys = new int[size];
		
		String fileName = "atm.txt";
		
		while(true) {
			
			System.out.println("-------------");
			System.out.print("상태 : ");	
			if(identifier == -1) {
				System.out.println("로그아웃");
			}
			else {
				System.out.println(accs[identifier] + "님, 로그인중");
			}
			System.out.println("-------------");
			
			for (int i = 0; i < accsCnt; i++) {
				System.out.println(accs[i] + ":" + pws[i] + ":" + moneys[i]);
			}
			System.out.println("-------------");
			
			
			System.out.println("[MEGA ATM]");
			System.out.println("[1]회원가입");
			System.out.println("[2]회원탈퇴");
			System.out.println("[3]로그인");
			System.out.println("[4]로그아웃");
			System.out.println("[5]입금");
			System.out.println("[6]출금");
			System.out.println("[7]이체");
			System.out.println("[8]잔액조회");
			System.out.println("[9]저장");
			System.out.println("[10]로드");
			System.out.println("[0]종료");
			
			System.out.print("메뉴 선택 : ");
			int sel = scan.nextInt();
			
			if		(sel == 1) {
				if(accsCnt == 5) {
					System.out.println("더이상 회원가입이 불가합니다.");
					continue;
				}
				
				System.out.print("회원가입할 acc를 입력해주세요 : ");
				String joinAcc = scan.next();
				if(accsCnt > 0) {
					for (int i = 0; i < accsCnt; i++) {
						if( accs[i].equals(joinAcc)) {
							System.out.println("이미 존재하는 계좌입니다.");
							continue;
						}
					}
				}	
					
				System.out.print("가입할 acc의 pw를 입력해주세요 : ");
				String joinPw = scan.next();
						
				accs[accsCnt] = joinAcc;
				pws[accsCnt] = joinPw;
				moneys[accsCnt] +=1000;
				accsCnt++;
				
					
				}
				
			
			else if (sel == 2) {
				if(identifier == -1) {
					System.out.println("로그인 후 가능합니다.");
					continue;
				}
				System.out.print("탈퇴를 하시려면 비밀번호를 입력해주세요 : ");
				String getPw = scan.next();
				
				if(getPw.equals(pws[identifier])) {
					String[] tempAcc = accs;
					String[] tempPw = pws;
					int [] tempMoney = moneys;
					accs = new String[accsCnt -1];
					pws = new String[accsCnt -1];
					moneys = new int[accsCnt -1];	
					for (int j = 0; j < identifier; j++) {
						accs[j] = tempAcc[j];
						pws[j] = tempPw[j];
						moneys[j] = tempMoney[j];
					}
					for (int j2 = identifier + 1; j2 < accsCnt; j2++) {
						accs[j2 + 1] = tempAcc[j2];
						pws[j2 + 1] = tempPw[j2];
						moneys[j2 + 1] = tempMoney[j2];
					}
					accsCnt--;
					identifier = -1;
				}
				else {
					System.out.println("비밀번호가 일치하지 않습니다.");
					continue;
				}
			}
			else if (sel == 3) {
				if(identifier != -1) {
					System.out.println("이미 로그인 되어있습니다.");
					continue;
				}
				System.out.print("로그인할 acc를 입력해주세요 :");
				String logAcc = scan.next();
				System.out.print("로그인할 pw를 입력해주세요 : ");
				String logPw = scan.next();
				
				for (int j = 0; j < accsCnt; j++) {
					if(logAcc.equals(accs[j]) && logPw.equals(pws[j])) {
						System.out.println(accs[j] + "님, 로그인되었습니다.");
						identifier = j;
					}
				}
				if(identifier == -1) {
					System.out.println("acc와 pw를 확인해주세요.");
					continue;
				}
			}
			else if (sel == 4) {
				if(identifier == -1) {
					System.out.println("이미 로그아웃 되어있습니다.");
					continue;
				}
				System.out.print("로그아웃 하시려면 비밀번호를 입력해주세요 : ");
				String logoutPw = scan.next();
				
				if(pws[identifier].equals(logoutPw)) {
					identifier = -1;
					System.out.println("로그아웃 되었습니다.");
				}
				else {
					System.out.println("비밀번호를 확인해주세요.");
				}
			}
			else if (sel == 5) {
				if(identifier == -1) {
					System.out.println("로그인 후에 이용해주세요.");
					continue;
				}
				System.out.println("입금하실 금액을 입력해주세요 : ");
				int deposit = scan.nextInt();
				
				moneys[identifier] += deposit;
				System.out.println("입금이 완료되었습니다.");
				
			}
			else if (sel == 6) {
				if(identifier == -1) {
					System.out.println("로그인 후에 이용해주세요.");
					continue;
				}
				System.out.println("출금하실 금액을 입력해주세요 : ");
				int withdraw = scan.nextInt();
				
				if(moneys[identifier] < withdraw) {
					System.out.println("출금할 금액이 부족합니다.");
					continue;
				}
				else {
					moneys[identifier] -= withdraw;
					System.out.println("출금이 완료되었습니다.");
				}
			}
			else if (sel == 7) {
				if(identifier == -1) {
					System.out.println("로그인 후에 이용해주세요.");
					continue;
				}
				System.out.print("이체할 계좌를 입력해주세요 : ");
				String transAcc = scan.next();
				int transIdx = -1;
				for (int j = 0; j < accsCnt; j++) {
					if(transAcc.equals(accs[j])) {
						transIdx = j;
					}
				}
				if(transIdx == -1) {
					System.out.println("일치하는 계좌번호가 없습니다.");
					continue;
				}
				else {
					System.out.println("이체할 금액을 입력해주세요 : ");
					int transMoney = scan.nextInt();
					if(transMoney > moneys[identifier]) {
						System.out.println("이체할 금액이 부족합니다.");
						continue;
					}
					else {
						moneys[identifier] -= transMoney;
						moneys[transIdx] += transMoney;
						System.out.println("이체가 완료되었습니다.");
					}
				}			
			}
			else if (sel == 8) {
				if(identifier == -1) {
					System.out.println("로그인 후에 이용해주세요.");
					continue;
				}
				System.out.println(accs[identifier] + "님, 현재 잔액 : " + moneys[identifier]);
			}
			else if (sel == 9) {
				
				FileWriter fw = null;
				String data = "";
				
				for (int j = 0; j <accsCnt; j++) {
					data += accs[j] + "/";
					data += pws[j]	+ "/";
					data += Integer.toString(moneys[j]);
					if(j != accsCnt - 1) {
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
			else if (sel == 10) {
				File file = new File(fileName);
				FileReader fr =  null;
				BufferedReader br = null;
				
				if(file.exists()) {
					try {
						
						fr = new FileReader(file);
						br = new BufferedReader(fr);
						
						String data = "";
						
						accs = new String[size];
						pws = new String[size];
						moneys = new int[size];
						
						while(true) {
						String info = br.readLine(); 
						if(info == null) {
							break;
						}
						data += info;
						data += "\n";
						}
						data = data.substring(0,data.length() - 1);
						
						String[] temp = data.split("\n");
						
						for (int i = 0; i < temp.length; i++) {
							String[] arr = temp[i].split("/");
							accs[i] = arr[0];
							pws[i] = arr[1];
							moneys[i] = Integer.parseInt(arr[2]);
							accsCnt++;
						}
						
						br.close();
						fr.close();	
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				
				}
			}
			else if (sel == 0) {
				break;
			}
			
		}
		scan.close();
	}
}
