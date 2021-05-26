package com.runecast.demo.domainobject;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Security_grp_details")
public class SecurityGrpDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupName;

    private String groupId;

    @JsonIgnore
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "ec2_id")
    private ECInstanceDO ecInstanceDO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public ECInstanceDO getEcInstanceDO() {
        return ecInstanceDO;
    }

    public void setEcInstanceDO(ECInstanceDO ecInstanceDO) {
        this.ecInstanceDO = ecInstanceDO;
    }
}
