package yk.opic.sql;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MybatisDaoFactory {
  InvocationHandler invocationHandler;

  public MybatisDaoFactory(SqlSessionFactory sqlSessionFactory) {
    this.invocationHandler = (Object proxy, Method method, Object[] args) -> {
      Class<?> daoInterface = proxy.getClass().getInterfaces()[0];
      String id = daoInterface.getName() + "." + method.getName();
      System.out.println("id : "+id);
      Class<?> returnType = method.getReturnType();
      try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
        if(returnType == List.class) {
          return args == null ?
              sqlSession.selectList(id, null) : sqlSession.selectList(id, args[0]);
        } else if(returnType == int.class || returnType == void.class) {
          return args == null ?
              sqlSession.insert(id, null) : sqlSession.insert(id, args[0]);
        } else {
          return args == null ?
              sqlSession.selectOne(id, null) : sqlSession.selectOne(id, args[0]);
        }
      }
    };
  }

  @SuppressWarnings("unchecked")
  public <T>T createDao(Class<T> daoInterface) {

    return (T) Proxy.newProxyInstance(
        this.getClass().getClassLoader(),
        new Class[] {daoInterface},
        invocationHandler);
  }

}
