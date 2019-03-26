
package com.urjc.daw.practica.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class QuoteImage {

    @Id
    @GeneratedValue
    private Long id;

    private String imageName;

    @Lob
    private byte[] image;

    public  QuoteImage(){
        //JPA NEEDS THIS
    }
    public QuoteImage(String fileName, byte[] bytes){
        this.imageName=fileName;
        this.image=bytes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
