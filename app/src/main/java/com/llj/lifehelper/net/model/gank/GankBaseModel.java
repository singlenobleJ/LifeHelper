package com.llj.lifehelper.net.model.gank;

import java.util.List;

/**
 * <pre>
 *    创建者：LLJ
 *    创建时间：2018/5/4 12:49
 *    描述：
 * </pre>
 */
public class GankBaseModel {
	private boolean error;
	private List<GankModel> results;

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public List<GankModel> getResults() {
		return results;
	}

	public void setResults(List<GankModel> results) {
		this.results = results;
	}
}
