package yk.opic.service.impl;

import java.util.List;
import yk.opic.project.dao.PhotoBoardDao;
import yk.opic.project.dao.PhotoFileDao;
import yk.opic.project.domain.PhotoBoard;
import yk.opic.service.PhotoBoardService;
import yk.opic.sql.PlatformTransactionManager;
import yk.opic.sql.TransactionCallBack;
import yk.opic.sql.TransactionTemplate;

public class PhotoBoardServiceImpl implements PhotoBoardService{
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;
  TransactionTemplate txTemplate;

  public PhotoBoardServiceImpl(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao,
      PlatformTransactionManager txManager) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
    this.txTemplate = new TransactionTemplate(txManager);
  }

  @Override
  public int add(PhotoBoard photoBoard) throws Exception {
    return (int) txTemplate.execute(() -> {
      if(photoBoardDao.insert(photoBoard)==0) {
        throw new Exception("사진 게시글 등록에 실패했습니다.");
      }
      return photoFileDao.insert(photoBoard);
    });
  }

  @Override
  public int delete(int photoBoardNo) throws Exception {

    return (int) txTemplate.execute(() -> {
      photoFileDao.deleteAll(photoBoardNo);
      return photoBoardDao.delete(photoBoardNo);
    });
  }


  @Override
  public PhotoBoard get(int photoNo) throws Exception {
    return photoBoardDao.findByNo(photoNo);
  }

  @Override
  public List<PhotoBoard> getLesson(int lessonNo) throws Exception {
    return photoBoardDao.findAllByLessonNo(lessonNo);
  }

  @Override
  public List<PhotoBoard> list() throws Exception {
    return null;
  }

  @Override
  public int update(PhotoBoard photoBoard) throws Exception {

    txTemplate.execute(new TransactionCallBack() {
      @Override
      public Object doInTransaction() throws Exception {
        if(photoBoardDao.update(photoBoard)==0) {
          throw new Exception("사진 게시글 등록에 실패했습니다.");
        }

        if(photoBoard.getFiles() != null) {
          photoFileDao.deleteAll(photoBoard.getNo());
          photoFileDao.insert(photoBoard);
        }
        return 1;
      }

    });
    return 0;
  }


}
