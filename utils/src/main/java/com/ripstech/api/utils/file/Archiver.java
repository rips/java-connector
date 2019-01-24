package com.ripstech.api.utils.file;

import com.ripstech.api.utils.MinimalLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
* Manages and compresses scan source code.
* */
public class Archiver extends MinimalLogging {

	final private RipsFileFilter fileFilter;
	final private Path basePath;
	private File archive;


	public Archiver (@NotNull RipsFileFilter fileFilter) {
		this.fileFilter = fileFilter;
		this.basePath = Paths.get("");
	}

	public Archiver(RipsFileFilter fileFilter, Path basePath) {
		this.fileFilter = fileFilter;
		this.basePath = basePath;
	}

	@Override
	public Archiver setLogger(@NotNull Consumer<String> logger) {
		super.logger = logger;
		return this;
	}

	/**
	 * Checks if a ZIP file already exists.
	 *
	 * @return true if exists
	 */
	public boolean checkIfZipExists() {
		return archive != null && archive.exists();
	}

	/**
	 * Removes the created ZIP file. Should be called after the file was uploaded.
	 *
	 * @return true if the deletion was successful, false otherwise.
	 *
	 */
	public boolean removeZipFile() {
		if(checkIfZipExists()) {
			boolean isDeleted = archive.delete();
			archive = null;
			return  isDeleted;
		}
		return false;
	}

	/**
	 *
	 * @param sourceCode Relative path to the source code to zip.
	 * @param targetDir Path where the zip should be created. Defaults to the standard
	 *                  temp folder if null.
	 * @return the created ZIP file or null if already exists
	 * @throws IOException Can be thrown if creation of the file fails.
	 */
	public File createZip(Path sourceCode, @Nullable File targetDir, @NotNull String archivePrefix) throws IOException{
		if (!checkIfZipExists()) {
			archive = File.createTempFile(archivePrefix,".zip", targetDir);

			sourceCode = sanitizePath(sourceCode);

			try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(archive))) {
				try (Stream<Path> stream = Files.walk(sourceCode)) {
					List<Path> pathList = stream.filter(path -> fileFilter.accept(path.toFile()))
					                            .collect(Collectors.toList());
					for (Path path : pathList) {
						ZipEntry zipEntry = new ZipEntry(sourceCode.relativize(path).toString());
						zipOutputStream.putNextEntry(zipEntry);
						Files.copy(path, zipOutputStream);
						zipOutputStream.closeEntry();
					}
				}
			}

			log("Created archive %s with extensions: %s", archive.getAbsolutePath(), fileFilter.getExtensions());
			return archive;
		}
		else {
			log("Found zip file in archive");
			return archive;
		}
	}

	/**
	 * @param sourceCode Relative path to the source code to zip.
	 * @return the created ZIP file or null if already exists
	 * @throws IOException
	 */
	public File createZip(Path sourceCode) throws IOException {
		return createZip(sourceCode, null, "source");
	}

	/**
	 * @param sourceCode Relative path to the source code to zip.
	 * @param buildToolIdentifier String which will be used as archive prefix
	 * @return the created ZIP file or null if already exists
	 * @throws IOException
	 */
	public File createZip(Path sourceCode, String buildToolIdentifier) throws IOException {
		return createZip(sourceCode, null, buildToolIdentifier);
	}

	public File getArchive() {
		return archive;
	}

	/**
	 * Validates a path.
	 * @param path The path to validate.
	 * @return The validated path.
	 */
	@NotNull
	private Path sanitizePath(Path path) {

		String absolutePath = path.toAbsolutePath().normalize().toString();

		if (!absolutePath.startsWith(basePath.toAbsolutePath().toString())) {
			throw new InvalidPathException(path.toString(),
			                               "Only paths within the current working directory are allowed");
		}

		return path.normalize();
	}
}
