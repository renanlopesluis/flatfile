package com.ilegra.flatfile.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManager {

	public static List<Path> getAllFilesFromDirectory() throws IOException {
		try (Stream<Path> stream = Files.walk(Paths.get(FileConfiguration.IN_PATH.toString()))) {
			return stream.filter(Files::isRegularFile).filter(x -> x.toString().endsWith(FileConfiguration.EXTENSION))
					.collect(Collectors.toList());
		}
	}
	
	public static String extractFileName(Path path) {
		String[] files = path.toString().split("\\\\");
		return files[files.length - 1].split("\\.")[0];
	}
}
