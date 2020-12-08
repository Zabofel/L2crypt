package acmi.l2.clientmod.crypt.des;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author Mifesto
 */
public class L2Ver31xInputStream extends InputStream implements L2Ver31x
{
	private final CipherInputStream in;

	public L2Ver31xInputStream(InputStream input, byte[] key)
	{
		Objects.requireNonNull(key, "key");
		Objects.requireNonNull(input, "stream");

		try {
			DESKeySpec dks = new DESKeySpec(key);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
			SecretKey desKey = skf.generateSecret(dks);

			var cipher = Cipher.getInstance("DES/ECB/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, desKey);
			in = new CipherInputStream(input, cipher);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int read() throws IOException
	{
		return in.read() & 0xff;
	}

	@Override
	public int available() throws IOException
	{
		return in.available();
	}

	@Override
	public void close() throws IOException
	{
		in.close();
	}
}
