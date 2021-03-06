package com.company.SlidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
* 438. 找到字符串中所有字母异位词
给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。

字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。

说明：

字母异位词指字母相同，但排列不同的字符串。
不考虑答案输出的顺序。
示例 1:

输入:
s: "cbaebabacd" p: "abc"

输出:
[0, 6]

解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 示例 2:

输入:
s: "abab" p: "ab"

输出:
[0, 1, 2]

解释:
起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。*/
public class N438 {
    public static void main(String[] args) {
        System.out.println(new N438().findAnagrams("baa", "aa"));
    }
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if(s == null || s.length() == 0 || p == null || p.length() == 0 || p.length() > s.length()){
            return result;
        }
        int size = p.length();
        HashMap<Character,Integer> ref = new HashMap<>();
        HashMap<Character,Integer> window = new HashMap<>();
        for(char tem : p.toCharArray()){
            ref.put(tem,ref.getOrDefault(tem,0) + 1);
        }
        for(char tem : ref.keySet()){
            ref.put(tem,ref.getOrDefault(tem,0) + 1);
        }
        int left = 0,right = 0,valid = 0;
        while(right < s.length()){
            char c = s.charAt(right);
            if(ref.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+ 1);
                if(window.get(c).equals( ref.get(c))){
                    valid++;
                }
            }
            while (valid == ref.size()){
                char d = s.charAt(left);
                if(right - left + 1 == size){
                    result.add(left);
                }
                left++;
                if(ref.containsKey(d)){
                    if(ref.get(d).equals( window.get(d))){
                        valid--;
                    }
                    window.put(d,window.get(d) - 1);
                }
            }
            right++;
        }
        return result;
    }
}
