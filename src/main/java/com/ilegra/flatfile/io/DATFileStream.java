package com.ilegra.flatfile.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DATFileStream extends FileStream {
	
	private static final String LINE_BREAK = "\n";

	public DATFileStream(String path) {
		super();
		super.path = path;
	}
	
	@Override
	public void write(List<String> lines) throws IOException {
		File file = new File(super.path);
		file.getParentFile().mkdirs();
		try {
			OutputStream outputStream = new FileOutputStream(file);
			file.createNewFile();
				for (String line : lines) {
					outputStream.write(line.getBytes());
					outputStream.write(LINE_BREAK.getBytes());
				}
			
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<String> read() throws IOException {
		List<String> lines = new ArrayList<>();
		try {
			InputStream is = new FileInputStream(super.path);
			Scanner reader = new Scanner(is);
			while (reader.hasNext()) {
				lines.add(reader.nextLine());
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	@Override
	public void delete(File file) {
		file.delete();		
	}

}
