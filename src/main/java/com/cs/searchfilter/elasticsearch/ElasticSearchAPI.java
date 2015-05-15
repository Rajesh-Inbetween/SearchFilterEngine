package com.cs.searchfilter.elasticsearch;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.elasticsearch.search.SearchHit;

public class ElasticSearchAPI {

	public static Node node;

	public static Client client;

	public static void main(String srgs[]) {
		String index = "kodcucom";
		String type = "article";
//		String id = "2";

		try {
			startInstance();
			Thread.sleep(5000);
			
			searchDocument(index, type, "content", "test");
			
			/*for (Integer count = 4; count <= 6; count++) {
				Map<String, Object> jsonDocument = getContent();
				putData(index, type, count.toString(), jsonDocument);
			}*/
			
			/*for (Integer count = 1; count <= 6; count++) {
				getData(index, type, count.toString());
			}*/
			
			stopInstance();

		} catch (Exception e) {

			stopInstance();
		}

	}

	public static void startInstance() {
		node = NodeBuilder.nodeBuilder().node();
		client = node.client();
	}

	public static void stopInstance() {
		node.close();
	}
	
	public static Map<String, Object> getContent () {
		Map<String, Object> jsonDocument = new HashMap<String, Object>();
		jsonDocument.put("title", "New Test Data 2");
		jsonDocument.put("content", "New This is a test content 2.");
		jsonDocument.put("Timestamp", System.currentTimeMillis());
		jsonDocument.put("tags", new String[] { "elasticsearch" });
		jsonDocument.put("author", "New Test Author");
		
		return jsonDocument;

	}

	public static void putData(String index, String type, String id,
			Map<String, Object> data) {
		client.prepareIndex(index, type, id).setSource(data).execute()
				.actionGet();
	}

	public static void getData(String index, String type, String id) {
		GetResponse getResponse = client.prepareGet(index, type, id)
				.execute().actionGet();

		Map<String, Object> source = getResponse.getSource();

		System.out.println("------------------------------");
		System.out.println("Index: " + getResponse.getIndex());
		System.out.println("Type: " + getResponse.getType());
		System.out.println("Id: " + getResponse.getId());
		System.out.println("Version: " + getResponse.getVersion());
		System.out.println(source);
		System.out.println("------------------------------");
	}

	public static void searchDocument(String index, String type, String field,
			String value) {
		SearchResponse response = client.prepareSearch(index).setTypes(type)
				.setSearchType(SearchType.QUERY_AND_FETCH)
				.setQuery(QueryBuilders.prefixQuery(field, value))
//				.setPostFilter(FilterBuilders.rangeFilter("Timestamp").from("1431608769844").to("1431608769928"))
				.setExplain(true).execute().actionGet();

		SearchHit[] results = response.getHits().getHits();

		System.out.println("Current results: " + results.length);
		for (SearchHit hit : results) {
			System.out.println("------------------------------");
			Map<String, Object> result = hit.getSource();
			System.out.println(result);
			
		}

	}

}
