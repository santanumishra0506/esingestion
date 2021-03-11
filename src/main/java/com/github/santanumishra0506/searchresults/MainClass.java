package com.github.santanumishra0506.searchresults;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.search.SearchHit;

public class MainClass {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		ObjectMapper objectMapper = new ObjectMapper();
		
		List<Product> products = new ArrayList<Product>();
		
		Map<String, Object> response = null;
		
		SearchHit[] searchHit = null;
		
		QueryData query = new QueryData();
		
		searchHit = query.freeTextSearch();
		
		for(SearchHit hit: searchHit)
		{
			response = hit.getSourceAsMap();
			products.add(objectMapper.convertValue(response, Product.class));
			//System.out.println("map:" + Arrays.toString(response.entrySet().toArray()));		
		}
		
		for(Product temp:products)
		{
			System.out.println(temp);
		}
		
	}

}
