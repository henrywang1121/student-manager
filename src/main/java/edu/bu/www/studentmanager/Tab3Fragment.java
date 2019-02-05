package edu.bu.www.studentmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class Tab3Fragment extends Fragment {
    private static final String TAG = "Tab3Fragment";

    private Button fragment3Button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3_fragment,container,false);
        fragment3Button = (Button) view.findViewById(R.id.fragment3Button);

        fragment3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Fragment 3",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
