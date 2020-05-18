package application;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import command.Command;
import command.CommandBuilder;
import command.SaveCustomObject;
import command.SaveObject;
import command.SaveSprite;
import exception.Abort;
import exception.Quit;
import loader.CustomObjectLoader;
import loader.GeometryLoader;
import loader.MaterialLoader;
import loader.SpriteLoader;
import service.database.object.loader.DataService;

/**
 * 
 * @author Aleksey Shishkin
 *
 */
public class Application {
	
	private static final String ABORT = "\\a";
	private static final String QUIT = "\\q";
	private static final String GEOMETRY_PATH = "Enter a path to the geometry file:";
	private static final String MATERIAL_PATH = "Enter a path to the material file:";
	private static final String SPRITE_PATH = "Enter a path to the sprite file:";
	private static final String CUSTOM_BJECT_PATH = "Enter a path to the custom object file:";
	private static final String NAME = "enter resource name (for database)";
	private static final String HELP = "type \\a to abort the command, type \\q to quite the programm!";
	private static final String ABORT_MESSAGE = "command aborted!";
	private static final String OPTION = "type \\o to enter a 3DO, type \\s to enter a sprite, type \\c to enter a custom object:";
	private static final String SPRITE_MODE = "\\s";
	private static final String OBJECT_MODE = "\\o";
	private static final String CUSTOM_OBJECT_MODE = "\\c";
	private static final String TABLE_NAME = "Enter a table name for the custom object:";

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext("config");

		CommandBuilder builder = getPreparedBuilder(context);
		System.out.println(HELP);
		try {
			while(true) {
				try {
					Command command = getCommand(builder);
					command.execute();
				} catch (Abort e) {
					System.out.println(ABORT_MESSAGE);
				}
			}
		} catch (Quit e) {
			System.out.println("Exit!");
		}
	}

	private static Command getCommand(CommandBuilder builder) {
		String option = getString(OPTION);
		if (spriteOption(option)) {
			builder.setSpritePath(getString(SPRITE_PATH));
			builder.setName(getString(NAME));
			return builder.build(SaveSprite.class);
		} else {
			if (objectOption(option)) {
				builder.setGeometryPath(getString(GEOMETRY_PATH));
				builder.setMaterialPath(getString(MATERIAL_PATH));
				builder.setName(getString(NAME));
				return builder.build(SaveObject.class);
			}
			if (customObjectOption(option)) {
				// Custom object option.
				builder.setCustomObjectPath(getString(CUSTOM_BJECT_PATH));
				builder.setName(getString(NAME));
				builder.setTableName(getString(TABLE_NAME));
				return builder.build(SaveCustomObject.class);
			}
			System.out.println("Wrong mode:");
			throw new Abort();
		}
	}
	
	private static boolean customObjectOption(String option) {
		return option.equals(CUSTOM_OBJECT_MODE);
	}

	private static boolean objectOption(String option) {
		return option.equals(OBJECT_MODE);
	}

	private static boolean spriteOption(String option) {
		return option.equals(SPRITE_MODE);
	}

	private static CommandBuilder getPreparedBuilder(ApplicationContext context) {
		CommandBuilder builder = new CommandBuilder();
		builder.setGeometryLoader(new GeometryLoader());
		builder.setMaterialLoader(new MaterialLoader());
		builder.setSpriteLoader(new SpriteLoader());
		builder.setCustomObjectLoader(new CustomObjectLoader());
		DataService service = context.getBean(DataService.class);
		builder.setService(service);
		return builder;
	}
	
	private static void check(String command) {
		if (command == null || command.equals(ABORT)) throw new Abort();
		if (command.equals(QUIT)) throw new Quit();
	}
	
	private static String getString(String argument) {
		System.out.println(argument);
		Scanner scanner = new Scanner(System.in);
		String result = scanner.nextLine();
		check(result);
		return result;
	}

}
