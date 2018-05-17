package com.perkins.SpringBootSecondKill.util;

public class PagingUtil {

	private int totalCount = 0;

	private int pageSize = 16;

	private int pageCount = 0;

	private int currentPage = 1;

	private int offset;

	private String sort = "ASC";

	public int getTotalCount() {

		return totalCount;

	}

	public void setTotalCount(int totalCount) {

		this.totalCount = totalCount;

	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {

		this.pageSize = pageSize;

	}

	public int getPageCount() {

		if (totalCount < 1) {

			pageCount = 0;

			return pageCount;

		}

		pageCount = (totalCount - 1) / getPageSize() + 1;

		return pageCount;

	}

	public void setPageCount(int pageCount) {

		this.pageCount = pageCount;

	}

	public int getCurrentPage() {

		if (currentPage < 1) {

			currentPage = 1;

		}

		return currentPage;

	}

	public void setCurrentPage(int currentPage) {

		this.currentPage = currentPage;

	}

	public int getOffset() {

		offset = (getCurrentPage() - 1) * getPageSize();

		return offset;

	}

	public String getSort() {

		return sort;

	}

	public void setSort(String sort) {

		this.sort = sort;

	}
}
