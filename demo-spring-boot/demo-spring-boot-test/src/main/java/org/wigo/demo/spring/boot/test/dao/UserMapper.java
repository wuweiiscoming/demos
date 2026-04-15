package org.wigo.demo.spring.boot.test.dao;

import org.apache.ibatis.annotations.Param;
import org.wigo.demo.spring.boot.test.entity.User;

/**
 * @author wuwei
 * @since 2021/4/12
 */
public interface UserMapper {

    User selectById(Long id);

    User selectById2(@Param("id") Long id);
}
