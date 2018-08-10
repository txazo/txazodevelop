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
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Assert;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * Lucene
 * 
 * <pre>
 * 1) 全文检索: 建立索引, 搜索索引
 *    索引过程: 分词 - 建立索引 - 存储索引
 *    搜索过程: 分词 - 搜索索引 - 结果排序
 * </pre>
 * 
 * @author txazo
 * 
 */
public class LuceneTest extends BaseTest {

	@Test
	public void testLucene() throws IOException, ParseException {
		Version version = Version.LUCENE_4_9;
		Analyzer analyzer = new StandardAnalyzer(version);
		Directory directory = FSDirectory.open(new File("E:/lucene"));

		IndexWriterConfig config = new IndexWriterConfig(version, analyzer);
		config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

		IndexWriter writer = new IndexWriter(directory, config);
		Document document = new Document();
		document.add(new Field("fieldName", "txazo", TextField.TYPE_STORED));
		writer.deleteAll();
		writer.addDocument(document);
		writer.close();

		DirectoryReader reader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(reader);
		QueryParser parser = new QueryParser(version, "fieldName", analyzer);
		Query query = parser.parse("txazo");
		TopDocs topDocs = searcher.search(query, 1000);
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		Document hitDoc = null;
		for (int i = 0; i < scoreDocs.length; i++) {
			hitDoc = searcher.doc(scoreDocs[i].doc);
			Assert.assertEquals("txazo", hitDoc.get("fieldName"));
		}
		reader.close();

		directory.close();
	}

}
