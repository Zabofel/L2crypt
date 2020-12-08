package acmi.l2.clientmod.crypt.des;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

/**
 * @author Mifesto
 */
public class L2Ver31xOutputStream extends OutputStream implements L2Ver31x
{
	private final CipherOutputStream out;

	public L2Ver31xOutputStream(OutputStream output, byte[] key)
	{
		Objects.requireNonNull(key, "key");
		Objects.requireNonNull(output, "stream");

		try {
			DESKeySpec dks = new DESKeySpec(key);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
			SecretKey desKey = skf.generateSecret(dks);

			var cipher = Cipher.getInstance("DES/ECB/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, desKey);
			out = new CipherOutputStream(output, cipher);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void write(int b) throws IOException
	{
		out.write(b);
	}

	@Override
	public void flush() throws IOException
	{
		out.flush();
	}

	@Override
	public void close() throws IOException
	{
		out.close();
	}
}
