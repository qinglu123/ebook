package com.ebook.test;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @BelongsProject: ebook
 * @BelongsPackage: com.ebook.test
 * @Author: LuQing
 * @CreateTime: 2024-03-28  17:49
 * @Description: 描述信息
 * @Version: 1.0
 */
public class MapText {

    public static void main(String[] args) {
        String str = "{"+
                "    \"研究背景与目标\": {\n" +
                "      \"1.1 战斗机发展现状\": {},\n" +
                "      \"1.2 先进战斗机技术需求\": {}\n" +
                "    },\n" +
                "    \"研究方法与技术\": {\n" +
                "      \"2.1 数据收集与处理\": {},\n" +
                "      \"2.2 战斗机性能评估指标\": {},\n" +
                "      \"2.3 战斗机先进技术分析\": {}\n" +
                "    },\n" +
                "    \"先进战斗机技术应用\": {\n" +
                "      \"3.1 第五代战斗机技术\": {},\n" +
                "      \"3.2 第六代战斗机技术\": {},\n" +
                "      \"3.3 第七代战斗机技术\": {}\n" +
                "    },\n" +
                "    \"先进战斗机发展趋势\": {\n" +
                "      \"4.1 国际先进战斗机发展动态\": {},\n" +
                "      \"4.2 我国先进战斗机发展现状\": {},\n" +
                "      \"4.3 先进战斗机未来发展趋势\": {}\n" +
                "    },\n" +
                "    \"结论与建议\": {\n" +
                "      \"5.1 研究结论\": {},\n" +
                "      \"5.2 研究建议\": {}\n" +
                "    }\n" +
                "  \n" +
                "}";
        LinkedHashMap<String, Object> reportLists = JSONObject.parseObject(str,LinkedHashMap.class);
        saveReportLists(reportLists, 0, 1);
    }

    public static void saveReportLists(Map<String, Object> reportLists, Integer parentId, Integer id) {
        Iterator<Map.Entry<String, Object>> iterator = reportLists.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            String listName = next.getKey();
            System.out.println("id-"+ id + "    parentId-" + (parentId) + "-------" + listName);
            Object value = next.getValue();
            if (value instanceof Map) {
                reportLists = Convert.toMap(String.class, Object.class, value);
                saveReportLists(reportLists, id, ++id);
            }
            id++;
        }
    }
}
