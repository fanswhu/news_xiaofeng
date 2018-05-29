package com.jayce.xiaofeng_news.utils;

/**
 * Created by fwx576768 on 2018/5/29.
 */

import android.graphics.Color;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 */

public class StringUtils {
        /**
         * @param targetStr 要处理的字符串
         * @description 切割字符串，将文本和img标签碎片化，如"ab<img>cd"转换为"ab"、"<img>"、"cd"
         */
        public static List<String> cutStringByImgTag(String targetStr) {
            List<String> splitTextList = new ArrayList<String>();
            Pattern pattern = Pattern.compile("<img.*?src=\\\"(.*?)\\\".*?>");
            Matcher matcher = pattern.matcher(targetStr);
            int lastIndex = 0;
            while (matcher.find()) {
                if (matcher.start() > lastIndex) {
                    splitTextList.add(targetStr.substring(lastIndex, matcher.start()));
                }
                splitTextList.add(targetStr.substring(matcher.start(), matcher.end()));
                lastIndex = matcher.end();
            }
            if (lastIndex != targetStr.length()) {
                splitTextList.add(targetStr.substring(lastIndex, targetStr.length()));
            }
            return splitTextList;
        }

        /**
         * 获取img标签中的src值
         *
         * @param content
         * @return
         */
        public static ArrayList<String> getImgSrc(String content) {
            ArrayList<String> srcList = new ArrayList<>();
            String str_src = null;
            //目前img标签标示有3种表达式
            //<img alt="" src="1.jpg"/>   <img alt="" src="1.jpg"></img>     <img alt="" src="1.jpg">
            //开始匹配content中的<img />标签
            Pattern p_img = Pattern.compile("<(img|IMG)(.*?)(/>|></img>|>)");
            Matcher m_img = p_img.matcher(content);
            boolean result_img = m_img.find();
            if (result_img) {
                while (result_img) {
                    //获取到匹配的<img />标签中的内容
                    String str_img = m_img.group(2);

                    //开始匹配<img />标签中的src
                    Pattern p_src = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");
                    Matcher m_src = p_src.matcher(str_img);
                    if (m_src.find()) {
                        str_src = m_src.group(3);
                    }
                    //结束匹配<img />标签中的src

                    //匹配content中是否存在下一个<img />标签，有则继续以上步骤匹配<img />标签中的src
                    result_img = m_img.find();
                    srcList.add(str_src);
                }
            }
            return srcList;
        }

        /**
         * 关键字高亮显示
         *
         * @param target 需要高亮的关键字
         * @param text   需要显示的文字
         * @return spannable 处理完后的结果，记得不要toString()，否则没有效果
         * SpannableStringBuilder textString = TextUtilTools.highlight(item.getItemName(), KnowledgeActivity.searchKey);
         * vHolder.tv_itemName_search.setText(textString);
         */
        public static SpannableStringBuilder highlight(String text, String target) {
            SpannableStringBuilder spannable = new SpannableStringBuilder(text);
            CharacterStyle span = null;

            Pattern p = Pattern.compile(target);
            Matcher m = p.matcher(text);
            while (m.find()) {
                span = new ForegroundColorSpan(Color.parseColor("#068ED6"));// 需要重复！
                spannable.setSpan(span, m.start(), m.end(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            return spannable;
        }

        public static String getSpan(String val) {
            String start = null;
            String end = null;
            //"去<span class="web_mark">日本</span>留学的你后悔了吗？"
            if (val.contains("span")) {
                start = "<span class=\"web_mark\">";
                end = "</span>";
            } else if (val.contains("em")) {
                start = "<em>";
                end = "</em>";
            }
            int s = val.indexOf(start) + start.length();
            int e = val.indexOf(end);
            //防止找不到对应标签造成角标越界
            if (e > s)
                return val.substring(s, e);
            else
                return "";
        }

        /**
         * 去除html标签
         *
         * @param htmlStr
         * @return
         */
        public static String delHTMLTag(String htmlStr) {
//        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
//        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
//        String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式
//
//        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
//        Matcher m_script = p_script.matcher(htmlStr);
//        htmlStr = m_script.replaceAll(""); //过滤script标签
//
//        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
//        Matcher m_style = p_style.matcher(htmlStr);
//        htmlStr = m_style.replaceAll(""); //过滤style标签
//
//        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
//        Matcher m_html = p_html.matcher(htmlStr);
//        htmlStr = m_html.replaceAll(""); //过滤html标签
//
//        return htmlStr.trim(); //返回文本字符串
            if (!TextUtils.isEmpty(htmlStr))
                return Html.fromHtml(htmlStr).toString().trim();
            else
                return "";
        }

        /**
         * 是否是网络路径
         *
         * @param str
         * @return
         */
        public static boolean isNetPath(String str) {
            String regExp = "^(http://)";
            Pattern p = Pattern.compile(regExp);
            Matcher m = p.matcher(str);
            return m.matches();
        }

        /**
         * 匹配是否是英文字母和数字
         *
         * @param charaString
         * @return
         */
        public static boolean isEnglishOrNum(String charaString) {
            return charaString.matches("[a-zA-Z0-9]");
        }

        /**
         * 匹配是否是汉字
         *
         * @param str
         * @return
         */
        public static boolean isChinese(String str) {
            String regEx = "[\\u4e00-\\u9fa5]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            if (m.find())
                return true;
            else
                return false;
        }

        /**
         * @Description:把数组转换为一个用逗号分隔的字符串 ，以便于用in+String 查询
         */
        public static String converToString(String[] ig) {
            String str = "";
            if (ig != null && ig.length > 0) {
                for (int i = 0; i < ig.length; i++) {
                    str += ig[i] + ",";
                }
            }
            str = str.substring(0, str.length() - 1);
            return str;
        }

        /**
         * @Description:把list转换为一个用逗号分隔的字符串
         */
        public static String listToString(List list) {
            StringBuilder sb = new StringBuilder();
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    if (i < list.size() - 1) {
                        sb.append(list.get(i) + ",");
                    } else {
                        sb.append(list.get(i));
                    }
                }
            }
            return sb.toString();
        }

        /**
         * 是否是表情 注意事项：将中文标点符号也过滤了
         *
         * @param codePoint
         * @return
         */
        public static boolean isEmojiCharacter(char codePoint) {
            return !((codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD) || ((codePoint >= 0x20) && codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
        }

        /**
         * 是否包含表情符号
         *
         * @param str
         * @return
         */
        public static boolean isContainEmoji(String str) {
            for (int i = 0; i < str.length(); i++) {
                if (isEmojiCharacter(str.charAt(i))) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 是否包含表情符号
         *
         * @param str
         * @return
         */
        public static boolean isContainEmoji(Editable str) {
            for (int i = 0; i < str.length(); i++) {
                if (isEmojiCharacter(str.charAt(i))) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 是否包含表情符号
         *
         * @param source
         * @return
         */
        public static boolean containsEmoji(String source) {
            int len = source.length();
            boolean isEmoji = false;
            for (int i = 0; i < len; i++) {
                char hs = source.charAt(i);
                if (0xd800 <= hs && hs <= 0xdbff) {
                    if (source.length() > 1) {
                        char ls = source.charAt(i + 1);
                        int uc = ((hs - 0xd800) * 0x400) + (ls - 0xdc00) + 0x10000;
                        if (0x1d000 <= uc && uc <= 0x1f77f) {
                            return true;
                        }
                    }
                } else {
                    // non surrogate
                    if (0x2100 <= hs && hs <= 0x27ff && hs != 0x263b) {
                        return true;
                    } else if (0x2B05 <= hs && hs <= 0x2b07) {
                        return true;
                    } else if (0x2934 <= hs && hs <= 0x2935) {
                        return true;
                    } else if (0x3297 <= hs && hs <= 0x3299) {
                        return true;
                    } else if (hs == 0xa9 || hs == 0xae || hs == 0x303d || hs == 0x3030 || hs == 0x2b55 || hs == 0x2b1c || hs == 0x2b1b || hs == 0x2b50 || hs == 0x231a) {
                        return true;
                    }
                    if (!isEmoji && source.length() > 1 && i < source.length() - 1) {
                        char ls = source.charAt(i + 1);
                        if (ls == 0x20e3) {
                            return true;
                        }
                    }
                }
            }
            return isEmoji;
        }

        /**
         * 判断html转换成字符串是否为空
         * @param html
         * @return
         */
        public static boolean htmlIsEmpty(String html){
            String temp=html.replaceAll(" ","").replaceAll("<br>","");
            return TextUtils.isEmpty(temp.trim());
        }

        /**
         * 过滤所有以"<"开头以">"结尾的标签
         *
         * @param html
         * @return
         */
        public static String filterTagGetContent(String html) {
            //替换掉<img></img>和<img />的形式的字符串为空字符串
            //String destStr = html.replaceAll("<(img|IMG)(.*?)(/>|></img>|>)", "");
            //只保留文字内容
            String destStr = html.replaceAll("<([^>]*)>","");
            return destStr;
        }

}
