package org.wigo.demos.mybatis;

import org.apache.ibatis.annotations.Param;

/**
 * @author wuwei
 * @since 2021/4/12
 */
public interface UserMapper {

    User selectById(Long id);

    User selectById2(@Param("id") Long id);
}
