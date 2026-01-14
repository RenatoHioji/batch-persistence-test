package com.batch.service;

import com.batch.dto.BatchDTORequest;
import com.batch.entity.BatchEntity;
import com.batch.repository.BatchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BatchService {

    private static final int BATCH_SIZE = 50;
    private final BatchRepository batchRepository;

    @PersistenceContext
    private EntityManager em;

    public BatchEntity create(BatchDTORequest batchDTORequest){
        return batchRepository.save(new BatchEntity(batchDTORequest));
    }

    @Transactional
    public void createBatch(List<BatchDTORequest> batchDTORequestList){
        int i = 0;

        for(BatchDTORequest batchDTORequest : batchDTORequestList){
            em.persist(new BatchEntity(batchDTORequest));

            if(++i % BATCH_SIZE == 0){
                em.flush();
                em.clear();
            }
        }
    }
}
