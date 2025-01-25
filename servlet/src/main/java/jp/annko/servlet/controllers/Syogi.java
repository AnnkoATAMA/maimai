package jp.annko.servlet.controllers;

import java.util.ArrayList;
import java.util.List;

public class Syogi {
	
	public List<List<Integer>> initaialData(){
		List<List<Integer>> bord = new ArrayList<>();
		for(int i = 0; i < 20;i++){
			List<Integer> line = new ArrayList<>();
			for(int j = 0;j < 9;j++) {
				line.add(0);
			}
			bord.add(line);
		}
		
		for(int i = 0;i < 4;i++) {
			bord.get(0).set(i, i + 2);
			bord.get(0).set(8 - i, i + 2);
			bord.get(8).set(i, i + 2);
			bord.get(8).set(8 - i, i + 2);
		}
		for(int i = 0;i < 9;i++) {
			bord.get(2).set(i, 1);
			bord.get(6).set(i, 1);
		}
		
		for(int i : new int[] {0,8}) {
			bord.get(i).set(4, 8);
		}
		
		for(int i : new int[] {1,7}) {
			bord.get(i).set(-i + 8, 6);
			bord.get(i).set(i, 7);
		}
		
		
		
		for(int i : new int[] {17,19}) {
			for(int j = 0;j < 9;j++) {
				bord.get(i).set(j, 1);
			}
		}
		
		bord.get(18).set(1, 1);
		bord.get(18).set(7, 1);
		
		for(int i : new int[] {11,13}) {
			for(int j = 0;j < 9;j++) {
				bord.get(i).set(j, 2);
			}
		}

		bord.get(12).set(1, 2);
		bord.get(12).set(7, 2);
		
		return bord;
	}

	public String pieceName(int pieceNumber) {
		if(pieceNumber == 1) {
			return "歩";
		}else if(pieceNumber == 2) {
			return "香";
		}else if(pieceNumber == 3) {
			return "桂";
		}else if(pieceNumber == 4) {
			return "銀";
		}else if(pieceNumber == 5) {
			return "金";
		}else if(pieceNumber == 6) {
			return "角";
		}else if(pieceNumber == 7) {
			return "飛";
		}else if(pieceNumber == 8) {
			return "王";
		}else if(pieceNumber == 11){
			return "と";
		}else if(pieceNumber == 12){
			return "杏";
		}else if(pieceNumber == 13){
			return "圭";
		}else if(pieceNumber == 14){
			return "全";
		}else if(pieceNumber == 16){
			return "馬";
		}else if(pieceNumber == 17){
			return "竜";
		}
		
		return " ";
	}
	
	public List<List<List<Integer>>> pieceMoveData(){
		
		List<List<List<Integer>>> pieceMoves = new ArrayList<>();
		
		for(int pieceNumber = 0; pieceNumber < 18;pieceNumber++) {
		
		
			List<List<Integer>> pieceMove = new ArrayList<>();
			for(int i = 0;i < 4;i++) {
				List<Integer> line = new ArrayList<>();
				for(int j = 0;j < 3;j++) {
					line.add(0);
				}
				pieceMove.add(line);
			}
			
			if(pieceNumber == 1) {
				pieceMove.get(1).set(1,1);
				
			} else if(pieceNumber == 2) {
				pieceMove.get(1).set(1, 2);
				
			} else if(pieceNumber == 3) {
				pieceMove.get(0).set(0, 1);
				pieceMove.get(0).set(2, 1);
			
			} else if(pieceNumber == 4) {
				for(int i = 0;i < 3;i++) {
					pieceMove.get(1).set(i, 1);
				}
				pieceMove.get(3).set(0, 1);
				pieceMove.get(3).set(2, 1);
				
			} else if(pieceNumber == 5) {
				for(int i = 0;i < 3;i++) {
					pieceMove.get(1).set(i, 1);
				}
				pieceMove.get(2).set(0, 1);
				pieceMove.get(2).set(2, 1);
				pieceMove.get(3).set(1, 1);
				
			} else if(pieceNumber == 6) {
				pieceMove.get(1).set(0, 2);
				pieceMove.get(1).set(2, 2);
				pieceMove.get(3).set(0, 2);
				pieceMove.get(3).set(2, 2);
				
			} else if(pieceNumber == 7) {
				pieceMove.get(1).set(1, 2);
				pieceMove.get(2).set(0, 2);
				pieceMove.get(2).set(2, 2);
				pieceMove.get(3).set(1, 2);
				
			} else if(pieceNumber == 8) {
				for(int i = 0;i < 3;i++) {
					pieceMove.get(1).set(i, 1);
					pieceMove.get(3).set(i, 1);
				}
				pieceMove.get(2).set(0, 1);
				pieceMove.get(2).set(2, 1);
				
			} else if(pieceNumber == 11) {
				for(int i = 0;i < 3;i++) {
					pieceMove.get(1).set(i, 1);
				}
				pieceMove.get(2).set(0, 1);
				pieceMove.get(2).set(2, 1);
				pieceMove.get(3).set(1, 1);
			} else if(pieceNumber == 12) {
				for(int i = 0;i < 3;i++) {
					pieceMove.get(1).set(i, 1);
				}
				pieceMove.get(2).set(0, 1);
				pieceMove.get(2).set(2, 1);
				pieceMove.get(3).set(1, 1);
			} else if(pieceNumber == 13) {
				for(int i = 0;i < 3;i++) {
					pieceMove.get(1).set(i, 1);
				}
				pieceMove.get(2).set(0, 1);
				pieceMove.get(2).set(2, 1);
				pieceMove.get(3).set(1, 1);
			} else if(pieceNumber == 14) {
				for(int i = 0;i < 3;i++) {
					pieceMove.get(1).set(i, 1);
				}
				pieceMove.get(2).set(0, 1);
				pieceMove.get(2).set(2, 1);
				pieceMove.get(3).set(1, 1);
			} else if(pieceNumber == 16) {
				pieceMove.get(1).set(0, 2);
				pieceMove.get(1).set(2, 2);
				pieceMove.get(3).set(0, 2);
				pieceMove.get(3).set(2, 2);
				
				pieceMove.get(1).set(1, 1);
				pieceMove.get(2).set(0, 1);
				pieceMove.get(2).set(2, 1);
				pieceMove.get(3).set(1, 1);
			} else if(pieceNumber == 17) {
				pieceMove.get(1).set(1, 2);
				pieceMove.get(2).set(0, 2);
				pieceMove.get(2).set(2, 2);
				pieceMove.get(3).set(1, 2);
				
				pieceMove.get(1).set(0, 1);
				pieceMove.get(1).set(2, 1);
				pieceMove.get(3).set(0, 1);
				pieceMove.get(3).set(2, 1);
			}
			
			pieceMoves.add(pieceMove);
		}
		
		return pieceMoves;
	}

	public int isFriendOrFoe(int isFoeNuumber) {
		if (isFoeNuumber == 2) {
			return 180;
		} else {
			return 0;
		}
	}
}
