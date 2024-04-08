package com.ebook.rest;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: ebook
 * @BelongsPackage: com.ebook.rest
 * @Author: LuQing
 * @CreateTime: 2024-03-13  09:56
 * @Description: 描述信息
 * @Version: 1.0
 */
@RestController
@RequestMapping("xml")
public class XMLTest {

//    @RequestMapping(value = "format", method = RequestMethod.GET)
//    public List<Map<String, Object>> xmlFormat(@RequestParam(name = "result") String result, @RequestParam(name = "dataParam") String dataParam) {
//        try {
//            return getXmlResultData(result, dataParam);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }



    public static void main(String[] args) {
        String result = "<docs>" +
                "<doc>" +
                "<freeterm>International Space Station;Commercial Resupply Services-CRS;Orbital ATK</freeterm>" +
                "<createdate>2019-05-20</createdate>" +
                "<libyear>2017</libyear>" +
                "<language>英文</language>" +
                "<documenttype>图片</documenttype>" +
                "<dateissued>2017-02-01</dateissued>" +
                "<recordno>6B8D8E8B5C7061CF9ED0F66DC456F6FB</recordno>" +
                "<secretlevel>00</secretlevel>" +
                "<titleproper>OA-7 Service Module Arrival</titleproper>" +
                "</doc>" +
                "<doc>" +
                "<freeterm>GSDO;vehicle support posts;mobile launcher;exploration;SLS;EM-1;Journey to Mars</freeterm>" +
                "<createdate>2019-05-20</createdate>" +
                "<libyear>2017</libyear>" +
                "<language>英文</language>" +
                "<documenttype>图片</documenttype>" +
                "<dateissued>2017-05-25</dateissued>" +
                "<recordno>01A9C6FA865B0CAF753E81D0B85D0257</recordno>" +
                "<secretlevel>00</secretlevel>" +
                "<titleproper>Vehicle Support Posts Installation onto Mobile Launcher</titleproper>" +
                "</doc>" +
                "</docs>";
        String dataParam = "$.docs";
        List<Map<String, Object>> xmlResultData = getXmlResultData(result, dataParam);
        System.out.println(JSONUtil.toJsonStr(xmlResultData));


    }

    private static List<Map<String, Object>> getXmlResultData(String result, String dataParam) {
        List<Map<String, Object>> datas = new ArrayList<>();
        //将XML格式的字符串转为Document对象
        Document document = XmlUtil.readXML(IoUtil.toStream(result, "UTF-8"));
        //获取根节点
        Element root = document.getDocumentElement();
        String tagName = root.getTagName();
        if (!(ObjectUtil.isEmpty(dataParam) || StrUtil.equalsIgnoreCase("$", dataParam))) {
            List<String> split = StrUtil.split(dataParam, ".");
            if (split.size() > 1 && StrUtil.equalsIgnoreCase("$", split.get(0)) && StrUtil.equalsIgnoreCase(tagName, split.get(1))) {
                if (ObjectUtil.equals(2, split.size())) {
                    datas.add(generateXmlDataMap(root));
                } else {
                    split.remove("$");
                    split.remove(tagName);
                    generateData(root.getElementsByTagName(split.remove(0)), split, datas, 0);
                }
            }
        }
        return datas;
    }


    public static List<Map<String, Object>> generateData(NodeList nodeList, List<String> split, List<Map<String, Object>> datas, int index) {
        if (CollUtil.isNotEmpty(split) && index < split.size()) {
            for (int j = 0; j < nodeList.getLength(); j++) {
                String tag = split.get(index);
                Element personElement = (Element) nodeList.item(j);
                NodeList elementsByTagName = personElement.getElementsByTagName(tag);
                index++;
                generateData(elementsByTagName, split, datas, index);
                index = 0;
            }
        } else {
            for (int x = 0; x < nodeList.getLength(); x++) {
                Element personElement = (Element) nodeList.item(x);
                datas.add(generateXmlDataMap(personElement));
            }
        }
        return datas;
    }

    public static Map<String, Object> generateXmlDataMap(Element element) {
        // 获取子标签
        NodeList children = element.getChildNodes();
        Map<String, Object> xmlDataMap = new HashMap<>();
        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                // 处理元素节点
                Element childElement = (Element) node;
                String tagName = childElement.getTagName();
                String tagData = childElement.getTextContent();
                // 将标签名称与数据放入Map
                xmlDataMap.put(tagName, tagData);
            }
        }
        return xmlDataMap;
    }
}
