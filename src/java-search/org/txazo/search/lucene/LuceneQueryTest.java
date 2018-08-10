package org.txazo.search.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.MultiPhraseQuery;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.RegexpQuery;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.search.spans.SpanFirstQuery;
import org.apache.lucene.search.spans.SpanNearQuery;
import org.apache.lucene.search.spans.SpanNotQuery;
import org.apache.lucene.search.spans.SpanOrQuery;
import org.apache.lucene.search.spans.SpanQuery;
import org.apache.lucene.search.spans.SpanTermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

public class LuceneQueryTest extends BaseTest {

	private static Version version = Version.LUCENE_4_9;
	private static Analyzer analyzer;
	private static Directory directory;
	private static IndexWriter writer;
	private static DirectoryReader reader;
	private static IndexSearcher searcher;
	private static TopDocs topDocs;

	private final static boolean createIndex = false;

	@BeforeClass
	public static void init() throws IOException {
		analyzer = new StandardAnalyzer(version);
		directory = FSDirectory.open(new File("F:/lucene"));

		if (createIndex) {
			createIndex();
		}

		reader = DirectoryReader.open(directory);
		searcher = new IndexSearcher(reader);
	}

	@AfterClass
	public static void close() throws IOException {
		reader.close();
		directory.close();
	}

	private static void createIndex() throws IOException {
		IndexWriterConfig config = new IndexWriterConfig(version, analyzer);
		config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
		writer = new IndexWriter(directory, config);
		writer.deleteAll();
		DirectoryIndexer.index(new File("F:/GraduationProject/txazo/txazodevelop/src"), writer);
		writer.close();
	}

	@Test
	public void testTermQuery() throws IOException {
		Query query = new TermQuery(new Term("content", "package"));
		topDocs = searcher.search(query, 1000);
		logger.info("{}", topDocs.totalHits);
	}

	@Test
	public void testBooleanQuery() throws IOException {
		BooleanQuery query = new BooleanQuery();
		query.add(new TermQuery(new Term("name", "java")), Occur.MUST);
		query.add(new TermQuery(new Term("content", "package")), Occur.MUST_NOT);
		topDocs = searcher.search(query, 1000);
		logger.info("{}", topDocs.totalHits);
	}

	@Test
	public void testNumericRangeQuery() throws IOException {
		Query query = NumericRangeQuery.newLongRange("size", 1000L, 1000000L, true, true);
		topDocs = searcher.search(query, 1000);
		logger.info("{}", topDocs.totalHits);
	}

	@Test
	public void testTermRangeQuery() throws IOException {
		Query query = TermRangeQuery.newStringRange("content", "in", "iv", true, true);
		topDocs = searcher.search(query, 1000);
		logger.info("{}", topDocs.totalHits);
	}

	@Test
	public void testPrefixQuery() throws IOException {
		Query query = new PrefixQuery(new Term("content", "package"));
		topDocs = searcher.search(query, 1000);
		logger.info("{}", topDocs.totalHits);
	}

	@Test
	public void testPhraseQuery() throws IOException {
		PhraseQuery query = new PhraseQuery();
		query.add(new Term("content", "static"));
		query.add(new Term("content", "final"));
		query.setSlop(1);
		topDocs = searcher.search(query, 1000);
		logger.info("{}", topDocs.totalHits);
	}

	@Test
	public void testMultiPhraseQuery() throws IOException {
		MultiPhraseQuery query = new MultiPhraseQuery();
		query.add(new Term("content", "private"));
		query.add(new Term[] { new Term("content", "static"), new Term("content", "final") });
		query.add(new Term("content", "string"));
		query.setSlop(1);
		topDocs = searcher.search(query, 1000);
		logger.info("{}", topDocs.totalHits);
	}

	@Test
	public void testFuzzyQuery() throws IOException {
		Query query = new FuzzyQuery(new Term("content", "Memcach"), 0, 2);
		topDocs = searcher.search(query, 1000);
		logger.info("{}", topDocs.totalHits);
	}

	@Test
	public void testRegexpQuery() throws IOException {
		Query query = new RegexpQuery(new Term("content", "package*"));
		topDocs = searcher.search(query, 1000);
		logger.info("{}", topDocs.totalHits);
	}

	@Test
	public void testWildcardQuery() throws IOException {
		Query query = new WildcardQuery(new Term("name", "L*Test.java"));
		topDocs = searcher.search(query, 1000);
		logger.info("{}", topDocs.totalHits);
	}

	@Test
	public void testMatchAllDocsQuery() throws IOException {
		Query query = new MatchAllDocsQuery();
		topDocs = searcher.search(query, 1000);
		logger.info("{}", topDocs.totalHits);
	}

	@Test
	public void testSpanTermQuery() throws IOException {
		SpanQuery spanTermQuery = new SpanTermQuery(new Term("content", "package"));
		topDocs = searcher.search(spanTermQuery, 1000);
		logger.info("{}", topDocs.totalHits);

		SpanQuery spanFirstQuery = new SpanFirstQuery(new SpanTermQuery(new Term("content", "test")), 30);
		topDocs = searcher.search(spanFirstQuery, 1000);
		logger.info("{}", topDocs.totalHits);

		SpanQuery spanNearQuery = new SpanNearQuery(new SpanQuery[] { new SpanTermQuery(new Term("content", "static")),
				new SpanTermQuery(new Term("content", "string")) }, 2, true);
		topDocs = searcher.search(spanNearQuery, 1000);
		logger.info("{}", topDocs.totalHits);

		Query spanOrQuery = new SpanOrQuery(spanFirstQuery, spanNearQuery);
		topDocs = searcher.search(spanOrQuery, 1000);
		logger.info("{}", topDocs.totalHits);

		Query spanNotQuery = new SpanNotQuery(spanTermQuery, new SpanTermQuery(new Term("content", "Spring")));
		topDocs = searcher.search(spanNotQuery, 1000);
		logger.info("{}", topDocs.totalHits);
	}

}
