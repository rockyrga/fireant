package com.rga.fireant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rga.fireant.model.TestCase;
import com.rga.fireant.model.data.TestCaseRepository;

@Service
public class CreateTestCaseImpl implements CreateTestCase {

    private final TestCaseRepository repository;

    @Autowired
    public CreateTestCaseImpl(TestCaseRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = false)
    public TestCase create(TestCase testCase) {
        return repository.save(testCase);
    }

}
