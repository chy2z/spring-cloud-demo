package org.chy.carorder.controller;

import org.chy.carorder.entity.CUser;
import org.chy.carorder.entity.MUser;
import org.chy.carorder.entity.Users;
import org.chy.carorder.entity.response.ResponseEntityDTO;
import org.chy.inject.InjectCUser;
import org.chy.inject.InjectMUser;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chy on 2021/10/10.
 */
@RestController
@RequestMapping("/user")
public class InjectUserInfoController {

    /**
     * 测试获取配置
     * http://localhost:8080/user/cuser?searchKey=1
     *
     * @return
     */
    @GetMapping("/cuser")
    public ResponseEntityDTO<String> getCUser(@RequestParam(value = "searchKey", required = false) String searchKey, @InjectCUser CUser cUser) {
        return ResponseEntityDTO.success(cUser.getName());
    }

    /**
     * 测试获取配置
     * http://localhost:8080/user/muser
     *
     * @return
     */
    @PostMapping("/muser")
    public ResponseEntityDTO<String> getMUser(@RequestBody Users users, @InjectMUser MUser mUser) {
        return ResponseEntityDTO.success(mUser.getName());
    }

    /**
     * 测试获取配置
     * http://localhost:8080/user/twouser
     *
     * @return
     */
    @PostMapping("/twouser")
    public ResponseEntityDTO<String> getMUser(@RequestBody Users users, @InjectMUser MUser mUser, @InjectCUser CUser cUser) {
        return ResponseEntityDTO.success(users.getName() + "|" + mUser.getName() + "|" + cUser.getName());
    }
}