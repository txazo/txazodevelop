package org.txazo.search.lucene.base;

import java.io.IOException;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.sandbox.queries.DuplicateFilter;
import org.apache.lucene.search.FieldCacheTermsFilter;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.NumericRangeFilter;
import org.apache.lucene.search.PrefixFilter;
import org.apache.lucene.search.QueryWrapperFilter;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeFilter;
import org.apache.lucene.util.BytesRef;
import org.junit.Test;

/**
 * Filter查询过滤
 * 
 * @author txazo
 * 
 */
public class FilterTest extends LuceneBaseTest {

	@Test
	public void testAddDocument() throws Exception {
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

	@Test
	public void testNumericRangeFilter() throws IOException {
		Filter filter = NumericRangeFilter.newFloatRange("price", 60.00f, 70.00f, true, true);
		search(new MatchAllDocsQuery(), filter, 1000);
	}

	@Test
	public void testPrefixFilter() throws IOException {
		Filter filter = new PrefixFilter(new Term("product", "水"));
		search(new MatchAllDocsQuery(), filter, 1000);
	}

	@Test
	public void testTermRangeFilter() throws IOException {
		Filter filter = new TermRangeFilter("quantity", new BytesRef("20"), new BytesRef("60"), true, true);
		search(new MatchAllDocsQuery(), filter, 1000);
	}

	@Test
	public void testFieldCacheTermsFilter() throws IOException {
		Filter filter = new FieldCacheTermsFilter("quantity", "33", "15");
		search(new MatchAllDocsQuery(), filter, 1000);
	}

	@Test
	public void testQueryWrapperFilter() throws IOException {
		Filter filter = new QueryWrapperFilter(new TermQuery(new Term("product", "水")));
		search(new MatchAllDocsQuery(), filter, 1000);
	}

	@Test
	public void testDuplicateFilter() throws IOException {
		IndexWriter writer = getIndexWriter(true);
		String[] names = new String[] { "id", "name", "type" };
		writer.addDocument(newDocument(names, new String[] { "1", "香蕉", "水果" }));
		writer.addDocument(newDocument(names, new String[] { "2", "苹果", "水果" }));
		writer.addDocument(newDocument(names, new String[] { "3", "草莓", "水果" }));
		writer.addDocument(newDocument(names, new String[] { "4", "雪碧", "饮料" }));
		writer.addDocument(newDocument(names, new String[] { "5", "脉动", "饮料" }));
		writer.commit();

		Filter filter = new DuplicateFilter("type");
		search(new MatchAllDocsQuery(), filter, 1000);
	}

}
