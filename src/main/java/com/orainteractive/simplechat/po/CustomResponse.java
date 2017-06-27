package com.orainteractive.simplechat.po;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomResponse<T> {
	private HashMap<String, ? extends Object> meta = new HashMap<>();
	private List<T> data = new ArrayList<>();

	public CustomResponse() {
	}

	public CustomResponse(List<T> data) {
		this.data = data;
	}

	public HashMap<String, ? extends Object> getMeta() {
		return meta;
	}

	public void setMeta(HashMap<String, ? extends Object> meta) {
		this.meta = meta;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
