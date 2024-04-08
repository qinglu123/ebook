//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ebook.util;

import java.util.HashMap;
import java.util.Map;

public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        this.put((String)"code", 200);
        this.put((String)"msg", "success");
        this.put((String)"ok", true);
    }

    public static R okDeletedToNormal(Object obj) {
        R r = new R();
        r.put((String)"status", 1);
        r.put((String)"code", 201);
        r.put((String)"ok", false);
        r.put((String)"msg", "此条目已删除，是否需要重新维护？");
        r.put("data", obj);
        return r;
    }

    public static R errorGeneration(Object obj) {
        R r = new R();
        r.put((String)"status", 1);
        r.put((String)"code", 201);
        r.put((String)"ok", false);
        r.put((String)"msg", "保存成功，自动生成信息失败！");
        r.put("data", obj);
        return r;
    }

    public static R errorAll(Object obj) {
        R r = new R();
        r.put((String)"status", 2);
        r.put((String)"code", 201);
        r.put((String)"ok", false);
        r.put((String)"msg", "保存失败，自动生成信息失败！");
        r.put("data", obj);
        return r;
    }

    public static R okRepeated() {
        R r = new R();
        r.put((String)"status", 2);
        r.put((String)"code", 201);
        r.put((String)"ok", false);
        r.put((String)"msg", "数据重复，请重新输入");
        return r;
    }

    public static R okSave() {
        R r = new R();
        r.put((String)"msg", "保存成功");
        return r;
    }

    public static R okDelete() {
        R r = new R();
        r.put((String)"msg", "删除成功");
        return r;
    }

    public static R okUpdate() {
        R r = new R();
        r.put((String)"msg", "修改成功");
        return r;
    }

    public static R error(String msg) {
        R r = new R();
        r.put((String)"code", 201);
        r.put((String)"ok", false);
        r.put((String)"msg", msg);
        return r;
    }

    public static R errorBug() {
        R r = new R();
        r.put((String)"code", 201);
        r.put((String)"ok", false);
        r.put((String)"msg", "系统异常，请联系管理员");
        return r;
    }

    public static R ok(String key, Object value, int status) {
        R r = new R();
        r.put((String)"status", status);
        r.put(key, value);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put((String)"msg", msg);
        return r;
    }

    public static R ok(String msg, String key, Object value) {
        R r = new R();
        r.put((String)"msg", msg);
        r.put(key, value);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok(int sum, String key, Object value) {
        R r = new R();
        r.put((String)"sum", sum);
        r.put(key, value);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
