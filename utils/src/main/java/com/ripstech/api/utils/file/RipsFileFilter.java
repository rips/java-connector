package com.ripstech.api.utils.file;

import com.ripstech.apiconnector2.Api;
import com.ripstech.apiconnector2.exception.ApiException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RipsFileFilter implements FileFilter {

	private final Set<String> extensions;

	public RipsFileFilter(Set<String> extensions) {
		this.extensions = extensions;
	}

	public RipsFileFilter(Api api, Long appId) throws ApiException {
		this.extensions = FileExtension.getByAppId(api, appId);
	}

	public RipsFileFilter(Api api, String language) throws ApiException {
		this.extensions = FileExtension.getByLang(api, language);
	}

	@Override
	public boolean accept(File file) {
		if(!file.isFile()) {
			return false;
		}
		return extensions.stream().anyMatch(s -> file.getName().endsWith("." + s));
	}

	/**
	 * Filters a given list by file types.
	 *
	 * @return filtered list
	 */
	@Contract(pure = true)
	public List<File> filter(@NotNull List<File> fileList) {
		return fileList.stream().filter(this::accept).collect(Collectors.toList());
	}

	public Set<String> getExtensions() {
		return extensions;
	}
}
