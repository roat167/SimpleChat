package com.orainteractive.simplechat.po;

//"current_page": 1,
//"per_page": 50,
//"page_count": 3,
//"total_count": 150
public class Pagination {
	private int currentPage;
	private int perPage;
	private int pageCount;
	private long totalCount;

	public Pagination(int currentPage, int perPage, int pagecount, long totalCount) {
		this.currentPage = currentPage;
		this.perPage = perPage;
		this.pageCount = pagecount;
		this.totalCount = totalCount;
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPerPage() {
		return this.perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	public int getPageCount() {
		return this.pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public long getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
}
