package com.hdjunction.project.yunsang.domain.hospital.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "code_group")
@DynamicInsert
@DynamicUpdate
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
    @OneToMany(mappedBy = "codeGroup", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Code> code = new ArrayList<>();
}
