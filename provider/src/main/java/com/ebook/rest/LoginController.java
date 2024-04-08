package com.ebook.rest;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONUtil;
import com.ebook.util.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @BelongsProject: ebook
 * @BelongsPackage: com.ebook.rest
 * @Author: LuQing
 * @CreateTime: 2023-11-29  16:55
 * @Description: 描述信息
 * @Version: 1.0
 */
@RestController
@RequestMapping("test")
public class LoginController {

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public R hot() {
        try {
            return R.ok("登录成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("后台操作异常，请联系管理员，异常信息：" + e.getMessage());
        }
    }


    public static void main(String[] args) {
        String json = "{\n" +
                "    \"a\":\"1\",\n" +
                "    \"b\":\"2\",\n" +
                "    \"c\":{\n" +
                "        \"c1\":\"31\",\n" +
                "        \"c2\":{\n" +
                "\t\t\"c21\":\"21\",\n" +
                "\t\t\"c22\":\"22\"\n" +
                "\t\t},\n" +
                "\t\"c3\": [\n" +
                "        {\n" +
                "            \"c31\":\"31\",\n" +
                "            \"c32\":\"32\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"c31\":\"43\",\n" +
                "            \"c32\":\"44\"\n" +
                "        }\n" +
                "    ]\n" +
                "    },\n" +
                "    \"d\":[\n" +
                "        {\n" +
                "            \"d1\":\"41\",\n" +
                "            \"d2\":{\n" +
                "\t\t\"d21\":\"221\",\n" +
                "\t\t\"d22\":\"222\"\n" +
                "\t\t},\n" +
                "\"d3\":[\n" +
                "\t{\n" +
                "\t\"d31\":\"31\",\n" +
                "\t\"d32\":\"32\",\n" +
                "\t\"d33\":\"33\"\n" +
                "},\n" +
                "\t{\n" +
                "\t\"d31\":\"311\",\n" +
                "\t\"d32\":\"322\",\n" +
                "\t\"d33\":\"333\"\n" +
                "}\n" +
                "]\n" +
                "        },\n" +
                "        {\n" +
                "            \"d1\":\"43\",\n" +
                "            \"d2\":\"44\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        Map map = JSONUtil.toBean(json, Map.class);
        Map map1 = formatMapData(map);
        System.out.println(JSONUtil.toJsonStr(map1));
    }

    public static Map<String, Object> formatMapData(Map<String, Object> map) {
        Map<String, Object> data = new HashMap<>();
        for (String key : map.keySet()) {
            Object value = map.get(key);
            if (value instanceof Map) {
                Map<String, Object> valueMap = Convert.toMap(String.class, Object.class, value);
                Map<String, Object> map1 = formatMapData(valueMap);
                for (String valueKey : map1.keySet()) {
                    Object objValue = map1.get(valueKey);
                    data.put(key + "." + valueKey, objValue);
                }
            } else if (value instanceof List) {
                List<Map> mapList = Convert.toList(Map.class, value);
                for (Map map1 : mapList) {
                    Map<String, Object> map2 = Convert.toMap(String.class, Object.class, map1);
                    for (String keys : map2.keySet()) {
                        String listKey = key + "." + keys;
                        Object o = map2.get(keys);
                        if (o instanceof String) {
                            List<String> lists = Convert.toList(String.class, data.get(listKey));
                            if (CollUtil.isEmpty(lists)) {
                                List<String> valueList = new ArrayList<>();
                                valueList.add(Convert.toStr(o));
                                data.put(listKey, valueList);
                            } else {
                                lists.add(Convert.toStr(o));
                                lists = lists.stream().distinct().collect(Collectors.toList());
                                data.put(listKey, lists);
                            }
                        } else if (o instanceof Map) {
                            Map<String, Object> map3 = formatMapData(Convert.toMap(String.class, Object.class, o));
                            for (String valueKey : map3.keySet()) {
                                Object objValue = map3.get(valueKey);
                                data.put(key + "." + valueKey, Convert.toStr(objValue));
                            }
                        } else if (o instanceof List) {
                            Map<String, Object> map3 = formatMapData(map2);
                            for (String s : map3.keySet()) {
                                data.put(key + "." + s, Convert.toList(map3.get(s)));
                            }
                        }
                    }
                }
            } else if (value instanceof String) {
                data.put(key, Convert.toStr(value));
            }
        }
        return data;
    }
}
