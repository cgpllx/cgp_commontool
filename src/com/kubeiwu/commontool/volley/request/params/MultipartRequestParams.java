//2014-8-12
package com.kubeiwu.commontool.volley.request.params;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.http.HttpEntity;

import com.kubeiwu.commontool.volley.request.entity.MultipartEntity;
/**
 * @author  cgpllx1@qq.com (www.kubeiwu.com)
 * @date    2014-8-12
 */
public class MultipartRequestParams {
	protected ConcurrentHashMap<String, String> urlParams;
	protected ConcurrentHashMap<String, FileWrapper> fileParams;

	public MultipartRequestParams() {
		init();
	}

	public MultipartRequestParams(String key, String value) {
		init();
		put(key, value);
	}

	private void init() {
		urlParams = new ConcurrentHashMap<String, String>();
		fileParams = new ConcurrentHashMap<String, FileWrapper>();
	}

	/**
	 * 添加value到request中
	 * @param key
	 * @param value
	 */
	public void put(String key, String value) {
		if (key != null && value != null) {
			urlParams.put(key, value);
		}
	}

	/**
	 * 添加文件到request中
	 * @param key
	 * @param file
	 */
	public void put(String key, File file) {
		try {
			put(key, new FileInputStream(file), file.getName());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加stream到request中
	 * @param key
	 * @param stream
	 * @param fileName
	 */
	public void put(String key, InputStream stream, String fileName) {
		put(key, stream, fileName, null);
	}

	/**
	 * 添加stream到request中
	 * @param key
	 * @param stream
	 * @param fileName
	 * @param contentType
	 */
	public void put(String key, InputStream stream, String fileName, String contentType) {
		if (key != null && stream != null) {
			fileParams.put(key, new FileWrapper(stream, fileName, contentType));
		}
	}

	public HttpEntity getEntity() {
		HttpEntity entity = null;
		if (!fileParams.isEmpty()) {
			MultipartEntity multipartEntity = new MultipartEntity();

			// Add string params
			for (ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
				multipartEntity.addPart(entry.getKey(), entry.getValue());
			}

			// Add file params
			int currentIndex = 0;
			int lastIndex = fileParams.entrySet().size() - 1;
			for (ConcurrentHashMap.Entry<String, FileWrapper> entry : fileParams.entrySet()) {
				FileWrapper file = entry.getValue();
				if (file.inputStream != null) {
					boolean isLast = currentIndex == lastIndex;
					if (file.contentType != null) {
						multipartEntity.addPart(entry.getKey(), file.getFileName(), file.inputStream, file.contentType, isLast);
					} else {
						multipartEntity.addPart(entry.getKey(), file.getFileName(), file.inputStream, isLast);
					}
				}
				currentIndex++;
			}

			entity = multipartEntity;
		}
		return entity;
	}

	private static class FileWrapper {
		public InputStream inputStream;
		public String fileName;
		public String contentType;

		public FileWrapper(InputStream inputStream, String fileName, String contentType) {
			this.inputStream = inputStream;
			this.fileName = fileName;
			this.contentType = contentType;
		}

		public String getFileName() {
			if (fileName != null) {
				return fileName;
			} else {
				return "nofilename";
			}
		}
	}
}
