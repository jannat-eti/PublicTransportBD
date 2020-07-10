package com.example.jsonconvert.testPackage;

public class EdgeItem extends AbstractItem {

    public EdgeItem(String label , String id , String uid ) {
        super(label , id , uid );
    }



    @Override
    public int getType() {
        return TYPE_EDGE;
    }

}
