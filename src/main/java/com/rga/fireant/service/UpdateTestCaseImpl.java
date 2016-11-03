package com.rga.fireant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rga.fireant.model.TestCase;
import com.rga.fireant.model.data.TestCaseRepository;

@Service
public class UpdateTestCaseImpl implements UpdateTestCase {

    private final TestCaseRepository repository;

    @Autowired
    public UpdateTestCaseImpl(TestCaseRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = false)
    public TestCase update(TestCase testCase) {
        return repository.save(testCase);
    }

}
