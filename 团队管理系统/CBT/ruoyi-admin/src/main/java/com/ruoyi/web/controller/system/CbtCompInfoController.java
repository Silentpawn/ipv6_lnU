package com.ruoyi.web.controller.system;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.ruoyi.system.cbt.Comp;
import com.ruoyi.system.cbt.Pso;
import com.ruoyi.system.cbt.Student;
import com.ruoyi.system.domain.CbtCompTeam;
import com.ruoyi.system.domain.CbtTeamUser;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ICbtCompTeamService;
import com.ruoyi.system.service.ICbtTeamUserService;
import com.ruoyi.system.service.ISysUserService;
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
import com.ruoyi.system.domain.CbtCompInfo;
import com.ruoyi.system.service.ICbtCompInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import static com.ruoyi.framework.util.ShiroUtils.getSysUser;


/**
 * 竞赛属性Controller
 *
 * @author wsl
 * @date 2020-06-16
 */
@Controller
@RequestMapping("/system/comp_info")
public class CbtCompInfoController extends BaseController {
    private String prefix = "system/comp_info";

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ICbtCompInfoService cbtCompInfoService;

    @RequiresPermissions("system:comp_info:view")
    @GetMapping()
    public String comp_info() {
        return prefix + "/comp_info";
    }

    /**
     * 查询竞赛属性列表
     */
    @RequiresPermissions("system:comp_info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CbtCompInfo cbtCompInfo) {
        startPage();
        List<CbtCompInfo> list = cbtCompInfoService.selectCbtCompInfoList(cbtCompInfo);
        return getDataTable(list);
    }

    /**
     * 导出竞赛属性列表
     */
    @RequiresPermissions("system:comp_info:export")
    @Log(title = "竞赛属性", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CbtCompInfo cbtCompInfo) {
        List<CbtCompInfo> list = cbtCompInfoService.selectCbtCompInfoList(cbtCompInfo);
        ExcelUtil<CbtCompInfo> util = new ExcelUtil<CbtCompInfo>(CbtCompInfo.class);
        return util.exportExcel(list, "comp_info");
    }

    /**
     * 新增竞赛属性
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存竞赛属性
     */
    @RequiresPermissions("system:comp_info:add")
    @Log(title = "竞赛属性", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CbtCompInfo cbtCompInfo) {
        return toAjax(cbtCompInfoService.insertCbtCompInfo(cbtCompInfo));
    }

    /**
     * 修改竞赛属性
     */
    @GetMapping("/edit/{compId}")
    public String edit(@PathVariable("compId") Long compId, ModelMap mmap) {
        CbtCompInfo cbtCompInfo = cbtCompInfoService.selectCbtCompInfoById(compId);
        mmap.put("cbtCompInfo", cbtCompInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存竞赛属性
     */
    @RequiresPermissions("system:comp_info:edit")
    @Log(title = "竞赛属性", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CbtCompInfo cbtCompInfo) {
        return toAjax(cbtCompInfoService.updateCbtCompInfo(cbtCompInfo));
    }

    /**
     * 删除竞赛属性
     */
    @RequiresPermissions("system:comp_info:remove")
    @Log(title = "竞赛属性", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(cbtCompInfoService.deleteCbtCompInfoByIds(ids));
    }


    @Autowired
    private ICbtCompTeamService cbtCompTeamService;

    @Autowired
    private ICbtTeamUserService cbtTeamUserService;

    /**
     * 竞赛团队匹配
     *
     * @return
     */
    @RequiresPermissions("system:comp_info:matching")
    @GetMapping("/match/{compId}")
    @ResponseBody
    public AjaxResult matching(@PathVariable("compId") Long compId) {
        CbtCompInfo cbtCompInfo = cbtCompInfoService.selectCbtCompInfoById(compId);
        Date nowDate = new Date();
        if (nowDate.after(cbtCompInfo.getStartTime()) && nowDate.before(cbtCompInfo.getEndTime())) {
            Long createId = getSysUser().getUserId();
            SysUser user = userService.selectUserById(createId);
            List<SysUser> list = userService.selectUserListToMatch();
            List<Student> studentList = new ArrayList<>();
            List<CbtTeamUser> selectedUserId = cbtTeamUserService.selectMemByCompId(compId);
            Iterator<SysUser> userIterator = list.iterator();
            while(userIterator.hasNext()){
                Long userId = userIterator.next().getUserId();
                Iterator<CbtTeamUser> sUserIterator = selectedUserId.iterator();
                while(sUserIterator.hasNext()){
                    Long sUserId = sUserIterator.next().getUserId();
                    if(userId.equals(sUserId)){
                        userIterator.remove();
                        sUserIterator.remove();
                    }
                }
            }
            if(list.size() < cbtCompInfo.getMemNum()){
                return error("没有足够的学生进行匹配");
            }
            for (SysUser s : list) {
                Student student = new Student(s.getUserId().longValue(), s.getTa().doubleValue(),
                        s.getPa().doubleValue(), s.getCe().doubleValue(), s.getLa().doubleValue(), s.getCa().doubleValue(),
                        s.getAe().doubleValue());
                studentList.add(student);
            }
            Comp comp = new Comp(cbtCompInfo);
            Pso pso = new Pso(1000, 20, studentList, comp);
            int x[] = pso.getXgbest();
            CbtCompTeam cbtCompTeam = new CbtCompTeam();
            cbtCompTeam.setCompId(cbtCompInfo.getCompId());
            cbtCompTeam.setStatus(new Long(1));
            cbtCompTeam.setCreateBy(user.getLoginName());
            cbtCompTeam.setCreateTime(nowDate);
            AjaxResult ajaxResult = toAjax(cbtCompTeamService.insertCbtCompTeam(cbtCompTeam));
            for (int i = 0; i < cbtCompInfo.getMemNum().intValue(); i++) {
                CbtTeamUser teamUser = new CbtTeamUser();
                teamUser.setTeamId(new Long(cbtCompTeam.getTeamId()));
                teamUser.setUserId(new Long(studentList.get(x[i]).getStuId()));
                cbtTeamUserService.insertCbtTeamUser(teamUser);
            }
            return ajaxResult;
        } else {
            return error("不在竞赛组队时间内！");
        }
    }
}
