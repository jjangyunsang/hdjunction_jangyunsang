package com.hdjunction.project.yunsang.domain.hospital.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity(name = "code")
@DynamicInsert
@DynamicUpdate
@Getter
@NoArgsConstructor
public class Code {
    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "code_name")
    private String codeName;
    @ManyToOne
    @JoinColumn(name = "code_group")
    private CodeGroup codeGroup;
}
