package com.rga.fireant.service;

import com.rga.fireant.model.Execution;

public interface UpdateExecution {

    Execution update(long caseId, Execution execution);
}
