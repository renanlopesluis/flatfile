package com.ilegra.flatfile.io;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public abstract class FileStream {
	
	protected String path;
	
	public abstract void write(List<String> lines) throws IOException;
	public abstract List<String> read() throws IOException;
	public  abstract void delete(File file);
}
