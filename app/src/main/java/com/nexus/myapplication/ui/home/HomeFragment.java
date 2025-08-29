package com.nexus.myapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.nexus.myapplication.activities.Home;
import com.nexus.myapplication.R;
import com.nexus.myapplication.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    String s;
    int src=-1;
    int dest=-1;


    private FragmentHomeBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel dashboardViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        AutoCompleteTextView autocomplete1 = (AutoCompleteTextView) root.findViewById(R.id.source1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line, Home.arr);

        autocomplete1.setThreshold(1);
        autocomplete1.setAdapter(adapter1);

        autocomplete1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                s=arg0.getItemAtPosition(arg2).toString();
                src=Integer.parseInt(s.charAt(s.length()-2)+""+s.charAt(s.length()-1)+"");
            }
        });


        AutoCompleteTextView autocomplete2 = (AutoCompleteTextView) root.findViewById(R.id.destination);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line, Home.arr);

        autocomplete2.setThreshold(1);
        autocomplete2.setAdapter(adapter2);

        autocomplete2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                s=arg0.getItemAtPosition(arg2).toString();
                dest=Integer.parseInt(s.charAt(s.length()-2)+""+s.charAt(s.length()-1)+"");
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}