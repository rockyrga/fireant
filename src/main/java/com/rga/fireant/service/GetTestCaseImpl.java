package com.rga.fireant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rga.fireant.model.TestCase;
import com.rga.fireant.model.data.TestCaseRepository;

@Service
public class GetTestCaseImpl implements GetTestCase {

    private final TestCaseRepository repository;

    @Autowired
    public GetTestCaseImpl(TestCaseRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public TestCase get(long id) {
        return repository.findOne(id);
    }

}
