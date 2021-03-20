package com.github.santanumishra0506.autosuggest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.SuggestionBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;


import com.github.santanumishra0506.autosuggest.Connection;

public class RetrieveSuggestion {
	
	public void autoSuggest() throws IOException
	{
		
		List<String> suggestions = new ArrayList<String>();
		
		Connection con = new Connection();
		RestHighLevelClient client = con.createClient();
		
		SearchRequest searchRequest = new SearchRequest("productsuggestion");
		
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		
		SuggestionBuilder productSuggestionBuilder = SuggestBuilders.completionSuggestion("suggest").prefix("dry");
		
		SuggestBuilder suggestBuilder = new SuggestBuilder();
		
		suggestBuilder.addSuggestion("product-suggest", productSuggestionBuilder);
		searchSourceBuilder.suggest(suggestBuilder);
		
		searchRequest.source(searchSourceBuilder);
		
		SearchResponse searchResponse = null;
		
		searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		
		Suggest suggest = searchResponse.getSuggest();
		
		CompletionSuggestion entries = suggest.getSuggestion("product-suggest");
		
		for(CompletionSuggestion.Entry entry:entries)
		{
			for(CompletionSuggestion.Entry.Option option:entry.getOptions())
			{
				suggestions.add(option.getText().string());
			}
		}
		
		for(String temp: suggestions)
		{
			System.out.println(temp);
		}
		
	}
	
	
	

}
