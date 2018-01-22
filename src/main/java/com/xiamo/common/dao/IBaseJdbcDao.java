/**
 * 
 */
package com.xiamo.common.dao;


import com.xiamo.common.vo.PageInfo;
import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * 基于JDBC的DAO的公共操作接口，对数据库表提供标准的增删改查功能
 * 
 * @author 刘江宁
 * 
 */
public interface IBaseJdbcDao {
	/**
	 * 
	 * @param sql  sql语句
	 * @param args 参数值
	 * @param argTypes 参数类型
	 * @return
	 * @throws DataAccessException
	 */
    int update(String sql, Object[] args, int[] argTypes)throws DataAccessException;
	
	/**
	 * 执行查询SQL语句，返回String值
	 * 
	 * @param sql
	 * @return
	 * @throws
	 */
    String queryForString(String sql) throws DataAccessException;
	/**
	 * 执行查询SQL语句，返回int值
	 * 
	 * @param sql
	 * @return
	 * @throws DataAccessException
	 */
    int queryForInt(String sql) throws DataAccessException;
	/**
	 * 执行查询SQL语句，返回long值
	 * 
	 * @param sql
	 * @return
	 * @throws DataAccessException
	 */
    long queryForLong(String sql) throws DataAccessException;

	/**
	 * 执行查询SQL语句
	 * 
	 * @param sql
	 * @param objectClass
	 * @return 返回Class的对象
	 * @throws DataAccessException
	 *
	 */
    <T> Object queryForObject(String sql, Class<T> objectClass)
			throws DataAccessException;

	/**
	 * 校验表是否存在
	 * 
	 * @param tableName
	 * @return
	 * @throws DataAccessException
	 */
    boolean tableExist(String tableName) throws DataAccessException;

	/**
	 * 校验数据是否存在。
	 * 
	 * @param tableName
	 *            需要校验的表名
	 * @param whereClause
	 *            查询条件子句，不需要包含“where”关键字
	 * @return
	 * @throws DataAccessException
	 *
	 */
    boolean dataExist(String tableName, String whereClause)
			throws DataAccessException;

	/**
	 * 返回指定的sequence的下一个值
	 * 
	 * @param name
	 * @return
	 * @throws DataAccessException
	 */
    long getSequence(String name) throws DataAccessException;

	/**
	 * 执行查询SQL语句
	 * 
	 * @param sql
	 * @return 返回结果列表
	 * @throws DataAccessException
	 */
    <T> List<T> queryForList(String sql, Object[] args, int[] argTypes,
                             Class<T> poClass) throws DataAccessException;

	/**
	 * 执行查询SQL语句
	 * 
	 * @param sql
	 * @return Object
	 * @throws DataAccessException
	 */
    <T> T queryForObject(String sql, Object[] args, int[] argTypes,
                         Class<T> poClass) throws DataAccessException;

	

	/**
	 * 执行分页查询SQL语句
	 * 
	 * @param sql
	 * @return 返回结果列表
	 * @throws DataAccessException
	 */
    <T> List<T> queryByPage(String sql, Object[] args, int[] argTypes,
                            PageInfo page, Class<T> poClass) throws DataAccessException;

	/**
	 * 执行分页查询SQL语句
	 * 
	 * @param sql
	 * @return 返回结果列表
	 * @throws DataAccessException
	 */
    <T> List<T> queryByPage(String sql, PageInfo page, Class<T> poClass)
			throws DataAccessException;
	
	/**
	 * 获取Sequence
	 * @param seqName
	 * @return
	 * @throws DataAccessException
	 */
    String getSequenceStr(String seqName) throws DataAccessException;
	 /**
     * 执行sql语句 得到记录总数
     * @param sql
     * @param args
     * @param argTypes
     * @return int
     * @throws DataAccessException
     */
     int getMaxRowCount(String sql, Object[] args, int[] argTypes)
			throws DataAccessException;
	/**
     * 执行sql语句 得到记录总数
     * @param sql
     * @return int
     * @throws DataAccessException
     */
    int getMaxRowCount(String sql) throws DataAccessException;
	/**
	 * 执行查询SQL语句
	 * 
	 * @param sql
	 * @return 返回结果列表
	 * @throws DataAccessException
	 * 
	 *
	 */
    <T> List<T> queryForList(String sql, Class<T> poClass)
			throws DataAccessException;
}
