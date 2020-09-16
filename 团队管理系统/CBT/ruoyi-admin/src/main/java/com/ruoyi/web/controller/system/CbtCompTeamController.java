package com.ruoyi.web.controller.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import static com.ruoyi.framework.util.ShiroUtils.getSysUser;

/**
 * 竞赛团队Controller
 *
 * @author wsl
 * @date 2020-06-16
 */
@Controller
@RequestMapping("/system/comp_team")
public class CbtCompTeamController extends BaseController {
    private String prefix = "system/comp_team";

    @Autowired
    private ICbtCompTeamService cbtCompTeamService;

    @RequiresPermissions("system:comp_team:view")
    @GetMapping()
    public String comp_team() {
        return prefix + "/comp_team";
    }

    /**
     * 查询竞赛团队列表byUser
     */

    @RequiresPermissions("system:comp_team:listbyuser")
    @GetMapping("/list1")
    public String list11() {

        return prefix + "/list1";
    }


    @RequiresPermissions("system:comp_team:listbyuser")
    @PostMapping("/list1")
    @ResponseBody
    public TableDataInfo list1() {
        Long userId = getSysUser().getUserId();
        CbtTeamUser teamUser = new CbtTeamUser();
        teamUser.setUserId(userId);
        List<CbtCompTeam> list1 = new ArrayList<>();
        List<CbtTeamUser> userList = cbtTeamUserService.selectCbtTeamUserList(teamUser);
        if(userList.size() > 0){
            String ids = userList.get(0).getTeamId().toString();
            for (int i = 1; i <userList.size(); i++) {
                ids = ids + "," +userList.get(i).getTeamId().toString();
            }
            startPage();
            list1 = cbtCompTeamService.selectCbtCompTeamByUserIds(ids);
        }
        return getDataTable(list1);
    }

    /**
     * 查询竞赛团队列表
     */
    @RequiresPermissions("system:comp_team:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CbtCompTeam cbtCompTeam) {
        startPage();
        List<CbtCompTeam> list = cbtCompTeamService.selectCbtCompTeamList(cbtCompTeam);
        return getDataTable(list);
    }

    /**
     * 导出竞赛团队列表
     */
    @RequiresPermissions("system:comp_team:export")
    @Log(title = "竞赛团队", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CbtCompTeam cbtCompTeam) {
        List<CbtCompTeam> list = cbtCompTeamService.selectCbtCompTeamList(cbtCompTeam);
        ExcelUtil<CbtCompTeam> util = new ExcelUtil<CbtCompTeam>(CbtCompTeam.class);
        return util.exportExcel(list, "comp_team");
    }

    /**
     * 新增竞赛团队
     */
    @RequiresPermissions("system:comp_team:add")
    @GetMapping("/addByCompId/{compId}")
    public String addByCompId(@PathVariable("compId") Long compId, ModelMap mmap) {
        mmap.put("compId", compId);
        return prefix + "/add";
    }

    /**
     * 新增竞赛团队
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @Autowired
    private ICbtCompInfoService cbtCompInfoService;

    /**
     * 新增保存竞赛团队
     */
    ISysRoleService iSysRoleService;
    @Autowired
    private ISysUserService userService;
    @RequiresPermissions("system:comp_team:add")
    @Log(title = "竞赛团队", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CbtCompTeam cbtCompTeam) {
        Long userId = getSysUser().getUserId();
        SysUser user = userService.selectUserById(userId);
        CbtCompInfo cbtCompInfo = cbtCompInfoService.selectCbtCompInfoById(cbtCompTeam.getCompId());
        Long compId = cbtCompInfo.getCompId();
        Date nowDate = new Date();
        cbtCompTeam.setCreateBy(user.getLoginName());
        cbtCompTeam.setCreateTime(nowDate);
        List<SysUserRole> userRoles = userService.selectUserRoleByUserId(userId);
        boolean isAdmin = false;
        for (SysUserRole l:userRoles) {
            if(l.getRoleId().equals(2L)){
                isAdmin = true;
            }
        }
        if (nowDate.after(cbtCompInfo.getStartTime()) && nowDate.before(cbtCompInfo.getEndTime())) {
            if(isAdmin){
                AjaxResult result = toAjax(cbtCompTeamService.insertCbtCompTeam(cbtCompTeam));
                return result;
            }
            else{
                List<CbtTeamUser> selectedUserId = cbtTeamUserService.selectMemByCompId(compId);
                for (CbtTeamUser s: selectedUserId) {
                    if(s.getUserId().equals(userId)){
                        return error("该同学已参加此竞赛！");
                    }
                }
                AjaxResult result = toAjax(cbtCompTeamService.insertCbtCompTeam(cbtCompTeam));
                CbtTeamUser teamUser = new CbtTeamUser();
                teamUser.setTeamId(cbtCompTeam.getTeamId());
                teamUser.setUserId(user.getUserId());
                cbtTeamUserService.insertCbtTeamUser(teamUser);
                return result;
            }
        } else {
            return error("不在竞赛组队时间内！");
        }
    }

    /**
     * 查看竞赛团队成员
     */

    @GetMapping("/listTeamUser/{teamId}")
    public String edit(@PathVariable("teamId") Long teamId, ModelMap mmap) {
        mmap.put("teamId", teamId);
        return "system/comp_team_mem/listmem";
    }

    @GetMapping("/listTeamUserByStu/{teamId}")
    public String listTeamUserByStu(@PathVariable("teamId") Long teamId, ModelMap mmap) {
        mmap.put("teamId", teamId);
        return "system/comp_team_mem/listTeamUserByStu";
    }


    /**
     * 删除竞赛团队
     */
    @Autowired
    private ICbtTeamUserService cbtTeamUserService;
    @RequiresPermissions("system:comp_team:remove")
    @Log(title = "竞赛团队", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        int count = cbtTeamUserService.countMemById(Long.parseLong(ids));
        if (count == 0) {
            return toAjax(cbtCompTeamService.deleteCbtCompTeamByIds(ids));
        } else {
            return error("删除团队前，请先清空团队成员！");
        }
    }
}
