package com.elena.myapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.elena.myapplication.AnimalItem;
import com.elena.myapplication.CSVReaderHelper;
import com.elena.myapplication.CardAdapter;
import com.elena.myapplication.databinding.FragmentHomeBinding;

import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.elena.myapplication.databinding.FragmentHomeBinding;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private CardAdapter adapter;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       // HomeViewModel homeViewModel =
        //        new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        recyclerView = binding.recyclerView; // Используем binding для доступа к RecyclerView
        if (recyclerView == null) {
            Log.e("ERROR", "RecyclerView is NULL! Check fragment_home.xml");
            return binding.getRoot();
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Чтение CSV и заполнение RecyclerView
        List<AnimalItem> animalList = CSVReaderHelper.readCSVFromAssets(getActivity(), "csv/data_processed.csv");
        adapter = new CardAdapter(getActivity(), animalList);
        recyclerView.setAdapter(adapter);

        return binding.getRoot(); // Возвращаем корень layout-файла
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}