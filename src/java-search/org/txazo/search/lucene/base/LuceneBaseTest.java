package org.txazo.search.lucene.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.After;
import org.junit.Before;
import org.txazo.test.base.BaseTest;

/**
 * LuceneBaseTest
 * 
 * @author txazo
 * 
 */
public class LuceneBaseTest extends BaseTest {

	private boolean show = true;

	private String indexDirectory;
	private Directory directory;

	private Version version;
	private Analyzer analyzer;

	private IndexWriter writer;

	private DirectoryReader reader;
	private IndexSearcher searcher;

	protected void init(AnalyzerType type) throws Exception {
		init(null, null, type);
	}

	protected void init(String indexDirectory, Version version, AnalyzerType type) throws Exception {
		if (indexDirectory != null) {
			this.indexDirectory = indexDirectory;
		}
		if (version != null) {
			this.version = version;
		}

		Class<?> clazz = Class.forName(type.getClassName());
		Constructor<?> constructor = clazz.getDeclaredConstructor(Version.class);
		analyzer = (Analyzer) constructor.newInstance(version);
	}

	@Before
	public void before() throws IOException {
		indexDirectory = "E:/lucene";
		version = Version.LUCENE_4_9;
		analyzer = new StandardAnalyzer(version);
		directory = FSDirectory.open(new File(indexDirectory));
	}

	protected IndexWriter getIndexWriter(boolean create) throws IOException {
		IndexWriterConfig config = new IndexWriterConfig(version, analyzer);
		if (create) {
			config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
		}
		writer = new IndexWriter(directory, config);
		return writer;
	}

	protected DirectoryReader getDirectoryReader() throws IOException {
		reader = DirectoryReader.open(directory);
		return reader;
	}

	protected IndexSearcher getIndexSearcher() throws IOException {
		searcher = new IndexSearcher(getDirectoryReader());
		return searcher;
	}

	protected Document newDocument(Field... fields) {
		Document document = new Document();
		if (fields != null && fields.length > 0) {
			for (Field field : fields) {
				document.add(field);
			}
		}
		return document;
	}

	protected Document newDocument(String[] fieldNames, String[] fieldValues) {
		Document document = new Document();
		if (fieldNames != null && fieldNames.length > 0 && fieldValues != null && fieldNames.length == fieldValues.length) {
			for (int i = 0; i < fieldNames.length; i++) {
				document.add(new Field(fieldNames[i], fieldValues[i], TextField.TYPE_STORED));
			}
		}
		return document;
	}

	protected void addDocument(IndexWriter writer, Field... fields) throws IOException {
		if (writer != null) {
			writer.addDocument(newDocument(fields));
		}
	}

	protected void setShow(boolean show) {
		this.show = show;
	}

	protected void showDocNum() throws IOException {
		DirectoryReader reader = getDirectoryReader();
		logger.info("maxDoc: {} \t numDocs: {} \t numDeletedDocs: {}", reader.maxDoc(), reader.numDocs(), reader.numDeletedDocs());
	}

	protected void showDocDesc(String... fields) throws Exception {
		showDocDesc(1000, fields);
	}

	protected void showDocDesc(int max, String... fields) throws Exception {
		if (fields != null && fields.length > 0) {
			Query query = new WildcardQuery(new Term(fields[0], "*"));
			TopDocs topDocs = getIndexSearcher().search(query, max);
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			Document hitDoc = null;
			Map<String, String> jsonMap = null;
			for (int i = 0; i < scoreDocs.length; i++) {
				hitDoc = searcher.doc(scoreDocs[i].doc);
				jsonMap = new HashMap<String, String>();
				for (String field : fields) {
					jsonMap.put(field, hitDoc.get(field));
				}
				logger.info("doc {} : {}", scoreDocs[i].doc, JSONArray.fromObject(jsonMap));
			}
		}
	}

	protected void showDocDesc() throws Exception {
		showDocDesc(1000);
	}

	protected void showDocDesc(int max) throws Exception {
		TopDocs topDocs = getIndexSearcher().search(new MatchAllDocsQuery(), max);
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

	protected void search(Query query, int n) throws IOException {
		IndexSearcher searcher = getIndexSearcher();
		TopDocs topDocs = searcher.search(query, n);
		search(getIndexSearcher(), topDocs);
	}

	protected void search(Query query, int n, Sort sort) throws IOException {
		IndexSearcher searcher = getIndexSearcher();
		TopDocs topDocs = searcher.search(query, n, sort);
		search(getIndexSearcher(), topDocs);
	}

	protected void search(Query query, Filter filter, int n) throws IOException {
		IndexSearcher searcher = getIndexSearcher();
		TopDocs topDocs = searcher.search(query, filter, n);
		search(getIndexSearcher(), topDocs);
	}

	protected void search(Query query, Filter filter, int n, Sort sort) throws IOException {
		IndexSearcher searcher = getIndexSearcher();
		TopDocs topDocs = searcher.search(query, filter, n, sort);
		search(getIndexSearcher(), topDocs);
	}

	protected void search(Query query, Filter filter, int n, Sort sort, boolean doDocScores, boolean doMaxScore) throws IOException {
		IndexSearcher searcher = getIndexSearcher();
		TopDocs topDocs = searcher.search(query, filter, n, sort, doDocScores, doMaxScore);
		search(getIndexSearcher(), topDocs);
	}

	private void search(IndexSearcher searcher, TopDocs topDocs) throws IOException {
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
		setShow(false);
	}

	@After
	public void after() throws Exception {
		if (show) {
			showDocNum();
			showDocDesc();
		}

		if (writer != null) {
			writer.close();
		}
		if (reader != null) {
			reader.close();
		}
		directory.close();
	}

	public enum AnalyzerType {

		SimpleAnalyzer("org.apache.lucene.analysis.core.SimpleAnalyzer"), StandardAnalyzer("org.apache.lucene.analysis.standard.StandardAnalyzer"), CJKAnalyzer(
				"org.apache.lucene.analysis.cjk.CJKAnalyzer"), SmartChineseAnalyzer("org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer");

		private String className;

		private AnalyzerType(String className) {
			this.className = className;
		}

		public String getClassName() {
			return className;
		}

	}

}
