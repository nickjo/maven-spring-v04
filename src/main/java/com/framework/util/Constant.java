package com.framework.util;

/**
 * 시스템 전반에 사용되는 상수 형태의 상태값
 * @author dgbds
 *
 */
public class Constant
{
	/*
	 * ===========================================
	 * 공통 상수
	 * ===========================================
	 */
	/**
	 * 데이터 입력시 Key
	 */
	public static final String IN_DATA = "IN_DATA";
	/**
	 * 데이터 입력시 Key(JSON 파싱으로 MAP 전환 되어야 되는 경우 사용)
	 */
	public static final String IN_DATA_JSON = "IN_DATA_JSON";
	/**
	 * 데이터 반환시 Key
	 */
	public static final String OUT_DATA = "OUT_DATA";
	/**
	 * 데이터 개수 반환시 Key
	 */
	public static final String OUT_DATA_CNT = "OUT_DATA_CNT";
	/**
	 * 로직 처리 결과 Key
	 */
	public static final String RESULT = "RESULT";
	/**
	 * 로직 처리 결과 성공
	 */
	public static final String RESULT_SUCCESS = "RESULT_SUCCESS";
	/**
	 * 로직 처리 결과 실패
	 */
	public static final String RESULT_FAILURE = "RESULT_FAILURE";
	/**
	 * 로직 처리 결과 메시지 Key
	 */
	public static final String RESULT_MSG = "RESULT_MSG";
	/**
	 * 에러 메시지 Key
	 */
	public static final String RESULT_ERRNM = "RESULT_ERRNM";
	/**
	 * CRUD 모드 Key
	 */
	public static final String CRUD = "mode";
	/**
	 * CRUD C Key
	 */
	public static final String CRUD_C = "c";
	/**
	 * CRUD R Key
	 */
	public static final String CRUD_R = "r";
	/**
	 * CRUD U Key
	 */
	public static final String CRUD_U = "u";
	/**
	 * CRUD D Key
	 */
	public static final String CRUD_D = "d";
	/**
	 * Report Key
	 */
	public static final String REPORT_KEY = "REPORT_KEY";

	/*
	 * ===========================================
	 * 엑셀 관련 상수
	 * ===========================================
	 */
	public static final String EXCEL_FILENM = "EXCEL_FILENM";
	public static final String EXCEL_IN_DATA = "EXCEL_IN_DATA";
	public static final String EXCEL_IN_COLUMN = "EXCEL_IN_COLUMN";
	public static final String EXCEL_IN_MARKER = "EXCEL_IN_MARKER";

	/*
	 * ===========================================
	 * json AXGrid 조회 출력
	 * ===========================================
	 */
	/**
	 * AxisJ용 출력: 처리결과
	 */
	public static final String AX_RESULT = "result";
	/**
	 * AxisJ용 출력: 처리성공
	 */
	public static final String AX_RESULT_OK = "ok";
	/**
	 * AxisJ용 출력: 처리실패
	 */
	public static final String AX_RESULT_FAIL = "fail";
	/**
	 * AxisJ용 출력: 처리메시지
	 */
	public static final String AX_MSG = "msg";
	/**
	 * AxisJ용 입출력: 페이지
	 */
	public static final String AX_PAGE = "page";
	/**
	 * AxisJ용 입출력: 페이지 번호
	 */
	public static final String AX_PAGENO = "pageNo";
	/**
	 * AxisJ용 출력: 페이지 개수
	 */
	public static final String AX_PAGECOUNT = "pageCount";
	/**
	 * AxisJ용 입력: 페이지당 행 개수
	 */
	public static final String AX_PAGESIZE = "pageSize";
	/**
	 * AxisJ용 출력: 조회된 행 개수
	 */
	public static final String AX_LISTCOUNT = "listCount";
	/**
	 * AxisJ용 출력: 반환 데이터 리스트
	 */
	public static final String AX_LIST = "list";

	/*
	 * ===========================================
	 * 프로젝트 귀속 상수
	 * ===========================================
	 */
	/**
	 * 세션에 설정된 유저 정보 Key
	 */
	public static final String USER_INFO = "USER_INFO";
	/**
	 * 세션에 설정된 유저 정보중 ip정보 Key
	 */
	public static final String IP_ADDR = "IP_ADDR";
	/**
	 * 암호화 대상 컬럼명 배열
	 */
	public static final String[] ENC_COL_NM = {"REG_USER", "REG_USER2"};
	/**
	 * 개인정보 컬럼명 배열
	 */
	public static final String[] PRV_COL_NM = {"DEC_RRNO"};
}
