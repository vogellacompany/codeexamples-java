package de.vogella.web.crawler.checklists;

import java.util.ArrayList;
import java.util.List;

import de.vogella.web.crawler.model.Webpage;

public class CheckList {
	List<Webpage> whiteList = new ArrayList<Webpage>();
	List<Webpage> blackList= new ArrayList<Webpage>();


	public void addBlackList(Webpage page){
		blackList.add(page);
	}
	
	public void addWhiteList(Webpage page){
		if (!blackList.contains(page)){
			whiteList.add(page);
		}
	}
	
	/*
	 * Only check whitelisted sites if a site is whitelisted
	 */
	public boolean check(Webpage page) {
		
		for (Webpage webpage: blackList){
			if (page.getUrl().contains(webpage.getUrl())){
				return false; 
			}
		}
		if (whiteList.isEmpty()){
			return true; 
		}
		for (Webpage webpage: whiteList){
			if (page.getUrl().contains(webpage.getUrl())){
				return true; 
			}
		}
		return false;
	}
	
}
