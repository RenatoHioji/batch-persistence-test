package com.batch.entity;

import com.batch.dto.BatchDTORequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "batch_entity")
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class BatchEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    @Column(name = "id")
    String id;

    @Column(name = "data")
    String data;


    public BatchEntity(BatchDTORequest request){
         this.data = request.data();
    }
}
