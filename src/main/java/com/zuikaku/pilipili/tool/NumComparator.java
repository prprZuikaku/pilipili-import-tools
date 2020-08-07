package com.zuikaku.pilipili.tool;

import com.zuikaku.pilipili.pojo.SortFileDO;

import java.util.Comparator;

/**
 * 数字文件比较器
 * @program: pilipili-web-ImportTool->NumComparetor
 * @description: ${description}
 * @author: ty
 * @create: 2020-08-07 11:28
 **/
public class NumComparator implements Comparator<SortFileDO> {
    public int compare(SortFileDO o1, SortFileDO o2) {
        return o1.getNumberName()-o2.getNumberName();
    }
}
