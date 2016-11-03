package com.rga.fireant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rga.fireant.model.TestCase;
import com.rga.fireant.model.data.TestCaseRepository;

@Service
public class ListTestCaseImpl implements ListTestCase {

    private final TestCaseRepository repository;

    @Autowired
    public ListTestCaseImpl(TestCaseRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestCase> list() {
        return repository.findAll();
    }

}
