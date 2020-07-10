package com.example.jsonconvert.testPackage;

public  class itemModel {


    private String label;
    private String id ;
    private String uid ;
    long type ;

    public itemModel() {
    }

    public itemModel(String label, String id, String uid, long type) {
        this.label = label;
        this.id = id;
        this.uid = uid;
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }
}
