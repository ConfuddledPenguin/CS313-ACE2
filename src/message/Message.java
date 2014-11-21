package message;

/**
 * An interface for the message interface
 *
 */
public interface Message {

	/**
	 * Sets the count values
	 * 
	 * @param s The String to base the counts off
	 */
	public void setCounts(String s);
	
	/**
	 * Returns the character count
	 * 
	 * @return The number of characters
	 */
	public int getCharacterCount();
	
	/**
	 * Returns the digit count
	 * 
	 * @return The number of digits
	 */
	public int getDigitCount();
}
