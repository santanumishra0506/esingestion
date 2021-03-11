package com.github.santanumishra0506.esingestion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

public class IngestData {

//	public String dataIngestion() throws IOException
//	{
//		Connection con = new Connection();
//		RestHighLevelClient client = con.createClient();
//		
//		
//		IndexRequest request = new IndexRequest("posts"); 
//		request.id("102"); 
//		
//		String jsonString = "{" +
//		        "\"name\":\"DishWasher\"," +
//		        "\"price\":\"100\"," +
//		        "\"in_stock\":\"4\"" +
//		        "}";
//		
//		request.source(jsonString, XContentType.JSON);
//		
//		IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
//		
//		client.close();
//		return indexResponse.getId();
//	}
	
	public BulkResponse bulkIngestion() throws IOException
	{
		Connection con = new Connection();
		RestHighLevelClient client = con.createClient();		
		BulkRequest request = new BulkRequest();
		
		
		ObjectMapper objectMapper = new ObjectMapper(); 
		List<Product> products = new ArrayList<Product>();
		Product product = new Product();
		
		File jsonFile = new File("/Users/sangeetamohapatra/Desktop/Santanu/workspace-spring-tool-suite-4-4.8.1.RELEASE/esingestion/products.json");
		
		FileReader fr = new FileReader(jsonFile);
		
		BufferedReader br = new BufferedReader(fr);
		
		String line ;
		
		while(( line= br.readLine())!=null)
		{
		    product = objectMapper.readValue(line, Product.class);
			products.add(product);
		}
		
		for (Product individualroduct: products)
		{
			final byte[] bytes = objectMapper.writeValueAsBytes(individualroduct);
			IndexRequest indexRequest = new IndexRequest("posts");
			indexRequest.id(individualroduct.getId());
			indexRequest.source(bytes,XContentType.JSON);
			request.add(indexRequest);
			
		}
		BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
				
		client.close();
		return response;
	}
}
