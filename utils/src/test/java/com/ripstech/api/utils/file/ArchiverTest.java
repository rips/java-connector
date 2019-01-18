package com.ripstech.api.utils.file;

import com.ripstech.api.utils.file.Archiver;
import com.ripstech.api.utils.file.RipsFileFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.nio.file.Paths;

import static org.mockito.Mockito.mock;

class ArchiverTest {

  @Mock
  static RipsFileFilter fileFilter;

  private Archiver archiver = new Archiver(fileFilter);

  @BeforeAll
  static void init() {
    fileFilter = mock(RipsFileFilter.class);
  }

  @Test
  void createAndDeleteZip() throws IOException {

    archiver.createZip(Paths.get("src"));


    Assertions.assertTrue(archiver.checkIfZipExists());
    Assertions.assertTrue(archiver.removeZipFile());
    Assertions.assertFalse(archiver.checkIfZipExists());
  }

  @Test
  void createAndDeleteZipInDirectory() throws IOException {

    archiver.createZip(Paths.get("src"), Paths.get("/tmp").toFile());


    Assertions.assertTrue(archiver.checkIfZipExists());
    Assertions.assertTrue(archiver.removeZipFile());
    Assertions.assertFalse(archiver.checkIfZipExists());
  }
}
