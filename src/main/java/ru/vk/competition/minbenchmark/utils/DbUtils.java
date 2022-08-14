package ru.vk.competition.minbenchmark.utils;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class DbUtils {

    public String substituteParams(@NonNull String query, Map<String, String> params) {
        String resultQuery = query;
        for (Map.Entry<String, String> param : params.entrySet()) {
            String searchParam = ":" + param.getKey();
            String replaceString = param.getValue();
            resultQuery = resultQuery.replaceAll(searchParam, replaceString);
        }
        return resultQuery;
    }
}
