package org.txazo.search.lucene.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.AtomicReaderContext;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.search.Collector;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Scorer;
import org.junit.Test;

/**
 * Collector
 * 
 * @author txazo
 * 
 */
public class CollectorTest extends LuceneBaseTest {

	@Test
	public void testCollector() throws IOException {
		IndexWriter writer = getIndexWriter(true);
		String[] names = new String[] { "id", "name", "type" };
		writer.addDocument(newDocument(names, new String[] { "1", "香蕉", "水果" }));
		writer.addDocument(newDocument(names, new String[] { "2", "苹果", "水果" }));
		writer.addDocument(newDocument(names, new String[] { "3", "草莓", "水果" }));
		writer.addDocument(newDocument(names, new String[] { "4", "雪碧", "饮料" }));
		writer.addDocument(newDocument(names, new String[] { "5", "脉动", "饮料" }));
		writer.commit();

		CustomCollector collector = new CustomCollector();
		IndexSearcher searcher = getIndexSearcher();
		searcher.search(new MatchAllDocsQuery(), collector);
		List<ScoreDoc> scoreDocs = collector.docs();
		Document hitDoc = null;
		IndexableField field = null;
		Map<String, String> jsonMap = null;
		Iterator<IndexableField> fields = null;
		for (ScoreDoc scoreDoc : scoreDocs) {
			hitDoc = searcher.doc(scoreDoc.doc);
			jsonMap = new HashMap<String, String>();
			for (fields = hitDoc.iterator(); fields.hasNext();) {
				field = fields.next();
				jsonMap.put(field.name(), field.stringValue());
			}
			logger.info("doc {} {} : {}", scoreDoc.doc, scoreDoc.score, JSONArray.fromObject(jsonMap));
		}

		setShow(false);
	}

	/**
	 * 自定义Collector
	 */
	public class CustomCollector extends Collector {

		private int docBase;
		private Scorer scorer;
		private List<ScoreDoc> docs = new ArrayList<>();

		@Override
		public boolean acceptsDocsOutOfOrder() {
			return false;
		}

		@Override
		public void collect(int doc) throws IOException {
			docs.add(new ScoreDoc(docBase + doc, scorer.score()));
		}

		@Override
		public void setNextReader(AtomicReaderContext context) throws IOException {
			this.docBase = context.docBase;
		}

		@Override
		public void setScorer(Scorer scorer) throws IOException {
			this.scorer = scorer;
		}

		public List<ScoreDoc> docs() {
			return docs;
		}

	}

}
