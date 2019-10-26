package loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a simple reader that provides reading of objects from file.
 * @author Aleksey Shishkin
 *
 */
public class DataReader implements ObjectReader {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataReader.class);
	
	protected byte[] readFile(String name) throws IOException {
		// TODO simplify this method, may be to use autoclosable.
		InputStream stream = new FileInputStream(new File(name));
		int available = stream.available();
		byte[] buffer = new byte[available];
		LinkedList<Byte> bytes = new LinkedList<Byte>();
		LOGGER.info("<----- Begin to read file {} ----->", name);
		while (available != 0) {
			int k = stream.read(buffer);
			for (int i = 0; i < k; i++)
				bytes.add(buffer[i]);
			available = stream.available();
			buffer = new byte[available];
		}
		stream.close();
		byte[] result = new byte[bytes.size()];
		int i = 0;
		for (byte b : bytes) {
			result[i] = b;
			i++;
		}
		return result;
	}

	@Override
	public byte[] read(String fileName) {
		try {
			return readFile(fileName);
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			return new byte[0];
		}
	}

}
