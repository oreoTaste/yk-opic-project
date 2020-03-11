package yk.opic.sql;

public class TransactionTemplate {

  PlatformTransactionManager txManager;

  public TransactionTemplate(PlatformTransactionManager txManager) {
    this.txManager = txManager;
  }

  public void execute(TransactionCallBack txCallBack) throws Exception {

    try {
      txManager.beginTransaction();

      txCallBack.doInTransaction();

      txManager.commit();
    } catch(Exception e) {
      try {
        txManager.rollback();
      } catch (Exception e1) {
      }
    }

  }

}
