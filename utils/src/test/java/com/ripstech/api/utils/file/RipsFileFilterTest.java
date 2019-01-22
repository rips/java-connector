package com.ripstech.api.utils.file;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.spy;

@Disabled
public class RipsFileFilterTest {
	private RipsFileFilter ripsFileFilter;
	private List<File> files = Arrays.asList(new File("test.java"),
	                                         new File("test.jar"),
	                                         new File("test.class"),
	                                         new File("test.php"));



	@BeforeEach
	public void setUp() {
		ripsFileFilter = spy(RipsFileFilter.class);
	}

	@Test
	public void should_fail_filter() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> ripsFileFilter.filter(null));
	}

	@Test
	public void should_pass_filter() {
		List<File> filteredFiles = ripsFileFilter.filter(files);
		Assertions.assertEquals(filteredFiles.size(), 2);
	}

}
