package org.txazo.search.lucene.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.TermQuery;
import org.junit.Test;

/**
 * Document
 * 
 * @author txazo
 * 
 */
public class DocumentTest extends LuceneBaseTest {

	@Test
	public void testAddDocument() throws Exception {
		IndexWriter writer = getIndexWriter(true);
		addDocument(writer, new Field("id", "1", TextField.TYPE_STORED), new Field("name", "狗是一种动物", TextField.TYPE_STORED));
		addDocument(writer, new Field("id", "2", TextField.TYPE_STORED), new Field("name", "猫是一种动物", TextField.TYPE_STORED));
		addDocument(writer, new Field("id", "3", TextField.TYPE_STORED), new Field("name", "苹果是一种植物", TextField.TYPE_STORED));
		addDocument(writer, new Field("id", "4", TextField.TYPE_STORED), new Field("name", "香蕉是一种植物", TextField.TYPE_STORED));
		writer.commit();
	}

	@Test
	public void testDeleteDocument() throws Exception {
		IndexWriter writer = getIndexWriter(false);
		writer.deleteDocuments(new Term("id", "3"));
		writer.deleteDocuments(new TermQuery(new Term("name", "动")));
		writer.commit();
	}

	@Test
	public void testUpdateDocument() throws Exception {
		IndexWriter writer = getIndexWriter(false);
		writer.updateDocument(new Term("id", "2"), newDocument(new Field("id", "5", TextField.TYPE_STORED), new Field("name", "猪是一种动物", TextField.TYPE_STORED)));
		List<Document> docs = new ArrayList<Document>();
		docs.add(newDocument(new Field("id", "6", TextField.TYPE_STORED), new Field("name", "草莓是一种植物", TextField.TYPE_STORED)));
		docs.add(newDocument(new Field("id", "7", TextField.TYPE_STORED), new Field("name", "橘子是一种植物", TextField.TYPE_STORED)));
		writer.updateDocuments(new Term("name", "植"), docs);
		writer.commit();
	}

}
