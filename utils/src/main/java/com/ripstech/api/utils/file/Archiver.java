package com.ripstech.api.utils.file;

import com.ripstech.api.utils.MinimalLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
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
	@Nullable
	public File createZip(Path sourceCode, @Nullable File targetDir ) throws IOException{
		if (!checkIfZipExists()) {
			archive = File.createTempFile("sources" ,".zip", targetDir);

			sourceCode = sanitizePath(sourceCode);
			File sourceCodeFile = sourceCode.toFile();

			FileOutputStream fileOutputStream = new FileOutputStream(archive);
			ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

			zipFile(sourceCodeFile, sourceCode.getFileName().toString(), zipOutputStream);
			log("Created archive %s with extensions: %s", archive.getAbsolutePath(), fileFilter.getExtensions());


			zipOutputStream.close();
			fileOutputStream.close();

			return archive;
		}
		else {
			log("Found zip file in archive");
			return null;
		}
	}

	/**
	 *
	 *
	 * @param sourceCode Relative path to the source code to zip.
	 * @return the created ZIP file or null if already exists
	 * @throws IOException
	 */
	public File createZip(Path sourceCode) throws IOException {
		return createZip(sourceCode, null);
	}

	public File getArchive() {
		return archive;
	}

	/**
	 * Zips a file or a directory. Traverses sub-directories recursively
	 * @param sourceFile File to zip.
	 * @param fileName Desired output file name.
	 * @param zipOut Zip output stream.
	 * @throws IOException Throws an exception if sourceCode is an invalid path.
	 */
	private void zipFile(File sourceFile, String fileName, ZipOutputStream zipOut) throws IOException {
		if (sourceFile.isDirectory()) {
			File[] children = sourceFile.listFiles();
			if (null != children) {
				List<File> childrenList = Arrays.asList(children);
				List<File> dirs = childrenList.stream().filter(File::isDirectory).collect(Collectors.toList());
				List<File> filteredChildren = fileFilter.filter(childrenList);
				filteredChildren.addAll(dirs);

				for (File child : filteredChildren) {
					zipFile(child, fileName + "/" + child.getName(), zipOut);
				}
			}
			return;
		}

		FileInputStream fis = new FileInputStream(sourceFile);
		ZipEntry zipEntry = new ZipEntry(fileName);
		zipOut.putNextEntry(zipEntry);
		byte[] bytes = new byte[1024];
		int length;

		while ((length = fis.read(bytes)) >= 0) {
			zipOut.write(bytes, 0, length);
		}
		fis.close();

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

		return path;
	}
}
