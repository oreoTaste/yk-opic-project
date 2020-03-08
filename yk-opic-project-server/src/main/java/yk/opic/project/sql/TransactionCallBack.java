package yk.opic.project.sql;

public interface TransactionCallBack {
  Object doInTransaction() throws Exception;
}
