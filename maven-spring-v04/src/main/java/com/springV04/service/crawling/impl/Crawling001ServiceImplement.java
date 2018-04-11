package com.springV04.service.crawling.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.springV04.service.crawling.Crawling001Service;

public class Crawling001ServiceImplement implements Crawling001Service{
	private Logger log = Logger.getLogger(this.getClass()); 

	@Override
	public String connMolit() {
		
		String siteUrl = "http://rt.molit.go.kr/";
		StringBuilder sb = new StringBuilder();
		String line = "";
		
		try {
			HttpPost http = new HttpPost(siteUrl);
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpResponse response = httpClient.execute(http); 
			HttpEntity entity = response.getEntity();
			
			ContentType contentType = ContentType.getOrDefault(entity);
			Charset charset = contentType.getCharset();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), charset));
			
			while((line=br.readLine()) != null) {
				sb.append(line + "\n");
			}
			
			log.debug("##sb:" + sb.toString());
			
			Document doc = Jsoup.parse(sb.toString());
			
			
			log.debug("###doc: " + doc.data());
			
//			Document doc = Jsoup.parse(sb.toString());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sb.toString();
	}

}
