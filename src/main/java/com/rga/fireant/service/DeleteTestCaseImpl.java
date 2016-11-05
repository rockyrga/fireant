package com.rga.fireant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rga.fireant.model.Execution;
import com.rga.fireant.model.TestCase;
import com.rga.fireant.model.data.ExecutionRepository;
import com.rga.fireant.model.data.TestCaseRepository;

@Service
public class DeleteTestCaseImpl implements DeleteTestCase {

    private final TestCaseRepository repository;
    private final ExecutionRepository executionRepository;

    @Autowired
    public DeleteTestCaseImpl(TestCaseRepository repository, ExecutionRepository executionRepository) {

        this.repository = repository;
        this.executionRepository = executionRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(long id) {

        TestCase testCase = repository.findOne(id);
        List<Execution> executions = executionRepository.findAllByTestCase(testCase);
        executionRepository.delete(executions);
        repository.delete(id);
    }

}
