package com.vishop.core.util;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Splitter;
import com.google.common.collect.Collections2;

import java.util.*;

/**
 * 通用工具，弥补guava没有提供的常用工具类
 * 
 * @author LIHAOYU
 * @since 2015.07.01
 * @version 1.0
 * 
 */
public abstract class GuavaUtil {

	public static final char[] HEXBYTES = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * 空数组常量
	 */
	public static final Object[] NULL_ARRAY = new Object[0];

	/**
	 * 空字符串常量
	 */
	public static final String NULL_STR = "";

	/**
	 * 字符串逗号分割器
	 */
	public static final Splitter COMMA_SPLITTER = Splitter.on(',');

	/**
	 * 检查输入的容器对象是不是为 null或者为空
	 * 
	 * @param c
	 *            检查用的容器对象
	 * @return {@code true} 如果==null or isEmpty()
	 */
	public static boolean isNullOrEmpty(Collection<?> c) {
		return c == null ? true : c.isEmpty();
	}

	/**
	 * 检查输入的字符串是不是为 null或者为空字符串
	 * 
	 * <p>
	 * ，这个版本使用了jdk6新增了判断字符串是否为空的函数 {@link String#isEmpty()}。 开发中用哪个都可以。
	 * 
	 * @param string
	 *            检查用的字符串
	 * @return {@code true} 如果==null or isEmpty()
	 */
	public static boolean isNullOrEmpty(String string) {
		return string == null || string.isEmpty();
	}

	/**
	 * 返回 {@code true} 当输入的字符串有内容时
	 * 
	 * <p>
	 * 与 {@link #isNullOrEmpty(String) } 意义相反，并且更进一步要求字符串有实在的内容
	 * 
	 * @param string
	 *            检查用的字符串
	 * @return {@code true} 如果不为空且去掉头尾的空格和TAB后还有内容
	 */
	public static boolean hasContent(String string) {
		return string != null && !string.trim().isEmpty();
	}

	public static Predicate<String> startWith(final String prefix) {
		return new Predicate<String>() {
			public boolean apply(String input) {
				return input.startsWith(prefix);
			}
		};
	}

	public static Predicate<String> endsWith(final String suffix) {
		return new Predicate<String>() {
			public boolean apply(String input) {
				return input.endsWith(suffix);
			}
		};
	}

	/**
	 * 把一个整数或者字符串转为指定的枚举类型
	 * 
	 * @param enumType
	 * @param value
	 * @return 转换后的枚举值
	 */
	public static <E extends Enum<E>> E toEnum(Class<E> enumType, Object value) {
		if (value == null)
			return null;
		if (value instanceof Integer)
			return enumType.getEnumConstants()[(Integer) value];
		else if (value instanceof String)
			return Enum.valueOf(enumType, (String) value);
		throw new IllegalArgumentException(
				"only int or string can transfer to enum");
	}

	/**
	 * 把一个byte数组转为16进制表示
	 * 
	 * @param b
	 * @return
	 */
	public static String byteToHex(byte[] b) {
		int len = b.length;
		char[] s = new char[len * 2];
		for (int i = 0, j = 0; i < len; i++) {
			int c = (b[i]) & 0xff;
			s[j++] = HEXBYTES[c >> 4 & 0xf];
			s[j++] = HEXBYTES[c & 0xf];
		}
		return new String(s);
	}

	/**
	 * 用一个字符来分割字符串，返回一个分割好的字符串数组
	 * 
	 * @param str
	 *            待处理字符串
	 * @param delimiter
	 *            分隔符
	 * @return 分割好的字符串数组
	 */
	public static String[] split(String str, char delimiter) {
		if (isNullOrEmpty(str))
			return new String[0];
		char[] buf = str.toCharArray();
		int count = 1;
		for (char c : buf) {
			if (c == delimiter)
				count++;
		}
		if (count == 1)
			return new String[] { str };
		String[] result = new String[count];
		count = 0;
		int index = 0;
		int point = 0;
		for (char c : buf) {
			if (c == delimiter) {
				if (point == index) {
					result[count++] = NULL_STR;
				} else {
					result[count++] = new String(buf, index, point - index);
				}
				point++;
				index = point;
			} else
				point++;
		}
		if (point > index) {
			result[count] = new String(buf, index, point - index);
		} else
			result[count] = NULL_STR;
		return result;
	}

	/**
	 * 把第一个List中没有但在第二个List中的元素找出来并放到第一个List中
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public static <T> void unionList(final List<T> first, List<T> second) {
		Collection<T> f = Collections2.filter(second,
				Predicates.not(Predicates.in(first)));
		if (!f.isEmpty())
			first.addAll(f);
	}

	/**
	 * 根据函数转换一组列表，替代Guava中的Lists.transform
	 * 
	 * @param fromList
	 * @param function
	 * @return
	 */
	public static <F, T> List<T> transform(List<F> fromList,
			Function<? super F, ? extends T> function) {
		List<T> result = (fromList instanceof RandomAccess) ? new ArrayList<T>(
				fromList.size()) : new LinkedList<T>();
		for (F f : fromList)
			result.add(function.apply(f));
		return result;
	}
}
