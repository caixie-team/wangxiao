package io.wangxiao.edu.home.service.impl.user;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.Security.PurseSecurityUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.commons.util.web.WebUtils;
import io.wangxiao.edu.common.constants.CommonConstants;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.common.contoller.SingletonLoginUtils;
import io.wangxiao.edu.common.util.StatisticsUtil;
import io.wangxiao.edu.common.util.UserAgentUtil;
import io.wangxiao.edu.home.constants.enums.*;
import io.wangxiao.edu.home.dao.user.UserDao;
import io.wangxiao.edu.home.dao.user.UserExpandDao;
import io.wangxiao.edu.home.dao.user.UserIntegralDao;
import io.wangxiao.edu.home.entity.user.*;
import io.wangxiao.edu.home.service.card.CardCodeService;
import io.wangxiao.edu.home.service.order.ShopcartService;
import io.wangxiao.edu.home.service.order.TrxorderService;
import io.wangxiao.edu.home.service.user.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private ShopcartService shopcartService;

    @Autowired
    UserIntegralDao userIntegralDao;
    private static final int HMAC_KEY_LEN = 60;
    //redis存储
    CacheKit cacheKit = CacheKit.getInstance();
    @Autowired
    public RedisTemplate<String, long[]> redisTemplate;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Getter
    @Setter
    private Map<String, Object> userMsg = new HashMap<String, Object>();

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserExpandDao userExpandDao;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private UserCodeService userCodeService;
    @Autowired
    private TrxorderService trxorderService;// 订单服务层
    @Autowired
    private UserExpandService userExpandService;
    @Autowired
    private UserIntegralService userIntegralService;
    @Autowired
    private UserLoginLogService userLoginLogService;
    @Autowired
    private LoginOnlineService loginOnlineService;
    @Autowired
    private CardCodeService cardCodeService;
    @Autowired
    private UserGroupMiddleService userGroupMiddleService;

    /**
     * 添加User
     *
     * @param user 要添加的User
     * @return id
     */
    public Long addUser(User user) {
        // 密码加密
        generatePassword(user);
        user.setCreatedate(new Date());
        userDao.addUser(user);
        // 加入扩展信息,扩展的用户信息以后使用
        UserExpand userExpand = new UserExpand();
        userExpand.setCusId(user.getId());
        userExpand.setLastSystemTime(new Date());
        userExpand.setRegisterFrom(user.getRegisterFrom());// 账号来源
        userExpand.setRealname(user.getRealname());
        userExpandDao.addUserExpand(userExpand);

        // 添加账户
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(user.getId());
        userAccount.setCreateTime(new Date());
        userAccount.setLastUpdateTime(new Date());
        userAccount.setBalance(new BigDecimal(0));
        userAccount.setForzenAmount(new BigDecimal(0));
        userAccount.setAccountStatus(AccountStatus.ACTIVE.toString());
        userAccount.setCashAmount(new BigDecimal(0));
        userAccount.setVmAmount(new BigDecimal(0));
        userAccount.setVersion(Long.valueOf(0));
        userAccountService.addUserAccount(userAccount);

        // 添加积分表
        UserIntegral userIntegral = new UserIntegral();
        userIntegral.setUserId(user.getId());// 用户id
        userIntegral.setCurrentScore(0L);// 当前积分
        userIntegral.setTotalScore(0L);// 总积分
        userIntegralDao.addUserIntegral(userIntegral);// 添加用户积分
        // 今日新增用户 存入redis
        StatisticsUtil.incrActionOneDayOrActionCount(MemConstans.MEMFIX + StatisticsQueryType.REGISTER_NUM.toString(), sdf.format(new Date()));
        return user.getId();
    }

    /**
     * 根据id删除一个User
     *
     * @param id 要删除的id
     */
    public void deleteUserById(Long id) {
        userDao.deleteUserById(id);
    }

    /**
     * 修改User
     *
     * @param user 要修改的User
     */
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    /**
     * 修改QueryUser
     *
     * @throws Exception
     */
    public String updateQueryUser(UserExpandDto queryUser) throws Exception {
        // 用户数据
//		User u = getUserById(queryUser.getId());
        // 修改用户表
        User user = new User();
        user.setId(queryUser.getId());
        user.setNickname(queryUser.getNickname());
//		user.setEmail(queryUser.getEmail());
//		user.setMobile(queryUser.getMobile());
//		user.setUpdateEmail(queryUser.getUpdateEmail());
//		// 验证手机唯一
//		if (StringUtils.isNotEmpty(queryUser.getMobile()) && !u.getMobile().equals(queryUser.getMobile()) && getUserByMobile(user) > 0) {
//			return "mobileHave";
//		}
        this.updateWebUser(user);
        // 修改用户拓展表
        UserExpand uex = userExpandService.getUserExpandByUserId(queryUser.getId());
        UserExpand userExpand = new UserExpand();
        userExpand.setCusId(queryUser.getId());
        userExpand.setGender(queryUser.getGender());
        userExpand.setNickname(queryUser.getNickname());
        userExpand.setRealname(queryUser.getRealname());
        userExpand.setAvatar(uex.getAvatar());
        userExpand.setUserInfo(queryUser.getUserInfo());
        userExpand.setRegisterFrom(uex.getRegisterFrom());
        userExpandService.updateUserExpand(userExpand);
        // 删除缓存
        cacheKit.remove(MemConstans.USEREXPAND_INFO + queryUser.getId());
        return "success";
    }

    /**
     * 修改QueryUser
     *
     * @throws Exception
     */
    public String updateAppQueryUser(UserExpandDto queryUser) throws Exception {
        // 修改用户表
        if (queryUser.getNickname() != null && !queryUser.getNickname().equals("")) {
            User user = new User();
            user.setId(queryUser.getId());
            user.setNickname(queryUser.getNickname());
            this.updateUser(user);
        }
        // 修改用户拓展表
        UserExpandDto userExpandDto = userExpandService.getUserExpandByUid(queryUser.getId());
        if (queryUser.getGender() > -1) {
            userExpandDto.setGender(queryUser.getGender());
        }
        if (queryUser.getRealname() != null && !queryUser.getRealname().equals("")) {
            userExpandDto.setRealname(queryUser.getRealname());
        }
        if (queryUser.getUserInfo() != null && !queryUser.getUserInfo().equals("")) {
            userExpandDto.setUserInfo(queryUser.getUserInfo());
        }
        UserExpand userExpand = new UserExpand();
        userExpand.setCusId(userExpandDto.getId());
        userExpand.setGender(userExpandDto.getGender());
        userExpand.setRealname(userExpandDto.getRealname());
        userExpand.setAvatar(userExpandDto.getAvatar());
        userExpand.setUserInfo(userExpandDto.getUserInfo());
        userExpand.setRegisterFrom(userExpandDto.getRegisterFrom());
        userExpandService.updateUserExpand(userExpand);
        // 删除缓存
        cacheKit.remove(MemConstans.USEREXPAND_INFO + queryUser.getId());
        return "success";
    }

    /**
     * 通过用户id更新用户Isavalible （冻结，解冻操作）
     *
     * @param user 要修改的User
     */
    public void updateUserForIsavalibleById(User user) {
        userDao.updateUserForIsavalibleById(user);
    }

    /**
     * 根据id获取单个User对象
     *
     * @param id 要查询的id
     * @return User
     */
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    /**
     * 通过用户id 更新密码
     *
     * @return User
     */
    public void updatePwdById(User user, UserCode userCode) {
        generatePassword(user);
        userDao.updatePwdById(user);
        if (ObjectUtils.isNotNull(userCode)) {
            // 更新邮件验证码的状态
            userCode.setStatus(1L);// 更新为已经使用
            userCodeService.updateUserCode(userCode);
        }
    }

    /**
     * 通过用户id 更新密码
     *
     * @return User
     */
    public void updatePwdById(User user) {
        generatePassword(user);
        userDao.updatePwdById(user);
    }

    /**
     * 根据条件获取User列表
     *
     * @param user 查询条件email
     * @return List<User>
     */
    public List<User> getUserList(User user) {
        return userDao.getUserList(user);
    }

    /**
     * 根据条件获取User列表(用户登陆添加了冻结状态)
     *
     * @param user 查询条件
     * @return List<User>
     */
    public List<User> getUserListForLogin(User user) {
        return userDao.getUserListForLogin(user);
    }

    /**
     * 根据手机号获取User列表(用户登陆添加了冻结状态)
     *
     * @param user 查询条件
     * @return List<User>
     */
    public List<User> getUserListForTelLogin(User user) {
        return userDao.getUserListForTelLogin(user);
    }

    /**
     * 根据条件获取User列表
     *
     * @param user 用户
     * @param page 分页参数
     * @return
     */
    public List<User> getUserListByCondition(User user, PageEntity page) {
        return userDao.getUserListByCondition(user, page);
    }

    /**
     * 根据条件获取User列表 带课程名称
     *
     * @param user 用户
     * @param page 分页参数
     * @return
     */
    public List<User> getUserListAndCourse(User user, PageEntity page) {
        return userDao.getUserListAndCourse(user, page);
    }

    /**
     * 设计加密密码
     *
     * @param user
     */
    private void generatePassword(User user) {
        String customerKey = StringUtils.getRandomString(HMAC_KEY_LEN);
        String password = PurseSecurityUtils.secrect(user.getPassword(), customerKey);
        user.setPassword(password);
        user.setCustomerkey(customerKey);
    }

    /**
     * 设置用户为登录状态
     *
     * @param user
     * @param response
     * @return
     */
    public Map<String, Object> setLoginStatus(User user, String autoThirty, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 存储登录的信息到缓存中
            UserExpandDto userExpandDto = queryUserExpand(user.getId());
            Long current = System.currentTimeMillis();
            userExpandDto.setCurrent(current);
            String uuid = StringUtils.createUUID().replace("-", "");
            // 存数用户的登录信息
            // cacheKit.set(uuid, userExpandDto, MemConstans.USER_TIME);
            UserIntegral userIntegral = userIntegralService.getUserIntegralByUserId(user.getId());
            userExpandDto.setScore(userIntegral.getCurrentScore() == null ? 0 : userIntegral.getCurrentScore());
            SingletonLoginUtils.setLoginInfo(uuid, userExpandDto, autoThirty);
            if (autoThirty != null && autoThirty.equals("true")) {// 7天自动登录
                cacheKit.set(MemConstans.USER_CURRENT_LOGINTIME + user.getId(), current.toString(), MemConstans.USER_AUTO_TIME);
                WebUtils.setCookie(response, CommonConstants.USER_SINGEL_ID, uuid, 7);
                WebUtils.setCookie(response, CommonConstants.USER_SINGEL_NAME, URLEncoder.encode(user.getEmail(), "UTF-8"), 7);
            } else {
                cacheKit.set(MemConstans.USER_CURRENT_LOGINTIME + user.getId(), current.toString(), MemConstans.USER_TIME);
                WebUtils.setCookie(response, CommonConstants.USER_SINGEL_ID, uuid, 1);
                WebUtils.setCookie(response, CommonConstants.USER_SINGEL_NAME, URLEncoder.encode(user.getEmail(), "UTF-8"), 1);
            }
            // 登录时把cookie中的购物车信息加到数据库中
            shopcartService.addTempShopCart(request, response, user.getId());
            // 添加登录记录
            UserLoginLog userLoginLog = new UserLoginLog();
            userLoginLog.setLoginIp(WebUtils.getIpAddr(request));
            userLoginLog.setUserId(user.getId());
            userLoginLog.setLoginTime(new Date());
            String userAgent = UserAgentUtil.getUserAgent(request);
            if (StringUtils.isNotEmpty(userAgent)) {
                userLoginLog.setUserAgent(userAgent.split(";")[0]);// 浏览器
                userLoginLog.setOsname(userAgent.split(";")[1]);// 操作系统
            }
            userLoginLogService.addUserLoginLog(userLoginLog);
            // 登陆赠送积分
            userIntegralService.addUserIntegral(IntegralKeyword.login.toString(), user.getId(), 0L, 0L, "");
            // 添加在线记录状态
            LoginOnline loginOnline = new LoginOnline();
            loginOnline.setUserId(user.getId());
            loginOnline.setLoginsid(uuid);
            loginOnline.setCreateTime(new Date());
            loginOnline.setType(ReqChanle.WEB.toString());
            loginOnlineService.addLoginOnline(loginOnline);
            //存入redis 用户登录
            StatisticsUtil.setUniqueCount(MemConstans.MEMFIX + StatisticsQueryType.LOGIN_NUM.toString(), sdf.format(new Date()), user.getId());
            // 根据 注册来源 如果是 学员卡 赠送 用户 ，第一次登录时 修改 学员卡 使用状态为 已使用
            if ((UserExpandFrom.userCardFrom.toString()).equals(userExpandDto.getRegisterFrom())) {
                // 修改
                cardCodeService.updateCardStatusByCode(user.getEmail());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 用户定时操作 更新用户地址
     */
    public void updateUserAddress() {
        List<UserLoginLog> userLoginLogList = userLoginLogService.getUserLoginLogList();
        for (UserLoginLog userLoginLog : userLoginLogList) {
            try {
                // 循环处理用户登陆信息
                userLoginLogService.updateUserLoginLog(userLoginLog);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 查询学员邮箱是否存在
     *
     * @param emails
     * @return Integer
     */
    public List<User> getUserIsExsitByEmail(List<String> emails) {
        return userDao.getUserIsExsitByEmail(emails);
    }

    /**
     * 批量添加用户
     *
     * @param users
     */
    public void addUsers(List<User> users) {
        for (User user : users) {
            generatePassword(user);// 给密码加密
        }
        userDao.addUsers(users);
    }

    /**
     * 验证手机唯一
     *
     * @param user
     * @return
     * @throws Exception
     */
    public Integer getUserByMobile(User user) throws Exception {
        return userDao.getUserByMobile(user);
    }

    // 从excel表中导出的数据处理
    public Map<String, Object> updateImportExcel(MultipartFile myFile, String groupIds, int level, int sysGroupId) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        List<UserJw> addUserList = new ArrayList<UserJw>();
        String retrunmsg = "";
        List<Object> information = new ArrayList<Object>();
        int maxcell = 0;
        int count = 0;
        HSSFWorkbook wookbook = new HSSFWorkbook(myFile.getInputStream());
        HSSFSheet sheet = wookbook.getSheet("Sheet1");
        //
        boolean cellOne = false;
        boolean cellTwo = false;
        boolean cellThree = false;
        String errMsg = "";
        List<UserGroupMiddle> userGroupMiddleList = new ArrayList<UserGroupMiddle>();
        int rows = sheet.getLastRowNum();// 指的行数，一共有多少行+
        for (int i = 1; i <= rows + 1; i++) {
            cellOne = false;
            cellTwo = false;
            cellThree = false;
            // 读取左上端单元格
            HSSFRow row = sheet.getRow(i);
            // 行不为空
            if (row != null) {
                User user = new User();

                // 获取到Excel文件中的所有的列
                maxcell = row.getLastCellNum();
                // **读取cell**
                errMsg = "";
                String mobile = getCellValue(row.getCell((short) 0));// 获得手机号
                if (ObjectUtils.isNull(mobile) || mobile.equals("")) {
                    errMsg += "手机号为空";
                }
                // 在手机不为空的情况下，验证手机格式是否正确
                if (ObjectUtils.isNotNull(mobile) && StringUtils.isNotEmpty(mobile) && !mobile.matches("^(13[0-9]|15[012356789]|18[0-9]|14[57]|17[012356789])[0-9]{8}$")) {
                    errMsg += errMsg.equals("") ? "" : ",";
                    errMsg += "手机号:" + mobile + "格式不正确";
                }
                user.setMobile(mobile);
                int ismobile = this.getUserByMobile(user);
                // 验证手机唯一
                if (ismobile != 0) {
                    errMsg += errMsg.equals("") ? "" : ",";
                    errMsg += "手机号：" + user.getMobile() + "已被占用";
                } else {
                    cellOne = true;
                }
                String email = getCellValue(row.getCell((short) 1));// 邮箱
                if (ObjectUtils.isNull(email) || email.equals("")) {
                    continue;
                }
                user.setEmail(email);
                user.setVerifyType("email");
                List<User> list = this.getUserList(user);
                // 验证邮箱唯一,邮箱是否已经注册
                if (ObjectUtils.isNotNull(list)) {
                    errMsg += errMsg.equals("") ? "" : ",";
                    errMsg += "邮箱：" + user.getEmail() + "已被占用";
                } else {
                    cellTwo = true;
                }

                String nickName = getCellValue(row.getCell((short) 2));// 姓名
                if (ObjectUtils.isNull(nickName) || nickName.equals("")) {
                    errMsg += errMsg.equals("") ? "" : ",";
                    errMsg += "姓名为空";
                }
                user.setVerifyType("nickname");
                user.setNickname(nickName);
                list = this.getUserList(user);
                // 验证名称是否重复
                if (ObjectUtils.isNotNull(list)) {
                    errMsg += errMsg.equals("") ? "" : ",";
                    errMsg += "姓名：" + user.getNickname() + "已被占用";
                } else {
                    cellThree = true;
                }
                String pwd = getCellValue(row.getCell((short) 3));// 获取密码
                if (ObjectUtils.isNull(pwd) || pwd.equals("")) {
                    pwd = "111111";
                }
                if (cellOne && cellTwo && cellThree) {
                    User users = new User();
                    users.setEmail(email);
                    users.setMobile(mobile);// 默认手机号
                    users.setNickname(nickName);
                    users.setLevel(level);
                    users.setSysGroupId(sysGroupId);
                    if (StringUtils.isEmpty(pwd)) {
                        users.setPassword("111111");// 设置默认密码
                    } else {
                        users.setPassword(pwd);// 设置默认密码
                    }
                    users.setRegisterFrom(UserExpandFrom.adminFrom.toString());// 后台批量生成账号来源
                    addUser(users);
                    // 注册送积分
                    userIntegralService.addUserIntegral(IntegralKeyword.register.toString(), users.getId(), 0L, 0L, "");
                    user = users;
                    // 可以添加多个组
                    if (StringUtils.isNotEmpty(groupIds)) {
                        // 学员信息分组
                        String[] groupIdArray = groupIds.split(",");
                        for (int l = 0; l < groupIdArray.length; l++) {
                            UserGroupMiddle _userGroupMiddle = new UserGroupMiddle();
                            _userGroupMiddle.setUserId(user.getId());
                            _userGroupMiddle.setGroupId(Long.parseLong(groupIdArray[l]));
                            userGroupMiddleList.add(_userGroupMiddle);
                        }
                    }
                    count++;
                    UserJw userJw = new UserJw();
                    userJw.setPhoneNumber(user.getMobile());
                    userJw.setStudentName(user.getNickname());
                    userJw.setNetSchoolUserId(user.getId());
                    userJw.setEmail(user.getEmail());
                    addUserList.add(userJw);
                    retrunmsg = "成功";// 备注信息
                    user.setRowsNumber(i + 1);// 行号
                    user.setNote(retrunmsg);// 设置备注信息
                    information.add(user);// 设置属性

                } else {
                    mobile = getCellValue(row.getCell((short) 0));// 电话
                    email = getCellValue(row.getCell((short) 1));// 邮箱
                    nickName = getCellValue(row.getCell((short) 2));// 姓名
                    user.setMobile(mobile);
                    user.setEmail(email);
                    user.setNickname(nickName);
                    pwd = getCellValue(row.getCell((short) 3));// 获取密码
                    if (ObjectUtils.isNull(pwd) || pwd.equals("")) {
                        pwd = "111111";
                        user.setPassword(pwd);
                    }
                    // 添加记录错误数据
                    retrunmsg = "操作失败情况:" + errMsg + "<br/>";
                    user.setNote(retrunmsg);
                    user.setRowsNumber(i + 1);// 行号
                    information.add(user);
                }
            }
        }
        // 添加入学员组
        if (userGroupMiddleList.size() > 0) {
            userGroupMiddleService.addUserGroupMiddle(userGroupMiddleList);
        }
        String record = "总共操作条数:" + rows + ",成功:" + count + "条" + "失败:" + (rows - count) + "条<br/>";
        if (retrunmsg != "") {// 如果retrunmsg不是空的（有操作失败记录）
            retrunmsg = "操作失败情况:<br/>" + retrunmsg;
        } else {
            retrunmsg = record;
        }
        map.put("retrunmsg", retrunmsg);
        map.put("information", information);
        map.put("record", record);
        return map;
    }

    // 订单流水信息
    public void addOrderMsg(Long userId, String courseId) throws Exception {
        // 拼装参数
        Map<String, String> sourceMap = new HashMap<String, String>();
        sourceMap.put("type", "1");// 下单类型
        sourceMap.put("couponcode", "");// 优惠卷编码
        sourceMap.put("userid", userId + "");
        sourceMap.put("reqchanle", ReqChanle.WEB.toString());
        sourceMap.put("reqIp", "");
        sourceMap.put("courseId", courseId);
        sourceMap.put("payType", "FREE");
        trxorderService.addFreeTrxorder(sourceMap);
    }

    /**
     * 获得Hsscell内容
     *
     * @param cell
     * @return
     */
    public String getCellValue(HSSFCell cell) {
        String value = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_FORMULA:
                    break;
                case HSSFCell.CELL_TYPE_NUMERIC:
                    DecimalFormat df = new DecimalFormat("0");
                    value = df.format(cell.getNumericCellValue());
                    break;
                case HSSFCell.CELL_TYPE_STRING:
                    value = cell.getStringCellValue().trim();
                    break;
                default:
                    value = "";
                    break;
            }
        }
        return value.trim();
    }

    /**
     * 获得网站注册人数
     *
     * @return
     */
    public Integer getWebsiteRegNumber() {
        return userDao.getWebsiteRegNumber();
    }

    /**
     * 添加用户操作记录
     *
     * @param userId      用户的id
     * @param type        业务类型
     * @param optuser     操作者id
     * @param optusername 操作者名字
     * @param bizId       业务id
     * @param desc        描述
     */
    public void addUserOptRecord(Long userId, String type, Long optuser, String optusername, Long bizId, String desc) {

        UserOptRecord userOptRecord = new UserOptRecord();
        userOptRecord.setUserId(userId);// 添加用户id
        userOptRecord.setType(type);// 业务类型
        userOptRecord.setBizId(bizId);// 业务id
        userOptRecord.setOptuser(optuser);// 操作者id
        userOptRecord.setOptusername(optusername);// 操作者名字
        userOptRecord.setDescription(desc);// 描述
        userOptRecord.setCreateTime(new Date());
        userDao.addUserOptRecord(userOptRecord);
    }

    @Override
    public UserExpandDto queryUserExpand(long userId) {
        return userExpandDao.queryUserByid(userId);
    }

    /**
     * 获取后台赠送操作列表
     *
     * @param userOptRecord
     * @param page
     * @return
     */
    public List<UserOptRecord> getUserOptRecordListByCondition(UserOptRecord userOptRecord, PageEntity page) {
        return userDao.getUserOptRecordListByCondition(userOptRecord, page);
    }

    /**
     * 获取组的学员列表
     */
    public List<User> getUserListByGroupId(User user, PageEntity page) {
        return this.userDao.getUserListByGroupId(user, page);
    }

    @Override
    public List<UserJw> getUserJwFromJsonArray(String userDate) {

        List<UserJw> userJwList = new ArrayList<UserJw>();
        JsonParser jsonParser = new JsonParser();
        System.out.println(userDate);
        JsonObject jsonObject = jsonParser.parse(userDate).getAsJsonObject();
        String data = jsonObject.get("list").toString();
        JsonArray jsonArray = jsonParser.parse(data).getAsJsonArray();
        Iterator<?> it = jsonArray.iterator();
        it = jsonArray.iterator();
        // 数组转换
        while (it.hasNext()) {
            UserJw userJw = new UserJw();
            JsonElement e = (JsonElement) it.next();
            if (e.getAsJsonObject().get("code").getAsString().equals("1")) {
                String jwStudentId = e.getAsJsonObject().get("jwStudentId").getAsString();
                String netSchoolUserId = e.getAsJsonObject().get("netSchoolUserId").getAsString();
                userJw.setJwStudentId(Long.parseLong(jwStudentId));
                userJw.setNetSchoolUserId(Long.parseLong(netSchoolUserId));
                userJwList.add(userJw);
            }
        }
        return userJwList;

    }

    @Override
    public void batchUpdateUser(List<UserJw> userJwList) {
        // TODO Auto-generated method stub
        userDao.batchUpdateUserList(userJwList);
    }

    /**
     * 查询部门下的员工
     */
    public List<User> queryGroupByid(User user, PageEntity page) {
        return this.userDao.queryGroupByid(user, page);
    }

    /**
     * 根据id获取员工
     */
    public List<User> queryGroupUserIds(String ids) {
        return this.userDao.queryGroupUserIds(ids);
    }

    @Override
    public User getUserByNickName(String nickname) {
        // TODO Auto-generated method stub
        return this.userDao.getUserByNickName(nickname);
    }

    @Override
    public void delUserBatch(String userIds) {
        userIds = userIds.substring(0, userIds.length() - 1);
        cacheKit.remove(MemConstans.HOME_INDEX_ARTILE);
        cacheKit.remove(MemConstans.HOME_INDEX_ARTILE_TOP);
        userDao.delUserBatch(userIds);

    }

    @Override
    public User getUserByName(String name) {
        // TODO Auto-generated method stub
        return userDao.getUserByName(name);
    }

    @Override
    public User getUserByLoginEmail(String email) {
        // TODO Auto-generated method stub
        return userDao.getUserByLoginEmail(email);
    }

    @Override
    public User getUserByLoginMobile(String mobile) {
        // TODO Auto-generated method stub
        return userDao.getUserByLoginMobile(mobile);
    }

    /**
     * 获取用户来源统计数据
     *
     * @return
     */
    public UserFromStatistics getUserFromStatistics() {
        return userDao.getUserFromStatistics();
    }

    @Override
    public List<User> queryUserListByIds(String ids) {
        return this.userDao.queryUserListByIds(ids);
    }

    @Override
    public List<User> getUserListByGroup(User user, PageEntity page) {
        // TODO Auto-generated method stub
        return this.userDao.getUserListByGroup(user, page);
    }

    @Override
    public Map<String, Object> getRegisterFromMsg(String month, String year) {
        if (month != null && month != "") {
            getRegisterFromByMonth(month, year);// 按月查
        } else if (year != null) {
            getRegisterFromByYear(year);//按年查
        }
        return userMsg;
    }

    /**
     * 按月查询网站统计
     *
     * @param month
     * @return
     */
    public void getRegisterFromByMonth(String month, String year) {
        List<UserFromStatistics> registerFromList = userDao.getRegisterFromByMonth(month, year);
        if (ObjectUtils.isNotNull(registerFromList)) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.valueOf(year), Integer.valueOf(month), 0);
            int dateNum = registerFromList.size() - calendar.get(Calendar.DATE);
            for (int i = 0; i < dateNum; i++) {
                registerFromList.remove(i);
            }
        }
        userMsg.put("registerFromList", registerFromList);
    }

    /**
     * 按年查询网站统计
     *
     * @param year
     * @return
     */
    public void getRegisterFromByYear(String year) {
        List<UserFromStatistics> registerFromList = userDao.getRegisterFromByYear(year);
        userMsg.put("registerFromList", registerFromList);
    }

    /**
     * 个人中心修改用户信息
     */
    public void updateWebUser(User user) {
        this.userDao.updateWebUser(user);
    }

}
