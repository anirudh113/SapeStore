package com.sapestore.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.ProductDao;
import com.sapestore.vo.BookVO;

public class FileValidator implements Validator {
  @Autowired
  private ProductDao dao;

  public ProductDao getDao() {
    return dao;
  }

  public void setDao(ProductDao dao) {
    this.dao = dao;
  }

  private static final  SapeStoreLogger LOGGER = SapeStoreLogger
      .getLogger(ProductDao.class.getName());

  @Override
  public boolean supports(Class<?> arg0) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void validate(Object arg0, Errors arg1) {
    LOGGER.error("valisate");

    BookVO addBooks = (BookVO) arg0;

    if (addBooks.getThumbImage().getSize() == 0) {

      arg1.rejectValue("thumbImage", "addBooks.thumbImage",
          "Please provide book thumbnail image.");
    }

    if (addBooks.getFullImage().getSize() == 0) {
      arg1.rejectValue("fullImage", "addBooks.fullImage",
          "Please provide book detail image.");
    }

    if (addBooks.getRentAvailable() != null
        && addBooks.getRentAvailable().equalsIgnoreCase("Y")
        && (addBooks.getRentPrice() == null || (addBooks.getRentPrice() != null
            && addBooks.getRentPrice().trim().isEmpty()))) {
      arg1.rejectValue("rentPrice", "addBooks.rentPrice",
          "Please provide book rent price.");
    }

  }

}
