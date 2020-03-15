package yk.opic.sql;

import java.sql.Connection;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;

public class SqlSessionFactoryProxy implements SqlSessionFactory{

  SqlSessionFactory original;
  ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();

  public SqlSessionFactoryProxy(SqlSessionFactory original) {
    this.original = original;
  }


  public void close() {

  }

  @Override
  public SqlSession openSession() {
    return this.openSession(true);
  }

  @Override
  public SqlSession openSession(boolean autoCommit) {
    SqlSession sqlSession = threadLocal.get();
    if(sqlSession != null) {
      return sqlSession;
    }
    sqlSession = new SqlSessionProxy(original.openSession(autoCommit));
    threadLocal.set(sqlSession);
    return sqlSession;
  }

  public void closeSession() {
    SqlSession sqlSession = threadLocal.get();
    if(sqlSession != null) {
      threadLocal.remove();
      ((SqlSessionProxy)sqlSession).realClose();
    }
  }

  @Override
  public SqlSession openSession(Connection connection) {
    return original.openSession(connection);
  }

  @Override
  public SqlSession openSession(TransactionIsolationLevel level) {
    return original.openSession(level);
  }

  @Override
  public SqlSession openSession(ExecutorType execType) {
    return original.openSession(execType);
  }

  @Override
  public SqlSession openSession(ExecutorType execType, boolean autoCommit) {
    return original.openSession(execType, autoCommit);
  }

  @Override
  public SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level) {
    return original.openSession(execType, level);
  }

  @Override
  public SqlSession openSession(ExecutorType execType, Connection connection) {
    return original.openSession(execType, connection);
  }

  @Override
  public Configuration getConfiguration() {
    return original.getConfiguration();
  }


}
