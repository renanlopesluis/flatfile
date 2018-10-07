package com.ilegra.flatfile.io;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileConfiguration {

	public static final String IN_DIRECTORY = "\\data\\in";
	public static final String OUT_DIRECTORY = "\\data\\out\\%s.done.dat";
	public static final Path IN_PATH = Paths.get(System.getProperty("user.dir") + IN_DIRECTORY);
	public static final Path OUT_PATH = Paths.get(System.getProperty("user.dir") + OUT_DIRECTORY);
	public static final String EXTENSION = ".dat";
	
}