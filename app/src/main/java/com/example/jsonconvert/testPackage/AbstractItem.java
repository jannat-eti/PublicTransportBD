package com.example.jsonconvert.testPackage;

public abstract class AbstractItem {

    public static final int TYPE_CENTER = 0;
    public static final int TYPE_EDGE = 1;
    public static final int TYPE_EMPTY = 2;


    private String label;
    private String id ;
    private String uid ;
    private String type  ;


    public AbstractItem() {
    }

    public AbstractItem(String label, String id, String uid) {
        this.label = label;
        this.id = id;
        this.uid = uid;
    }

    public AbstractItem(String label, String id, String uid, String type) {
        this.label = label;
        this.id = id;
        this.uid = uid;
        this.type = type;
    }

    public static int getTypeCenter() {
        return TYPE_CENTER;
    }

    public static int getTypeEdge() {
        return TYPE_EDGE;
    }

    public static int getTypeEmpty() {
        return TYPE_EMPTY;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getLabel() {
        return label;
    }

    abstract public int getType();




}
