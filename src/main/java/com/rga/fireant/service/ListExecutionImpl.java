package com.rga.fireant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rga.fireant.model.Execution;
import com.rga.fireant.model.TestCase;
import com.rga.fireant.model.data.ExecutionRepository;

@Service
public class ListExecutionImpl implements ListExecution {

    private final GetTestCase getTestCase;
    private final ExecutionRepository repository;

    @Autowired
    public ListExecutionImpl(GetTestCase getTestCase, ExecutionRepository repository) {

        this.getTestCase = getTestCase;
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Execution> list(long caseId) {

        TestCase testCase = getTestCase.get(caseId);
        return repository.findAllByTestCase(testCase);
    }

}
