package org.chy.carorder.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.chy.carorder.bo.CarOderSearchBo;
import org.chy.carorder.dao.CarOrderDao;
import org.chy.carorder.dto.req.CarOderSearchReqDto;
import org.chy.carorder.dto.resp.CarOderSearchRespDto;
import org.chy.carorder.entity.CarOrderEntity;
import org.chy.carorder.mapper.ext.CarOrderMapperExt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by chy on 2022/1/23.
 */
@Service
public class CarOrderDaoImpl extends
        ServiceImpl<CarOrderMapperExt, CarOrderEntity> implements
        CarOrderDao {
    @Override
    public List<CarOrderEntity> selectPage(Long pageNum, Long limit) {
        // 设置分页
        IPage<CarOrderEntity> page = new Page<>(pageNum, limit);
        // 设置查询条件
        LambdaQueryWrapper<CarOrderEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(CarOrderEntity::getId);
        IPage<CarOrderEntity> pageRecords = super.baseMapper.selectPage(page, wrapper);
        return pageRecords.getRecords();
    }

    @Override
    public CarOderSearchRespDto search(CarOderSearchReqDto reqDto) {
        CarOderSearchBo bo = new CarOderSearchBo();
        bo.setId(reqDto.getId());
        bo.setCarNo(reqDto.getCarNo());
        bo.setOrderNo(reqDto.getOrderNo());
        // 设置分页
        Page<CarOrderEntity> page = new Page<>(reqDto.getPageNum(), reqDto.getPageSize());
        // 返回分页结果
        List<CarOrderEntity> datas = super.baseMapper.search(page, bo);
        // 收集结果
        CarOderSearchRespDto result = new CarOderSearchRespDto();
        result.setList(datas);
        result.setPages(page.getPages());
        result.setPageNum(page.getCurrent());
        result.setTotal(page.getTotal());
        result.setPageSize(page.getSize());
        return result;
    }

    @Override
    public List<CarOrderEntity> searchByCarNo(String carNo) {
        LambdaQueryWrapper<CarOrderEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CarOrderEntity::getCarNo, carNo);
        return super.baseMapper.selectList(wrapper);
    }

    @Override
    public boolean add(CarOrderEntity entity) {
        return super.baseMapper.insert(entity)>0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class,RuntimeException.class})
    public boolean updateStockById(Integer id, Integer stockCount) {
        CarOrderEntity carOrderEntity = new CarOrderEntity();
        carOrderEntity.setStockCount(stockCount);
        LambdaQueryWrapper<CarOrderEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CarOrderEntity::getId, id);
        return super.baseMapper.update(carOrderEntity, wrapper) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class,RuntimeException.class})
    public boolean updateStockByCarNo(String carNo, Integer stockCount) {
        CarOrderEntity carOrderEntity = new CarOrderEntity();
        carOrderEntity.setStockCount(stockCount);
        LambdaQueryWrapper<CarOrderEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CarOrderEntity::getCarNo, carNo);
        return super.baseMapper.update(carOrderEntity, wrapper) > 0;
    }

    @Override
    public boolean delete(String carNo) {
        LambdaQueryWrapper<CarOrderEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CarOrderEntity::getCarNo, carNo);
        return super.baseMapper.delete(wrapper) > 0;
    }
}