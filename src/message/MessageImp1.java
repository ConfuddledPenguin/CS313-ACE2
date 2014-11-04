package message;

import java.io.Serializable;

public class MessageImp1 implements Message, Serializable {

	private int charCount = 0;
	private int digitCount = 0;
	

	@Override
	public void setCounts(String s) {
		
		charCount = s.length();
		
		for(int i = 0; i < charCount; i++){
		
			if ( Character.isDigit(s.charAt(i))){
				digitCount++;
			}
		}
		
	}

	@Override
	public int getCharacterCount() {
		return charCount;
	}

	@Override
	public int getDigitCount() {
		return digitCount;
	}

}
