package com.rga.fireant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rga.fireant.model.Execution;
import com.rga.fireant.model.TestCase;
import com.rga.fireant.model.data.ExecutionRepository;

@Service
public class UpdateExecutionImpl implements UpdateExecution {

    private final GetTestCase getTestCase;
    private final ExecutionRepository repository;

    @Autowired
    public UpdateExecutionImpl(GetTestCase getTestCase, ExecutionRepository repository) {

        this.getTestCase = getTestCase;
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = false)
    public Execution update(long caseId, Execution execution) {

        TestCase testCase = getTestCase.get(caseId);
        if (testCase.getExecutions().stream().noneMatch(e -> e.getId() == execution.getId())) {
            // throw exception
        }

        return repository.save(execution);
    }
}
