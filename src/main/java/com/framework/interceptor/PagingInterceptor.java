package com.framework.interceptor;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;

import com.framework.util.Constant;
import com.framework.util.StrUtil;

// 어노테이션을 이용해 Bean 생성(sql-mapper-config.xml에서 지정한 타입이 이 어노테이션의 Bean을 Autowired로 끌어갈 것으로 예상됨)
@Intercepts({
	/*
	 *  @Signature: 가로챌 타겟(메서드)를 지정하는 어노테이션
	 *  type: 타겟으로 지정할 클래스(인터페이스)
	 *  	org.apache.ibatis.executor.Executor(update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
	 *  	org.apache.ibatis.executor.parameter.ParameterHandler(getParameterObject, setParamaters)
	 *  	org.apache.ibatis.executor.resultset.ResultSetHandler(handlerResultSets, handleOutputParamaters)
	 *  	org.apache.ibatis.executor.statement.StatementHandler(prepare, parameterize, batch, update, query)
	 *  method: 지정한 type에서 타겟으로 지정할 메서드명
	 *  args: method에서 지정한 타겟 메서드의 인자 타입
	 */
	@Signature(
		type = org.apache.ibatis.executor.statement.StatementHandler.class,
		method = "prepare",
		args = { Connection.class }
	)
})
public class PagingInterceptor implements Interceptor
{
	private static final Log log = LogFactory.getLog(PagingInterceptor.class);
	private Properties prop;

	/**
	 * 쿼리에 페이징 처리를 위한 추가 쿼리를 적용<br>
	 * 2016-12-15 D'amo 함수 적용 공통화
	 * @param invocation 호출될 메서드 객체
	 * @return 메서드 객체 실행 결과의 Object
	 */
	@Override
	public Object intercept(Invocation invocation) throws Exception
	{
		Object result = null;	// 쿼리 실행 결과 Object
		StatementHandler sh = (StatementHandler)invocation.getTarget();
		MetaObject mo = MetaObject.forObject(sh, new DefaultObjectFactory(), new DefaultObjectWrapperFactory(), null);

		// D'amo 처리

		/*setEnc(mo);
		setDec(mo);*/

		/*String[] type = {"enc", "dec"};
		String[] pattern = {"HASH", "NORMAL", "PATTERN1", "PATTERN7", "PATTERN8"};
		for(String tp : type)
		{
			for(String pt : pattern)
			{
				setDAmo(mo, tp, pt);
			}
		}*/

		setPaging(mo);	// 페이징 처리

		result = invocation.proceed();	// 쿼리 실행 진행

		return result;	// 쿼리 실행 메서드의 결과물인 Object를 반환
	}

	/**
	 * 플러그인 등록 메서드
	 * @param target 실제 타겟 대상
	 * @return
	 */
	@Override
	public Object plugin(Object target)
	{
		return Plugin.wrap(target, this);
	}

	/**
	 * 플러그인 설정 시 지정한 프로퍼티 처리 메서드
	 * @param property 설정된 프로퍼티
	 */
	@Override
	public void setProperties(Properties property)
	{
		prop = property;

		log.debug("PROPERTY = " + prop.toString());
	}

	/**
	 * 쿼리에 페이징 처리
	 * @param mo
	 */
	private void setPaging(MetaObject mo)
	{
		String originalSql = (String)mo.getValue("delegate.boundSql.sql");

		if(originalSql.toUpperCase().startsWith("SELECT")
		&& !originalSql.toUpperCase().startsWith("SELECT COUNT(1) AS CNT"))	// SELECT로 시작하는 리스트 조회쿼리만 페이징 대상(카운트 쿼리는 제외)
		{
			@SuppressWarnings("unchecked")
			Map<String, Object> paramObject = (Map<String, Object>)mo.getValue("delegate.parameterHandler.parameterObject");

			if(paramObject.get(Constant.AX_PAGENO) != null
			&& paramObject.get(Constant.AX_PAGESIZE) != null)	// 화면단 자바스크립트의 페이징 옵션이 있는 경우에만 페이징 대상
			{
				// 페이지 파라미터 계산
				Integer pageNo = Integer.parseInt(paramObject.get(Constant.AX_PAGENO).toString());
				Integer pageSize = Integer.parseInt(paramObject.get(Constant.AX_PAGESIZE).toString());
				Integer startIndex = (pageNo - 1) * pageSize + 1;
				Integer endIndex = pageNo * pageSize;

				// 쿼리 수정
				String prefix = "SELECT * FROM ( SELECT ROWNUM AS PRNUM, A.* FROM ( ";
				String suffix = " ) A WHERE ROWNUM <= " + endIndex + " ) WHERE PRNUM >= " + startIndex;
				mo.setValue("delegate.boundSql.sql", prefix + originalSql + suffix);
			}
		}
	}

	/**
	 * 쿼리 컬럼 암호화 처리
	 * @param mo
	 */
	@SuppressWarnings("unused")
	private void setEnc(MetaObject mo)
	{
		/*String prefix = "scp.enc_b64('DAMO','SCP','PATTERN1.SCPS',";
		String suffix = ") AS ";*/
		String prefix = "";
		String suffix = "";

		String reservedSuffix = "_ENC";
		String originalSql = (String)mo.getValue("delegate.boundSql.sql");

		List<String> cols = StrUtil.findWord(originalSql, reservedSuffix, "[,\\s()=]");

		for(String col : cols)
		{
			/*if(col.contains("?"))	// 파라미터인 경우 파라미터 전용 처리
			{
				suffix = ") ";
			}
			else
			{
				suffix = ") AS " + col.replace(reservedSuffix, "");
			}*/

			/*System.err.println("col: " + col);
			System.err.println("ch col: " + prefix + col.replace(reservedSuffix, "") + suffix);*/
			originalSql = originalSql.replace(col, prefix + col.replace(reservedSuffix, "") + suffix);
		}

		mo.setValue("delegate.boundSql.sql", originalSql);
	}

	/**
	 * 쿼리 컬럼 복호화 처리
	 * @param mo
	 */
	@SuppressWarnings("unused")
	private void setDec(MetaObject mo)
	{
		/*String prefix = "scp.dec_b64('DAMO','SCP','PATTERN1.SCPS',";
		String suffix = ") AS ";*/
		String prefix = "";
		String suffix = "";

		String reservedSuffix = "_DEC";
		String originalSql = (String)mo.getValue("delegate.boundSql.sql");

		List<String> cols = StrUtil.findWord(originalSql, reservedSuffix, "[,\\s()=]");

		for(String col : cols)
		{
			/*if(col.contains("?"))	// 파라미터인 경우 파라미터 전용 처리
			{
				suffix = ") ";
			}
			else
			{
				suffix = ") AS " + col.replace(reservedSuffix, "");
			}*/

			/*System.err.println("col: " + col);
			System.err.println("ch col: " + prefix + col.replace(reservedSuffix, "") + suffix);*/
			originalSql = originalSql.replace(col, prefix + col.replace(reservedSuffix, "") + suffix);
		}

		mo.setValue("delegate.boundSql.sql", originalSql);
	}

	/**
	 * 쿼리 암복호화 처리
	 * @param mo
	 * @param type 암복호화 구분(enc, dec)
	 * @param pattern 암복호화 패턴(HASH, NORMAL, PATTERN1, PATTERN7, PATTERN8)
	 */
	@SuppressWarnings("unused")
	private void setDAmo(MetaObject mo, String type, String pattern)
	{
		String prefix = "scp." + type + "_b64('DAMO','SCP','" + pattern + ".SCPS',";

		if("HASH".equals(pattern))	// HASH 타입인 경우 예외처리
		{
			prefix = "scp.hash_b64(71, ";
		}

		String suffix = ") AS ";

		String reservedSuffix = "_" + type.toUpperCase() + "." + pattern;
		String originalSql = (String)mo.getValue("delegate.boundSql.sql");

		List<String> cols = StrUtil.findWord(originalSql, reservedSuffix, "[,\\s()=]");

		for(String col : cols)
		{
			if(col.contains("?"))	// 파라미터인 경우 파라미터 전용 처리
			{
				suffix = ") ";
			}
			else
			{
				suffix = ") AS " + col.replace(reservedSuffix, "");
			}

			/*System.err.println("col: " + col);
			System.err.println("ch col: " + prefix + col.replace(reservedSuffix, "") + suffix);*/
		}

		mo.setValue("delegate.boundSql.sql", originalSql);
	}
}
