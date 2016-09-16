package com.sapestore.service.impl;

import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.InventoryDao;
import com.sapestore.dao.ProductDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.service.InventoryService;
import com.sapestore.vo.BookVO;

// TODO: Auto-generated Javadoc
/**
 * Service class for Manage Inventory functionality.
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */

@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {

  /** The Constant LOGGER. */
  private static final  SapeStoreLogger LOGGER = 
      SapeStoreLogger.getLogger(InventoryServiceImpl.class.getName());

  /** The inventory dao. */
  @Autowired
  private InventoryDao inventoryDao;

  /** The book dao. */
  @Autowired
  private ProductDao bookDao;

  /* (non-Javadoc)
   * @see com.sapestore.service.InventoryService#getAdminInventory()
   */
  @Override
  public List<Book> getAdminInventory() {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("getAdminInventory method: START");
    }
    List<Book> adminInventoryBeanList = null;
    try {
      adminInventoryBeanList = inventoryDao.getAdminInventory();
    } catch (Exception e) {
      LOGGER.error("getAdminInventory method: ERROR: " + e);
    }
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("getAdminInventory method: END");
    }
    return adminInventoryBeanList;
  }

  /* (non-Javadoc)
   * @see com.sapestore.service.InventoryService#updateBooks(com.sapestore.vo.BookVO)
   */
  @Override
  public void updateBooks(BookVO updateInventory) throws SapeStoreException {
    BasicConfigurator.configure();
    LOGGER.debug("updateBooks method: START");

    System.out.println("----------in updateBooks---------->" + updateInventory);
    if (null != updateInventory) {
      bookDao.updateBooks(updateInventory);
    }
    LOGGER.debug("updateBooks method: END");
  }

}
