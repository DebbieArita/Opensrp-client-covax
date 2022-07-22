package com.example.opensrp_client_covacs.holders;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.opensrp_client_covacs.R;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChildRegisterViewHolder extends RecyclerView.ViewHolder {

    public TextView textViewChildName;
    public TextView childOpensrpID;
    public TextView textViewChildAge;
    public TextView textViewAddressGender;
    public View childProfileInfoLayout;
    public Button dueButton;
    public View dueButtonLayout;
    public View registerColumns;
    public ImageView goToProfileImage;
    public View goToProfileLayout;

    public ChildRegisterViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewChildName = itemView.findViewById(R.id.text_view_child_name);
//        childOpensrpID = itemView.findViewById(R.id.child_zeir_id);
        textViewChildAge = itemView.findViewById(R.id.text_view_child_age);
        textViewAddressGender = itemView.findViewById(R.id.text_view_address_gender);
        childProfileInfoLayout = itemView.findViewById(R.id.patient_column);
        dueButton = itemView.findViewById(R.id.due_button);
        dueButtonLayout = itemView.findViewById(R.id.due_button_wrapper);
        registerColumns = itemView.findViewById(R.id.register_columns);

//        goToProfileImage = itemView.findViewById(R.id.go_to_profile_image_view);
//        goToProfileLayout = itemView.findViewById(R.id.go_to_profile_layout);
    }
}
