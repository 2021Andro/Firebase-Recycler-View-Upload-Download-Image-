package com.example.demorecfir.Classes;

public class Demo {
    private String image, name;

    public Demo() {
    }

    public Demo(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "image='" + image + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
