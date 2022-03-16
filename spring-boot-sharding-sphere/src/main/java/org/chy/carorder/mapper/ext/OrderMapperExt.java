package org.chy.carorder.mapper.ext;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.chy.carorder.dto.req.OrderSearchReqDTO;
import org.chy.carorder.mapper.OrderMapper;

import java.util.List;
/**
 * mybatis xml扩展能力
 */
public interface OrderMapperExt extends OrderMapper {
    List<Long> search(Page<String> page, @Param("query") OrderSearchReqDTO query);
}
