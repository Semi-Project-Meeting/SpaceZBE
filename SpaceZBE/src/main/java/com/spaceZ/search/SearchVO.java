package com.spaceZ.search;

public class SearchVO {

	private String searchWord;
	private String searchTime;
	public SearchVO() {
	}
	public SearchVO(String searchWord, String searchTime) {
		this.searchWord = searchWord;
		this.searchTime = searchTime;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getSearchTime() {
		return searchTime;
	}
	public void setSearchTime(String searchTime) {
		this.searchTime = searchTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((searchTime == null) ? 0 : searchTime.hashCode());
		result = prime * result + ((searchWord == null) ? 0 : searchWord.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchVO other = (SearchVO) obj;
		if (searchTime == null) {
			if (other.searchTime != null)
				return false;
		} else if (!searchTime.equals(other.searchTime))
			return false;
		if (searchWord == null) {
			if (other.searchWord != null)
				return false;
		} else if (!searchWord.equals(other.searchWord))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SearchVO [searchWord=" + searchWord + ", searchTime=" + searchTime + "]";
	}
	
	
}
