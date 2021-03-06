package com.kubeiwu.commontool.volley.request.util;

import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamProgress extends OutputStream {

	private final OutputStream outstream;
	private long bytesWritten = 0;
	private long totalSize = 0;
	private final ProgressListener progressListener;

	public OutputStreamProgress(OutputStream outstream, ProgressListener progressListener) {
		this.outstream = outstream;
		this.progressListener = progressListener;
	}

	@Override
	public void write(int b) throws IOException {
		outstream.write(b);
		if (null != progressListener) {
			bytesWritten++;
			progressListener.onProgress(bytesWritten, totalSize);
		}
	}

	@Override
	public void write(byte[] b) throws IOException {
		outstream.write(b);
		if (null != progressListener) {
			bytesWritten += b.length;
			progressListener.onProgress(bytesWritten, totalSize);
		}
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		outstream.write(b, off, len);
		if (null != progressListener) {
			bytesWritten += len;
			progressListener.onProgress(bytesWritten, totalSize);
		}
	}

	@Override
	public void flush() throws IOException {
		outstream.flush();
	}

	@Override
	public void close() throws IOException {
		outstream.close();
	}

	public static interface ProgressListener {
		void onProgress(long bytesWritten, long totalSize);
	}
}