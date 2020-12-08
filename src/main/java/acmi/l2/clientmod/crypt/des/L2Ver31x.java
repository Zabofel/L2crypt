package acmi.l2.clientmod.crypt.des;

/**
 * @author Mifesto
 */
public interface L2Ver31x
{
	byte[] DES_KEY_311 = getXORKey("HIhiHIYoMan~");

	private static byte[] getXORKey(String sKey)
	{
		byte[] key = sKey.getBytes();
		byte[] keyXor = new byte[key.length];
		for (int i = 0; i < key.length; ++i) {
			keyXor[i % 8] ^= key[i];
		}
		return keyXor;
	}
}
