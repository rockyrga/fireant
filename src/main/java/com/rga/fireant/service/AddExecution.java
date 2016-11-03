package com.rga.fireant.service;

import com.rga.fireant.model.Execution;

public interface AddExecution {

    Execution add(long caseId, Execution execution);
}
