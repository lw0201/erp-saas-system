package com.zhuoxun.it.iam.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zhuoxun.it.common.base.impl.BaseService;
import com.zhuoxun.it.common.conditions.Criteria;
import com.zhuoxun.it.common.conditions.Wrapper;
import com.zhuoxun.it.common.constans.Constants;
import com.zhuoxun.it.common.enums.TenantState;
import com.zhuoxun.it.common.enums.UserType;
import com.zhuoxun.it.common.exception.ApplicationException;
import com.zhuoxun.it.common.handler.BusinessCode;
import com.zhuoxun.it.common.utils.EncryptUtils;
import com.zhuoxun.it.common.utils.JWTUtils;
import com.zhuoxun.it.common.utils.RedisUtils;
import com.zhuoxun.it.common.utils.TokenUtil;
import com.zhuoxun.it.iam.dao.ITenantDao;
import com.zhuoxun.it.iam.dao.ITenantUserDao;
import com.zhuoxun.it.iam.dao.IUserDao;
import com.zhuoxun.it.iam.entity.TenantUserVO;
import com.zhuoxun.it.iam.entity.TenantVO;
import com.zhuoxun.it.iam.entity.UserVO;
import com.zhuoxun.it.iam.service.IUserService;

/**
 * User实体业务逻辑处理层
 * 
 * @author liwen
 *
 */
@Service
public class UserService extends BaseService<UserVO> implements IUserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private IUserDao userDao;

    @Autowired
    private ITenantUserDao tenantUserDao;

    @Autowired
    private ITenantDao iTenantDao;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public Map<String, Object> login(UserVO user, HttpServletResponse response, int userTpye) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        logger.info("UserService进入用户登录接口=============", user);

        Wrapper<UserVO> loginWrapper =
            new Wrapper<UserVO>().where(new Criteria().eq("userAccount", user.getUserAccount()))
                .or(new Criteria().eq("phone", user.getUserAccount()));
        List<UserVO> list = userDao.queryByLoginWrapper(loginWrapper);

        if (list == null || list.isEmpty()) {
            throw new ApplicationException(BusinessCode.NOT_FOUND_DATA, "用户不存在");
        }
        int flag = 0;
        UserVO userVO = new UserVO();
        for (int i = 0; i < list.size(); i++) {

            if (!EncryptUtils.md5Base64(user.getPassword()).equals(list.get(i).getPassword())) {
                ++flag;
            }
            if (EncryptUtils.md5Base64(user.getPassword()).equals(list.get(i).getPassword())) {
                userVO = list.get(i);
                break;
            }
        }
        if (flag == list.size()) {
            throw new ApplicationException("用户名或密码错误");
        }
        if (userVO.getState().intValue() != TenantState.Enable.getCode()) {
            throw new ApplicationException("账户已被禁用");
        }
        TenantUserVO TenantUserentity = new TenantUserVO();
        TenantUserentity.setUserId(userVO.getId());
        List<TenantUserVO> tenantUser = tenantUserDao.findList(TenantUserentity);
        if (tenantUser != null && !tenantUser.isEmpty()) {
            TenantVO tenant = iTenantDao.findById(tenantUser.get(0).getTenantId());
            if (tenant != null && System.currentTimeMillis() >= tenant.getEffectiveDate().getTime()) {
                throw new ApplicationException("您的账户已超过有效期");
            }
            if (tenant != null && tenant.getState().intValue() != TenantState.Enable.getCode()) {
                throw new ApplicationException("账户已被禁用");
            }
        }
        Map<String, Object> map = createLoginMap(userTpye, userVO);
        String jsonStr = JSON.toJSONString(map);
        //TODO屏蔽挤下线
//        tokenUtil.deleteToken(Constants.LOGIN_PER + userVO.getId());
        long seq = System.currentTimeMillis();
        String key = Constants.LOGIN_PER + userVO.getId() + "_" + seq;
        String jwtStr = JWTUtils.createJWT(UUID.randomUUID().toString().replaceAll("-", ""), key, jsonStr,
            Constants.JWT_EXPIR_TIME);
        
        logger.info("login_redis过期时间(秒)=========={}", Constants.REDIS_TOKEN_EXPIR_TIME);
        redisUtils.set(key, jwtStr, Constants.REDIS_TOKEN_EXPIR_TIME);
        JSONArray keyArray = new JSONArray();
        keyArray.add(key);
        redisUtils.set(Constants.LOGIN_PER + userVO.getId(), keyArray, Constants.REDIS_TOKEN_EXPIR_TIME);
        String token = EncryptUtils.encryptByAES(key, Constants.TOKEN_KEY);
        resultMap.put(HttpHeaders.AUTHORIZATION, token);
        map.remove("userId");
        resultMap.put("user-info", map);
        tokenUtil.setToken(token, response);
        userVO.setLastUpdateDate(new Date());
        userDao.update(userVO);
        return resultMap;
    }

    private Map<String, Object> createLoginMap(int userType, UserVO userVO) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userVO.getId());
        map.put("userAccount", userVO.getUserAccount());
        map.put("realName", userVO.getRealName());
        map.put("phone", userVO.getPhone());
        map.put("sex", userVO.getSex());
        map.put("birthDate", userVO.getBirthDate());
        map.put("lastUpdateDate", userVO.getLastUpdateDate());
        map.put("userType", userVO.getUserType());
        switch (UserType.forCode(userType)) {
            case System:
                break;
            case Tenant:
                TenantUserVO query = new TenantUserVO();
                query.setUserId(userVO.getId());
                TenantUserVO tuser = tenantUserDao.query(query);
                if (tuser == null) {
                    throw new ApplicationException("租户不存在");
                }
                TenantVO tenant = iTenantDao.findById(tuser.getTenantId());
                if (tenant == null) {
                    throw new ApplicationException("租户不存在");
                }
                if (tenant.getState().intValue() == TenantState.Disble.getCode()) {
                    throw new ApplicationException("租户已被禁用");
                }
                map.put("tenantId", tenant.getId());
                map.put("tenantCode", tenant.getTenantCode());
                map.put("tenantName", tenant.getTenantName());
                map.put("tenantType", tenant.getTenantType());
                map.put("tenantPhone", tenant.getPhone());
                map.put("effectiveDate", tenant.getEffectiveDate());
                map.put("userName", tenant.getUserName());
                map.put("creditCode", tenant.getCreditCode());
                break;
            case Guest:

                break;
            default:
                throw new ApplicationException("差找不到用户类型");
        }

        return map;
    }

    /**
     * 保存用户和租户信息
     */
    @Override
    public int saveTenantUser(UserVO user, String tenantId) {
        // 第一步保存用戶信息
        int resultRow = userDao.insert(user);
        if (resultRow > 0) {
            // 第二步保存租戶信息
            TenantUserVO tenantUserVO = new TenantUserVO();
            tenantUserVO.setTenantId(tenantId);
            tenantUserVO.setUserId(user.getId());
            tenantUserVO.setCreatedBy(user.getCreatedBy());
            tenantUserVO.setCreatedDate(user.getCreatedDate());
            tenantUserVO.setLastUpdateBy(user.getLastUpdateBy());
            tenantUserVO.setLastUpdateDate(user.getLastUpdateDate());
            return tenantUserDao.insert(tenantUserVO);
        } else {
            return resultRow;
        }
    }

}
