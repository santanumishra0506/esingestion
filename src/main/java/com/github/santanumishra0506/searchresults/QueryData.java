package com.github.santanumishra0506.searchresults;

import java.io.IOException;
import java.util.Map;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import com.github.santanumishra0506.esingestion.Connection;

public class QueryData {
	
	public SearchHit[] freeTextSearch() throws IOException {
		
		Connection con = new Connection();
		RestHighLevelClient client = con.createClient();
		
		
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices("posts");
		
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		//searchSourceBuilder.query(QueryBuilders.matchAllQuery());
		
		
		searchSourceBuilder.query(QueryBuilders.multiMatchQuery("4", "name","in_stock"));
		searchRequest.source(searchSourceBuilder);
		
		
		SearchResponse searchResponse = null;
		searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		
		SearchHit[] searchHit = null;
		
		if(searchResponse.getHits().getTotalHits().value>0)
		{
			searchHit = searchResponse.getHits().getHits();
			
		}
		client.close();
		return searchHit;
		
	}

}
