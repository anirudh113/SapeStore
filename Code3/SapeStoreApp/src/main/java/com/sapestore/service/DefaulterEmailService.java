
package com.sapestore.service;

import org.springframework.stereotype.Service;

/**
 * @author hsin59.
 */
@Service("defaulterEmailService")
public interface DefaulterEmailService {
  /**
   * @param ids
   *          This is storing email.
   * @return String
   */
  String sendDefaulterNotification(String ids);
}
