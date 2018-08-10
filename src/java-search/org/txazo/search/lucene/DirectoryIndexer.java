package org.txazo.search.lucene;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;

public class DirectoryIndexer {

	public static void index(File root, IndexWriter writer) throws IOException {
		File[] childFiles = root.listFiles();
		File child = null;
		for (int i = 0; i < childFiles.length; i++) {
			child = childFiles[i];
			if (child.isDirectory()) {
				index(child, writer);
			} else {
				Document document = new Document();
				document.add(new TextField("name", child.getName(), Field.Store.YES));
				document.add(new TextField("path", child.getAbsolutePath(), Field.Store.YES));
				document.add(new LongField("size", child.length(), Field.Store.YES));
				document.add(new LongField("lastModifyTime", child.lastModified(), Field.Store.YES));
				document.add(new TextField("content", new FileReader(child)));
				writer.addDocument(document);

				System.out.println("index\t" + child.getAbsolutePath() + File.separator + child.getName());
			}
		}
	}

}
