package com.framework.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 문자열 관련 유틸
 *
 */
public class StrUtil
{
	/**
	 * SQL 의 nvl 과 동일
	 * @param s
	 * @param c
	 * @param length
	 * @return
	 */
	public static String nvl(Object s, String d) {
		return (s == null) ? d : (String)s;
	}

	/**
	 * 널 검사 메서드 stVal이 null인 경우 "" 반환
	 *
	 * @param stVal null 검사 대상
	 * @return null인 경우 ""
	 */
	public static String checkNull(String stVal)
	{
		return checkNull(stVal, "");
	}

	/**
	 * 널 검사 메서드 str이 null인 경우 def 반환
	 *
	 * @param str null 검사 대상
	 * @param def null 대체 값
	 * @return str이 null인 경우 def
	 */
	public static String checkNull(String str, String def)
	{
		if (str == null || "null".equals(str))
		{
			str = def;
		}
		else
		{
			str = str.trim();
		}

		return str;
	}

	/**
	 * 널, 공백 검사 메서드 str이 null인 경우 def 반환
	 *
	 * @param str null 검사 대상
	 * @param def null 대체 값
	 * @return str이 null인 경우 def
	 */
	public static String checkNullEmpty(String str, String def)
	{
		if (str == null || "null".equals(str) || (str.equals("")))
		{
			str = def;
		}
		else
		{
			str = str.trim();
		}

		return str;
	}

	/**
	 * 널 검사 메서드 str이 null인 경우 def 반환
	 *
	 * @param str null 검사 대상
	 * @param def null 대체 값
	 * @return str이 null인 경우 def
	 */
	public static int checkNull(String str, int def)
	{
		if ((str == null) || (str.equals("null")) || (str.equals("")))
		{
			return def;
		}

		return Integer.parseInt(str);
	}

	/**
	 * 빈문자열인지 체크한 후 모자라는 문자수만큼 왼쪽에서부터 지정된 캐릭터로 채운다.
	 * @param str 검사 대상 문자열
	 * @param c 채워넣을 문자
	 * @param length 맞출 길이
	 * @return 가공된 문자열(null, 공백 인 경우 null 반환)
	 */
	public static String lpad(String str, char c, int length)
	{
		StringBuffer out = new StringBuffer(length);

		if (str != null
		|| !("".equals(str)))
		{
			int count = length - str.length();
			if (count <= 0)
			{
				return str;
			}

			while (--count >= 0)
			{
				out.append(c);
				if (count == 0)
				{
					out.append(str);
				}
			}
			return out.toString();
		}
		return null;
	}

	/**
	 * 문자열의 마지막 콤마를 제거하는 메서드<br>
	 * 문자열 끝의 공백도 같이 제거될 수 있음
	 * @param target 제거 대상 문자열
	 * @return 제거된 문자열
	 */
	public static String removeLastComma(String target)
	{
		String result = "";

		if(target == null || "".equals(target))
		{
			return "";
		}

		result = target.trim();
		if(",".equals(result.substring(result.length() - 1)))
		{
			result = result.substring(0, result.length() - 1).trim();
		}

		return result;
	}

	/**
	 * 문자열 배열을 문자열로 변환시키는 메서드
	 * @param array 문자열 배열
	 * @return 하나의 문자열
	 */
	public static String aTos(String[] array)
	{
		String result = "";

		if(array.length <= 0)
		{
			return result;
		}

		for(String val : array)
		{
			result += val + ", ";
		}
		result = removeLastComma(result);

		return result;
	}

	/**
	 * Map 객체를 문자열로 변환시키는 메서드
	 * @param map
	 * @return
	 */
	public static String mapTos(Map<String, Object> map)
	{
		String result = "";

		if(map.size() <= 0)
		{
			return result;
		}

		for(String key : map.keySet())
		{
			result += key + ": " + map.get(key) + ", ";
		}
		result = removeLastComma(result);

		return result;
	}

	/**
	 * Map 객체를 json 형식의 문자열로 변환시키는 메서드
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String mapToJsonStr(Map<String, Object> map)
	{
		String result = "";

		if(map.size() <= 0)
		{
			return "{}";
		}

		result += "{";
		for(String key : map.keySet())
		{
			result += key + " : ";

			Object value = map.get(key);
			if(value instanceof Map)
			{
				result += mapToJsonStr((Map<String, Object>)map.get(key));
			}
			else if(value instanceof List)
			{
				result += listToJsonStr((List<Object>)map.get(key));
			}
			else if(value instanceof Object[])
			{
				result += arrToJsonStr((Object[])map.get(key));
			}
			else
			{
				result += "\"" + map.get(key) + "\"";
			}

			result += ", ";
		}
		result = removeLastComma(result) + "}";

		return result;
	}

	/**
	 * List 객체를 문자열로 변환시키는 메서드
	 * @param map
	 * @return
	 */
	public static String listTos(List<Object> list)
	{
		String result = "";

		if(list.size() <= 0)
		{
			return result;
		}

		for(Object val : list)
		{
			result += val.toString() + ", ";
		}
		result = removeLastComma(result);

		return result;
	}

	/**
	 * List 객체를 json 형식의 문자열로 변환시키는 메서드
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String listToJsonStr(List<Object> list)
	{
		String result = "";

		if(list.size() <= 0)
		{
			return "[]";
		}

		result += "[";
		for(Object value : list)
		{
			if(value instanceof Map)
			{
				result += mapToJsonStr((Map<String, Object>)value);
			}
			else if(value instanceof List)
			{
				result += listToJsonStr((List<Object>)value);
			}
			else
			{
				result += "\"" + value.toString() + "\"";
			}

			result += ", ";
		}
		result = removeLastComma(result) + "]";

		return result;
	}

	/**
	 * 배열 객체를 json 형식의 문자열로 변환시키는 메서드
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String arrToJsonStr(Object[] list)
	{
		String result = "";

		if(list.length <= 0)
		{
			return "[]";
		}

		result += "[";
		for(Object value : list)
		{
			if(value instanceof Map)
			{
				result += mapToJsonStr((Map<String, Object>)value);
			}
			else if(value instanceof List)
			{
				result += listToJsonStr((List<Object>)value);
			}
			else
			{
				result += "\"" + value.toString() + "\"";
			}

			result += ", ";
		}
		result = removeLastComma(result) + "]";

		return result;
	}

	/**
	 * JSONObject 를 Map 로 변환하는 메서드<br>
	 * 내부 JSONObject, JSONArray 재귀 변환
	 * @param json 변환 대상 JSONObject
	 * @return 변환된 Map
	 */
	public static Map<String, Object> jsonObjToMap(JSONObject json)
	{
		Map<String, Object> result = new HashMap<String, Object>();

		@SuppressWarnings("unchecked")
		Iterator<String> keys = json.keys();
		while(keys.hasNext())
		{
			String key = keys.next();
			Object value = json.get(key);

			if(value instanceof JSONObject)
			{
				result.put(key, jsonObjToMap((JSONObject)value));
			}
			else if(value instanceof JSONArray)
			{
				result.put(key, jsonArrToList((JSONArray)value));
			}
			else	// 위의 두 경우가 아닌 경우 일반 타입으로 간주
			{
				result.put(key, value);
			}
			//System.out.println(key + ": " + value.getClass().getName());
		}

		return result;
	}

	/**
	 * JSONArray 를 List 로 변환하는 메서드<br>
	 * 내부 JSONObject, JSONArray 재귀 변환
	 * @param json 변환 대상 JSONArray
	 * @return 변환된 List
	 */
	public static List<Object> jsonArrToList(JSONArray json)
	{
		List<Object> result = new ArrayList<Object>();

		@SuppressWarnings("unchecked")
		Iterator<Object> iter = json.iterator();
		while(iter.hasNext())
		{
			Object value = iter.next();

			if(value instanceof JSONObject)
			{
				result.add(jsonObjToMap((JSONObject)value));
			}
			else if(value instanceof JSONArray)
			{
				result.add(jsonArrToList((JSONArray)value));
			}
			else	// 위의 두 경우가 아닌 경우 일반 타입으로 간주
			{
				result.add(value);
			}
		}

		return result;
	}

	/**
	 * 문자열 중에서 특정 문자열을 포함하는 단어를 검색하는 메서드
	 * @param src 단어를 검색할 문자열
	 * @param target 검색할 단어의 일부
	 * @param slicer 해당 단어를 구분할 문자열<br>
	 * 정규식 사용([,\\s])
	 * @return 구분된 단어들이 포함된 리스트
	 */
	public static List<String> findWord(String src, String target, String slicer)
	{
		List<String> result = new ArrayList<String>();

		Pattern pTarget = Pattern.compile(target);
		Pattern pSlicer = Pattern.compile(slicer);

		Matcher mTarget = pTarget.matcher(src);
		while(mTarget.find())
		{
			Integer targetIdx = mTarget.start();

			Matcher mSlicer = pSlicer.matcher(src);
			Integer fromIdx = 0, toIdx = 0;
			while(mSlicer.find())
			{
				if(mSlicer.start() <= targetIdx)
				{
					fromIdx = mSlicer.start() + 1;
				}
				else
				{
					toIdx = mSlicer.start();
					break;
				}
			}

			if(toIdx < fromIdx)
			{
				toIdx = src.length();
			}
			String word = src.substring(fromIdx, toIdx);
			result.add(word);
		}

		return result;
	}

	public static String getDecodedErrStack(Throwable e){

		ByteArrayOutputStream resultLog = new ByteArrayOutputStream();
		e.printStackTrace(new PrintStream(resultLog));

		return resultLog.toString();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map mergeMap(Map from, Map to)
	{
		if(from == null)
			return to;

		if(to == null)
		{
			to = new HashMap();
		}

		Set s = from.keySet();

		Iterator it = s.iterator();

		while(it.hasNext()){

			Object key = it.next();

			to.put(key, from.get(key));
		}

		return to;
	}

	public static String rpad(String s, char c, int length)
	{
		//int count = length - s.length();

		if(s == null)
			s = "";

		if(s.length() > length){
			return s.substring(0, length);
		}

		int count = length - s.getBytes().length;

		while(--count >= 0) s = s + c;

		return s;
	}

	public static boolean isNullEmpty(String val)
	{
		return (val == null || val.trim().length() == 0);
	}

	/**
	 * 현재 일자를 구분자 없는 문자열로 반환하는 메서드
	 * @param yearOffset 년도 오프셋
	 * @param monOffset 월 오프셋
	 * @param dayOffset 일 오프셋
	 * @return YYYYMMDD
	 */
	public static String getNowDate(Integer yearOffset, Integer monOffset, Integer dayOffset)
	{
		String result = "";

		Calendar now = Calendar.getInstance();

		now.add(Calendar.YEAR, yearOffset);
		now.add(Calendar.MONTH, monOffset);
		now.add(Calendar.DAY_OF_MONTH, dayOffset);

		Integer year = now.get(Calendar.YEAR);
		Integer mon = now.get(Calendar.MONTH) + 1;
		Integer date = now.get(Calendar.DAY_OF_MONTH);

		result  = year.toString()
				+ StrUtil.lpad(mon.toString(), '0', 2)
				+ StrUtil.lpad(date.toString(), '0', 2);

		return result;
	}

	/**
	 * 입력받은 문자열에 해당하는 Calendar 객체 반환 메서드
	 * @param dt 변환할 일시 문자열(YYYYMM ~ YYYYMMDDHH24MiSS)<br>
	 * 중간에 숫자가 아닌 것이 있을 경우 제외하고 계산함
	 * @return 입력받은 일시에 해당하는 calendar 객체
	 */
	public static Calendar strTocal(String dt)
	{
		Calendar result = Calendar.getInstance();

		if(dt == null || "".equals(dt))
		{
			return result;
		}

		dt = dt.replaceAll("\\D", "");

		if(dt.length() >= 6)
		{
			Integer year = Integer.parseInt(dt.substring(0, 4));
			Integer mon  = Integer.parseInt(dt.substring(4, 6)) - 1;
			result.set(Calendar.YEAR, year);
			result.set(year, mon, 1);
		}
		if(dt.length() >= 8)
		{
			Integer year = Integer.parseInt(dt.substring(0, 4));
			Integer mon  = Integer.parseInt(dt.substring(4, 6)) - 1;
			Integer date = Integer.parseInt(dt.substring(6, 8));
			result.set(year, mon, date);
		}
		if(dt.length() >= 10)
		{
			Integer hour = Integer.parseInt(dt.substring(8, 10));
			result.set(Calendar.HOUR_OF_DAY, hour);
		}
		if(dt.length() >= 12)
		{
			Integer min = Integer.parseInt(dt.substring(10, 12));
			result.set(Calendar.MINUTE, min);
		}
		if(dt.length() >= 14)
		{
			Integer sec = Integer.parseInt(dt.substring(12, 14));
			result.set(Calendar.SECOND, sec);
		}

		return result;
	}

	/**
	 * 입력받은 Calendar에 해당하는 문자열 반환 메서드
	 * @param dt 변환할 Calendar 객체
	 * @param type 문자열 포맷(0: YYYYMM, 1: YYYYMMDD, 2: YYYYMMDD HH24MISS)
	 * @return 입력받은 calendar 객체에 해당하는 일시 문자열
	 */
	public static String calTostr(Calendar dt, Integer type)
	{
		String result = "";

		switch(type)
		{
			case 0:
				result =
				Integer.toString(dt.get(Calendar.YEAR)) +
				Integer.toString(dt.get(Calendar.MONTH) + 1)
				;
				break;
			case 1:
				result =
				Integer.toString(dt.get(Calendar.YEAR)) +
				Integer.toString(dt.get(Calendar.MONTH) + 1) +
				Integer.toString(dt.get(Calendar.DAY_OF_MONTH))
				;
				break;
			case 2:
				result =
				Integer.toString(dt.get(Calendar.YEAR)) +
				Integer.toString(dt.get(Calendar.MONTH) + 1) +
				Integer.toString(dt.get(Calendar.DAY_OF_MONTH)) +
				Integer.toString(dt.get(Calendar.HOUR)) +
				Integer.toString(dt.get(Calendar.MINUTE)) +
				Integer.toString(dt.get(Calendar.SECOND))
				;
				break;
			default:
				break;
		}

		return result;
	}

	/**
	 * 날짜 formatter
	 * @param value
	 * @return
	 */
	public static String fmtDate(String value)
	{
		String result = "";

		result = checkNull(value, "");
		result = result.replaceAll("\\D", "");
		switch(result.length())
		{
			case 4:
				result = result.replaceAll("(\\d{4})", "$1");
				break;
			case 6:
				result = result.replaceAll("(\\d{4})(\\d{2})", "$1-$2");
				break;
			case 8:
				result = result.replaceAll("(\\d{4})(\\d{2})(\\d{2})", "$1-$2-$3");
				break;
			case 10:
				result = result.replaceAll("(\\d{4})(\\d{2})(\\d{2})(\\d{2})", "$1-$2-$3 $4");
				break;
			case 12:
				result = result.replaceAll("(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})", "$1-$2-$3 $4:$5");
				break;
			case 14:
				result = result.replaceAll("(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})", "$1-$2-$3 $4:$5:$6");
				break;
			default:
				break;
		}

		return result;
	}

	/**
	 * 사용여부 formatter
	 * @param value
	 * @return
	 */
	public static String fmtUseyn(String value)
	{
		String result = "";

		result = checkNull(value, "");

		return result;
	}

	/**
	 * 주민등록번호 formatter
	 * @param value
	 * @return
	 */
	public static String fmtRrno(String value)
	{
		String result = "";

		result = checkNull(value, "");
		result = result.replaceAll("\\D", "");
		result = result.replaceAll("(\\d{6})(\\d{7})", "$1-$2");

		return result;
	}

	/**
	 * 쉼표 숫자 formatter
	 * @param value
	 * @return
	 */
	public static String fmtMoney(String value)
	{
		String result = "";

		if("".equals(checkNull(value)))
		{
			return result;
		}

		// 소수점 위치부터 거꾸로 진행
		result = value;
		int ptIdx = result.indexOf(".") - 1;
		if(ptIdx < 0)
		{
			ptIdx = result.length() - 1;
		}
		int nCnt = 0;
		for(;ptIdx>=0; ptIdx--)
		{
			nCnt++;
			if(nCnt == 3 && ptIdx != 0)	// 숫자가 3자리 모이면 쉼표 출력
			{
				String pre = result.substring(0, ptIdx);
				String post = result.substring(ptIdx);
				result = pre + "," + post;
				nCnt = 0;
			}
		}
		return result;
	}

	public static String reportResultKey(HttpServletRequest request, String crfFileNm, Map<String, Object> inData, JSONObject result)
	{
//		String propertyPath = request.getSession().getServletContext().getRealPath("/")
//			                + "WEB-INF"
//							+ File.separator
//							+ "clipreport4"
//							+ File.separator
//							+ "clipreport4.properties";
//
//		String fileURL = "http://"
//							+ request.getServerName()
//							+ ":"
//							+ request.getServerPort()
//							+ request.getContextPath()
//							+ "/report";
//
//		OOFDocument oofDoc = OOFDocument.newOOF();
//
//		for(Map.Entry<String, Object> lpMap : inData.entrySet()) {
//			oofDoc.addField(lpMap.getKey(), lpMap.getValue().toString());
//		}
//
//		OOFFile oofFile = oofDoc.addFile("crf.root", fileURL + crfFileNm);
//		OOFConnectionMemo oofConnMemo = oofFile.addConnectionMemo("*", result.toString());
//		oofConnMemo.addContentParamJSON("*", "utf-8", "{%dataset.json.root%}");

		String resultKey = null; //ReportUtil.createReport(request, oofDoc, "false", "false", "localhost", propertyPath);
		return resultKey;
	}
}