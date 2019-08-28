package com.example.demo.util.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

public class JsonFileUtils {
    public static JSONObject readJSONObject(String path) {
        return read(path, JSONObject.class);
    }

    public static JSONArray readJSONArray(String path) {
        return read(path, JSONArray.class);
    }

    public static <T> T read(String path, Class<T> type) {
        try {
            // 判断文件是否存在
            File file = ResourceUtils.getFile(path);
            if (!file.exists()) {
                System.out.println(String.format("%s not exists", path));
                return null;
            }

            // 读取文件内容
            String content = FileUtils.readFileToString(file, "UTF-8");

            if (JSONObject.class.equals(type)) {        // JSONObject
                JSONObject bodyJSON = JSONObject.parseObject(content);
                return (T) bodyJSON;
            } else if (JSONArray.class.equals(type)) {  // JSONArray
                JSONArray bodyJSONArray = JSONArray.parseArray(content);
                return (T) bodyJSONArray;
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return null;
    }
}
