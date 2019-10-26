package command;

/**
 * This interface defines a command that will be executed 
 * to save a data (3DO or anything else) to database.
 * @author Aleksey Shishkin
 *
 */
public interface Command {

	public boolean execute();
	
}
