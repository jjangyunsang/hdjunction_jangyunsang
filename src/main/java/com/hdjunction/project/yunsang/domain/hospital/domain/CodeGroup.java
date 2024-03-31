package com.hdjunction.project.yunsang.domain.hospital.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "code_group")
@Getter
@NoArgsConstructor
public class CodeGroup {
    @Id
    @Column(name = "code_group")
    private String codeGroup;
    @Column(name = "code_group_name")
    private String codeGroupName;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "codeGroup")
    private List<Code> code = new ArrayList<>();
}
