package com.orainteractive.simplechat.helper;

import java.util.HashMap;

import org.springframework.data.domain.Page;

import com.orainteractive.simplechat.po.CustomResponse;
import com.orainteractive.simplechat.po.Pagination;

public class ResponseHelper {
	/**	 Covert result from page to CustomRespon for httpResponse
	 * @param list collection of corresponding data such as users chats messages
	 * @param pageNumber requested page
	 * @param size number of elements per page
	 * @return
	 */
	public static <T> CustomResponse<T> convertFromPage(Page<T> list, int pageNumber, int size) {
		CustomResponse<T> result = new CustomResponse<T>(list.getContent());
		HashMap<String, Pagination> meta = new HashMap<>();
		Pagination pagination = new Pagination(pageNumber, size, list.getTotalPages(), list.getTotalElements());
		meta.put("pagination", pagination);
		result.setMeta(meta);
		return result;
	}
}
