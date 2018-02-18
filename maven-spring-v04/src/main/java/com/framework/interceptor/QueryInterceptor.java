package com.framework.interceptor;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

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
		type = org.apache.ibatis.executor.Executor.class,
		method = "update",
		args = { MappedStatement.class, Object.class }
	),
	@Signature(
		type = org.apache.ibatis.executor.Executor.class,
		method = "query",
		args = {
			MappedStatement.class,
			Object.class,
			org.apache.ibatis.session.RowBounds.class,
			org.apache.ibatis.session.ResultHandler.class,
			org.apache.ibatis.cache.CacheKey.class,
			BoundSql.class
		}
	),
	@Signature(
		type = org.apache.ibatis.executor.Executor.class,
		method = "query",
		args = {
			MappedStatement.class,
			Object.class,
			org.apache.ibatis.session.RowBounds.class,
			org.apache.ibatis.session.ResultHandler.class
		}
	)
})
public class QueryInterceptor implements Interceptor
{
	private static final Log log = LogFactory.getLog(QueryInterceptor.class);
	private Properties prop;

	/**
	 * 메서드 실행 전에 실행되는 인터셉터 메서드
	 * @param invocation 호출될 메서드 객체
	 * @return 메서드 객체 실행 결과의 Object
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable
	{
		Object result;	// 쿼리 실행 결과 Object

		/*Object[] args = invocation.getArgs();
		MappedStatement ms = (MappedStatement)args[0];	// MappedStatement
		Object param = args[1];	// 쿼리 파라미터 Object

		// 쿼리 출력(파라미터 적용 전)
		BoundSql boundSql = ms.getBoundSql(param);
		String sql = boundSql.getSql();
		log.debug("\n=================================================================\n"
				+ "[" + ms.getId() + "] - INPUT SQL\n"
				+ sql
				+ "\n=================================================================");

		// 파라미터 적용 쿼리 문자열 생성
		try
		{
			if(param == null)	// 파라미터가 없을 경우
			{
				sql = sql.replaceAll("\\?", "''");
			}
			else if(   param instanceof Integer
					|| param instanceof Long
					|| param instanceof Float
					|| param instanceof Double)	// 파라미터가 숫자 타입일 경우(단일 파라미터)
			{
				sql = sql.replaceFirst("\\?", String.valueOf(param));
			}
			else if(param instanceof String)	// 파라미터가 문자열 타입일 경우(단일 파라미터)
			{
				sql = sql.replaceFirst("\\?", "'" + (String)param + "'");
			}
			else if(param instanceof Map)	// 파라미터가 맵 타입일 경우
			{
				List<ParameterMapping> paramMapping = boundSql.getParameterMappings();

				for(ParameterMapping mapping : paramMapping)
				{
					String propValue = mapping.getProperty();
					sql = sql.replaceFirst("\\?", "'" + ((Map)param).get(propValue) + "'");
				}
			}
			else	// 파라미터가 그 외(빈 형식?)일 경우
			{
				List<ParameterMapping> paramMapping = boundSql.getParameterMappings();

				for(ParameterMapping mapping : paramMapping)
				{
					String propValue = mapping.getProperty();
					sql = sql.replaceFirst("\\?", "'" + PropertyUtils.getSimpleProperty(param, propValue)+ "'");
				}
			}
		}
		catch(Exception e)
		{
			log.error("QueryInterceptor Exception: " + e.getMessage());
		}

		// 쿼리 출력(파라미터 적용 후)
		log.debug("\n=================================================================\n"
				+ "[" + ms.getId() + "] - MAPPING SQL\n"
				+ sql
				+ "\n=================================================================");*/

		result = invocation.proceed();	// 쿼리 실행 진행

		// 실행 결과 출력
		/*if(result != null && result instanceof List)
		{
			List<Object> list = (List<Object>) result;

			String logStr = "";
			log.debug(result.getClass().getName());
			logStr += "\n================================================================="
					+ "\n\t" + "[Result]"
					+ "\n\t" + "size of result: " + list.size() + "\n";

			for(int i=0; i<list.size(); i++)
			{
				logStr += "\n\t" + list.get(i).getClass().getName() + " result[" + i + "]: ";

				Object row = list.get(i);

				if(row instanceof Map)
				{
					Iterator<String> iterKey = ((Map<String, Object>)row).keySet().iterator();
					while(iterKey.hasNext())
					{
						String key = iterKey.next();
						logStr += key + "=" + ((Map<String, Object>)row).get(key) + ", ";
					}
				}
				else
				{
					logStr += row.toString();
				}
			}

			logStr += "\n=================================================================";
			log.debug(logStr);
		}*/

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
}
