package connection;

import java.util.concurrent.ThreadLocalRandom;

public class Encrypter {

	private String object;

	private int keyShifterRange_lower_key = 0;
	private int keyShifterRange_upper_key = 10;
	@SuppressWarnings("unused")
	private int RandomShifter = ThreadLocalRandom.current().nextInt(keyShifterRange_lower_key, keyShifterRange_upper_key);

	public Encrypter(String Object) {
		this.object = Object;
	}

	public Encrypter(String plain_text, int keyShifterRange_lower_key, int keyShifterRange_upper_key) {
		this.object = plain_text;
		this.keyShifterRange_lower_key = keyShifterRange_lower_key;
		this.keyShifterRange_upper_key = keyShifterRange_upper_key;
	}

	public Encrypter setKeyShifterRange(int lower, int upper) {
		this.keyShifterRange_lower_key = lower;
		this.keyShifterRange_upper_key = upper;

		return this;
	}

	/**
	 * enciphers the plain text, which has been passed to the Constructor
	 * @return
	 */
	public Encrypter encipher() {
		String temp = "enciphered " + object;
		this.object = temp;
		return this;
	}

	public Encrypter decipher() {
		this.object = object.substring(object.indexOf(" ") + 1);
		return this;
	}

	public Encrypter setObject(String object) {

		this.object = object;

		return this;
	}

	public String getEncipheredObject() {
		return this.object;
	}

	public String getDecipheredObject() {
		return this.object;
	}

}
