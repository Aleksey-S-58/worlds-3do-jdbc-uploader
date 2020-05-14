package loader;

/**
 * This interface defines some reader which will read some object from file as an array of bytes.
 * @author Aleksey Shishkin
 *
 */
public interface ObjectReader {

	public byte[] read(String fileName);
}
