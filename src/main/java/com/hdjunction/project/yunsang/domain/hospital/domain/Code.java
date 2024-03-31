package com.hdjunction.project.yunsang.domain.hospital.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "code")
@Getter
@NoArgsConstructor
public class Code {
    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "code_name")
    private String codeName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_group")
    private CodeGroup codeGroup;
}
