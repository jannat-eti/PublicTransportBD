package com.example.jsonconvert.testPackage;

public class EmptyItem extends AbstractItem {

    public EmptyItem(String label , String id , String uid ) {
        super(label , id , uid);
    }


    @Override
    public int getType() {
        return TYPE_EMPTY;
    }

}
