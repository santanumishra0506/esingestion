package com.github.santanumishra0506.esingestion;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.search.SearchHit;

import com.github.santanumishra0506.searchresults.QueryData;


public class MainClass {

	public static void main(String[] args) throws IOException {
		
		IngestData ingestion = new IngestData();
//		String response = ingestion.dataIngestion();
		BulkResponse response = ingestion.bulkIngestion();
		
		for(int i=0;i<response.getItems().length;i++)
		{
			System.out.println(response.getItems()[i].getId());
		}
		
	}
	
}


