package com.rga.fireant.model.data;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.rga.fireant.model.Execution;
import com.rga.fireant.model.TestCase;

public interface ExecutionRepository extends AbstractRepository<Execution> {

    @Transactional
    Long deleteByTestCaseAndId(TestCase testCase, Long id);

    List<Execution> findAllByTestCase(TestCase testCase);

}
