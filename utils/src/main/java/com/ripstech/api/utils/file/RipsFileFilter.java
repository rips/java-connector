package com.ripstech.api.utils.file;

import com.ripstech.api.connector.Api;
import com.ripstech.api.connector.exception.ApiException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RipsFileFilter implements FileFilter {

	private final Set<String> extensions;
	private boolean includeHidden = false;

	public RipsFileFilter(Set<String> extensions) {
		this.extensions = extensions;
	}

	public RipsFileFilter(Api api, Long appId) throws ApiException {
		this.extensions = FileExtension.getByAppId(api, appId);
	}

	public RipsFileFilter(Api api, String language) throws ApiException {
		this.extensions = FileExtension.getByLang(api, language);
	}

	public void setIncludeHidden(boolean includeHidden) {
		this.includeHidden = includeHidden;
	}

	@Override
	public boolean accept(File file) {
		if(!file.isFile()) {
			return false;
		}
		if(!includeHidden && file.isHidden()) {
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
