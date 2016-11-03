package com.rga.fireant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rga.fireant.model.data.TestCaseRepository;

@Service
public class DeleteTestCaseImpl implements DeleteTestCase {

    private final TestCaseRepository repository;

    @Autowired
    public DeleteTestCaseImpl(TestCaseRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(long id) {
        repository.delete(id);
    }

}
