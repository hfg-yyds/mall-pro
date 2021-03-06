package com.hacker.service;

import com.hacker.vo.AttrGroupRelationVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hacker.entity.AttrAttrgroupRelationEntity;

import java.util.List;

/**
 * 属性&属性分组关联
 *
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-10-01 21:08:49
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {


    void saveBatch(List<AttrGroupRelationVo> vos);

}

