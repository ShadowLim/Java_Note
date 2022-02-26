package com.lbj.note.service;


import cn.hutool.core.util.StrUtil;
import com.lbj.note.dao.NoteDao;
import com.lbj.note.po.Note;
import com.lbj.note.util.Page;
import com.lbj.note.vo.NoteVo;
import com.lbj.note.vo.ResultInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NoteService {
    private NoteDao noteDao = new NoteDao();

    /**
     * 添加或修改云记
         1. 参数的非空判断
         2. 设置回显对象 Note对象
         3. 调用Dao层，添加云记记录，返回受影响的行数
         4. 判断受影响的行数
     * @param typeId
     * @param title
     * @param content
     * @return
     */
    public ResultInfo<Note> addOrUpdate(String typeId, String title, String content,
                                        String noteId, String lon, String lat) {
        ResultInfo<Note> resultInfo = new ResultInfo<>();

        // 1. 参数的非空判断
        if (StrUtil.isBlank(typeId)) {
            resultInfo.setCode(0);
            resultInfo.setMsg("请选择云记类型！");
            return resultInfo;
        }
        if (StrUtil.isBlank(title)) {
            resultInfo.setCode(0);
            resultInfo.setMsg("云记标题不能为空！");
            return resultInfo;
        }
        if (StrUtil.isBlank(content)) {
            resultInfo.setCode(0);
            resultInfo.setMsg("云记内容不能为空！");
            return resultInfo;
        }

        // 设置经纬度的默认值(北京:116.404, 39.915)
        if (lon == null || lat == null) {
            lon = "116.404";
            lat = "39.915";
        }

        // 2. 设置回显对象
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        note.setTypeId(Integer.parseInt(typeId));
        note.setLon(Float.parseFloat(lon));
        note.setLat(Float.parseFloat(lat));

        // 判断云记id是否为空
        if (!StrUtil.isBlank(noteId)) {
            note.setNoteId(Integer.parseInt(noteId));
        }

        resultInfo.setResult(note);

        // 3. 调用Dao层，添加云记记录，返回受影响的行数
        int row = noteDao.addOrUpdate(note);
        // 4. 判断受影响的行数
        if (row > 0) {
            resultInfo.setCode(1);
        } else {
            resultInfo.setCode(0);
            resultInfo.setResult(note);  // 回显
            resultInfo.setMsg("更新失败！");
        }
        return resultInfo;
    }

    /**
     * 分页查询云记列表
         设置分页参数的默认值
         1. 参数的非空校验 （如果参数不为空，则设置参数）
         2. 查询当前登录用户的云记数量，返回总记录数 （long类型）
         3. 判断总记录数是否大于0
         4. 如果总记录数大于0，调用Page类的带参构造，得到其他分页参数的值，返回Page对象
         5. 查询当前登录用户下当前页的数据列表，返回note集合
         6. 将note集合设置到page对象中
         7. 返回Page对象
     * @param pageNumStr
     * @param pageSizeStr
     * @param userId
     * @param title
     * @param typeId
     * @return
     */
    public Page<Note> findNoteListByPage(String pageNumStr, String pageSizeStr, Integer userId,
                                         String title, String date, String typeId) {
        // 设置分页参数的默认值
        Integer pageNum = 1; // 默认当前页是第一页
        Integer pageSize = 5; // 默认每页显示5条数据

        // 1. 参数的非空校验 （如果参数不为空，则设置参数）
        if (!StrUtil.isBlank(pageNumStr)) {
            // 设置当前页
            pageNum = Integer.parseInt(pageNumStr);
        }
        if (!StrUtil.isBlank(pageSizeStr)) {
            // 设置每页显示的数量
            pageSize = Integer.parseInt(pageSizeStr);
        }

        // 2. 查询当前登录用户的云记数量，返回总记录数 （long类型）
        long count = noteDao.findNoteCount(userId, title, date, typeId);

        // 3. 判断总记录数是否大于0
        if (count < 1) {
            return null;
        }
        // 4. 如果总记录数大于0，调用Page类的带参构造，得到其他分页参数的值，返回Page对象
        Page<Note> page = new Page<>(pageNum, pageSize, count);

        // 得到数据库中分页查询开始的下标
        Integer index = (pageNum - 1) * pageSize;

        // 5. 查询当前登录用户下当前页的数据列表，返回note集合
        List<Note> noteList = noteDao.findNoteListByPage(userId, index, pageSize, title, date, typeId);

        // 6. 将note集合设置到page对象中
        page.setDataList(noteList);

        // 7. 返回Page对象
        return page;
    }

    /**
     * 通过日期分组查询当前登录用户下的云记数量
     * @param userId
     * @return
     */
    public List<NoteVo> findNoteCountByDate(Integer userId) {
        return noteDao.findNoteCountByDate(userId);
    }

    /**
     * 通过类型分组查询当前登录用户下的云记数量
     * @param userId
     * @return
     */
    public List<NoteVo> findNoteCountByType(Integer userId) {
        return noteDao.findNoteCountByType(userId);
    }


    /**
     * 查询云记详情
         1. 参数的非空判断
         2. 调用Dao层的查询，通过noteId查询note对象
         3. 返回note对象
     * @param noteId
     * @return
     */
    public Note findNoteById(String noteId) {
        // 1. 参数的非空判断
        if (StrUtil.isBlank(noteId)) {
            return null;
        }
        // 2. 调用Dao层的查询，通过noteId查询note对象
        Note note = noteDao.findNoteById(noteId);
        // 3. 返回note对象
        return note;
    }

    /**
     * 删除云记
         1. 判断参数
         2. 调用Dao层的更新方法，返回受影响的行数
         3. 判断受影响的行数是否大于0
            如果大于0，返回1；否则返回0
     * @param noteId
     * @return
     */
    public Integer deleteNote(String noteId) {
        // 1. 判断参数
        if (StrUtil.isBlank(noteId)) {
            return 0;
        }
        // 2. 调用Dao层的更新方法，返回受影响的行数
        int row = noteDao.deleteNoteById(noteId);
        // 3. 判断受影响的行数是否大于0
        if (row > 0) { //如果大于0，返回1；否则返回0
            return 1;
        }
        return 0;
    }


    /**
     * 通过月份查询对应的云记数量
     * @param userId
     * @return
     */
    public ResultInfo<Map<String, Object>> queryNoteCountByMonth(Integer userId) {
        ResultInfo<Map<String, Object>> resultInfo = new ResultInfo<>();

        // 通过月份分类查询云记数量
        List<NoteVo> noteVos = noteDao.findNoteCountByDate(userId);

        // 判断集合是否存在
        if (noteVos != null && noteVos.size() > 0) {
            // 得到月份
            List<String> monthList = new ArrayList<>();
            // 得到云记集合
            List<Integer> noteCountList = new ArrayList<>();

            // 遍历月份分组集合
            for (NoteVo noteVo : noteVos) {
                monthList.add(noteVo.getGroupName());
                noteCountList.add((int)noteVo.getNoteCount());
            }

            // 准备Map对象，封装对应的月份与云记数量
            Map<String, Object> map = new HashMap<>();
            map.put("monthArray", monthList);
            map.put("dataArray", noteCountList);

            // 将 map对象设置到ResultInfo对象中
            resultInfo.setCode(1);
            resultInfo.setResult(map);
        }

        return resultInfo;
    }

    /**
     * 查询用户发布云记时的坐标
     * @param userId
     * @return
     */
    public ResultInfo<List<Note>> queryNoteLonAndLat(Integer userId) {
        ResultInfo<List<Note>> resultInfo = new ResultInfo<>();

        // 通过用户id查询云记列表
        List<Note> noteList = noteDao.queryNoteList(userId);

        // 判断是否为空
        if (noteList != null && noteList.size() > 0) {
            resultInfo.setCode(1);
            resultInfo.setResult(noteList);
        }

        return resultInfo;
    }
}
