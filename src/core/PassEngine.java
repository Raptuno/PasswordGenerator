package core;

public class PassEngine {
	public int randomizer() {
		return (int)(Math.random()*128);
	}
	
	public int digitRandom() {
		return (int)(Math.random()*(9-0));
	}
	
	public String PassGen(int length) {
		StringBuilder pwd=new StringBuilder();
		char rndChar;
		
		boolean nccas;
		
		for (int i=0; i<length; i++) {
			rndChar=(char) randomizer();
			nccas=(!Character.isISOControl(rndChar)&&!Character.isWhitespace(rndChar));
			
			if(nccas) {
				pwd.append(rndChar);
			} else {
				i--;
			}
		}
		
		return pwd.toString();
	}
}
