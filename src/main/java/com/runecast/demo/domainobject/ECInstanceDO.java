package com.runecast.demo.domainobject;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "EC2_Details")
public class ECInstanceDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ec2_id")
    private String ec2Id;

    @OneToMany(mappedBy = "ecInstanceDO", cascade = CascadeType.ALL)
    private Set<SecurityGrpDO> securityGrpDO;

    @OneToMany(mappedBy = "ecInstanceDO", cascade = CascadeType.ALL)
    private Set<VolumeDO> volumeDO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEc2Id() {
        return ec2Id;
    }

    public void setEc2Id(String ec2Id) {
        this.ec2Id = ec2Id;
    }

    public Set<SecurityGrpDO> getSecurityGrpDO() {
        return securityGrpDO;
    }

    public void setSecurityGrpDO(Set<SecurityGrpDO> securityGrpDO) {
        this.securityGrpDO = securityGrpDO;
    }

    public Set<VolumeDO> getVolumeDO() {
        return volumeDO;
    }

    public void setVolumeDO(Set<VolumeDO> volumeDO) {
        this.volumeDO = volumeDO;
    }
}
