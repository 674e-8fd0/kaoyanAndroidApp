package com.androidbook.kaoyanapp.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class ArtittleReponse {
    List<ArtitleBean> artitleBeans;

    public ArtittleReponse(List<ArtitleBean> artitleBeans) {
        this.artitleBeans = artitleBeans;
    }

    public List<ArtitleBean> getArtitleBeans() {
        return artitleBeans;
    }

    public void setArtitleBeans(List<ArtitleBean> artitleBeans) {
        this.artitleBeans = artitleBeans;
    }
}
