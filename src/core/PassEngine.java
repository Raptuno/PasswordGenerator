package core;
import org.apache.commons.lang3.RandomStringUtils;

public class PassEngine {
	public int randomizer() {
		return (int)(Math.random()*128);
	}
	
	public int digitRandom() {
		return (int)(Math.random()*(9-0));
	}
	
	public String PassGen(int length, boolean includeSpaces) {
		return includeSpaces ? RandomStringUtils.randomPrint(length) : RandomStringUtils.randomAscii(length);
	}
}
