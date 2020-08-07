package com.zuikaku.pilipili.tool;

import com.zuikaku.pilipili.pojo.SortFileDO;

import java.util.Comparator;

/**
 * 字符串比较器
 * @program: pilipili-web-ImportTool->StrComparator
 * @description: ${description}
 * @author: ty
 * @create: 2020-08-07 11:29
 **/
public class StrComparator implements Comparator<SortFileDO> {
    public int compare(SortFileDO o1, SortFileDO o2) {
        return o1.getNoNumberName().compareTo(o2.getNoNumberName());
    }
}
