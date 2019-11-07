package com.hugong.ssm.service;

import com.hugong.ssm.domain.SysLog;

import java.util.List;

public interface ISysLogService {
    public void save(SysLog sysLog) throws Exception;

    public List<SysLog> findAll(int page,int size) throws Exception;
}
