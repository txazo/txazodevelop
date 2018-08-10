package org.txazo.search.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * Lucene 评分机制
 * 
 * <pre>
 * </pre>
 * 
 * @author txazo
 * 
 */
public class LuceneScoreTest extends BaseTest {

	@Test
	public void testLuceneScore() throws Exception {
		Version version = Version.LUCENE_4_9;
		Analyzer analyzer = new StandardAnalyzer(version);
		Directory directory = FSDirectory.open(new File("E:/lucene"));

		IndexWriterConfig config = new IndexWriterConfig(version, analyzer);
		config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

		IndexWriter writer = new IndexWriter(directory, config);
		writer.deleteAll();
		addDocument(writer);
		writer.close();

		DirectoryReader reader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(reader);
		QueryParser parser = new QueryParser(version, "content", analyzer);
		Query query = parser.parse("field1^100 field2");
		TopDocs topDocs = searcher.search(query, 1000);
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		for (int i = 0; i < scoreDocs.length; i++) {
			logger.info("{}\t{}", scoreDocs[i].doc, scoreDocs[i].score);
			// logger.info("{}", searcher.explain(query, scoreDocs[i].doc));
		}
		reader.close();

		directory.close();
	}

	private void addDocument(IndexWriter writer) throws IOException {
		Document document = new Document();
		document.add(new Field("content", "field1 field1 field1 field2 field2", TextField.TYPE_STORED));
		writer.addDocument(document);
		document = new Document();
		Field field = new Field("content", "field2 field2 field1 field2 field2 add add add add", TextField.TYPE_STORED);
		field.setBoost(100);
		document.add(field);
		writer.addDocument(document);
	}

}
