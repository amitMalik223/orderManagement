package com.orders.management.commonutils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Rest Service Template which make the response with all other attributes with
 * the data
 *
 * @author Amit Malik
 *
 */
@Component
public final class RestServiceTemplateUtils {

	/**
	 * @author Amit Malik
	 */
	private RestServiceTemplateUtils() {
		// private constructor to prevent instantiation
	}

	/**
	 * record created successfully response
	 *
	 * @author Amit Malik
	 * @param object   object because of future use
	 * @param response response
	 * @return response format map
	 */
	public static Map<String, Object> createdSuccessResponse(final Object object, final HttpServletResponse response) {
		response.setStatus(HttpStatus.CREATED.value());
		return response(HttpStatus.CREATED.value(), true, "Record successfully created", object);
	}

	/**
	 * response which is to be created
	 *
	 * @author Amit Malik
	 * @param status    Status of the response
	 * @param isSuccess Success response
	 * @param message   response message
	 * @param object    Object which response contains
	 * @return map which contains object in response
	 */
	private static Map<String, Object> response(final int status, final boolean isSuccess, final String message,
			final Object object) {
		final Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("status", status);
		responseMap.put("isSuccess", isSuccess);
		responseMap.put("message", message);
		if (isSuccess) {
			responseMap.put("data", object);
		} else {
			responseMap.put("error", object);
		}
		return responseMap;
	}

	/**
	 * record successfully fetched in response
	 *
	 * @author Amit Malik
	 * @param object   object
	 * @param response response
	 * @return response format map
	 */
	public static Map<String, Object> getRecordSuccessResponse(final Object object,
			final HttpServletResponse response) {
		response.setStatus(HttpStatus.OK.value());
		return response(HttpStatus.OK.value(), true, "Record successfully fetched", object);
	}
}
