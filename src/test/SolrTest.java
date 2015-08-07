package test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

public class SolrTest {

	private final static String baseURL="http://172.16.20.123:8080/solr"; 
	public  static void createIndex(){
		try {
			HttpSolrServer server=new HttpSolrServer(baseURL);
			SolrInputDocument doc=new SolrInputDocument();
			doc.addField("id","1");
			doc.addField("name", "中国");
			doc.addField("content", "现在好冷啊a");
			server.add(doc);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void searcher(){
		
		try {
			HttpSolrServer server=new HttpSolrServer(baseURL);
			SolrQuery query=new SolrQuery("罚款");
			query.setParam("df", "CFBZ");
			query.setParam("rows", "20");
			QueryResponse resp=server.query(query);
			SolrDocumentList list=resp.getResults();
			for (SolrDocument doc : list) {
				System.out.println(doc.get("id"));
				System.out.println(doc.get("CFBZ"));
				System.out.println(doc.get("STRNAME"));
				System.out.println(doc.get("YJTL")+"\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static void main(String[] args) {
		
		//createIndex();
		searcher();
		
	}
	
}
