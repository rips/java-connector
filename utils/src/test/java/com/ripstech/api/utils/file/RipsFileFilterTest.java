package com.ripstech.api.utils.file;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


public class RipsFileFilterTest {
	private RipsFileFilter ripsFileFilter = new RipsFileFilter(new HashSet<>(Arrays.asList("java", "jar")));
	private static List<File> files = Arrays.asList(new File("test.java"),
	                                         new File("test.jar"),
	                                         new File("test.class"),
	                                         new File("test.php"));
	@BeforeAll
	static void createFiles() throws IOException {
		for (File file : files) {
			file.createNewFile();
		}
	}

	@Test
	public void should_pass_filter() {

		List<File> filteredFiles = ripsFileFilter.filter(files);
		Assertions.assertEquals(2, filteredFiles.size());
	}

	@AfterAll
	static void deleteFiles() {
		for (File file : files) {
			file.delete();
		}
	}

}
