package org.txazo.search.lucene.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.junit.Test;

/**
 * Sort 排序
 * 
 * @author txazo
 * 
 */
public class SortTest extends LuceneBaseTest {

	@Test
	public void testSort() throws Exception {
		IndexWriter writer = getIndexWriter(true);
		String[] names = new String[] { "id", "price", "product", "quantity" };
		writer.addDocument(newDocument(names, new String[] { "1", "74.00", "水果-香蕉", "33" }));
		writer.addDocument(newDocument(names, new String[] { "2", "67.00", "水果-苹果", "15" }));
		writer.addDocument(newDocument(names, new String[] { "3", "67.00", "水果-草莓", "56" }));
		writer.addDocument(newDocument(names, new String[] { "4", "98.00", "饮料-雪碧", "87" }));
		writer.addDocument(newDocument(names, new String[] { "5", "12.00", "饮料-脉动", "64" }));
		writer.commit();

		setShow(false);
	}

	/**
	 * Sort.INDEXORDER SortField.FIELD_DOC 按文档排序
	 */
	@Test
	public void testSortByIndex() throws Exception {
		IndexSearcher searcher = getIndexSearcher();
		TopDocs topDocs = searcher.search(new MatchAllDocsQuery(), 1000, Sort.INDEXORDER);
		display(topDocs, searcher);
		setShow(false);
	}

	/**
	 * Sort.RELEVANCE SortField.FIELD_SCORE 按评分排序
	 */
	@Test
	public void testSortByScore() throws Exception {
		IndexSearcher searcher = getIndexSearcher();
		TopDocs topDocs = searcher.search(new TermQuery(new Term("product", "果")), 1000, Sort.RELEVANCE);
		display(topDocs, searcher);
		setShow(false);
	}

	@Test
	public void testSortByDouble() throws Exception {
		Sort sort = new Sort(new SortField("price", SortField.Type.DOUBLE, true));
		IndexSearcher searcher = getIndexSearcher();
		TopDocs topDocs = searcher.search(new MatchAllDocsQuery(), 1000, sort);
		display(topDocs, searcher);
		setShow(false);
	}

	@Test
	public void testSortByMultiField() throws Exception {
		Sort sort = new Sort(new SortField("price", SortField.Type.DOUBLE, true), new SortField("quantity", SortField.Type.INT, true));
		IndexSearcher searcher = getIndexSearcher();
		TopDocs topDocs = searcher.search(new MatchAllDocsQuery(), 1000, sort);
		display(topDocs, searcher);
		setShow(false);
	}

	public void display(TopDocs topDocs, IndexSearcher searcher) throws IOException {
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		Document hitDoc = null;
		IndexableField field = null;
		Map<String, String> jsonMap = null;
		Iterator<IndexableField> fields = null;
		for (int i = 0; i < scoreDocs.length; i++) {
			hitDoc = searcher.doc(scoreDocs[i].doc);
			jsonMap = new HashMap<String, String>();
			for (fields = hitDoc.iterator(); fields.hasNext();) {
				field = fields.next();
				jsonMap.put(field.name(), field.stringValue());
			}
			logger.info("doc {} : {}", scoreDocs[i].doc, JSONArray.fromObject(jsonMap));
		}
	}

}
