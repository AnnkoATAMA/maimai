package jp.annko.servlet.controllers;

import java.util.List;

public class Json {
	public String parseJson(List<List<Integer>> data) {
		StringBuilder json = new StringBuilder("[");
		
		for(int i = 0;i < data.size();i++) {
			json.append("[");
			
			List<Integer> dataLine = data.get(i);
			for(int j = 0;j < dataLine.size();j++) {
				json.append(dataLine.get(j));
				if (j < dataLine.size() - 1) {
                    json.append(",");
                }
			}
			
			json.append("]");
			if (i < data.size() - 1) {
                json.append(",");
            }
		}
		
		json.append("]");
		
		return json.toString();
	}
	
	public String parseJsonThree(List<List<List<Integer>>> data) {
		StringBuilder json = new StringBuilder("[");
		
		for(int pieceData = 0;pieceData < data.size();pieceData++) {
			json.append("[");
			List<List<Integer>> twoData = data.get(pieceData);
			
			for(int i = 0;i < twoData.size();i++) {
				json.append("[");
				
				List<Integer> dataLine = twoData.get(i);
				for(int j = 0;j < dataLine.size();j++) {
					json.append(dataLine.get(j));
					if (j < dataLine.size() - 1) {
	                    json.append(",");
	                }
				}
				
				json.append("]");
				if (i < twoData.size() - 1) {
	                json.append(",");
	            }
			}
			
			json.append("]");
			if (pieceData < data.size() - 1) {
                json.append(",");
            }
		}
		
		json.append("]");
		
		System.out.print(json.toString());
		
		return json.toString();
	}
}
