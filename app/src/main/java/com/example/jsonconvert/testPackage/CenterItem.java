package com.example.jsonconvert.testPackage;

public class CenterItem extends AbstractItem {

    public CenterItem(String label , String id , String uid ) {
        super(label, id , uid );
    }


    @Override
    public int getType() {
        return TYPE_CENTER;
    }

}
