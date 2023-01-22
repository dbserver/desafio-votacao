package br.com.vitt.apivotacao.validation;

public class Time {
	
	public static boolean isFormatTime(String time) {
		
		System.out.println(time.length());
		if(time.length()!=16) return false;
		
		String teste = "_";
		for(int i = 4; i< 14; i+=3) {
			if(time.charAt(i) != teste.charAt(0)) return false;
		}
		
		time = time.replace("_", "");		
		if(!time.matches("^[0-9]+$")) return false;
		
		return true;
	}
	
	

}
