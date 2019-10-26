package application;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import command.Command;
import command.CommandBuilder;
import command.SaveObject;
import command.SaveSprite;
import exception.Abort;
import exception.Quit;
import loader.GeometryLoader;
import loader.MaterialLoader;
import loader.SpriteLoader;
import service.database.object.loader.ObjectService;

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
	private static final String NAME = "enter resource name (for database)";
	private static final String HELP = "type \\a to abort the command, type \\q to quite the programm!";
	private static final String ABORT_MESSAGE = "command aborted!";
	private static final String OPTION = "type \\o to enter a 3DO, type \\s to enter a sprite:";
	private static final String SPRITE_MODE = "\\s";

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
		if (spriteOption(getString(OPTION))) {
			builder.setSpritePath(getString(SPRITE_PATH));
			builder.setName(getString(NAME));
			return builder.build(SaveSprite.class);
		} else {
			builder.setGeometryPath(getString(GEOMETRY_PATH));
			builder.setMaterialPath(getString(MATERIAL_PATH));
			builder.setName(getString(NAME));
			return builder.build(SaveObject.class);
		}
	}
	
	private static boolean spriteOption(String option) {
		return option.equals(SPRITE_MODE);
	}
	
	private static CommandBuilder getPreparedBuilder(ApplicationContext context) {
		CommandBuilder builder = new CommandBuilder();
		builder.setGeometryLoader(new GeometryLoader());
		builder.setMaterialLoader(new MaterialLoader());
		builder.setSpriteLoader(new SpriteLoader());
		builder.setService(context.getBean(ObjectService.class));
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
