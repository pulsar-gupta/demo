package com.runecast.demo.domainobject;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "volume_rel")
public class VolumeDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String volumeId;

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

    public String getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(String volumeId) {
        this.volumeId = volumeId;
    }

    public ECInstanceDO getEcInstanceDO() {
        return ecInstanceDO;
    }

    public void setEcInstanceDO(ECInstanceDO ecInstanceDO) {
        this.ecInstanceDO = ecInstanceDO;
    }
}
