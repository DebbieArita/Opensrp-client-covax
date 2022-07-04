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
    public TextView textViewReferralDay;
    public TextView textViewAddressGender;
    public TextView textViewChildAge;
    public Button dueButton;
    public View dueButtonLayout;
    public View childColumn;
    public ImageView goToProfileImage;
    public View goToProfileLayout;

    public ChildRegisterViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewChildName = itemView.findViewById(R.id.text_view_child_name);
        textViewAddressGender = itemView.findViewById(R.id.text_view_address_gender);
//        textViewReferralDay = itemView.findViewById(R.id.text_view_referral_day);
        textViewChildAge = itemView.findViewById(R.id.text_view_child_age);
        dueButton = itemView.findViewById(R.id.due_button);
        dueButtonLayout = itemView.findViewById(R.id.due_button_wrapper);
//        goToProfileImage = itemView.findViewById(R.id.go_to_profile_image_view);
//        goToProfileLayout = itemView.findViewById(R.id.go_to_profile_layout);
    }
}
