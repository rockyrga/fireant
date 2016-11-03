package com.rga.fireant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rga.fireant.model.TestCase;
import com.rga.fireant.model.data.ExecutionRepository;

@Service
public class DeleteExecutionImpl implements DeleteExecution {

    private final GetTestCase getTestCase;
    private final ExecutionRepository repository;

    @Autowired
    public DeleteExecutionImpl(GetTestCase getTestCase, ExecutionRepository repository) {

        this.getTestCase = getTestCase;
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(long caseId, long executionId) {

        TestCase testCase = getTestCase.get(caseId);
        if (testCase.getExecutions().stream().noneMatch(e -> e.getId() == executionId)) {
            // throw exception
        }

        repository.delete(executionId);
    }
}
