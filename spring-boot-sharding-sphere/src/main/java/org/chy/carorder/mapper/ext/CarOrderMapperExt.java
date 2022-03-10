package org.chy.carorder.mapper.ext;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.chy.carorder.bo.CarOderSearchBo;
import org.chy.carorder.entity.CarOrderEntity;
import org.chy.carorder.mapper.CarOrderMapper;

import java.util.List;

/**
 * mybatis xml扩展能力
 */
public interface CarOrderMapperExt extends CarOrderMapper {
    /**
     * 搜索主订单列表
     */
    List<CarOrderEntity> search(Page<CarOrderEntity> page, @Param("query") CarOderSearchBo query);
}
