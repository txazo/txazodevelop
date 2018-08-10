package org.txazo.search.lucene.base;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.junit.Test;

/**
 * Lucene分页查询
 * 
 * @author txazo
 * 
 */
public class SearchPageTest extends LuceneBaseTest {

	@Test
	public void testaddDocument() throws Exception {
		IndexWriter writer = getIndexWriter(true);
		String[] names = new String[] { "id" };
		for (int i = 0; i < 100; i++) {
			writer.addDocument(newDocument(names, new String[] { String.valueOf(i) }));
		}
		writer.commit();
	}

	@Test
	public void testSearchPage() throws Exception {
		search(20, 5);
		setShow(false);
	}

	public void search(int pageIndex, int pageSize) throws Exception {
		IndexSearcher searcher = getIndexSearcher();
		TopDocs topDocs = searcher.search(new MatchAllDocsQuery(), 1000);
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;

		if (pageIndex * pageSize > scoreDocs.length) {
			return;
		}

		int offset = (pageIndex - 1) * pageSize;
		int end = offset + pageSize;

		Document hitDoc = null;
		IndexableField field = null;
		Map<String, String> jsonMap = null;
		Iterator<IndexableField> fields = null;
		for (int i = offset; i < end; i++) {
			hitDoc = searcher.doc(scoreDocs[i].doc);
			jsonMap = new HashMap<String, String>();
			for (fields = hitDoc.iterator(); fields.hasNext();) {
				field = fields.next();
				jsonMap.put(field.name(), field.stringValue());
			}
			logger.info("doc {} : {}", scoreDocs[i].doc, JSONArray.fromObject(jsonMap));
		}
	}

	public void searchAfter(int pageIndex, int pageSize) throws Exception {
	}

}
