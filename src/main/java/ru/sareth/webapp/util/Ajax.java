package ru.sareth.webapp.util;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dzorin on 18.10.2016.
 * OATTeam
 */
public class Ajax {

    private static final Logger LOGGER = LoggerFactory.getLogger(Ajax.class);
    public static Map<String, Object> successResponse(Object object) {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("result", "success");
        response.put("data", object);
        LOGGER.debug(response.toString());
        return response;
    }

    public static Map<String, String> successResponse(String object) {
        Map<String, String> response = new HashMap<String, String>();
        response.put("result", "success");
        response.put("data", object);
        LOGGER.debug(response.toString());
        return response;
    }

    public static Map<String, Object> emptyResponse() {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("result", "success");
        LOGGER.debug(response.toString());
        return response;
    }

    public static Map<String, Object> errorResponse(String errorMessage) {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("result", "error");
        response.put("message", errorMessage);
        LOGGER.debug(response.toString());
        return response;
    }
}
