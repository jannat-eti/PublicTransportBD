package com.example.jsonconvert.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jsonconvert.Bus;
import com.example.jsonconvert.R;

import java.util.List;

public class ViewDialog {

    String busListString = "";

    public void viewDialog(Activity activity, final TextView busListET, List<Bus> busList){

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);


        final CheckBox bikash = dialog.findViewById(R.id.CB_bus_bkash);
        final CheckBox bolaka = dialog.findViewById(R.id.CB_bus_bolaka);
        final CheckBox brtc = dialog.findViewById(R.id.CB_bus_brtc);
        final CheckBox provati = dialog.findViewById(R.id.CB_bus_provati);
        final CheckBox himaloy = dialog.findViewById(R.id.CB_bus_himaloy);
        final CheckBox skyline = dialog.findViewById(R.id.CB_bus_skyline);
        final CheckBox vip = dialog.findViewById(R.id.CB_bus_vip);
        final CheckBox ajmeri = dialog.findViewById(R.id.CB_bus_ajmeri);
        final CheckBox turag = dialog.findViewById(R.id.CB_bus_turag);
        final CheckBox raida = dialog.findViewById(R.id.CB_bus_raida);
        final CheckBox onabil = dialog.findViewById(R.id.CB_bus_onabil);
        final CheckBox asmani = dialog.findViewById(R.id.CB_bus_asmani);
        final CheckBox salsabil = dialog.findViewById(R.id.CB_bus_salsabil);
        final CheckBox pollobi = dialog.findViewById(R.id.CB_bus_pollobi);
        final CheckBox bhuiya = dialog.findViewById(R.id.CB_bus_bhuiya);
        final CheckBox boshumoti = dialog.findViewById(R.id.CB_bus_boshumoti);

        Button doneBTN = dialog.findViewById(R.id.BTN_done);
        Button cancelBTN = dialog.findViewById(R.id.BTN_cancel);

        cancelBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        doneBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bikash.isChecked()){

                    busListString = busListString + "BIKASH, ";

                } if (bolaka.isChecked()){

                    busListString = busListString + "BOLAKA, ";

                } if (brtc.isChecked()){

                    busListString = busListString + "BRTC, ";

                } if (provati.isChecked()){

                    busListString = busListString + "PROVATI, ";

                } if (himaloy.isChecked()){

                    busListString = busListString + "HIMALOY, ";

                } if (skyline.isChecked()){

                    busListString = busListString + "SKYLINE, ";

                } if (vip.isChecked()){

                    busListString = busListString + "VIP, ";

                } if (ajmeri.isChecked()){

                    busListString = busListString + "AJMERI, ";

                } if (turag.isChecked()){

                    busListString = busListString + "TURAG, ";

                } if (raida.isChecked()){

                    busListString = busListString + "RAIDA, ";

                } if (onabil.isChecked()){

                    busListString = busListString + "ONABIL, ";

                } if (asmani.isChecked()){

                    busListString = busListString + "ASMANI, ";

                } if (salsabil.isChecked()){

                    busListString = busListString + "SALSABIL, ";

                } if (pollobi.isChecked()){

                    busListString = busListString + "POLLOBI, ";

                } if (bhuiya.isChecked()){

                    busListString = busListString + "BHUIYA, ";

                } if (boshumoti.isChecked()){

                    busListString = busListString + "BOSHUMOTI, ";

                }



                busListET.setText(busListString);
                dialog.dismiss();

            }
        });


        dialog.show();

    }
}
