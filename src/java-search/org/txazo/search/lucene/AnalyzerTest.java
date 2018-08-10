package org.txazo.search.lucene;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.pattern.PatternTokenizer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.txazo.test.base.BaseTest;

/**
 * Analyzer - 分词器
 * 
 * <pre>
 * 1) 分词步骤: 关键词切分 - 去除停用词 - 转化小写
 * 2) 中文分词: 单字分词, 二分法分词, 词典分词, 语义分词
 * 3) IKAnalyzer: classpath:IKAnalyzer.cfg.xml, classpath:stopword.dic
 * </pre>
 * 
 * @author txazo
 * 
 */
public class AnalyzerTest extends BaseTest {

	@Test
	public void testAnalyzer() throws IOException {
		Version version = Version.LUCENE_4_9;

		AnalyzerUtil.participle("Hello Beijing, 中华人民共和国", new SimpleAnalyzer(version));
		AnalyzerUtil.participle("Hello Beijing, 中华人民共和国", new StandardAnalyzer(version));
		AnalyzerUtil.participle("Hello Beijing, 中华人民共和国", new CJKAnalyzer(version));
		AnalyzerUtil.participle("Hello Beijing, 中华人民共和国", new SmartChineseAnalyzer(version));
		AnalyzerUtil.participle("Hello Beijing, 中华人民共和国", new WhitespaceAnalyzer(version));
		// AnalyzerUtil.participle("Hello Beijing, 中华人民共和国", new IKAnalyzer());
	}

	@Test
	public void testPatternAnalyzer() throws IOException {
		/** 单字切分 */
		AnalyzerUtil.participle("苹果#水果#香蕉", new PatternAnalyzer(""));
		/** 空格切分 */
		AnalyzerUtil.participle("苹果 水果 香蕉", new PatternAnalyzer(" "));
		/** 指定分隔符切分 */
		AnalyzerUtil.participle("苹果#水果#香蕉", new PatternAnalyzer("#"));
	}

	public static class AnalyzerUtil {

		public static void participle(String text, Analyzer analyzer) throws IOException {
			StringBuilder builder = new StringBuilder();
			TokenStream stream = analyzer.tokenStream("field", new StringReader(text));
			CharTermAttribute attribute = stream.addAttribute(CharTermAttribute.class);
			try {
				stream.reset();
				while (stream.incrementToken()) {
					builder.append(attribute.toString()).append("/");
				}
				stream.end();
			} finally {
				stream.close();
			}
			logger.info(builder.substring(0, builder.length() - 1));
		}

	}

	public class PatternAnalyzer extends Analyzer {

		private String regex;

		private PatternAnalyzer(String regex) {
			this.regex = regex;
		}

		@Override
		protected TokenStreamComponents createComponents(String fieldName, Reader reader) {
			return new TokenStreamComponents(new PatternTokenizer(reader, Pattern.compile(regex), -1));
		}

	}

}
