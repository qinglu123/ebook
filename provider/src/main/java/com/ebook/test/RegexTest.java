package com.ebook.test;

import cn.hutool.core.util.StrUtil;

import java.util.Scanner;

/**
 * @BelongsProject: ebook
 * @BelongsPackage: com.ebook.test
 * @Author: LuQing
 * @CreateTime: 2024-03-20  15:34
 * @Description: 正则表达式校验
 * @Version: 1.0
 */
public class RegexTest {
    public static void main(String[] args) {
        Boolean tag = true;
        Scanner scanner = new Scanner(System.in);
        while (tag) {
            System.out.print("请输入需要校验的数据：");
            String text = scanner.nextLine();
            System.out.print("请输入正则表达式：");
            String regex = scanner.nextLine();
            if (text.matches(regex)) {
                System.out.println("格式正确");
            } else {
                System.out.println("格式错误");
            }
            System.out.print("是否继续校验(y/n)：");
            if (StrUtil.equals("y",scanner.nextLine())) tag = true;
            else tag = false;
        }
        scanner.close();
    }
}
