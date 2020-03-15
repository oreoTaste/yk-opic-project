package yk.opic.sql;

public class TransactionTemplate {

  PlatformTransactionManager txManager;

  public TransactionTemplate(PlatformTransactionManager txManager) {
    this.txManager = txManager;
  }

  public Object execute(TransactionCallBack txCallBack) throws Exception {

    try {
      txManager.beginTransaction();

      txCallBack.doInTransaction();

      txManager.commit();
      return 1;
    } catch(Exception e) {
      try {
        txManager.rollback();
        return 0;
      } catch (Exception e1) {
        return 0;
      }
    }

  }


}
