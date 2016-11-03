package com.rga.fireant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rga.fireant.model.Execution;
import com.rga.fireant.model.TestCase;
import com.rga.fireant.model.data.ExecutionRepository;

@Service
public class AddExecutionImpl implements AddExecution {

    private final GetTestCase getTestCase;
    // private final UpdateTestCase updateTestCase;
    private final ExecutionRepository executionRepository;

    @Autowired
    public AddExecutionImpl(GetTestCase getTestCase, ExecutionRepository executionRepository) {

        this.getTestCase = getTestCase;
        this.executionRepository = executionRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public Execution add(long caseId, Execution execution) {

        TestCase testCase = getTestCase.get(caseId);
        execution.setTestCase(testCase);

        return executionRepository.save(execution);
    }
}
