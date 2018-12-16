package com.jarasy.lv.common.utils;


import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	/**
	 * 过滤特殊字符
	 */
	public static String filter(String value) {
		if (value == null)
			return "";

		StringBuffer result = new StringBuffer();
		for (int i = 0; i < value.length(); i++) {
			char ch = value.charAt(i);

			switch (ch) {
			case '<':
				result.append("&lt;");
				break;
			case '>':
				result.append("&gt;");
				break;
			case '"':
				result.append("&quot;");
				break;
			case '\'':
				result.append("&#39;");
				break;
			case '%':
				result.append("&#37;");
				break;
			case ';':
				result.append("&#59;");
				break;
			case '(':
				result.append("&#40;");
				break;
			case ')':
				result.append("&#41;");
				break;
			case '&':
				result.append("&amp;");
				break;
			case '+':
				result.append("&#43;");
				break;
			default:
				result.append(value.charAt(i));
				break;
			}

		}
		// return result.toString();
		return (result.toString().replaceAll("([;]+|(--)+).*", " "));
	}

	public static String trimToMaxLength(String str, String encoding,
			int maxLength) throws UnsupportedEncodingException {
		String[] s = str.split("");
		int len = 0;
		int i;
		for (i = 1; i < s.length; i++) {
			try {
				int byteLength = s[i].getBytes(encoding).length;
				if (len + byteLength <= maxLength)
					len += byteLength;
				else
					break;
			} catch (UnsupportedEncodingException e) {
			}
		}
		return new String(str.getBytes(encoding), 0, len, encoding);
	}

	/**
	 * 判断字符串的编码
	 * 
	 * @param str
	 * @return
	 */
	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s = encode;
				return s;
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		return "";
	}

	static final private String threeEggs = "000";

	static final private String twoEggs = "00";

	static final private String oneEgg = "0";

	public static String filterSQL(String str) {
		String filterStr = str;
		if (filterStr.indexOf("'") >= 0) {
			filterStr = filterStr.replaceAll("'", "");
		} else if (filterStr.indexOf("\"") >= 0) {
			filterStr = filterStr.replaceAll("\"", "");
		}
		return filterStr;
	}

	public static String generateNumber(String deptNo, String approveDate,
			String sequence) {
		int len = sequence.length();
		String resultNo = deptNo + approveDate;
		switch (len) {
		case 1:
			resultNo += threeEggs + sequence;
			break;
		case 2:
			resultNo += twoEggs + sequence;
			break;
		case 3:
			resultNo += oneEgg + sequence;
			break;
		case 4:
			resultNo += sequence;
			break;
		default:
			resultNo = null;
		}
		return resultNo;
	}

	public static boolean checkNull(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 将字符串str按reg的规则分解
	 * 
	 * @param str
	 * @param reg
	 *            如 '|'与':'等
	 * @return
	 */
	public static ArrayList<String> stringToArrayList(String str, String reg) {
		ArrayList<String> tmp = new ArrayList<String>();
		if (str == null)
			return tmp;

		String[] strArr = StringUtils.split(str, reg);
		if (str.length() > 0) {
			for (int i = 0; i < strArr.length; ++i) {

				tmp.add(strArr[i].toLowerCase());
			}
		}
		return tmp;
	}


	/**
	 * 将字符串str按reg的规则分解
	 *
	 * @param str
	 * @param reg
	 *            如 '|'与':'等
	 * @return
	 */
	public static String[]  stringToArray(String str, String reg) {
		if (str == null){
			return null;
		}
		return StringUtils.split(str, reg);
	}

	/**
	 * 将字符串数组arr组装Sql in
	 * 
	 * @param arr
	 *            {"1","2"}
	 * @return '','1','2'
	 */
	public static String arrToSqlInStr(String[] arr) {
		String sqlStr = "''";
		if (null != arr && arr.length > 0) {
			for (String str : arr) {
				sqlStr += ",'" + str + "'";
			}
		}
		return sqlStr.replace("'',", "");
	}


	/**
	 * 根据输入的时间格式判断是否是日期类型的字符串
	 * 
	 * @param str
	 *            需要判断的字符串
	 * @param format
	 *            判断是否匹配对应的格式
	 * @return 返回匹配结果
	 */
	public static boolean isDate(String str, String format) {
		if (StringUtils.isEmpty(str))
			return false;
		if (StringUtils.isEmpty(format))
			return false;
		try {
			DateFormat df = new SimpleDateFormat(format);
			df.parse(str);
		} catch (Exception ex) {
			return false;
		}

		return true;
	}

	public static String toUnicode(String strText, String code)
			throws UnsupportedEncodingException {
		char c;
		String strRet = "";
		int intAsc;
		String strHex;
		for (int i = 0; i < strText.length(); i++) {
			c = strText.charAt(i);
			intAsc = (int) c;
			if (intAsc > 128) {
				strHex = Integer.toHexString(intAsc);
				strRet = strRet + "&#x" + strHex + ";";
			} else {
				strRet = strRet + c;
			}
		}
		return strRet;
	}


	public static String ChineseToUnicode(String s) {
		StringBuffer bu = new StringBuffer(s);
		String unicode = "";
		for (int i = 0; i < bu.length(); i++) {
			String tmp = Integer.toHexString((int) bu.charAt(i));
			unicode = unicode + "\\u" + tmp;
		}
		return unicode;
	}

	public static String isoToChinese(String str) {
		if (str == null) {
			return null;
		}

		try {
			str = new String(str.getBytes("iso-8859-1"), "gbk");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return str;
	}

	public static boolean isNumeric(String str) {
		// 判空处理
		if (str == null || "".equals(str.trim()))
			return false;

		for (int i = 0; i < str.length(); i++) {
			// System.out.println(str.charAt(i));
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 字符串位数
	 */
	public static final String STR_FORMAT_4 = "0000";
	public static final String STR_FORMAT_5 = "00000";
	public static final String STR_FORMAT_6 = "000000";
	public static final String STR_FORMAT_7 = "0000000";
	public static final String STR_FORMAT_8 = "00000000";

	/**
	 * 数字左边补零
	 * 
	 * @param num
	 * @param strBitSize
	 * @return
	 */
	public static String numToFormatStr(String num, String strBitSize) {
		Integer intHao = Integer.parseInt(num);
		DecimalFormat df = new DecimalFormat(strBitSize);
		return df.format(intHao);
	}

	public static String getString(String str) {
		if (null == str || "null".equals(str)) {
			return "";
		} else {
			return str.trim();
		}

	}

	/**
	 * 取字符串长度（字节数）
	 * 
	 * @param str
	 * @return
	 */
	public static int getBytesLength(String str) {
		if (null == str || "null".equals(str)) {
			return 0;
		} else {
			int len = 0;
			try {
				len = str.trim().getBytes("GBK").length;
			} catch (UnsupportedEncodingException e) {
			}
			return len;
		}
	}


	// 求两个字符串数组的并集，利用set的元素唯一性
	public static String[] union(String[] arr1, String[] arr2) {
		Set<String> set = new HashSet<String>();
		for (String str : arr1) {
			set.add(str);
		}
		for (String str : arr2) {
			set.add(str);
		}
		String[] result = {};
		return set.toArray(result);
	}

	// 求两个数组的交集
	public static String[] intersect(String[] arr1, String[] arr2) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		LinkedList<String> list = new LinkedList<String>();
		for (String str : arr1) {
			if (!map.containsKey(str)) {
				map.put(str, Boolean.FALSE);
			}
		}
		for (String str : arr2) {
			if (map.containsKey(str)) {
				map.put(str, Boolean.TRUE);
			}
		}

		for (Entry<String, Boolean> e : map.entrySet()) {
			if (e.getValue().equals(Boolean.TRUE)) {
				list.add(e.getKey());
			}
		}

		String[] result = {};
		return list.toArray(result);
	}

	// 求两个数组的差集
	public static String[] minusStringArr(String[] arr1, String[] arr2) {
		LinkedList<String> list = new LinkedList<String>();
		LinkedList<String> history = new LinkedList<String>();
		String[] longerArr = arr1;
		String[] shorterArr = arr2;
		// 找出较长的数组来减较短的数组
		if (arr1.length > arr2.length) {
			longerArr = arr2;
			shorterArr = arr1;
		}
		for (String str : longerArr) {
			if (!list.contains(str)) {
				if (null != str && !"".equals(str)) {
					list.add(str);
				}
			}
		}
		for (String str : shorterArr) {
			if (list.contains(str)) {
				history.add(str);
				list.remove(str);
			} else {
				if (!history.contains(str)) {
					if (null != str && !"".equals(str)) {
						list.add(str);
					}
				}
			}
		}
		String[] result = {};
		return list.toArray(result);
	}

	// 判断字符串是否为数字 xjm
	public static boolean ifNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	public static String dellastchar(String str) {
		if (!isStringNull(str)) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

	/**
	 * 半角转全角
	 * 
	 * @param input
	 *            String.
	 * @return 全角字符串.
	 */
	public static String ToSBC(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == ' ') {
				c[i] = '\u3000';
			} else if (c[i] < '\177') {
				c[i] = (char) (c[i] + 65248);

			}
		}
		return new String(c);
	}

	/**
	 * 全角转半角
	 * 
	 * @param input
	 *            String.
	 * @return 半角字符串
	 */
	public static String ToDBC(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);

			}
		}
		String returnString = new String(c);
		return returnString;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param s
	 *            s
	 * @return boolean
	 */
	public static boolean isStringNull(String s) {
		if (s == null || s.length() <= 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isConSpeCharacters(String string) {
		return checkCharacters(string, "(?is)<script[^>]*?>.*?<\\/script>")
				|| checkCharacters(string, "[`~^'|]");
	}

	public static boolean checkCharacters(String string, String charset) {
		Pattern p = Pattern.compile(charset);
		Matcher m = p.matcher(string);
		return m.find();
	}

	/**
	 *
	 *  获取没有分隔符的uuid
	 */
	public static String getUUID(){

		String uuidStr = UUID.randomUUID().toString();
		uuidStr = uuidStr.replace("-","");
		return uuidStr;
	}
}
