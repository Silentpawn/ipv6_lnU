package com.ruoyi.web.controller.system;

import java.util.Iterator;
import java.util.List;

import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ICbtCompTeamService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.CbtTeamUser;
import com.ruoyi.system.service.ICbtTeamUserService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.servlet.ModelAndView;

import static com.ruoyi.framework.util.ShiroUtils.getSysUser;

/**
 * 团队成员Controller
 *
 * @author wsl
 * @date 2020-06-16
 */
@Controller
@RequestMapping("/system/comp_team_mem")
public class CbtTeamUserController extends BaseController {
    private String prefix = "system/comp_team_mem";

    @Autowired
    private ICbtTeamUserService cbtTeamUserService;

    @RequiresPermissions("system:comp_team_mem:view")
    @GetMapping()
    public String comp_team_mem() {
        return prefix + "/comp_team_mem";
    }


    /**
     * 查询团队成员列表
     */
    @RequiresPermissions("system:comp_team_mem:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CbtTeamUser cbtTeamUser) {
        startPage();
        List<CbtTeamUser> list = cbtTeamUserService.selectCbtTeamUserList(cbtTeamUser);
        return getDataTable(list);
    }

    /**
     * 导出团队成员列表
     */
    @RequiresPermissions("system:comp_team_mem:export")
    @Log(title = "团队成员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CbtTeamUser cbtTeamUser) {
        List<CbtTeamUser> list = cbtTeamUserService.selectCbtTeamUserList(cbtTeamUser);
        ExcelUtil<CbtTeamUser> util = new ExcelUtil<CbtTeamUser>(CbtTeamUser.class);
        return util.exportExcel(list, "comp_team_mem");
    }

    /**
     * 新增团队成员
     */
    @GetMapping("/add/{teamId}")
    public String add(@PathVariable("teamId") Long teamId, ModelMap mmap) {
        mmap.put("teamId", teamId);
        return prefix + "/add";
    }

    /**
     * 新增保存团队成员
     */
    @Autowired
    private ICbtCompTeamService cbtCompTeamService;

    @RequiresPermissions("system:comp_team_mem:add")
    @Log(title = "团队成员", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CbtTeamUser cbtTeamUser) {
        Long userId = cbtTeamUser.getUserId();
        Long teamId = cbtTeamUser.getTeamId();
        Long compId = cbtCompTeamService.selectCbtCompTeamById(teamId).getCompId();
        Long localUserId = getSysUser().getUserId();
        boolean isInTeam = false;
        CbtTeamUser localTeamUser = new CbtTeamUser();
        localTeamUser.setTeamId(teamId);
        List<CbtTeamUser> localTeamUserList = cbtTeamUserService.selectCbtTeamUserList(localTeamUser);
        for (CbtTeamUser c:localTeamUserList) {
            if(c.getUserId().equals(localUserId)){
                isInTeam = true;
            }
        }
        if(isInTeam){
            List<CbtTeamUser> selectedUserId = cbtTeamUserService.selectMemByCompId(compId);
            for (CbtTeamUser s: selectedUserId) {
                if(s.getUserId().equals(userId)){
                    return error("该同学已参加此竞赛！");
                }
            }
            return toAjax(cbtTeamUserService.insertCbtTeamUser(cbtTeamUser));
        }else{
            return error("您不在该团队中！");
        }
    }

    /**
     * 修改团队成员
     */
    @GetMapping("/edit/{tuId}")
    public String edit(@PathVariable("tuId") Long tuId, ModelMap mmap) {
        CbtTeamUser cbtTeamUser = cbtTeamUserService.selectCbtTeamUserById(tuId);
        mmap.put("cbtTeamUser", cbtTeamUser);
        return prefix + "/edit";
    }

    /**
     * 修改保存团队成员
     */
    @RequiresPermissions("system:comp_team_mem:edit")
    @Log(title = "团队成员", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CbtTeamUser cbtTeamUser) {
        return toAjax(cbtTeamUserService.updateCbtTeamUser(cbtTeamUser));
    }

    @RequiresPermissions("system:comp_team_mem:exitTeam")
    @Log(title = "团队成员", businessType = BusinessType.DELETE)
    @PostMapping("/exitTeam/{teamId}")
    @ResponseBody
    public AjaxResult exitTeam(@PathVariable("teamId") Long teamId) {
        Long userId = getSysUser().getUserId();
        CbtTeamUser teamUser = new CbtTeamUser();
        teamUser.setUserId(userId);
        teamUser.setTeamId(teamId);
        List<CbtTeamUser> list = cbtTeamUserService.selectCbtTeamUserList(teamUser);
        if(list.size()>0)
        {
            String ids = list.get(0).getTuId().toString();
            for (int i = 1; i < list.size(); i++) {
                ids = "," + list.get(i).getTuId().toString();
            }
            return toAjax(cbtTeamUserService.deleteCbtTeamUserByIds(ids));
        }else{
            return error("您未参加此团队！");
        }

    }

    /**
     * 删除团队成员
     */
    @RequiresPermissions("system:comp_team_mem:remove")
    @Log(title = "团队成员", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(cbtTeamUserService.deleteCbtTeamUserByIds(ids));
    }
}
