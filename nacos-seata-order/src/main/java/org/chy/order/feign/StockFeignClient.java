/*
 *  Copyright 1999-2021 Seata.io Group.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.chy.order.feign;

import org.chy.order.response.ResponseEntityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chy
 * @Title: 库存接口
 * @Description:
 * @date 2022/12/2 22:36
 */
@FeignClient(name = "nacos-seata-stock")
public interface StockFeignClient {

    @PostMapping("stock/deduct")
    ResponseEntityDTO<Boolean> deduct(@RequestParam("commodityCode") String commodityCode, @RequestParam("count") Integer count);
}
