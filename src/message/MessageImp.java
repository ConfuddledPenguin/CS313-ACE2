package message;

import java.io.Serializable;

/**
 * An implementation of the message interface
 * 
 * @author Tom Maxwell
 *
 */
public class MessageImp implements Message, Serializable {

	private static final long serialVersionUID = -8732565995736487562L;
	private int charCount = 0;
	private int digitCount = 0;
	
	/**
	 * Sets the count values
	 * 
	 * @param s The String to base the counts off
	 */
	@Override
	public void setCounts(String s) {
		
		if(s == null){
			charCount = 0;
			digitCount = 0;
			return;
		}
		
		charCount = s.length();
		
		digitCount = 0; // first reset the value
		for(int i = 0; i < charCount; i++){
		
			if ( Character.isDigit(s.charAt(i))){
				digitCount++;
			}
		}
		
	}

	/**
	 * Returns the character count
	 * 
	 * @return The number of characters
	 */
	@Override
	public int getCharacterCount() {
		return charCount;
	}

	/**
	 * Returns the digit count
	 * 
	 * @return The number of digits
	 */
	@Override
	public int getDigitCount() {
		return digitCount;
	}

}
