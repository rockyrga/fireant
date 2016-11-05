package com.rga.fireant.service;

import java.util.List;

import com.rga.fireant.model.Execution;

public interface ListExecution {

    List<Execution> list(long caseId);
}
