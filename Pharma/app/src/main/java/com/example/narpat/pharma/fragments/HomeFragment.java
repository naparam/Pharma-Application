package com.example.narpat.pharma.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.narpat.pharma.R;
import com.example.narpat.pharma.activity.HomeActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    Integer[] iconIds={
            R.drawable.place_order,
            R.drawable.pending_order,
            R.drawable.not_available_drugs,
            R.drawable.pending_bills,
    };

    String[] labels={
            "Place Order",
            "Pending Order",
            "Not Available Drugs",
            "Pending Bills"

    };

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GridView gv=(GridView) getView().findViewById(R.id.grid_view);
        //set the adapter
        gv.setAdapter(new IconAdapter(getActivity()));
    }

    public class IconAdapter extends BaseAdapter {

        private Context context;

        public IconAdapter(Context ctx){

            context=ctx;

        }


        @Override
        public int getCount() {
            return iconIds.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            if (convertView==null){
                view=new View(context);
                view=inflater.inflate(R.layout.grid_list,null);


                TextView tv= (TextView)view.findViewById(R.id.image_label);
                ImageView iv= (ImageView)view.findViewById(R.id.image_view);

                iv.setImageResource(iconIds[position]);
                tv.setText(labels[position]);
            }
            else {
                view=convertView;
            }

            //Tanzeel - Set a listener on each item of grid
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Tanzeel - Handling click of grid icons
                    // We need to check labels here and do something similar to whats done in HomeActivity on navigationItem click
                    // Here "v" == the view clicked which is the grid view item.. So i can use that to identify which is the text choosen by user
                    TextView tv= (TextView)v.findViewById(R.id.image_label);
                    String label = tv.getText().toString();
                    if(label.equals("Place Order")){
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentContainer, new OrderFragment()).commit();
                    }
                    else if (label.equals("Pending Order")){

                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentContainer, new PendingOrderFragment()).commit();
                    }
                    else if (label.equals("Not Available Drugs")){

                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentContainer, new DrugsNotAvailableFragment()).commit();
                    }
                    else if (label.equals("Pending Bills")){

                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentContainer, new PendingBillsFragment()).commit();
                    }

                }
            });
            return view;
        }
    }
}
