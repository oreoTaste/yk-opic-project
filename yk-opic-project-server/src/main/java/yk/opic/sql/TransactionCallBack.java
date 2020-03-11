package yk.opic.sql;

public interface TransactionCallBack {
  Object doInTransaction() throws Exception;
}
